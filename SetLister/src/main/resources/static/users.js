/**
 * Functionality to refresh the user list
 */

function createUserLI(u) {
	console.log("> createUserLI()", u)
	var username = u.username
	var deletebutton = "<button>delete</button>"
	var editbutton = "<button>edit</button>"
	var cells = [username, deletebutton, editbutton]
	var row = cells.map(c => "<td>" + c + "</td>").join()
	var result = "<tr>" + row + "</tr>"
	return result
}

function refreshUserList() {
	console.log("> refreshUserList()")
	fetch('http://localhost:8080/users')
		.then(response => {
			console.log("= refreshUserList()");
			response.json()
				.then(data => {
					var users = data.map(createUserLI);
					var table = "<table>" + users.join() + "</table>"
					console.log()
					document.getElementById("userlist").innerHTML = "table"
				})
		})
}

/**
 * Functionality to create a new user
 */

function setupNewUserButton() {
	console.log("> setupNewUserButton()")
	document.getElementById("newuserbutton").setAttribute("onclick", "createNewUser()");
}

function createNewUser() {
	console.log("> createNewUser()")
	fetch('http://localhost:8080/users',
		{
			method: 'POST'
		}
	).then(response => response.json())
		.then(data => function() {
			// Put new user edit form
			// editUser(data.key)
			refreshUserList()
		})
}


refreshUserList()
setupNewUserButton()
console.log("users.js seen.")
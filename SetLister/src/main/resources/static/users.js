/**
* Static data
*/

var editedUser = {}
/**
 * Functionality to refresh the user list
 */

function createUserLI(u) {
	console.log("> createUserLI()", u)
	var username = u.username
	var deletebutton = '<button onclick="deleteUser(\'' + u.key + '\')">delete</button>'
	var editbutton = '<button onclick="editUser(\'' + u.key + '\')">edit</button>'
	var cells = [username, deletebutton, editbutton]
	var row = cells.map(c => "<td>" + c + "</td>").join('')
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
					var table = "<table>" + users.join('') + "</table>"
					document.getElementById("userlist").innerHTML = table
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
		.then(data => {
			// Put new user edit form
			editUser(data.key)
			refreshUserList()
		})
}

/**
 * Functionality to delete a user
 */

function deleteUser(key) {
	console.log("> deleteUser(" + key + ")")
	fetch('http://localhost:8080/users/' + key,
		{
			method: 'DELETE'
		}
	).then(
		refreshUserList()
	)
}

/**
 * Functionality to edit a user
 */

function editUser(key) {
	console.log("> editUser(" + key + ")")
	fetch('http://localhost:8080/users/' + key,
		{
			method: 'GET'
		}
	).then(response => response.json())
		.then(data =>
			refreshEditedUser(data)
		)
}

function refreshEditedUser(user) {
	editedUser = user
	if (user.username != null)
		document.getElementById('edituser-username').value = user.username
	if (user.password != null)
		document.getElementById('edituser-password').value = user.password
	if (user.role != null)
		document.getElementById('edituser-role').value = user.role
	if (user.key != null)
		editedUserKey = user.key
	document.getElementById('edituser-update').setAttribute("onclick", "updateUser('" + user.key + "')")
}

function updateUser(key) {
	console.log("> editUser(" + key + ")")
	editedUser.username = document.getElementById('edituser-username').value
	editedUser.password = document.getElementById('edituser-password').value
	editedUser.role = document.getElementById('edituser-role').value
	
	fetch('http://localhost:8080/users/' + key,
		{
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json'
				// 'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: JSON.stringify(editedUser)
		}
	).then(
		refreshUserList()
	)
}


refreshUserList()
setupNewUserButton()
console.log("users.js seen.")
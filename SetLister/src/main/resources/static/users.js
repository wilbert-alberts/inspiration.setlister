/**
 * 
 */

var uidEditedUser = null

function formatUser(value) {

	var nameField = document.createElement('td')
	nameField.innerHTML = value.username
	var passwordField = document.createElement('td')
	passwordField.innerHTML = value.password
	var deleteButton = createDeleteUserButton(value)
	var editButton = createEditUserButton(value)
	var result = document.createElement('tr')
	result.appendChild(editButton)
	result.appendChild(nameField)
	result.appendChild(passwordField)
	result.appendChild(deleteButton)
	return result
}

function createDeleteUserButton(user) {
	var result = document.createElement('td')
	result.innerHTML = '<button type="button" onclick="deleteUser(' + user.id + ')">Delete</button>'
	return result
}

function createEditUserButton(user) {
	var result = document.createElement('td')
	result.innerHTML = '<button type="button" onclick="editUser(' + user.id + ')">Edit</button>'
	return result
}

function updateUserList(ul) {
	console.log("Received users")
	formattedUsers = ul.map(formatUser)
	document.getElementById('userlist').innerHTML = ''
	formattedUsers.forEach(value =>
		document.getElementById('userlist').appendChild(value)
	)
	console.log(ul)
}

function requestUserList() {
	console.log("Fetching users")
	fetch('http://localhost:8080/users')
		.then(response => response.json())
		.then(data => updateUserList(data))
}


function deleteUser(uid) {
	console.log("Delete user " + uid)
	if (uid == uidEditedUser) {
		uidEditedUser = null
		document.getElementById('uname').value = ''
		document.getElementById('pw').value = ''
	}
	fetch('http://localhost:8080/users/' + uid,
		{
			method: 'DELETE'
		})
		.then(response => requestUserList())
}


function createNewUser() {
	console.log("Create user ")
	formdata = '{ "username":"null", "password":"null" }'
	fetch('http://localhost:8080/users/',
		{
			method: 'POST',
			body: formdata,
			headers: {
				'Content-Type': 'application/json'
				// 'Content-Type': 'application/x-www-form-urlencoded',
			}
		})
		.then(response => requestUserList())
}

function editUser(uid) {
	uidEditedUser = uid
	fetch('http://localhost:8080/users/' + uid)
		.then(response => response.json())
		.then(data => {
			document.getElementById('uname').value = data.username
			document.getElementById('pw').value = data.password
		})
}

function updateUser() {
	newName = document.getElementById('uname').value
	newPW = document.getElementById('pw').value
	formdata = {
		id: uidEditedUser,
		username: newName,
		password: newPW
	}

	fetch('http://localhost:8080/users/' + uidEditedUser,
		{
			method: 'PUT',
			body: JSON.stringify(formdata),
			headers: {
				'Content-Type': 'application/json'
				// 'Content-Type': 'application/x-www-form-urlencoded',
			}
		})
		.then(response => requestUserList())
}

console.log("users.js seen.")
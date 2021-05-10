/**
 * Functionality to refresh the song list
 */

var editedSong = {}

function createSongLI(u) {
	console.log("> createSongLI()", s)
	var songtitle = s.title
	var deletebutton = '<button onclick="deleteSong(\'' + s.key + '\')">delete</button>'
	var editbutton = '<button onclick="editSong(\'' + s.key + '\')">edit</button>'
	var cells = [songtitle, deletebutton, editbutton]
	var row = cells.map(c => "<td>" + c + "</td>").join('')
	var result = "<tr>" + row + "</tr>"
	return result
}

function refreshSongList() {
	console.log("> refreshSongList()")
	fetch('http://localhost:8080/songs')
		.then(response => {
			console.log("= refreshSongList()");
			response.json()
				.then(data => {
					var songs = data.map(createSongLI);
					var table = "<table>" + songs.join('') + "</table>"
					document.getElementById("songlist").innerHTML = table
				})
		})
}

/**
 * Functionality to create a new song
 */

function setupNewSongButton() {
	console.log("> setupNewSongButton()")
	document.getElementById("newsongbutton").setAttribute("onclick", "createNewSong()");
}

function createNewSong() {
	console.log("> createNewSong()")
	fetch('http://localhost:8080/songs',
		{
			method: 'POST'
		}
	).then(response => response.json())
		.then(data => {
			// Put new song edit form
			editSong(data.key)
			refreshSongList()
		})
}

/**
 * Functionality to delete a song
 */

function deleteSong(key) {
	console.log("> deleteSong(" + key + ")")
	fetch('http://localhost:8080/songs/' + key,
		{
			method: 'DELETE'
		}
	).then(
		refreshSongList()
	)
}

/**
 * Functionality to edit a song
 */

function durationToString(d) {
	var minutes = d / 60
	var seconds = d % 60
	var separator = ':'
	if (seconds < 10)
		separator = ':0'
	return minutes + separator + seconds
}

function stringToDuration(s) {
	const secondsRE = /^([0-9]+)$/
	const minutesSecondsRE = /^([0-9]+):([0-9]{2})$/

	if (secondsRE.test(s)) {
		return +s
	}
	if (minutesSecondsRE.test(s)) {
		var match = minutesSecondsRE.match(s)
		if (match != null) {
			if (match[2] < 60) {
				return match[1] * 60 + match[2]
			}
		}
	}
	return 0
}

function editSong(key) {
	console.log("> editSong(" + key + ")")
	fetch('http://localhost:8080/songs/' + key,
		{
			method: 'GET'
		}
	).then(response => response.json())
		.then(data =>
			refreshEditedSong(data)
		)
}

function refreshEditedSong(song) {
	console.log('> refreshEditedSong('+song+')')
	editedSong = song
	if (song.title != null)
		document.getElementById('editsong-title').value = song.title
	if (song.duration != null)
		document.getElementById('editsong-duration').value = durationToString(song.duration)
	if (song.score != null)
		document.getElementById('editsong-score').value = song.score
	if (song.tracks != null && song.tracks.length() > 0) {
//		document.getElementById('tracklist').appendChild(refreshTracklist(editedSong))
		document.getElementById('tracklist').children = refreshTracklist(editedSong)
	}
	document.getElementById('editsong-updatebutton').setAttribute("onclick", "updateSong('" + user.key + "')")
}

function refereshTrackList(song) {
	trackRows = song.tracks.map(createTrackRow)

	var result = document.createElement('table')
	result.children = trackRows
	return result
}

function createTrackRow(track) {
	var result = document.createElement('tr')
	result.setAttribute('class', 'trackrow')
	result.appendChild(createTrackKind(track))
	result.appendChild(createTrackURL(track))
	result.appendChild(createTrackDeleteBtn(track))
	return result
}

function createTrackKind(track) {
	var selectElement = document.createElemetn('select')
	selectElement.setAttribute('class', 'track-kind')
	selectElement.innerHTML = 
		'<option value="ACCOMPANIMENT">Begeleiding</option>'  +
		'<option value="REHEARSE_ALL">Oefenmateriaal - allen</option>' +
		'<option value="REHEARSE_SOPRANO">Oefenmateriaal - sopraan</option>' +
		'<option value="REHEARSE_ALTO">Oefenmateriaal - alt</option>' +
		'<option value="REHEARSE_TENOR">Oefenmateriaal - tenor</option>' +
		'<option value="REHEARSE_UNSPECIFIED">Overig</option>'
	selectElement.value = track.kind
	
	var result = document.createElement('td')
	result.appendChild(selectElement)
	return result
}

function createTrackURL(track) {
	var inputElement = document.createElement('input')
	inputElement.setAttribute('class', 'track-url')
	inputElement.setAttribute('type', 'text')
	inputElement.value = track.url
	
	var result = document.createElement('td')
	result.appendChild(inputElement)
	
	return result
}

function createTrackDeleteBtn(track) {
	var btnElement = document.createElement('button')
	btnElement.innerText = 'delete'
	
	var result = document.createElement('td')
	result.appendChild(btnElement)
	return result
}
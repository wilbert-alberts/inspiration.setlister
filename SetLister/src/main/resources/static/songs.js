/**
 * Functionality to refresh the song list
 */

var editedSong = {}
var trackCounter = 0

function createSongLI(s) {
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

function getTrackCounter() {
	var result = trackCounter
	trackCounter++
	return result
}

function clearTrackCounter() {
	trackCounter = 0
}

function durationToString(d) {
	var minutes = Math.floor(d / 60)
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
		var match = s.match(minutesSecondsRE)
		if (match != null) {
			if (match[2] < 60) {
				return parseInt(match[1], 10) * 60 + parseInt(match[2], 10)
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
	console.log('> refreshEditedSong(' + song + ')')
	editedSong = song
	document.getElementById('editsong-title').value = song.title
	document.getElementById('editsong-duration').value = durationToString(song.durationInSeconds)
	document.getElementById('editsong-score').value = song.scoreURL

	document.getElementById('tracklist').innerHTML = ''
	clearTrackCounter()
	document.getElementById('tracklist').appendChild(refreshTracklist(editedSong))

	document.getElementById('editsong-updatebutton').setAttribute("onclick", "updateSong('" + song.key + "')")
}

function refreshTracklist(song) {
	var result = document.createElement('table')
	result.setAttribute('id', 'tracktable')

	if (song.tracks != null && song.tracks.length > 0) {
		trackRows = song.tracks.map(createTrackRow)
		for (const r of trackRows) {
			result.appendChild(r)
		}
	}
	return result
}

function createTrackRow(track) {
	var result = document.createElement('tr')
	result.setAttribute('class', 'trackrow')
	result.id = 'track' + getTrackCounter()
	result.appendChild(createTrackKind(track))
	result.appendChild(createTrackURL(track))
	result.appendChild(createTrackDeleteBtn(track, result.id))

	return result
}

function createTrackKind(track) {
	var selectElement = document.createElement('select')
	selectElement.setAttribute('class', 'track-kind')
	selectElement.innerHTML =
		'<option value="ACCOMPANIMENT">Begeleiding</option>' +
		'<option value="REHEARSE_ALL">Oefenmateriaal - allen</option>' +
		'<option value="REHEARSE_SOPRANO">Oefenmateriaal - sopraan</option>' +
		'<option value="REHEARSE_ALTO">Oefenmateriaal - alt</option>' +
		'<option value="REHEARSE_TENOR">Oefenmateriaal - tenor</option>' +
		'<option value="UNSPECIFIED">Overig</option>'
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

function createTrackDeleteBtn(track, id) {
	var btnElement = document.createElement('button')
	btnElement.innerText = 'delete'
	btnElement.setAttribute('onclick', 'deleteTrack("' + id + '")')

	var result = document.createElement('td')
	result.appendChild(btnElement)
	return result
}

function setupNewTrackButton() {
	document.getElementById('editsong-addtrackbutton').setAttribute('onclick', 'addTrackToTable()')
}

function addTrackToTable() {
	var trackTable = document.getElementById('tracktable')
	track = {
		url: null,
		kind: 'UNSPECIFIED'
	}
	var row = createTrackRow(track)
	trackTable.appendChild(row)
}

function deleteTrack(ele) {
	var toBeDeleted = document.getElementById(ele)
	var parent = toBeDeleted.parentNode
	parent.removeChild(toBeDeleted)
}

function updateSong(key) {
	editedSong.title = document.getElementById('editsong-title').value
	editedSong.durationInSeconds = stringToDuration(document.getElementById('editsong-duration').value)
	editedSong.scoreURL = document.getElementById('editsong-score').value
	editedSong.tracks = []
	var tracks = document.getElementsByClassName('trackrow')
	for (tr of tracks) {
		addTrackToSong(editedSong, tr)
	}

	console.log('updating song: ', editedSong)
	fetch('http://localhost:8080/songs/' + key,
		{
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json'
				// 'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: JSON.stringify(editedSong)
		}
	).then(
		refreshSongList()
	)
}

function addTrackToSong(song, trackRow) {
	url = trackRow.getElementsByClassName('track-url').item(0).value
	if (url != null) {
		kind = trackRow.getElementsByClassName('track-kind').item(0).value
		if (kind == null)
			kind = 'UNSPECIFIED'
		song.tracks.push({
			url: url,
			kind: kind
		})
	}
}

refreshSongList()
setupNewSongButton()
setupNewTrackButton()
console.log('songs.js seen')

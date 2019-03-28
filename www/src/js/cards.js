function ok() {
	document.cookie = "secondCard=" + encodeURIComponent(document.getElementById("second").value);
	document.cookie = "groupCard=" + encodeURIComponent(document.getElementById("group").value);
	window.location.href = "playCards.html";
}
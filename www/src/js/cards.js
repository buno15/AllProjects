function ok() {
	document.cookie = "second=" + encodeURIComponent(document.getElementById("second").value);
	document.cookie = "group=" + encodeURIComponent(document.getElementById("group").value);
	window.location.href = "playCards.html";
}
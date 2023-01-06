function ok() {
	document.cookie = "secondNum=" + encodeURIComponent(document.getElementById("second").value);
	document.cookie = "digit=" + encodeURIComponent(document.getElementById("digit").value);
	document.cookie = "groupNum=" + encodeURIComponent(document.getElementById("group").value);
	window.location.href = "playNumbers.html";
}
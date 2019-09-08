<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';
$groupID = $_COOKIE['groupID'];
$groupPASS = "none0";
$newPass1 = "none0";
$newPass2 = "none0";

if (isset($_POST['groupPASS'])) {
	$groupPASS = $_POST['groupPASS'];
}
if (isset($_POST['newPass1'])) {
	$newPass1 = $_POST['newPass1'];
}
if (isset($_POST['newPass2'])) {
	$newPass2 = $_POST['newPass2'];
}
if (isValue($groupPASS) && isValue($newPass1) && isValue($newPass2)) {
	$db = getPDO();
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID' AND groupPASS='$groupPASS'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {
		if ($newPass1 == $newPass2) {
			$db = getPDO();
			$sql = "UPDATE Gro SET groupPASS = '$newPass1' WHERE groupID='$groupID' AND groupPASS = '$groupPASS'";
			$stmt = $db -> query($sql);

			setcookie("groupPASS", $newPass1);
			echo 'Change Pass<br>';
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-group.php'\" value=\"back\"/>";
		} else {
			echo 'Not match password<br>';
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-group-pass.php'\" value=\"back\"/>";
		}
	} else {
		echo 'Wrong password<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-group-pass.php'\" value=\"back\"/>";
	}
} else {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Change password</h1>";
	echo "<form action=\"edit-group-pass.php\" method=\"POST\">";
	echo "Current Password";
	echo "<input type=\"text\" name=\"groupPASS\"value=\"\" required>";
	echo "<br>New Password";
	echo "<input type=\"text\" name=\"newPass1\"value=\"\" required>";
	echo "<br>New Password(again)";
	echo "<input type=\"text\" name=\"newPass2\" value=\"\" required>";
	echo "<br>";
	echo "<input type=\"submit\" value=\"Save\">";
	echo "</form>";
}
?>
<html>
	<body></body>
</html>
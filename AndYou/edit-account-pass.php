<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';
$id = $_COOKIE['id'];
$pass = "none0";
$newPass1 = "none0";
$newPass2 = "none0";

if (isset($_POST['pass'])) {
	$pass = $_POST['pass'];
}
if (isset($_POST['newPass1'])) {
	$newPass1 = $_POST['newPass1'];
}
if (isset($_POST['newPass2'])) {
	$newPass2 = $_POST['newPass2'];
}

if (isValue($pass) && isValue($newPass1) && isValue($newPass2)) {
	$db = getPDO();
	$sql = "SELECT * FROM User WHERE id='$id' AND pass='$pass'";
	$stmt = $db -> query($sql);

	if ($stmt -> rowCount() > 0) {
		if ($newPass1 == $newPass2) {
			$sql = "UPDATE User SET pass = '$newPass1' WHERE id='$id' AND pass = '$pass'";
			$stmt = $db -> query($sql);
			echo 'Change Pass<br>';
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-account.php'\" value=\"back\"/>";
		} else {
			echo 'not match pass';
		}
	} else {
		echo 'wrong pass';
	}
} else {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Create a group</h1>";
	echo "<form action=\"edit-account-pass.php\" method=\"POST\">";
	echo "Current Password";
	echo "<input type=\"text\" name=\"pass\"value=\"\" required>";
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
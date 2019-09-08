<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';
$id = "none0";
$pass = "none0";
if (isset($_POST['id'])) {
	$id = $_POST['id'];
}
if (isset($_POST['pass'])) {
	$pass = $_POST['pass'];
}

if (isValue($id) && isValue($pass)) {
	if (isAccount($id)) {
		$sql = "DELETE FROM User WHERE id = '$id' AND pass='$pass'";
		$stmt = $db -> query($sql);
		echo 'Delete account<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='index.php'\" value=\"back\"/>";

		setcookie('id', "none0");
		setcookie('groupID', "none0");
	} else {
		echo 'Error<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='delete-account.php'\" value=\"back\"/>";
	}
} else {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Delete account</h1>";
	echo "<form action=\"delete-account.php\" method=\"POST\">";
	echo "ID";
	echo "<input type=\"text\" name=\"id\"value=\"\" required>";
	echo "<br>Password";
	echo "<input type=\"text\" name=\"pass\" value=\"\"required>";
	echo "<br>";
	echo "<input type=\"submit\" value=\"Delete\">";
	echo "</form>";
}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Delete account</title>
	</head>
	<body></body>
</html>
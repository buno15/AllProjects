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
$groupID = "";

if (isValue($id) && isValue($pass)) {
	$db = getPDO();
	$sql = "SELECT * FROM User WHERE id ='$id' AND pass = '$pass'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {
		foreach ($stmt as $row) {
			$id = $row['id'];
			$groupID = $row['groupID'];
		}
		setcookie('id', $id);
		setcookie('groupID', $groupID);
		header("Location: ./index.php");
		exit ;
	} else {
		echo 'ID does not exist.<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='signin.php'\" value=\"back\"/>";
	}
} else {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Sign in</h1>";
	echo "<form action=\"signin.php\" method=\"POST\">";
	echo "ID";
	if (isset($_COOKIE['id']) && $_COOKIE['id'] != "none0") {
		$id = $_COOKIE['id'];
		$pass = getAccountValue($id, "pass");
		echo "<input type=\"text\" name=\"id\"value=\"$id\" required>";
		echo "<br>Password";
		echo "<input type=\"text\" name=\"pass\" value=\"$pass\" required>";
	} else {
		echo "<input type=\"text\" name=\"id\"value=\"\" required>";
		echo "<br>Password";
		echo "<input type=\"text\" name=\"pass\" value=\"\"required>";
	}
	echo "<br>";
	echo "<input type=\"submit\" value=\"Sign in\">";
	echo "</form>";
}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Sign in</title>
	</head>
	<body>

	</body>
</html>
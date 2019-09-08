<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';

$id = "none0";
$groupID = $_COOKIE['groupID'];
$groupPASS = "none0";

if (isset($_POST['groupPASS'])) {
	$groupPASS = $_POST['groupPASS'];
}
if (isValue($groupPASS)) {
	$db = getPDO();
	$sql = "SELECT * FROM Gro WHERE groupID ='$groupID' AND groupPASS = '$groupPASS'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {
		setcookie('groupID', $groupID);
		setcookie('groupPASS', $groupPASS);

		header("Location: ./edit-group.php");
		exit ;
	} else {
		echo 'Wrong password<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='signin-group.php'\" value=\"back\"/>";
	}
} else {

	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Edit group</h1>";
	echo "<form action=\"signin-group.php\" method=\"POST\">";
	echo "Password";
	if (isset($_COOKIE['groupPASS']) && $_COOKIE['groupPASS'] != "none0") {
		$groupPASS = $_COOKIE['groupPASS'];
		echo "<input type=\"text\" name=\"groupPASS\"value=\"$groupPASS\" required>";
	} else {
		echo "<input type=\"text\" name=\"groupPASS\"value=\"\" required>";
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
		<title>Edit group</title>
	</head>
	<body></body>
</html>
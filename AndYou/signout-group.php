<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';
$id = "none0";
$groupID = "none0";

$flag = "";

if (isset($_COOKIE['id'])) {
	$id = $_COOKIE['id'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
}
if (isset($_GET['flag'])) {
	$flag = $_GET['flag'];
}

if ($flag == "signout") {
	if (isValue($id) && isValue($groupID)) {
		if (isAccount($id)) {
			setAccountValue($id, "groupID", "none0");
			setAccountValue($id, "reward", "0");

			setcookie('groupID', "none0");
			echo 'Leave group<br>';
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-account.php'\" value=\"back\"/>";
		} else {
			echo 'Error<br>';
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-account.php'\" value=\"back\"/>";
		}
	} else {
		echo 'Error<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-account.php'\" value=\"back\"/>";
	}
} else if ($flag == "conform") {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Leave group?</h1>";
	echo "<input type=\"button\" onclick=\"location.href='./edit-account.php'\" value=\"Cancel\">";
	echo "<input type=\"button\" onclick=\"location.href='./signout-group.php?flag=signout'\" value=\"OK\">";
} else {
	echo 'Error<br>';
	echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-account.php'\" value=\"back\"/>";
}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Leave group</title>
	</head>
	<body></body>
</html>
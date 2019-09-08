<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';

$id = $_COOKIE['id'];
$groupID = "none0";
$color = $_COOKIE['color'];
$beforeId = $_COOKIE['id'];
$beforeColor = getAccountValue($id, "color");
$groupNAME = "";

if (isset($_POST['id'])) {
	$id = $_POST['id'];
}
if (isset($_POST['color'])) {
	$color = $_POST['color'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
	if ($groupID != "none0")
		$groupNAME = getGroupValue($groupID, "groupNAME");
}

echo "<div id=\"head\">";
echo "<a href=\"index.php\"><img src=\"img/title.png\" alt=\"AndY-ou\" /></a>";
if (isValue($id)) {
	echo "<a href=\"edit-account.php\"><h2>";
	if ($id != "none0")
		echo $id;
	echo "</a></h2>";
}
echo "<h1>Setting</h1>";
echo "</div>";

if (isValue($id) && $beforeId != $id) {
	if (isAccount($id)) {
		echo "ID already exists.";
	} else {
		setAccountValue($beforeId, "id", $id);
		setcookie('id', $id);
	}
}
if (isValue($color) && $beforeColor != $color) {
	setAccountValue($id, "color", $color);
	setcookie("color", $color);
}

echo "<form action=\"edit-account.php\" method=\"POST\">";
echo "ID";
echo "<input type=\"text\" name=\"id\"value=\"$id\" required>";
echo "<input type=\"color\" name=\"color\" value=\"$color\" required>";
echo "<input type=\"submit\" value=\"Save\">";
echo "<br>";
echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-account-pass.php'\" value=\"Change password\"/>";
echo "<input type=\"button\" name=\"add\" onclick=\"location.href='delete-account.php'\" value=\"Delete account\"/>";
if (isset($_COOKIE['groupID']) && $_COOKIE['groupID'] != "none0")
	echo "<input type=\"button\" onclick=\"location.href='./signout-group.php?flag=conform'\" value=\"Leave " . $groupNAME . "\">";
echo "</form>";
?>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<title>Account</title>
	</head>
	<body></body>
</html>
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
<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';

$id = $_COOKIE['id'];
$groupID = "none0";
$color = "none0";
$beforeId = $_COOKIE['id'];
$beforeColor = getAccountValue($id, "color");
$groupNAME = "";

$flag = false;

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

if (isValue($id) && $beforeId != $id) {
	if (isAccount($id)) {
		$flag = true;
	} else {
		setAccountValue($beforeId, "id", $id);
		setcookie('id', $id);
		$beforeId = $id;
	}
}

echo "<div id=\"head\">";
echo "<ul>";
echo "<li>";
echo "<a href=\"index.php\"><img id=\"icon\" src=\"img/title.png\" alt=\"AndY-ou\"/></a>";
echo "</li>";
if (isValue($id)) {
	echo "<li><a href=\"edit-account.php\"><h2>$beforeId</h2></a></li>";
	echo "<li><a href=\"edit-account.php\"><img src=\"img/account.png\"/></a></li>";
}
echo "</ul>";
echo "</div>";
echo "<hr>";

echo "<div id=\"left\">";
echo "<div id=\"menu\">";
echo "<ul>";
echo "<li><a class=\"active\" >Menu</a></li>";
echo "<li><a href=\"edit-account.php\">Home</a></li>";
echo "<li><a href=\"edit-account-pass.php\">Change password</a></li>";
if ($id != "none0" && $groupID == "none0") {
	echo "<li><a href=\"create-group.php\">Create group</a></li>";
	echo "<li><a href=\"join-group.php\">Join group</a></li>";
}
if (isset($_COOKIE['groupID']) && $_COOKIE['groupID'] != "none0")
	echo "<li><a href=\"signout-group.php?flag=conform\">Leave group</a></li>";
if ($id != "none0") {
	echo "<li><a href=\"signout.php\">Sign out</a></li>";
}
echo "<li><a href=\"delete-account.php\">Delete account</a></li>";
echo "</ul>";
echo "</div>";
echo "</div>";

echo "<div id=\"pagebody\">";
if ($flag) {
	echo "<div class=\"error\">";
	echo "ID already exists.";
	echo "</div>";
}
if (isValue($color) && $beforeColor != $color) {
	setAccountValue($id, "color", $color);
	$beforeColor = $color;
}
echo "<form action=\"edit-account.php\" method=\"POST\">";
echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
echo "<input type=\"text\" name=\"id\" value=\"$beforeId\" placeholder=\"ID\" required>";
echo "</label>";
echo "</div>";
echo "<input id=\"color\" class=\"btn-flat-border\" type=\"color\" name=\"color\" value=\"$beforeColor\" required>";

echo "<div class=\"submit\">";
echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Save\">";
echo "</div>";
echo "</form>";
echo "</div>";
?>
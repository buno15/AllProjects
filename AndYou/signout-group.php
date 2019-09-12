<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<title>Setting</title>
	</head>
	<body></body>
</html><?php
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

echo "<div id=\"head\">";
echo "<ul>";
echo "<li>";
echo "<a href=\"index.php\"><img id=\"icon\" src=\"img/title.png\" alt=\"AndY-ou\"/></a>";
echo "</li>";
if (isValue($id)) {
	echo "<li><a href=\"edit-account.php\"><h2>$id</h2></a></li>";
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

if ($flag == "signout") {
	if (isValue($id) && isValue($groupID)) {
		if (isAccount($id)) {
			setAccountValue($id, "groupID", "none0");
			setAccountValue($id, "reward", "0");

			setcookie('groupID', "none0");
			echo "<div class=\"accept\">";
			echo "<h1>Accepted</h1>";
			echo "</div>";
		} else {
			echo "<div class=\"error\">";
			echo "<h1>Error</h1>";
			echo "</div>";
		}
	} else {
		echo "<div class=\"error\">";
		echo "<h1>Error</h1>";
		echo "</div>";
	}
} else if ($flag == "conform") {
	echo "<div class=\"warning\">";
	echo "<h1>Leave group?</h1>";
	echo "</div>";
	echo "<div class=\"submit\">";
	echo "<div class=\"conform\">";
	echo "<input type=\"button\" class=\"btn-flat-border\" onclick=\"location.href='./edit-account.php'\" value=\"Cancel\">";
	echo "</div>";
	echo "<div class=\"conform\">";
	echo "<input type=\"button\" class=\"btn-flat-border\" onclick=\"location.href='./signout-group.php?flag=signout'\" value=\"OK\">";
	echo "</div>";
	echo "</div>";
} else {
	echo 'Error<br>';
	echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-account.php'\" value=\"back\"/>";
}
echo "</div>";
?>
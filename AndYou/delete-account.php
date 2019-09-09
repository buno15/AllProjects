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
</html>
<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';
$id = $_COOKIE['id'];
$pass = "none0";
if (isset($_POST['id'])) {
	$id = $_POST['id'];
}
if (isset($_POST['pass'])) {
	$pass = $_POST['pass'];
}
$groupID;
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
}

echo "<div id=\"head\">";
echo "<ul>";
echo "<li>";
echo "<a href=\"index.php\"><img src=\"img/title.png\" alt=\"AndY-ou\"/></a>";
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
if (isValue($id) && isValue($pass)) {
	if (isAccount($id)) {
		$db = getPDO();
		$sql = "SELECT * FROM User WHERE id = '$id' AND pass='$pass'";
		$stmt = $db -> query($sql);
		if ($stmt -> rowCount() > 0) {
			$sql = "DELETE FROM User WHERE id = '$id' AND pass='$pass'";
			$stmt = $db -> query($sql);

			setcookie('id', "none0");
			setcookie('groupID', "none0");
			setcookie('groupPASS', "none0");
			setcookie('color', "none0");

			header("Location: ./index.php");
			exit ;
		} else {
			echo "<div class=\"error\">";
			echo "<h1>Error</h1>";
			echo "</div>";
			echo "<div class=\"submit\">";
			echo "<div class=\"conform\">";
			echo "<input class=\"btn-flat-border\" type=\"button\" name=\"add\" onclick=\"location.href='delete-account.php'\" value=\"Back\"/>";
			echo "</div>";
			echo "</div>";
		}
	} else {
		echo "<div class=\"error\">";
		echo "<h1>Error</h1>";
		echo "</div>";
		echo "<div class=\"submit\">";
		echo "<div class=\"conform\">";
		echo "<input class=\"btn-flat-border\" type=\"button\" name=\"add\" onclick=\"location.href='delete-account.php'\" value=\"Back\"/>";
		echo "</div>";
		echo "</div>";
	}
} else {
	echo "<div class=\"warning\">";
	echo "<h1>Delete account?</h1>";
	echo "</div>";

	echo "<form action=\"delete-account.php\" method=\"POST\">";
	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"id\" placeholder=\"ID\" required>";
	echo "</label>";
	echo "</div>";

	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"pass\" placeholder=\"Password\" required>";
	echo "</label>";
	echo "</div>";

	echo "<div class=\"submit\">";
	echo "<input  class=\"btn-flat-border\" type=\"submit\" value=\"Delete\">";
	echo "</div>";
	echo "</form>";
	echo "</div>";
}
?>
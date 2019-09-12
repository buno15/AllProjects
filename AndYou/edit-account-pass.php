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

$groupID;
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
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

if (isValue($pass) && isValue($newPass1) && isValue($newPass2)) {
	$db = getPDO();
	$sql = "SELECT * FROM User WHERE id='$id' AND pass='$pass'";
	$stmt = $db -> query($sql);

	if ($stmt -> rowCount() > 0) {
		if ($newPass1 == $newPass2) {
			$sql = "UPDATE User SET pass = '$newPass1' WHERE id='$id' AND pass = '$pass'";
			$stmt = $db -> query($sql);

			echo "<div class=\"accept\">";
			echo 'Accepted<br>';
			echo "</div>";
		} else {
			echo "<div class=\"error\">";
			echo 'Not match password';
			echo "</div>";
		}
	} else {
		echo "<div class=\"error\">";
		echo 'Wrong password';
		echo "</div>";
	}
}

echo "<form action=\"edit-account-pass.php\" method=\"POST\">";

echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
echo "<input type=\"text\" name=\"pass\" placeholder=\"Current password\" required>";
echo "</label>";
echo "</div>";

echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
echo "<input type=\"text\" name=\"newPass1\" placeholder=\"New password\" required>";
echo "</label>";
echo "</div>";

echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
echo "<input type=\"text\" name=\"newPass2\" placeholder=\"New password(again)\" required>";
echo "</label>";
echo "</div>";

echo "<div class=\"submit\">";
echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Save\">";
echo "</div>";
echo "</form>";
echo "</div>";
?>
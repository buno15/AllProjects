<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<title>Delete group</title>
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
$groupPASS = "none0";
if (isset($_POST['groupID'])) {
	$groupID = $_POST['groupID'];
}
if (isset($_POST['groupPASS'])) {
	$groupPASS = $_POST['groupPASS'];
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
echo "<li><a href=\"edit-group.php\">Home</a></li>";
echo "<li><a href=\"create-task.php\">Add task</a></li>";
echo "<li><a href=\"edit-group-setting.php\">Setting</a></li>";
echo "<li><a href=\"edit-group-pass.php\">Change password</a></li>";
echo "<li><a href=\"delete-group.php\">Delete group</a></li>";
echo "</ul>";
echo "</div>";
echo "</div>";

echo "<div id=\"pagebody\">";

if (isValue($groupID) && isValue($groupPASS)) {
	if ($groupID == $_COOKIE['groupID'] && $groupPASS == $_COOKIE['groupPASS']) {
		$db = getPDO();
		$sql = "SELECT * FROM Gro WHERE groupID ='$groupID' AND groupPASS = '$groupPASS'";
		$stmt = $db -> query($sql);
		if ($stmt -> rowCount() > 0) {
			$sql = "DELETE FROM Gro WHERE groupID ='$groupID' AND groupPASS = '$groupPASS'";
			$stmt = $db -> query($sql);

			setcookie('groupID', "none0");
			setcookie('groupPASS', "none0");

			header("Location: ./index.php");
			exit ;
		} else {
			echo "<div class=\"error\">";
			echo "<h1>Error</h1>";
			echo "</div>";
			echo "<div class=\"submit\">";
			echo "<div class=\"conform\">";
			echo "<input class=\"btn-flat-border\" type=\"button\" name=\"add\" onclick=\"location.href='delete-group.php'\" value=\"Back\"/>";
			echo "</div>";
			echo "</div>";
		}
	} else {
		echo "<div class=\"error\">";
		echo "<h1>Error</h1>";
		echo "</div>";
		echo "<div class=\"submit\">";
		echo "<div class=\"conform\">";
		echo "<input class=\"btn-flat-border\" type=\"button\" name=\"add\" onclick=\"location.href='delete-group.php'\" value=\"Back\"/>";
		echo "</div>";
		echo "</div>";
	}
} else {
	echo "<div class=\"warning\">";
	echo "<h1>Delete group?</h1>";
	echo "</div>";

	echo "<form action=\"delete-group.php\" method=\"POST\">";
	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"groupID\" placeholder=\"Group ID\" required>";
	echo "</label>";
	echo "</div>";

	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"groupPASS\" placeholder=\"Group password\" required>";
	echo "</label>";
	echo "</div>";

	echo "<div class=\"submit\">";
	echo "<input  class=\"btn-flat-border\" type=\"submit\" value=\"Delete\">";
	echo "</div>";
	echo "</form>";
	echo "</div>";
}
?>
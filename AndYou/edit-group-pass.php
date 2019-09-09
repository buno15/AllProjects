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

$id = $_COOKIE['id'];
require_once 'func.php';
$groupID = $_COOKIE['groupID'];
$groupPASS = "none0";
$newPass1 = "none0";
$newPass2 = "none0";

if (isset($_POST['groupPASS'])) {
	$groupPASS = $_POST['groupPASS'];
}
if (isset($_POST['newPass1'])) {
	$newPass1 = $_POST['newPass1'];
}
if (isset($_POST['newPass2'])) {
	$newPass2 = $_POST['newPass2'];
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

if (isValue($groupPASS) && isValue($newPass1) && isValue($newPass2)) {
	$db = getPDO();
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID' AND groupPASS='$groupPASS'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {
		if ($newPass1 == $newPass2) {
			$db = getPDO();
			$sql = "UPDATE Gro SET groupPASS = '$newPass1' WHERE groupID='$groupID' AND groupPASS = '$groupPASS'";
			$stmt = $db -> query($sql);

			setcookie("groupPASS", $newPass1);
			echo 'Change Pass<br>';
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-group.php'\" value=\"back\"/>";
		} else {
			echo 'Not match password<br>';
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-group-pass.php'\" value=\"back\"/>";
		}
	} else {
		echo 'Wrong password<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-group-pass.php'\" value=\"back\"/>";
	}
} else {
	echo "<form action=\"edit-group-pass.php\" method=\"POST\">";

	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"groupPASS\" placeholder=\"Current password\" required>";
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
}
?>
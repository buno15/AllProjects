<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<title>Group</title>
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
$groupID = $_COOKIE['groupID'];
$groupPASS = "none0";

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

echo "<div id=\"pagebody\">";

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
	echo "<form action=\"signin-group.php\" method=\"POST\">";
	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	if (isset($_COOKIE['groupPASS']) && $_COOKIE['groupPASS'] != "none0") {
		$groupPASS = $_COOKIE['groupPASS'];
		echo "<input type=\"text\" name=\"groupPASS\" placeholder=\"Group password\" value=\"$groupPASS\" required>";
	} else {
		echo "<input type=\"text\" name=\"groupPASS\" placeholder=\"Group password\" required>";
	}
	echo "</label>";
	echo "</div>";

	echo "<div class=\"submit\">";
	echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Sign in\">";
	echo "</div>";
	echo "</form>";
	echo "</div>";
}
?>
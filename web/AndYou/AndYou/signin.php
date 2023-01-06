<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<link rel="shortcut icon" href="img/icon.png">
		<title>Sign in</title>
	</head>
	<body>

	</body>
</html>
<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';
$id = "none0";
$pass = "none0";

if (isset($_POST['id'])) {
	$id = $_POST['id'];
}
if (isset($_POST['pass'])) {
	$pass = $_POST['pass'];
}
$groupID = "";

echo "<div id=\"head\">";
echo "<ul>";
echo "<li>";
echo "<a href=\"index.php\"><img id=\"icon\" src=\"img/title.png\" alt=\"AndY-ou\"/></a>";
echo "</li>";
echo "</ul>";
echo "</div>";
echo "<hr>";

echo "<div id=\"pagebody\">";

if (isValue($id) && isValue($pass)) {
	$db = getPDO();
	$sql = "SELECT * FROM User WHERE id ='$id' AND pass = '$pass'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {
		foreach ($stmt as $row) {
			$id = $row['id'];
			$groupID = $row['groupID'];
		}
		setcookie('id', $id);
		setcookie('groupID', $groupID);
		header("Location: ./index.php");
		exit ;
	} else {
		echo "<div class=\"error\">";
		echo "ID does not exist.";
		echo "</div>";
	}
}
echo "<form action=\"signin.php\" method=\"POST\">";

echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
if (isset($_COOKIE['id']) && $_COOKIE['id'] != "none0") {
	$id = $_COOKIE['id'];
	$pass = getAccountValue($id, "pass");
	echo "<input type=\"text\" name=\"id\" value=\"$id\" pattern=\"^[0-9A-Za-z]+$\" placeholder=\"ID\" required>";
	echo "</label>";
	echo "</div>";
	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"pass\" value=\"$pass\" pattern=\"^[0-9A-Za-z]+$\" placeholder=\"Password\" required>";
} else {
	echo "<input type=\"text\" name=\"id\" value=\"\" pattern=\"^[0-9A-Za-z]+$\" placeholder=\"ID\" required>";
	echo "</label>";
	echo "</div>";
	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"pass\" value=\"\" pattern=\"^[0-9A-Za-z]+$\" placeholder=\"Password\" required>";
}
echo "</label>";
echo "</div>";

echo "<div class=\"submit\">";
echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Sign in\">";
echo "</div>";
echo "</form>";
echo "</div>";
?>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<title>Sign up</title>
	</head>
	<body></body>
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

$groupID = "none0";
$reward = "0";
$color = "#cee4ae";

$db = getPDO();
$sql = "SELECT * FROM User WHERE id='$id'";
$stmt = $db -> query($sql);

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

echo "<div id=\"pagebody\">";

if (isValue($id) && isValue($pass)) {
	if ($stmt -> rowCount() > 0) {
		echo "<div class=\"error\">";
		echo 'ID already exists.<br>';
		echo "</div>";
	} else {
		$sql = "INSERT INTO User (id, pass, groupID, reward , color) VALUES (:id, :pass, :groupID ,:reward ,:color)";
		$stmt = $db -> prepare($sql);
		$stmt -> bindParam(':id', $id, PDO::PARAM_STR);
		$stmt -> bindParam(':pass', $pass, PDO::PARAM_STR);
		$stmt -> bindParam(':groupID', $groupID, PDO::PARAM_STR);
		$stmt -> bindParam(':reward', $reward, PDO::PARAM_STR);
		$stmt -> bindParam(':color', $color, PDO::PARAM_STR);
		$stmt -> execute();

		setcookie('id', $id);
		setcookie('groupID', $groupID);
		header("Location: ./index.php");
		exit ;
	}
}
echo "<form action=\"signup.php\" method=\"POST\">";
echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
echo "<input type=\"text\" name=\"id\" value=\"\" pattern=\"^[0-9A-Za-z]+$\" placeholder=\"ID\" required>";
echo "</label>";
echo "</div>";
echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
echo "<input type=\"text\" name=\"pass\" value=\"\" pattern=\"^[0-9A-Za-z]+$\" placeholder=\"Password\" required>";
echo "</label>";
echo "</div>";

echo "<div class=\"submit\">";
echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Sign up\">";
echo "</div>";
echo "</form>";
echo "</div>";
?>
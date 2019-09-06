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

if (isValue($id) && isValue($pass)) {
	if ($stmt -> rowCount() > 0) {
		echo 'ID already exists.<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='signup.php'\" value=\"back\"/>";
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
} else {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Sign up</h1>";
	echo "<form action=\"signup.php\" method=\"POST\">";
	echo "ID";
	echo "<input type=\"text\" name=\"id\"value=\"\" required>";
	echo "<br>Password";
	echo "<input type=\"text\" name=\"pass\" value=\"\" required>";
	echo "<br>";
	echo "<input type=\"submit\" value=\"Sign up\">";
	echo "</form>";
}
?>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Sign up</title>
	</head>
	<body></body>
</html>
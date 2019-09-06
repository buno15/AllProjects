<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';

$id = $_COOKIE['id'];
$groupID = "none0";
if (isset($_GET['groupID'])) {
	$groupID = $_GET['groupID'];
}

$db = getPDO();
$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
$stmt = $db -> query($sql);

if (isValue($groupID)) {
	if ($stmt -> rowCount() > 0) {
		$sql = "UPDATE User SET groupID = '$groupID',reward='0' WHERE id = '$id'";
		$stmt = $db -> query($sql);

		setcookie('groupID', $groupID);

		$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
		$stmt = $db -> query($sql);
		if ($stmt -> rowCount() > 0) {
			header("Location: ./index.php");
			exit ;
		}
	} else {
		echo 'Group does not exist.<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='join-group.php'\" value=\"back\"/>";
	}
} else {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Join a group</h1>";
	echo "<form action=\"join-group.php\" method=\"GET\">";
	echo "Group ID";
	echo "<input type=\"text\" name=\"groupID\"value=\"\" required>";
	echo "<br>";
	echo "<input type=\"submit\" value=\"Join\">";
	echo "</form>";
}
?>
<html>
	<meta charset="UTF-8" />
	<title>Join a group</title>
	<body></body>
</html>
<?php

require_once 'func.php';
$groupID = "none0";
$groupPASS = "none0";
if (isset($_POST['groupID'])) {
	$groupID = $_POST['groupID'];
}
if (isset($_POST['groupPASS'])) {
	$groupPASS = $_POST['groupPASS'];
}

if (isValue($groupID) && isValue($groupPASS)) {
	$db = getPDO();
	$sql = "SELECT * FROM Gro WHERE groupID ='$groupID' AND groupPASS = '$groupPASS'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {
		$sql = "DELETE FROM Gro WHERE groupID ='$groupID' AND groupPASS = '$groupPASS'";
		$stmt = $db -> query($sql);
		echo 'Delete group<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='index.php'\" value=\"back\"/>";

		setcookie('groupID', "none0");
		setcookie('groupPASS', "none0");
	} else {
		echo 'Error<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='delete-group.php'\" value=\"back\"/>";
	}
} else {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>Delete Group</h1>";
	echo "<form action=\"delete-group.php\" method=\"POST\">";
	echo "Group ID";
	echo "<input type=\"text\" name=\"groupID\"value=\"\" required>";
	echo "<br>Group Password";
	echo "<input type=\"text\" name=\"groupPASS\" value=\"\"required>";
	echo "<br>";
	echo "<input type=\"submit\" value=\"Delete\">";
	echo "</form>";
}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Delete group</title>
	</head>
	<body></body>
</html>
<?php
require_once 'func.php';

$id = $_COOKIE['id'];
$groupID = "none0";
$color = $_COOKIE['color'];
$beforeId = $_COOKIE['id'];
$beforeColor = getAccountValue($id, "color");
$groupNAME = "";

if (isset($_POST['id'])) {
	$id = $_POST['id'];
}
if (isset($_POST['color'])) {
	$color = $_POST['color'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
	if ($groupID != "none0")
		$groupNAME = getGroupValue($groupID, "groupNAME");
}

echo "<h1><a href=\"index.php\">AndYou</a></h1>";
echo "<h1>Setting</h1>";

if (isValue($id) && $beforeId != $id) {
	$db = getPDO();
	$sql = "SELECT * FROM User WHERE id='$id'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {
		echo "ID already exists.";
	} else {
		$sql = "UPDATE User SET id = '$id' WHERE id = '$beforeId' AND groupID = '$groupID'";
		$stmt = $db -> query($sql);
		setcookie('id', $id);
	}
}
if (isValue($color) && $beforeColor != $color) {
	$db = getPDO();
	$sql = "UPDATE User SET color = '$color' WHERE id = '$id' AND groupID = '$groupID'";
	$stmt = $db -> query($sql);
	setcookie('color', $color);
}

echo "<form action=\"edit-account.php\" method=\"POST\">";
echo "ID";
echo "<input type=\"text\" name=\"id\"value=\"$id\" required>";
echo "<input type=\"color\" name=\"color\" value=\"$color\" required>";
echo "<input type=\"submit\" value=\"Save\">";
echo "<br>";
echo "<input type=\"button\" name=\"add\" onclick=\"location.href='html/edit_account_pass.html'\" value=\"Change Password\"/>";
echo "<input type=\"button\" name=\"add\" onclick=\"location.href='html/delete_account.html'\" value=\"Delete account\"/>";
if (isset($_COOKIE['groupID']))
	echo "<input type=\"button\" onclick=\"location.href='./exit_group.php'\" value=\"Leave " . $groupNAME . "\">";
echo "</form>";
?>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8" />
		<title>Setting</title>
	</head>
	<body></body>
</html>
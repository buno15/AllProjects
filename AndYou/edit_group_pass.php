<?php
header('Content-Type: text/html; charset=UTF-8');

$groupID = $_COOKIE['groupID'];
$groupPASS = @$_POST['groupPASS'];
$newPass1 = $_POST['newPass1'];
$newPass2 = $_POST['newPass2'];

$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$sql = "SELECT * FROM Gro WHERE groupID='$groupID' AND groupPASS='$groupPASS'";
$stmt = $db -> query($sql);

if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	if ($newPass1 == $newPass2) {
		$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$sql = "UPDATE Gro SET groupPASS = '$newPass1' WHERE groupID='$groupID' AND groupPASS = '$groupPASS'";
		$stmt = $db -> query($sql);

		setcookie('groupPASS', $newPass1);
		echo 'Change Pass';
	} else {
		echo 'not match pass';
	}
} else {
	echo 'wrong pass';
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='edit_group.php'" value="back"/>
	</body>
</html>
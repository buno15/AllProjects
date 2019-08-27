<?php
header('Content-Type: text/html; charset=UTF-8');

$id = @$_POST['id'];
$pass = @$_COOKIE['pass'];
$groupID = $_COOKIE['groupID'];
// パスワード

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
// SQLの組み立て
//$sql = "DELETE FROM User WHERE id=:id";

$sql = "SELECT * FROM User WHERE groupID='$groupID' AND pass='$pass'";
$stmt = $db -> query($sql);

if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$sql = "UPDATE User SET id = '$id' WHERE groupID = '$groupID' AND pass='$pass'";
	$stmt = $db -> query($sql);

	setcookie('id', $id);
	echo 'Change ID';
} else {
	echo 'wrong pass';
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='edit_account.php'" value="back"/>
	</body>
</html>
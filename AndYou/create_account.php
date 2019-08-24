<?php
header('Content-Type: text/html; charset=UTF-8');
$id = @$_POST['id'];
// ユーザID
$pass = @$_POST['pass'];

$groupID = "none0";
$reward = "0";
// パスワード

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
// SQLの組み立て
//$sql = "DELETE FROM User WHERE id=:id";

$sql = "SELECT * FROM User WHERE id='$id'";
$stmt = $db -> query($sql);

if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	echo 'アカウントが存在します';
} else {
	$sql = "INSERT INTO User (id, pass, groupID, reward) VALUES (:id, :pass, :groupID ,:reward)";
	$stmt = $db -> prepare($sql);
	$stmt -> bindParam(':id', $id, PDO::PARAM_STR);
	$stmt -> bindParam(':pass', $pass, PDO::PARAM_STR);
	$stmt -> bindParam(':groupID', $groupID, PDO::PARAM_STR);
	$stmt -> bindParam(':reward', $reward, PDO::PARAM_STR);
	$stmt -> execute();

	setcookie('id', $id);
	setcookie('pass', $pass);
	setcookie('groupID', $groupID);
	setcookie('reward', $reward);
	if ($groupID == "none0") {
		setcookie('groupNAME', "none0");
		setcookie('taskNAME', "none0");
		setcookie('taskREWARD', "none0");
		setcookie('doubletAMOUNT', "none0");
		setcookie('doubletREWARD', "none0");
	}

	echo '新規登録しました';
	echo "<br/>ID:";
	echo $id;
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="戻る"/>
	</body>
</html>
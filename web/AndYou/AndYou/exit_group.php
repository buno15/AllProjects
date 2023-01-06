<?php
if (isset($_COOKIE['id'])) {
	$id = $_COOKIE['id'];
}
if (isset($_COOKIE['pass'])) {
	$pass = $_COOKIE['pass'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
}
if (isset($_COOKIE['reward'])) {
	$reward = $_COOKIE['reward'];
}
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$sql = "SELECT * FROM User WHERE id ='$id' AND pass = '$pass'";
$stmt = $db -> query($sql);
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<?php
		if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
			$sql = "UPDATE User SET groupID = 'none0',reward='none0' WHERE id = '$id' AND pass='$pass'";
			$stmt = $db -> query($sql);

			echo '退会しました。';
			setcookie('groupID', "none0");
			setcookie('groupNAME', "none0");
			setcookie('taskNAME', "none0");
			setcookie('taskREWARD', "none0");
			setcookie('doubletAMOUNT', "none0");
			setcookie('doubletREWARD', "none0");
			setcookie('reward', "none0");
		} else {
			echo 'エラー';
		}
		?>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="戻る"/>
	</body>
</html>
<?php
$id = "";
$pass = "";
if (isset($_COOKIE['id'])) {
	$id = $_POST['id'];
}
if (isset($_COOKIE['pass'])) {
	$pass = $_POST['pass'];
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
			$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$sql = "DELETE FROM User WHERE id = '$id' AND pass='$pass'";
			$stmt = $db -> query($sql);

			echo 'Delete this account';
			setcookie('id', "none0");
			setcookie('pass', "none0");
			setcookie('groupID', "none0");
			setcookie('groupNAME', "none0");
			setcookie('groupPASS', "none0");
			setcookie('taskNAME', "none0");
			setcookie('taskREWARD', "none0");
			setcookie('doubletAMOUNT', "none0");
			setcookie('doubletREWARD', "none0");
			setcookie('reward', "0");
		} else {
			echo 'Error';
		}
		?>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="back"/>
	</body>
</html>
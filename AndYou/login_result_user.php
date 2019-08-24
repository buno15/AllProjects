<?php
header('Content-Type: text/html; charset=UTF-8');
$id = @$_POST['id'];
// ユーザID
$pass = @$_POST['pass'];
// パスワード
// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
// SQLの組み立て
$sql = "SELECT * FROM User WHERE id ='$id' AND pass = '$pass'";
$stmt = $db -> query($sql);
// クエリー実行
?>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<?php
		if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
			echo 'ログインしました。';

			foreach ($stmt as $row) {
				$id = $row['id'];
				$pass = $row['pass'];
				$groupID = $row['groupID'];
				$reward = $row['reward'];
			}
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
		} else {
			echo 'アカウントが存在しません。';
		}
		?>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="戻る"/>
	</body>
</html>
<?php
header('Content-Type: text/html; charset=UTF-8');
$groupID = $_COOKIE['groupID'];
$groupPASS = @$_POST['groupPASS'];
// パスワード
// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
// SQLの組み立て
$sql = "SELECT * FROM Gro WHERE groupID ='$groupID' AND groupPASS = '$groupPASS'";
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
			foreach ($stmt as $row) {
				$groupID = $row['groupID'];
				$groupPASS = $row['groupPASS'];
			}
			setcookie('groupID', $groupID);
			setcookie('groupPASS', $groupPASS);

			header("Location: ./edit_group.php");
			exit ;
		} else {
			setcookie('groupPASS', $groupPASS);
			echo 'パスワードが違います。';
		}
		?>
		<br />
		<input type="button" name="add" onclick="location.href='login_group.php'" value="戻る"/>
	</body>
</html>
<?php
$id = $_COOKIE['id'];
$pass = $_COOKIE['pass'];
$groupID = $_COOKIE['groupID'];
?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Please</title>
	</head>
	<body>
		<h1>IDとパスワードを入力してください</h1>
		<form action="login_result.php" method="POST">
			ユーザ名
			<input type="text" name="id" placeholder="id" value="<?php
			if ($id == "default") {
				echo "";
			}
			?>">
			<br>
			パスワード
			<input type="text" name="pass" placeholder="pass" value="<?php
			if ($pass == "default") {
				echo "";
			}
			?>">
			<br>
			<input type="submit" value="ログイン">
			<input type="button" name="add" onclick="location.href='html/create_account.html'" value="新規登録"/>

		</form>
	</body>
</html>
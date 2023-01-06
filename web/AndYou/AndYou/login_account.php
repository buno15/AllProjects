<?php

$id = "";
$pass = "";
$groupID = "";

if (isset($_COOKIE['id'])) {
	$id = $_COOKIE['id'];
}
if (isset($_COOKIE['pass'])) {
	$pass = $_COOKIE['pass'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Please</title>
	</head>
	<body>
		<h1><a href="index.php">AndYou</a></h1>
		<h1>IDとパスワードを入力してください</h1>
		<form action="login_account_result.php" method="POST">
			ユーザ名
			<input type="text" name="id" placeholder="id" value="<?php
			if ($id != "none0")
				echo $id;
			?>">
			<br>
			パスワード
			<input type="text" name="pass" placeholder="pass" value="<?php
			if ($pass != "none0")
				echo $pass;
			?>">
			<br>
			<input type="submit" value="ログイン">
			<input type="button" name="add" onclick="location.href='html/create_account.html'" value="新規登録"/>

		</form>
	</body>
</html>
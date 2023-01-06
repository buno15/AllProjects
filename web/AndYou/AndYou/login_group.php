<?php

$groupID = "";

if (isset($_COOKIE['groupPASS'])) {
	$groupPASS = $_COOKIE['groupPASS'];
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Please</title>
	</head>
	<body>
		<h1>パスワードを入力してください</h1>
		<form action="login_group_result.php" method="POST">
			パスワード
			<input type="text" name="groupPASS" placeholder="groupPASS" value="<?php
			if ($groupPASS != "none0")
				echo $groupPASS;
			?>">
			<br>
			<input type="submit" value="ログイン">
		</form>
	</body>
</html>
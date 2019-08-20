<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupID = $_COOKIE['groupID'];
$taskNAME = $_GET['taskNAME'];

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sql = "UPDATE Gro SET taskNAME = '$taskNAME' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

echo 'タスクを作成しました';
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="back"/>
	</body>
</html>
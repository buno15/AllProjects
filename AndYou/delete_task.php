<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupID = $_COOKIE['groupID'];
$taskNAME = $_GET['taskNAME'];
$taskREWARD = (int)$_GET['taskREWARD'];

$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

$afterTaskNAME = "";
$afterTaskREWARD = "";

for ($i = 0; $i < count($taskNAMEs); $i++) {
	if ($taskNAMEs[$i] != "none0" && $taskNAMEs[$i] != $taskNAME) {
		$afterTaskNAME .= $taskNAMEs[$i] . ",";
		$afterTaskREWARD .= $taskREWARDs[$i] . ",";
	}
}

if (mb_substr($afterTaskNAME, -1) == ",") {
	$afterTaskNAME = mb_substr($afterTaskNAME, 0, -1);
	$afterTaskREWARD = mb_substr($afterTaskREWARD, 0, -1);
}

if ($afterTaskNAME == null) {
	$afterTaskNAME = "none0";
	$afterTaskREWARD = "none0";
}

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sql = "UPDATE Gro SET taskNAME = '$afterTaskNAME' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET taskREWARD = '$afterTaskREWARD' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

setcookie('taskNAME', $afterTaskNAME);
setcookie('taskREWARD', $afterTaskREWARD);

echo 'タスクを削除しました';
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='edit_group.php'" value="back"/>
	</body>
</html>
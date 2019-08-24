<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupID = $_COOKIE['groupID'];
$taskNAME = $_GET['taskNAME'];
$taskREWARD = $_GET['taskREWARD'];

$afterTaskNAME = $_COOKIE['taskNAME'];
if ($taskNAME != "" && $taskREWARD != "") {
	if ($afterTaskNAME != "none0") {
		$afterTaskNAME .= "," . $taskNAME;
	} else {
		$afterTaskNAME = $taskNAME;
	}
	$afterTaskREWARD = $_COOKIE['taskREWARD'];
	if ($afterTaskREWARD != "none0") {
		$afterTaskREWARD .= "," . $taskREWARD;
	} else {
		$afterTaskREWARD = $taskREWARD;
	}
	$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$sql = "UPDATE Gro SET taskNAME = '$afterTaskNAME' WHERE groupID = '$groupID'";
	$stmt = $db -> query($sql);

	$sql = "UPDATE Gro SET taskREWARD = '$afterTaskREWARD' WHERE groupID = '$groupID'";
	$stmt = $db -> query($sql);

	setcookie('taskNAME', $afterTaskNAME);
	setcookie('taskREWARD', $afterTaskREWARD);

	echo 'タスクを作成しました';
} else {
	echo "タスクが不明です";
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='edit_group.php'" value="back"/>
	</body>
</html>
<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupID = $_COOKIE['groupID'];
$taskNAME = $_GET['taskNAME'];
$taskREWARD = $_GET['taskREWARD'];

$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

list($taskNAMEs, $taskREWARDs) = removeTask($taskNAMEs, $taskREWARDs, $taskNAME, $taskREWARD);
$afterTaskNAME = $taskNAMEs;
$afterTaskREWARD = $taskREWARDs;

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
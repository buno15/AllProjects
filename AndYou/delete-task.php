<?php

header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';

$groupID = $_COOKIE['groupID'];
$taskNAME = $_GET['taskNAME'];
$taskREWARD = $_GET['taskREWARD'];

$taskNAMEs = explode(",", getGroupValue($groupID, "taskNAME"));
$taskREWARDs = explode(",", getGroupValue($groupID, "taskREWARD"));

list($taskNAMEs, $taskREWARDs) = removeTask($taskNAMEs, $taskREWARDs, $taskNAME, $taskREWARD);
$afterTaskNAME = $taskNAMEs;
$afterTaskREWARD = $taskREWARDs;

setGroupValue($groupID, "taskNAME", $afterTaskNAME);
setGroupValue($groupID, "taskREWARD", $afterTaskREWARD);

header("Location: ./edit-group.php");
exit ;
?>
<html>
	<meta charset="UTF-8" />
	<title>AndYou</title>
	<body></body>
</html>
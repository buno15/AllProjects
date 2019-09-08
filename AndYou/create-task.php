<?php
header('Content-Type: text/html; charset=UTF-8');
require_once 'func.php';

$taskNAME = "none0";
$taskREWARD = "none0";
$groupID = $_COOKIE['groupID'];

if (isset($_GET['taskNAME']))
	$taskNAME = $_GET['taskNAME'];
if (isset($_GET['taskREWARD']))
	$taskREWARD = $_GET['taskREWARD'];

$taskNAMEs = explode(",", getGroupValue($groupID, "taskNAME"));
$taskREWARDs = explode(",", getGroupValue($groupID, "taskREWARD"));
$doTASKs = explode(",", getGroupValue($groupID, "doTASK"));

$afterTaskNAME = "";
$afterTaskREWARD = "";

$flag = true;

if (isValue($taskNAME) && isValue($taskREWARD)) {
	if (in_array($taskNAME, $taskNAMEs) || in_array($taskNAME, $doTASKs)) {
		echo "alredy exist<br>";
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='create-task.php'\" value=\"back\"/>";
	} else {
		for ($i = 0; $i < count($taskNAMEs); $i++) {
			if ($taskNAMEs[$i] == "neutral" && $flag) {
				$afterTaskNAME .= $taskNAME . ",";
				$afterTaskREWARD .= $taskREWARD . ",";
				$flag = false;
			} else if ($taskNAMEs[$i] != "none0") {
				$afterTaskNAME .= $taskNAMEs[$i] . ",";
				$afterTaskREWARD .= $taskREWARDs[$i] . ",";
			}
		}

		if (mb_substr($afterTaskNAME, -1) == ",") {
			$afterTaskNAME = mb_substr($afterTaskNAME, 0, -1);
			$afterTaskREWARD = mb_substr($afterTaskREWARD, 0, -1);
		}

		setGroupValue($groupID, "taskNAME", $afterTaskNAME);
		setGroupValue($groupID, "taskREWARD", $afterTaskREWARD);
		header("Location: ./edit-group.php");
		exit ;
	}
} else {
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<h1>New task</h1>";
	echo "<form action=\"create-task.php\" method=\"GET\">";
	echo "Task name";
	echo "<input type=\"text\" name=\"taskNAME\"value=\"\" required>";
	echo "<br>Task reward";
	echo "<input type=\"text\" name=\"taskREWARD\"value=\"\" required>";
	echo "<br>";
	echo "<input type=\"submit\" value=\"Save\">";
	echo "</form>";
}
?>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>New Task</title>
	</head>
	<body></body>
</html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<title>Account</title>
	</head>
	<body></body>
</html>
<?php
header('Content-Type: text/html; charset=UTF-8');
require_once 'func.php';

$id = $_COOKIE['id'];
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

echo "<div id=\"head\">";
echo "<ul>";
echo "<li>";
echo "<a href=\"index.php\"><img src=\"img/title.png\" alt=\"AndY-ou\"/></a>";
echo "</li>";
if (isValue($id)) {
	echo "<li><a href=\"edit-account.php\"><h2>$id</h2></a></li>";
	echo "<li><a href=\"edit-account.php\"><img src=\"img/account.png\"/></a></li>";
}
echo "</ul>";
echo "</div>";
echo "<hr>";

echo "<div id=\"left\">";
echo "<div id=\"menu\">";
echo "<ul>";
echo "<li><a class=\"active\" >Menu</a></li>";
echo "<li><a href=\"edit-group.php\">Home</a></li>";
echo "<li><a href=\"create-task.php\">Add task</a></li>";
echo "<li><a href=\"edit-group-setting.php\">Setting</a></li>";
echo "<li><a href=\"edit-group-pass.php\">Change password</a></li>";
echo "<li><a href=\"delete-group.php\">Delete group</a></li>";
echo "</ul>";
echo "</div>";
echo "</div>";

echo "<div id=\"pagebody\">";

if (isValue($taskNAME) && isValue($taskREWARD)) {
	if (in_array($taskNAME, $taskNAMEs) || in_array($taskNAME, $doTASKs)) {
		echo "<div class=\"error\">";
		echo "Task already exists.";
		echo "</div>";
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
}
echo "<form action=\"create-task.php\" method=\"GET\">";
echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
echo "<input type=\"text\" name=\"taskNAME\" value=\"\" placeholder=\"Task name\" required>";
echo "</label>";
echo "</div>";
echo "<div class=\"cp_iptxt\">";
echo "<label class=\"ef\">";
echo "<input type=\"number\" name=\"taskREWARD\" value=\"\" placeholder=\"Task reward\" required>";
echo "</label>";
echo "</div>";

echo "<div class=\"submit\">";
echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Add\">";
echo "</div>";
echo "</form>";
echo "</div>";
?>
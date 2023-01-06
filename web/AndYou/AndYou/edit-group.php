<?php
require_once 'func.php';

$id = $_COOKIE['id'];
$groupID = "";
$groupNAME = "";
$doubletAMOUNT = "";
$doubletREWARD = "";
$taskNAME = "";
$taskREWARD = "";
$doDATE = "";
$doTASK = "";
$doREWARD = "";
$doACCOUNT = "";
$start = "";

if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
	$groupNAME = getGroupValue($groupID, "groupNAME");
	$taskNAME = getGroupValue($groupID, "taskNAME");
	$taskREWARD = getGroupValue($groupID, "taskREWARD");
	$doDATE = getGroupValue($groupID, "doDATE");
	$doTASK = getGroupValue($groupID, "doTASK");
	$doREWARD = getGroupValue($groupID, "doREWARD");
	$doACCOUNT = getGroupValue($groupID, "doACCOUNT");
	$start = getGroupValue($groupID, "start");
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<link rel="shortcut icon" href="img/icon.png">
		<title>Group</title>
	</head>
	<body>
		<?php
		echo "<div id=\"head\">";
		echo "<ul>";
		echo "<li>";
		echo "<a href=\"index.php\"><img id=\"icon\" src=\"img/title.png\" alt=\"AndY-ou\"/></a>";
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
		echo "<li><a href=\"edit-group.php\">Group home</a></li>";
		echo "<li><a href=\"create-task.php\">Add task</a></li>";
		echo "<li><a href=\"edit-group-setting.php\">Setting</a></li>";
		echo "<li><a href=\"edit-group-pass.php\">Change password</a></li>";
		echo "<li><a href=\"delete-group.php\">Delete group</a></li>";
		echo "</ul>";
		echo "</div>";

		echo "<div id=\"group\">";
		echo "<h2>" . $groupNAME . "</h2>";
		echo "</div>";

		echo "<div id=\"member\">";
		echo "<ol>";
		if ($groupID != "none0") {
			$ids = array();
			$db = getPDO();
			$sql = "SELECT * FROM User WHERE groupID='$groupID'";
			$stmt = $db -> query($sql);

			if ($stmt -> rowCount() > 0) {
				foreach ($stmt as $row) {
					$ids[$row['id']] = $row['reward'];
				}
			}
			arsort($ids);

			foreach ($ids as $key => $value) {
				echo "<li>";
				echo $key . ' : ' . $value;
				echo "</li>";
			}

		}
		echo "</ol>";
		echo "</div>";
		echo "</div>";

		echo "<div id=\"pagebody\">";
		echo "<h2>Group ID : ";
		echo $groupID;
		echo "</h2>";
		echo "<div id=\"do-group\">";
		if (isValue($doDATE)) {
			$doDATEs = explode(",", $doDATE);
			$doTASKs = explode(",", $doTASK);
			$doREWARDs = explode(",", $doREWARD);
			$doACCOUNTs = explode(",", $doACCOUNT);

			for ($i = 0; $i < count($doDATEs); $i++) {
				if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start && $doTASKs[$i] != "BINGO") {
					echo "<a class=\"btn-do\" href=\"delete-do.php?doDATE=$doDATEs[$i]&doTASK=$doTASKs[$i]&doREWARD=$doREWARDs[$i]&doACCOUNT=$doACCOUNTs[$i]\">";
					echo "<p>" . $doDATEs[$i] . " : " . $doACCOUNTs[$i] . "<br>->" . $doTASKs[$i] . " : " . $doREWARDs[$i] . "</p>";
					echo "</a>";
				}
			}
		}
		echo "</div>";
		echo "</div>";

		echo "<div id=\"task\">";
		if (isValue($taskNAME)) {
			$taskNAMEs = explode(",", $taskNAME);
			$taskREWARDs = explode(",", $taskREWARD);

			for ($i = 0; $i < count($taskNAMEs); $i++) {
				if ($taskNAMEs[$i] != "none0" && mb_substr($taskNAMEs[$i], 0, 3) != "###" && $taskNAMEs[$i] != "neutral") {
					echo "<a class=\"btn-group-task\" href=\"delete-task.php?taskNAME=$taskNAMEs[$i]&taskREWARD=$taskREWARDs[$i]\">$taskNAMEs[$i] $taskREWARDs[$i]</a>";
				}
			}
		}
		echo "</div>";
		?>
	</body>
</html>
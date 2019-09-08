<?php
require_once 'func.php';

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
		<title>AndYou</title>
	</head>
	<body>
		<h1><a href="index.php">AndYou</a></h1>
		<?php
		echo "<h2>GroupID:";
		echo $groupID;
		echo "</h2>";

		echo "<h2>Group:";
		echo $groupNAME;
		echo "</h2>";

		/*
		 * $db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
		 $sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
		 $stmt = $db -> query($sql);
		 if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
		 foreach ($stmt as $row) {
		 $doubletAMOUNT = $row['doubletAMOUNT'];
		 $doubletREWARD = $row['doubletREWARD'];
		 }
		 setcookie('doubletAMOUNT', $doubletAMOUNT);
		 setcookie('doubletREWARD', $doubletREWARD);
		 }
		 *
		 * if (isset($_COOKIE['doubletAMOUNT'])) {
		 $doubletAMOUNTs = explode(",", $_COOKIE['doubletAMOUNT']);
		 $doubletREWARDs = explode(",", $_COOKIE['doubletREWARD']);

		 for ($i = 0; $i < count($doubletAMOUNTs); $i++) {
		 if ($doubletAMOUNTs[$i] != "none0") {
		 echo "<h3>Doublet:";
		 echo $doubletAMOUNTs[$i] . "yen->add" . $doubletREWARDs[$i];
		 echo "<input type=\"button\" onclick=\"location.href='delete_doublet.php?doubletAMOUNT=$doubletAMOUNTs[$i]&doubletREWARD=$doubletREWARDs[$i]'\" value=\"Delete\">";
		 echo "</h3>";
		 }
		 }
		 }*/
		if (isValue($taskNAME)) {
			$taskNAMEs = explode(",", $taskNAME);
			$taskREWARDs = explode(",", $taskREWARD);

			for ($i = 0; $i < count($taskNAMEs); $i++) {
				if ($taskNAMEs[$i] != "none0" && mb_substr($taskNAMEs[$i], 0, 3) != "###" && $taskNAMEs[$i] != "neutral") {
					echo "<h3>Task:";
					echo $taskNAMEs[$i] . "->" . $taskREWARDs[$i];
					echo "<input type=\"button\" onclick=\"location.href='delete-task.php?taskNAME=$taskNAMEs[$i]&taskREWARD=$taskREWARDs[$i]'\" value=\"Delete\">";
					echo "</h3>";
				}
			}
		}
		if (isValue($doDATE)) {
			$doDATEs = explode(",", $doDATE);
			$doTASKs = explode(",", $doTASK);
			$doREWARDs = explode(",", $doREWARD);
			$doACCOUNTs = explode(",", $doACCOUNT);

			for ($i = 0; $i < count($doDATEs); $i++) {
				if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start && $doTASKs[$i] != "BINGO") {
					echo "<h3>Do:";
					echo $doDATEs[$i] . "->" . $doTASKs[$i] . ":" . $doREWARDs[$i] . "->" . $doACCOUNTs[$i];
					echo "<input type=\"button\" onclick=\"location.href='delete-do.php?doDATE=$doDATEs[$i]&doTASK=$doTASKs[$i]&doREWARD=$doREWARDs[$i]&doACCOUNT=$doACCOUNTs[$i]'\" value=\"Delete\">";
					echo "</h3>";
				}
			}
		}
		?>
		<input type="button" name="add" onclick="location.href='./create-task.php'" value="Add task">
		<input type="button" name="add" onclick="location.href='./edit-group-setting.php'" value="Setting">
		<input type="button" name="add" onclick="location.href='edit-group-pass.php'" value="Change password"/>
		<?php
		echo "<input type=\"button\" onclick=\"location.href='delete-group.php'\" value=\"Delete " . $groupNAME . "\"/>";
		?>
	</body>
</html>
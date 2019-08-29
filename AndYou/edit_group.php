<?php
session_start();

$groupID = "";
$groupNAME = "";
$doubletAMOUNT = "";
$doubletREWARD = "";

if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
	$groupNAME = $_COOKIE['groupNAME'];

	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
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

		if (isset($_COOKIE['doubletAMOUNT'])) {
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
		}
		echo "<br/>";
		if (isset($_COOKIE['taskNAME'])) {
			$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
			$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

			for ($i = 0; $i < count($taskNAMEs); $i++) {
				if ($taskNAMEs[$i] != "none0") {
					echo "<h3>Task:";
					echo $taskNAMEs[$i] . "->" . $taskREWARDs[$i];
					echo "<input type=\"button\" onclick=\"location.href='delete_task.php?taskNAME=$taskNAMEs[$i]&taskREWARD=$taskREWARDs[$i]'\" value=\"Delete\">";
					echo "</h3>";
				}
			}
		}
		if (isset($_COOKIE['doDATE'])) {
			$doDATEs = explode(",", $_COOKIE['doDATE']);
			$doTASKs = explode(",", $_COOKIE['doTASK']);
			$doREWARDs = explode(",", $_COOKIE['doREWARD']);
			$doACCOUNTs = explode(",", $_COOKIE['doACCOUNT']);

			for ($i = 0; $i < count($doDATEs); $i++) {
				if ($doDATEs[$i] != "none0") {
					echo "<h3>Do:";
					echo $doDATEs[$i] . "->" . $doTASKs[$i] . ":" . $doREWARDs[$i] . "->" . $doACCOUNTs[$i];
					echo "<input type=\"button\" onclick=\"location.href='delete_do.php?doDATE=$doDATEs[$i]&doTASK=$doTASKs[$i]&doREWARD=$doREWARDs[$i]&doACCOUNT=$doACCOUNTs[$i]'\" value=\"Delete\">";
					echo "</h3>";
				}
			}
		}
		?>
		<input type="button" name="add" onclick="location.href='index.php'" value="戻る">
		<input type="button" name="add" onclick="location.href='./html/create_doublet.html'" value="ボーナス作成">
		<input type="button" name="add" onclick="location.href='./html/create_task.html'" value="Add new Task">
		<input type="button" name="add" onclick="location.href='./html/create_group_setting.html'" value="Setting">
		<input type="button" name="add" onclick="location.href='html/edit_group_pass.html'" value="Change Password"/>
		<?php
		echo "<input type=\"button\" onclick=\"location.href='html/delete_group.html'\" value=\"Delete " . $groupNAME . "\"/>";
		?>
	</body>
</html>
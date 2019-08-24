<?php
session_start();

$groupID = "";
$doubletAMOUNT = "";
$doubletREWARD = "";

if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];

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
		<?php
		echo "<h2>GroupID:";
		echo $groupID;
		echo "</h2>";

		echo "<h2>Group:";
		echo $_COOKIE['groupNAME'];
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
		?>
		<input type="button" name="add" onclick="location.href='index.php'" value="戻る">
		<input type="button" name="add" onclick="location.href='./html/create_doublet.html'" value="ボーナス作成">
		<input type="button" name="add" onclick="location.href='./html/create_task.html'" value="タスク追加">
	</body>
</html>
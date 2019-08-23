<?php
session_start();

$id = "";
$pass = "";
$groupID = "";
$groupNAME = "";
$taskNAME = "";
$taskREWARD = "";
$doubletAMOUNT = "";
$doubletREWARD = "";
/*include 'Data.php';

 class Data {
 public function get() {
 global $taskNAMEs;
 return $taskNAMEs;
 }

 public function set($array) {
 global $taskNAMEs;
 $taskNAMEs = $array;
 }

 }*/

if (isset($_COOKIE['id'])) {
	$id = $_COOKIE['id'];
}
if (isset($_COOKIE['pass'])) {
	$pass = $_COOKIE['pass'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
	if ($groupID == "none0") {
		setcookie('groupNAME', "none0");
		setcookie('taskNAME', "none0");
		setcookie('taskREWARD', "none0");
	}

	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功

		foreach ($stmt as $row) {
			$groupNAME = $row['groupNAME'];
			$taskNAME = $row['taskNAME'];
			$taskREWARD = $row['taskREWARD'];
			$doubletAMOUNT = $row['doubletAMOUNT'];
			$doubletREWARD = $row['doubletREWARD'];
		}
		setcookie('groupNAME', $groupNAME);
		setcookie('taskNAME', $taskNAME);
		setcookie('taskREWARD', $taskREWARD);
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
		<h1 >AndYou</h1>
		<h2>ID:<?php echo $id; ?></h2>
		<h2>Group:<?php echo $groupNAME; ?></h2>
		<?php
		if (isset($_COOKIE['taskNAME'])) {
			$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
			$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

			for ($i = 0; $i < count($taskNAMEs); $i++) {
				if ($taskNAMEs[$i] != "none0") {
					echo "<h3>Task:";
					echo $taskNAMEs[$i] . "->" . $taskREWARDs[$i];
					echo "<input type=\"button\" onclick=\"location.href='do.php?taskNAME=$taskNAMEs[$i]&taskREWARD=$taskREWARDs[$i]'\" value=\"Do\">";
					echo "</h3>";
				}
			}
		}
		?>
		<input type="button" name="add" onclick="location.href='login.php'" value="ログイン">
		<input type="button" name="add" onclick="location.href='./html/create_group.html'" value="グループ作成">
		<input type="button" name="add" onclick="location.href='./html/join_group.html'" value="グループ参加">
		<input type="button" name="add" onclick="location.href='./edit_group.php'" value="グループ編集">
		<input type="button" name="add" onclick="location.href='./html/create_task.html'" value="タスク追加">
	</body>
</html>
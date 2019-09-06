<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$id = $_COOKIE['id'];
$pass = $_COOKIE['pass'];
$color = $_COOKIE['color'];
if ($color == "none0") {
	$color = "#cee4ae";
	setAccountValue($id, $pass, 'color', $color);
}

$groupID = $_GET['groupID'];
$groupNAME = "";

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
$stmt = $db -> query($sql);

if ($stmt -> rowCount() > 0) {
	$sql = "UPDATE User SET groupID = '$groupID',reward='0',color='$color' WHERE id = '$id' AND pass='$pass'";
	$stmt = $db -> query($sql);

	setcookie('groupID', $groupID);
	setcookie('color', $color);

	$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
		foreach ($stmt as $row) {
			$groupNAME = $row['groupNAME'];
			$groupNAME = $row['groupNAME'];
			$taskNAME = $row['taskNAME'];
			$taskREWARD = $row['taskREWARD'];
			$doubletAMOUNT = $row['doubletAMOUNT'];
			$doubletREWARD = $row['doubletREWARD'];
		}
		setcookie('reward', "0");
		setcookie('groupNAME', $groupNAME);
		setcookie('taskNAME', $taskNAME);
		setcookie('taskREWARD', $taskREWARD);
		setcookie('doubletAMOUNT', $doubletAMOUNT);
		setcookie('doubletREWARD', $doubletREWARD);
	}

	echo 'グループに参加しました<br/>ID:';
	echo $groupNAME;
} else {
	echo 'グループが存在しません';
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="back"/>
	</body>
</html>
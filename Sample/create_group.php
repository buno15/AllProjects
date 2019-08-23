<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupNAME = @$_GET['groupNAME'];
$groupID = md5(uniqid(rand() . $groupNAME, 1));
$taskNAME = "none0";
$taskREWARD = "none0";
$doubletAMOUNT = "none0";
$doubletREWARD = "none0";

$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
$stmt = $db -> query($sql);

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	echo 'エラー：グループを作成できません。';
} else {
	$sql = "INSERT INTO Gro (groupID, groupNAME, taskNAME, taskREWARD, doubletAMOUNT, doubletREWARD) VALUES (:groupID, :groupNAME, :taskNAME, :taskREWARD, :doubletAMOUNT, :doubletREWARD)";
	$stmt = $db -> prepare($sql);
	$stmt -> bindParam(':groupID', $groupID, PDO::PARAM_STR);
	$stmt -> bindParam(':groupNAME', $groupNAME, PDO::PARAM_STR);
	$stmt -> bindParam(':taskNAME', $taskNAME, PDO::PARAM_STR);
	$stmt -> bindParam(':taskREWARD', $taskREWARD, PDO::PARAM_STR);
	$stmt -> bindParam(':doubletAMOUNT', $doubletAMOUNT, PDO::PARAM_STR);
	$stmt -> bindParam(':doubletREWARD', $doubletREWARD, PDO::PARAM_STR);
	$stmt -> execute();

	$id = $_COOKIE['id'];
	$sql = "UPDATE User SET groupID = '$groupID' WHERE id = '$id'";
	$stmt = $db -> query($sql);

	setcookie('groupID', $groupID);
	setcookie('groupNAME', $groupNAME);
	setcookie('taskNAME', $taskNAME);
	setcookie('taskREWARD', $taskREWARD);
	setcookie('doubletAMOUNT', $doubletAMOUNT);
	setcookie('doubletREWARD', $doubletREWARD);

	echo 'グループを作成しました';
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="back"/>
	</body>
</html>
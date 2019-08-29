<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupNAME = @$_GET['groupNAME'];
$groupID = md5(uniqid(rand() . $groupNAME, 1));
$groupPASS = @$_GET['groupPASS'];
$taskNAME = "none0";
$taskREWARD = "none0";
$doubletAMOUNT = "none0";
$doubletREWARD = "none0";
$period = "none0";
$bingoREWARD = "none0";
$doDATE = "none0";
$doTASK = "none0";
$doREWARD = "none0";
$doACCOUNT = "none0";

$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
$stmt = $db -> query($sql);

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	echo 'エラー：グループを作成できません。';
} else {
	$sql = "INSERT INTO Gro (groupID, groupPASS, groupNAME, taskNAME, taskREWARD, doubletAMOUNT, doubletREWARD, period, bingoREWARD, doDATE, doTASK, doREWARD, doACCOUNT) VALUES (:groupID, :groupPASS, :groupNAME, :taskNAME, :taskREWARD, :doubletAMOUNT, :doubletREWARD, :period, :bingoREWARD, :doDATE, :doTASK, :doREWARD, :doACCOUNT)";
	$stmt = $db -> prepare($sql);
	$stmt -> bindParam(':groupID', $groupID, PDO::PARAM_STR);
	$stmt -> bindParam(':groupPASS', $groupPASS, PDO::PARAM_STR);
	$stmt -> bindParam(':groupNAME', $groupNAME, PDO::PARAM_STR);
	$stmt -> bindParam(':taskNAME', $taskNAME, PDO::PARAM_STR);
	$stmt -> bindParam(':taskREWARD', $taskREWARD, PDO::PARAM_STR);
	$stmt -> bindParam(':doubletAMOUNT', $doubletAMOUNT, PDO::PARAM_STR);
	$stmt -> bindParam(':doubletREWARD', $doubletREWARD, PDO::PARAM_STR);
	$stmt -> bindParam(':period', $period, PDO::PARAM_STR);
	$stmt -> bindParam(':bingoREWARD', $bingoREWARD, PDO::PARAM_STR);
	$stmt -> bindParam(':doDATE', $doDATE, PDO::PARAM_STR);
	$stmt -> bindParam(':doTASK', $doTASK, PDO::PARAM_STR);
	$stmt -> bindParam(':doREWARD', $doREWARD, PDO::PARAM_STR);
	$stmt -> bindParam(':doACCOUNT', $doACCOUNT, PDO::PARAM_STR);
	$stmt -> execute();

	$id = $_COOKIE['id'];
	$sql = "UPDATE User SET groupID = '$groupID' WHERE id = '$id'";
	$stmt = $db -> query($sql);

	setcookie('groupID', $groupID);
	setcookie('groupPASS', $groupPASS);
	setcookie('groupNAME', $groupNAME);
	setcookie('taskNAME', $taskNAME);
	setcookie('taskREWARD', $taskREWARD);
	setcookie('doubletAMOUNT', $doubletAMOUNT);
	setcookie('doubletREWARD', $doubletREWARD);
	setcookie('period', $period);
	setcookie('bingoREWARD', $bingoREWARD);
	setcookie('doDATE', $doDATE);
	setcookie('doTASK', $doTASK);
	setcookie('doREWARD', $doREWARD);
	setcookie('doACCOUNT', $doACCOUNT);

	echo 'Made new group.';
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="back"/>
	</body>
</html>
<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupNAME = @$_GET['groupNAME'];
$groupID = md5(uniqid(rand() . $groupNAME, 1));
$groupPASS = @$_GET['groupPASS'];

$taskNAME = "";
$taskREWARD = "";
$arrangementTASK = "";
$arrangementACCOUNT = "";
for ($i = 0; $i < 25; $i++) {
	$taskNAME .= "neutral,";
	$taskREWARD .= "neutral,";
	$arrangementTASK .= "neutral,";
	$arrangementACCOUNT .= "neutral,";
}

if (mb_substr($taskNAME, -1) == ",") {
	$taskNAME = mb_substr($taskNAME, 0, -1);
	$taskREWARD = mb_substr($taskREWARD, 0, -1);
	$arrangementTASK = mb_substr($arrangementTASK, 0, -1);
	$arrangementACCOUNT = mb_substr($arrangementACCOUNT, 0, -1);
}

$doubletAMOUNT = "none0";
$doubletREWARD = "none0";

$bingoWEIGHT = "3";
$bingoREWARD = "none0";
$bingoCOMBINATION = "none0";
$doDATE = "none0";
$doTASK = "none0";
$doREWARD = "none0";
$doACCOUNT = "none0";
$period = "30";
$start = date("Y/m/d");
$end = date("Y/m/d",strtotime("+30 day"));

$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
$stmt = $db -> query($sql);

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	echo 'エラー：グループを作成できません。';
} else {
	$sql = "INSERT INTO Gro (groupID, groupPASS, groupNAME, taskNAME, taskREWARD, doubletAMOUNT, doubletREWARD, arrangementTASK, arrangementACCOUNT, bingoWEIGHT, bingoREWARD, bingoCOMBINATION, doDATE, doTASK, doREWARD, doACCOUNT, period ,start ,end) VALUES (:groupID, :groupPASS, :groupNAME, :taskNAME, :taskREWARD, :doubletAMOUNT, :doubletREWARD, :arrangementTASK, :arrangementACCOUNT, :bingoWEIGHT, :bingoREWARD, :bingoCOMBINATION, :doDATE, :doTASK, :doREWARD, :doACCOUNT ,:period ,:start ,:end)";
	$stmt = $db -> prepare($sql);
	$stmt -> bindParam(':groupID', $groupID, PDO::PARAM_STR);
	$stmt -> bindParam(':groupPASS', $groupPASS, PDO::PARAM_STR);
	$stmt -> bindParam(':groupNAME', $groupNAME, PDO::PARAM_STR);
	$stmt -> bindParam(':taskNAME', $taskNAME, PDO::PARAM_STR);
	$stmt -> bindParam(':taskREWARD', $taskREWARD, PDO::PARAM_STR);
	$stmt -> bindParam(':doubletAMOUNT', $doubletAMOUNT, PDO::PARAM_STR);
	$stmt -> bindParam(':doubletREWARD', $doubletREWARD, PDO::PARAM_STR);

	$stmt -> bindParam(':arrangementTASK', $arrangementTASK, PDO::PARAM_STR);
	$stmt -> bindParam(':arrangementACCOUNT', $arrangementACCOUNT, PDO::PARAM_STR);
	$stmt -> bindParam(':bingoWEIGHT', $bingoWEIGHT, PDO::PARAM_STR);
	$stmt -> bindParam(':bingoREWARD', $bingoREWARD, PDO::PARAM_STR);
	$stmt -> bindParam(':bingoCOMBINATION', $bingoCOMBINATION, PDO::PARAM_STR);
	$stmt -> bindParam(':doDATE', $doDATE, PDO::PARAM_STR);
	$stmt -> bindParam(':doTASK', $doTASK, PDO::PARAM_STR);
	$stmt -> bindParam(':doREWARD', $doREWARD, PDO::PARAM_STR);
	$stmt -> bindParam(':doACCOUNT', $doACCOUNT, PDO::PARAM_STR);
	$stmt -> bindParam(':period', $period, PDO::PARAM_STR);
	$stmt -> bindParam(':start', $start, PDO::PARAM_STR);
	$stmt -> bindParam(':end', $end, PDO::PARAM_STR);
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

	setcookie('arrangementTASK', $arrangementTASK);
	setcookie('arrangementACCOUNT', $arrangementACCOUNT);
	setcookie('bingoWEIGHT', $bingoWEIGHT);
	setcookie('bingoREWARD', $bingoREWARD);
	setcookie('bingoCOMBINATION', $bingoCOMBINATION);
	setcookie('doDATE', $doDATE);
	setcookie('doTASK', $doTASK);
	setcookie('doREWARD', $doREWARD);
	setcookie('doACCOUNT', $doACCOUNT);
	setcookie('period', $period);
	setcookie('start', $start);
	setcookie('end', $end);

	echo 'Made new group.';
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="back"/>
	</body>
</html>
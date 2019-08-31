<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

$groupID = $_COOKIE['groupID'];
$groupPASS = @$_COOKIE['groupPASS'];
$period = "";
$bingoREWARD = "";
$bingoWEIGHT = "";
if (isset($_GET['period']))
	$period = $_GET['period'];
if (isset($_GET['bingoREWARD']))
	$bingoREWARD = $_GET['bingoREWARD'];
if (isset($_GET['bingoWEIGHT']))
	$bingoWEIGHT = $_GET['bingoWEIGHT'];

if (isGroup($groupID, $groupPASS)) {// SELECTした行が存在する場合ログイン成功
	if ($period != null && $period != "0") {
		setGroupValue($groupID, $groupPASS, "period", $period);
		setGroupValue($groupID, $groupPASS, "bingoWEIGHT", $bingoWEIGHT);
		setGroupValue($groupID, $groupPASS, "bingoREWARD", $bingoREWARD);

		setcookie('period', $period);
		setcookie('bingoREWARD', $bingoREWARD);
		setcookie('bingoWEIGHT', $bingoWEIGHT);
		header("Location: ./edit_group.php");
		exit ;
	}
} else {
	echo 'wrong period';
}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Change period</title>
	</head>
	<body>
		<h1>期間を入力してください</h1>
		<form action="./create_group_setting.php" method="get">
			期間
			<input type="text" name="period" placeholder="period"/>
			<br>
			BINGOマス目
			<input type="text" name="bingoWEIGHT" placeholder="bingoWEIGHT"/>
			<br>
			BINGO報酬
			<input type="text" name="bingoREWARD" placeholder="bingoREWARD"/>
			<br>
			<input type="submit" value="Save"/>
		</form>
	</body>
</html>
<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

$groupID = $_COOKIE['groupID'];
$groupPASS = $_COOKIE['groupPASS'];

$bingoREWARD = "";
$bingoWEIGHT = "";

$period = "";
$start = $_COOKIE['start'];
$end = "";

if (isset($_GET['period']))
	$period = $_GET['period'];
if (isset($_GET['bingoREWARD']))
	$bingoREWARD = $_GET['bingoREWARD'];
if (isset($_GET['bingoWEIGHT']))
	$bingoWEIGHT = $_GET['bingoWEIGHT'];

if (isGroup($groupID, $groupPASS)) {// SELECTした行が存在する場合ログイン成功
	if ($period != null && $period != "0") {
		setGroupValue($groupID, $groupPASS, "period", $period);
		$end = $start . "+" . $period . " day";
		setGroupValue($groupID, $groupPASS, "end", date("Y/m/d", strtotime($end)));

		setGroupValue($groupID, $groupPASS, "bingoWEIGHT", $bingoWEIGHT);
		setGroupValue($groupID, $groupPASS, "bingoREWARD", $bingoREWARD);

		if ($bingoWEIGHT != $_COOKIE['bingoWEIGHT']) {
			$arrangementTASK = "";
			$arrangementACCOUNT = "";
			for ($i = 0; $i < 25; $i++) {
				$arrangementTASK .= "neutral,";
				$arrangementACCOUNT .= "neutral,";
			}

			if (mb_substr($taskNAME, -1) == ",") {
				$arrangementTASK = mb_substr($arrangementTASK, 0, -1);
				$arrangementACCOUNT = mb_substr($arrangementACCOUNT, 0, -1);
			}
			setGroupValue($groupID, $groupPASS, "arrangementTASK", $arrangementTASK);
			setGroupValue($groupID, $groupPASS, "arrangementACCOUNT", $arrangementACCOUNT);
			setcookie('arrangementTASK', $arrangementTASK);
			setcookie('arrangementACCOUNT', $arrangementACCOUNT);

			setGroupValue($groupID, $groupPASS, "bingoCOMBINATION", "none0");
			setcookie('bingoCOMBINATION', "none0");
		}

		setcookie('bingoREWARD', $bingoREWARD);
		setcookie('bingoWEIGHT', $bingoWEIGHT);

		setcookie('period', $period);
		setcookie('end', $end);

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
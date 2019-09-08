<?php

header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';
$groupID = $_COOKIE['groupID'];

$intervalTIME = "none0";
$bingoREWARD = "none0";
$bingoWEIGHT = "none0";

$start = getGroupValue($groupID, "start");
$end = "";

if (isset($_GET['intervalTIME']))
	$intervalTIME = $_GET['intervalTIME'];
if (isset($_GET['bingoREWARD']))
	$bingoREWARD = $_GET['bingoREWARD'];
if (isset($_GET['bingoWEIGHT']))
	$bingoWEIGHT = $_GET['bingoWEIGHT'];

if (isValue($intervalTIME) && isValue($bingoWEIGHT) && isValue($bingoREWARD)) {
	if (isGroup($groupID)) {// SELECTした行が存在する場合ログイン成功
		if ($intervalTIME != null && $intervalTIME != "0") {
			setGroupValue($groupID, "intervalTIME", $intervalTIME);
			$end = date("Y/m/d", strtotime($start . "+" . $intervalTIME . " month"));
			$end = date("Y/m/d", strtotime($end . "-1 day"));
			setGroupValue($groupID, "end", $end);

			setGroupValue($groupID, "bingoWEIGHT", $bingoWEIGHT);
			setGroupValue($groupID, "bingoREWARD", $bingoREWARD);

			if ($bingoWEIGHT != getGroupValue($groupID, "bingoWEIGHT")) {
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
				setGroupValue($groupID, "arrangementTASK", $arrangementTASK);
				setGroupValue($groupID, "arrangementACCOUNT", $arrangementACCOUNT);

				setGroupValue($groupID, "bingoCOMBINATION", "none0");
			}

			header("Location: ./index.php");
			exit ;
		}
	} else {
		echo 'Error<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='create-group-setting.php'\" value=\"back\"/>";
	}
} else {

}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Setting</title>
	</head>
	<body>
		<h1><a href="index.php">AndYou</a></h1>
		<form action="./create-group-setting.php" method="get">
			interval
			<select name="intervalTIME">
				<option value="1">A month</option>
				<option value="3">Three months</option>
				<option value="6">Six months</option>
			</select>
			<br>
			Table size
			<select name="bingoWEIGHT">
				<option value="3">3x3</option>
				<option value="4">4x4</option>
				<option value="5">5x5</option>
			</select>
			<br>
			Bingo reward
			<input type="number" name="bingoREWARD"/>
			<br>
			<input type="submit" value="Save"/>
		</form>
	</body>
</html>
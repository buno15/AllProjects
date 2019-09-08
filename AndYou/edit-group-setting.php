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

			header("Location: ./edit-group.php");
			exit ;
		}
	} else {
		echo 'Error<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='edit-group-setting.php'\" value=\"back\"/>";
	}
} else {
	$intervalTIME = getGroupValue($groupID, "intervalTIME");
	$bingoWEIGHT = getGroupValue($groupID, "bingoWEIGHT");
	$bingoREWARD = getGroupValue($groupID, "bingoREWARD");
	echo "<h1><a href=\"index.php\">AndYou</a></h1>";
	echo "<form action=\"./edit-group-setting.php\" method=\"get\">";

	echo "Interval";
	echo "<select name=\"intervalTIME\">";
	if ($intervalTIME == 1)
		echo "<option value=\"1\" selected>A month</option>";
	else
		echo "<option value=\"1\">A month</option>";
	if ($intervalTIME == 3)
		echo "<option value=\"3\" selected>Three months</option>";
	else
		echo "<option value=\"3\">Three months</option>";
	if ($intervalTIME == 6)
		echo "<option value=\"6\" selected>Six months</option>";
	else
		echo "<option value=\"6\">Six months</option>";
	echo "</select>";

	echo "<br>";
	echo "Table size";
	echo "<select name=\"bingoWEIGHT\">";
	if ($bingoWEIGHT == 3)
		echo "<option value=\"3\" selected>3x3</option>";
	else
		echo "<option value=\"3\">3x3</option>";
	if ($bingoWEIGHT == 4)
		echo "<option value=\"4\" selected>4x4</option>";
	else
		echo "<option value=\"4\">4x4</option>";
	if ($bingoWEIGHT == 5)
		echo "<option value=\"5\" selected>5x5</option>";
	else
		echo "<option value=\"5\">5x5</option>";
	echo "</select>";

	echo "<br>";
	echo "Bingo reward";
	echo "<input type=\"number\" name=\"bingoREWARD\" value=\"$bingoREWARD\" required/>";
	echo "<br>";

	echo "<input type=\"submit\" value=\"Save\"/>";
	echo "</form>";
}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Setting</title>
	</head>
	<body></body>
</html>
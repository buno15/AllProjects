<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

$groupID = $_COOKIE['groupID'];

$doDATE = $_GET['doDATE'];
$doTASK = $_GET['doTASK'];
$doREWARD = $_GET['doREWARD'];
$doACCOUNT = $_GET['doACCOUNT'];
$bingoREWARD = getGroupValue($groupID, "bingoREWARD");
$bingoWEIGHT = getGroupValue($groupID, "bingoWEIGHT");

$taskNAMEs = explode(",", getGroupValue($groupID, "taskNAME"));
$taskREWARDs = explode(",", getGroupValue($groupID, "taskREWARD"));

$reward = 0;

$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
$sql = "SELECT * FROM User WHERE id='$doACCOUNT'";
$stmt = $db -> query($sql);
if ($stmt -> rowCount() > 0) {
	foreach ($stmt as $row) {
		$reward = (int)$row['reward'];
	}
}

$reward -= (int)$doREWARD;

$doDATEs = explode(",", getGroupValue($groupID, "doDATE"));
$doTASKs = explode(",", getGroupValue($groupID, "doTASK"));
$doREWARDs = explode(",", getGroupValue($groupID, "doREWARD"));
$doACCOUNTs = explode(",", getGroupValue($groupID, "doACCOUNT"));

$afterTaskNAME = "";
$afterTaskREWARD = "";
$afterDoDATE = "";
$afterDoTASK = "";
$afterDoREWARD = "";
$afterDoACCOUNT = "";

for ($i = 0; $i < count($taskNAMEs); $i++) {
	if ($taskNAMEs[$i] != "none0" && $taskNAMEs[$i] != "###" . $doTASK) {
		$afterTaskNAME .= $taskNAMEs[$i] . ",";
		$afterTaskREWARD .= $taskREWARDs[$i] . ",";
	} else if ($taskNAMEs[$i] == "###" . $doTASK) {
		$taskNAMEs[$i] = ltrim($taskNAMEs[$i], '###');
		$taskREWARDs[$i] = ltrim($taskREWARDs[$i], '###');
		$afterTaskNAME .= $taskNAMEs[$i] . ",";
		$afterTaskREWARD .= $taskREWARDs[$i] . ",";
	}
}

if (mb_substr($afterTaskNAME, -1) == ",") {
	$afterTaskNAME = mb_substr($afterTaskNAME, 0, -1);
	$afterTaskREWARD = mb_substr($afterTaskREWARD, 0, -1);
}

$arrangementTASKs = explode(",", getGroupValue($groupID, "arrangementTASK"));
$arrangementACCOUNTs = explode(",", getGroupValue($groupID, "arrangementACCOUNT"));

$afterArrangementTASK = "";
$afterArrangementACCOUNT = "";
$removeCount = 0;

for ($i = 0; $i < count($arrangementTASKs); $i++) {
	if ($arrangementTASKs[$i] == $doTASK && $arrangementACCOUNTs[$i] == $doACCOUNT) {
		$afterArrangementTASK .= "neutral,";
		$afterArrangementACCOUNT .= "neutral,";

		$bingoCOMBINATIONs = explode(",", getGroupValue($groupID, "bingoCOMBINATION"));
		list($x, $y) = getMatrix($i, $bingoWEIGHT);
		$matrix = $x . "" . $y;
		$afterBingoCOMBINATION = "";

		for ($j = 0; $j < count($bingoCOMBINATIONs); $j++) {
			if (strpos($bingoCOMBINATIONs[$j], $matrix) !== false) {
				$reward = $reward - $bingoREWARD;
				$removeCount++;
			} else {
				$afterBingoCOMBINATION .= $bingoCOMBINATIONs[$j] . ",";
			}
		}

		if (mb_substr($afterBingoCOMBINATION, -1) == ",") {
			$afterBingoCOMBINATION = mb_substr($afterBingoCOMBINATION, 0, -1);
		}
		setGroupValue($groupID, "bingoCOMBINATION", $afterBingoCOMBINATION);
	} else {
		$afterArrangementTASK .= $arrangementTASKs[$i] . ",";
		$afterArrangementACCOUNT .= $arrangementACCOUNTs[$i] . ",";
	}

}
for ($k = 0; $k < count($doTASKs); $k++) {
	if ($doDATEs[$k] != "none0") {
		if ($doTASKs[$k] == "BINGO" && $doACCOUNTs[$k] == $doACCOUNT && $removeCount > 0) {
			$removeCount--;
		} else if ($doTASKs[$k] == $doTASK && $doDATEs[$k] == $doDATE && $doACCOUNTs[$k] == $doACCOUNT) {

		} else {
			$afterDoDATE .= $doDATEs[$k] . ",";
			$afterDoTASK .= $doTASKs[$k] . ",";
			$afterDoREWARD .= $doREWARDs[$k] . ",";
			$afterDoACCOUNT .= $doACCOUNTs[$k] . ",";
		}
	}
}

if (mb_substr($afterDoDATE, -1) == ",") {
	$afterDoDATE = mb_substr($afterDoDATE, 0, -1);
	$afterDoTASK = mb_substr($afterDoTASK, 0, -1);
	$afterDoREWARD = mb_substr($afterDoREWARD, 0, -1);
	$afterDoACCOUNT = mb_substr($afterDoACCOUNT, 0, -1);
}

if ($afterDoDATE == null) {
	$afterDoDATE = "none0";
	$afterDoTASK = "none0";
	$afterDoREWARD = "none0";
	$afterDoACCOUNT = "none0";
}

if (mb_substr($afterArrangementTASK, -1) == ",") {
	$afterArrangementTASK = mb_substr($afterArrangementTASK, 0, -1);
	$afterArrangementACCOUNT = mb_substr($afterArrangementACCOUNT, 0, -1);
}
setGroupValue($groupID, "arrangementTASK", $afterArrangementTASK);
setGroupValue($groupID, "arrangementACCOUNT", $afterArrangementACCOUNT);

if ($reward < 0) {
	$reward = 0;
}
setAccountValue($doACCOUNT, "reward", $reward);
setGroupValue($groupID, "taskNAME", $afterTaskNAME);
setGroupValue($groupID, "taskREWARD", $afterTaskREWARD);
setGroupValue($groupID, "doDATE", $afterDoDATE);
setGroupValue($groupID, "doTASK", $afterDoTASK);
setGroupValue($groupID, "doREWARD", $afterDoREWARD);
setGroupValue($groupID, "doACCOUNT", $afterDoACCOUNT);

header("Location: ./edit-group.php");
exit ;
?>
<html>
	<meta charset="UTF-8" />
	<title>AndYou</title>
	<body></body>
</html>
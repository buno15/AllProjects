<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupID = $_COOKIE['groupID'];
$groupPASS = $_COOKIE['groupPASS'];

$doDATE = $_GET['doDATE'];
$doTASK = $_GET['doTASK'];
$doREWARD = $_GET['doREWARD'];
$doACCOUNT = $_GET['doACCOUNT'];
$bingoREWARD = $_COOKIE['bingoREWARD'];
$bingoWEIGHT = $_COOKIE['bingoWEIGHT'];

$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

$reward = 0;

$sql = "SELECT * FROM User WHERE id='$doACCOUNT'";
$stmt = $db -> query($sql);
if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	foreach ($stmt as $row) {
		$reward = (int)$row['reward'];
	}
}

$reward -= (int)$doREWARD;

$doDATEs = explode(",", $_COOKIE['doDATE']);
$doTASKs = explode(",", $_COOKIE['doTASK']);
$doREWARDs = explode(",", $_COOKIE['doREWARD']);
$doACCOUNTs = explode(",", $_COOKIE['doACCOUNT']);

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

$arrangementTASKs = explode(",", $_COOKIE['arrangementTASK']);
$arrangementACCOUNTs = explode(",", $_COOKIE['arrangementACCOUNT']);

$afterArrangementTASK = "";
$afterArrangementACCOUNT = "";
$removeCount = 0;

for ($i = 0; $i < count($arrangementTASKs); $i++) {
	if ($arrangementTASKs[$i] == $doTASK && $arrangementACCOUNTs[$i] == $doACCOUNT) {
		$afterArrangementTASK .= "neutral,";
		$afterArrangementACCOUNT .= "neutral,";

		$bingoCOMBINATIONs = explode(",", $_COOKIE['bingoCOMBINATION']);
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
		setGroupValue($groupID, $groupPASS, "bingoCOMBINATION", $afterBingoCOMBINATION);
		setcookie('bingoCOMBINATION', $afterBingoCOMBINATION);
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
setGroupValue($groupID, $groupPASS, "arrangementTASK", $afterArrangementTASK);
setGroupValue($groupID, $groupPASS, "arrangementACCOUNT", $afterArrangementACCOUNT);
setcookie('arrangementTASK', $afterArrangementTASK);
setcookie('arrangementACCOUNT', $afterArrangementACCOUNT);

$sql = "UPDATE User SET reward = '$reward' WHERE id = '$doACCOUNT'";
$stmt = $db -> query($sql);

setcookie('reward', $reward);

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sql = "UPDATE Gro SET taskNAME = '$afterTaskNAME' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET taskREWARD = '$afterTaskREWARD' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET doDATE = '$afterDoDATE' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET doTASK = '$afterDoTASK' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET doREWARD = '$afterDoREWARD' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET doACCOUNT = '$afterDoACCOUNT' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

setcookie('taskNAME', $afterTaskNAME);
setcookie('taskREWARD', $afterTaskREWARD);
setcookie('doDATE', $afterDoDATE);
setcookie('doTASK', $afterDoTASK);
setcookie('doREWARD', $afterDoREWARD);
setcookie('doACCOUNT', $afterDoACCOUNT);

echo 'Logを削除しました';
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='edit_group.php'" value="back"/>
	</body>
</html>
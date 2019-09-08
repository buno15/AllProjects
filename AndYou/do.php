<?php
require_once 'func.php';

$index = $_GET['index'];

$id = $_COOKIE['id'];
$groupID = $_COOKIE['groupID'];
$taskNAME = $_GET['taskNAME'];
$taskREWARD = (int)$_GET['taskREWARD'];
$bingoREWARD = getGroupValue($groupID, "bingoREWARD");
$bingoWEIGHT = getGroupValue($groupID, "bingoWEIGHT");

$arrangementTASKs = explode(",", getGroupValue($groupID, "arrangementTASK"));
$arrangementACCOUNTs = explode(",", getGroupValue($groupID, "arrangementACCOUNT"));

if ($index != "-1") {

	//$doDATE = "2018/06/03 12:12:12";
	$doDATE = date("Y/m/d H:i:s");

	list($arrangementTASKs, $arrangementACCOUNTs) = addArrangement($arrangementTASKs, $arrangementACCOUNTs, $taskNAME, $id, $index);

	$reward = (int)getAccountValue($id, "reward") + $taskREWARD;

	$taskNAMEs = explode(",", getGroupValue($groupID, "taskNAME"));
	$taskREWARDs = explode(",", getGroupValue($groupID, "taskREWARD"));

	list($taskNAMEs, $taskREWARDs) = addDo($taskNAMEs, $taskREWARDs, $taskNAME, $taskREWARD);
	$afterTaskNAME = $taskNAMEs;
	$afterTaskREWARD = $taskREWARDs;
	$afterDoDATE = getGroupValue($groupID, "doDATE");
	$afterDoTASK = getGroupValue($groupID, "doTASK");
	$afterDoREWARD = getGroupValue($groupID, "doREWARD");
	$afterDoACCOUNT = getGroupValue($groupID, "doACCOUNT");

	if ($afterDoDATE != "none0") {
		$afterDoDATE = $doDATE . "," . $afterDoDATE;
		$afterDoTASK = $taskNAME . "," . $afterDoTASK;
		$afterDoREWARD = $taskREWARD . "," . $afterDoREWARD;
		$afterDoACCOUNT = $id . "," . $afterDoACCOUNT;
	} else {
		$afterDoDATE = $doDATE;
		$afterDoTASK = $taskNAME;
		$afterDoREWARD = $taskREWARD;
		$afterDoACCOUNT = $id;
	}

	$beforeBingoCOMBINATIONs = explode(",", getGroupValue($groupID, "bingoCOMBINATION"));
	$bingoCOMBINATIONs = isBingo(explode(",", $arrangementACCOUNTs), $bingoWEIGHT, $index);
	$bingoCOMBINATION = arrayToString($beforeBingoCOMBINATIONs);
	if (count($bingoCOMBINATIONs) != 0) {
		for ($j = 0; $j < count($bingoCOMBINATIONs); $j++) {
			if (!in_array($bingoCOMBINATIONs[$j], $beforeBingoCOMBINATIONs)) {
				$bingoCOMBINATION .= "," . $bingoCOMBINATIONs[$j];
				$reward = $reward + intval($bingoREWARD);
				$afterDoDATE = $doDATE . "," . $afterDoDATE;
				$afterDoTASK = "BINGO," . $afterDoTASK;
				$afterDoREWARD = $bingoREWARD . "," . $afterDoREWARD;
				$afterDoACCOUNT = $id . "," . $afterDoACCOUNT;
			}
		}
		setGroupValue($groupID, "bingoCOMBINATION", $bingoCOMBINATION);
	}

	setGroupValue($groupID, "arrangementTASK", $arrangementTASKs);
	setGroupValue($groupID, "arrangementACCOUNT", $arrangementACCOUNTs);

	setAccountValue($id, "reward", $reward);
	setGroupValue($groupID, "taskNAME", $afterTaskNAME);
	setGroupValue($groupID, "taskREWARD", $afterTaskREWARD);
	setGroupValue($groupID, "doDATE", $afterDoDATE);
	setGroupValue($groupID, "doTASK", $afterDoTASK);
	setGroupValue($groupID, "doREWARD", $afterDoREWARD);
	setGroupValue($groupID, "doACCOUNT", $afterDoACCOUNT);

	header("Location: ./index.php");
	exit ;
} else {
	$flag = true;
	for ($i = 0; $i < count($arrangementTASKs); $i++) {
		if ($taskNAME == $arrangementTASKs[$i]) {
			$flag = false;
		}
	}
	if ($flag) {
		echo "<table>";
		echo "<table border=\"1\">";

		for ($i = 0; $i < $bingoWEIGHT * $bingoWEIGHT; $i++) {
			if ($i % $bingoWEIGHT == 0)
				echo "
				<tr>
					";
			if ($arrangementTASKs[$i] != "none0") {
				$n = "";
				$r = "";
				if (mb_substr($arrangementTASKs[$i], 0, 3) == "###") {
					$n = mb_substr($arrangementTASKs[$i], 3);
					$r = mb_substr($arrangementACCOUNTs[$i], 3);
				} else {
					$n = $arrangementTASKs[$i];
					$r = $arrangementACCOUNTs[$i];
				}
				echo "<td>";
				if ($r == "neutral") {
					echo "<button type=\"button\" onclick=\"location.href='do.php?taskNAME=$taskNAME&taskREWARD=$taskREWARD&index=$i'\" value=\"code\">$n</button>";
				} else {
					$pass = "";
					$tableColor = getAccountValue($arrangementACCOUNTs[$i], "color");
					echo "<button type=\"button\" style=\"background-color:$tableColor;\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r&index=$i'\" value=\"code\" disabled>$n<br/>$r</button>";
				}
				echo "</td>";
			}
			if (($i + 1) % $bingoWEIGHT == 0)
				echo "</tr>";
		}
		echo "</table>";
	} else {
		echo 'Error<br>';
		echo "<input type=\"button\" name=\"add\" onclick=\"location.href='index.php'\" value=\"back\"/>";
	}
}
?>
<html>
	<body>

	</body>
</html>
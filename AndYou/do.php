<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

$index = $_GET['index'];

$id = $_COOKIE['id'];
$groupID = $_COOKIE['groupID'];
$groupPASS = $_COOKIE['groupPASS'];
$taskNAME = $_GET['taskNAME'];
$taskREWARD = (int)$_GET['taskREWARD'];
$bingoREWARD = $_COOKIE['bingoREWARD'];
$bingoWEIGHT = $_COOKIE['bingoWEIGHT'];

if ($index != "-1") {

	// データベースに接続
	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

	$doDATE = date("Y/m/d H:i:s");

	$arrangementTASKs = explode(",", $_COOKIE['arrangementTASK']);
	$arrangementACCOUNTs = explode(",", $_COOKIE['arrangementACCOUNT']);

	list($arrangementTASKs, $arrangementACCOUNTs) = addArrangement($arrangementTASKs, $arrangementACCOUNTs, $taskNAME, $id, $index);

	$reward = (int)$_COOKIE['reward'] + $taskREWARD;

	$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
	$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

	list($taskNAMEs, $taskREWARDs) = addDo($taskNAMEs, $taskREWARDs, $taskNAME, $taskREWARD);
	$afterTaskNAME = $taskNAMEs;
	$afterTaskREWARD = $taskREWARDs;
	$afterDoDATE = $_COOKIE['doDATE'];
	$afterDoTASK = $_COOKIE['doTASK'];
	$afterDoREWARD = $_COOKIE['doREWARD'];
	$afterDoACCOUNT = $_COOKIE['doACCOUNT'];

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

	$beforeBingoCOMBINATIONs = explode(",", $_COOKIE['bingoCOMBINATION']);
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
		setGroupValue($groupID, $groupPASS, "bingoCOMBINATION", $bingoCOMBINATION);
		setcookie('bingoCOMBINATION', $bingoCOMBINATION);
	}

	$sql = "UPDATE User SET reward = '$reward' WHERE groupID = '$groupID' AND id='$id'";
	$stmt = $db -> query($sql);

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

	setGroupValue($groupID, $groupPASS, "arrangementTASK", $arrangementTASKs);
	setGroupValue($groupID, $groupPASS, "arrangementACCOUNT", $arrangementACCOUNTs);

	setcookie('taskNAME', $afterTaskNAME);
	setcookie('taskREWARD', $afterTaskREWARD);
	setcookie('reward', $reward);
	setcookie('doDATE', $afterDoDATE);
	setcookie('doTASK', $afterDoTASK);
	setcookie('doREWARD', $afterDoREWARD);
	setcookie('doACCOUNT', $afterDoACCOUNT);
	setcookie('arrangementTASK', $arrangementTASKs);
	setcookie('arrangementACCOUNT', $arrangementACCOUNTs);

	header("Location: ./index.php");
	exit ;
}
?>
<html>
	<body>
		<table>
			<table border="1">
				<?php
				if (isset($_COOKIE['arrangementTASK'])) {
					$arrangementTASKs = explode(",", $_COOKIE['arrangementTASK']);
					$arrangementACCOUNTs = explode(",", $_COOKIE['arrangementACCOUNT']);
				}
				$bingoWeight = getGroupValue($groupID, $groupPASS, "bingoWEIGHT");

				for ($i = 0; $i < $bingoWeight * $bingoWeight; $i++) {
					if ($i % $bingoWeight == 0)
						echo "<tr>";
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
							echo "<button type=\"button\" onclick=\"location.href='do.php?taskNAME=$taskNAME&taskREWARD=$taskREWARD&index=$i'\" value=\"code\" disabled>$n<br/>$r</button>";

						}
						echo "</td>";
					}
					if (($i + 1) % $bingoWeight == 0)
						echo "</tr>";
				}
				?>
			</table>
	</body>
</html>
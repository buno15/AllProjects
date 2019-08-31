<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$id = $_COOKIE['id'];
$groupID = $_COOKIE['groupID'];
$groupPASS = $_COOKIE['groupPASS'];
$taskNAME = $_GET['taskNAME'];
$taskREWARD = (int)$_GET['taskREWARD'];
$doDATE = date("Y/m/d H:i:s");

$reward = (int)$_COOKIE['reward'] + $taskREWARD;

$sql = "UPDATE User SET reward = '$reward' WHERE groupID = '$groupID' AND id='$id'";
$stmt = $db -> query($sql);

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
	$afterDoDATE .= "," . $doDATE;
	$afterDoTASK .= "," . $taskNAME;
	$afterDoREWARD .= "," . $taskREWARD;
	$afterDoACCOUNT .= "," . $id;
} else {
	$afterDoDATE = $doDATE;
	$afterDoTASK = $taskNAME;
	$afterDoREWARD = $taskREWARD;
	$afterDoACCOUNT = $id;
}

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
setcookie('reward', $reward);
setcookie('doDATE', $afterDoDATE);
setcookie('doTASK', $afterDoTASK);
setcookie('doREWARD', $afterDoREWARD);
setcookie('doACCOUNT', $afterDoACCOUNT);
?>
<html>
	<body>
		<table>
			<table border="1">
				<?php
				require_once 'func.php';
				$taskNAMEs;
				$taskREWARDs;
				if (isset($_COOKIE['taskNAME'])) {
					$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
					$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);
				}
				$bingoWeight = getGroupValue($groupID, $groupPASS, "bingoWEIGHT");

				$tableN = splitArray($taskNAMEs, $bingoWeight);
				$tableR = splitArray($taskREWARDs, $bingoWeight);

				for ($i = 0; $i < $bingoWeight; $i++) {
					echo "<tr>";
					for ($j = 0; $j < $bingoWeight; $j++) {
						if ($tableN[$i] != "none0") {
							$n = "";
							$r = "";
							if (mb_substr($tableN[$i][$j], 0, 3) == "###") {
								$n = mb_substr($tableN[$i][$j], 3);
								$r = mb_substr($tableR[$i][$j], 3);
							} else {
								$n = $tableN[$i][$j];
								$r = $tableR[$i][$j];
							}
							echo "<td>";
							echo "<button type=\"button\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r'\" value=\"code\">$n<br/>$r</button>";
							echo "</td>";
						}
					}
					echo "</tr>";
				}
				?>
			</table>
	</body>
</html>
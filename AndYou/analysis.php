<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

$index = $_GET['index'];
$index_after = (int)$index - 1;
$index_before = (int)$index + 1;

$id = $_COOKIE['id'];
$groupID = $_COOKIE['groupID'];
$groupPASS = $_COOKIE['groupPASS'];
$bingoREWARD = $_COOKIE['bingoREWARD'];
$bingoWeight = getGroupValue($groupID, $groupPASS, "bingoWEIGHT");

$dateSTART = getGroupValue($groupID, $groupPASS, "dateSTART");
$dateEND = getGroupValue($groupID, $groupPASS, "dateEND");

$dateSTARTs = stringToArray($dateSTART);
$dateENDs = stringToArray($dateEND);

$start = $dateSTARTs[$index];
$end = $dateENDs[$index];

$arrangementTASKPrevious = $_COOKIE['arrangementTASKPrevious'];
$arrangementACCOUNTPrevious = $_COOKIE['arrangementACCOUNTPrevious'];
$arrangementTASKPreviouses = stringToArray($arrangementTASKPrevious);
$arrangementACCOUNTPreviouses = stringToArray($arrangementACCOUNTPrevious);

$arrangementTASK = $arrangementTASKPreviouses[$index];
$arrangementACCOUNT = $arrangementACCOUNTPreviouses[$index];
$arrangementTASKs = splitArrangement($arrangementTASK);
$arrangementACCOUNTs = splitArrangement($arrangementACCOUNT);

$doDATEs = explode(",", $_COOKIE['doDATE']);
$doTASKs = explode(",", $_COOKIE['doTASK']);
$doREWARDs = explode(",", $_COOKIE['doREWARD']);
$doACCOUNTs = explode(",", $_COOKIE['doACCOUNT']);
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>AndYou</title>
	</head>
	<body>
		<h1><a href="index.php">AndYou</a></h1>
		<?php
		if ($index_before >= count($dateSTARTs))
			echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=$index_before'\" value=\"←\" disabled>";
		else
			echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=$index_before'\" value=\"←\">";

		echo "<h3>" . $start . "~" . $end . "</h3>";

		if ($index_after <= 0)
			echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=$index_after'\" value=\"→\" disabled>";
		else
			echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=$index_after'\" value=\"→\">";
		?>
		<table>
		<table border="1">
		<?php
		$bingoWeight = getGroupValue($groupID, $groupPASS, "bingoWEIGHT");
		echo "<br>";

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
				if ($n == "neutral") {
					echo "<button type=\"button\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r&index=$i'\" value=\"code\" disabled>$n</button>";
				} else {
					echo "<button type=\"button\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r&index=$i'\" value=\"code\" disabled>$n<br/>$r</button>";
				}
				echo "</td>";
			}
			if (($i + 1) % $bingoWeight == 0)
				echo "</tr>";
		}
		?>
		</table>

		<?php
		for ($i = 0; $i < count($doDATEs); $i++) {
			if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start && $doDATEs[$i] <= $end) {
				echo "<h3>Do:";
				echo $doDATEs[$i] . "->" . $doTASKs[$i] . ":" . $doREWARDs[$i] . "->" . $doACCOUNTs[$i];
				echo "</h3>";
			}
		}
		?>
	</body>
</html>
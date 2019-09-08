<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

$index = $_GET['index'];
$index_after = (int)$index - 1;
$index_before = (int)$index + 1;

$id = $_COOKIE['id'];
$groupID = $_COOKIE['groupID'];
$bingoREWARD = getGroupValue($groupID, "bingoREWARD");
$bingoWeight = getGroupValue($groupID, "bingoWEIGHT");

$dateSTART = getGroupValue($groupID, "dateSTART");
$dateEND = getGroupValue($groupID, "dateEND");

$dateSTARTs = stringToArray($dateSTART);
$dateENDs = stringToArray($dateEND);

$start = $dateSTARTs[$index];
$end = $dateENDs[$index];

$arrangementTASKPrevious = getGroupValue($groupID, "arrangementTASKPrevious");
$arrangementACCOUNTPrevious = getGroupValue($groupID, "arrangementACCOUNTPrevious");
$arrangementTASKPreviouses = stringToArray($arrangementTASKPrevious);
$arrangementACCOUNTPreviouses = stringToArray($arrangementACCOUNTPrevious);

$arrangementTASK = $arrangementTASKPreviouses[$index];
$arrangementACCOUNT = $arrangementACCOUNTPreviouses[$index];
$arrangementTASKs = splitArrangement($arrangementTASK);
$arrangementACCOUNTs = splitArrangement($arrangementACCOUNT);

$doDATEs = explode(",", getGroupValue($groupID, "doDATE"));
$doTASKs = explode(",", getGroupValue($groupID, "doTASK"));
$doREWARDs = explode(",", getGroupValue($groupID, "doREWARD"));
$doACCOUNTs = explode(",", getGroupValue($groupID, "doACCOUNT"));
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
		if ($start != "none0" || $end != "none0") {
			if ($index_before >= count($dateSTARTs))
				echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=$index_before'\" value=\"←\" disabled>";
			else
				echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=$index_before'\" value=\"←\">";

			echo "<h3>" . $start . "~" . $end . "</h3>";

			if ($index_after <= 0)
				echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=$index_after'\" value=\"→\" disabled>";
			else
				echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=$index_after'\" value=\"→\">";

			echo "<table>";
			echo "<table border=\"1\">";
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
						$tableColor = getAccountValue($arrangementACCOUNTs[$i], "color");
						echo "<button type=\"button\" style=\"background-color:$tableColor;\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r&index=$i'\" value=\"code\" disabled>$n<br/>$r</button>";
					}
					echo "</td>";
				}
				if (($i + 1) % $bingoWeight == 0)
					echo "</tr>";
			}
			echo "</table>";

			for ($i = 0; $i < count($doDATEs); $i++) {
				if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start && $doDATEs[$i] <= $end) {
					echo "<h3>Do:";
					echo $doDATEs[$i] . "->" . $doTASKs[$i] . ":" . $doREWARDs[$i] . "->" . $doACCOUNTs[$i];
					echo "</h3>";
				}
			}
			if ($groupID != "none0") {
				$ids = array();
				for ($i = 0; $i < count($doACCOUNTs); $i++) {
					if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start && $doDATEs[$i] <= $end) {
						if (!in_array($doACCOUNTs[$i], $ids, true)) {
							$ids["$doACCOUNTs[$i]"] = 0;
						}
					}
				}
				foreach ($ids as $key => $value) {
					for ($i = 0; $i < count($doACCOUNTs); $i++) {
						if ($doACCOUNTs[$i] === $key) {
							if ($doDATEs[$i] >= $start && $doDATEs[$i] <= $end) {
								$ids["$key"] += $doREWARDs[$i];
							}
						}
					}
				}
				arsort($ids);
				foreach ($ids as $key => $value) {
					echo $key . '->' . $value;
					echo "<br/>";
				}
			}
		} else {
			echo "There is no data.<br>";
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='index.php'\" value=\"back\"/>";
		}
		?>
	</body>
</html>
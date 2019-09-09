<?php
require_once 'func.php';
header('Content-Type: text/html; charset=UTF-8');

$index = $_GET['index'];
$index_after = (int)$index - 1;
$index_before = (int)$index + 1;

$id = $_COOKIE['id'];
$groupID = $_COOKIE['groupID'];
$groupNAME = getGroupValue($groupID, "groupNAME");
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
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<link rel="stylesheet" href="css/table.css">
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/table_smart.css" />
		<title>AndY-ou</title>
	</head>
	<body>
		<?php
		echo "<div id=\"head\">";
		echo "<ul>";
		echo "<li>";
		echo "<a href=\"index.php\"><img src=\"img/title.png\" alt=\"AndY-ou\"/></a>";
		echo "</li>";
		if (isValue($id)) {
			echo "<li><a href=\"edit-account.php\"><h2>$id</h2></a></li>";
			echo "<li><a href=\"edit-account.php\"><img src=\"img/account.png\"/></a></li>";
		}
		echo "</ul>";
		echo "</div>";
		echo "<hr>";

		echo "<div id=\"left\">";
		echo "<div id=\"menu\">";
		echo "<ul>";
		echo "<li><a class=\"active\" >Menu</a></li>";
		echo "<li><a href=\"signin-group.php\">Group</a></li>";
		echo "<li><a href=\"analysis.php?index=0\">Analysis</a></li>";
		echo "</ul>";
		echo "</div>";

		echo "<div id=\"group\">";
		echo "<h2>" . $groupNAME . "</h2>";
		echo "</div>";

		echo "<div id=\"member\">";
		echo "<ol>";
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
		echo "</ol>";
		echo "</div>";
		echo "</div>";

		echo "<div id=\"pagebody\">";
		if ($start != "none0" || $end != "none0") {
			echo "<div id=\"analysis\">";
			echo "<h2>" . $start . "~" . $end . "</h2>";

			echo "<div id=\"analysis-button\">";
			if ($index_after <= 0)
				echo "<input class=\"btn-flat-border\" type=\"button\" onclick=\"location.href='./analysis.php?index=$index_after'\" value=\"→\" disabled>";
			else
				echo "<input class=\"btn-flat-border\" type=\"button\" onclick=\"location.href='./analysis.php?index=$index_after'\" value=\"→\">";
			echo "</div>";
			echo "<div id=\"analysis-button\">";
			if ($index_before >= count($dateSTARTs))
				echo "<input class=\"btn-flat-border\" type=\"button\" onclick=\"location.href='./analysis.php?index=$index_before'\" value=\"←\" disabled>";
			else
				echo "<input class=\"btn-flat-border\" type=\"button\" onclick=\"location.href='./analysis.php?index=$index_before'\" value=\"←\">";
			echo "</div>";
			echo "</div>";

			if (isValue($arrangementTASKPrevious) && isValue($arrangementACCOUNTPrevious)) {
				echo "<table class=\"table\" border=\"1\">";
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
			}
			echo "<div id=\"do\">";
			for ($i = 0; $i < count($doDATEs); $i++) {
				if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start && $doDATEs[$i] <= $end) {
					echo "<h3>Do:";
					echo $doDATEs[$i] . "->" . $doTASKs[$i] . ":" . $doREWARDs[$i] . "->" . $doACCOUNTs[$i];
					echo "</h3>";
				}
			}
			echo "</div>";
			echo "</div>";
		} else {
			echo "<div class=\"error\">";
			echo "<h1>There is no data.</h1>";
			echo "</div>";
			echo "<div class=\"submit\">";
			echo "<div class=\"conform\">";
			echo "<input class=\"btn-flat-border\" type=\"button\" name=\"add\" onclick=\"location.href='delete-group.php'\" value=\"Back\"/>";
			echo "</div>";
			echo "</div>";
		}
		echo "</div>";
		?>
	</body>
</html>
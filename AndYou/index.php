<?php
/*同一アカウントID禁止　同一タスク名禁止　同一ボーナス報酬金額禁止*/
/*include 'Data.php';

 class Data {
 public function get() {
 global $taskNAMEs;
 return $taskNAMEs;
 }

 public function set($array) {
 global $taskNAMEs;
 $taskNAMEs = $array;
 }

 }
 *
 *
 *
 * <select name="color">
 <?php
 $colors = array("#9fa0a0", "#dddcd6", "#ec6d71", "#f0cfa0", "#ffea00", "#98d98e", "#3eb370", "#c1e4e9", "#89c3eb", "#8491c3");
 for ($i = 0; $i < count($colors); $i++) {
 $index = $i + 1;
 print "<option value=\"color$index\" style = background-color:$colors[$i];>color$index</option>\n";
 }

 ?>
 </select>
 *
 *
 * if (isset($_COOKIE['doubletAMOUNT'])) {
 $doubletAMOUNTs = explode(",", $_COOKIE['doubletAMOUNT']);
 $doubletREWARDs = explode(",", $_COOKIE['doubletREWARD']);

 for ($i = 0; $i < count($doubletAMOUNTs); $i++) {
 if ($doubletAMOUNTs[$i] != "none0") {
 echo "<h3>Doublet:";
 echo $doubletAMOUNTs[$i] . "yen->add" . $doubletREWARDs[$i];
 echo "</h3>";
 }
 }
 }
 *
 *
 *
 *
 *
 *
 else {
 echo "
 <input type=\"button\" onclick=\"location.href='signin.php'\" value=\"Sign in\">";
 echo "<input type=\"button\" name=\"add\" onclick=\"location.href='signup.php'\" value=\"Sign up\"/>";
 }
 if ($id != "none0" && $groupID == "none0") {
 echo "<input type=\"button\" onclick=\"location.href='./create-group.php'\" value=\"Create a group\">";
 echo "<input type=\"button\" onclick=\"location.href='./join-group.php'\" value=\"Join a group\">";
 } else if ($groupID != "none0") {
 echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=0'\" value=\"Analysis\">";
 echo "<input type=\"button\" onclick=\"location.href='./signin-group.php'\" value=\"Edit " . $groupNAME . "\">";
 }
 * */

require_once 'func.php';

$db = getPDO();

$id = "";
$pass = "";
$groupID = "";
$reward = "";
$color = "";

$groupNAME = "";
$groupPASS = "";
$taskNAME = "";
$taskREWARD = "";
$arrangementTASK = "";
$arrangementACCOUNT = "";
$arrangementTASKs = array();
$arrangementACCOUNTs = array();
$arrangementTASKPrevious = "";
$arrangementACCOUNTPrevious = "";
$doubletAMOUNT = "";
$doubletREWARD = "";

$bingoWEIGHT = "";
$bingoREWARD = "";
$doDATE = "";
$doTASK = "";
$doREWARD = "";
$doACCOUNT = "";
$intervalTIME = "";
$start = "";
$end = "";
$dateSTART = "";
$dateEND = "";

if (isset($_COOKIE['id'])) {
	$id = $_COOKIE['id'];
} else {
	$id = "none0";
	setcookie("id", $id);
}
if (isValue(getAccountValue($id, "groupID"))) {
	$groupID = getAccountValue($id, "groupID");
	setAccountValue($id, "groupID", $groupID);
	setcookie("groupID", $groupID);
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {
		foreach ($stmt as $row) {
			$groupPASS = $row['groupPASS'];
			$groupNAME = $row['groupNAME'];
			$taskNAME = $row['taskNAME'];
			$taskREWARD = $row['taskREWARD'];
			$doubletAMOUNT = $row['doubletAMOUNT'];
			$doubletREWARD = $row['doubletREWARD'];
			$arrangementTASK = $row['arrangementTASK'];
			$arrangementACCOUNT = $row['arrangementACCOUNT'];
			$arrangementTASKPrevious = $row['arrangementTASKPrevious'];
			$arrangementACCOUNTPrevious = $row['arrangementACCOUNTPrevious'];
			$bingoWEIGHT = $row['bingoWEIGHT'];
			$bingoREWARD = $row['bingoREWARD'];
			$doDATE = $row['doDATE'];
			$doTASK = $row['doTASK'];
			$doREWARD = $row['doREWARD'];
			$doACCOUNT = $row['doACCOUNT'];
			$intervalTIME = $row['intervalTIME'];
			$start = $row['start'];
			$end = $row['end'];
			$dateSTART = $row['dateSTART'];
			$dateEND = $row['dateEND'];
		}
	} else {
		$groupID = "none0";
		setAccountValue($id, "groupID", $groupID);
		setcookie("groupID", $groupID);
	}
} else {
	$groupID = "none0";
	setAccountValue($id, "groupID", $groupID);
	setcookie("groupID", $groupID);
}
if ($groupID != null && $groupID != "none0") {
	if (date("Y/m/d") > $end) {
		$dateSTARTs = stringToArray($dateSTART);
		$dateENDs = stringToArray($dateEND);
		array_unshift($dateSTARTs, $start);
		array_unshift($dateENDs, $end);
		$dateSTART = arrayToString($dateSTARTs);
		$dateEND = arrayToString($dateENDs);
		setGroupValue($groupID, "dateSTART", $dateSTART);
		setGroupValue($groupID, "dateEND", $dateEND);

		$arrangementTASKs = explode(",", getGroupValue($groupID, "arrangementTASK"));
		$arrangementACCOUNTs = explode(",", getGroupValue($groupID, "arrangementACCOUNT"));
		$arrangementTASKPreviouses = stringToArray($arrangementTASKPrevious);
		$arrangementACCOUNTPreviouses = stringToArray($arrangementACCOUNTPrevious);
		$aTp = unifyArrangement($arrangementTASKs);
		$aAp = unifyArrangement($arrangementACCOUNTs);
		array_unshift($arrangementTASKPreviouses, $aTp);
		array_unshift($arrangementACCOUNTPreviouses, $aAp);
		$arrangementTASKPrevious = arrayToString($arrangementTASKPreviouses);
		$arrangementACCOUNTPrevious = arrayToString($arrangementACCOUNTPreviouses);
		setGroupValue($groupID, "arrangementTASKPrevious", $arrangementTASKPrevious);
		setGroupValue($groupID, "arrangementACCOUNTPrevious", $arrangementACCOUNTPrevious);

		$start = date("Y/m/01");
		setGroupValue($groupID, "start", $start);
		$end = date("Y/m/d", strtotime($start . "+" . $intervalTIME . " month"));
		$end = date("Y/m/d", strtotime($end . "-1 day"));
		setGroupValue($groupID, "end", $end);

		setGroupValue($groupID, "bingoCOMBINATION", "none0");

		$arrangementTASK = "";
		$arrangementACCOUNT = "";
		for ($i = 0; $i < 25; $i++) {
			$arrangementTASK .= "neutral,";
			$arrangementACCOUNT .= "neutral,";
		}
		if (mb_substr($arrangementTASK, -1) == ",") {
			$arrangementTASK = mb_substr($arrangementTASK, 0, -1);
			$arrangementACCOUNT = mb_substr($arrangementACCOUNT, 0, -1);
		}
		setGroupValue($groupID, "arrangementTASK", $arrangementTASK);
		setGroupValue($groupID, "arrangementACCOUNT", $arrangementACCOUNT);

		$sql = "SELECT * FROM User WHERE groupID='$groupID'";
		$stmt = $db -> query($sql);
		if ($stmt -> rowCount() > 0) {
			foreach ($stmt as $row) {
				$Gid = $row['id'];
				setAccountValue($Gid, "reward", "0");
			}
		}

		$taskNAMEs = explode(",", getGroupValue($groupID, "taskNAME"));
		$taskREWARDs = explode(",", getGroupValue($groupID, "taskREWARD"));
		list($taskNAME, $taskREWARD) = removeDo($taskNAMEs, $taskREWARDs);
		setGroupValue($groupID, "taskNAME", $taskNAME);
		setGroupValue($groupID, "taskREWARD", $taskREWARD);
	}
}
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
		<div id="head">
		<ul>
		<li>
		<a href="index.php"><img id="icon" src="img/title.png" alt="AndY-ou"/></a>
		</li>

		<?php
		if (isValue($id)) {
			echo "<li><a href=\"edit-account.php\"><h2>$id</h2></a></li>";
			echo "<li><a href=\"edit-account.php\"><img src=\"img/account.png\"/></a></li>";
		}
		echo "</ul>";
		echo "</div>";
		echo "<hr>";
		if (!isValue($id)) {
			echo "<div id=\"left\">";
			echo "<div id=\"menu\">";
			echo "<ul>";
			echo "<li><a class=\"active\" >Welcome</a></li>";
			echo "<li><a href=\"signin.php\">Sign in</a></li>";
			echo "<li><a href=\"signup.php\">Sign up</a></li>";
			echo "</ul>";
			echo "</div>";
		}
		if (isValue($groupID)) {
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
				$sql = "SELECT * FROM User WHERE groupID='$groupID'";
				$stmt = $db -> query($sql);

				if ($stmt -> rowCount() > 0) {
					foreach ($stmt as $row) {
						$ids[$row['id']] = $row['reward'];
					}
				}
				arsort($ids);

				foreach ($ids as $key => $value) {
					echo "<li>";
					echo $key . ' : ' . $value;
					echo "</li>";
				}

			}
			echo "</ol>";
			echo "</div>";
			echo "</div>";

			echo "<div id=\"pagebody\">";
			echo "<table class=\"table\" border=\"1\">";

			if ($arrangementTASK != null && $arrangementTASK != "none0") {
				$arrangementTASKs = explode(",", getGroupValue($groupID, "arrangementTASK"));
				$arrangementACCOUNTs = explode(",", getGroupValue($groupID, "arrangementACCOUNT"));

				for ($i = 0; $i < $bingoWEIGHT * $bingoWEIGHT; $i++) {
					if ($i % $bingoWEIGHT == 0)
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
							echo "<button type=\"button\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r&index=$i'\" value=\"code\" disabled><p></p></button>";
						} else {
							$tableColor = getAccountValue($arrangementACCOUNTs[$i], "color");
							echo "<button type=\"button\" style=\"background-color:$tableColor;\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r&index=$i'\" value=\"code\" disabled><span>$n<br/>$r</span></button>";
						}
						echo "</td>";
					}
					if (($i + 1) % $bingoWEIGHT == 0)
						echo "</tr>";
				}
			}
			echo "</table>";
			echo "<div id=\"do\">";
			if (isValue($doDATE)) {
				$doDATEs = explode(",", $doDATE);
				$doTASKs = explode(",", $doTASK);
				$doREWARDs = explode(",", $doREWARD);
				$doACCOUNTs = explode(",", $doACCOUNT);

				for ($i = 0; $i < count($doDATEs); $i++) {
					if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start) {
						echo "<p>";
						echo $doDATEs[$i] . " : " . $doACCOUNTs[$i] . "<br>->" . $doTASKs[$i] . " : " . $doREWARDs[$i] . "<br>";
						echo "</p>";
					}
				}
			}
			echo "</div>";
			echo "</div>";

			echo "<div id=\"task\">";
			if (isValue($taskNAME)) {
				$taskNAMEs = explode(",", $taskNAME);
				$taskREWARDs = explode(",", $taskREWARD);

				for ($i = 0; $i < count($taskNAMEs); $i++) {
					if (mb_substr($taskNAMEs[$i], 0, 3) != "###" && $taskNAMEs[$i] != "neutral" && $taskNAMEs[$i] != "none0") {
						echo "<a class=\"btn-sticky\" href=\"do.php?taskNAME=$taskNAMEs[$i]&taskREWARD=$taskREWARDs[$i]&index=-1\">$taskNAMEs[$i] $taskREWARDs[$i]</a>";
					}
				}

			}
			echo "</div>";
		} else {

		}
		?>
	</body>
</html>

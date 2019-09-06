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
 * */

require_once 'func.php';

$db;

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
$period = "";
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
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
	$db = getPDO();
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
			$period = $row['period'];
			$start = $row['start'];
			$end = $row['end'];
			$dateSTART = $row['dateSTART'];
			$dateEND = $row['dateEND'];
		}
	} else {
		$groupID = "none0";
		setcookie("groupID", $groupID);
	}
} else {
	$groupID = "none0";
	setcookie("groupID", $groupID);
}
if ($groupID != null && $groupID != "none0") {
	if (date("Y/m/d") >= $end) {
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

		$start = date("Y/m/d");
		$end = $start . "+" . $period . " day";
		setGroupValue($groupID, "start", $start);
		setGroupValue($groupID, "end", date("Y/m/d", strtotime($end)));

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

		$db = getPDO();
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
		<title>AndYou</title>
	</head>
	<body>
		<h1><a href="index.php">AndYou</a></h1>
		<?php
		if (isValue($id)) {
			echo "<h2>ID:";
			if ($id != "none0")
				echo $id;
			echo "</h2>";
		}
		if (isValue($groupID)) {
			echo "<h2>Group:";
			if ($groupNAME != "none0")
				echo $groupNAME;
			echo "</h2>";
			echo "<table>";
			echo "<table border=\"1\">";

			if ($arrangementTASK != null && $arrangementTASK != "none0") {
				$arrangementTASKs = explode(",", getGroupValue($groupID, "arrangementTASK"));
				$arrangementACCOUNTs = explode(",", getGroupValue($groupID, "arrangementACCOUNT"));
				$bingoWeight = getGroupValue($groupID, "bingoWEIGHT");

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
			}
			echo "		</table>";
		}
		if (isValue($taskNAME)) {
			$taskNAMEs = explode(",", $taskNAME);
			$taskREWARDs = explode(",", $taskREWARD);

			for ($i = 0; $i < count($taskNAMEs); $i++) {
				if (mb_substr($taskNAMEs[$i], 0, 3) != "###" && $taskNAMEs[$i] != "neutral" && $taskNAMEs[$i] != "none0") {
					echo "<h3>Task:";
					echo $taskNAMEs[$i] . "->" . $taskREWARDs[$i];
					echo "<input type=\"button\" onclick=\"location.href='do.php?taskNAME=$taskNAMEs[$i]&taskREWARD=$taskREWARDs[$i]&index=-1'\" value=\"Do it!\">";
					echo "</h3>";
				}
			}
		}

		if (isValue($doDATE)) {
			$doDATEs = explode(",", $doDATE);
			$doTASKs = explode(",", $doTASK);
			$doREWARDs = explode(",", $doREWARD);
			$doACCOUNTs = explode(",", $doACCOUNT);

			for ($i = 0; $i < count($doDATEs); $i++) {
				if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start) {
					echo "<h3>Do:";
					echo $doDATEs[$i] . "->" . $doTASKs[$i] . ":" . $doREWARDs[$i] . "->" . $doACCOUNTs[$i];
					echo "</h3>";
				}
			}
		}

		if ($id != "none0") {
			echo "<input type=\"button\" onclick=\"location.href='signout.php'\" value=\"Sign out\">";
			echo "<input type=\"button\" onclick=\"location.href='./edit-account.php'\" value=\"Setting\">";
		} else {
			echo "<input type=\"button\" onclick=\"location.href='signin.php'\" value=\"Sign in\">";
			echo "<input type=\"button\" name=\"add\" onclick=\"location.href='signup.php'\" value=\"Sign up\"/>";
		}
		if ($id != "none0" && $groupID == "none0") {
			echo "<input type=\"button\" onclick=\"location.href='./create-group.php'\" value=\"Create a group\">";
			echo "<input type=\"button\" onclick=\"location.href='./join-group.php'\" value=\"Join a group\">";
		} else if ($groupID != "none0") {
			echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=0'\" value=\"Analysis\">";
			echo "<input type=\"button\" onclick=\"location.href='./login_group.php'\" value=\"Edit " . $groupNAME . "\">";
		}

		echo "<br>";

		if ($groupID != "none0") {
			$ids = array();
			$db = getPDO();
			$sql = "SELECT * FROM User WHERE groupID='$groupID'";
			$stmt = $db -> query($sql);

			if ($stmt -> rowCount() > 0) {
				foreach ($stmt as $row) {
					$ids[$row['reward']] = $row['id'];
				}
			}
			krsort($ids);
			foreach ($ids as $key => $value) {
				echo $value . '->' . $key;
				echo "<br/>";
			}
		}
		?>
	</body>
</html>

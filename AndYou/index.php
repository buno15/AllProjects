<?php
/*同一アカウントID禁止　同一タスク名禁止　同一ボーナス報酬金額禁止*/
require_once 'func.php';

$id = "";
$pass = "";
$groupID = "";
$reward = "";
$color;

$groupNAME = "";
$groupPASS = "";
$taskNAME = "";
$taskREWARD = "";
$arrangementTASK = "";
$arrangementACCOUNT = "";
$arrangementTASKs;
$arrangementACCOUNTs;
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
 * */

if (isset($_COOKIE['id'])) {
	$id = $_COOKIE['id'];

} else {
	setcookie('id', "none0");
	setcookie('pass', "none0");
	setcookie('groupID', "none0");
	setcookie('reward', "0");
	setcookie('color', "none0");

	setcookie('groupNAME', "none0");
	setcookie('groupPASS', "none0");
	setcookie('taskNAME', "none0");
	setcookie('taskREWARD', "none0");
	setcookie('doubletAMOUNT', "none0");
	setcookie('doubletREWARD', "none0");

	setcookie('bingoWEIGHT', "none0");
	setcookie('bingoREWARD', "none0");
	setcookie('doDATE', "none0");
	setcookie('doTASK', "none0");
	setcookie('doREWARD', "none0");
	setcookie('doACCOUNT', "none0");
	setcookie('period', "none0");
	setcookie('start', "none0");
	setcookie('end', "none0");
}
if (isset($_COOKIE['pass'])) {
	$pass = $_COOKIE['pass'];
}
if (isset($_COOKIE['reward'])) {
	$reward = $_COOKIE['reward'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
	$color = getAccountValue($id, $pass, "color");
	if ($groupID == "none0") {
		setcookie('groupNAME', "none0");
		setcookie('taskNAME', "none0");
		setcookie('taskREWARD', "none0");
		setcookie('doubletAMOUNT', "none0");
		setcookie('doubletREWARD', "none0");
		setcookie('period', "none0");
		setcookie('bingoREWARD', "none0");
		setcookie('doDATE', "none0");
		setcookie('doTASK', "none0");
		setcookie('doREWARD', "none0");
		setcookie('doACCOUNT', "none0");
	}

	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
		foreach ($stmt as $row) {
			$groupNAME = $row['groupNAME'];
			$groupPASS = $row['groupPASS'];
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
		setcookie('groupNAME', $groupNAME);
		setcookie('groupPASS', $groupPASS);
		setcookie('taskNAME', $taskNAME);
		setcookie('taskREWARD', $taskREWARD);
		setcookie('doubletAMOUNT', $doubletAMOUNT);
		setcookie('doubletREWARD', $doubletREWARD);

		setcookie('arrangementTASK', $arrangementTASK);
		setcookie('arrangementACCOUNT', $arrangementACCOUNT);
		setcookie('arrangementTASKPrevious', $arrangementTASKPrevious);
		setcookie('arrangementACCOUNTPrevious', $arrangementACCOUNTPrevious);
		setcookie('bingoWEIGHT', $bingoWEIGHT);
		setcookie('bingoREWARD', $bingoREWARD);
		setcookie('doDATE', $doDATE);
		setcookie('doTASK', $doTASK);
		setcookie('doREWARD', $doREWARD);
		setcookie('doACCOUNT', $doACCOUNT);
		setcookie('period', $period);
		setcookie('start', $start);
		setcookie('end', $end);
		setcookie('dateSTART', $dateSTART);
		setcookie('dateEND', $dateEND);
	}
}
if (date("Y/m/d") >= $end) {
	$dateSTARTs = stringToArray($dateSTART);
	$dateENDs = stringToArray($dateEND);
	array_unshift($dateSTARTs, $start);
	array_unshift($dateENDs, $end);
	$dateSTART = arrayToString($dateSTARTs);
	$dateEND = arrayToString($dateENDs);

	setGroupValue($groupID, $groupPASS, "dateSTART", $dateSTART);
	setGroupValue($groupID, $groupPASS, "dateEND", $dateEND);
	setcookie('dateSTART', $dateSTART);
	setcookie('dateEND', $dateEND);

	$arrangementTASKs = explode(",", $_COOKIE['arrangementTASK']);
	$arrangementACCOUNTs = explode(",", $_COOKIE['arrangementACCOUNT']);
	$arrangementTASKPreviouses = stringToArray($arrangementTASKPrevious);
	$arrangementACCOUNTPreviouses = stringToArray($arrangementACCOUNTPrevious);
	$aTp = unifyArrangement($arrangementTASKs);
	$aAp = unifyArrangement($arrangementACCOUNTs);
	array_unshift($arrangementTASKPreviouses, $aTp);
	array_unshift($arrangementACCOUNTPreviouses, $aAp);
	$arrangementTASKPrevious = arrayToString($arrangementTASKPreviouses);
	$arrangementACCOUNTPrevious = arrayToString($arrangementACCOUNTPreviouses);

	setGroupValue($groupID, $groupPASS, "arrangementTASKPrevious", $arrangementTASKPrevious);
	setGroupValue($groupID, $groupPASS, "arrangementACCOUNTPrevious", $arrangementACCOUNTPrevious);
	setcookie('arrangementTASKPrevious', $arrangementTASKPrevious);
	setcookie('arrangementACCOUNTPrevious', $arrangementACCOUNTPrevious);

	$start = date("Y/m/d");
	$end = $start . "+" . $period . " day";

	setGroupValue($groupID, $groupPASS, "start", $start);
	setGroupValue($groupID, $groupPASS, "end", date("Y/m/d", strtotime($end)));
	setcookie('start', $start);
	setcookie('end', $end);

	setGroupValue($groupID, $groupPASS, "bingoCOMBINATION", "none0");
	setcookie('bingoCOMBINATION', "none0");

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
	setGroupValue($groupID, $groupPASS, "arrangementTASK", $arrangementTASK);
	setGroupValue($groupID, $groupPASS, "arrangementACCOUNT", $arrangementACCOUNT);
	setcookie('arrangementTASK', $arrangementTASK);
	setcookie('arrangementACCOUNT', $arrangementACCOUNT);

	$sql = "SELECT * FROM User WHERE groupID='$groupID'";
	$stmt = $db -> query($sql);

	$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	if ($stmt -> rowCount() > 0) {
		foreach ($stmt as $row) {
			$Gid = $row['id'];
			$Gpass = $row['pass'];
			setAccountValue($Gid, $Gpass, "reward", "0");
		}
	}
	setcookie('reward', "0");

	$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
	$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

	list($taskNAME, $taskREWARD) = removeDo($taskNAMEs, $taskREWARDs);

	setGroupValue($groupID, $groupPASS, "taskNAME", $taskNAME);
	setGroupValue($groupID, $groupPASS, "taskREWARD", $taskREWARD);
	setcookie('taskNAME', $taskNAME);
	setcookie('taskREWARD', $taskREWARD);
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
		echo "<h2>ID:";
		if ($id != "none0")
			echo $id;
		echo "</h2>";

		echo "<h2>Group:";
		if ($groupNAME != "none0")
			echo $groupNAME;
		echo "</h2>";
		?>

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
						if ($n == "neutral") {
							echo "<button type=\"button\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r&index=$i'\" value=\"code\" disabled>$n</button>";
						} else {
							$tableColor = getAccountValue($arrangementACCOUNTs[$i], $pass, "color");
							echo "<button type=\"button\" style=\"background-color:$tableColor;\" onclick=\"location.href='do.php?taskNAME=$n&taskREWARD=$r&index=$i'\" value=\"code\" disabled>$n<br/>$r</button>";
						}
						echo "</td>";
					}
					if (($i + 1) % $bingoWeight == 0)
						echo "</tr>";
				}
				?>
			</table>
			<?php
			if (isset($_COOKIE['taskNAME'])) {
				$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
				$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

				for ($i = 0; $i < count($taskNAMEs); $i++) {
					if (mb_substr($taskNAMEs[$i], 0, 3) != "###" && $taskNAMEs[$i] != "neutral" && $taskNAMEs[$i] != "none0") {
						echo "<h3>Task:";
						echo $taskNAMEs[$i] . "->" . $taskREWARDs[$i];
						echo "<input type=\"button\" onclick=\"location.href='do.php?taskNAME=$taskNAMEs[$i]&taskREWARD=$taskREWARDs[$i]&index=-1'\" value=\"Do it!\">";
						echo "</h3>";
					}
				}
			}

			/*if (isset($_COOKIE['doubletAMOUNT'])) {
			 $doubletAMOUNTs = explode(",", $_COOKIE['doubletAMOUNT']);
			 $doubletREWARDs = explode(",", $_COOKIE['doubletREWARD']);

			 for ($i = 0; $i < count($doubletAMOUNTs); $i++) {
			 if ($doubletAMOUNTs[$i] != "none0") {
			 echo "<h3>Doublet:";
			 echo $doubletAMOUNTs[$i] . "yen->add" . $doubletREWARDs[$i];
			 echo "</h3>";
			 }
			 }
			 }*/

			if (isset($_COOKIE['doDATE'])) {
				$doDATEs = explode(",", $_COOKIE['doDATE']);
				$doTASKs = explode(",", $_COOKIE['doTASK']);
				$doREWARDs = explode(",", $_COOKIE['doREWARD']);
				$doACCOUNTs = explode(",", $_COOKIE['doACCOUNT']);

				for ($i = 0; $i < count($doDATEs); $i++) {
					if ($doDATEs[$i] != "none0" && $doDATEs[$i] >= $start) {
						echo "<h3>Do:";
						echo $doDATEs[$i] . "->" . $doTASKs[$i] . ":" . $doREWARDs[$i] . "->" . $doACCOUNTs[$i];
						echo "</h3>";
					}
				}
			}

			if ($id != "none0") {
				echo "<input type=\"button\" onclick=\"location.href='logout.php'\" value=\"Logout\">";
				echo "<input type=\"button\" onclick=\"location.href='./analysis.php?index=0'\" value=\"Analysis\">";
				echo "<input type=\"button\" onclick=\"location.href='./edit_account.php'\" value=\"Setting\">";
			} else {
				echo "<input type=\"button\" onclick=\"location.href='login_account.php'\" value=\"Login\">";
			}
			if ($groupID == "none0") {
				echo "<input type=\"button\" onclick=\"location.href='./html/create_group.html'\" value=\"Create New Group\">";
				echo "<input type=\"button\" onclick=\"location.href='./html/join_group.html'\" value=\"Join a group\">";
			} else if ($groupID != "none0") {
				echo "<input type=\"button\" onclick=\"location.href='./login_group.php'\" value=\"Edit " . $groupNAME . "\">";

			}

			echo "<br>";

			if ($groupID != "none0") {
				$ids = array();
				$sql = "SELECT * FROM User WHERE groupID='$groupID'";
				$stmt = $db -> query($sql);

				if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
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
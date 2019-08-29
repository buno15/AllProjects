<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$id = $_COOKIE['id'];
$groupID = $_COOKIE['groupID'];
$taskNAME = $_GET['taskNAME'];
$taskREWARD = (int)$_GET['taskREWARD'];
$doDATE = date("Y/m/d H:i:s");

$reward = (int)$_COOKIE['reward'] + $taskREWARD;

$sql = "UPDATE User SET reward = '$reward' WHERE groupID = '$groupID' AND id='$id'";
$stmt = $db -> query($sql);

$taskNAMEs = explode(",", $_COOKIE['taskNAME']);
$taskREWARDs = explode(",", $_COOKIE['taskREWARD']);

$afterTaskNAME = "";
$afterTaskREWARD = "";
$afterDoDATE = $_COOKIE['doDATE'];
$afterDoTASK = $_COOKIE['doTASK'];
$afterDoREWARD = $_COOKIE['doREWARD'];
$afterDoACCOUNT = $_COOKIE['doACCOUNT'];

for ($i = 0; $i < count($taskNAMEs); $i++) {
	if ($taskNAMEs[$i] != "none0" && $taskNAMEs[$i] != $taskNAME) {
		$afterTaskNAME .= $taskNAMEs[$i] . ",";
		$afterTaskREWARD .= $taskREWARDs[$i] . ",";
	}
}

if (mb_substr($afterTaskNAME, -1) == ",") {
	$afterTaskNAME = mb_substr($afterTaskNAME, 0, -1);
	$afterTaskREWARD = mb_substr($afterTaskREWARD, 0, -1);
}

if ($afterTaskNAME == null) {
	$afterTaskNAME = "none0";
	$afterTaskREWARD = "none0";
}

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

header("Location: ./index.php");
exit ;
?>
<html>
	</body>
</html>
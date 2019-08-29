<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupID = $_COOKIE['groupID'];

$doDATE = $_GET['doDATE'];
$doTASK = $_GET['doTASK'];
$doREWARD = $_GET['doREWARD'];
$doACCOUNT = $_GET['doACCOUNT'];

$reward = 0;

$sql = "SELECT * FROM User WHERE id='$doACCOUNT'";
$stmt = $db -> query($sql);
if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	foreach ($stmt as $row) {
		$reward = (int)$row['reward'];
	}
}

$reward -= (int)$doREWARD;
$sql = "UPDATE User SET reward = '$reward' WHERE id = '$doACCOUNT'";
$stmt = $db -> query($sql);

setcookie('reward', $reward);

$doDATEs = explode(",", $_COOKIE['doDATE']);
$doTASKs = explode(",", $_COOKIE['doTASK']);
$doREWARDs = explode(",", $_COOKIE['doREWARD']);
$doACCOUNTs = explode(",", $_COOKIE['doACCOUNT']);

$afterDoDATE = "";
$afterDoTASK = "";
$afterDoREWARD = "";
$afterDoACCOUNT = "";

for ($i = 0; $i < count($doDATEs); $i++) {
	if ($doDATEs[$i] != "none0" && $doDATEs[$i] != $doDATE) {
		$afterDoDATE .= $doDATEs[$i] . ",";
		$afterDoTASK .= $doTASKs[$i] . ",";
		$afterDoREWARD .= $doREWARDs[$i] . ",";
		$afterDoACCOUNT .= $doACCOUNTs[$i] . ",";
	}
}

if (mb_substr($afterDoDATE, -1) == ",") {
	$afterDoDATE = mb_substr($afterDoDATE, 0, -1);
	$afterDoTASK = mb_substr($afterDoTASK, 0, -1);
	$afterDoREWARD = mb_substr($afterDoREWARD, 0, -1);
	$afterDoACCOUNT = mb_substr($afterDoACCOUNT, 0, -1);
}
if ($afterDoDATE == null) {
	$afterDoDATE = "none0";
	$afterDoTASK = "none0";
	$afterDoREWARD = "none0";
	$afterDoACCOUNT = "none0";
}

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sql = "UPDATE Gro SET doDATE = '$afterDoDATE' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET doTASK = '$afterDoTASK' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET doREWARD = '$afterDoREWARD' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET doACCOUNT = '$afterDoACCOUNT' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

setcookie('doDATE', $afterDoDATE);
setcookie('doTASK', $afterDoTASK);
setcookie('doREWARD', $afterDoREWARD);
setcookie('doACCOUNT', $afterDoACCOUNT);

echo 'Logを削除しました';
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='edit_group.php'" value="back"/>
	</body>
</html>
<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupID = $_COOKIE['groupID'];
$doubletAMOUNT = $_GET['doubletAMOUNT'];
$doubletREWARD = $_GET['doubletREWARD'];

$doubletAMOUNTs = explode(",", $_COOKIE['doubletAMOUNT']);
$doubletREWARDs = explode(",", $_COOKIE['doubletREWARD']);

$afterDoubletAMOUNT = "";
$afterDoubletREWARD = "";

for ($i = 0; $i < count($doubletAMOUNTs); $i++) {
	if ($doubletAMOUNTs[$i] != "none0" && $doubletAMOUNTs[$i] != $doubletAMOUNT) {
		$afterDoubletAMOUNT .= $doubletAMOUNTs[$i] . ",";
		$afterDoubletREWARD .= $doubletREWARDs[$i] . ",";
	}
}

if (mb_substr($afterDoubletAMOUNT, -1) == ",") {
	$afterDoubletAMOUNT = mb_substr($afterDoubletAMOUNT, 0, -1);
	$afterDoubletREWARD = mb_substr($afterDoubletREWARD, 0, -1);
}

if ($afterDoubletAMOUNT == null) {
	$afterDoubletAMOUNT = "none0";
	$afterDoubletREWARD = "none0";
}

$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$sql = "UPDATE Gro SET doubletAMOUNT = '$afterDoubletAMOUNT' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

$sql = "UPDATE Gro SET doubletREWARD = '$afterDoubletREWARD' WHERE groupID = '$groupID'";
$stmt = $db -> query($sql);

setcookie('doubletAMOUNT', $doubletAMOUNT);
setcookie('doubletREWARD', $doubletREWARD);

echo 'ボーナスを削除しました';
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='edit_group.php'" value="back"/>
	</body>
</html>
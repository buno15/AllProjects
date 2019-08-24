<?php
header('Content-Type: text/html; charset=UTF-8');

// データベースに接続
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$groupID = $_COOKIE['groupID'];
$doubletAMOUNT = $_GET['doubletAMOUNT'];
$doubletREWARD = $_GET['doubletREWARD'];

$afterDoubletAMOUNT = $_COOKIE['doubletAMOUNT'];
$afterDoubletREWARD = $_COOKIE['doubletREWARD'];

if ($doubletAMOUNT != "") {
	if ($afterDoubletAMOUNT != "none0") {
		$afterDoubletAMOUNT .= "," . $doubletAMOUNT;
		$afterDoubletREWARD .= "," . $doubletREWARD;
	} else {
		$afterDoubletAMOUNT = $doubletAMOUNT;
		$afterDoubletREWARD = $doubletREWARD;
	}
	$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$sql = "UPDATE Gro SET doubletAMOUNT = '$afterDoubletAMOUNT' WHERE groupID = '$groupID'";
	$stmt = $db -> query($sql);

	$sql = "UPDATE Gro SET doubletREWARD = '$afterDoubletREWARD' WHERE groupID = '$groupID'";
	$stmt = $db -> query($sql);

	setcookie('doubletAMOUNT', $afterDoubletAMOUNT);
	setcookie('doubletREWARD', $afterDoubletREWARD);

	echo 'ボーナスを追加しました';
} else {
	echo "ボーナスが不明です";
}
?>
<html>
	<body>
		<br />
		<input type="button" name="add" onclick="location.href='edit_group.php'" value="back"/>
	</body>
</html>
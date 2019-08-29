<?php
header('Content-Type: text/html; charset=UTF-8');

$groupID = $_COOKIE['groupID'];
$groupPASS = @$_COOKIE['groupPASS'];
$period = $_GET['period'];
$bingoREWARD = $_GET['bingoREWARD'];

$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$sql = "SELECT * FROM Gro WHERE groupID='$groupID' AND groupPASS='$groupPASS'";
$stmt = $db -> query($sql);

if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
	if ($period != null && $period != "0") {
		$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$sql = "UPDATE Gro SET period = '$period' , bingoREWARD = '$bingoREWARD' WHERE groupID='$groupID' AND groupPASS = '$groupPASS'";
		$stmt = $db -> query($sql);

		setcookie('period', $period);
		setcookie('bingoREWARD', $bingoREWARD);
		header("Location: ./edit_group.php");
		exit ;
	}
} else {
	echo 'wrong period';
}
?>
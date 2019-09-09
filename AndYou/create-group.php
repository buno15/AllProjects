<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<title>Account</title>
	</head>
	<body></body>
</html>
<?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';

$id = $_COOKIE['id'];
$groupID = "none0";
$groupNAME = "none0";
$groupPASS = "none0";

if (isset($_POST['groupNAME'])) {
	$groupNAME = $_POST['groupNAME'];
	$groupID = md5(uniqid(rand() . $groupNAME, 1));
}
if (isset($_POST['groupPASS'])) {
	$groupPASS = $_POST['groupPASS'];
}

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
echo "<li><a href=\"edit-account.php\">Home</a></li>";
echo "<li><a href=\"edit-account-pass.php\">Change password</a></li>";
if ($id != "none0" && $groupID == "none0") {
	echo "<li><a href=\"create-group.php\">Create group</a></li>";
	echo "<li><a href=\"join-group.php\">Join group</a></li>";
}
if (isset($_COOKIE['groupID']) && $_COOKIE['groupID'] != "none0")
	echo "<li><a href=\"signout-group.php?flag=conform\">Leave group</a></li>";
if ($id != "none0") {
	echo "<li><a href=\"signout.php\">Sign out</a></li>";
}
echo "<li><a href=\"delete-account.php\">Delete account</a></li>";
echo "</ul>";
echo "</div>";
echo "</div>";

echo "<div id=\"pagebody\">";

if (isValue($groupNAME) && isValue($groupPASS)) {
	$taskNAME = "";
	$taskREWARD = "";
	$arrangementTASK = "";
	$arrangementACCOUNT = "";
	for ($i = 0; $i < 25; $i++) {
		$taskNAME .= "neutral,";
		$taskREWARD .= "neutral,";
		$arrangementTASK .= "neutral,";
		$arrangementACCOUNT .= "neutral,";
	}
	if (mb_substr($taskNAME, -1) == ",") {
		$taskNAME = mb_substr($taskNAME, 0, -1);
		$taskREWARD = mb_substr($taskREWARD, 0, -1);
		$arrangementTASK = mb_substr($arrangementTASK, 0, -1);
		$arrangementACCOUNT = mb_substr($arrangementACCOUNT, 0, -1);
	}
	$arrangementTASKPrevious = "none0";
	$arrangementACCOUNTPrevious = "none0";
	$doubletAMOUNT = "none0";
	$doubletREWARD = "none0";
	$bingoWEIGHT = "3";
	$bingoREWARD = "100";
	$bingoCOMBINATION = "none0";
	$doDATE = "none0";
	$doTASK = "none0";
	$doREWARD = "none0";
	$doACCOUNT = "none0";
	$intervalTIME = "1";
	$start = date("Y/m/01");
	$end = date("Y/m/d", strtotime($start . "+1 month"));
	$end = date("Y/m/d", strtotime($end . "-1 day"));
	$dateSTART = "none0";
	$dateEND = "none0";

	$db = getPDO();
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
	$stmt = $db -> query($sql);

	if ($stmt -> rowCount() > 0) {
		echo 'Group already exists.';
	} else {
		$sql = "INSERT INTO Gro (groupID, groupPASS, groupNAME, taskNAME, taskREWARD, doubletAMOUNT, doubletREWARD, arrangementTASK, arrangementACCOUNT,  arrangementTASKPrevious, arrangementACCOUNTPrevious, bingoWEIGHT, bingoREWARD, bingoCOMBINATION, doDATE, doTASK, doREWARD, doACCOUNT, intervalTIME ,start ,end ,dateSTART ,dateEND) VALUES (:groupID, :groupPASS, :groupNAME, :taskNAME, :taskREWARD, :doubletAMOUNT, :doubletREWARD, :arrangementTASK, :arrangementACCOUNT, :arrangementTASKPrevious, :arrangementACCOUNTPrevious, :bingoWEIGHT, :bingoREWARD, :bingoCOMBINATION, :doDATE, :doTASK, :doREWARD, :doACCOUNT ,:intervalTIME ,:start ,:end ,:dateSTART ,:dateEND)";
		$stmt = $db -> prepare($sql);
		$stmt -> bindParam(':groupID', $groupID, PDO::PARAM_STR);
		$stmt -> bindParam(':groupPASS', $groupPASS, PDO::PARAM_STR);
		$stmt -> bindParam(':groupNAME', $groupNAME, PDO::PARAM_STR);
		$stmt -> bindParam(':taskNAME', $taskNAME, PDO::PARAM_STR);
		$stmt -> bindParam(':taskREWARD', $taskREWARD, PDO::PARAM_STR);
		$stmt -> bindParam(':doubletAMOUNT', $doubletAMOUNT, PDO::PARAM_STR);
		$stmt -> bindParam(':doubletREWARD', $doubletREWARD, PDO::PARAM_STR);

		$stmt -> bindParam(':arrangementTASK', $arrangementTASK, PDO::PARAM_STR);
		$stmt -> bindParam(':arrangementACCOUNT', $arrangementACCOUNT, PDO::PARAM_STR);
		$stmt -> bindParam(':arrangementTASKPrevious', $arrangementTASKPrevious, PDO::PARAM_STR);
		$stmt -> bindParam(':arrangementACCOUNTPrevious', $arrangementACCOUNTPrevious, PDO::PARAM_STR);
		$stmt -> bindParam(':bingoWEIGHT', $bingoWEIGHT, PDO::PARAM_STR);
		$stmt -> bindParam(':bingoREWARD', $bingoREWARD, PDO::PARAM_STR);
		$stmt -> bindParam(':bingoCOMBINATION', $bingoCOMBINATION, PDO::PARAM_STR);
		$stmt -> bindParam(':doDATE', $doDATE, PDO::PARAM_STR);
		$stmt -> bindParam(':doTASK', $doTASK, PDO::PARAM_STR);
		$stmt -> bindParam(':doREWARD', $doREWARD, PDO::PARAM_STR);
		$stmt -> bindParam(':doACCOUNT', $doACCOUNT, PDO::PARAM_STR);
		$stmt -> bindParam(':intervalTIME', $intervalTIME, PDO::PARAM_STR);
		$stmt -> bindParam(':start', $start, PDO::PARAM_STR);
		$stmt -> bindParam(':end', $end, PDO::PARAM_STR);
		$stmt -> bindParam(':dateSTART', $dateSTART, PDO::PARAM_STR);
		$stmt -> bindParam(':dateEND', $dateEND, PDO::PARAM_STR);
		$stmt -> execute();

		$sql = "UPDATE User SET groupID = '$groupID' WHERE id = '$id'";
		$stmt = $db -> query($sql);
		setcookie('groupID', $groupID);
		setcookie('groupPASS', $groupPASS);
		header("Location: ./create-group-setting.php");
		exit ;
	}
} else {
	echo "<form action=\"create-group.php\" method=\"POST\">";
	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"groupNAME\" placeholder=\"Group name\" required>";
	echo "</label>";
	echo "</div>";

	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"groupPASS\" placeholder=\"Group password\" required>";
	echo "</label>";
	echo "</div>";

	echo "<div class=\"submit\">";
	echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Create\">";
	echo "</div>";
	echo "</form>";
	echo "</div>";
}
?>
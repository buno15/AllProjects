<!DOCTYPE html>
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

header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';
$id = $_COOKIE['id'];
$groupID = $_COOKIE['groupID'];

$intervalTIME = "none0";
$bingoREWARD = "none0";
$bingoWEIGHT = "none0";

$start = getGroupValue($groupID, "start");
$end = "";

if (isset($_GET['intervalTIME']))
	$intervalTIME = $_GET['intervalTIME'];
if (isset($_GET['bingoREWARD']))
	$bingoREWARD = $_GET['bingoREWARD'];
if (isset($_GET['bingoWEIGHT']))
	$bingoWEIGHT = $_GET['bingoWEIGHT'];

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
echo "<li><a href=\"edit-account-pass.php\">Change password</a></li>";
if ($id != "none0") {
	echo "<li><a href=\"signout.php\">Sign out</a></li>";
}
echo "<li><a href=\"delete-account.php\">Delete account</a></li>";
echo "</ul>";
echo "</div>";
echo "</div>";

echo "<div id=\"pagebody\">";

if (isValue($intervalTIME) && isValue($bingoWEIGHT) && isValue($bingoREWARD)) {
	if (isGroup($groupID)) {// SELECTした行が存在する場合ログイン成功
		if ($intervalTIME != null && $intervalTIME != "0") {
			setGroupValue($groupID, "intervalTIME", $intervalTIME);
			$end = date("Y/m/d", strtotime($start . "+" . $intervalTIME . " month"));
			$end = date("Y/m/d", strtotime($end . "-1 day"));
			setGroupValue($groupID, "end", $end);

			setGroupValue($groupID, "bingoWEIGHT", $bingoWEIGHT);
			setGroupValue($groupID, "bingoREWARD", $bingoREWARD);

			if ($bingoWEIGHT != getGroupValue($groupID, "bingoWEIGHT")) {
				$arrangementTASK = "";
				$arrangementACCOUNT = "";
				for ($i = 0; $i < 25; $i++) {
					$arrangementTASK .= "neutral,";
					$arrangementACCOUNT .= "neutral,";
				}

				if (mb_substr($taskNAME, -1) == ",") {
					$arrangementTASK = mb_substr($arrangementTASK, 0, -1);
					$arrangementACCOUNT = mb_substr($arrangementACCOUNT, 0, -1);
				}
				setGroupValue($groupID, "arrangementTASK", $arrangementTASK);
				setGroupValue($groupID, "arrangementACCOUNT", $arrangementACCOUNT);

				setGroupValue($groupID, "bingoCOMBINATION", "none0");
			}

			header("Location: ./index.php");
			exit ;
		}
	} else {
		echo "<div class=\"error\">";
		echo 'Error<br>';
		echo "</div>";
	}
} else {
	echo "<form action=\"./create-group-setting.php\" method=\"get\">";

	echo "<div class=\"cp_ipselect\">";
	echo "<select class=\"cp_sl06\" name=\"intervalTIME\" required>";
	echo "<option value=\"\" hidden disabled selected>Interval</option>";
	echo "<option value=\"1\">A month</option>";
	echo "<option value=\"3\">Three months</option>";
	echo "<option value=\"6\">Six months</option>";
	echo "</select>";
	echo "<span class=\"cp_sl06_highlight\"></span>";
	echo "<span class=\"cp_sl06_selectbar\"></span>";
	echo "<label class=\"cp_sl06_selectlabel\">Interval</label>";
	echo "</div>";

	echo "<div class=\"cp_ipselect\">";
	echo "<select class=\"cp_sl06\" name=\"bingoWEIGHT\" required>";
	echo "<option value=\"\" hidden disabled selected>Table size</option>";
	echo "<option value=\"3\">3x3</option>";
	echo "<option value=\"4\">4x4</option>";
	echo "<option value=\"5\">5x5</option>";
	echo "</select>";
	echo "<span class=\"cp_sl06_highlight\"></span>";
	echo "<span class=\"cp_sl06_selectbar\"></span>";
	echo "<label class=\"cp_sl06_selectlabel\">Table size</label>";
	echo "</div>";

	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"number\" name=\"bingoREWARD\" value=\"$bingoREWARD\" placeholder=\"BINGO reward\" required>";
	echo "</label>";
	echo "</div>";

	echo "<div class=\"submit\">";
	echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Save\">";
	echo "</div>";
	echo "</form>";
	echo "</div>";
}
?>
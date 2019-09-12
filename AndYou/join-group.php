<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<link rel="stylesheet" href="css/base.css" />
		<link rel="stylesheet" media="screen and (max-width:800px)" href="css/base_smart.css" />
		<title>Account</title>
	</head>
	<body></body>
</html><?php
header('Expires:');
header('Cache-Control:');
header('Pragma:');
header('Content-Type: text/html; charset=UTF-8');

require_once 'func.php';

$id = $_COOKIE['id'];
$groupID = "none0";
if (isset($_GET['groupID'])) {
	$groupID = $_GET['groupID'];
}

echo "<div id=\"head\">";
echo "<ul>";
echo "<li>";
echo "<a href=\"index.php\"><img id=\"icon\" src=\"img/title.png\" alt=\"AndY-ou\"/></a>";
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

$db = getPDO();
$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
$stmt = $db -> query($sql);

if (isValue($groupID)) {
	if ($stmt -> rowCount() > 0) {
		$sql = "UPDATE User SET groupID = '$groupID',reward='0' WHERE id = '$id'";
		$stmt = $db -> query($sql);

		setcookie('groupID', $groupID);

		$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
		$stmt = $db -> query($sql);
		if ($stmt -> rowCount() > 0) {
			header("Location: ./index.php");
			exit ;
		}
	} else {
		echo "<div class=\"error\">";
		echo 'Group does not exist.';
		echo "</div>";
	}
} else {
	echo "<form action=\"join-group.php\" method=\"GET\">";

	echo "<div class=\"cp_iptxt\">";
	echo "<label class=\"ef\">";
	echo "<input type=\"text\" name=\"groupID\" placeholder=\"Group ID\" required>";
	echo "</label>";
	echo "</div>";

	echo "<div class=\"submit\">";
	echo "<input class=\"btn-flat-border\" type=\"submit\" value=\"Join\">";
	echo "</div>";
	echo "</form>";
	echo "</div>";
}
?>
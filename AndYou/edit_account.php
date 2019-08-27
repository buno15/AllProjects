<?php

$id = "";
$groupID = "";
$groupNAME = "";

if (isset($_COOKIE['id'])) {
	$id = $_COOKIE['id'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
}
if (isset($_COOKIE['groupNAME'])) {
	$groupNAME = $_COOKIE['groupNAME'];
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Please</title>
	</head>
	<body>
		<h1><a href="index.php">AndYou</a></h1>
		<h1>Setting</h1>
		<form action="edit_account_result.php" method="POST">
			ID
			<input type="text" name="id" placeholder="id" value="<?php
			echo $id;
			?>">
			<br>
			<input type="submit" value="Save">
			<input type="button" name="add" onclick="location.href='html/edit_account_pass.html'" value="Change Password"/>
			<input type="button" name="add" onclick="location.href='html/delete_account.html'" value="Delete this account"/>
			<?php
			if ($groupID != "none0") {
				echo "<input type=\"button\" onclick=\"location.href='./exit_group.php'\" value=\"Leave " . $groupNAME . "\">";
			}
			?>
		</form>
	</body>
</html>
<?php
$groupID = $_POST['groupID'];
$groupPASS = $_POST['groupPASS'];
$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");

$sql = "SELECT * FROM Gro WHERE groupID ='$groupID' AND groupPASS = '$groupPASS'";
$stmt = $db -> query($sql);
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<?php
		if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
			$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$sql = "DELETE FROM Gro WHERE groupID = '$groupID' AND groupPASS='$groupPASS'";
			$stmt = $db -> query($sql);

			echo 'Delete this Group';
			setcookie('groupID', "none0");
			setcookie('groupNAME', "none0");
			setcookie('taskNAME', "none0");
			setcookie('taskREWARD', "none0");
			setcookie('doubletAMOUNT', "none0");
			setcookie('doubletREWARD', "none0");
		} else {
			echo 'Error';
		}
		?>
		<br />
		<input type="button" name="add" onclick="location.href='index.php'" value="back"/>
	</body>
</html>
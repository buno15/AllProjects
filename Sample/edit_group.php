<?php
session_start();

$groupID = "";
$doubletAMOUNT = "";
$doubletREWARD = "";

if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];

	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
		foreach ($stmt as $row) {
			$doubletAMOUNT = $row['doubletAMOUNT'];
			$doubletREWARD = $row['doubletREWARD'];
		}
		setcookie('doubletAMOUNT', $doubletAMOUNT);
		setcookie('doubletREWARD', $doubletREWARD);
	}
}
 ?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>AndYou</title>
	</head>
	<body>
		<h2>Group:<?php echo $_COOKIE['groupNAME']; ?></h2>
		<?php
		if (isset($_COOKIE['doubletAMOUNT'])) {
			$doubletAMOUNTs = explode(",", $_COOKIE['doubletAMOUNT']);
			$doubletREWARDs = explode(",", $_COOKIE['doubletREWARD']);

			for ($i = 0; $i < count($doubletAMOUNTs); $i++) {
				if ($doubletAMOUNTs[$i] != "none0") {
					echo "<h3>Doublet:";
					echo $doubletAMOUNTs[$i] . "yen->add" . $doubletREWARDs[$i];
					echo "<input type=\"button\" onclick=\"location.href='delete_doublet.php?doubletAMOUNT=$doubletAMOUNTs[$i]&doubletREWARD=$doubletREWARDs[$i]'\" value=\"Delete\">";
					echo "</h3>";
				}
			}
		}
		?>
		<input type="button" name="add" onclick="location.href='index.php'" value="戻る">
		<input type="button" name="add" onclick="location.href='./html/create_doublet.html'" value="ボーナス作成">
	</body>
</html>
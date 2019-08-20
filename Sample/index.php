<?php
$id = "";
$pass = "";
$groupID = "";
$groupNAME = "";

if (isset($_COOKIE['id'])) {
	$id = $_COOKIE['id'];
}
if (isset($_COOKIE['pass'])) {
	$pass = $_COOKIE['pass'];
}
if (isset($_COOKIE['groupID'])) {
	$groupID = $_COOKIE['groupID'];
	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID'";
	$stmt = $db -> query($sql);
	if ($stmt -> rowCount() > 0) {// SELECTした行が存在する場合ログイン成功
		foreach ($stmt as $row) {
			$groupNAME = $row['groupNAME'];
		}
		setcookie('groupNAME', $groupNAME);
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
		<h1>AndYou</h1>
		<h2>ID:<?php echo $id; ?></h2>
		<h2>Group:<?php echo $groupNAME; ?></h2>
		<?php
		for ($i = 0; $i < 2; $i++) {
			echo "<h3>";
			echo "</h3>";
			echo "<br/>";
		}
		?>
		<input type="button" name="add" onclick="location.href='login.php'" value="ログイン"/>
		<input type="button" name="add" onclick="location.href='./html/create_group.html'" value="グループ作成"/>
		<input type="button" name="add" onclick="location.href='./html/add.html'" value="グループ参加"/>
		<input type="button" name="add" onclick="location.href='./html/create_task.html'" value="タスク追加"/>
	</body>
</html>
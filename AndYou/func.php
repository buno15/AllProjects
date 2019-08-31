<?php

//Account

function isAccount($id, $pass) {
	header('Content-Type: text/html; charset=UTF-8');
	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$sql = "SELECT * FROM User WHERE id='$id' AND pass='$pass'";
	$stmt = $db -> query($sql);

	return $stmt -> rowCount() > 0 ? true : false;
}

function getAccountValue() {
	$sql = "SELECT * FROM User WHERE id='$id' AND pass='$pass'";
}

//Group

function isGroup($groupID, $groupPASS) {
	header('Content-Type: text/html; charset=UTF-8');
	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID' AND groupPASS='$groupPASS'";
	$stmt = $db -> query($sql);

	return $stmt -> rowCount() > 0 ? true : false;
}

function getGroupValue($groupID, $groupPASS, $kindOf) {
	header('Content-Type: text/html; charset=UTF-8');
	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$sql = "SELECT * FROM Gro WHERE groupID='$groupID' AND groupPASS='$groupPASS'";
	$stmt = $db -> query($sql);

	if ($stmt -> rowCount() > 0) {
		foreach ($stmt as $row) {
			$value = $row[$kindOf];
			return $value;
		}
	} else {
		return NULL;
	}
}

function setGroupValue($groupID, $groupPASS, $kindOf, $value) {
	if (isGroup($groupID, $groupPASS)) {
		header('Content-Type: text/html; charset=UTF-8');
		$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
		$sql = "UPDATE Gro SET $kindOf = '$value' WHERE groupID='$groupID' AND groupPASS = '$groupPASS'";
		$stmt = $db -> query($sql);
	}
}

function splitArray($array, $weight) {//一次元配列→二次元配列
	$table = array();
	$count = 0;
	for ($i = 0; $i < $weight; $i++) {
		for ($j = 0; $j < $weight; $j++) {
			$table[$i][$j] = $array[$count++];
		}
	}
	return $table;
}

/*function getBingoWeight($array) {//テーブルサイズ
 $count = 0;
 for ($i = 0; $i < count($array); $i++) {
 if ($array[$i] != "neutral") {
 $count++;
 }
 }
 if ($count <= 9) {
 return 3;
 } else if ($count <= 16) {
 return 4;
 } else {
 return 5;
 }
 }*/

/*function getRandomArray($array1, $array2) {//テーブルランダム配置
 $bingoWeight = getBingoWeight($array1);
 $array3;
 $array4;
 $random = range(0, $bingoWeight * $bingoWeight - 1);
 shuffle($random);
 for ($i = 0; $i < $bingoWeight * $bingoWeight; $i++) {
 $array3[$i] = $array1[$random[$i]];
 $array4[$i] = $array2[$random[$i]];
 }
 return array($array3, $array4);
 }*/

function removeTask($array1, $array2, $taskNAME, $taskREWARD) {
	$after1 = "";
	$after2 = "";
	for ($i = 0; $i < count($array1); $i++) {
		if ($array1[$i] != $taskNAME || $array2[$i] != $taskREWARD) {
			$after1 .= $array1[$i] . ",";
			$after2 .= $array2[$i] . ",";
		}
	}
	$after1 .= "neutral";
	$after2 .= "neutral";
	return array($after1, $after2);
}

function addDo($array1, $array2, $taskNAME, $taskREWARD) {
	$after1 = "";
	$after2 = "";
	for ($i = 0; $i < count($array1); $i++) {
		if ($array1[$i] != $taskNAME) {
			$after1 .= $array1[$i] . ",";
			$after2 .= $array2[$i] . ",";
		} else if ($array1[$i] == $taskNAME && $array2[$i] == $taskREWARD) {
			$after1 .= "###" . $array1[$i] . ",";
			$after2 .= "###" . $array2[$i] . ",";
		}
	}
	if (mb_substr($after1, -1) == ",") {
		$after1 = mb_substr($after1, 0, -1);
		$after2 = mb_substr($after2, 0, -1);
	}
	return array($after1, $after2);
}
?>

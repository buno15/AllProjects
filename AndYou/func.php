<?php

//Account

function isAccount($id, $pass) {
	header('Content-Type: text/html; charset=UTF-8');
	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$sql = "SELECT * FROM User WHERE id='$id'";
	$stmt = $db -> query($sql);

	return $stmt -> rowCount() > 0 ? true : false;
}

function getAccountValue($id, $pass, $kindOf) {
	header('Content-Type: text/html; charset=UTF-8');
	$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
	$db -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$sql = "SELECT * FROM User WHERE id='$id'";
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

function setAccountValue($id, $pass, $kindOf, $value) {
	if (isAccount($id, $pass)) {
		header('Content-Type: text/html; charset=UTF-8');
		$db = new PDO("mysql:host=127.0.0.1;dbname=AndYou", "root", "");
		$sql = "UPDATE User SET $kindOf = '$value' WHERE id='$id'";
		$stmt = $db -> query($sql);
	}
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

function arrayToString($array) {
	$str = "";
	for ($i = 0; $i < count($array); $i++) {
		$str .= $array[$i] . ",";
	}
	while (mb_substr($str, -1) == ",") {
		$str = mb_substr($str, 0, -1);
	}
	return $str;
}

function stringToArray($str) {
	$array = explode(",", $str);
	return $array;
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

function removeDo($array1, $array2) {
	$after1 = "";
	$after2 = "";
	for ($i = 0; $i < count($array1); $i++) {
		if (mb_substr($array1[$i], 0, 3) == "###") {
			$after1 .= mb_substr($array1[$i], 3) . ",";
			$after2 .= mb_substr($array2[$i], 3) . ",";
		} else {
			$after1 .= $array1[$i] . ",";
			$after2 .= $array2[$i] . ",";
		}
	}
	if (mb_substr($after1, -1) == ",") {
		$after1 = mb_substr($after1, 0, -1);
		$after2 = mb_substr($after2, 0, -1);
	}
	return array($after1, $after2);
}

function addArrangement($arrangementTASKs, $arrangementACCOUNTs, $taskNAME, $id, $index) {
	$after1 = "";
	$after2 = "";
	for ($i = 0; $i < count($arrangementTASKs); $i++) {
		if ($i == $index) {
			$after1 .= $taskNAME . ",";
			$after2 .= $id . ",";
		} else {
			$after1 .= $arrangementTASKs[$i] . ",";
			$after2 .= $arrangementACCOUNTs[$i] . ",";
		}
	}
	if (mb_substr($after1, -1) == ",") {
		$after1 = mb_substr($after1, 0, -1);
		$after2 = mb_substr($after2, 0, -1);
	}
	return array($after1, $after2);
}

function unifyArrangement($arrangement) {
	$str = "";
	for ($i = 0; $i < count($arrangement); $i++) {
		$str .= $arrangement[$i] . "-";
	}
	if (mb_substr($str, -1) == "-") {
		$str = mb_substr($str, 0, -1);
	}
	return $str;
}

function splitArrangement($arrangement) {
	return explode("-", $arrangement);
}

function isBingo($arrangementACCOUNTs, $bingoWEIGHT, $index) {//ビンゴ判定
	$dx = array( array(1, -1), array(0, 0), array(1, -1), array(1, -1));
	$dy = array( array(0, 0), array(1, -1), array(1, -1), array(-1, 1));

	list($x, $y) = getMatrix($index, $bingoWEIGHT);
	$table = splitArray($arrangementACCOUNTs, $bingoWEIGHT);
	$bingoCOMBINATIONs = array();

	for ($i = 0; $i < 4; $i++) {
		$count = 0;
		$stackX = array($x);
		$stackY = array($y);
		$listX = array();
		$listY = array();

		while (!empty($stackX) && !empty($stackY)) {
			$X = array_shift($stackX);
			$Y = array_shift($stackY);
			array_push($listX, $X);
			array_push($listY, $Y);
			for ($j = 0; $j < 2; $j++) {
				$nx = $X + $dx[$i][$j];
				$ny = $Y + $dy[$i][$j];
				if ($nx >= 0 && $nx < $bingoWEIGHT && $ny >= 0 && $ny < $bingoWEIGHT) {
					if (isList($listX, $listY, $nx, $ny)) {
						continue;
					}
					if ($table[$y][$x] == $table[$ny][$nx]) {
						array_push($stackX, $nx);
						array_push($stackY, $ny);
						$count++;
					}
				}
			}
			if (count($listX) == $bingoWEIGHT) {//BINGO組み合わせ作成
				$mat = array();
				//組み合わせソート用
				$comb = "";
				//組み合わせ文字列
				for ($j = 0; $j < count($listX); $j++) {
					array_push($mat, $listX[$j] . $listY[$j]);
				}
				sort($mat);
				for ($j = 0; $j < count($mat); $j++) {
					$comb .= $mat[$j] . "-";
				}
				if (mb_substr($comb, -1) == "-") {
					$comb = mb_substr($comb, 0, -1);
				}
				array_push($bingoCOMBINATIONs, $comb);
			}
		}
	}
	return $bingoCOMBINATIONs;
}

function getMatrix($index, $bingoWEIGHT) {//配列から座標取得
	$x = intval($index % $bingoWEIGHT);
	$y = intval($index / $bingoWEIGHT);
	return array($x, $y);
}

function isList($listX, $listY, $x, $y) {//探索済みリストに登録されいているか
	for ($i = 0; $i < count($listX); $i++) {
		if ($listX[$i] == $x && $listY[$i] == $y) {
			return true;
		}
	}
	return false;
}

function signBingo($arrangementACCOUNTs, $bingoWEIGHT, $index) {

}
?>

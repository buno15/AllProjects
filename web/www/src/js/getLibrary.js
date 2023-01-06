/*
 * var xmlHttp; if (window.XMLHttpRequest) { xmlHttp = new XMLHttpRequest(); }
 * else { if (window.ActiveXObject) { xmlHttp = new
 * ActiveXObject("Microsoft.XMLHTTP"); } else { xmlHttp = null; } }
 * xmlHttp.open("GET", "http://www.bunooboi.sakura.ne.jp/res/BMlist.txt",
 * false); xmlHttp.send(null); var array = xmlHttp.responseText.split("\n"); for
 * (var i = 0; i < array.length; i++) {
 * document.getElementById("library").innerHTML += '<a
 * href="../../../res/sample' + (i + 1) + '.zip" target="_blank">' + array[i] + '</a><br />'; }
 */
var array = new Array();

array[0] = "Rio Olympics Men's Singles Semi-final";
array[1] = "Rio Olympics Men's Singles Gold Medal Match";
array[2] = "Rio Olympics Men's Doubles Gold Medal Match";
array[3] = "Rio Olympics Women's Singles Gold Medal Match";
array[4] = "リオオリンピック準々決勝女子シングルス奥原希望VS山口茜";
array[5] = "リオオリンピック準決勝女子シングルス奥原VSプサルラ";
array[6] = "リオオリンピック決勝女子ダブルス高橋松友VSリターユールペデルセン";
array[7] = "2016全日本実業団男子決勝第一ダブルス園田嘉村VS早川遠藤";
array[8] = "2015全日本総合男子ダブルス園田嘉村VS保木小林";
array[9] = "2015全日本総合女子ダブルス福万輿猶VS高橋松友";
array[10] = "2015全日本総合男子シングルス桃田VS西本";
array[11] = "2013全国小学生大会";

for (var i = 0; i < array.length; i++) {
	document.getElementById("library").innerHTML += '<a href="../../../res/sample'
			+ (i + 1) + '.zip" target="_blank">' + array[i] + '</a><br />';
}
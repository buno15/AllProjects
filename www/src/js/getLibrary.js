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
var array=new Array();
array[0] = "2015全日本総合男子ダブルス園田嘉村VS保木小林";
array[1] = "2015全日本総合女子ダブルス福万輿猶VS高橋松友";
array[2] = "2016全日本実業団男子決勝第一ダブルス園田嘉村VS早川遠藤";
array[3] = "2015全日本総合男子シングルス桃田VS西本";
array[4] = "2013全国小学生大会";
array[5] = "リオオリンピック準々決勝女子シングルス奥原希望VS山口茜";
array[6] = "リオオリンピック準決勝女子シングルス奥原VSプサルラ";
array[7] = "リオオリンピック決勝女子ダブルス高橋松友VSリターユールペデルセン";
array[8] = "Rio Olympics Men's Doubles Gold Medal Match";
array[9] = "Rio Olympics Men's Singles Gold Medal Match";
array[10] = "Rio Olympics Women's Singles Gold Medal Match";
array[11] = "Rio Olympics Men's Singles Semi-final";
for (var i = 0; i < array.length; i++) {
	document.getElementById("library").innerHTML += '<a href="../../../res/sample'
			+ (i + 1) + '.zip" target="_blank">' + array[i] + '</a><br />';
}
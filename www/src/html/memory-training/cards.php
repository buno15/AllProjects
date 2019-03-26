<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1">
<link rel="stylesheet" href="../../css/base.css" />
<link rel="stylesheet" media="screen and (max-width:800px)"
	href="../../css/base_smart.css" />
<link rel="stylesheet" href="../../css/table.css" />
<link rel="stylesheet" media="screen and (max-width:800px)"
	href="../../css/table_smart.css" />
<link rel="shortcut icon" href="../../../img/icon.ico" />
<title>割り勘｜bunooboi</title>
</head>
<body>
	<div id="pagebody">
		<div id="head">
			<h1>
				<a href="../../../index.html"><img src="../../../img/title.png"
					alt="bunooboi" /></a>
			</h1>
		</div>
		<ul id="menu">
			<li><a href="../../../index.html">Home</a></li>
			<li><a href="../app-game.html">App/Game</a></li>
			<li><a href="../web.html">WebApplication</a></li>
			<li><a href="../feedback.html">Message</a></li>
		</ul>
		<div class="title">
			<img src="../../../img/web_warikan.png" alt="割り勘のアイコン画像" />
			<h2>割り勘</h2>
		</div>
		<div id="text">
			<table class="table">
				<tr>
					<th>人数</th>
					<td><input id="human" type="number"></td>
					<th>人</th>
				</tr>
				<tr>
					<th>お会計</th>
					<td><input id="money" type="number"></td>
					<th>円</th>
				</tr>
			</table>
			<div class="radio">
				<input name="fraction" type="radio" id="r1"> <label for="r1">1円</label>
				<input name="fraction" type="radio" id="r2" checked> <label
					for="r2">10円</label> <input name="fraction" type="radio" id="r3">
				<label for="r3">100円</label>
			</div>
			<div class="button">
				<button id="ok" type="button">決定</button>
				<button id="clear" type="button">クリア</button>
			</div>
			<table class="table">
				<tr>
					<th>一人当たり</th>
					<td><input id="oneperson" type="number"></td>
					<th>円</th>
				</tr>
				<tr>
					<th>お支払い</th>
					<td><input id="result" type="number"></td>
					<th>円</th>
				</tr>
				<tr>
					<th>お釣り</th>
					<td><input id="remainder" type="number"></td>
					<th>円</th>
				</tr>
			</table>
		</div>
		<div id="copy">
			<small>&copy; bunooboi</small>
		</div>
		<script type="text/javascript" src="../../js/warikan.js"></script>
	</div>
</body>
</html>
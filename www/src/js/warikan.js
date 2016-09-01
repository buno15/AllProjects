var money = 0;
var human = 0;
var result = 0;
var oneperson=0;
var remainder = 0;
var fraction = 0;
var radioList = document.getElementsByName("fraction");

document.getElementById("ok").onclick=function() {
	for (var i = 0; i < radioList.length; i++) {
		if (radioList[0].checked) {
			fraction=1;
		} else if (radioList[1].checked) {
			fraction=10;
		} else if (radioList[2].checked) {
			fraction=100;
		}
	}
	money=parseInt(document.getElementById("money").value);
	human=parseInt(document.getElementById("human").value);
	if(isNaN(money)==false&&isNaN(human)==false){
		document.getElementById("money").value=money;
		document.getElementById("human").value=human;
		if(human==0){
			alert("0で割っています");
		}else{
		if (money > human) {
			oneperson =Math.ceil((money / human / fraction)) * fraction;
			result = oneperson * human;
			remainder = result - money;
			document.getElementById('result').value = result;
			document.getElementById('oneperson').value = oneperson;
			document.getElementById('remainder').value = remainder;
		} else {
			alert("人数が金額より多いです");
		}
	}
	}else{
		alert("無効な入力です");
	}
}
document.getElementById("clear").onclick = function() {
	money = 0;
	human = 0;
	result = 0;
	oneperson=0;
	remainder = 0;
	fraction = 0;
	document.getElementById('money').value = "";
	document.getElementById('human').value = "";
	document.getElementById('result').value = "";
	document.getElementById('oneperson').value = "";
	document.getElementById('remainder').value = "";
}
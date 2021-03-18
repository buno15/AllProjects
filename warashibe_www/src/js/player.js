var item;

var hp = -1;
var damage = -1;
var power = -1;
var brain = -1;
var item_have;
var add = -1;

function init() {
	item = new Array();
	item.push(new Item("なし", -1, -1));
	item.push(new Item("木の枝", 12, 1));

	item_have = item[0];
	load();
};

function save() {
	localStorage.setItem("hp", hp);
}

function load() {
	hp = localStorage.getItem("hp");
}

init();



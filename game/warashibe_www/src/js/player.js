var hp = -1;
var damage = -1;
var power = -1;
var brain = -1;
var item_have1;
var item_have2;
var addHP1 = -1;
var addPower1 = -1;
var addBrain1 = -1;
var addHP2 = -1;
var addPower2 = -1;
var addBrain2 = -1;
var level = -1;

var enemyID = "";
var n_flag;
//はじめからかどうか

function save(name) {
    localStorage.setItem("hp", hp);
    localStorage.setItem("damage", damage);
    localStorage.setItem("power", power);
    localStorage.setItem("brain", brain);
    localStorage.setItem("item_have1", item_have1.getName());
    localStorage.setItem("item_have2", item_have2.getName());
    localStorage.setItem("n_flag", n_flag);
    localStorage.setItem("enemyID", name);
}

function saveBattle(name) {
    localStorage.setItem("enemyID", name);
}

function load() {
    hp = parseInt(localStorage.getItem("hp"));
    damage = parseInt(localStorage.getItem("damage"));
    power = parseInt(localStorage.getItem("power"));
    brain = parseInt(localStorage.getItem("brain"));
    item_have1 = getItemIsName(localStorage.getItem("item_have1"));
    item_have2 = getItemIsName(localStorage.getItem("item_have2"));
    enemyID = localStorage.getItem("enemyID");
}

function itemDrop(id) {
    if (id == 1) {
        item_have1 = item[0];
    } else if (id == 2) {
        item_have2 = item[0];
    } else if (id == 3) {
        item_have1 = item[0];
        item_have2 = item[0];
    }
}

function getYesNo() {
    var random = Math.round(Math.random() * 99) + 1;
    if (random <= (brain + addBrain1 + addBrain2)) {
        return true;
    } else {
        return false;
    }
}

function setLevel() {
    if ((hp > 750 && hp <= 1000) || (power > 75 && power <= 500) || (brain > 75 && brain <= 500)) {
        level = 4;
    } else if ((hp > 500 && hp <= 750) || (power > 50 && power <= 75) || (brain > 50 && brain <= 75)) {
        level = 3;
    } else if ((hp > 250 && hp <= 500) || (power > 25 && power <= 50) || (brain > 25 && brain <= 50)) {
        level = 2;
    } else {
        level = 1;
    }

}


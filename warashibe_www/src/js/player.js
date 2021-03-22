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

function save() {
    localStorage.setItem("hp", hp);
}

function load() {
    hp = localStorage.getItem("hp");
    hp = 50;
    damage = 50;
    power = 50;
    brain = 50;
    item_have1 = item[20];
    item_have2 = item[21];
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
    if ((hp > 75 && hp <= 1000) || (power > 75 && power <= 1000) || (brain > 75 && brain <= 1000)) {
        level = 4;
    } else if ((hp > 50 && hp <= 75) || (power > 50 && power <= 75) || (brain > 50 && brain <= 75)) {
        level = 3;
    } else if ((hp > 25 && hp <= 50) || (power > 25 && power <= 50) || (brain > 25 && brain <= 50)) {
        level = 2;
    } else {
        level = 1;
    }

}


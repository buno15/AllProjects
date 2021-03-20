var Item = function(name, value, level, food) {
    if (!(this instanceof Item)) {
        return new Item(name, value, level);
    }
    this.name = name;
    this.value = value;
    this.level = level;
    this.food = food;
};

Item.prototype = {
    getName : function() {
        return this.name;
    },

    getValue : function() {
        return this.value;
    },

    getLevel : function() {
        return this.level;
    },

    isFood : function() {
        return this.food;
    },

    isNone : function() {
        if (this.name == "なし") {
            return true;
        } else {
            return false;
        }
    },
};

function getAdd(name) {
    //hp1 power2 brain3
    if (name.indexOf("鎧") != -1 || name.indexOf("兜") != -1) {
        return 1;
    } else if (name.indexOf("弓") != -1 || name.indexOf("槍") != -1 || name.indexOf("剣") != -1) {
        return 2;
    } else if (name.indexOf("インサイト") != -1) {
        return 3;
    } else {
        return 0;
    }
}

var item = new Array();
item.push(new Item("なし", -1, -1));

item.push(new Item("シャベル", 0, 1, false));
item.push(new Item("パン", 3, 1, true));
item.push(new Item("イモ", 2, 1, true));
item.push(new Item("魚", 4, 1, true));
item.push(new Item("肉", 5, 1, true));
item.push(new Item("イス", 0, 1, false));
item.push(new Item("テーブル", 0, 1, false));
item.push(new Item("ナベ", 0, 1, false));
item.push(new Item("笛", 0, 1, false));
item.push(new Item("寝袋", 1, 1, false));
item.push(new Item("ボロイ弓", 3, 1, false));
item.push(new Item("ボロイ槍", 5, 1, false));
item.push(new Item("ボロイ剣", 4, 1, false));
item.push(new Item("ボロイ鎧", 5, 1, false));
item.push(new Item("ボロイ兜", 3, 1, false));
item.push(new Item("インサイト", 5, 1, false));

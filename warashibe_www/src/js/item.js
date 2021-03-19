var Item = function(name, value, level) {
    if (!(this instanceof Item)) {
        return new Item(name, value, level);
    }
    this.name = name;
    this.value = value;
    this.level = level;
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

    isNone : function() {
        if (this.name == "なし") {
            return true;
        } else {
            return false;
        }
    },
};

var item = new Array();
item.push(new Item("なし", -1, -1));

item.push(new Item("シャベル", 0, 1));
item.push(new Item("パン", 3, 1));
item.push(new Item("イモ", 2, 1));
item.push(new Item("魚", 4, 1));
item.push(new Item("肉", 5, 1));
item.push(new Item("イス", 0, 1));
item.push(new Item("テーブル", 0, 1));
item.push(new Item("ナベ", 0, 1));
item.push(new Item("笛", 0, 1));
item.push(new Item("寝袋", 1, 1));
item.push(new Item("ボロイ弓", 3, 1));
item.push(new Item("ボロイ槍", 5, 1));
item.push(new Item("ボロイ剣", 4, 1));
item.push(new Item("ボロイ鎧", 5, 1));
item.push(new Item("ボロイ兜", 3, 1));
item.push(new Item("インサイト", 5, 1));

var Item = function(name, value, level, food, horse) {
    if (!(this instanceof Item)) {
        return new Item(name, value, level, food, horse);
    }
    this.name = name;
    this.value = value;
    this.level = level;
    this.food = food;
    this.horse = horse;
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

    isHorse : function() {
        return this.horse;
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

function getItemIsName(name) {
    for (var i = 0; i < item.length; i++) {
        if (item[i].getName() == name) {
            return item[i];
        }
    }
    return item[0];
}

var item = new Array();
item.push(new Item("なし", -1, -1, false, false));
item.push(new Item("ネクタル", 200, 5, false, false));
item.push(new Item("帝国", 0, 5, false, false));

item.push(new Item("えんまく", -1, 1, false, false));
item.push(new Item("えんまく", -1, 2, false, false));
item.push(new Item("えんまく", -1, 3, false, false));
item.push(new Item("えんまく", -1, 4, false, false));

item.push(new Item("馬", -1, 1, false, true));
item.push(new Item("馬", -1, 2, false, true));
item.push(new Item("馬", -1, 3, false, true));
item.push(new Item("馬", -1, 4, false, true));

item.push(new Item("木の枝", 0, 1, false, false));
item.push(new Item("シャベル", 0, 1, false, false));
item.push(new Item("パン", 100, 1, true, false));
item.push(new Item("イモ", 70, 1, true, false));
item.push(new Item("魚", 120, 1, true, false));
item.push(new Item("肉", 150, 1, true, false));
item.push(new Item("イス", 0, 1, false, false));
item.push(new Item("テーブル", 0, 1, false, false));
item.push(new Item("ナベ", 0, 1, false, false));
item.push(new Item("笛", 0, 1, false, false));
item.push(new Item("寝袋", 50, 1, true, false));
item.push(new Item("ボロイ弓", 3, 1, false, false));
item.push(new Item("ボロイ槍", 5, 1, false, false));
item.push(new Item("ボロイ剣", 4, 1, false, false));
item.push(new Item("ボロイ鎧", 20, 1, false, false));
item.push(new Item("ボロイ兜", 10, 1, false, false));
item.push(new Item("インサイト", 5, 1, false, false));

item.push(new Item("たいこ", 0, 2, false, false));
item.push(new Item("弁当", 100, 2, true, false));
item.push(new Item("おいしい魚", 150, 2, true, false));
item.push(new Item("おいしい肉", 200, 2, true, false));
item.push(new Item("上級寝袋", 100, 2, true, false));
item.push(new Item("タンス", 0, 2, false, false));
item.push(new Item("牛", 0, 2, false, false));
item.push(new Item("豚", 0, 2, false, false));
item.push(new Item("鍵開け道具", 0, 2, false, false));
item.push(new Item("フツウノ弓", 6, 2, false, false));
item.push(new Item("フツウノ槍", 10, 2, false, false));
item.push(new Item("フツウノ剣", 8, 2, false, false));
item.push(new Item("フツウノ鎧", 50, 2, false, false));
item.push(new Item("フツウノ兜", 30, 2, false, false));
item.push(new Item("メガインサイト", 10, 2, false, false));

item.push(new Item("バイオリン", 0, 3, false, false));
item.push(new Item("家", 200, 3, true, false));
item.push(new Item("すごい魚", 250, 3, true, false));
item.push(new Item("すごい肉", 300, 3, true, false));
item.push(new Item("食事", 220, 3, true, false));
item.push(new Item("鉱山", 0, 3, false, false));
item.push(new Item("牧場", 0, 3, false, false));
item.push(new Item("農場", 0, 3, false, false));
item.push(new Item("漁場", 0, 3, false, false));
item.push(new Item("森の地図", 0, 3, false, false));
item.push(new Item("ツヨイ弓", 20, 3, false, false));
item.push(new Item("ツヨイ槍", 30, 3, false, false));
item.push(new Item("ツヨイ剣", 25, 3, false, false));
item.push(new Item("ツヨイ鎧", 100, 3, false, false));
item.push(new Item("ツヨイ兜", 70, 3, false, false));
item.push(new Item("ギガインサイト", 20, 3, false, false));

item.push(new Item("ピアノ", 0, 4, false, false));
item.push(new Item("城", 400, 4, true, false));
item.push(new Item("伝説の魚", 450, 4, true, false));
item.push(new Item("伝説の肉", 500, 4, true, false));
item.push(new Item("宝石", 0, 4, false, false));
item.push(new Item("町", 0, 4, false, false));
item.push(new Item("商会", 0, 4, false, false));
item.push(new Item("精霊", 0, 4, false, false));
item.push(new Item("デンセツノ弓", 30, 4, false, false));
item.push(new Item("デンセツノ槍", 50, 4, false, false));
item.push(new Item("デンセツノ剣", 40, 4, false, false));
item.push(new Item("デンセツノ鎧", 200, 4, false, false));
item.push(new Item("デンセツノ兜", 150, 4, false, false));
item.push(new Item("テラインサイト", 30, 4, false, false));


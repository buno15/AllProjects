var Unit = function(name, img, level, animal) {
    if (!(this instanceof Unit)) {
        return new Unit(name, img, level);
    }
    this.name = name;
    this.img = img;
    this.level = level;
    this.animal = animal;

    this.init();
};

Unit.prototype = {
    init : function(noitem) {
        this.item = this.setItem(this.level, noitem);
        this.hp = this.setHP(this.level);
        this.power = this.setPower(this.level);
        this.brain = this.setBrain(this.level);
    },

    setItem : function(level, noitem) {//noitem=自分アイテムとの重複禁止
        while (true) {
            var random = Math.round(Math.random() * (item.length - 1));
            if (item[random].getLevel() == level && item[random].getName() != noitem) {
                return item[random];
            }
        }
    },

    setItemIsName : function(name) {
        for (var i = 0; i < item.length; i++) {
            if (item[i].getName() == name) {
                this.item = item[i];
                return;
            }
        }
        this.item = item[0];
    },

    setHP : function(level) {//HP初期化
        switch(level) {
        case 1:
            return Math.round(Math.random() * 20) + 30;
            break;
        case 2:
            return Math.round(Math.random() * 50) + 100;
            break;
        case 3:
            return Math.round(Math.random() * 50) + 175;
            break;
        case 4:
            return Math.round(Math.random() * 100) + 200;
            break;
        case 5:
            return 10;
            break;
        }
    },

    setPower : function(level) {
        switch(level) {
        case 1:
            return Math.round(Math.random() * 20) + 15;
            break;
        case 2:
            return Math.round(Math.random() * 25) + 30;
            break;
        case 3:
            return Math.round(Math.random() * 25) + 65;
            break;
        case 4:
            return Math.round(Math.random() * 25) + 90;
            break;
        case 5:
            return 30;
            break;
        }
    },
    setBrain : function(level) {
        switch(level) {
        case 1:
            return Math.round(Math.random() * 20) + 15;
            break;
        case 2:
            return Math.round(Math.random() * 25) + 40;
            break;
        case 3:
            return Math.round(Math.random() * 25) + 65;
            break;
        case 4:
            return Math.round(Math.random() * 25) + 90;
            break;
        case 5:
            return 200;
            break;
        }
    },

    addDamage : function(damage) {
        this.hp -= damage;
    },

    getName : function() {
        return this.name;
    },
    getImg : function() {
        return this.img;
    },
    getLevel : function() {
        return this.level;
    },
    getItem : function() {
        return this.item;
    },
    getHP : function() {
        return this.hp;
    },
    getPower : function() {
        return this.power;
    },

    getBrain : function() {
        return this.brain;
    },

    isAnimal : function() {
        return this.animal;
    }
};

var unit = new Array();
unit.push(new Unit("追い剥ぎ", "oihagi.png", 1, false));
unit.push(new Unit("酔っぱらい", "yopparai.png", 1, false));
unit.push(new Unit("異国の兵士", "heisi.png", 1, false));
unit.push(new Unit("クマ", "kuma.png", 1, true));

unit.push(new Unit("ゴブリン", "goburinn.png", 2, false));
unit.push(new Unit("オーガ", "ooga.png", 2, true));
unit.push(new Unit("ならず者のボス", "naraboss.png", 2, false));
unit.push(new Unit("バイス将軍", "vice.png", 2, false));

unit.push(new Unit("キマイラ", "kimaira.png", 3, true));
unit.push(new Unit("ミノタウロス", "minotaurosu.png", 3, true));
unit.push(new Unit("サイクロプス", "saikuropusu.png", 3, true));
unit.push(new Unit("吸血鬼", "kyuuketuki.png", 3, false));

unit.push(new Unit("ヒュドラー", "hyudora.png", 4, true));
unit.push(new Unit("ヨルムンガンド", "yorumunnganndo.png", 4, true));
unit.push(new Unit("ベヒーモス", "behimosu.png", 4, true));
unit.push(new Unit("ドラゴン", "ribaiasann.png", 4, true));

unit.push(new Unit("恐怖の大王", "daiou.png", 5, true));

function getEnemy(level, noitem) {
    var random = Math.round(Math.random() * 3);
    switch(level) {//相手の設定の仕方むりくりしてる
    case 1:
        unit[random].init(noitem);
        return unit[random];
    case 2:
        random += 4;
        unit[random].init(noitem);
        return unit[random];
    case 3:
        random += 8;
        unit[random].init(noitem);
        return unit[random];
    case 4:
        random += 12;
        unit[random].init(noitem);
        return unit[random];
    }
}

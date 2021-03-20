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
    init : function() {
        this.item = this.setItem(this.level);
        this.hp = this.setHP(this.level);
        this.power = this.setPower(this.level);
    },

    setItem : function(level) {
        if (level == 1) {
            return item[1];
        }
    },

    setHP : function(level) {//HP初期化
        switch(level) {
        case 1:
            return Math.round(Math.random() * 15) + 10;
            break;
        }
    },

    setPower : function(level) {
        switch(level) {
        case 1:
            return Math.round(Math.random() * 2) + 1;
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
    isAnimal : function() {
        return this.animal;
    }
};

var unit = new Array();
unit.push(new Unit("追い剥ぎ", "oihagi.png", 1, false));
unit.push(new Unit("酔っぱらい", "yopparai.png", 1, false));
unit.push(new Unit("異国の兵士", "heisi.png", 1, false));
unit.push(new Unit("クマ", "kuma.png", 1, true));

function getEnemy(level) {
    var random = Math.round(Math.random() * 3);
    switch(level) {
    case 1:
        unit[random].init();
        return unit[random];
        break;
    }
}

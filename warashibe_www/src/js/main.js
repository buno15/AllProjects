function init() {
    item_have1 = item[6];
    item_have2 = item[10];
    load();
};

init();

var app = new Vue({
    el : "#app",
    data : {
        status : 101,
        image_src : "res/img/sougenn.png",
        left : "地平線に向かって走り続ける",
        right : "暗雲に向かって走り続ける",
        name_text : "ならず者のボス",
        console_text : "どこへ行こう？",
        damage_text : damage,
        hp_text : hp,
        power_text : power,
        brain_text : brain,
        item_text1 : item_have1.getName(),
        item_text2 : item_have2.getName(),

        Disabled : false,
        flag_key1 : false,
        flag_key2 : false,
    },

    mounted : function() {
        window.addEventListener('keydown', this.keyActionDown, false);
        window.addEventListener('keyup', this.keyActionUp, false);
    },

    destroyed : function() {
        window.removeEventListener('keydown', this.keyActionDown, false);
        window.removeEventListener('keydown', this.keyActionUp, false);
    },

    methods : {
        keyActionDown : function(event) {
            if (!this.flag_key1) {
                this.flag_key1 = true;
                this.flag_key2 = true;
                console.log(event.keyCode);
                if (event.keyCode === 39) {
                    app.next('right');
                } else if (event.keyCode === 37) {
                    app.next('left');
                } else if (event.keyCode === 38) {
                    app.next('item1');
                } else if (event.keyCode === 40) {
                    app.next('item2');
                }
            }
        },

        keyActionUp : function(event) {
            if (this.flag_key) {

            }
        },

        reload : function() {
            this.damage_text = damage;
            this.hp_text = hp;
            this.power_text = power;
            this.brain_text = brain;
            this.item_text1 = item_have1.getName();
            this.item_text2 = item_have2.getName();
        },

        buttonDisabled : function() {
            this.left = "";
            this.right = "";
            this.Disabled = true;
            this.flag_key1 = true;
        },

        buttonEnabled : function() {
            if (this.flag_key2) {
                this.Disabled = false;
                this.flag_key1 = false;
                console.log("ffffffffffffffff");
            }
        },

        readyFlag : function() {
            this.flag_key2 = true;
        },

        setImageSrc : function(img) {
            this.image_src = "res/img/" + img;
        },

        setTime : function(button, time) {
            setTimeout(this.next, time, button);
            setTimeout(this.readyFlag, time);
        },

        next : function(button) {
            var enemy;
            //敵unit Instance
            var itemID;
            //1=item1 2=item2 3=both

            this.name_text = "---";

            switch(this.status) {
            case 1:
                //移動
                this.setImageSrc("sougenn.png");
                this.left = "地平線に向かって走り続ける";
                this.right = "暗雲に向かって走り続ける";
                this.console_text = "どこへ行こう？";
                this.status = 101;
                break;
            case 101:
                //戦闘
                level = 1;
                this.enemy = getEnemy(level);
                this.setImageSrc(this.enemy.getImg() + "");
                this.console_text = this.enemy.getName() + "が現れた";
                this.left = "攻撃";
                this.right = "知の一撃";
                this.status = 111;
                break;
            case 102:
                this.console_text = "どうする？";
                this.left = "攻撃";
                this.right = "知の一撃";
                this.status = 111;
                break;
            case 111:
                if (button == "item1" || button == "item2") {
                    if (button == "item1") {
                        if (!item_have1.isNone()) {
                            this.console_text = item_have1.getName() + "を差し出して降参しますか？";
                            this.itemID = 1;
                        } else {
                            this.console_text = "今は使えない";
                            this.status = 102;
                            this.buttonDisabled();
                            this.setTime("", 1000);
                            return;
                        }
                    } else {
                        if (!item_have2.isNone()) {
                            this.console_text = item_have2.getName() + "を差し出して降参しますか？";
                            this.itemID = 2;
                        } else {
                            this.console_text = "今は使えない";
                            this.status = 102;
                            this.buttonDisabled();
                            this.setTime("", 1000);
                            return;
                        }
                    }
                    this.left = "はい";
                    this.right = "いいえ";
                    this.status = 112;
                } else if (button == "left" || button == "right") {
                    this.status = 131;
                    this.next(button);
                }
                break;
            case 112:
                if (button == "left") {
                    this.status = 113;
                    this.next(button);
                    return;
                } else if (button == "right") {
                    this.status = 102;
                    this.next(button);
                    return;
                }
                break;
            case 113:
                this.name_text = this.enemy.getName();
                var flag = false;
                if (item_have1.getName() == "なし" || item_have2.getName() == "なし") {
                    flag = true;
                }
                if (getYesNo() || flag) {
                    this.console_text = "「よかろう！見逃してやる」";
                    this.status = 1;
                } else {
                    this.console_text = "「こんなもので命乞いとは笑わせてくれる！」";
                    this.status = 102;
                }
                if (this.itemID == 1) {
                    itemDrop(1);
                } else if (this.itemID == 2) {
                    itemDrop(2);
                }
                this.buttonDisabled();
                this.flag_key2 = false;
                this.setTime("", 1500);
                return;
            case 131:
                this.buttonDisabled();
                if (button == "left") {

                } else if (button == "right") {

                }
                break;
            case 3:
                //交換
                break;
            }
            console.log(this.status);
            this.reload();

            setTimeout(this.buttonEnabled, 250);
        },
    },
});


function init() {
    item_have1 = item[6];
    item_have2 = item[10];
    load();
};

init();

var app = new Vue({
    el : "#app",
    data : {
        status : 1,
        image_src : "res/img/sougenn.png",
        left : "地平線に向かって走り続ける",
        right : "暗雲に向かって走り続ける",
        console_text : "どこへ行こう？",
        damage_text : damage,
        hp_text : hp,
        power_text : power,
        brain_text : brain,
        item_text1 : item_have1.getName(),
        item_text2 : item_have2.getName(),

        Disabled : false,
    },
    methods : {
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
        },

        buttonEnabled : function() {
            this.Disabled = false;
        },

        next : function(button) {
            var enemy;
            //敵unit Instance
            var itemID;
            //1=item1 2=item2 3=both

            if (this.status == 1) {
                this.status = 101;
            }

            this.buttonEnabled();

            switch(this.status) {
            case 1:
                //移動
                break;
            case 101:
                //戦闘
                level = 1;
                this.enemy = getEnemy(level);
                this.image_src = "res/img/" + this.enemy.getImg();
                this.console_text = this.enemy.getName() + "が現れた";
                this.left = "攻撃";
                this.right = "防御 or 突進";
                this.status = 111;
                break;
            case 102:
                this.console_text = "どうする？";
                this.left = "攻撃";
                this.right = "防御 or 突進";
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
                            this.buttonDisabled();
                            this.status = 102;
                            setTimeout(this.next, 1000, "");
                            return;
                        }
                    } else {
                        if (!item_have2.isNone()) {
                            this.console_text = item_have2.getName() + "を差し出して降参しますか？";
                            this.itemID = 2;
                        } else {
                            this.console_text = "今は使えない";
                            this.buttonDisabled();
                            this.status = 102;
                            setTimeout(this.next, 1000, "");
                            return;
                        }
                    }
                    this.left = "はい";
                    this.right = "いいえ";
                    this.status = 112;
                } else if (button == "left" || button == "right") {
                    this.status = 131;
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
                if (false) {

                } else {
                    this.console_text = this.enemy.getName() + "「こんなもので命乞いとは笑わせてくれる！」";
                    this.buttonDisabled();
                    if (this.itemID == 1) {
                        itemDrop(1);
                    } else if (this.itemID == 2) {
                        itemDrop(2);
                    }
                    this.status = 102;
                    setTimeout(this.next, 1500, "");
                }

                break;
            case 105:
                break;

            case 3:
                //交換
                break;
            }
            console.log(this.status);
            this.reload();
        },
    },
});


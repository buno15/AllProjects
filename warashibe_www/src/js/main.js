var enemy;
//敵unit Instance
var itemID = -1;
//1=item1 2=item2 3=both

function init() {
    item_have1 = item[11];
    item_have2 = item[14];
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
        name_text : "---",
        console_text : "どこへ行こう？",
        damage_text : damage,
        hp_text : hp,
        power_text : power,
        brain_text : brain,
        item_text1 : item_have1.getName(),
        item_text2 : item_have2.getName(),

        Disabled : false,
        flag_key1 : false,
        flag_key2 : false,//animationの後にボタンをenableする補助flag
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
                this.flag_key2 = false;
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

        reload : function() {
            if (getAdd(item_have1.getName()) == 1) {
                addHP1 = item_have1.getValue();
                addPower1 = 0;
                addBrain1 = 0;
            } else if (getAdd(item_have1.getName()) == 2) {
                addPower1 = item_have1.getValue();
                addHP1 = 0;
                addBrain1 = 0;
            } else if (getAdd(item_have1.getName()) == 3) {
                addBrain1 = item_have1.getValue();
                addHP1 = 0;
                addPower1 = 0;
            } else {
                addHP1 = 0;
                addPower1 = 0;
                addBrain1 = 0;
            }
            if (getAdd(item_have2.getName()) == 1) {
                addHP2 = item_have2.getValue();
                addPower2 = 0;
                addBrain2 = 0;
            } else if (getAdd(item_have2.getName()) == 2) {
                addPower2 = item_have2.getValue();
                addHP2 = 0;
                addBrain2 = 0;
            } else if (getAdd(item_have2.getName()) == 3) {
                addBrain2 = item_have2.getValue();
                addHP2 = 0;
                addPower2 = 0;
            } else {
                addHP2 = 0;
                addPower2 = 0;
                addBrain2 = 0;
            }

            console.log(addHP1 + " " + addHP2);
            if (addHP1 != 0 || addHP2 != 0) {
                this.hp_text = hp + "+" + (addHP1 + addHP2);
            } else {
                this.hp_text = hp;
            }
            if (addPower1 != 0 || addPower2 != 0) {
                this.power_text = power + "+" + (addPower1 + addPower2);
            } else {
                this.power_text = power;
            }
            if (addBrain1 != 0 || addBrain2 != 0) {
                this.brain_text = brain + "+" + (addBrain1 + addBrain2);
            } else {
                this.brain_text = brain;
            }
            this.damage_text = damage;
            this.item_text1 = item_have1.getName();
            this.item_text2 = item_have2.getName();
        },

        buttonDisabled : function() {
            this.left = "";
            this.right = "";
            this.Disabled = true;
            this.flag_key1 = true;
            this.flag_key2 = true;
        },

        buttonEnabled : function() {
            if (!this.flag_key2) {
                this.Disabled = false;
                this.flag_key1 = false;
            }
        },

        readyFlag : function() {//button key多重禁止用のflag
            this.flag_key2 = false;
        },

        setImageSrc : function(img) {//画像表示
            this.image_src = "res/img/" + img;
        },

        setTimer : function(button, time) {//場面転換時の画面遷移用のタイマー buttonをenabledにしたい時呼び出す
            setTimeout(this.next, time, button);
            setTimeout(this.readyFlag, time);
        },

        normalAttack : function(i) {//攻撃アニメーション
            switch(i) {
            case 0:
                //通常攻撃始
                this.enemy.addDamage(power + addPower1 + addPower2);
                this.setImageSrc("attack.png");
                if (this.enemy.getHP() <= 0) {
                    setTimeout(this.normalAttack, 125, 11);
                } else {
                    setTimeout(this.normalAttack, 125, 1);
                }
                break;
            case 1:
                //相手の攻撃始
                this.setImageSrc(this.enemy.getImg());
                this.console_text = this.enemy.getName() + "の攻撃！\n" + this.enemy.getPower() + "ダメージ。";
                setTimeout(this.normalAttack, 800, 2);
                break;
            case 2:
                damage -= this.enemy.getPower();
                if (damage < 0) {
                    damage = 0;
                }
                this.setImageSrc("damage.png");
                setTimeout(this.normalAttack, 125, 3);
                break;
            case 3:
                console.log(this.enemy.getHP() + " " + damage);
                this.setImageSrc(this.enemy.getImg());
                if (damage <= 0) {
                    setTimeout(this.normalAttack, 100, 12);
                } else
                    setTimeout(this.normalAttack, 100, 4);
                break;
            case 4:
                //通常攻撃終
                this.status = 102;
                this.setTimer("", 100);
                break;
            case 11:
                //相手死亡
                this.status = 131;
                this.setTimer("", 100);
                break;
            case 12:
                //自分死亡
                this.status = -1;
                setTimeout(this.next, 100, "");
                break;
            }
        },

        brainAttack : function() {

        },

        myAttack : function() {

        },

        enemyAttack : function() {

        },

        next : function(button) {
            this.name_text = "---";

            switch(this.status) {//this.next()の後はreturn
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
                //降参
                if (button == "item1" || button == "item2") {
                    if (button == "item1") {
                        if (!item_have1.isNone()) {
                            if (this.enemy.isAnimal())
                                this.console_text = item_have1.getName() + "を囮に逃げますか？";
                            else
                                this.console_text = item_have1.getName() + "を差し出して降参しますか？";
                            itemID = 1;
                        } else {
                            this.console_text = "今は使えない";
                            this.status = 102;
                            this.buttonDisabled();
                            this.setTimer("item1", 1000);
                            return;
                        }
                    } else {
                        if (!item_have2.isNone()) {
                            if (this.enemy.isAnimal())
                                this.console_text = item_have2.getName() + "を囮に逃げますか？";
                            else
                                this.console_text = item_have2.getName() + "を差し出して降参しますか？";
                            itemID = 2;
                        } else {
                            this.console_text = "今は使えない";
                            this.status = 102;
                            this.buttonDisabled();
                            this.setTimer("item2", 1000);
                            return;
                        }
                    }
                    this.left = "はい";
                    this.right = "いいえ";
                    this.status = 112;
                } else if (button == "left" || button == "right") {
                    this.status = 121;
                    this.next(button);
                    return;
                }
                break;
            case 112:
                if (button == "left") {
                    this.status = 113;
                    this.next(button);
                    return;
                } else if (button == "right") {
                    this.status = 102;
                    this.next("");
                    return;
                }
                break;
            case 113:
                var flag = false;
                if (this.enemy.isAnimal()) {
                    if ((itemID == 1 && item_have1.isFood()) || (itemID == 2 && item_have2.isFood())) {
                        flag = true;
                    }
                    if (flag) {
                        this.console_text = "逃げ切れた！";
                        this.status = 1;
                    } else {
                        this.console_text = "逃してくれそうにない・・・";
                        this.status = 102;
                    }
                } else {
                    if (item_have1.getName() == "なし" || item_have2.getName() == "なし") {
                        flag = true;
                    }
                    this.name_text = this.enemy.getName();
                    if (getYesNo() || flag) {
                        this.console_text = "「よかろう！見逃してやる」";
                        this.status = 1;
                    } else {
                        this.console_text = "「こんなもので命乞いとは笑わせてくれる！」";
                        this.status = 102;
                    }
                }
                if (itemID == 1) {
                    itemDrop(1);
                } else if (itemID == 2) {
                    itemDrop(2);
                }
                this.buttonDisabled();
                this.setTimer("", 1500);
                return;
            case 121:
                //攻撃
                this.buttonDisabled();
                if (button == "left") {
                    this.console_text = "攻撃！\n" + this.enemy.getName() + "に" + power + "ダメージ。";
                    setTimeout(this.normalAttack, 800, 0);
                    return;
                } else if (button == "right") {

                }
                break;
            case 131:
                //相手倒した
                this.console_text = this.enemy.getName() + "を倒した。";
                this.left = "旅を続ける";
                this.right = "身ぐるみ剥ぐ";
                this.status = 132;
                break;
            case 132:
                if (button == "left") {
                    this.status = 1;
                    this.next("");
                    return;
                } else if (button == "right") {

                }
                break;
            case 3:
                //交換
                break;
            case -1:
                //死亡
                this.console_text = "あなたは死んだ。";
                break;
            }
            console.log(this.status);
            this.reload();

            setTimeout(this.buttonEnabled, 250);
        },
    },
});


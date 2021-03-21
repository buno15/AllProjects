var enemy;
//敵unit Instance
var itemID = -1;
//1=item1 2=item2 3=both
var workID = -1;

var move_random;

var event_random = new Array(101);

var sound_attack;
var sound_brainattack;
var sound_damage;
var sound_recovery;

function init() {
    item_have1 = item[1];
    item_have2 = item[2];
    load();
    sound_attack = new Audio('res/raw/attack.mp3');
    sound_brainattack = new Audio('res/raw/brainattack.mp3');
    sound_damage = new Audio('res/raw/damage.mp3');
    sound_recovery = new Audio('res/raw/recovery.mp3');
};

init();

var app = new Vue({
    el : "#app",
    data : {
        status : 1,

        image_src : "res/img/tiheisenn.png",
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
            this.reloadAdd();
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

        reloadAdd : function() {
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

        /**************************************攻撃アニメーション**************************************************/

        normalAttack : function(i) {
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
                sound_damage.play();
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

        brainAttack : function(i) {
            switch(i) {
            case 0:
                //知の一撃始
                var random = Math.round(Math.random() * ((brain + addBrain1 + addBrain2) + this.enemy.getBrain() - 1)) + 1;
                if (random <= (brain + addBrain1 + addBrain2)) {
                    sound_brainattack.play();
                    this.console_text = "知の一撃！！！\n" + this.enemy.getName() + "に" + (power + addPower1 + addPower2 + brain + addBrain1 + addBrain2) + "ダメージ。\n\n";
                    this.enemy.addDamage(power + addPower1 + addPower2 + brain + addBrain1 + addBrain2);
                    setTimeout(this.brainAttack, 500, 1);
                } else {
                    sound_damage.play();
                    this.console_text = this.enemy.getName() + "の攻撃！\n" + this.enemy.getPower() + "ダメージ。";
                    setTimeout(this.brainAttack, 800, 11);
                }
                break;
            case 1:
                this.setImageSrc("attackbrain.png");
                if (this.enemy.getHP() <= 0) {
                    setTimeout(this.brainAttack, 125, 21);
                } else {
                    setTimeout(this.brainAttack, 125, 2);
                }
                break;
            case 2:
                this.setImageSrc(this.enemy.getImg());
                this.console_text += this.enemy.getName() + "はひるんだ。";
                setTimeout(this.brainAttack, 1500, 14);
                break;
            case 11:
                damage -= this.enemy.getPower();
                if (damage < 0) {
                    damage = 0;
                }
                this.setImageSrc("damage.png");
                setTimeout(this.brainAttack, 125, 12);
                break;
            case 12:
                this.setImageSrc(this.enemy.getImg());
                if (damage <= 0) {
                    setTimeout(this.brainAttack, 125, 22);
                } else
                    setTimeout(this.brainAttack, 125, 13);
                break;
            case 13:
                this.console_text = "あなたの集中は切れた。";
                setTimeout(this.brainAttack, 1250, 14);
                break;
            case 14:
                //知の一撃終
                this.status = 102;
                this.setTimer("", 100);
                break;
            case 21:
                //相手死亡
                this.status = 131;
                this.setImageSrc("attack.png");
                this.setTimer("", 100);
                break;
            case 22:
                //自分死亡
                this.status = -1;
                setTimeout(this.next, 100, "");
                break;
            }
        },

        setMoveEvent : function(i) {
            switch(i) {
            case 0:
                this.setImageSrc("matinaka.png");
                this.left = "路地裏へ";
                this.right = "町を出る";
                this.console_text = "町にやって来た。";
                break;
            case 1:
                this.setImageSrc("sougenn.png");
                this.left = "駆け抜ける";
                this.right = "ゆっくりする";
                this.console_text = "見渡すかぎり草原だ。";
                break;
            case 2:
                this.setImageSrc("tiheisenn.png");
                this.left = "地平線に向かって走り続ける";
                this.right = "暗雲に向かって走り続ける";
                this.console_text = "どこへ行こう？";
                break;
            case 3:
                this.setImageSrc("umi.png");
                if (item_have1.getName() == "なし" && item_have2.getName() == "なし") {
                    this.console.text = "ネクタルという伝説の薬があるらしい。";
                    this.left = "伝説さ";
                    this.right = "噂さ";
                } else {
                    if (item_have1.getName() != "なし") {
                        this.console_text = item_have1.getName() + "は私の宝だ。";
                    } else {
                        this.console_text = item_have2.getName() + "は私の宝だ。";
                    }
                    this.left = "いつまでも一緒";
                    this.right = "別のアイテムを探す";
                }
                break;
            case 4:
                this.setImageSrc("wakaremiti.png");
                this.left = "左";
                this.right = "右";
                this.console_text = "分かれ道だ。";
                break;
            case 5:
                this.setImageSrc("yama.png");
                this.left = "山へ行く";
                this.right = "引き返す";
                this.console_text = "山が見える。";
                break;
            }
        },

        setNextEvent : function() {

        },

        nextIsMove : function() {
            this.status = 1;
            this.next("");
        },

        nextIsMoveAnime : function() {
            this.buttonDisabled();
            this.status = 1;
            this.setTimer("", 2000);
        },

        next : function(button) {
            this.name_text = "---";

            switch(this.status) {//this.next()の後はreturn
            case 1:
                /********************************************************移動***********************************************************/
                setLevel();
                //自分のレベルを設定
                move_random = Math.round(Math.random() * 5);
                this.setMoveEvent(move_random);
                this.status = 2;
                this.next("");
                break;
            case 2:
                this.setMoveEvent(move_random);

                if (button == "item1" || button == "item2") {
                    if (button == "item1") {
                        itemID = 1;
                    } else if (button == "item2") {
                        itemID = 2;
                    }
                    this.status = 3;
                    this.next(button);
                    return;
                } else if (button == "left" || button == "right") {
                    this.status = 301;
                    this.next(button);
                    return;
                }
                break;
            case 3:
                if (itemID == 1) {
                    if (item_have1.isFood() && damage < hp) {
                        this.console_text = item_have1.getName() + "を使用しますか？";
                        this.status = 4;
                        this.left = "はい";
                        this.right = "いいえ";
                    } else {
                        this.console_text = "今は使えない。";
                        this.buttonDisabled();
                        this.status = 2;
                        this.setTimer("", 1000);
                        return;
                    }
                } else if (itemID == 2) {
                    if (item_have2.isFood() && damage < hp) {
                        this.console_text = item_have2.getName() + "を使用しますか？";
                        this.status = 4;
                        this.left = "はい";
                        this.right = "いいえ";
                    } else {
                        this.console_text = "今は使えない。";
                        this.buttonDisabled();
                        this.status = 2;
                        this.setTimer("", 1000);
                        return;
                    }
                }
                break;
            case 4:
                if (button == "left") {
                    if (itemID == 1) {
                        this.console_text = item_have1.getName() + "を使用した。\n体力が" + item_have1.getValue() + "回復した。";
                        damage += item_have1.getValue();
                        if (damage > hp) {
                            damage = hp;
                        }
                        if (item_have1.getName() != "寝袋" && item_have1.getName() != "家" && item_have1.getName() != "城") {
                            itemDrop(1);
                        }
                    } else {
                        this.console_text = item_have2.getName() + "を使用した。\n体力が" + item_have2.getValue() + "回復した。";
                        damage += item_have2.getValue();
                        if (damage > hp) {
                            damage = hp;
                        }
                        if (item_have2.getName() != "寝袋" && item_have2.getName() != "家" && item_have2.getName() != "城") {
                            itemDrop(2);
                        }
                    }
                    this.buttonDisabled();
                    this.status = 2;
                    this.setTimer("", 2000);
                    return;
                } else if (button == "right") {
                    this.status = 2;
                    this.next("");
                    return;
                }
                break;
            case 101:
                /********************************************************戦闘***********************************************************/
                level = 1;
                this.enemy = getEnemy(level, "");
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
                this.buttonDisabled();

                var flag = false;
                if (this.enemy.isAnimal()) {
                    if ((itemID == 1 && item_have1.isFood()) || (itemID == 2 && item_have2.isFood())) {
                        flag = true;
                    }
                    if (itemID == 1) {
                        itemDrop(1);
                    } else if (itemID == 2) {
                        itemDrop(2);
                    }
                    if (flag) {
                        this.console_text = "逃げ切れた！";
                        this.nextIsMoveAnime();
                    } else {
                        this.console_text = "逃してくれそうにない・・・";
                        setTimeout(this.normalAttack, 800, 1);
                    }
                } else {
                    if (item_have1.getName() == "なし" || item_have2.getName() == "なし") {
                        flag = true;
                    }
                    if (itemID == 1) {
                        itemDrop(1);
                    } else if (itemID == 2) {
                        itemDrop(2);
                    }
                    this.name_text = this.enemy.getName();
                    if (getYesNo() || flag) {
                        this.console_text = "「よかろう！見逃してやる」";
                        this.nextIsMoveAnime();
                    } else {
                        this.console_text = "「こんなもので命乞いとは笑わせてくれる！」";
                        setTimeout(this.normalAttack, 800, 1);
                    }
                }
                return;
            case 121:
                //攻撃
                this.buttonDisabled();
                if (button == "left") {
                    sound_attack.play();
                    this.console_text = "攻撃！\n" + this.enemy.getName() + "に" + (power + addPower1 + addPower2) + "ダメージ。\n\n";
                    setTimeout(this.normalAttack, 500, 0);
                    return;
                } else if (button == "right") {
                    this.console_text = "あなたは集中している。";
                    setTimeout(this.brainAttack, 2000, 0);
                    return;
                }
                break;
            case 131:
                //相手倒した
                this.console_text += this.enemy.getName() + "を倒した。";
                this.left = "旅を続ける";
                this.right = "身ぐるみ剥ぐ";
                this.status = 132;
                break;
            case 132:
                if (button == "left") {
                    this.nextIsMove();
                    return;
                } else if (button == "right") {
                    var random = Math.round(Math.random() * 9);
                    if (random < 8) {
                        this.status = 133;
                        this.next("");
                        return;
                    } else {
                        this.status = 141;
                        this.next("");
                        return;
                    }
                }
                break;
            case 133:
                this.console_text = this.enemy.getName() + "は" + this.enemy.getItem().getName() + "を持っていた。\n";
                if (item_have1.getName() == "なし" || item_have2.getName() == "なし") {
                    this.buttonDisabled();
                    this.status = 1;
                    this.console_text += this.enemy.getItem().getName() + "を手に入れた。";
                    if (item_have1.getName() == "なし") {
                        item_have1 = this.enemy.getItem();
                    } else if (item_have2.getName() == "なし") {
                        item_have2 = this.enemy.getItem();
                    }
                    this.setTimer("", 2000);
                    return;
                } else {
                    this.console_text += "手持ちと交換しますか？";
                    this.status = 134;
                    this.left = "はい";
                    this.right = "いいえ";
                }
                break;
            case 134:
                if (button == "left") {
                    this.console_text = this.enemy.getItem().getName() + "と何を交換しますか？";
                    this.status = 135;
                    this.left = item_have1.getName();
                    this.right = item_have2.getName();
                } else if (button == "right") {
                    this.nextIsMove();
                }
                break;
            case 135:
                if (button == "left" || button == "right") {
                    if (button == "left") {
                        item_have1 = this.enemy.getItem();
                    } else if (button == "right") {
                        item_have2 = this.enemy.getItem();
                    }
                    this.buttonDisabled();
                    this.status = 1;
                    this.console_text = this.enemy.getItem().getName() + "を手に入れた。";
                    this.setTimer("", 2000);
                }
                break;
            case 141:
                //息を吹き返す
                this.buttonDisabled();
                this.console_text = this.enemy.getName() + "が息を吹き返した！";
                this.setImageSrc(this.enemy.getImg());
                setTimeout(this.normalAttack, 800, 1);
                return;
            case 201:
                /********************************************************交換（アイテムは必ず持っていると仮定）***********************************************************/
                var random = Math.round(Math.random() * 1);
                if (random == 0) {
                    itemID = 1;
                    if (item_have1.getName() == "なし") {
                        itemID = 2;
                    }
                } else {
                    itemID = 2;
                    if (item_have2.getName() == "なし") {
                        itemID = 1;
                    }
                }
                this.enemy = getEnemy(level, (itemID == 1 ? item_have1.getName() : item_have2.getName()));
                if (level == 1) {
                    this.setImageSrc("tabibito.png");
                    this.name_text = "旅人";
                    this.left = "はい";
                    this.right = "いいえ";
                    this.console_text = "あなたの持っている" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "と、\n私の" + this.enemy.getItem().getName() + "を交換しませんか？";
                } else if (level == 2) {
                    this.setImageSrc("kodomo.png");
                    this.name_text = "子供";
                    this.left = "あげる";
                    this.right = "やらん";
                    this.console_text = (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "がどうしても必要なんだ。\nおいらの" + this.enemy.getItem().getName() + "と交換しないか？";
                } else if (level == 3) {
                    this.setImageSrc("hugou.png");
                    this.name_text = "富豪";
                    this.left = "いいよ";
                    this.right = "いやだ";
                    this.console_text = (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "が必要なの。\n譲ると申すなら、代わりのものをくれてやるぞ。";
                } else if (level == 4) {
                    this.setImageSrc("king.png");
                    this.name_text = "王様";
                    this.left = "どうぞ";
                    this.right = "ご勘弁を";
                    this.console_text = (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "がわしには必要なのじゃ。\n譲ると申すなら代わりに、\n" + this.enemy.getItem().getName() + "を授けるぞ。";
                }
                this.status = 202;
                break;
            case 202:
                if (button == "left") {
                    if (itemID == 1) {
                        item_have1 = this.enemy.getItem();
                    } else {
                        item_have2 = this.enemy.getItem();
                    }
                    this.buttonDisabled();
                    this.console_text = this.enemy.getItem().getName() + "を手に入れた。";
                    this.status = 1;
                    this.setTimer("", 2000);
                } else if (button == "right") {
                    this.nextIsMove();
                    return;
                }
                break;
            case 301:
                /********************************************************仕事***********************************************************/
                workID = Math.round(Math.random() * 2);
                if (level == 1) {
                    this.left = "いいよ";
                    if (workID == 0) {
                        this.setImageSrc("kouzann.png");
                        this.name_text = "鉱員";
                        this.right = "用事があるから";
                        this.console_text = "人手が足りなくて困っているんだ。\nあんた手伝ってくれないかね？";
                    } else if (workID == 1) {
                        this.setImageSrc("ryousi.png");
                        this.name_text = "猟師";
                        this.right = "そんな時間はない";
                        this.console_text = "狩りに行きたいのだが、\n一人じゃ不安なんだ。\n一緒に来てくれないかね？";
                    } else if (workID == 2) {
                        this.setImageSrc("zakkaya.png");
                        this.name_text = "雑貨屋";
                        this.right = "人見知りだから";
                        this.console_text = "雇っていたのが急に休んじまって、\n店番してくれないかね？";
                    }
                } else if (level == 2) {
                    this.left = "まかせて";
                    if (workID == 0) {
                        this.setImageSrc("sakanaya.png");
                        this.name_text = "魚屋";
                        this.right = "役に立てない";
                        this.console_text = "最近売り上げが悪いんだ。\n呼び込みをやってくれないか？";
                    } else if (workID == 1) {
                        this.setImageSrc("taityou.png");
                        this.name_text = "隊長";
                        this.right = "私は弱いです";
                        this.console_text = "君はなかなか強そうだな。\n蛮族を退治するのだが、\n参加するかね？";
                    } else if (workID == 2) {
                        this.setImageSrc("sakabanosyuzinn.png");
                        this.name_text = "酒場の主人";
                        this.right = "人見知りだから";
                        this.console_text = "人手が足りなくて困っているんだ。\nあんた店を手伝ってくれないかね？";
                    }
                } else if (level == 3) {
                    this.left = "引き受けた";
                    if (workID == 0) {
                        this.setImageSrc("yadoyanosyuzinn.png");
                        this.name_text = "一流宿屋";
                        this.right = "お役に立てない";
                        this.console_text = "最近サービスが悪いと言われるんだ。\n監督してくれないかね？";
                    } else if (workID == 1) {
                        this.setImageSrc("syougunn.png");
                        this.name_text = "将軍";
                        this.right = "戦えません";
                        this.console_text = "この度きみに召集がかかった。\n隣国に攻め入るのだ。\nともに行こう！";
                    } else if (workID == 2) {
                        this.setImageSrc("kazinonosyuzinn.png");
                        this.name_text = "カジノの支配人";
                        this.right = "務まりません";
                        this.console_text = "警備が不足していて、\nきみやってくれない？";
                    }
                } else if (level == 4) {
                    this.left = "お任せを";
                    if (workID == 0) {
                        this.console_text = "そなたを近衛隊長に任命する。\n王宮を守るのじゃ！";
                    } else if (workID == 1) {
                        this.console_text = "そなたを将軍に任命する。\n国の平和を守るのじゃ！";
                    } else if (workID == 2) {
                        this.console_text = "そなたを大臣に任命する。\n国の維持に努めるのじゃ！";
                    }
                    this.name_text = "王";
                    this.setImageSrc("gyokuza.png");
                    this.right = "お断りします";
                }
                this.status = 302;
                break;
            case 302:
                if (button == "left") {
                    var random = Math.round(Math.random() * 9);
                    var add;
                    if (level == 1) {
                        add = 1;
                    } else if (level == 2) {
                        add = 2;
                    } else if (level == 3) {
                        add = 3;
                    } else if (level == 4) {
                        add = 5;
                    }
                    if (random < 8 || (damage - (add * 2)) < 1) {
                        if (level == 1) {
                            if (workID == 0) {
                                this.console_text = "あなたは一生懸命働いた。\n体力がついた。";
                            } else if (workID == 1) {
                                this.console_text = "あなたは猛獣と戦った。\n力が上がった。";
                            } else if (workID == 2) {
                                this.console_text = "あなたはしばらく店番をした。\n知力が上がった。";
                            }
                        } else if (level == 2) {
                            if (workID == 0) {
                                this.console_text = "あなたは呼び込みをした。\nおかげで店は繁盛。\n体力がついた。";
                            } else if (workID == 1) {
                                this.console_text = "あなたは蛮族と戦った。\n力が上がった。";
                            } else if (workID == 2) {
                                this.console_text = "あなたはしばらく酒場で働いた。\n知力が上がった。";
                            }
                        } else if (level == 3) {
                            if (workID == 0) {
                                this.console_text = "あなたは監督をした。\nおかげで宿屋は繁盛。\n体力がついた。";
                            } else if (workID == 1) {
                                this.console_text = "あなたは隣国へ向かった。\n力が上がった。";
                            } else if (workID == 2) {
                                this.console_text = "あなたはカジノで警備をした。\n知力が上がった。";

                            }
                        } else if (level == 4) {
                            if (workID == 0) {
                                this.console_text = "あなたは王宮の警備をした。\n体力がついた。";
                            } else if (workID == 1) {
                                this.console_text = "あなたは軍を指揮した。\n力が上がった。";
                            } else if (workID == 2) {
                                this.console_text = "あなたは大臣を務めた。\n知力が上がった。";
                            }
                        }
                        if (workID == 0) {
                            hp += add;
                        } else if (workID == 1) {
                            power += add;
                        } else if (workID == 2) {
                            brain += add;
                        }
                    } else {
                        if (level == 1) {
                            if (workID == 0) {
                                this.name_text = "鉱員";
                            } else if (workID == 1) {
                                this.name_text = "猟師";
                            } else if (workID == 2) {
                                this.name_text = "雑貨屋";
                            }
                        } else if (level == 2) {
                            if (workID == 0) {
                                this.name_text = "魚屋";
                            } else if (workID == 1) {
                                this.name_text = "隊長";
                            } else if (workID == 2) {
                                this.name_text = "酒場の主人";
                            }
                        } else if (level == 3) {
                            if (workID == 0) {
                                this.name_text = "一流宿屋";
                            } else if (workID == 1) {
                                this.name_text = "将軍";
                            } else if (workID == 2) {
                                this.name_text = "カジノの支配人";
                            }
                        } else if (level == 4) {
                            this.name_text = "王";
                        }
                        this.console_text = "つかえない奴め！\nボゴッ　ドスッ　バコッ\n\n" + (add * 2) + "ダメージ。";
                        damage -= (add * 2);
                    }
                    this.nextIsMoveAnime();
                    return;
                } else if (button == "right") {
                    this.nextIsMove();
                    return;
                } else {
                    if (level == 1) {
                        if (workID == 0) {
                            this.name_text = "鉱員";
                        } else if (workID == 1) {
                            this.name_text = "猟師";
                        } else if (workID == 2) {
                            this.name_text = "雑貨屋";
                        }
                    } else if (level == 2) {
                        if (workID == 0) {
                            this.name_text = "魚屋";
                        } else if (workID == 1) {
                            this.name_text = "隊長";
                        } else if (workID == 2) {
                            this.name_text = "酒場の主人";
                        }
                    } else if (level == 3) {
                        if (workID == 0) {
                            this.name_text = "一流宿屋";
                        } else if (workID == 1) {
                            this.name_text = "将軍";
                        } else if (workID == 2) {
                            this.name_text = "カジノの支配人";
                        }
                    } else if (level == 4) {
                        this.name_text = "王";
                    }
                }
                break;
            case 401:
                //宝探し
                break;
            case 501:
                //賭け事
                break;
            case 601:
                //損得交換（アイテムは必ず持ってると仮定）
                break;
            case 701:
                //演奏（楽器は必ず持ってると仮定）
                break;
            case 801:
                //宿屋
                this.setImageSrc("yadoya.png");
                this.name_text = "宿屋";
                this.console_text = "いらっしゃい\n泊まっていくかい？\nあんたの持ってるものを、\n宿代としてもらうよ。";
                this.left = "はい";
                this.right = "いいえ";
                this.status = 802;
                break;
            case 802:
                this.name_text = "宿屋";
                if (button == "left") {
                    sound_recovery.play();
                    var random = Math.round(Math.random() * 1);
                    if (random == 0) {
                        itemID = 1;
                        if (item_have1.getName() == "なし") {
                            itemID = 2;
                        }
                    } else {
                        itemID = 2;
                        if (item_have2.getName() == "なし") {
                            itemID = 1;
                        }
                    }
                    if (itemID == 1) {
                        this.console_text = item_have1.getName() + "をもらうよ。\n\n体力が全回復した。";
                        itemDrop(1);
                    } else {
                        this.console_text = item_have2.getName() + "をもらうよ。\n\n体力が全回復した。";
                        itemDrop(2);
                    }
                    this.reload();
                    damage = (hp + addHP1 + addHP2);
                    this.nextIsMoveAnime();
                    return;
                } else if (button == "right") {
                    this.nextIsMove();
                    return;
                }
                break;
            case 901:
                //シスター
                sound_recovery.play();
                var random = Math.round(Math.random() * 1);
                if (random == 0) {
                    this.name_text = "ご婦人";
                    this.console_text = "あんた疲れているね。\n休んでくといいよ。\n\n体力が回復した。";
                    this.setImageSrc("gohuzinn.png");
                    damage += (hp / 2);
                    if (damage > hp) {
                        damage = hp;
                    }
                } else {
                    this.name_text = "シスター";
                    this.console_text = "傷だらけですね。\n休んでいくと良いでしょう。\n\n体力が全回復した。";
                    this.setImageSrc("sister.png");
                    damage = (hp + addHP1 + addHP2);
                }
                this.left = "ありがとう";
                this.right = "Thank you";
                this.status = 1;
                break;
            case -1:
                //死亡
                this.console_text = "あなたは死んだ。";
                this.setImageSrc("damage.png");
                this.buttonDisabled();
                break;
            }
            console.log(this.status);
            this.reload();

            setTimeout(this.buttonEnabled, 250);
        },
    },
});


var enemy;
//敵unit Instance

var itemID = -1;
//1=item1 2=item2 3=both

var workID = -1;
//1=hp 2=power 3=brain

var page = 1;

var move_random;
//ランダム移動先
var event_random = new Array(101, 201, 301, 401, 501, 601, 701, 801, 901);
var prev_event_random = -1;
//前回のイベント
var scam_random;
//損得確率
var en_count = 0;
//何回Moveしたか

var flag_kengou = false;
var flag_daiou = false;
var flag_sleep = false;

var sound_start;
var sound_attack1;
var sound_attack2;
var sound_brainattack;
var sound_damage;
var sound_recovery;
var sound_item_compare;
var sound_item_get;
var sound_bgm_move;
var sound_bgm_battle;
var sound_bgm_battleboss;
var sound_bgm_event;
var sound_syuutyuu;
var sound_knockdown;
var sound_escape;
var sound_revive;
var sound_scam;
var sound_powerup;
var sound_dead;

function init() {
    n_flag = localStorage.getItem("n_flag");
    if (n_flag == "1") {
        page = 9001;
        hp = Math.round(Math.random() * 20) + 80;
        damage = hp;
        power = Math.round(Math.random() * 8) + 7;
        brain = Math.round(Math.random() * 8) + 7;
        item_have1 = item[11];
        item_have2 = item[0];
        n_flag = "2";
        enemyID = "none";
    } else {
        page = 9003;
        load();
        if (enemyID != "none") {
            page = 101;
        }
    }

    sound_start = new Audio('../../res/raw/start.mp3');
    sound_attack1 = new Audio('../../res/raw/attack_1.mp3');
    sound_attack2 = new Audio('../../res/raw/attack_2.mp3');
    sound_brainattack = new Audio('../../res/raw/brainattack.mp3');
    sound_damage = new Audio('../../res/raw/damage.mp3');
    sound_recovery = new Audio('../../res/raw/recovery.mp3');
    sound_item_compare = new Audio('../../res/raw/item_compare.mp3');
    sound_item_get = new Audio('../../res/raw/item_get.mp3');
    sound_bgm_move = new Audio('../../res/raw/bgm_move.mp3');
    sound_bgm_battle = new Audio('../../res/raw/bgm_battle.mp3');
    sound_bgm_battleboss = new Audio('../../res/raw/bgm_battle_boss.mp3');
    sound_bgm_event = new Audio('../../res/raw/bgm_event.mp3');
    sound_syuutyuu = new Audio('../../res/raw/syuutyuu.mp3');
    sound_knockdown = new Audio('../../res/raw/knock_down.mp3');
    sound_escape = new Audio('../../res/raw/escape.mp3');
    sound_revive = new Audio('../../res/raw/revive.mp3');
    sound_scam = new Audio('../../res/raw/scam.mp3');
    sound_powerup = new Audio('../../res/raw/powerup.mp3');
    sound_dead = new Audio('../../res/raw/dead.mp3');
};

init();

var app = new Vue({
    el : "#app",
    data : {
        image_src : "",
        left : "",
        right : "",
        name_text : "---",
        console_text : "",
        damage_text : damage,
        hp_text : hp,
        power_text : power,
        brain_text : brain,
        item_text1 : item_have1.getName(),
        item_text2 : item_have2.getName(),

        Disabled : false,
        flag_key1 : false,
        flag_key2 : false, //animationの後にボタンをenableする補助flag
    },

    created : function() {
    },

    mounted : function() {
        window.addEventListener('keydown', this.keyActionDown, false);
        window.addEventListener('keyup', this.keyActionUp, false);
        this.next("");
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
            if (hp == 1000 || power >= 100 || brain >= 100) {
                flag_daiou = true;
            } else {
                flag_daiou = false;
            }
            console.log(damage + " " + hp + " " + power + " " + brain);
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
            this.image_src = "../../res/img/" + img;
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
                this.setImageSrc(this.enemy.getImg());
                if (damage <= 0) {
                    setTimeout(this.normalAttack, 100, 12);
                } else
                    setTimeout(this.normalAttack, 100, 4);
                break;
            case 4:
                //通常攻撃終
                page = 102;
                this.setTimer("", 100);
                break;
            case 11:
                //相手死亡
                page = 131;
                this.setTimer("", 100);
                break;
            case 12:
                //自分死亡
                page = -1;
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
                    setTimeout(this.brainAttack, 100, 21);
                } else {
                    setTimeout(this.brainAttack, 100, 2);
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
                page = 102;
                this.setTimer("", 100);
                break;
            case 21:
                //相手死亡
                page = 131;
                this.setImageSrc("attack.png");
                this.setTimer("", 100);
                break;
            case 22:
                //自分死亡
                page = -1;
                setTimeout(this.next, 100, "");
                break;
            }
        },

        setMoveEvent : function(i) {
            if (flag_daiou) {
                this.setImageSrc("bosstf.png");
                this.left = "旅を続ける";
                this.right = "討伐する";
                this.console_text = "恐怖の大王がやって来た。";
            } else {
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
                        this.console_text = "ネクタルという伝説の薬があるらしい。";
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
            }
        },

        setNextEvent : function() {
            while (true) {
                var random = Math.round(Math.random() * (event_random.length - 1));
                if (prev_event_random == random)
                    continue;
                if (random == 1 || random == 5 || random == 7) {
                    if (item_have1.getName() != "なし" || item_have2.getName() != "なし") {
                        if (random == 7) {
                            if (damage < hp / 2) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else if (random == 1) {
                    if (level == 4 && (item_have1.getName() == "ネクタル" || item_have2.getName() == "ネクタル")) {
                        continue;
                    }
                } else if (random == 6) {
                    if (item_have1.getName() == "笛" || item_have2.getName() == "笛" || item_have1.getName() == "たいこ" || item_have2.getName() == "たいこ" || item_have1.getName() == "バイオリン" || item_have2.getName() == "バイオリン" || item_have1.getName() == "ピアノ" || item_have2.getName() == "ピアノ") {
                        break;
                    }
                } else if (random == 8) {
                    if (damage < hp / 2) {
                        break;
                    } else {
                        random = 0;
                        break;
                    }
                } else {
                    break;
                }
            }
            prev_event_random = random;
            return event_random[random];
        },

        nextIsMove : function() {
            page = 1;
            this.next("");
        },

        nextIsMoveAnime : function() {
            this.buttonDisabled();
            page = 1;
            this.setTimer("", 2000);
        },

        haveInekutaru : function() {
            if (item_have1.getName() == "ネクタル" || item_have2.getName() == "ネクタル") {
                return true;
            }
            return false;
        },

        notHaveItem : function() {
            if (item_have1.getName() == "なし" && item_have2.getName() == "なし") {
                return true;
            }
            return false;
        },

        atleastOneNoItem : function() {
            if (item_have1.getName() == "なし" || item_have2.getName() == "なし") {
                return true;
            }
            return false;
        },

        next : function(button) {
            this.name_text = "---";

            switch(page) {//this.next()の後はreturn
            case 1:
                /********************************************************移動***********************************************************/
                sound_bgm_battle.pause();
                sound_bgm_battle.currentTime = 0;
                sound_bgm_battleboss.pause();
                sound_bgm_battleboss.currentTime = 0;
                sound_bgm_event.pause();
                sound_bgm_event.currentTime = 0;

                sound_bgm_move.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_move.play();
                setLevel();
                flag_sleep = false;
                save("none");
                //自分のレベルを設定
                if (hp > 999) {
                    hp = 999;
                }
                if (power > 100) {
                    power = 100;
                }
                if (brain > 100) {
                    brain = 100;
                }
                en_count++;
                move_random = Math.round(Math.random() * 5);
                this.setMoveEvent(move_random);
                page = 2;
                this.next("");
                break;
            case 2:
                this.setMoveEvent(move_random);
                if (this.notHaveItem() && en_count % 10 == 0) {
                    if (level == 1)
                        item_have1 = item[3];
                    else if (level == 2)
                        item_have1 = item[4];
                    else if (level == 3)
                        item_have1 = item[5];
                    else if (level == 4)
                        item_have1 = item[6];
                }

                if (button == "item1" || button == "item2") {
                    if (button == "item1") {
                        itemID = 1;
                    } else if (button == "item2") {
                        itemID = 2;
                    }
                    page = 3;
                    this.next(button);
                    return;
                } else if (button == "left" || button == "right") {
                    sound_bgm_move.pause();
                    sound_bgm_move.currentTime = 0;

                    page = this.setNextEvent();
                    if (flag_daiou && button == "right") {
                        page = 101;
                    } else if (button == "left") {
                        flag_daiou = false;
                    }
                    this.next(button);
                    return;
                }
                /*******************************************************************************デバッグ***************************************************************************/
                break;
            case 3:
                if (itemID == 1) {
                    if (item_have1.getName() == "帝国") {
                        sound_item_compare.play();
                        this.console_text = "即位しますか？\nゲームをクリアできます。";
                        this.left = "はい";
                        this.right = "いいえ";
                        page = 11;
                    } else if (item_have1.isFood()) {
                        if (flag_sleep && (item_have1.getName() == "寝袋" || item_have1.getName() == "上級寝袋" || item_have1.getName() == "家" || item_have1.getName() == "城")) {
                            this.console_text = "今は使えない。";
                            this.buttonDisabled();
                            page = 2;
                            this.setTimer("", 1000);
                            return;
                        }
                        if (damage < (hp + addHP1 + addHP2)) {
                            sound_item_compare.play();
                            this.console_text = item_have1.getName() + "を使用しますか？";
                            page = 4;
                            this.left = "はい";
                            this.right = "いいえ";
                        } else {
                            this.console_text = "十分に回復している。";
                            this.buttonDisabled();
                            page = 2;
                            this.setTimer("", 1000);
                            return;
                        }
                    } else {
                        this.console_text = "今は使えない。";
                        this.buttonDisabled();
                        page = 2;
                        this.setTimer("", 1000);
                        return;
                    }
                } else if (itemID == 2) {
                    if (item_have2.getName() == "帝国") {
                        sound_item_compare.play();
                        this.console_text = "即位しますか？\nゲームをクリアできます。";
                        this.left = "はい";
                        this.right = "いいえ";
                        page = 11;
                    } else if (item_have2.isFood()) {
                        if (flag_sleep && (item_have2.getName() == "寝袋" || item_have2.getName() == "上級寝袋" || item_have2.getName() == "家" || item_have2.getName() == "城")) {
                            this.console_text = "今は使えない。";
                            this.buttonDisabled();
                            page = 2;
                            this.setTimer("", 1000);
                            return;
                        }
                        if (damage < (hp + addHP1 + addHP2)) {
                            sound_item_compare.play();
                            this.console_text = item_have2.getName() + "を使用しますか？";
                            page = 4;
                            this.left = "はい";
                            this.right = "いいえ";
                        } else {
                            this.console_text = "十分に回復している。";
                            this.buttonDisabled();
                            page = 2;
                            this.setTimer("", 1000);
                            return;
                        }
                    } else {
                        this.console_text = "今は使えない。";
                        this.buttonDisabled();
                        page = 2;
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
                        if (damage > (hp + addHP1 + addHP2)) {
                            damage = (hp + addHP1 + addHP2);
                        }
                        if (item_have1.getName() != "寝袋" && item_have1.getName() != "上級寝袋" && item_have1.getName() != "家" && item_have1.getName() != "城") {
                            itemDrop(1);
                        } else {
                            flag_sleep = true;
                        }
                    } else {
                        this.console_text = item_have2.getName() + "を使用した。\n体力が" + item_have2.getValue() + "回復した。";
                        damage += item_have2.getValue();
                        if (damage > (hp + addHP1 + addHP2)) {
                            damage = (hp + addHP1 + addHP2);
                        }
                        if (item_have2.getName() != "寝袋" && item_have2.getName() != "上級寝袋" && item_have2.getName() != "家" && item_have2.getName() != "城") {
                            itemDrop(2);
                        } else {
                            flag_sleep = true;
                        }
                    }
                    sound_recovery.play();
                    this.buttonDisabled();
                    page = 2;
                    this.setTimer("", 2000);
                    return;
                } else if (button == "right") {
                    page = 2;
                    this.next("");
                    return;
                }
                break;
            case 11:
                if (button == "left") {
                    sound_item_compare.play();
                    this.console_text = "本当に良いですか？";
                    this.left = "即位する";
                    this.right = "やめる";
                    page = 12;
                } else if (button == "right") {
                    page = 2;
                    this.next("");
                    return;
                }
                break;
            case 12:
                if (button == "left") {
                    this.setImageSrc("moneyclear.png");
                    this.console_text = "先帝からの禅譲により、あなたは帝位についた。\nあなたが建国した帝国は、\nこの後２８９年栄えたと言われている。";
                    this.buttonDisabled();
                } else if (button == "right") {
                    page = 2;
                    this.next("");
                    return;
                }
                break;
            case 101:
                /********************************************************戦闘***********************************************************/
                sound_bgm_move.pause();
                sound_bgm_move.currentTime = 0;

                sound_bgm_battle.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_battle.play();

                for (var i = 0; i < unit.length; i++) {
                    if (unit[i].getName() == enemyID) {
                        this.enemy = unit[i];
                        break;
                    }
                }
                if (flag_daiou) {
                    this.enemy = unit[16];
                } else if (enemyID == "none") {
                    this.enemy = getEnemy(level, "");
                }
                saveBattle(this.enemy.getName());
                this.setImageSrc(this.enemy.getImg() + "");
                this.console_text = this.enemy.getName() + "が現れた";
                this.left = "攻撃";
                this.right = "知の一撃";
                page = 111;
                break;
            case 102:
                save(this.enemy.getName());
                this.setImageSrc(this.enemy.getImg() + "");
                this.console_text = "どうする？";
                this.left = "攻撃";
                this.right = "知の一撃";
                page = 111;
                break;
            case 111:
                //降参
                if (button == "item1" || button == "item2") {
                    if (button == "item1") {
                        if (!item_have1.isNone()) {
                            sound_item_compare.play();
                            if (item_have1.isHorse()) {
                                this.console_text = "逃げますか？";
                            } else if (item_have1.getName() == "えんまく") {
                                this.console_text = "えんまくを使用しますか？";
                            } else {
                                if (this.enemy.isAnimal())
                                    this.console_text = item_have1.getName() + "を囮に逃げますか？";
                                else
                                    this.console_text = item_have1.getName() + "を差し出して降参しますか？";
                            }
                            itemID = 1;
                        } else {
                            this.console_text = "今は使えない";
                            page = 102;
                            this.buttonDisabled();
                            this.setTimer("item1", 1000);
                            return;
                        }
                    } else {
                        if (!item_have2.isNone()) {
                            sound_item_compare.play();
                            if (item_have2.isHorse()) {
                                this.console_text = "逃げますか？";
                            } else if (item_have2.getName() == "えんまく") {
                                this.console_text = "えんまくを使用しますか？";
                            } else {
                                if (this.enemy.isAnimal())
                                    this.console_text = item_have2.getName() + "を囮に逃げますか？";
                                else
                                    this.console_text = item_have2.getName() + "を差し出して降参しますか？";
                            }
                            itemID = 2;
                        } else {
                            this.console_text = "今は使えない";
                            page = 102;
                            this.buttonDisabled();
                            this.setTimer("item2", 1000);
                            return;
                        }
                    }
                    this.left = "はい";
                    this.right = "いいえ";
                    page = 112;
                } else if (button == "left" || button == "right") {
                    page = 121;
                    this.next(button);
                    return;
                }
                break;
            case 112:
                if (button == "left") {
                    page = 113;
                    this.next(button);
                    return;
                } else if (button == "right") {
                    page = 102;
                    this.next("");
                    return;
                }
                break;
            case 113:
                this.buttonDisabled();
                this.reload();

                if ((itemID == 1 && item_have1.isHorse()) || (itemID == 2 && item_have2.isHorse())) {
                    var random = Math.round(Math.random() * 9);
                    if (random < 3) {
                        sound_escape.play();
                        this.console_text = "逃げ切れた！";
                        this.nextIsMoveAnime();
                        return;
                    } else {
                        this.console_text = "追いつかれた！";
                        setTimeout(this.normalAttack, 800, 1);
                        return;
                    }
                }

                var flag = false;
                if (this.enemy.isAnimal()) {
                    if ((itemID == 1 && item_have1.isFood()) || (itemID == 2 && item_have2.isFood())) {
                        flag = true;
                    }
                    if ((itemID == 1 && item_have1.getName() == "えんまく") || (itemID == 2 && item_have2.getName() == "えんまく")) {
                        flag = true;
                    }
                    if (itemID == 1) {
                        itemDrop(1);
                    } else if (itemID == 2) {
                        itemDrop(2);
                    }
                    if (flag) {
                        this.console_text = "逃げ切れた！";
                        sound_escape.play();
                        this.nextIsMoveAnime();
                    } else {
                        this.console_text = "逃してくれそうにない・・・";
                        setTimeout(this.normalAttack, 800, 1);
                    }
                } else {
                    if (this.atleastOneNoItem()) {
                        flag = true;
                    }

                    if ((itemID == 1 && item_have1.getName() == "えんまく") || (itemID == 2 && item_have2.getName() == "えんまく")) {
                        sound_escape.play();

                        this.name_text = this.enemy.getName();
                        this.console_text = "「うわーーー」";
                        this.nextIsMoveAnime();
                        return;
                    }

                    if (itemID == 1) {
                        itemDrop(1);
                    } else if (itemID == 2) {
                        itemDrop(2);
                    }
                    this.name_text = this.enemy.getName();
                    if (getYesNo() || flag) {
                        sound_escape.play();
                        this.console_text = "「よかろう！見逃してやる」";
                        this.nextIsMoveAnime();
                        return;
                    } else {
                        this.console_text = "「こんなもので命乞いとは笑わせてくれる！」";
                        setTimeout(this.normalAttack, 800, 1);
                    }
                }
                return;
            case 121:
                //攻撃
                this.buttonDisabled();
                if (button == "left" || button == "right") {
                    if (this.haveInekutaru()) {
                        page = 161;
                        damage += 100;
                        if (damage > (hp + addHP1 + addHP2)) {
                            damage = (hp + addHP1 + addHP2);
                        }
                        this.reload();
                        this.next(button);
                        return;
                    } else {
                        page = 122;
                        this.next(button);
                        return;
                    }
                }
                break;
            case 122:
                if (button == "left") {
                    if (addPower1 != 0 || addPower2 != 0)
                        sound_attack2.play();
                    else
                        sound_attack1.play();
                    this.console_text = "攻撃！\n" + this.enemy.getName() + "に" + (power + addPower1 + addPower2) + "ダメージ。\n\n";
                    setTimeout(this.normalAttack, 500, 0);
                    return;
                } else if (button == "right") {
                    sound_syuutyuu.play();
                    this.console_text = "あなたは集中している。";
                    setTimeout(this.brainAttack, 2000, 0);
                    return;
                }
                break;
            case 131:
                //相手倒した
                sound_bgm_battle.pause();
                sound_bgm_battle.currentTime = 0;

                sound_knockdown.play();

                this.console_text += this.enemy.getName() + "を倒した。";
                if (flag_daiou) {
                    this.console_text += "\n世界は救われた。";
                    this.left = "英雄になる";
                    this.right = "旅を終わる";
                    page = 151;
                } else {
                    this.left = "旅を続ける";
                    this.right = "身ぐるみ剥ぐ";
                    page = 132;
                }
                break;
            case 132:
                if (button == "left") {
                    hp = hp + 5;
                    power = power + 1;
                    brain = brain + 1;
                    page = 1;
                    this.nextIsMove();
                    return;
                } else if (button == "right") {
                    var random = Math.round(Math.random() * 9);
                    if (random < 8) {
                        page = 133;
                        hp = hp + 5;
                        power = power + 1;
                        brain = brain + 1;
                        this.next("");
                        return;
                    } else {
                        page = 141;
                        this.next("");
                        return;
                    }
                }
                break;
            case 133:
                this.console_text = this.enemy.getName() + "は" + this.enemy.getItem().getName() + "を持っていた。\n";
                if (this.atleastOneNoItem()) {
                    this.buttonDisabled();
                    page = 1;
                    sound_item_get.play();
                    this.console_text += this.enemy.getItem().getName() + "を手に入れた。";
                    if (item_have1.getName() == "なし") {
                        item_have1 = this.enemy.getItem();
                    } else if (item_have2.getName() == "なし") {
                        item_have2 = this.enemy.getItem();
                    }
                    this.setTimer("", 2000);
                    return;
                } else {
                    sound_item_compare.play();
                    this.console_text += "手持ちと交換しますか？";
                    page = 2001;
                    this.next("");
                    return;
                }
                break;
            case 141:
                //息を吹き返す
                sound_bgm_battle.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_battle.play();

                sound_revive.play();

                this.buttonDisabled();
                this.console_text = this.enemy.getName() + "が息を吹き返した！";
                this.setImageSrc(this.enemy.getImg());
                setTimeout(this.normalAttack, 800, 1);
                return;
            case 151:
                if (button == "left" || button == "right") {
                    this.setImageSrc("clear.png");
                    this.console_text = "恐怖の大王を討伐したあなたは、\n英雄として讃えられた。\nその名は永遠に語り継がれるだろう。";
                    this.buttonDisabled();
                    page = 152;
                    this.next("");
                    return;
                }
                break;
            case 161:
                sound_recovery.play();

                page = 122;
                this.console_text = "あなたはネクタルを飲んだ。\n体力が100回復した。";
                setTimeout(this.next, 2000, button);
                return;
            case 201:
                /********************************************************交換（アイテムは必ず持っていると仮定）***********************************************************/
                sound_bgm_event.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_event.play();
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
                    this.console_text = "「あなたの持っている" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "と、\n私の" + this.enemy.getItem().getName() + "を交換しませんか？」";
                } else if (level == 2) {
                    this.setImageSrc("kodomo.png");
                    this.name_text = "子供";
                    this.left = "あげる";
                    this.right = "やらん";
                    this.console_text = "「" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "がどうしても必要なんだ。\nおいらの" + this.enemy.getItem().getName() + "と交換しないか？」";
                } else if (level == 3) {
                    this.setImageSrc("hugou.png");
                    this.name_text = "富豪";
                    this.left = "いいよ";
                    this.right = "いやだ";
                    this.console_text = "「" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "が必要なの。\n譲ると申すなら、代わりのものをくれてやるぞ。」";
                } else if (level == 4) {
                    this.setImageSrc("king.png");
                    this.name_text = "皇帝";
                    this.left = "どうぞ";
                    this.right = "ご勘弁を";
                    if (this.haveInekutaru()) {
                        if (item_have1.getName() == "ネクタル") {
                            itemID = 1;
                        } else {
                            itemID = 2;
                        }
                        this.enemy.setItemIsName("帝国");
                    }
                    this.console_text = "「" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "がわしには必要なのじゃ。\n譲ると申すなら代わりに、\n" + this.enemy.getItem().getName() + "を授けるぞ。」";
                }
                page = 202;
                break;
            case 202:
                if (button == "left") {
                    if (itemID == 1) {
                        item_have1 = this.enemy.getItem();
                    } else {
                        item_have2 = this.enemy.getItem();
                    }
                    sound_item_get.play();
                    this.console_text = this.enemy.getItem().getName() + "を手に入れた。";
                    this.nextIsMoveAnime();
                } else if (button == "right") {
                    this.nextIsMove();
                    return;
                }
                break;
            case 301:
                /********************************************************仕事***********************************************************/
                sound_bgm_event.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_event.play();

                workID = Math.round(Math.random() * 2);
                if (level == 1) {
                    this.left = "いいよ";
                    if (workID == 0) {
                        this.setImageSrc("kouzann.png");
                        this.name_text = "鉱員";
                        this.right = "用事があるから";
                        this.console_text = "「人手が足りなくて困っているんだ。\nあんた手伝ってくれないかね？」";
                    } else if (workID == 1) {
                        this.setImageSrc("ryousi.png");
                        this.name_text = "猟師";
                        this.right = "そんな時間はない";
                        this.console_text = "「狩りに行きたいのだが、\n一人じゃ不安なんだ。\n一緒に来てくれないかね？」";
                    } else if (workID == 2) {
                        this.setImageSrc("zakkaya.png");
                        this.name_text = "雑貨屋";
                        this.right = "人見知りだから";
                        this.console_text = "「雇っていたのが急に休んじまって、\n店番してくれないかね？」";
                    }
                } else if (level == 2) {
                    this.left = "まかせて";
                    if (workID == 0) {
                        this.setImageSrc("sakanaya.png");
                        this.name_text = "魚屋";
                        this.right = "役に立てない";
                        this.console_text = "「最近売り上げが悪いんだ。\n呼び込みをやってくれないか？」";
                    } else if (workID == 1) {
                        this.setImageSrc("taityou.png");
                        this.name_text = "隊長";
                        this.right = "私は弱いです";
                        this.console_text = "「君はなかなか強そうだな。\n蛮族を退治するのだが、\n参加するかね？」";
                    } else if (workID == 2) {
                        this.setImageSrc("sakabanosyuzinn.png");
                        this.name_text = "酒場の主人";
                        this.right = "人見知りだから";
                        this.console_text = "「人手が足りなくて困っているんだ。\nあんた店を手伝ってくれないかね？」";
                    }
                } else if (level == 3) {
                    this.left = "引き受けた";
                    if (workID == 0) {
                        this.setImageSrc("yadoyanosyuzinn.png");
                        this.name_text = "一流宿屋";
                        this.right = "お役に立てない";
                        this.console_text = "「最近サービスが悪いと言われるんだ。\n監督してくれないかね？」";
                    } else if (workID == 1) {
                        this.setImageSrc("syougunn.png");
                        this.name_text = "将軍";
                        this.right = "戦えません";
                        this.console_text = "「この度きみに召集がかかった。\n隣国に攻め入るのだ。\nともに行こう！」";
                    } else if (workID == 2) {
                        this.setImageSrc("kazinonosyuzinn.png");
                        this.name_text = "カジノの支配人";
                        this.right = "務まりません";
                        this.console_text = "「警備が不足していて、\nきみやってくれない？」";
                    }
                } else if (level == 4) {
                    this.left = "お任せを";
                    if (workID == 0) {
                        this.console_text = "「そなたを近衛隊長に任命する。\n王宮を守るのじゃ！」";
                    } else if (workID == 1) {
                        this.console_text = "「そなたを将軍に任命する。\n国の平和を守るのじゃ！」";
                    } else if (workID == 2) {
                        this.console_text = "「そなたを大臣に任命する。\n国の維持に努めるのじゃ！」";
                    }
                    this.name_text = "王";
                    this.setImageSrc("gyokuza.png");
                    this.right = "お断りします";
                }
                page = 302;
                break;
            case 302:
                if (button == "left") {
                    var random = Math.round(Math.random() * 9);
                    var add;
                    var addD;
                    if (level == 1) {
                        add = 1;
                        addD = 10;
                    } else if (level == 2) {
                        add = 2;
                        addD = 25;
                    } else if (level == 3) {
                        add = 3;
                        addD = 50;
                    } else if (level == 4) {
                        add = 5;
                        addD = 100;
                    }
                    if (random < 8 || (damage - addD) < 1) {
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
                            if (level == 1) {
                                add = 20;
                            } else if (level == 2) {
                                add = 30;
                            } else if (level == 3) {
                                add = 40;
                            } else if (level == 4) {
                                add = 40;
                            }
                            hp += add;
                        } else if (workID == 1) {
                            power += add;
                        } else if (workID == 2) {
                            brain += add;
                        }
                        sound_powerup.play();
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
                        sound_damage.play();
                        this.console_text = "「つかえない奴め！」\nボゴッ　ドスッ　バコッ\n\n" + addD + "ダメージ。";
                        damage -= addD;
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
                /*********************************************************************宝探し************************************************************/
                sound_bgm_event.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_event.play();

                this.enemy = getEnemy(level);
                if (level == 1) {
                    this.console_text = "何かが埋まっている。\n掘りますか？";
                    this.setImageSrc("ana1.png");
                    this.left = "掘る";
                } else if (level == 2) {
                    this.console_text = "宝箱がある。\n開けますか？";
                    this.setImageSrc("takarabako1.png");
                    this.left = "開ける";
                } else if (level == 3) {
                    this.console_text = "迷いの森だ。\n進みますか？";
                    this.setImageSrc("mori1.png");
                    this.left = "進む";
                } else if (level == 4) {
                    this.enemy.setItemIsName("ネクタル");
                    this.console_text = "大迷宮の入り口だ。\n入りますか？";
                    this.setImageSrc("meikyuu1.png");
                    this.left = "入る";
                }
                this.right = "やめとく";
                page = 402;
                break;
            case 402:
                if (button == "left") {
                    var random = Math.round(Math.random() * 9);
                    if (level == 1) {
                        if (item_have1.getName() == "シャベル" || item_have2.getName() == "シャベル") {
                            this.setImageSrc("ana2.png");
                            if (random < 5) {
                                sound_item_compare.play();
                                this.console_text = "何もなかった・・・";
                                this.nextIsMoveAnime();
                                return;
                            }
                        } else {
                            if (random < 8) {
                                sound_item_compare.play();
                                this.setImageSrc("ana1.png");
                                this.console_text = "うまく掘れなかった。\nシャベルがあると良いかも。";
                                this.nextIsMoveAnime();
                                return;
                            } else {
                                this.setImageSrc("ana2.png");
                            }
                        }
                    } else if (level == 2) {
                        if (item_have1.getName() == "鍵開け道具" || item_have2.getName() == "鍵開け道具") {
                            this.setImageSrc("takarabako2.png");
                            if (random < 5) {
                                sound_item_compare.play();
                                this.console_text = "空っぽだった・・・";
                                this.nextIsMoveAnime();
                                return;
                            }
                        } else {
                            if (random < 8) {
                                sound_item_compare.play();
                                this.setImageSrc("takarabako1.png");
                                this.console_text = "開けられない。\n鍵開け道具があると良いかも。";
                                this.nextIsMoveAnime();
                                return;
                            } else {
                                this.setImageSrc("takarabako2.png");
                            }
                        }
                    } else if (level == 3) {
                        this.setImageSrc("mori2.png");
                        if (item_have1.getName() == "森の地図" || item_have2.getName() == "森の地図") {
                            if (random < 5) {
                                sound_item_compare.play();
                                this.console_text = "迷った・・・";
                                this.nextIsMoveAnime();
                                return;
                            }
                        } else {
                            if (random < 8) {
                                sound_item_compare.play();
                                this.console_text = "迷った・・・\n森の地図があると良いかも。";
                                this.nextIsMoveAnime();
                                return;
                            }
                        }
                    } else if (level == 4) {
                        this.setImageSrc("meikyuu2.png");
                        if (item_have1.getName() != "精霊" && item_have2.getName() != "精霊") {
                            if (random < 8) {
                                sound_item_compare.play();
                                this.console_text = "行き止まりだ・・・\n精霊がいると良いかも。";
                                this.nextIsMoveAnime();
                                return;
                            }
                        }
                    }
                    if (this.atleastOneNoItem()) {
                        sound_item_get.play();
                        this.console_text = this.enemy.getItem().getName() + "を手に入れた。";
                        if (item_have1.getName() == "なし") {
                            item_have1 = this.enemy.getItem();
                        } else {
                            item_have2 = this.enemy.getItem();
                        }
                        this.nextIsMoveAnime();
                        return;
                    } else {
                        sound_item_compare.play();
                        this.console_text = this.enemy.getItem().getName() + "を見つけた。\n手持ちと交換しますか？";
                        page = 2001;
                        this.next("");
                        return;
                    }

                } else if (button == "right") {
                    this.nextIsMove();
                    return;
                }
                break;
            case 501:
                /******************************************************賭け事******************************************************/
                sound_bgm_event.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_event.play();

                scam_random = Math.round(Math.random() * 1);
                //勝ち負けの設定 0=勝ち 1=負け

                var m_random = Math.round(Math.random() * 24) + 1;
                //未来を読めるかの値

                var nlevel = level + 1;
                if (level >= 4)
                    nlevel = 4;

                if (this.notHaveItem()) {
                    this.enemy = getEnemy(nlevel, "");
                    flag_kengou = true;
                    this.setImageSrc("kenngou.png");
                    this.name_text = "剣豪";
                    this.console_text = "「そなたに決闘を申し込む！\nそなたが勝てば我が家宝をくれてやる。\n決闘するか？」";
                    this.left = "命かける";
                    this.right = "やっぱやだ";
                    par = brain % 25;
                    if (par == 0)
                        par = 25;
                    if (m_random <= par) {
                        if (scam_random == 0) {
                            this.console_text += "\n\n（勝てる気がする）";
                        } else {
                            this.console_text += "\n\n（負ける気がする）";
                        }
                    } else {
                        if (scam_random == 0) {
                            this.console_text += "\n\n（負ける気がする）";
                        } else {
                            this.console_text += "\n\n（勝てる気がする）";
                        }
                    }
                } else {
                    console.log(nlevel);
                    this.enemy = getEnemy(nlevel, (itemID == 1 ? item_have1.getName() : item_have2.getName()));
                    flag_kengou = false;
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

                    this.left = "いいよ";
                    this.right = "いやだ";
                    if (level == 1) {
                        this.setImageSrc("man.png");
                        this.name_text = "男";
                        this.console_text = "「" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "を欲しがってる奴がいるんだ。\n後で代わりのアイテムをあげるから、\n私に預けてくれないか？」";
                    } else if (level == 2) {
                        this.setImageSrc("syounenn.png");
                        this.name_text = "少年";
                        this.console_text = "「おおーそれは" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "ですね！\n後ほどお礼の品を差し上げるので、\n私にくださいませんか？」";
                    } else if (level == 3) {
                        this.setImageSrc("urabuguya.png");
                        this.name_text = "裏商人";
                        this.console_text = "「君の" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "を譲ってくれないか？\n支払いは後になるけど。」";
                    } else if (level == 4) {
                        this.setImageSrc("ruretto.png");
                        this.name_text = "カジノ支配人";
                        this.console_text = "「ルーレットやっていきませんか。\n当たれば豪華賞品をプレゼント！\nはずれるとアイテム没収！」";
                        this.left = "やる";
                        this.right = "やらない";
                    }
                    par = brain % 25;
                    if (par == 0)
                        par = 25;
                    if (m_random <= par) {
                        if (scam_random == 0) {
                            if (level == 4)
                                this.console_text += "\n\n（勝てる気がする）";
                            else
                                this.console_text += "\n\n（この人の言うことは本当かも）";
                        } else {
                            if (level == 4)
                                this.console_text += "\n\n（負ける気がする）";
                            else
                                this.console_text += "\n\n（この人の言うことは嘘かも）";
                        }
                    } else {
                        if (scam_random == 0) {
                            if (level == 4)
                                this.console_text += "\n\n（負ける気がする）";
                            else
                                this.console_text += "\n\n（この人の言うことは嘘かも）";
                        } else {
                            if (level == 4)
                                this.console_text += "\n\n（勝てる気がする）";
                            else
                                this.console_text += "\n\n（この人の言うことは本当かも）";
                        }
                    }
                }
                page = 502;
                break;
            case 502:
                if (flag_kengou) {
                    this.name_text = "剣豪";
                } else {
                    if (level == 1) {
                        this.name_text = "男";
                    } else if (level == 2) {
                        this.name_text = "少年";
                    } else if (level == 3) {
                        this.name_text = "裏商人";
                    } else if (level == 4) {
                        this.name_text = "カジノ支配人";
                    }
                }
                if (button == "left") {
                    this.buttonDisabled();
                    if (flag_kengou) {
                        this.console_text = "「まいる！！！」";
                    } else {
                        if (level == 1) {
                            this.console_text = "「ちょっと待っていてくれ」";
                        } else if (level == 2) {
                            this.console_text = "「それでは少々お待ちください」";
                        } else if (level == 3) {
                            this.console_text = "「ありがとよ\nちょいと待ってくれ」";
                        } else if (level == 4) {
                            this.console_text = "「それではまいります！」";
                        }
                        if (itemID == 1) {
                            itemDrop(1);
                        } else {
                            itemDrop(2);
                        }

                    }
                    page = 503;
                    setTimeout(this.next, 2000);
                } else if (button == "right") {
                    this.nextIsMove();
                    return;
                }
                break;
            case 503:
                this.setImageSrc("attack.png");
                this.console_text = "・・";
                page = 504;
                setTimeout(this.next, 1000);
                return;
                break;
            case 504:
                this.console_text += "・・";
                page = 505;
                setTimeout(this.next, 1000);
                return;
                break;
            case 505:
                this.console_text += "・・";
                page = 506;
                setTimeout(this.next, 1000);
                return;
                break;
            case 506:
                if (scam_random == 0) {
                    if (flag_kengou) {
                        this.console_text = "剣豪を倒した。\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        item_have1 = this.enemy.getItem();
                    } else {
                        if (itemID == 1) {
                            item_have1 = this.enemy.getItem();
                        } else if (itemID == 2) {
                            item_have2 = this.enemy.getItem();
                        }
                        if (level == 1) {
                            this.setImageSrc("man.png");
                            this.name_text = "男";
                            this.console_text = "「約束の物だ。」\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        } else if (level == 2) {
                            this.setImageSrc("syounenn.png");
                            this.name_text = "少年";
                            this.console_text = "「こちらがお礼の品です。」\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        } else if (level == 3) {
                            this.setImageSrc("urabuguya.png");
                            this.name_text = "裏商人";
                            this.console_text = "「これが支払いの品だ。」\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        } else if (level == 4) {
                            this.setImageSrc("ruretto.png");
                            this.name_text = "カジノ支配人";
                            this.console_text = "「大当たり！\nこちらが景品です。」\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        }
                    }
                    sound_item_get.play();
                } else {
                    if (flag_kengou) {
                        sound_damage.play();
                        page = -1;
                        this.next("");
                        return;
                    } else {
                        sound_bgm_event.pause();
                        sound_bgm_event.currentTime = 0;

                        sound_scam.play();
                        if (level == 1) {
                            this.console_text = "持ち逃げされた・・・";
                        } else if (level == 2) {
                            this.console_text = "少年は消えた・・・";
                        } else if (level == 3) {
                            this.console_text = "持ち逃げされた・・・";
                        } else if (level == 4) {
                            this.setImageSrc("ruretto.png");
                            this.name_text = "カジノ支配人";
                            this.console_text = "「ハズレです。\nアイテムは没収でーす。」";
                        }
                    }
                }
                this.reload();
                this.nextIsMoveAnime();
                return;
            case 601:
                /****************************************************損得交換（アイテムは必ず持ってると仮定）************************************************************/
                sound_bgm_event.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_event.play();

                var nlevel = level + 1;
                if (level >= 4)
                    nlevel = 4;

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

                this.enemy = getEnemy(nlevel, (itemID == 1 ? item_have1.getName() : item_have2.getName()));
                if (level == 1) {
                    this.setImageSrc("huzinn.png");
                    this.name_text = "ご婦人";
                    this.left = "いいよ";
                    this.right = "いやだ";
                    this.console_text = "「" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "をくれないかい？」";
                } else if (level == 2) {
                    this.setImageSrc("wakamono.png");
                    this.name_text = "若者";
                    this.left = "あげる";
                    this.right = "やらん";
                    this.console_text = "「" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "を譲ってくれないかい？」";
                } else if (level == 3) {
                    this.setImageSrc("reizyou.png");
                    this.name_text = "令嬢";
                    this.left = "どうそ";
                    this.right = "お断りします";
                    this.console_text = "「" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "を譲っていただける？」";
                } else if (level == 4) {
                    this.setImageSrc("himenokerai.png");
                    this.name_text = "家来";
                    this.left = "仰せのままに";
                    this.right = "ご勘弁を";
                    this.console_text = "「" + (itemID == 1 ? item_have1.getName() : item_have2.getName()) + "を姫がご所望だ。」";
                }
                page = 602;
                break;
            case 602:
                if (button == "left") {
                    if (level == 1) {
                        this.name_text = "ご婦人";
                    } else if (level == 2) {
                        this.name_text = "若者";
                    } else if (level == 3) {
                        this.name_text = "令嬢";
                    } else if (level == 4) {
                        this.name_text = "家来";
                    }
                    var random = Math.round(Math.random() * 1);
                    if (random == 0) {
                        if (itemID == 1) {
                            itemDrop(1);
                        } else {
                            itemDrop(2);
                        }
                        sound_item_compare.play();
                        if (level == 1) {
                            this.console_text = "「ありがとね」";
                        } else if (level == 2) {
                            this.console_text = "「どうもありがとう」";
                        } else if (level == 3) {
                            this.console_text = "「ありがとう、うれしいわ」";
                        } else if (level == 4) {
                            this.console_text = "「ご苦労」";
                        }
                    } else {
                        if (itemID == 1) {
                            item_have1 = this.enemy.getItem();
                        } else {
                            item_have2 = this.enemy.getItem();
                        }
                        if (level == 1) {
                            this.console_text = "「ありがとう\nこれはお礼よ。」\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        } else if (level == 2) {
                            this.console_text = "「ありがとう\n代わりにこれを貰ってくれ。」\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        } else if (level == 3) {
                            this.console_text = "「ありがとう\n代わりにこれを受け取って。」\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        } else if (level == 4) {
                            this.console_text = "「代わりにこれを与える。」\n\n" + this.enemy.getItem().getName() + "を手に入れた。";
                        }
                        sound_item_get.play();
                    }
                    this.reload();
                    this.nextIsMoveAnime();
                } else if (button == "right") {
                    this.reload();
                    this.nextIsMove();
                }
                return;
            case 701:
                /**************************************************************演奏（楽器は必ず持ってると仮定）************************************************************/
                if (level == 1) {
                    this.name_text = "道具屋";
                    this.console_text = "「店の前で演奏して、\n客を呼び込んでくれんかね。」";
                    this.left = "いいよ";
                    this.right = "いやだ";
                    this.setImageSrc("douguya.png");
                } else if (level == 2) {
                    this.name_text = "武具屋";
                    this.console_text = "「店先で演奏して、\n呼び込みをしてくれ。」";
                    this.left = "いいよ";
                    this.right = "いやだ";
                    this.setImageSrc("bukiya.png");
                } else if (level == 3) {
                    this.name_text = "地主";
                    this.console_text = "「今宵の宴で演奏してくれぬか。」";
                    this.left = "承知した";
                    this.right = "無理です";
                    this.setImageSrc("zinusi.png");
                } else if (level == 4) {
                    this.name_text = "摂政";
                    this.console_text = "「わしのために、\nそなたの音色を聞かせてくれぬか。」";
                    this.left = "かしこまりました";
                    this.right = "荷が重いです";
                    this.setImageSrc("sessyou.png");
                }
                page = 702;
                break;
            case 702:
                if (button == "left") {
                    var random = Math.round(Math.random() * 9);
                    var add;
                    var addD;
                    if (level == 1) {
                        this.name_text = "道具屋";
                        add = 1;
                        addD = 10;
                    } else if (level == 2) {
                        this.name_text = "武具屋";
                        add = 2;
                        addD = 25;
                    } else if (level == 3) {
                        this.name_text = "地主";
                        add = 3;
                        addD = 50;
                    } else if (level == 4) {
                        this.name_text = "摂政";
                        add = 5;
                        addD = 100;
                    }
                    if (random < 8 || (damage - addD) < 1) {
                        var ability_random = Math.round(Math.random() * 2);
                        if (level == 1) {
                            this.console_text = "「君のおかげで繁盛したよ。ありがとう」\n\n";
                        } else if (level == 2) {
                            this.console_text = "「助かったよ。ありがとう」\n\n";
                        } else if (level == 3) {
                            this.console_text = "「良い演奏だったぞ。」\n\n";
                        } else if (level == 4) {
                            this.console_text = "「見事な音色じゃった。」\n\n";
                        }

                        if (ability_random == 0) {
                            if (level == 1) {
                                add = 20;
                            } else if (level == 2) {
                                add = 30;
                            } else if (level == 3) {
                                add = 40;
                            } else if (level == 4) {
                                add = 40;
                            }
                            hp += add;
                            this.console_text += "体力がついた。";
                        } else if (ability_random == 1) {
                            power += add;
                            this.console_text += "力が上がった。";
                        } else if (ability_random == 2) {
                            brain += add;
                            this.console_text += "知力が上がった。";
                        }
                        sound_powerup.play();
                    } else {
                        sound_damage.play();
                        this.console_text = "「つかえない奴め！」\nボゴッ　ドスッ　バコッ\nn" + addD + "ダメージ。";
                        damage -= addD;
                    }
                    this.nextIsMoveAnime();
                } else if (button == "right") {
                    this.nextIsMove();
                }
                return;
            case 801:
                /*************************************************************宿屋*******************************************************************/
                sound_bgm_event.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_event.play();

                this.setImageSrc("yadoya.png");
                this.name_text = "宿屋";
                this.console_text = "「いらっしゃい\n泊まっていくかい？\nあんたの持ってるものを、\n宿代としてもらうよ。」";
                this.left = "はい";
                this.right = "いいえ";
                page = 802;
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
                        this.console_text = "「" + item_have1.getName() + "をもらうよ。」\n\n体力が全回復した。";
                        itemDrop(1);
                    } else {
                        this.console_text = "「" + item_have2.getName() + "をもらうよ。」\n\n体力が全回復した。";
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

                sound_bgm_event.addEventListener('ended', function() {
                    this.play();
                });
                sound_bgm_event.play();

                var random = Math.round(Math.random() * 1);
                if (random == 0) {
                    this.name_text = "ご婦人";
                    this.console_text = "「あんた疲れているね。\n休んでくといいよ。」\n\n体力が回復した。";
                    this.setImageSrc("gohuzinn.png");
                    damage += parseInt((hp + addHP1 + addHP2) / 2);
                    if (damage > (hp + addHP1 + addHP2)) {
                        damage = (hp + addHP1 + addHP2);
                    }
                } else {
                    this.name_text = "シスター";
                    this.console_text = "「傷だらけですね。\n休んでいくと良いでしょう。」\n\n体力が全回復した。";
                    this.setImageSrc("sister.png");
                    damage = (hp + addHP1 + addHP2);
                }
                this.left = "ありがとう";
                this.right = "Thank you";
                page = 1;
                break;
            case 2001:
                /*******************************************************手持ちとのアイテム交換**********************************************************/
                this.left = "はい";
                this.right = "いいえ";
                page = 2002;
                break;
            case 2002:
                if (button == "left") {
                    this.console_text = this.enemy.getItem().getName() + "と何を交換しますか？";
                    this.left = item_have1.getName();
                    this.right = item_have2.getName();
                    page = 2003;
                } else if (button == "right") {
                    this.nextIsMove();
                }
                break;
            case 2003:
                if (button == "left" || button == "right") {
                    if (button == "left") {
                        item_have1 = this.enemy.getItem();
                    } else if (button == "right") {
                        item_have2 = this.enemy.getItem();
                    }
                    sound_item_get.play();
                    this.console_text = this.enemy.getItem().getName() + "を手に入れた。";
                    this.nextIsMoveAnime();
                }
                break;
            case 9001:
                this.console_text = "恐怖の大王がやってきた。\n無数のモンスターが世界を蹂躙している。\n帝国は衰退し、人々は英雄を望んでいる。";
                this.setImageSrc("bosstf.png");
                this.left = "立ち上がる";
                this.right = "あきらめる";
                page = 9002;
                break;
            case 9002:
                if (button == "left") {
                    this.nextIsMove();
                    return;
                } else if (button == "right") {
                    page = -1;
                    this.next("");
                    return;
                }
                break;
            case 9003:
                this.console_text = "あなたはまだ何もしていない。\n今、再び立ち上がらねば！";
                this.setImageSrc("bosstf.png");
                this.left = "立ち上がる";
                this.right = "目覚める";
                page = 9004;
                break;
            case 9004:
                if (button == "left" || button == "right") {
                    this.nextIsMove();
                    return;
                }
                break;
            case -1:
                //死亡
                sound_bgm_battle.pause();
                sound_bgm_battle.currentTime = 0;
                sound_bgm_battleboss.pause();
                sound_bgm_battleboss.currentTime = 0;
                sound_bgm_event.pause();
                sound_bgm_event.currentTime = 0;

                sound_dead.play();

                n_flag = "1";
                save("");
                this.console_text = "あなたは死んだ。";
                this.setImageSrc("damage.png");
                this.buttonDisabled();
                break;
            }
            console.log(page);
            this.reload();

            setTimeout(this.buttonEnabled, 250);
        },
    },
});

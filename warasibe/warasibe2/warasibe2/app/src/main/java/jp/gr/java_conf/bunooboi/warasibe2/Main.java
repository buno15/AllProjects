package jp.gr.java_conf.bunooboi.warasibe2;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    Context context;

    Scene meziha[] = new Scene[100];

    Scene meziha_Weapon;
    Scene meziha_Armor;
    Scene meziha_Inn[] = new Scene[7];

    Scene meziha_Lynch[] = new Scene[4];

    Scene battle[] = new Scene[9];                                                          //battleは直前のfinishから設定。nextSceneも設定する
    Scene shop[] = new Scene[12];

    Scene coffeeBreak;
    Scene eat;
    Scene eatIn[] = new Scene[2];
    Scene sleep;
    Scene search[] = new Scene[2];

    Scene dead;

    Handler handler = new Handler();

    Player enemy = new Player("トカ聖兵", R.drawable.heisi);
    Player clerk;


    Item wrongSword = new Item("ボロイ剣", R.drawable.wrongsword, 10, 1, Item.WEAPON);
    Item wrongBow = new Item("ボロイ弓", R.drawable.a, 10, 1, Item.WEAPON);
    Item wrongAxe = new Item("ボロイ斧", R.drawable.b, 10, 1, Item.WEAPON);
    Item wrongSpear = new Item("ボロイ槍", R.drawable.heisi, 10, 1, Item.WEAPON);
    Item wrongBlade = new Item("ボロイ刀", R.drawable.bukiya, 10, 1, Item.WEAPON);


    Item treeBranch = new Item("木の枝", R.drawable.treebranch, 10, 1, Item.MATERIAL);

    Item wrongHelmet = new Item("ボロイ兜", R.drawable.wronghelmet, 10, 1, Item.ARMOR);
    Item wrongArmor = new Item("ボロイ鎧", R.drawable.d, 10, 1, Item.ARMOR);
    Item wrongShield = new Item("ボロイ盾", R.drawable.f, 10, 1, Item.ARMOR);

    Item apple = new Item("りんご", R.drawable.apple, 10, 1, Item.FOOD);

    Item IHave;
    Item youHave;

    static ArrayList<Item> items = new ArrayList<>();

    Calendar calendar;

    int year;
    int month;
    int day;
    int hour;
    int minute;

    public Main(final Context context) {
        this.context = context;


        I.HP = 60;
        I.maxHP = 100;
        I.Stamina = 15;
        I.Power = 20;
        I.Defense = 10;
        I.Intelligence = 30;
        I.Karma = 20;
        I.init();

        calendar = Calendar.getInstance();
        calendar.set(500, 12, 30, 22, 00);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);


        wrongSword.setExchange(treeBranch);
        wrongBow.setExchange(wrongBlade);
        wrongAxe.setExchange(wrongSpear);
        wrongSpear.setExchange(treeBranch);
        wrongBlade.setExchange(treeBranch);

        wrongHelmet.setExchange(treeBranch);
        wrongArmor.setExchange(treeBranch);
        wrongShield.setExchange(treeBranch);

        items.add(wrongSword);
        items.add(wrongBow);
        items.add(wrongAxe);
        items.add(wrongSpear);
        items.add(wrongBlade);


        I.addItem(apple);
        I.addItem(treeBranch);

        MainActivity.predItem1 = I.getItem(I.ITEM1);
        MainActivity.predItem2 = I.getItem(I.ITEM2);


        /*---------------------------------------------------------------店シーン---------------------------------------------------------*/

        shop[0] = new Scene("交換", "→", "やめる", "");
        shop[0].setChangeStatus(false);
        shop[0].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                shop[0].setPlayImg(clerk.getImg());
                shop[0].setConsoleName(clerk.getName());

                youHave = clerk.getItem(0);
                IHave = clerk.getItem(0).getExchange();
                shop[0].setConsoleText(youHave.getName() + "はいかがかね？\n" + IHave.getName() + "と交換だよ。");
                shop[5].setNextScene(shop[0]);
            }
        });
        shop[0].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return shop[5];
                    case Scene.RIGHT:
                        return shop[1];
                    case Scene.DOWN:
                        return shop[0].getNextScene();
                    case Scene.ACTION:
                        return shop[6];
                }
                return null;
            }
        });

        shop[1] = new Scene("交換", "→", "やめる", "←");
        shop[1].setChangeStatus(false);
        shop[1].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                shop[1].setPlayImg(clerk.getImg());
                shop[1].setConsoleName(clerk.getName());

                youHave = clerk.getItem(1);
                IHave = clerk.getItem(1).getExchange();
                shop[1].setConsoleText(youHave.getName() + "はいかがかね？\n" + IHave.getName() + "と交換だよ。");
                shop[5].setNextScene(shop[1]);
            }
        });
        shop[1].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return shop[5];
                    case Scene.LEFT:
                        return shop[0];
                    case Scene.RIGHT:
                        return shop[2];
                    case Scene.DOWN:
                        return shop[0].getNextScene();
                    case Scene.ACTION:
                        return shop[6];
                }
                return null;
            }
        });

        shop[2] = new Scene("交換", "→", "やめる", "←");
        shop[2].setChangeStatus(false);
        shop[2].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                shop[2].setPlayImg(clerk.getImg());
                shop[2].setConsoleName(clerk.getName());

                youHave = clerk.getItem(2);
                IHave = clerk.getItem(2).getExchange();
                shop[2].setConsoleText(youHave.getName() + "はいかがかね？\n" + IHave.getName() + "と交換だよ。");
                shop[5].setNextScene(shop[2]);
            }
        });
        shop[2].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return shop[5];
                    case Scene.LEFT:
                        return shop[1];
                    case Scene.RIGHT:
                        return shop[3];
                    case Scene.DOWN:
                        return shop[0].getNextScene();
                    case Scene.ACTION:
                        return shop[6];
                }
                return null;
            }
        });
        shop[3] = new Scene("交換", "→", "やめる", "←");
        shop[3].setChangeStatus(false);
        shop[3].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                shop[3].setPlayImg(clerk.getImg());
                shop[3].setConsoleName(clerk.getName());

                youHave = clerk.getItem(3);
                IHave = clerk.getItem(3).getExchange();
                shop[3].setConsoleText(youHave.getName() + "はいかがかね？\n" + IHave.getName() + "と交換だよ。");
                shop[5].setNextScene(shop[3]);
            }
        });
        shop[3].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return shop[5];
                    case Scene.LEFT:
                        return shop[2];
                    case Scene.RIGHT:
                        return shop[4];
                    case Scene.DOWN:
                        return shop[0].getNextScene();
                    case Scene.ACTION:
                        return shop[6];
                }
                return null;
            }
        });
        shop[4] = new Scene("交換", "", "やめる", "←");
        shop[4].setChangeStatus(false);
        shop[4].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                shop[4].setPlayImg(clerk.getImg());
                shop[4].setConsoleName(clerk.getName());

                youHave = clerk.getItem(4);
                IHave = clerk.getItem(4).getExchange();
                shop[4].setConsoleText(youHave.getName() + "はいかがかね？\n" + IHave.getName() + "と交換だよ。");
                shop[5].setNextScene(shop[4]);
            }
        });
        shop[4].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return shop[5];
                    case Scene.LEFT:
                        return shop[3];
                    case Scene.DOWN:
                        return shop[0].getNextScene();
                    case Scene.ACTION:
                        return shop[6];
                }
                return null;
            }
        });

        shop[5] = new Scene();
        shop[5].setChangeStatus(false);
        shop[5].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                MainActivity.setButtonUnable();
                shop[5].setPlayImg(clerk.getImg());
                shop[5].setConsoleName(clerk.getName());

                if (I.findItem(IHave)) {
                    shop[5].setConsoleText("毎度あり！\n\n" + youHave.getName() + "を手に入れた。");
                    MainActivity.setConsole(shop[5]);
                    I.exchange(IHave, youHave);
                    MainActivity.updateStatus(shop[5]);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            shop[5].setConsoleText("");
                            MainActivity.next(dir);
                        }
                    }, 2000);
                } else {
                    shop[5].setConsoleText("冷やかしかい");
                    MainActivity.setButtonUnable();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.next(dir);
                        }
                    }, 1200);
                }

            }
        });
        shop[5].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return shop[5].getNextScene();
            }
        });


        shop[6] = new Scene("盗む", "脅迫", "やめる", "交渉");
        shop[6].setChangeStatus(false);
        shop[6].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                shop[6].setConsoleText(shop[5].getNextScene().getConsoleText());
                shop[6].setConsoleName(shop[5].getNextScene().getConsoleName());
                shop[6].setPlayImg(shop[5].getNextScene().getPlayImg());
            }
        });
        shop[6].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return shop[7];
                    case Scene.RIGHT:
                        return shop[8];
                    case Scene.LEFT:
                        return shop[10];
                    case Scene.DOWN:
                        return shop[5].getNextScene();
                }
                return null;
            }
        });

        shop[7] = new Scene();
        shop[7].setChangeStatus(false);
        shop[7].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                MainActivity.setButtonUnable();
                shop[7].setConsoleName(shop[5].getNextScene().getConsoleName());
                shop[7].setPlayImg(shop[5].getNextScene().getPlayImg());
                if (I.getItemSize() >= I.getMaxItemSize()) {
                    Item item = I.getItem(I.ITEM1);
                    I.exchange(youHave, I.ITEM1);
                    shop[7].setConsoleText("どろぼーう！！！\n\n" + youHave.getName() + "を手に入れた。\nはずみで" + item.getName() + "を落とした。");
                } else {
                    I.addItem(youHave);
                    shop[7].setConsoleText("どろぼーう！！！\n\n" + youHave.getName() + "を手に入れた。");
                }
                I.Karma += 10;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.next(dir);
                    }
                }, 2000);
            }
        });
        shop[7].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return shop[0].getNextScene();
            }
        });

        shop[8] = new Scene();
        shop[8].setChangeStatus(false);
        shop[8].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                shop[8].setConsoleName(shop[5].getNextScene().getConsoleName());
                shop[8].setPlayImg(shop[5].getNextScene().getPlayImg());
                if (clerk.getPower() < I.Power) {
                    shop[8].setSceneText("もらう", "", "やめる", "");
                    shop[8].setConsoleText("ひえー！差し上げるので、\nお助けをーーーーー！！！");
                    shop[8].setNext(new NextListener() {
                        @Override
                        public Scene next(int dir) {
                            switch (dir) {
                                case Scene.UP:
                                    return shop[9];
                                case Scene.DOWN:
                                    return shop[5].getNextScene();
                            }
                            return null;
                        }
                    });
                } else {
                    MainActivity.setButtonUnable();
                    shop[8].setConsoleText("さっさと出ていけーーー！！！\n\n手配値が上がった。");
                    I.Karma += 2;

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.next(dir);
                        }
                    }, 1200);
                    shop[8].setNext(new NextListener() {
                        @Override
                        public Scene next(int dir) {
                            return shop[0].getNextScene();
                        }
                    });
                }
            }
        });

        shop[9] = new Scene();
        shop[9].setChangeStatus(false);
        shop[9].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                MainActivity.setButtonUnable();
                shop[9].setConsoleName(shop[5].getNextScene().getConsoleName());
                shop[9].setPlayImg(shop[5].getNextScene().getPlayImg());

                final String str;
                if (shop[9].getPrevScene().equals(shop[8])) {
                    str = "何を捨てますか？";
                } else {
                    str = "どれと交換しますか？";
                }
                if (I.getItemSize() >= I.getMaxItemSize() || shop[9].getPrevScene().equals(shop[10])) {
                    MainActivity.setDialog(context, str);
                    MainActivity.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if (shop[9].getPrevScene().equals(shop[8])) {
                                shop[9].setConsoleText("まっ毎度あり〜・・・\n\n" + youHave.getName() + "を手に入れた。\n手配値が上がった。");
                                I.Karma += 10;
                            } else {
                                shop[9].setConsoleText("毎度あり！\n\n" + youHave.getName() + "を手に入れた。");
                            }
                            MainActivity.setConsole(shop[9]);
                            I.exchange(IHave, youHave);
                            MainActivity.updateStatus(shop[9]);

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    shop[9].setConsoleText("");
                                    MainActivity.next(dir);
                                }
                            }, 2000);
                        }
                    });
                } else {
                    if (shop[9].getPrevScene().equals(shop[8])) {
                        shop[9].setConsoleText("まっ毎度あり〜・・・\n\n" + youHave.getName() + "を手に入れた。\n手配値が上がった。");
                        I.Karma += 10;
                    } else {
                        shop[9].setConsoleText("毎度あり！\n\n" + youHave.getName() + "を手に入れた。");
                    }
                    MainActivity.setConsole(shop[9]);
                    I.addItem(youHave);
                    MainActivity.updateStatus(shop[9]);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            shop[9].setConsoleText("");
                            MainActivity.next(dir);
                        }
                    }, 2000);
                }
            }
        });
        shop[9].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return shop[5].getNextScene();
            }
        });


        shop[10] = new Scene();
        shop[10].setChangeStatus(false);
        shop[10].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                shop[10].setConsoleName(shop[5].getNextScene().getConsoleName());
                shop[10].setPlayImg(shop[5].getNextScene().getPlayImg());
                if (clerk.getIntelligence() < I.Intelligence) {
                    shop[10].setSceneText("交換", "", "やめる", "");
                    shop[10].setConsoleText("それなら仕方ない、\nあんたが持ってるものと交換でいいよ。");
                    shop[10].setNext(new NextListener() {
                        @Override
                        public Scene next(int dir) {
                            switch (dir) {
                                case Scene.UP:
                                    return shop[9];
                                case Scene.DOWN:
                                    return shop[5].getNextScene();
                            }
                            return null;
                        }
                    });
                } else {
                    MainActivity.setButtonUnable();
                    shop[10].setConsoleText("悪いが" + IHave.getName() + "としか交換できない。");

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.next(dir);
                        }
                    }, 1200);
                    shop[10].setNext(new NextListener() {
                        @Override
                        public Scene next(int dir) {
                            return shop[6];
                        }
                    });
                }
            }
        });


        /*---------------------------------------------------------------戦闘シーン---------------------------------------------------------*/

        battle[0] = new Scene("攻撃", "突進", "逃げる", "ガード");
        battle[0].setChangeStatus(false);
        battle[0].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                enemy = new Player("トカ聖兵", R.drawable.heisi);
                enemy.addItem(wrongSword);
                battle[0].setConsoleText(enemy.getName() + "が現れた");
                battle[0].setPlayImg(enemy.getImg());
                enemy.setStatus(100, 5, 30, 10, 10);
                wrongSword = enemy.getRandomItem();
            }
        });
        battle[0].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                    case Scene.LEFT:
                    case Scene.RIGHT:
                        return battle[4];
                    case Scene.DOWN:
                        if (Math.floor(Math.random() * 2) == 1) {
                            return battle[2];
                        } else {
                            return battle[3];
                        }
                }
                return null;
            }
        });
        battle[1] = new Scene("攻撃", "突進", "逃げる", "ガード");
        battle[1].setConsoleText("どうする");
        battle[1].setChangeStatus(false);
        battle[1].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                battle[1].setPlayImg(enemy.getImg());
            }
        });
        battle[1].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                    case Scene.LEFT:
                    case Scene.RIGHT:
                        return battle[4];
                    case Scene.DOWN:
                        if (Math.floor(Math.random() * 2) == 1) {
                            return battle[2];
                        } else {
                            return battle[3];
                        }
                }
                return null;
            }
        });
        battle[2] = new Scene();
        battle[2].setConsoleText("逃げ切れた。");
        battle[2].setChangeStatus(false);
        battle[2].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                battle[2].setPlayImg(enemy.getImg());
                MainActivity.setButtonUnable();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.next(dir);
                    }
                }, 2400);
            }
        });
        battle[2].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return battle[0].getNextScene();
            }
        });

        battle[3] = new Scene();
        battle[3].setChangeStatus(false);
        battle[3].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                battle[3].setPlayImg(enemy.getImg());
                battle[3].setConsoleText("逃げられない。\n");
                MainActivity.setButtonUnable();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        battle[3].setConsoleText(battle[3].getConsoleText() + enemy.getName() + "の攻撃！\n\n");
                        MainActivity.setConsole(battle[3]);
                    }
                }, 1000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        battle[3].setConsoleText(battle[3].getConsoleText() + "あなたはダメージを受けた。");
                        MainActivity.setConsole(battle[3]);
                        I.HP -= setDamage(enemy.getPower(), I.Defense);
                        if (I.HP < 0) {
                            I.HP = 0;
                        }
                        MainActivity.setPlayImg(R.drawable.damage);
                        MainActivity.setHP(I.HP);
                    }
                }, 2000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.setPlayImg(battle[3]);
                    }
                }, 2050);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.next(dir);
                    }
                }, 3200);
            }
        });
        battle[3].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                if (I.HP <= 0) {
                    return dead;
                } else {
                    return battle[1];
                }
            }
        });

        battle[4] = new Scene();
        battle[4].setChangeStatus(false);
        battle[4].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                battle[4].setPlayImg(enemy.getImg());

                final int intelligenceDiff = I.Intelligence - enemy.getIntelligence();

                int myHandmoto = 0;

                if (dir == Scene.LEFT) {//自分の手を決める
                    myHandmoto = Player.GU;
                } else if (dir == Scene.UP) {
                    myHandmoto = Player.CHOKI;
                } else if (dir == Scene.RIGHT) {
                    myHandmoto = Player.PA;
                }
                MainActivity.setButtonUnable();
                final int myHand = myHandmoto;

                int enemyHandmoto = 0;
                if ((int) Math.floor(Math.random() * 100) <= intelligenceDiff) {//知力によって相手の手が読めるかどうか
                    switch (myHand) {
                        case Player.GU:
                            enemyHandmoto = Player.CHOKI;
                            break;
                        case Player.CHOKI:
                            enemyHandmoto = Player.PA;
                            break;
                        case Player.PA:
                            enemyHandmoto = Player.GU;
                            break;
                    }
                } else {
                    enemyHandmoto = enemy.getAppearance(0);
                }
                final int enemyHand = enemyHandmoto;//相手の手を決める

                String text = "";
                int imgmoto = 0;

                switch (myHand) {
                    case Player.GU:
                        battle[4].setConsoleText("ガード！");
                        switch (enemyHand) {
                            case Player.GU:
                                break;
                            case Player.CHOKI:
                                enemy.decHP(setDamage(I.Power, enemy.getDefense()));
                                text = enemy.getName() + "にダメージ。";
                                imgmoto = R.drawable.attack;
                                break;
                            case Player.PA:
                                I.HP -= setDamage(enemy.getPower(), I.Defense);
                                if (I.HP <= 0) {
                                    I.Stamina = 0;
                                    I.HP = 0;
                                }
                                text = "あなたはダメージを受けた。";
                                imgmoto = R.drawable.damage;
                                break;
                        }
                        break;
                    case Player.CHOKI:
                        battle[4].setConsoleText("攻撃！");
                        switch (enemyHand) {
                            case Player.GU:
                                I.HP -= setDamage(enemy.getPower(), I.Defense);
                                if (I.HP <= 0) {
                                    I.Stamina = 0;
                                    I.HP = 0;
                                }
                                text = "あなたはダメージを受けた。";
                                imgmoto = R.drawable.damage;
                                break;
                            case Player.CHOKI:
                                I.Stamina--;
                                if (I.Stamina <= 0) {
                                    I.HP /= 2;
                                    if (I.HP > 0) {
                                        I.Stamina = 20;
                                    }
                                }
                                break;
                            case Player.PA:
                                enemy.decHP(setDamage(I.Power, enemy.getDefense()));
                                text = enemy.getName() + "にダメージ。";
                                imgmoto = R.drawable.attack;
                                break;
                        }
                        break;
                    case Player.PA:
                        battle[4].setConsoleText("突進！");

                        switch (enemyHand) {
                            case Player.GU:
                                enemy.decHP(setDamage(I.Power, enemy.getDefense()));
                                text = enemy.getName() + "にダメージ。";
                                imgmoto = R.drawable.attack;
                                break;
                            case Player.CHOKI:
                                I.HP -= setDamage(enemy.getPower(), I.Defense);
                                if (I.HP <= 0) {
                                    I.Stamina = 0;
                                    I.HP = 0;
                                }
                                text = "あなたはダメージを受けた。";
                                imgmoto = R.drawable.damage;
                                break;
                            case Player.PA:
                                I.Stamina--;
                                if (I.Stamina <= 0) {
                                    I.HP /= 2;
                                    if (I.HP > 0) {
                                        I.Stamina = 20;
                                    }
                                }
                                break;
                        }
                        break;
                }

                final String result = text;
                final int img = imgmoto;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (enemyHand) {
                            case Player.GU:
                                battle[4].setConsoleText(battle[4].getConsoleText() + "\n" + enemy.getName() + "のガード！");
                                break;
                            case Player.CHOKI:
                                battle[4].setConsoleText(battle[4].getConsoleText() + "\n" + enemy.getName() + "の攻撃！");
                                break;
                            case Player.PA:
                                battle[4].setConsoleText(battle[4].getConsoleText() + "\n" + enemy.getName() + "の突進！");
                                break;
                        }
                        MainActivity.setConsole(battle[4]);
                    }
                }, 1200);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (myHand == enemyHand) {
                            battle[4].setConsoleText(battle[4].getConsoleText() + "\n\n体力を消耗している！");
                            MainActivity.setConsole(battle[4]);
                            MainActivity.setStamina(I.Stamina);
                        } else {
                            battle[4].setConsoleText(battle[4].getConsoleText() + "\n\n" + result);
                            MainActivity.setConsole(battle[4]);
                            MainActivity.setPlayImg(img);
                        }
                        MainActivity.setHP(I.HP);
                    }
                }, 2400);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.setPlayImg(battle[4]);
                    }
                }, 2450);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.next(dir);
                    }
                }, 3600);
            }
        });

        battle[4].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                if (enemy.getHP() <= 0) {
                    return battle[5];
                } else if (I.HP <= 0) { //戦死
                    return dead;
                } else {
                    return battle[1];
                }
            }
        });

        battle[5] = new Scene("身ぐるみを剥ぐ", "", "やめる", "");
        battle[5].setChangeStatus(false);
        battle[5].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                battle[5].setConsoleText(enemy.getName() + "を倒した");
                battle[5].setPlayImg(R.drawable.attack);
            }
        });
        battle[5].setFinish(new FinishListener() {
            @Override
            public void finish(int dir) {
                switch (dir) {
                    case Scene.DOWN:
                        I.maxHP++;
                        I.Power++;
                        I.Intelligence++;
                        break;
                }
            }
        });

        battle[5].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        /*if (Math.floor(Math.random() * 100) <= I.Intelligence) {
                            return battle[6];
                        } else {
                            return battle[7];
                        }*/
                        return battle[7];
                    case Scene.DOWN:
                        return battle[0].getNextScene();
                }
                return null;
            }
        });

        battle[6] = new Scene();
        battle[6].setChangeStatus(false);
        battle[6].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                battle[6].setConsoleText(enemy.getName() + "が息を吹き返した。\n");
                battle[6].setPlayImg(enemy.getImg());
                MainActivity.setButtonUnable();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        battle[6].setConsoleText(battle[6].getConsoleText() + enemy.getName() + "の攻撃！\n\n");
                        MainActivity.setConsole(battle[6]);
                    }
                }, 1000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        battle[6].setConsoleText(battle[6].getConsoleText() + "あなたはダメージを受けた。");
                        MainActivity.setConsole(battle[6]);
                        I.HP -= setDamage(enemy.getPower(), I.Defense);
                        if (I.HP < 0) {
                            I.HP = 0;
                        }
                        MainActivity.setPlayImg(R.drawable.damage);
                        MainActivity.setHP(I.HP);
                    }
                }, 2000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.setPlayImg(battle[6]);
                    }
                }, 2050);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.next(dir);
                    }
                }, 3200);
            }
        });
        battle[6].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                if (I.HP <= 0) { //戦死
                    return dead;
                } else {
                    return battle[1];
                }
            }
        });


        battle[7] = new Scene();
        battle[7].setChangeStatus(false);
        battle[7].setPlayImg(R.drawable.attack);
        battle[7].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                battle[7].setConsoleText(wrongSword.getName() + "を持っていた。\n何と交換しますか？");
                battle[7].setSceneText("", I.getItem(I.ITEM2).getName(), "やめる", I.getItem(I.ITEM1).getName());
            }
        });
        battle[7].setFinish(new FinishListener() {
            @Override
            public void finish(int dir) {
                switch (dir) {
                    case Scene.DOWN:
                        I.maxHP++;
                        I.Power++;
                        I.Intelligence++;
                }
            }
        });
        battle[7].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.DOWN:
                        return battle[0].getNextScene();
                    case Scene.LEFT:
                    case Scene.RIGHT:
                        return battle[8];
                }
                return null;
            }
        });

        battle[8] = new Scene();
        battle[8].setChangeStatus(false);
        battle[8].setPlayImg(R.drawable.attack);
        battle[8].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                battle[8].setConsoleText(wrongSword.getName() + "を手に入れた");
                MainActivity.setButtonUnable();

                switch (dir) {
                    case Scene.LEFT:
                        I.exchange(wrongSword, I.ITEM1);
                        break;
                    case Scene.RIGHT:
                        I.exchange(wrongSword, I.ITEM2);
                        break;
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.next(dir);
                    }
                }, 2000);
            }
        });
        battle[8].setFinish(new FinishListener() {
            @Override
            public void finish(int dir) {
                I.maxHP++;
                I.Power++;
                I.Intelligence++;
            }
        });
        battle[8].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return battle[0].getNextScene();
            }
        });

        /*---------------------------------------------------------------------戦闘シーン終わり----------------------------------------------------------------------*/

        dead = new Scene();
        dead.setConsoleText("あなたは死んだ");
        dead.setInit(new InitListener() {
            @Override
            public void init(int dir) {

            }
        });
        dead.setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return null;
            }
        });

        coffeeBreak = new Scene("探索", "睡眠", "やめる", "食事");                        //その場アクション Scene毎にcoffeeBreak.setNextSceneで戻るSceneを設定する
        coffeeBreak.setConsoleText("何をする？");
        coffeeBreak.setChangeStatus(false);
        coffeeBreak.setInit(new InitListener() {
            @Override
            public void init(int dir) {
                coffeeBreak.setPlayImg(coffeeBreak.getNextScene().getPlayImg());
            }
        });
        coffeeBreak.setFinish(new FinishListener() {
            @Override
            public void finish(int dir) {
                if (dir == Scene.DOWN) {
                    MainActivity.updateTime(Calendar.MINUTE, -30);
                    MainActivity.updateHealth(1, 0);
                }
            }
        });
        coffeeBreak.setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return search[0];
                    case Scene.RIGHT:
                        return sleep;
                    case Scene.DOWN:
                        return coffeeBreak.getNextScene();
                    case Scene.LEFT:
                        if (I.getItemSize() != 0)
                            return eat;
                }
                return null;
            }
        });

        search[0] = new Scene();
        search[0].setChangeStatus(false);
        search[0].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                MainActivity.setButtonUnable();
                search[0].setConsoleText("付近を探索中・・・");
                search[0].setPlayImg(R.drawable.search1);
                MainActivity.setPlayImg(search[0]);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        search[0].setPlayImg(R.drawable.search2);
                        MainActivity.setPlayImg(search[0]);
                        if (Math.floor(Math.random() * 20) == 10) {
                            youHave = getLowLevelItem(I.getLevel());
                            if (I.getItemSize() >= I.getMaxItemSize()) {
                                search[0].setConsoleText(youHave.getName() + "を見つけた。");
                                search[0].setSceneText("交換", "", "やめる", "");
                                MainActivity.setButton(search[0]);
                                MainActivity.setText(search[0]);
                                search[0].setSceneText("", "", "", "");
                            } else {
                                search[0].setConsoleText(youHave.getName() + "を手に入れた。");
                                MainActivity.setConsole(search[0]);
                                I.addItem(youHave);
                                MainActivity.updateStatus(search[0]);

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        MainActivity.next(-1);
                                    }
                                }, 3600);
                            }
                        } else {
                            search[0].setConsoleText("何もなかった。");
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MainActivity.next(-1);
                                }
                            }, 3600);
                        }
                        MainActivity.setConsole(search[0]);
                        MainActivity.updateTime(Calendar.HOUR, 1);
                        MainActivity.updateHealth(-1, 0);
                        MainActivity.setDateText();
                    }
                }, 2400);
            }
        });
        search[0].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return search[1];
                    case Scene.RIGHT:
                    case Scene.LEFT:
                    case Scene.ACTION:
                        return null;
                }
                return coffeeBreak;
            }
        });

        search[1] = new Scene();
        search[1].setPlayImg(R.drawable.search2);
        search[1].setChangeStatus(false);
        search[1].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                MainActivity.setButtonUnable();
                MainActivity.setDialog(context, "何と交換しますか？");
                MainActivity.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        search[1].setConsoleText(youHave.getName() + "を手に入れた。");
                        MainActivity.setConsole(search[1]);
                        I.exchange(IHave, youHave);
                        MainActivity.updateStatus(search[1]);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                search[1].setConsoleText("");
                                MainActivity.next(dir);
                            }
                        }, 2000);
                    }
                });
            }
        });
        search[1].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return coffeeBreak;
            }
        });

        sleep = new Scene();
        sleep.setChangeStatus(false);
        sleep.setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                final int prevHP = I.HP;

                MainActivity.setButtonUnable();
                sleep.setConsoleText("あなたは眠った。\n\n");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sleep.setConsoleText(sleep.getConsoleText() + "6時間後・・・");
                        MainActivity.setConsole(sleep);
                        MainActivity.updateTime(Calendar.HOUR, 6);
                        MainActivity.setDateText();
                    }
                }, 2400);

                if (sleep.getPrevScene().equals(coffeeBreak)) {                                        //coffeeBreakからsleep
                    final int rand = (int) Math.floor(Math.random() * 100);
                    if (rand == 13) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                I.HP = 0;
                                MainActivity.next(dir);
                            }
                        }, 4800);
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.updateHealth(15, 10);

                                final String str;
                                if (rand % 3 == 0 && I.getItemSize() >= 1) {
                                    if (I.HP != prevHP)
                                        str = "持ち物を盗まれた！\n\nスタミナが回復した。\n体力が少し回復した。";
                                    else
                                        str = "持ち物を盗まれた！\n\nスタミナが回復した。";
                                    if (I.getItemSize() >= 2)
                                        I.removeItem(I.ITEM2);
                                    I.removeItem(I.ITEM1);
                                } else {
                                    if (I.HP != prevHP)
                                        str = "スッキリ目覚めた。\n\nスタミナが回復した。\n体力が少し回復した。";
                                    else
                                        str = "スッキリ目覚めた。\n\nスタミナが回復した。";
                                }
                                sleep.setConsoleText(str);
                                MainActivity.setConsole(sleep);


                                MainActivity.updateStatus(sleep);
                            }
                        }, 4800);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.next(dir);
                            }
                        }, 7200);
                    }
                } else {                                                                              //ベッドでsleep
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sleep.setConsoleText("スッキリ目覚めた。\n\nスタミナが回復した。\n体力が回復した。");
                            MainActivity.setConsole(sleep);
                            MainActivity.updateHealth(30, 0);
                            MainActivity.updateStatus(sleep);
                        }
                    }, 4800);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            I.inn = false;
                            MainActivity.next(dir);
                        }
                    }, 7200);
                }
            }
        });
        sleep.setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                if (I.HP <= 0) {
                    return dead;
                } else if (sleep.getPrevScene().equals(coffeeBreak)) {
                    return coffeeBreak;
                } else {
                    return sleep.getPrevScene();
                }
            }
        });

        eat = new Scene();
        eat.setChangeStatus(false);
        eat.setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                MainActivity.setButtonUnable();
                eat.setPlayImg(coffeeBreak.getNextScene().getPlayImg());
                MainActivity.setDialog(context, "何を食べますか？");
                MainActivity.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        final int time;
                        if (IHave.getKind() == Item.FOOD) {
                            eat.setConsoleText(IHave.getName() + "を食べた。\nスタミナが" + IHave.getAdd() + "回復した。");
                            MainActivity.updateHealth(IHave.getAdd(), 10);
                            I.removeItem(IHave);
                            time = 2400;
                        } else {
                            eat.setConsoleText("それは食べ物ではありません。");
                            time = 1200;
                        }
                        MainActivity.setConsole(eat);


                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                eat.setConsoleText("");
                                MainActivity.next(dir);
                            }
                        }, time);
                    }
                });
            }
        });
        eat.setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return coffeeBreak;
            }
        });


        eatIn[0] = new Scene("食事", "", "やめる", "");
        eatIn[0].setChangeStatus(false);
        eatIn[0].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                if (I.getItemSize() <= 0) {
                    eatIn[0].setConsoleText("何も持ってない人には食事を出せないよ。");
                } else {
                    eatIn[0].setConsoleText("お食事になさいますか？");
                }
            }
        });
        eatIn[0].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        if (I.getItemSize() <= 0)
                            return null;
                        else
                            return eatIn[1];
                    case Scene.DOWN:
                        return eatIn[0].getPrevScene();
                }
                return null;
            }
        });

        eatIn[1] = new Scene();
        eatIn[1].setChangeStatus(false);
        eatIn[1].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                MainActivity.setButtonUnable();
                eatIn[1].setPlayImg(eatIn[0].getPlayImg());
                MainActivity.setDialog(context, "どれと交換しますか？");
                MainActivity.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        eatIn[1].setConsoleText("食事をした。\n\nスタミナが回復した。\n体力が回復した。");
                        MainActivity.updateHealth(25, 20);
                        I.removeItem(IHave);
                        MainActivity.setConsole(eatIn[1]);


                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                eatIn[1].setConsoleText("");
                                MainActivity.next(dir);
                            }
                        }, 2400);
                    }
                });
            }
        });
        eatIn[1].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return eatIn[0].getPrevScene();
            }
        });


        meziha_Lynch[0] = new Scene("次へ", "", "", "");
        meziha_Lynch[0].setConsoleName("");
        meziha_Lynch[0].setChangeStatus(false);
        meziha_Lynch[0].setConsoleText("どんっ");

        meziha_Lynch[0].setPlayImg(R.drawable.heisi);
        meziha_Lynch[0].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha_Lynch[1];
                }
                return null;
            }
        });

        meziha_Lynch[1] = new Scene("次へ", "", "", "");
        meziha_Lynch[1].setChangeStatus(false);
        meziha_Lynch[1].setConsoleName("トカ聖兵");
        meziha_Lynch[1].setConsoleText("おい、ぶつかっておいて謝りもしないのか！");
        meziha_Lynch[1].setPlayImg(R.drawable.heisi);
        meziha_Lynch[1].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha_Lynch[2];
                }
                return null;
            }
        });

        meziha_Lynch[2] = new Scene("次へ", "", "", "");
        meziha_Lynch[2].setChangeStatus(false);
        meziha_Lynch[2].setConsoleName("トカ聖兵");
        meziha_Lynch[2].setConsoleText("このやろう！　ぼかっ");
        meziha_Lynch[2].setPlayImg(R.drawable.heisi);
        meziha_Lynch[2].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha_Lynch[3];
                }
                return null;
            }
        });

        meziha_Lynch[3] = new Scene("次へ", "", "", "");
        meziha_Lynch[3].setConsoleName("");
        meziha_Lynch[3].setChangeStatus(false);
        meziha_Lynch[3].setConsoleText("ダメージを受けた");
        meziha_Lynch[3].setPlayImg(R.drawable.heisi);
        meziha_Lynch[3].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                I.meziha_1 = true;
                I.HP -= 10;
                if (I.HP < 0) {
                    I.HP = 0;
                }
                MainActivity.setHP(I.HP);
            }
        });
        meziha_Lynch[3].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        if (I.HP <= 0) {
                            return dead;
                        } else {
                            return meziha[2];
                        }
                }
                return null;
            }
        });

        meziha[1] = new Scene("北", "東", "南", "西");
        meziha[1].setConsoleText("広場だ");
        meziha[1].setConsoleName("");
        meziha[1].setPlayImg(R.drawable.d);
        meziha[1].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                MainActivity.setActionButton(true);
            }
        });
        meziha[1].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        battle[0].setNextScene(meziha[1]);
                        return battle[0];
                    case Scene.RIGHT:
                        return meziha[11];
                    case Scene.DOWN:
                        if (Math.floor(Math.random() * 2) == 1 && !I.meziha_1) {
                            return meziha_Lynch[0];
                        } else {
                            return meziha[2];
                        }
                    case Scene.ACTION:
                        return null;
                }
                return null;
            }
        });

        meziha[2] = new Scene("北", "防具屋", "南", "武器屋");
        meziha[2].setConsoleText("町中だ");
        meziha[2].setPlayImg(R.drawable.a);
        meziha[2].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                if (!I.meziha_1) {
                    MainActivity.setActionButton(true);
                }
            }
        });
        meziha[2].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha[1];
                    case Scene.RIGHT:
                        return meziha_Armor;
                    case Scene.DOWN:
                        return meziha[3];
                    case Scene.LEFT:
                        return meziha_Weapon;
                    case Scene.ACTION:
                        if (!I.meziha_1) {
                            return meziha_Lynch[0];
                        } else {
                            coffeeBreak.setNextScene(meziha[2]);
                            return coffeeBreak;
                        }
                }
                return null;
            }
        });


        meziha[3] = new Scene("北", "東", "南", "西");
        meziha[3].setConsoleText("十字路だ");
        meziha[3].setPlayImg(R.drawable.c);
        meziha[3].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha[2];
                    case Scene.ACTION:
                        coffeeBreak.setNextScene(meziha[3]);
                        return coffeeBreak;
                }
                return null;
            }
        });

        meziha[11] = new Scene("東", "", "広場へ", "食品店");
        meziha[11].setConsoleText("後ろから広場の賑やかな音がする。");
        meziha[11].setPlayImg(R.drawable.meziha11);
        meziha[11].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha[12];
                    case Scene.DOWN:
                        return meziha[1];
                    case Scene.ACTION:
                        coffeeBreak.setNextScene(meziha[11]);
                        return coffeeBreak;
                }
                return null;
            }
        });

        meziha[12] = new Scene("東", "南", "西", "北");
        meziha[12].setConsoleText("十字路だ");
        meziha[12].setPlayImg(R.drawable.meziha12);
        meziha[12].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha[13];
                    case Scene.DOWN:
                        return meziha[11];
                    case Scene.ACTION:
                        coffeeBreak.setNextScene(meziha[12]);
                        return coffeeBreak;
                }
                return null;
            }
        });
        meziha[13] = new Scene("東", "", "西", "宿屋");
        meziha[13].setConsoleText("宿屋がある");
        meziha[13].setPlayImg(R.drawable.meziha13);
        meziha[13].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                    case Scene.LEFT:
                        return meziha_Inn[0];
                    case Scene.DOWN:
                        return meziha[12];
                    case Scene.ACTION:
                        coffeeBreak.setNextScene(meziha[13]);
                        return coffeeBreak;
                }
                return null;
            }
        });

        meziha_Armor = new Scene("防具屋", "", "戻る", "");
        meziha_Armor.setConsoleName("防具屋");
        meziha_Armor.setConsoleText("いらっしゃい。防具屋だよ〜");
        meziha_Armor.setPlayImg(R.drawable.f);
        meziha_Armor.setChangeStatus(false);
        meziha_Armor.setInit(new InitListener() {
            @Override
            public void init(int dir) {
                clerk = new Player("防具屋", R.drawable.f);
                clerk.addItem(wrongHelmet);
                clerk.addItem(wrongArmor);
                clerk.addItem(wrongShield);
                clerk.addItem(wrongSpear);
                clerk.addItem(wrongBlade);
                shop[0].setNextScene(meziha[2]);
            }
        });
        meziha_Armor.setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return shop[0];
                    case Scene.DOWN:
                        return meziha[2];
                }
                return null;
            }
        });


        meziha_Weapon = new Scene("武器屋", "", "戻る", "");
        meziha_Weapon.setConsoleName("武器屋");
        meziha_Weapon.setConsoleText("いらっしゃい。武器屋へようこそ！");
        meziha_Weapon.setPlayImg(R.drawable.bukiya);
        meziha_Weapon.setChangeStatus(false);
        meziha_Weapon.setInit(new InitListener() {
            @Override
            public void init(int dir) {
                clerk = new Player("武器屋", R.drawable.bukiya);
                clerk.setStatus(10, 10, 10, 10, 40);
                clerk.addItem(wrongSword);
                clerk.addItem(wrongBow);
                clerk.addItem(wrongAxe);
                clerk.addItem(wrongSpear);
                clerk.addItem(wrongBlade);
                shop[0].setNextScene(meziha[2]);
            }
        });
        meziha_Weapon.setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return shop[0];
                    case Scene.DOWN:
                        return meziha[2];
                }
                return null;
            }
        });

        meziha_Inn[0] = new Scene("カウンター", "テーブル", "外へ", "二階へ");
        meziha_Inn[0].setConsoleText("宿屋だ。");
        meziha_Inn[0].setChangeStatus(false);
        meziha_Inn[0].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                eatIn[0].setConsoleName("宿屋");
                eatIn[0].setPlayImg(R.drawable.inn);
            }
        });
        meziha_Inn[0].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha_Inn[1];
                    case Scene.LEFT:
                        return meziha_Inn[3];
                    case Scene.RIGHT:
                        return eatIn[0];
                    case Scene.DOWN:
                        return meziha[13];
                }
                return null;
            }
        });

        meziha_Inn[1] = new Scene("泊まる", "", "やめる", "");
        meziha_Inn[1].setConsoleName("宿屋");
        meziha_Inn[1].setChangeStatus(false);
        meziha_Inn[1].setPlayImg(R.drawable.inn);
        meziha_Inn[1].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                if (I.inn) {
                    meziha_Inn[1].setConsoleText("お部屋は二階です。\nどうぞごゆっくり。");
                } else {
                    if (I.getItemSize() == 0) {
                        meziha_Inn[1].setConsoleText("何も持ってない人は泊められないよ。");
                    } else {
                        meziha_Inn[1].setConsoleText("泊まってくかい？\nあんたの持ってるものと交換だよ。");
                    }
                }
            }
        });
        meziha_Inn[1].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        if (I.getItemSize() == 0) {
                            return null;
                        } else if (I.inn) {
                            return null;
                        } else {
                            return meziha_Inn[2];
                        }
                    case Scene.DOWN:
                        return meziha_Inn[0];
                }
                return null;
            }
        });
        meziha_Inn[2] = new Scene();
        meziha_Inn[2].setChangeStatus(false);
        meziha_Inn[2].setPlayImg(R.drawable.inn);
        meziha_Inn[2].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                MainActivity.setButtonUnable();
                MainActivity.setDialog(context, "どれと交換しますか？");
                MainActivity.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        I.removeItem(IHave);
                        meziha_Inn[2].setConsoleText("お部屋は二階です。\nどうぞごゆっくり。");
                        meziha_Inn[2].setConsoleName("宿屋");
                        MainActivity.setConsole(meziha_Inn[2]);
                        MainActivity.updateStatus(meziha_Inn[2]);
                        I.inn = true;

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.next(dir);
                            }
                        }, 2400);
                    }
                });
            }
        });
        meziha_Inn[2].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return meziha_Inn[0];
            }
        });

        meziha_Inn[3] = new Scene("101", "102", "一階へ", "");
        meziha_Inn[3].setConsoleText("二階には寝室があるようだ。");
        meziha_Inn[3].setChangeStatus(false);
        meziha_Inn[3].setInit(new InitListener() {
            @Override
            public void init(int dir) {

            }
        });
        meziha_Inn[3].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        if (I.inn)
                            return meziha_Inn[4];
                        else
                            return null;
                    case Scene.DOWN:
                        return meziha_Inn[0];
                }
                return null;
            }
        });
        meziha_Inn[4] = new Scene("", "ベッド", "部屋を出る", "");
        meziha_Inn[4].setConsoleText("101号室だ。");
        meziha_Inn[4].setChangeStatus(false);
        meziha_Inn[4].setInit(new InitListener() {
            @Override
            public void init(int dir) {

            }
        });
        meziha_Inn[4].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.RIGHT:
                        if (I.inn)
                            return sleep;
                        else
                            return null;
                    case Scene.DOWN:
                        return meziha_Inn[3];

                }
                return null;
            }
        });

    }

    int setDamage(int IPower, int youDefense) {
        int value = IPower - youDefense;
        if (value <= 0) {
            value = 1;
        }
        return value;
    }

    Item getSameLevelItem(int level) {
        while (true) {
            int index = new Random().nextInt(items.size());
            if (items.get(index).getLevel() == level) {
                return items.get(index);
            }
        }
    }

    Item getLowLevelItem(int level) {
        while (true) {
            int index = new Random().nextInt(items.size());
            if (items.get(index).getLevel() <= level) {
                return items.get(index);
            }
        }
    }
}

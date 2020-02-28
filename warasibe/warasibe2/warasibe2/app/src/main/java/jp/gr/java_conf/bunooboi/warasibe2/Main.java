package jp.gr.java_conf.bunooboi.warasibe2;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    Scene meziha[] = new Scene[100];

    Scene meziha_Weapon;
    Scene meziha_Armor;

    Scene meziha_Lynch[] = new Scene[4];

    Scene battle[] = new Scene[9];
    //battleは直前のfinishから設定。nextSceneも設定する
    Scene shop[] = new Scene[9];

    Scene dead;

    Handler handler = new Handler();

    Player enemy = new Player("トカ聖兵", R.drawable.heisi);
    Player clerk;


    Item wrongSword = new Item("ボロイ剣", R.drawable.horse, 1);
    Item wrongBow = new Item("ボロイ弓", R.drawable.a, 1);
    Item wrongAxe = new Item("ボロイ斧", R.drawable.b, 1);
    Item wrongSpear = new Item("ボロイ槍", R.drawable.heisi, 1);
    Item wrongBlade = new Item("ボロイ刀", R.drawable.bukiya, 1);


    Item treeBranch = new Item("木の枝", R.drawable.boss, 1);

    Item wrongHelmet = new Item("ボロイ兜", R.drawable.c, 1);
    Item wrongArmor = new Item("ボロイ鎧", R.drawable.d, 1);
    Item wrongShield = new Item("ボロイ盾", R.drawable.f, 1);

    Item IHave;
    Item youHave;

    static ArrayList<Item> items = new ArrayList<>();

    int time[] = new int[2];
    int date[] = new int[3];

    public Main() {
        I.HP = 4;
        I.Stamina = 5;
        I.Power = 100;
        I.Intelligence = 30;
        I.init();


        time[0] = 22;
        time[1] = 0;

        date[0] = 500;
        date[1] = 12;
        date[2] = 30;


        wrongSword.setExchange(treeBranch);
        wrongBow.setExchange(wrongBlade);
        wrongAxe.setExchange(wrongSpear);

        wrongHelmet.setExchange(treeBranch);
        wrongArmor.setExchange(treeBranch);
        wrongShield.setExchange(treeBranch);

        items.add(wrongSword);
        items.add(wrongBow);
        items.add(wrongAxe);
        items.add(wrongSpear);
        items.add(wrongBlade);


        I.addItem(wrongBlade);
        I.addItem(treeBranch);


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
                shop[5].setPrevScene(shop[0]);
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
                        return meziha[2];
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
                shop[5].setPrevScene(shop[1]);
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
                        return meziha[2];
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
                shop[5].setPrevScene(shop[2]);
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
                        return meziha[2];
                }
                return null;
            }
        });


        shop[5] = new Scene();
        shop[5].setChangeStatus(false);
        shop[5].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                shop[5].setPlayImg(clerk.getImg());
                shop[5].setConsoleName(clerk.getName());

                int time = 0;
                if (I.findItem(IHave)) {
                    I.exchange(IHave, youHave);
                    shop[5].setConsoleText("毎度あり！");
                    time = 2000;
                } else {
                    shop[5].setConsoleText("冷やかしかい");
                    time = 1200;
                }
                MainActivity.setButtonUnable();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.next(dir);
                    }
                }, time);
            }
        });
        shop[5].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return shop[5].getPrevScene();
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
                enemy.setStatus(4, 5, 10, 10);
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
        battle[2].setConsoleText("逃げ切れた");
        battle[2].setChangeStatus(false);
        battle[2].setInit(new InitListener() {
            @Override
            public void init(final int dir) {
                battle[2].setPlayImg(enemy.getImg());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.setButtonUnable();
                    }
                });
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.next(dir);
                            }
                        });

                    }
                }, 1500);
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
                        I.HP--;
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
                if (I.HP <= -1) {
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

                final int powerDiff = I.Power - enemy.getPower();
                final int intelligenceDiff = I.Intelligence - enemy.getIntelligence();

                int attackmoto = 1;
                if (powerDiff > 0) {
                    attackmoto = (int) Math.floor(Math.random() * powerDiff);
                    if (attackmoto <= 25) {
                        attackmoto = 1;
                    } else if (attackmoto > 25 && attackmoto <= 50) {
                        attackmoto = 2;
                    } else if (attackmoto > 50 && attackmoto <= 75) {
                        attackmoto = 3;
                    } else if (attackmoto > 75) {
                        attackmoto = 4;
                    }
                }

                final int attack = attackmoto;
                System.out.println(attack);

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
                                enemy.decHP(attack);
                                text = enemy.getName() + "にダメージ。";
                                imgmoto = R.drawable.attack;
                                break;
                            case Player.PA:
                                I.HP--;
                                if (I.HP <= -1) {
                                    I.Stamina = -1;
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
                                I.HP--;
                                if (I.HP <= -1) {
                                    I.Stamina = -1;
                                }
                                text = "あなたはダメージを受けた。";
                                imgmoto = R.drawable.damage;
                                break;
                            case Player.CHOKI:
                                I.Stamina--;
                                if (I.Stamina <= 0) {
                                    I.HP--;
                                    if (I.HP > -1) {
                                        I.Stamina = 20;
                                    }
                                }
                                break;
                            case Player.PA:
                                enemy.decHP(attack);
                                text = enemy.getName() + "にダメージ。";
                                imgmoto = R.drawable.attack;
                                break;
                        }
                        break;
                    case Player.PA:
                        battle[4].setConsoleText("突進！");

                        switch (enemyHand) {
                            case Player.GU:
                                enemy.decHP(attack);
                                text = enemy.getName() + "にダメージ。";
                                imgmoto = R.drawable.attack;
                                break;
                            case Player.CHOKI:
                                I.HP--;
                                if (I.HP <= -1) {
                                    I.Stamina = -1;
                                }
                                text = "あなたはダメージを受けた。";
                                imgmoto = R.drawable.damage;
                                break;
                            case Player.PA:
                                I.Stamina--;
                                if (I.Stamina <= 0) {
                                    I.HP--;
                                    if (I.HP > -1) {
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
                } else if (I.HP <= -1) { //戦死
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
                        I.HP--;
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
                if (I.HP <= -1) { //戦死
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

        dead = new Scene();
        dead.setConsoleText("あなたは死んだ");
        dead.setInit(new InitListener() {
            @Override
            public void init(int dir) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.setStamina(-1);
                    }
                });

            }
        });
        dead.setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                return null;
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
        meziha_Lynch[3].setConsoleText("ダメージを受けた");
        meziha_Lynch[3].setPlayImg(R.drawable.heisi);
        meziha_Lynch[3].setInit(new InitListener() {
            @Override
            public void init(int dir) {
                I.meziha_1 = true;
                I.HP--;
                MainActivity.setHP(I.HP);
            }
        });
        meziha_Lynch[3].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                switch (dir) {
                    case Scene.UP:
                        if (I.HP <= -1) {
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

            }
        });
        meziha[1].setNext(new NextListener() {
            @Override
            public Scene next(int dir) {
                if (dir == Scene.DOWN) {
                    if (Math.floor(Math.random() * 2) == 1 && !I.meziha_1) {
                        return meziha_Lynch[0];
                    } else {
                        battle[0].setNextScene(meziha[2]);
                        return battle[0];
                    }
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
                return meziha[2];
            }
        });

        meziha_Armor = new Scene("防具屋", "", "戻る", "");
        meziha_Armor.setConsoleName("防具屋");
        meziha_Armor.setConsoleText("いらっしゃい。防具屋だよ〜");
        meziha_Armor.setPlayImg(R.drawable.f);
        meziha_Armor.setInit(new InitListener() {
            @Override
            public void init(int dir) {
                clerk = new Player("防具屋", R.drawable.f);
                clerk.addItem(wrongHelmet);
                clerk.addItem(wrongArmor);
                clerk.addItem(wrongShield);
                clerk.addItem(wrongSpear);
                clerk.addItem(wrongBlade);
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
                clerk.addItem(wrongSword);
                clerk.addItem(wrongBow);
                clerk.addItem(wrongAxe);
                clerk.addItem(wrongSpear);
                clerk.addItem(wrongBlade);
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


    }

    Item getSameLevelItem(int level) {
        while (true) {
            int index = new Random().nextInt(items.size());
            if (items.get(index).getLevel() == level) {
                return items.get(index);
            }
        }
    }
}

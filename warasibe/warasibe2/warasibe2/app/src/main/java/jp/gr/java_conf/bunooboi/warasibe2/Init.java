package jp.gr.java_conf.bunooboi.warasibe2;

public class Init {

    Scene meziha[] = new Scene[100];

    Scene meziha_Weapon;
    Scene meziha_Armor;

    Scene meziha_Lynch[] = new Scene[4];

    public Init() {
        meziha_Lynch[0] = new Scene("次へ", "", "", "");
        meziha_Lynch[0].setConsoleName("");
        meziha_Lynch[0].setConsoleText("どんっ");
        meziha_Lynch[0].setPlayImg(R.drawable.boss);
        meziha_Lynch[0].setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha_Lynch[1];
                }
                return null;
            }
        });
        meziha_Lynch[1] = new Scene("次へ", "", "", "");
        meziha_Lynch[1].setConsoleName("トカ聖兵");
        meziha_Lynch[1].setConsoleText("おい、ぶつかっておいて謝りもしないのか！");
        meziha_Lynch[1].setPlayImg(R.drawable.boss);
        meziha_Lynch[1].setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha_Lynch[2];
                }
                return null;
            }
        });
        meziha_Lynch[2] = new Scene("次へ", "", "", "");
        meziha_Lynch[2].setConsoleName("トカ聖兵");
        meziha_Lynch[2].setConsoleText("このやろう！　ぼかっ");
        meziha_Lynch[2].setPlayImg(R.drawable.boss);
        meziha_Lynch[2].setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
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
        meziha_Lynch[3].setPlayImg(R.drawable.boss);
        meziha_Lynch[3].setInit(new InitListener() {
            @Override
            public void init() {
                I.meziha_1 = true;
            }
        });
        meziha_Lynch[3].setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
                switch (dir) {
                    case Scene.UP:
                        return meziha[2];
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
            public void init() {

            }
        });
        meziha[1].setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
                if (dir == Scene.DOWN) {
                    if (Math.floor(Math.random() * 2) == 1 && !I.meziha_1) {
                        return meziha_Lynch[0];
                    } else {
                        return meziha[2];
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
            public void init() {

            }
        });
        meziha[2].setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
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
        meziha[3].setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
                if (dir == Scene.UP) {
                    if (Math.floor(Math.random() * 2) == 1 && !I.meziha_1) {
                        return meziha_Lynch[0];
                    } else {
                        return meziha[2];
                    }
                }
                return null;
            }
        });

        meziha_Armor = new Scene("防具屋", "", "戻る", "");
        meziha_Armor.setConsoleName("防具屋");
        meziha_Armor.setConsoleText("いらっしゃい。防具屋だよ〜");
        meziha_Armor.setPlayImg(R.drawable.f);
        meziha_Armor.setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
                switch (dir) {
                    case Scene.DOWN:
                        return meziha[2];
                }
                return null;
            }
        });


        meziha_Weapon = new Scene("武器屋", "", "戻る", "");
        meziha_Weapon.setConsoleName("武器屋");
        meziha_Weapon.setConsoleText("いらっしゃい。武器屋へようこそ！");
        meziha_Weapon.setPlayImg(R.drawable.e);
        meziha_Weapon.setFinish(new FinishListener() {
            @Override
            public Scene finish(int dir) {
                switch (dir) {
                    case Scene.DOWN:
                        return meziha[2];
                }
                return null;
            }
        });


    }
}

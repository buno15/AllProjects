package jp.gr.java_conf.bunooboi.warasibe2;

public class Init {

    Scene meziha[] = new Scene[100];

    Scene meziha_Weapon;
    Scene meziha_Armor;

    Event meziha_Lynch;

    public Init() {

        meziha_Lynch = new Event(4);
        meziha_Lynch.getScene(0).setInit(new InitListener() {
            @Override
            public void init() {
                meziha_Lynch.start();
            }
        });
        meziha_Lynch.getScene(1).setScene("次へ", "", "", "");
        meziha_Lynch.getScene(1).setConsoleName("");
        meziha_Lynch.getScene(1).setConsoleText("どんっ");
        meziha_Lynch.getScene(1).setPlayImg(R.drawable.boss);
        meziha_Lynch.getScene(2).setScene("次へ", "", "", "");
        meziha_Lynch.getScene(2).setConsoleName("トカ聖兵");
        meziha_Lynch.getScene(2).setConsoleText("おい、ぶつかっておいて謝りもしないのか！");
        meziha_Lynch.getScene(2).setPlayImg(R.drawable.boss);
        meziha_Lynch.getScene(3).setScene("次へ", "", "", "");
        meziha_Lynch.getScene(3).setConsoleName("トカ聖兵");
        meziha_Lynch.getScene(3).setConsoleText("このやろう！　ぼかっ");
        meziha_Lynch.getScene(3).setPlayImg(R.drawable.boss);
        meziha_Lynch.getScene(4).setScene("次へ", "", "", "");
        meziha_Lynch.getScene(4).setConsoleName("");
        meziha_Lynch.getScene(4).setConsoleText("ダメージを受けた");
        meziha_Lynch.getScene(4).setPlayImg(R.drawable.boss);


        meziha[1] = new Scene("北", "東", "南", "西");
        meziha[1].setConsoleText("広場だ");
        meziha[1].setConsoleName("");
        meziha[1].setPlayImg(R.drawable.d);
        meziha[1].setInit(new InitListener() {
            @Override
            public void init() {

            }
        });

        meziha[2] = new Scene("北", "防具屋", "南", "武器屋");
        meziha[2].setConsoleText("町中だ");
        meziha[2].setPlayImg(R.drawable.a);
        meziha[2].setInit(new InitListener() {
            @Override
            public void init() {
                if (Math.floor(Math.random() * 2) == 1) {
                    if (!meziha_Lynch.done()) {
                        meziha_Lynch.start();
                    } else {
                        meziha_Lynch.preparation();
                    }
                }
            }
        });
        meziha[2].setEffect(new EffectListener() {
            @Override
            public Scene effect(int dir) {
                if (dir == Scene.ACTION) {
                    if (Math.floor(Math.random() * 2) == 1) {
                        return meziha_Lynch.getScene(0);
                    } else {
                        return
                    }
                }
            }
        });


        meziha[3] = new Scene("北", "東", "南", "西");
        meziha[3].setConsoleText("十字路だ");
        meziha[3].setPlayImg(R.drawable.c);

        meziha_Armor = new Scene("防具屋", "", "戻る", "");
        meziha_Armor.setConsoleName("防具屋");
        meziha_Armor.setConsoleText("いらっしゃい。防具屋だよ〜");
        meziha_Armor.setPlayImg(R.drawable.f);


        meziha_Weapon = new Scene("武器屋", "", "戻る", "");
        meziha_Weapon.setConsoleName("武器屋");
        meziha_Weapon.setConsoleText("いらっしゃい。武器屋へようこそ！");
        meziha_Weapon.setPlayImg(R.drawable.e);


    }

    public void connectScene() {
        meziha[1].setScene(meziha[1], meziha[1], meziha[2], meziha[1], null);
        meziha[2].setScene(meziha[1], meziha_Armor, meziha[3], meziha_Weapon, null);
        meziha[2].setScene(meziha_Lynch, Scene.ACTION);
        meziha[3].setScene(meziha[2], meziha[3], meziha[3], meziha[3], null);
        meziha_Weapon.setScene(meziha_Weapon, null, meziha[2], null, null);
        meziha_Armor.setScene(meziha_Armor, null, meziha[2], null, null);

        meziha_Lynch.connect();
        meziha_Lynch.connect(4, meziha[2], Scene.UP);
    }
}

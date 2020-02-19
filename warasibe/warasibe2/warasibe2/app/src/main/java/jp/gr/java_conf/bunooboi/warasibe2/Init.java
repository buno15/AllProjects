package jp.gr.java_conf.bunooboi.warasibe2;

import java.util.ArrayList;

public class Init {

    Scene meziha[] = new Scene[100];

    Scene meziha_Weapon;
    Scene meziha_Armor;

    Event meziha_Lynch;
    final Scene meziha_Lyn[] = new Scene[4];

    public Init() {

        meziha_Lynch = new Event();
        meziha_Lyn[0] = new Scene("次へ", "", "", "");
        meziha_Lyn[0].setConsoleName("");
        meziha_Lyn[0].setConsoleText("どんっ");
        meziha_Lyn[0].setPlayImg(R.drawable.boss);
        meziha_Lyn[1] = new Scene("次へ", "", "", "");
        meziha_Lyn[1].setConsoleName("トカ聖兵");
        meziha_Lyn[1].setConsoleText("おい、ぶつかっておいて謝りもしないのか！");
        meziha_Lyn[1].setPlayImg(R.drawable.boss);
        meziha_Lyn[2] = new Scene("次へ", "", "", "");
        meziha_Lyn[2].setConsoleName("トカ聖兵");
        meziha_Lyn[2].setConsoleText("このやろう！　ぼかっ");
        meziha_Lyn[2].setPlayImg(R.drawable.boss);
        meziha_Lyn[3] = new Scene("次へ", "", "", "");
        meziha_Lyn[3].setConsoleName("");
        meziha_Lyn[3].setConsoleText("ダメージを受けた");
        meziha_Lyn[3].setPlayImg(R.drawable.boss);
        meziha_Lyn[3].setEffect(new EffectListener() {
            @Override
            public void effect() {

            }
        });


        meziha[1] = new Scene("北", "東", "南", "西");
        meziha[1].setConsoleText("広場だ");
        meziha[1].setConsoleName("");
        meziha[1].setPlayImg(R.drawable.d);
        meziha[1].setEffect(new EffectListener() {
            @Override
            public void effect() {

            }
        });

        meziha[2] = new Scene("北", "防具屋", "南", "武器屋");
        meziha[2].setConsoleText("町中だ");
        meziha[2].setPlayImg(R.drawable.a);
        meziha[2].setEffect(new EffectListener() {
            @Override
            public void effect() {
                if (Math.floor(Math.random() * 2) == 1)
                    meziha_Lynch.startEvent();
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
        meziha_Weapon.setEffect(new EffectListener() {
            @Override
            public void effect() {
                meziha_Weapon.setConsoleText("いらっしゃい");
            }
        });


    }

    public void connectScene() {
        meziha[1].setScene(meziha[1], meziha[1], meziha[2], meziha[1], null);
        meziha[2].setScene(meziha[1], meziha_Armor, meziha[3], meziha_Weapon, meziha_Lyn[0]);
        meziha[3].setScene(meziha[2], meziha[3], meziha[3], meziha[3], null);
        meziha_Weapon.setScene(meziha_Weapon, null, meziha[2], null, null);
        meziha_Armor.setScene(meziha_Armor, null, meziha[2], null, null);

        meziha_Lynch.addScene(meziha_Lyn);
        for (int i = 0; i < meziha_Lyn.length; i++) {
            if (i < meziha_Lyn.length - 1)
                meziha_Lyn[i].setScene(meziha_Lyn[i + 1], null, null, null, null);
        }
        meziha_Lyn[3].setScene(meziha[2], null, null, null, null);
    }
}

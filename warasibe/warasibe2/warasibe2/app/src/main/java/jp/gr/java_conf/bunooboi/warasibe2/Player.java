package jp.gr.java_conf.bunooboi.warasibe2;

import java.util.ArrayList;

public class Player {
    String name;
    int HP;                                    // 体力
    int Stamina;                                //気力
    int Power;                                // 力
    int Intelligence;                            // 洞察力

    ArrayList<Item> item;

    public Player(String name) {
        this.name = name;

    }

    public void setStatus(int hp, int stamina, int power, int intelligence) {
        this.HP = hp;
        this.Stamina = stamina;
        this.Power = power;
        this.Intelligence = intelligence;
    }

    public int attack() {

    }

}

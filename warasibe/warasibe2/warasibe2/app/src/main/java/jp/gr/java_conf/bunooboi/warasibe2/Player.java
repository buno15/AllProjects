package jp.gr.java_conf.bunooboi.warasibe2;

import java.util.ArrayList;

public class Player {
    String name;
    int HP;                                    // 体力
    int Stamina;                                //気力
    int Power;                                // 力
    int Intelligence;                            // 洞察力

    int img;

    ArrayList<Item> item;

    public static final int GU = 1;
    public static final int CHOKI = 2;
    public static final int PA = 3;

    public Player(String name, int img) {
        this.name = name;
        this.img = img;
        item = new ArrayList<>();
    }

    public void setStatus(int hp, int stamina, int power, int intelligence) {
        this.HP = hp;
        this.Stamina = stamina;
        this.Power = power;
        this.Intelligence = intelligence;
    }

    public int getAppearance(int appear) {
        if (appear == 0) {
            int rand = (int) Math.floor(Math.random() * 3) + 1;
            if (rand == GU) {
                return GU;
            } else if (rand == CHOKI) {
                return CHOKI;
            } else if (rand == PA) {
                return PA;
            }
        }
        return appear;
    }

    public void addItem(Item item) {
        this.item.add(item);
    }

    public Item getRandomItem() {
        int rand = (int) Math.floor(Math.random() * item.size());
        return item.get(rand);
    }

}

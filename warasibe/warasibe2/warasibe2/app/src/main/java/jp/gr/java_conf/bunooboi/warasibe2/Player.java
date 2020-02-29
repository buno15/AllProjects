package jp.gr.java_conf.bunooboi.warasibe2;

import java.util.ArrayList;

public class Player {
    private String name;
    private int HP;                                    // 体力
    private int Stamina;                                //気力
    private int Power;                                // 力
    private int Defense;                             //防御力
    private int Intelligence;                            // 洞察力

    private int img;

    private ArrayList<Item> item;

    public static final int GU = 1;
    public static final int CHOKI = 2;
    public static final int PA = 3;

    public Player(String name, int img) {
        this.name = name;
        this.img = img;
        item = new ArrayList<>();
    }

    public void setStatus(int hp, int stamina, int power, int defense, int intelligence) {
        this.HP = hp;
        this.Stamina = stamina;
        this.Power = power;
        this.Defense = defense;
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

    public Item getItem(int i) {
        return item.get(i);
    }

    public Item getRandomItem() {
        int rand = (int) Math.floor(Math.random() * item.size());
        return item.get(rand);
    }

    public void setHP(int hp) {
        HP = hp;
    }

    public void decHP(int dec) {
        HP -= dec;
    }

    public void incHP(int inc) {
        HP += inc;
    }


    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public int getStamina() {
        return Stamina;
    }

    public int getPower() {
        return Power;
    }

    public int getDefense() {
        return Defense;
    }

    public int getIntelligence() {
        return Intelligence;
    }


    public int getImg() {
        return img;
    }
}

package jp.gr.java_conf.bunooboi.warasibe2;

import java.util.ArrayList;

public class I {

    static String name;
    static int HP;                                    // 体力
    static int Stamina;                                //気力
    static int Power;                                // 力
    static int Intelligence;                            // 洞察力

    static ArrayList<Item> item;

    public static final int ITEM1 = 0;
    public static final int ITEM2 = 1;

    static boolean meziha_1;


    public static void init() {
        item = new ArrayList<>();
        item.add(new Item("鍋", R.drawable.damage));
        item.add(new Item("弓", R.drawable.damage));
    }

    public static Item getItem(int i) {
        return item.get(i);
    }

    public static void exchange(Item item, int i) {
        I.item.set(i, item);
    }
}

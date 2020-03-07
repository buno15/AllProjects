package jp.gr.java_conf.bunooboi.warasibe2;

import java.util.ArrayList;

public class I {

    static String name;
    static int HP;                                    // 体力
    static int maxHP;
    static int Stamina;                                //気力
    static int Power;                                // 力
    static int Defense;                             //防御力
    static int Intelligence;                            // 洞察力
    static int Karma;                                //民心
    static int level;                                  //レベル（手に入れたアイテムの最大レベル）

    private static ArrayList<Item> item;

    public static final int ITEM1 = 0;
    public static final int ITEM2 = 1;


    private static boolean horse;
    private static boolean cart;
    private static boolean safe;

    static boolean inn;
    static boolean meziha_1;

    static int innTime;

    private I() {

    }


    public static void init() {
        item = new ArrayList<>();
    }

    public static void addItem(Item i) {
        item.add(i);
        I.setLevel(Math.max(I.getLevel(), i.getLevel()));
    }

    public static void addItemFirst(Item i) {
        item.add(0, i);
        I.setLevel(Math.max(I.getLevel(), i.getLevel()));
    }

    public static Item getItem(int i) {
        return item.get(i);
    }

    public static ArrayList<Item> getItem() {
        return item;
    }

    public static boolean findItem(Item i) {
        if (item.indexOf(i) == -1) {
            return false;
        }
        return true;
    }

    public static void removeItem(Item i) {
        item.remove(i);
    }

    public static void removeItem(int i) {
        item.remove(i);
    }

    public static int getItemSize() {
        return item.size();
    }

    public static void exchange(Item item, int i) {
        I.item.set(i, item);
        I.setLevel(Math.max(I.getLevel(), item.getLevel()));
    }

    public static void exchange(Item IHave, Item youHave) {
        item.remove(IHave);
        item.add(youHave);
        I.setLevel(Math.max(I.getLevel(), IHave.getLevel()));
    }

    static void haveItem(Item item) {
        switch (item.getKind()) {
            case Item.WEAPON:
                I.Power += item.getAdd();
                break;
            case Item.ARMOR:
                I.Defense += item.getAdd();
                break;
            case Item.MIND:
                I.Intelligence += item.getAdd();
                break;
        }
    }

    static void dropItem(Item item) {
        switch (item.getKind()) {
            case Item.WEAPON:
                I.Power -= item.getAdd();
                break;
            case Item.ARMOR:
                I.Defense -= item.getAdd();
                break;
            case Item.MIND:
                I.Intelligence -= item.getAdd();
                break;
        }
    }

    public static void setHorse(boolean horse) {
        I.horse = horse;
    }

    public static void setCart(boolean cart) {
        I.cart = cart;
    }

    public static void setSafe(boolean safe) {
        I.safe = safe;
    }

    public static int getMaxItemSize() {
        int size = 2;
        if (I.horse) {
            size = 5;
        }
        if (I.cart) {
            size = 10;
        }
        return size;
    }

    public static boolean haveHorse() {
        return horse;
    }

    public static boolean haveCart() {
        return cart;
    }

    public static boolean isSafe() {
        return safe;
    }

    public static boolean isCriminal() {
        if (Karma <= 10) {
            return true;
        }
        return false;
    }

    public static void setLevel(int level) {
        I.level = level;
    }

    public static int getLevel() {
        return level;
    }
}

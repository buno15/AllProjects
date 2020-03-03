package jp.gr.java_conf.bunooboi.warasibe2;

public class Item {
    private String name;
    private int img;
    private int add;
    private int level;
    private int kind;

    private Item exchange;

    public final static int WEAPON = 1;
    public final static int ARMOR = 2;
    public final static int MIND = 3;
    public final static int FOOD = 4;
    public final static int MATERIAL = 5;

    public Item(String name, int img, int add, int level, int kind) {
        this.name = name;
        this.img = img;
        this.add = add;
        this.level = level;
        this.kind = kind;
    }

    public void setExchange(Item item) {
        exchange = item;
    }

    public Item getExchange() {
        return exchange;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public int getAdd() {
        return add;
    }

    public int getLevel() {
        return level;
    }

    public int getKind() {
        return kind;
    }

}

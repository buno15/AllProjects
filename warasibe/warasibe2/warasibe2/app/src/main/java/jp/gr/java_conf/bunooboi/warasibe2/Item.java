package jp.gr.java_conf.bunooboi.warasibe2;

public class Item {
    private String name;
    private int img;
    private int add;
    private int level;

    private Item exchange;

    public Item(String name, int img, int level) {
        this.name = name;
        this.img = img;
        this.level = level;
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

}

package jp.gr.java_conf.bunooboi.warasibe2;

public class Request {
    private Item reward;
    private Player enemy;
    private City city;
    private int mode;

    public static final int EXCHANGE = 1;
    public static final int SP = 2;
    public static final int FRIEND = 3;

    public Request(Item reward, Player enemy, City city, int mode) {
        this.reward = reward;
        this.enemy = enemy;
        this.city = city;
        this.mode = mode;
    }

    public String getItemName() {
        return reward.getName();
    }

    public Item getItem() {
        return reward;
    }

    public String getEnemyName() {
        return enemy.getName();
    }

    public Player getEnemy() {
        return enemy;
    }

    public City getCity() {
        return city;
    }

    public int getMode() {
        return mode;
    }
}

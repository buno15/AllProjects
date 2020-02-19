package jp.gr.java_conf.bunooboi.warasibe2;

public class Scene {
    private String upStr;
    private String downStr;
    private String leftStr;
    private String rightStr;

    private String consoleText = "";
    private String consoleName = "";

    private Scene up;
    private Scene down;
    private Scene left;
    private Scene right;

    private Scene action;

    private int playImg;

    private EffectListener effect;


    public Scene(String up, String right, String down, String left) {
        this.upStr = up;
        this.downStr = down;
        this.leftStr = left;
        this.rightStr = right;
    }

    public void setScene(Scene up, Scene right, Scene down, Scene left, Scene action) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.action = action;
    }


    public Scene getUp() {
        return up;
    }

    public Scene getDown() {
        return down;
    }

    public Scene getLeft() {
        return left;
    }

    public Scene getRight() {
        return right;
    }

    public Scene getAction() {
        return action;
    }


    public String getUpStr() {
        return upStr;
    }

    public String getDownStr() {
        return downStr;
    }

    public String getLeftStr() {
        return leftStr;
    }

    public String getRightStr() {
        return rightStr;
    }

    public void setConsoleName(String text) {
        this.consoleName = text;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleText(String text) {
        this.consoleText = text;
    }

    public String getConsoleText() {
        return consoleText;
    }

    public void setPlayImg(int playImg) {
        this.playImg = playImg;
    }

    public int getPlayImg() {
        return playImg;
    }

    public void setEffect(EffectListener e) {
        this.effect = e;
    }

    public void getEffect() {
        if (effect != null)
            effect.effect();
    }
}

package jp.gr.java_conf.bunooboi.warasibe2;

import android.speech.tts.TextToSpeech;

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

    private InitListener init;
    private EffectListener effect;

    private int playImg;

    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;
    public static final int ACTION = 5;


    public Scene(String up, String right, String down, String left) {
        this.upStr = up;
        this.downStr = down;
        this.leftStr = left;
        this.rightStr = right;
    }

    public Scene() {
    }

    public void setScene(String up, String right, String down, String left) {
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

    public void setScene(Scene s, int dir) {
        switch (dir) {
            case UP:
                up = s;
                break;
            case RIGHT:
                right = s;
                break;
            case DOWN:
                down = s;
                break;
            case LEFT:
                left = s;
                break;
            case ACTION:
                action = s;
                break;
        }
    }

    public void setScene(Event e, int dir) {
        switch (dir) {
            case UP:
                up = e.getScene(0);
                break;
            case RIGHT:
                right = e.getScene(0);
                break;
            case DOWN:
                down = e.getScene(0);
                break;
            case LEFT:
                left = e.getScene(0);
                break;
            case ACTION:
                action = e.getScene(0);
                break;
        }
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

    public void setInit(InitListener i) {
        this.init = i;
    }

    public void getInit() {
        if (init != null) {
            init.init();
        }
    }

    public void setEffect(EffectListener e) {
        this.effect = e;
    }

    public Scene getEffect(int dir) {
        if (effect != null)
            return effect.effect(dir);
        return null;
    }
}

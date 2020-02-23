package jp.gr.java_conf.bunooboi.warasibe2;

public class Scene {
    private String up;
    private String down;
    private String left;
    private String right;

    private String consoleText = "";
    private String consoleName = "";

    private Scene prevScene;
    private Scene nextScene;

    private InitListener init;
    private FinishListener finish;

    private int playImg;

    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;
    public static final int ACTION = 5;
    public static final int ITEM1 = 6;
    public static final int ITEM2 = 7;


    public Scene(String up, String right, String down, String left) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public Scene() {
    }

    public void setSceneText(String up, String right, String down, String left) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void setPrevScene(Scene s) {
        prevScene = s;
    }

    public Scene getPrevScene() {
        return prevScene;
    }

    public void setNextScene(Scene s) {
        nextScene = s;
    }

    public Scene getNextScene() {
        return nextScene;
    }


    public String getUp() {
        return up;
    }

    public String getDown() {
        return down;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
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

    public void start(Scene s, int dir) {
        prevScene = s;
        if (init != null) {
            init.init(dir);
        }
    }

    public void setFinish(FinishListener f) {
        this.finish = f;
    }

    public Scene finish(int dir) {
        if (finish != null)
            return finish.finish(dir);
        return null;
    }

    public boolean isNext(int dir) {
        if (finish(dir) != null) {
            return true;
        }
        return false;
    }
}

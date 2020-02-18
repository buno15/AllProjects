package jp.gr.java_conf.bunooboi.warasibe2;

public class Scene {
    String upStr;
    String downStr;
    String leftStr;
    String rightStr;

    Scene up;
    Scene down;
    Scene left;
    Scene right;

    public Scene(String up, String right, String down, String left) {
        this.upStr = up;
        this.downStr = down;
        this.leftStr = left;
        this.rightStr = right;
    }
}

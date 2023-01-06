package jp.gr.java_conf.bunooboi.gams;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by bunooboi on 16/07/12.
 */
public class Table {
    private Context context;
    private ViewGroup root;
    private final int lengthX;
    private final int lengthY;

    private LinearLayout tableX[][];
    private LinearLayout tableY[];

    public Table(Context context, ViewGroup root) {
        this.context = context;
        this.root = root;
        lengthX = 3;
        lengthY = 3;
    }

    public Table(Context context, ViewGroup root, int x, int y) {
        this.context = context;
        this.root = root;
        lengthX = x;
        lengthY = y;
    }

    public void createTable() {
        tableX = new LinearLayout[lengthY][lengthX];
        tableY = new LinearLayout[lengthY];
        for (int i = 0; i < tableY.length; i++) {//TableY設定
            tableY[i] = new LinearLayout(context);
            tableY[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
            tableY[i].setOrientation(LinearLayout.HORIZONTAL);
            root.addView(tableY[i]);
        }
        for (int i = 0; i < lengthY; i++) {//TableX設定
            for (int j = 0; j < tableX[0].length; j++) {
                tableX[i][j] = new LinearLayout(context);
                tableX[i][j].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
                tableX[i][j].setOrientation(LinearLayout.VERTICAL);
                tableY[i].addView(tableX[i][j]);
            }
        }
    }

    public void setWeight(int x, int y, float weight) {
        tableY[y].getChildAt(x).setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weight));
    }

    public LinearLayout getViewGroup(int x, int y) {
        return (LinearLayout) tableY[y].getChildAt(x);
    }

    /*public void setLength(int x, int y) {
        lengthX = x;
        lengthY = y;
    }*/

    public int getLengthX() {
        return lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }
}

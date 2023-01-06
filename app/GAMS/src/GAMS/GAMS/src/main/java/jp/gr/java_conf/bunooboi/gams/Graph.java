package jp.gr.java_conf.bunooboi.gams;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

public class Graph extends Manager {
    private Context context;

    private LinearLayout GraphLayout;                            // レイアウト１
    private FitTextView GraphText;                                // 選手名、チーム名
    private TextView ScoreUnit;                                // グラフ
    private ArrayList<TextView> ScoreUnitList = new ArrayList<TextView>();// 全グラフ

    private ViewGroup textarea;                                    // 親textエリア
    private ViewGroup grapharea;                                    // 親graphエリア

    private int length = 100;                                    // グラフサイズ
    private float scale;                                    // 文字調節

    public Graph(Context context, ViewGroup textarea, ViewGroup grapharea) {
        this.context = context;
        this.textarea = textarea;
        this.grapharea = grapharea;
        scale = getScaleSize(context);
        createGraphLayout();
        createGraphText();
    }

    void createGraphLayout() {
        GraphLayout = new LinearLayout(context);
        GraphLayout.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        GraphLayout.setOrientation(LinearLayout.HORIZONTAL);
        GraphLayout.setGravity(Gravity.CENTER);
        grapharea.addView(GraphLayout);
    }

    void createGraphText() {
        GraphText = new FitTextView(context);
        GraphText.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        GraphText.setTextSize(3 * getScaleSize(context));
        GraphText.setTextColor(Color.BLACK);
        GraphText.setGravity(Gravity.CENTER);
        textarea.addView(GraphText);
    }

    void createScoreUnit() {
        ScoreUnit = new TextView(context);
        ScoreUnit.setLayoutParams(new LayoutParams((int) getDispW(context) / 65, LinearLayout
                .LayoutParams
                .MATCH_PARENT));
        ScoreUnit.setBackgroundResource(R.drawable.graph_notcheck);
        ScoreUnit.setGravity(Gravity.CENTER);
        ScoreUnit.setTextSize(2 * scale);
        ScoreUnit.setTextColor(Color.WHITE);
        GraphLayout.addView(ScoreUnit);
        ScoreUnitList.add(ScoreUnit);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setName(String name) {// 選手名セット
        GraphText.setText(name);
    }

    public void setGraphColor(int color) {// 色セット
        GraphText.setBackgroundResource(color);
    }

    public void createGraph() {// グラフ作成
        for (int i = 0; i < length; i++) {
            createScoreUnit();
        }
    }

    public void clean() {
        for (int i = 0; i < length; i++) {
            setUnCheckScoreUnit(i, 0);
        }
    }


    public void addScoreUnit() {// ScoreUnit追加
        createScoreUnit();
    }

    public void setCheckScoreUnit(int index, int value) {// グラフチェック、通常用
        ScoreUnitList.get(index).setBackgroundResource(R.drawable.graph_check);
        ScoreUnitList.get(index).setText(String.valueOf(value));
    }

    public void setUnCheckScoreUnit(int index, int value) {// グラフノットチェック、通常用
        ScoreUnitList.get(index).setBackgroundResource(R.drawable.graph_notcheck);
        ScoreUnitList.get(index).setText(String.valueOf(value));
    }

    public void setCheckScoreUnitAttribute(int index, int value, int key) {// グラフチェック、AceMiss用
        ScoreUnitList.get(index).setText(String.valueOf(value));
        ScoreUnitList.get(index).setBackgroundResource(AttributeGraph.getAttribute(key));
    }
}

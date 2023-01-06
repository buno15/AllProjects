package jp.gr.java_conf.bunooboi.gams;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreBoard extends Manager {
    private Context context;
    private ViewGroup view;            // 親view
    public Button ScoreButton;    // スコアボタン
    private LinearLayout PastScoreLayout;    // 全スコアレイアウト
    private ArrayList<TextView> PastScoreText = new ArrayList<TextView>();    // 全スコアテキスト

    public ScoreBoard(Context context, ViewGroup view) {
        this.context = context;
        this.view = view;
        createScoreButton();
        createPastScoreLayout();
    }

    public ScoreBoard(Context context, ViewGroup view, int length) {
        this.context = context;
        this.view = view;
        createScoreButton();
        createPastScoreLayout();
        for (int i = 0; i < length; i++) {
            PastScoreText.add(new TextView(context));
            createPastScoreText(PastScoreText.get(i));
        }
    }

    void createScoreButton() {
        ScoreButton = new Button(context);
        ScoreButton.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 2));
        ScoreButton.setTextSize(10 * getScaleSize(context));
        ScoreButton.setShadowLayer(5, 0, 0, Color.BLACK);
        ScoreButton.setBackgroundResource(R.drawable.score_text);
        view.addView(ScoreButton);
    }

    void createPastScoreLayout() {
        PastScoreLayout = new LinearLayout(context);
        PastScoreLayout.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        PastScoreLayout.setOrientation(LinearLayout.VERTICAL);
        PastScoreLayout.setBackgroundResource(R.drawable.score_text);
        view.addView(PastScoreLayout);
    }

    void createPastScoreText(TextView allscoretext) {
        allscoretext.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        allscoretext.setTextSize(4 * getScaleSize(context));
        allscoretext.setTextColor(Color.BLACK);
        allscoretext.setGravity(Gravity.CENTER);
        PastScoreLayout.addView(allscoretext);
    }

    public void setWeightScoreButton(int weight) {
        ScoreButton.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,
                weight));
    }

    public void setWeightPastScoreLayout(int weight) {
        PastScoreLayout.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                0, weight));
    }

    public void setTextColor(int color) {
        ScoreButton.setTextColor(color);
    }

    public void setPastScore(int index, int score) {
        PastScoreText.get(index).setText(String.valueOf(score));
    }

    public void setScore(int score) {
        ScoreButton.setText(String.valueOf(score));
    }

    public void setEnabled(boolean b) {
        ScoreButton.setEnabled(b);
    }
}

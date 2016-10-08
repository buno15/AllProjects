package jp.gr.java_conf.bunooboi.zbs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by bunooboi on 16/10/08.
 */
public class ActivityAnswer extends Activity {
    String rank;
    String review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        setAnswer(ActivityMain.total);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);

        textView1.setText(rank);
        textView2.setText(Html.fromHtml("<font color=\"#0000ff\">" + ActivityMain.total + "</font>点"));
        textView3.setText(review);

        textView1.setTextSize(100 * Mine.getScaleSize(getApplicationContext()));
        textView2.setTextSize(60 * Mine.getScaleSize(getApplicationContext()));
        textView3.setTextSize(20 * Mine.getScaleSize(getApplicationContext()));

        textView1.setTextColor(Color.RED);
    }

    void setAnswer(int total) {
        if (total <= 6) {
            rank = "Z";
            review = "最悪の心理状態。\nありえないレベル。\nしばらく落ち着き、気を楽にすることを推奨。";
        } else if (total > 6 && total <= 10) {
            rank = "F";
            review = "悪い心理状態。\n何が悪いかを振り返る必要がある。\n時間をかけて平常時以上に戻す。";
        } else if (total > 10 && total <= 15) {
            rank = "E";
            review = "悪い傾向の心理状態。\n平常時以上に戻すことを推奨。\n少し考えてもよい。";
        } else if (total > 15 && total <= 20) {
            rank = "D";
            review = "平常時の心理状態。\n良好な状態に持って行くことを推奨。";
        } else if (total > 20 && total <= 25) {
            rank = "C";
            review = "良好な心理状態。\n良い傾向。\nこのまま上昇することを期待。";
        } else if (total > 25 && total <= 29) {
            rank = "B";
            review = "素晴らしい心理状態。\nとてもよい状態。\nこの状態を維持し続けることを強く求める。";
        } else if (total > 29) {
            rank = "A";
            review = "最高の心理状態";
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Sound.restartMediaPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Sound.releaseMediaPlayer();
        ActivityMain.total = 0;
    }
}

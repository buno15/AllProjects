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
    StringBuilder review = new StringBuilder();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);

        textView2.setText(Html.fromHtml("<font color=\"#0000ff\">" + setAnswer(ActivityMain.point) + "</font>点"));
        textView1.setText(rank);
        textView3.setText(review);

        textView1.setTextSize(100 * Mine.getScaleSize(getApplicationContext()));
        textView2.setTextSize(60 * Mine.getScaleSize(getApplicationContext()));
        textView3.setTextSize(20 * Mine.getScaleSize(getApplicationContext()));

        textView1.setTextColor(Color.RED);
    }

    int setAnswer(int point[]) {
        int total = 0;
        for (int i = 0; i < point.length; i++) {
            total += point[i];
        }
        if (total <= 6) {
            rank = "Z";
            review.append("最悪の心理状態。\n一旦作業を止めましょう。\nこの音楽を聴き、落ち着きましょう。\n");
        } else if (total > 6 && total <= 10) {
            rank = "F";
            review.append("悪い心理状態。\n一度現在の状況を見直しましょう。\n");
        } else if (total > 10 && total <= 15) {
            rank = "E";
            review.append("悪い傾向の心理状態。\n");
        } else if (total > 15 && total <= 20) {
            rank = "D";
            review.append("平常時の心理状態。\n");
        } else if (total > 20 && total <= 25) {
            rank = "C";
            review.append("良好な心理状態。\n");
        } else if (total > 25 && total <= 29) {
            rank = "B";
            review.append("素晴らしい心理状態。\nこの状態を維持しましょう。\n");
        } else if (total > 29) {
            rank = "A";
            review.append("最高の心理状態\n");
        }
        if (point[0] <= 2) {
            review.append("禁欲をしてください。\n");
        }
        if (point[1] <= 2) {
            review.append("忍耐が低下しています。\n");
        }
        if (point[2] <= 2) {
            review.append("冷静さにかけています。\n");
        }
        if (point[3] <= 2) {
            review.append("集中がきれています。\n");
        }
        if (point[4] <= 2) {
            review.append("視野が狭いです。\n");
        }
        if (point[5] <= 2) {
            review.append("幸せではありません。\n");
        }
        return total;
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
        ActivityMain.point = new int[6];
    }
}

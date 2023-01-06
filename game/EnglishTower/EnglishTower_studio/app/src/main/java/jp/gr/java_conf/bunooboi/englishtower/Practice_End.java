package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hiro on 2016/07/17.
 */
public class Practice_End extends Activity {
    static long t1;
    static long t2;

    LinearLayout root;
    Animation animstart;
    Animation animend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.practiceend);

        long t3 = (t2 - t1) / 1000;

        root = (LinearLayout) findViewById(R.id.root);
        animstart = AnimationUtils.loadAnimation(this, R.anim.startthis);
        animend = AnimationUtils.loadAnimation(this, R.anim.endthis);

        TextView textview[] = new TextView[2];
        textview[0] = (TextView) findViewById(R.id.textview1);
        textview[1] = (TextView) findViewById(R.id.textview2);
        if (Practice.practicemode)
            textview[0].setText("レベル" + String.valueOf(Practice.minlevel + 1) + "～" + String.valueOf(Practice.minlevel + 10));
        else
            textview[0].setText("レベル" + String.valueOf(Practice.minlevel + 1));
        textview[1].setText("解答時間\n" + t3 / 3600 + "時間" + t3 % 3600 / 60 + "分" + t3 % 60 + "秒");
        for (int i = 0; i < textview.length; i++) {
            textview[i].setTextSize(20 * Tower.getScaleSize(getApplicationContext()));
            textview[i].setTextColor(Color.WHITE);
        }
        Button button = (Button) findViewById(R.id.button);
        button.setText("メイン");
        button.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.start2();
                animend.setDuration(1000);
                root.startAnimation(animend);
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        root.setAlpha(0);
                        Intent i = new Intent(getApplicationContext(), Main.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                        overridePendingTransition(0, 0);
                    }
                }, 1000);
            }
        });
    }
}

package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Over extends Activity {
    Timer t;
    Handler handler = new Handler();
    LinearLayout root;
    TextView overtext;
    TextView answertext;
    Button main;
    TextView textview;
    Animation animend;
    Animation animstart;
    static String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.over);

        Sound.media2play(1);

        Values.floor = 1;
        Values.levelfloor = 1;
        Values.level = 0;
        Values.life[0] = false;
        Values.life[1] = false;
        Values.life[2] = false;
        Values.save(this);

        root = (LinearLayout) findViewById(R.id.root);
        overtext = (TextView) findViewById(R.id.overtext);
        answertext = (TextView) findViewById(R.id.answertext);
        main = (Button) findViewById(R.id.main);

        animend = AnimationUtils.loadAnimation(this, R.anim.endthis);
        animstart = AnimationUtils.loadAnimation(this, R.anim.startthis);

        overtext.setText("GameOver");
        overtext.setTextSize(20 * Tower.getScaleSize(getApplicationContext()));
        overtext.setTextColor(Color.BLACK);
        overtext.setShadowLayer(5, 0, 0, Color.WHITE);
        answertext.setText("答え：" + answer);
        answertext.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
        answertext.setTextColor(Color.WHITE);
        answertext.setShadowLayer(5, 0, 0, Color.BLACK);
        main.setText("メイン");
        main.setTextSize(8 * Tower.getScaleSize(getApplicationContext()));

        textview = new TextView(this);
        textview.setText("1階");
        textview.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        textview.setTextSize(30 * Tower.getScaleSize(getApplicationContext()));
        textview.setTextColor(Color.YELLOW);
        textview.setShadowLayer(5, 0, 0, Color.BLACK);
        textview.setGravity(Gravity.CENTER);

        main.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.start2();
                Sound.mediaPlayer2.stop();
                animend.setDuration(1000);
                root.startAnimation(animend);
                if (t != null) {
                    t.cancel();
                    t = null;
                }
                if (t == null) {
                    t = new Timer();
                    t.schedule(new EndThisTimer(), 1000);
                }
            }
        });
    }

    class EndThisTimer extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    root.setAlpha(0);
                    Intent i = new Intent(getApplicationContext(), Main.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                    overridePendingTransition(0, 0);
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();
        Sound.mediaPlayer2.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        Sound.mediaPlayer2.pause();
    }
}

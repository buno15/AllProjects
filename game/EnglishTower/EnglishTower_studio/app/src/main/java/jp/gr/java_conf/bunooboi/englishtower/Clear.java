package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Clear extends Activity {
    TextView textview;
    Animation anim;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.clear);

        Sound.media2play(3);

        textview = (TextView) findViewById(R.id.textview);
        textview.setText("Congratulation");
        textview.setTextSize(20 * Tower.getScaleSize(getApplicationContext()));
        textview.setTextColor(Color.YELLOW);
        textview.setShadowLayer(5, 0, 0, Color.BLACK);
        textview.setPadding(0,0,0,Tower.getPctY(getApplicationContext(),30));

        anim = AnimationUtils.loadAnimation(this, R.anim.endthis);
        anim.setDuration(10000);

        Timer t = new Timer();
        t.schedule(new EndThisTimer(), 10000);
    }

    class EndThisTimer extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textview.startAnimation(anim);
                    Timer t = new Timer();
                    t.schedule(new GoToStartTimer(), 10000);
                }
            });
        }
    }

    class GoToStartTimer extends TimerTask {
        @Override
        public void run() {
            textview.setAlpha(0);
            Intent i = new Intent(getApplicationContext(), Main.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            finish();
            overridePendingTransition(0, 0);
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

package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Timer;
import java.util.TimerTask;

public class Start extends Activity {
    View root;
    Animation start;
    Animation end;

    Timer t;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.start);

        Sound.init(getApplicationContext());
        if (DataBase.word.size() == 0)
            DataBase.createDataBase();

        Sound.media2play(0);

        root = (View) findViewById(R.id.root);
        start = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.startthis);
        end = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.endthis);
        start.setDuration(5000);
        end.setDuration(3000);

        root.startAnimation(start);
    }

    class EndThisTimer extends TimerTask {

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    root.startAnimation(end);
                    Timer t = new Timer();
                    t.schedule(new GoMainTimer(), 3000);
                }
            });
        }
    }

    class GoMainTimer extends TimerTask {
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
        Sound.mediaPlayer2.seekTo(0);
        Sound.mediaPlayer2.start();
        t = new Timer();
        t.schedule(new EndThisTimer(), 9000);
    }

    @Override
    public void onPause() {
        super.onPause();
        Sound.mediaPlayer2.pause();
        if (t != null) {
            t.cancel();
            t = null;
        }
    }
}

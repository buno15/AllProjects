package jp.gr.java_conf.bunooboi.bmp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.gams.Manager;

public class Start extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.start);

        Manager.getLibrary();

        Timer t = new Timer();
        t.schedule(new EndTimer(), 3000);
    }

    class EndTimer extends TimerTask {
        Handler handler = new Handler();

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), Config.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                    overridePendingTransition(0, 0);
                }
            });
        }
    }
}

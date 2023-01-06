package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sentences;
import jp.gr.java_conf.bunooboi.mydic.Values;

/**
 * Created by hiro on 2016/08/04.
 */
public class Start extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start);
        File file = new File(Values.DataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Sentences.init();
        Values.context = getApplicationContext();
        final Handler h = new Handler();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), Main.class));
                        finish();
                    }
                });
            }
        }, 2000);
    }
}

package jp.gr.java_conf.bunooboi.mydic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hiro on 2016/08/04.
 */
public class Start extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        File file = new File(Values.ConfigPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Sentences.init();
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

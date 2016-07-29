package jp.gr.java_conf.bunooboi.TemporaryStorage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by hiro on 2016/07/29.
 */
public class Play extends Activity {
    static String text;
    static int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.play);

        switch (level) {
            case 0:
                text = getRandomString(new Random().nextInt(20) + 10);
                break;
            case 1:
                text = getRandomString(new Random().nextInt(20) + 30);
                break;
            case 2:
                text = getRandomString(new Random().nextInt(40) + 60);
                break;
            case 3:
                text = getRandomString(new Random().nextInt(100) + 100);
                break;
            default:
                text = getRandomString(new Random().nextInt(200) + 10);
        }
        Log.v("text", text);
        Log.v("text_length", String.valueOf(text.length()));

        TextView textview = (TextView) findViewById(R.id.textview);
        textview.setText(text);
        textview.setTextSize(30 * Start.getScaleSize(getApplicationContext()));

        Button button = (Button) findViewById(R.id.button);
        button.setText("OK");
        button.setTextSize(24 * Start.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Question.class));
            }
        });
    }

    public static String getRandomString(int cnt) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" +
                "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん";
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < cnt; i++) {
            int val = rnd.nextInt(chars.length());
            buf.append(chars.charAt(val));
        }
        return buf.toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

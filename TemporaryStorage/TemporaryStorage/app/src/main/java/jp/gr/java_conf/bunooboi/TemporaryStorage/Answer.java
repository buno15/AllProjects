package jp.gr.java_conf.bunooboi.TemporaryStorage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hiro on 2016/07/29.
 */
public class Answer extends Activity {
    static String answer;

    @Override
    protected void onCreate(Bundle savedInstanceStatte) {
        super.onCreate(savedInstanceStatte);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.answer);

        if (answer.equals(Play.text)) {
            findViewById(R.id.root).setBackgroundColor(Color.parseColor("#008740"));
        } else {
            findViewById(R.id.root).setBackgroundColor(Color.parseColor("#c40026"));
        }

        TextView textview1 = (TextView) findViewById(R.id.textview1);
        TextView textview2 = (TextView) findViewById(R.id.textview2);
        textview1.setText(Play.text);
        textview2.setText(answer);
        textview1.setTextSize(20 * Start.getScaleSize(getApplicationContext()));
        textview2.setTextSize(20 * Start.getScaleSize(getApplicationContext()));
        serch(textview1, textview2);

        Button button = (Button) findViewById(R.id.button);
        button.setText("MainMenu");
        button.setTextSize(24 * Start.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Start.class));
            }
        });
    }

    public static void serch(TextView text1, TextView text2) {
        char c1[] = text1.getText().toString().toCharArray();
        char c2[] = text2.getText().toString().toCharArray();
        int max = Math.max(c1.length, c2.length);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < max; i++) {
            if (c1.length <= i) {
                sb2.append("<font color=\"#c40026\">" + c2[i] + "</font>");
            } else if (c2.length <= i) {
                sb1.append("<font color=\"#c40026\">" + c1[i] + "</font>");
            } else {
                if (c1[i] == c2[i]) {
                    sb1.append(c1[i]);
                } else {
                    sb1.append("<font color=\"#c40026\">" + c1[i] + "</font>");
                }
            }
        }
        text1.setText(Html.fromHtml(new String(sb1)));
        if (c1.length < max) {
            text2.setText(Html.fromHtml(text2.getText().toString() + sb2));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

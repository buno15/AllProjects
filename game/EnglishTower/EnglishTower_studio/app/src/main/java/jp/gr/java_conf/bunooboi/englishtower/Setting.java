package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
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
 * Created by hiro on 2016/07/21.
 */
public class Setting extends Activity {
    LinearLayout root;
    Animation animend;
    Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.setting);

        root = (LinearLayout) findViewById(R.id.root);
        animend = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.endthis);

        TextView textview[] = new TextView[2];
        textview[0] = (TextView) findViewById(R.id.textview1);
        textview[1] = (TextView) findViewById(R.id.textview2);
        textview[0].setText("ーテキスト読み上げスピードーー");
        textview[1].setText("ーテキスト読み上げピッチーーー");
        for (TextView tv : textview) {
            tv.setTextSize(8 * Tower.getScaleSize(getApplicationContext()));
            tv.setTextColor(Color.BLACK);
        }
        final Button button[] = new Button[8];
        button[0] = (Button) findViewById(R.id.button1);
        button[1] = (Button) findViewById(R.id.button2);
        button[2] = (Button) findViewById(R.id.button3);
        button[3] = (Button) findViewById(R.id.button4);
        button[4] = (Button) findViewById(R.id.button5);
        button[5] = (Button) findViewById(R.id.button6);
        button[6] = (Button) findViewById(R.id.button7);
        button[7] = (Button) findViewById(R.id.button8);
        button[0].setText("遅い");
        button[1].setText("普通");
        button[2].setText("速い");
        button[3].setText("低い");
        button[4].setText("普通");
        button[5].setText("高い");
        button[6].setText("メイン");
        button[7].setText("ヘルプ");
        for (Button btn : button) {
            btn.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
            if (btn.equals(button[6]) || btn.equals(button[7])) {
                btn.setTextColor(Color.BLACK);
            } else {
                btn.setTextColor(Color.WHITE);
            }
        }
        button[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.button();
                button[0].setEnabled(false);
                button[1].setEnabled(true);
                button[2].setEnabled(true);
                Values.ttsSpeechRate = 0.5F;
            }
        });
        button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.button();
                button[0].setEnabled(true);
                button[1].setEnabled(false);
                button[2].setEnabled(true);
                Values.ttsSpeechRate = 1.0F;
            }
        });
        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.button();
                button[0].setEnabled(true);
                button[1].setEnabled(true);
                button[2].setEnabled(false);
                Values.ttsSpeechRate = 1.5F;
            }
        });
        button[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.button();
                button[3].setEnabled(false);
                button[4].setEnabled(true);
                button[5].setEnabled(true);
                Values.ttsPitch = 0.5F;
            }
        });
        button[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.button();
                button[3].setEnabled(true);
                button[4].setEnabled(false);
                button[5].setEnabled(true);
                Values.ttsPitch = 1.0F;
            }
        });
        button[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.button();
                button[3].setEnabled(true);
                button[4].setEnabled(true);
                button[5].setEnabled(false);
                Values.ttsPitch = 1.5F;
            }
        });
        button[6].setOnClickListener(new View.OnClickListener() {//メイン
            @Override
            public void onClick(View v) {
                Sound.start2();
                Values.soundSave(getApplicationContext());
                animend.setDuration(1000);
                root.startAnimation(animend);
                if (t != null) {
                    t.cancel();
                    t = null;
                }
                t = new Timer();
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
        button[7].setOnClickListener(new View.OnClickListener() {//ヘルプ
            @Override
            public void onClick(View v) {
                Sound.start2();
                Uri uri = Uri.parse("http://bunooboi.sakura.ne.jp/src/html/game/englishtower.html");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
        Values.soundLoad(getApplicationContext());//スピード、ピッチロード
        if (Values.ttsSpeechRate == 0.5F) {
            button[0].setEnabled(false);
        } else if (Values.ttsSpeechRate == 1.0F) {
            button[1].setEnabled(false);
        } else if (Values.ttsSpeechRate == 1.5F) {
            button[2].setEnabled(false);
        }
        if (Values.ttsPitch == 0.5F) {
            button[3].setEnabled(false);
        } else if (Values.ttsPitch == 1.0F) {
            button[4].setEnabled(false);
        } else if (Values.ttsPitch == 1.5F) {
            button[5].setEnabled(false);
        }
        Sound.start2();
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keycode, event);
    }
}

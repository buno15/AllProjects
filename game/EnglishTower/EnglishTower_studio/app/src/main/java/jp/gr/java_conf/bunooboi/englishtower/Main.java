package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
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

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Activity {
    LinearLayout root;
    TextView floortext;
    Button tower;
    Button practice;
    Button dictionary;
    Button help;
    Button feedback;
    Timer t;
    Handler handler = new Handler();
    Animation animend;
    Animation animstart;

    int rootWidth;
    int rootHeight;
    int next;

    boolean goTower = false;
    static TextToSpeech tts;//テキスト読み上げ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);

        Sound.start4();

        Values.soundLoad(getApplicationContext());
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = Locale.ENGLISH;
                    if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                        tts.setLanguage(locale);
                        tts.setPitch(Values.ttsPitch);
                        tts.setSpeechRate(Values.ttsSpeechRate);
                        tts.speak(" ", TextToSpeech.QUEUE_FLUSH, null);
                        System.out.println("TTS:OK");
                    } else {
                        System.out.println("Error Language");
                    }
                } else {
                    System.out.println("Error Init");
                }
            }
        });

        root = (LinearLayout) findViewById(R.id.root);
        tower = (Button) findViewById(R.id.tower);
        practice = (Button) findViewById(R.id.practice);
        dictionary = (Button) findViewById(R.id.dictionary);
        help = (Button) findViewById(R.id.help);
        feedback = (Button) findViewById(R.id.feedback);

        tower.setText("塔に挑戦");
        tower.setTextSize(8 * Tower.getScaleSize(getApplicationContext()));
        practice.setText("道場");
        practice.setTextSize(8 * Tower.getScaleSize(getApplicationContext()));
        dictionary.setText("辞典");
        dictionary.setTextSize(8 * Tower.getScaleSize(getApplicationContext()));
        help.setText("設定");
        help.setTextSize(8 * Tower.getScaleSize(getApplicationContext()));
        feedback.setText("ご意見・誤訳報告");
        feedback.setTextSize(8 * Tower.getScaleSize(getApplicationContext()));

        floortext = new TextView(this);
        floortext.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        floortext.setTextSize(30 * Tower.getScaleSize(getApplicationContext()));
        floortext.setTextColor(Color.YELLOW);
        floortext.setShadowLayer(5, 0, 0, Color.BLACK);
        floortext.setGravity(Gravity.CENTER);

        animend = AnimationUtils.loadAnimation(this, R.anim.endthis);
        animstart = AnimationUtils.loadAnimation(this, R.anim.startthis);

        tower.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goTower = true;
                allEnabled();
                Sound.mediaPlayer2.stop();
                Sound.start1();
                animend.setDuration(3000);
                animstart.setDuration(3000);
                root.startAnimation(animend);
                if (t != null) {
                    t.cancel();
                    t = null;
                }
                t = new Timer();
                t.schedule(new GoTowerTimer(), 3000);
            }
        });
        practice.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                allEnabled();
                next = 0;
                nextActivity();
            }
        });

        dictionary.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                allEnabled();
                next = 1;
                nextActivity();
            }
        });
        help.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                allEnabled();
                next = 2;
                nextActivity();
            }
        });
        feedback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:bunooboi@gmail.com");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra(Intent.EXTRA_SUBJECT, "ご意見・誤訳報告");
                startActivity(intent);
            }
        });
        Values.load(getApplicationContext());
        floortext.setText(String.valueOf(Values.floor) + "階");
        if (Values.Midstream()) {
            tower.setText("続きから");
        }
        TextView recordtext = (TextView) findViewById(R.id.recordtext);
        recordtext.setTextSize(8 * Tower.getScaleSize(getApplicationContext()));
        recordtext.setTextColor(Color.YELLOW);
        recordtext.setShadowLayer(10, 0, 0, Color.BLACK);
        if (Values.record == 5000) {
            recordtext.setText("最高記録：頂上");
        } else {
            recordtext.setText("最高記録：" + Values.record + "階");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (goTower) {
            if (t != null) {
                t.cancel();
                t = null;
            }
            finish();
        }
    }

    void nextActivity() {
        Sound.start2();
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

    void allEnabled() {
        tower.setEnabled(false);
        practice.setEnabled(false);
        dictionary.setEnabled(false);
        help.setEnabled(false);
        feedback.setEnabled(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyDialog.text = "ゲームを終了しますか？";
            MyDialog dialog = new MyDialog();
            dialog.show(getFragmentManager(), "test");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class EndThisTimer extends TimerTask {

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    root.setAlpha(0);
                    Intent i = null;
                    switch (next) {
                        case 0:
                            i = new Intent(getApplicationContext(), Practice_Setting.class);
                            break;
                        case 1:
                            i = new Intent(getApplicationContext(), Dictionary_List.class);
                            break;
                        case 2:
                            i = new Intent(getApplicationContext(), Setting.class);
                            break;
                    }
                    if (i != null) {
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                        overridePendingTransition(0, 0);
                    }
                }
            });
        }

    }

    class GoTowerTimer extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    root.removeAllViews();
                    if (Values.Midstream()) {
                        root.setBackgroundResource(R.drawable.zzz_nextfloor);
                    } else {
                        root.setBackgroundResource(R.drawable.zzz_bottom);
                        Sound.open();
                    }
                    root.addView(floortext);
                    root.startAnimation(animstart);
                    if (t != null) {
                        t.cancel();
                        t = null;
                    }
                    t = new Timer();
                    t.schedule(new StartTowerTimer(), 5000);
                }
            });
        }
    }

    class StartTowerTimer extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    root.setAlpha(1.0f);
                    Intent i = new Intent(getApplicationContext(), Tower.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                    overridePendingTransition(0, 0);
                    goTower = false;
                }
            });
        }
    }
}

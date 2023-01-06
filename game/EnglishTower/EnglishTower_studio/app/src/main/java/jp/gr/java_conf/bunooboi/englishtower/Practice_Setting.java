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
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Practice_Setting extends Activity {
    LinearLayout root;//親ViewGroup
    Animation animstart;
    Animation animend;
    Timer t;
    ArrayList<String> list = new ArrayList<>();//spinnerのデータ用
    Spinner spinner1;
    Spinner spinner2;
    MyAdapter adapter1;
    MyAdapter adapter2;
    Button button[] = new Button[4];
    boolean endthis = false;//endthistimer実行済みか
    boolean startpractice = false;//startpracticetimer実行済みか

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.practicesetting);

        Practice.minlevel = 0;
        Practice.question = 10;
        Practice.nowquestion = 1;
        Practice.practicemode = true;

        root = (LinearLayout) findViewById(R.id.root);
        animstart = AnimationUtils.loadAnimation(this, R.anim.startthis);
        animend = AnimationUtils.loadAnimation(this, R.anim.endthis);

        button[0] = (Button) findViewById(R.id.button1);
        button[1] = (Button) findViewById(R.id.button2);
        button[2] = (Button) findViewById(R.id.button3);
        button[3] = (Button) findViewById(R.id.button4);
        button[0].setText("練習モード");
        button[1].setText("暗記モード");
        button[2].setText("メイン");
        button[3].setText("スタート");
        button[0].setTextColor(Color.WHITE);
        button[1].setTextColor(Color.WHITE);
        for (Button btn : button) {
            btn.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
        }
        button[0].setEnabled(false);

        final TextView textview[] = new TextView[2];
        textview[0] = (TextView) findViewById(R.id.textview1);
        textview[1] = (TextView) findViewById(R.id.textview2);
        textview[0].setText("レベル");
        textview[1].setText("出題数");
        for (TextView tv : textview) {
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(12 * Tower.getScaleSize(getApplicationContext()));
        }

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        adapter1 = new MyAdapter(this);
        adapter2 = new MyAdapter(this);
        list.add("1~10");
        list.add("11~20");
        list.add("21~30");
        list.add("31~40");
        list.add("41~50");
        list.add("51~60");
        list.add("61~70");
        list.add("71~80");
        list.add("81~90");
        list.add("91~100");
        adapter1.setData(list);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sound.start3();
                String text = parent.getItemAtPosition(position).toString();
                if (Practice.practicemode) {//練習モードか
                    if (text.startsWith("1~")) {//レベル１なら
                        Practice.minlevel = Integer.parseInt(text.substring(0, 1)) - 1;
                    } else if (text.endsWith("100")) {//レベル１００なら
                        Practice.minlevel = Integer.parseInt(text.substring(0, 2)) - 1;
                    } else {
                        Practice.minlevel = Integer.parseInt(text.substring(0, 2)) - 1;
                    }
                } else {//暗記モード
                    Practice.minlevel = Integer.parseInt(text) - 1;
                    Practice.question = DataBase.word.get(Practice.minlevel).size();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        list = new ArrayList<>();
        list.add("10");
        list.add("20");
        list.add("30");
        list.add("40");
        list.add("50");
        list.add("60");
        list.add("70");
        list.add("80");
        list.add("90");
        list.add("100");
        adapter2.setData(list);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sound.start3();
                if (Practice.practicemode) {//練習モード
                    Practice.question = position * 10 + 10;
                } else {//暗記モード
                    if (position == 0) {
                        Practice.randommode = false;
                    } else {
                        Practice.randommode = true;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        button[0].setOnClickListener(new OnClickListener() {//練習モードボタン押したとき
            @Override
            public void onClick(View v) {
                Sound.button();
                button[0].setEnabled(false);
                button[1].setEnabled(true);
                textview[1].setText("出題数");

                Practice.practicemode = true;
                list = new ArrayList<String>();
                list.add("1~10");
                list.add("11~20");
                list.add("21~30");
                list.add("31~40");
                list.add("41~50");
                list.add("51~60");
                list.add("61~70");
                list.add("71~80");
                list.add("81~90");
                list.add("91~100");
                adapter1 = new MyAdapter(Practice_Setting.this);
                adapter1.setData(list);
                spinner1.setAdapter(adapter1);
                list = new ArrayList<>();
                list.add("10");
                list.add("20");
                list.add("30");
                list.add("40");
                list.add("50");
                list.add("60");
                list.add("70");
                list.add("80");
                list.add("90");
                list.add("100");
                adapter2 = new MyAdapter(Practice_Setting.this);
                adapter2.setData(list);
                spinner2.setAdapter(adapter2);
                Practice.minlevel = 0;
                Practice.question = 10;
            }
        });
        button[1].setOnClickListener(new OnClickListener() {//暗記モードボタン押したとき
            @Override
            public void onClick(View v) {
                Sound.button();
                button[0].setEnabled(true);
                button[1].setEnabled(false);
                textview[1].setText("並び順");

                Practice.practicemode = false;
                list = new ArrayList<String>();
                for (int i = 1; i < 101; i++) {
                    list.add(String.valueOf(i));
                }
                adapter1 = new MyAdapter(Practice_Setting.this);
                adapter1.setData(list);
                spinner1.setAdapter(adapter1);
                list = new ArrayList<String>();
                list.add("順番");
                list.add("ランダム");
                adapter2 = new MyAdapter(Practice_Setting.this);
                adapter2.setData(list);
                spinner2.setAdapter(adapter2);
                Practice.minlevel = 0;
                Practice.question = DataBase.word.get(0).size();
            }
        });
        button[2].setOnClickListener(new OnClickListener() {//メイン
            @Override
            public void onClick(View v) {
                Sound.start2();
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
        button[3].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {//スタート
                endthis = true;
                for (Button btn : button) {
                    btn.setEnabled(false);
                }
                spinner1.setEnabled(false);
                spinner2.setEnabled(false);
                Sound.start5();
                animstart.setDuration(3000);
                animend.setDuration(3000);
                root.startAnimation(animend);
                if (t != null) {
                    t.cancel();
                    t = null;
                }
                t = new Timer();//画面暗転
                t.schedule(new EndThisTimer(), 3000);
            }
        });
    }

    class EndThisTimer extends TimerTask {//画面暗転
        Handler handler;

        public EndThisTimer() {
            handler = new Handler();
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    startpractice = true;
                    root.startAnimation(animstart);
                    root.removeAllViews();
                    root.setBackgroundResource(R.drawable.zzz_practice);
                    TextView textview = new TextView(Practice_Setting.this);
                    textview.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
                    textview.setText("第1問");
                    textview.setTextSize(30 * Tower.getScaleSize(getApplicationContext()));
                    textview.setTextColor(Color.WHITE);
                    textview.setShadowLayer(5, 0, 0, Color.BLACK);
                    textview.setGravity(Gravity.CENTER);
                    root.addView(textview);
                    if (t != null) {
                        t.cancel();
                        t = null;
                    }
                    t = new Timer();//画面明るくなる
                    t.schedule(new StartPracticeTimer(), 3000);
                }
            });
        }
    }

    class StartPracticeTimer extends TimerTask {//練習ページ移動
        Handler handler;

        public StartPracticeTimer() {
            handler = new Handler();
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    endthis = false;
                    startpractice = false;
                    Practice_End.t1 = System.currentTimeMillis();
                    root.setAlpha(1.0f);
                    Intent i = new Intent(getApplicationContext(), Practice.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                    overridePendingTransition(0, 0);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (startpractice) {
            root.startAnimation(animstart);
            root.removeAllViews();
            root.setBackgroundResource(R.drawable.zzz_practice);
            TextView textview = new TextView(Practice_Setting.this);
            textview.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            textview.setText("第1問");
            textview.setTextSize(30 * Tower.getScaleSize(getApplicationContext()));
            textview.setTextColor(Color.WHITE);
            textview.setShadowLayer(5, 0, 0, Color.BLACK);
            textview.setGravity(Gravity.CENTER);
            root.addView(textview);
            if (t != null) {
                t.cancel();
                t = null;
            }
            t = new Timer();//画面明るくなる
            t.schedule(new StartPracticeTimer(), 3000);
        } else if (endthis) {
            endthis = true;
            for (Button btn : button) {
                btn.setEnabled(false);
            }
            spinner1.setEnabled(false);
            spinner2.setEnabled(false);
            Sound.start5();
            animstart.setDuration(3000);
            animend.setDuration(3000);
            root.startAnimation(animend);
            if (t != null) {
                t.cancel();
                t = null;
            }
            t = new Timer();//画面暗転
            t.schedule(new EndThisTimer(), 3000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (t != null) {
            t.cancel();
            t = null;
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

package jp.gr.java_conf.bunooboi.mydic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hiro on 2016/08/19.
 */
public class GameFall extends Activity {
    static ArrayList<Integer> participationConfig = new ArrayList<>();//出題する辞書番号
    static Sentence questionSentence;
    static View_FitTextView questionText;//問題文
    static TextView countText;//正解カウント文
    static TextView randomNumberText;//ランダム番号文
    FrameLayout gameMain;//メインレイアウト
    static int disp_w;// 画面幅
    static int disp_h;// 画面高さ
    static int bou_w;//棒の幅
    static int bou_h;//棒の高さ
    static int limit_h;//limitの高さ
    static long t1;
    static long t2;
    static int startCount;//始まりまでのカウントダウン
    static String randomNumber;//ランダム番号
    static String color;//ランダム色
    static boolean moveystoper = false;
    static boolean questionstoer = false;
    static Timer mainTimer;//メインタイマー
    static Timer startTimer;//スタートタイマー
    static Bou bou[] = new Bou[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.gamefall);

        t1 = System.currentTimeMillis() / 1000 + 4;

        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        disp_w = size.x;
        disp_h = size.y;
        bou_w = disp_w / 4;
        bou_h = disp_h / 6;
        randomNumber = createRandomNumber();
        color = createRandomColor();

        findViewById(R.id.root).setBackgroundColor(Color.parseColor(createRandomColor()));
        questionText = (View_FitTextView) findViewById(R.id.questionText);
        questionText.setTextColor(Color.BLACK);
        questionText.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        questionText.setPadding(10, 0, 10, 0);
        questionSentence = getQuestion(true);
        questionText.setText(questionSentence.getDescription());
        questionText.post(new Runnable() {
            @Override
            public void run() {
                limit_h = bou_h * 5 - questionText.getHeight() - Main.getPctY(getApplicationContext(), 2) * 2;
            }
        });
        countText = (TextView) findViewById(R.id.countText);
        countText.setTextColor(Color.GREEN);
        countText.setShadowLayer(5, 0, 0, Color.BLACK);
        countText.setTextSize(30 * Main.getScaleSize(getApplicationContext()));

        final View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(disp_w, Main.getPctY(getApplicationContext(), 1)));
        view.setBackgroundResource(R.drawable.setting_custom);
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setTranslationY(limit_h - view.getHeight());
            }
        });
        gameMain = (FrameLayout) findViewById(R.id.gameMain);
        gameMain.addView(view);

        bou[0] = new Bou(this, 0, 0);
        bou[1] = new Bou(this, 1, 1);
        bou[2] = new Bou(this, 2, 2);
        bou[3] = new Bou(this, 3, 3);
        bou[4] = new Bou(this, 4, 4);
        bou[5] = new Bou(this, 5, 4);
        for (Bou b : bou) {
            gameMain.addView(b.button);
        }
        for (int i = 0; i < bou.length; i++) {
            final int finalI = i;
            bou[i].button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionSentence.getTitle().equals(bou[finalI].text)) {//ゲーム終了
                        Sound.fall_fin();
                        fin(bou[finalI]);
                    } else {//棒けす
                        bou[finalI].init();
                        Sound.fall_touch();
                    }
                }
            });
            bou[i].button.setVisibility(View.INVISIBLE);
        }
        randomNumberText = new TextView(this);
        randomNumberText.setLayoutParams(new LinearLayout.LayoutParams(disp_w, disp_h / 9 * 7));
        randomNumberText.setTextSize(100 * Main.getScaleSize(getApplicationContext()));
        randomNumberText.setTextColor(Color.parseColor(color));
        randomNumberText.setShadowLayer(5, 0, 0, Color.BLACK);
        randomNumberText.setGravity(Gravity.CENTER);
        gameMain.addView(randomNumberText);
    }

    static void start() {//メインタイマースタート
        startCount = 3;
        final Handler handler = new Handler();
        startTimer = new Timer();
        startTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (startCount <= -1) {
                            startTimer.cancel();
                            startTimer = null;
                        } else if (startCount <= 0) {
                            randomNumberText.setText("スタート");
                        } else {
                            randomNumberText.setText(String.valueOf(startCount));
                        }
                        startCount--;
                    }
                });
            }
        }, 0, 1000);
        Sound.fall_start();
        mainTimer = new Timer();
        mainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        t2 = System.currentTimeMillis() / 1000;
                        countText.setText(String.valueOf(t2 - t1));
                        if ((t2 - t1) % 25 == 0) {
                            randomNumberText.setText(randomNumber);
                        }
                        if ((t2 - t1) % 30 == 0) {
                            questionSentence = getQuestion(true);
                            questionText.setText(questionSentence.getDescription());
                            for (Bou b : bou) {
                                b.button.setEnabled(false);
                                b.init();
                                if (moveystoper == false) {
                                    b.movey++;
                                }
                            }
                            moveystoper = true;
                            if (questionstoer == false) {
                                Sound.fall_question();
                                questionstoer = true;
                            }
                        } else {
                            randomNumberText.setText("");
                            for (Bou b : bou) {
                                b.move();
                            }
                            moveystoper = false;
                            questionstoer = false;
                        }
                    }
                });
            }
        }, 4000, 16);
    }

    void stop() {
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer = null;
        }
        for (Bou b : bou) {
            b.button.setEnabled(false);
        }
    }

    void fin(Bou bou) {//終了
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer = null;
        }
        Sound.stopMediaPlayer1();
        randomNumberText.setText("");
        bou.button.setBackgroundResource(R.drawable.fall_bou_2);
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), GameFin.class));
                finish();
                overridePendingTransition(0, 0);
            }
        }, 3000);
    }

    public static Sentence getQuestion(boolean getLevel) {
        int configIndex = (int) Math.floor(Math.random() * GameFall.participationConfig.size());
        Sentence sentence = new Sentence("", 1, new String[]{""}, "", "", "");
        if (getLevel) {
            switch ((int) Math.floor(Math.random() * 6)) {
                case 0:
                    sentence = questionLevel(configIndex, 1);
                    break;
                case 1:
                case 2:
                    sentence = questionLevel(configIndex, 2);
                    break;
                case 3:
                case 4:
                case 5:
                    sentence = questionLevel(configIndex, 3);
                    break;
            }
        } else {
            do {
                sentence = Sentences.sentences.get(GameFall.participationConfig.get(configIndex)).get((int) Math.floor(Math.random() * Sentences.sentences.get(GameFall.participationConfig.get(configIndex)).size()));
            } while (sentence.getLevel() == 0);
        }
        return sentence;
    }

    static Sentence questionLevel(int configIndex, int level) {
        Sentence sentence;
        do {
            sentence = Sentences.sentences.get(GameFall.participationConfig.get(configIndex)).get((int) Math.floor(Math.random() * Sentences.sentences.get(GameFall.participationConfig.get(configIndex)).size()));
        } while (sentence.getLevel() != level && sentence.getLevel() == 0);
        return sentence;
    }

    public static String createRandomNumber() {
        String str[] = new String[4];
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf((int) Math.floor(Math.random() * 10));
            if (str[i].equals("10")) {
                str[i] = "・";
            }
        }
        return str[0] + str[1] + "-" + str[2] + str[3];
    }

    public static String createRandomColor() {
        char str[] = new char[6];
        String color = "0123456789ABCDEF";
        for (int i = 0; i < str.length; i++) {
            str[i] = color.charAt((int) Math.floor(Math.random() * color.length()));
        }
        String s = "#" + str[0] + str[1] + str[2] + str[3] + str[4] + str[5];
        if (s.equals("#000000")) {
            s = "#ffdd99";
        }
        return s;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            stop();
            View_MyDialog.id = 1;
            View_MyDialog.text = "メインに戻りますか？";
            View_MyDialog dialog = new View_MyDialog();
            dialog.show(getFragmentManager(), "test");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();
        start();
        Sound.startMediaPlayer1(1);
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
        Sound.stopMediaPlayer1();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Sound.releaseMediaPlayer1();
    }

    class Bou {
        Button button;
        int x;
        int y;
        int movey;
        int level;
        int myx;
        String text;
        boolean stoper = false;

        public Bou(Context context, int level, int myx) {
            button = new Button(context);
            button.setLayoutParams(new LinearLayout.LayoutParams(GameFall.bou_w, GameFall.bou_h));
            button.setAllCaps(false);
            button.setBackgroundResource(R.drawable.fall_bou_1);
            button.setTextSize(26 * Main.getScaleSize(context));
            button.setTextColor(Color.BLACK);
            button.setPadding(Main.getPctX(context, 3), Main.getPctY(context, 2), Main.getPctX(context, 3), Main.getPctY
                    (context, 2));
            this.level = level;
            this.myx = myx;
            movey = 5;
            init();
        }

        synchronized void init() {
            x = setX();
            y = 0 - GameFall.bou_h - level * 100;
            button.layout(x, y, x + GameFall.bou_w, y + GameFall.bou_h);
            if ((int) Math.floor(Math.random() * 5) == 0) {
                text = questionSentence.getTitle();
            } else {
                text = getQuestion(false).getTitle();
            }
            button.setText(text);
        }

        synchronized void initnotTextChange() {
            x = setX();
            y = 0 - GameFall.bou_h - level * 100;
            button.layout(x, y, x + GameFall.bou_w, y + GameFall.bou_h);
            button.setText(text);
        }

        synchronized void move() {
            button.layout(x, y, x + GameFall.bou_w, y + GameFall.bou_h);
            if (button.getVisibility() == View.INVISIBLE) {
                button.setVisibility(View.VISIBLE);
            }
            if (button.isEnabled() == false) {
                button.setEnabled(true);
            }
            if (y > limit_h) {//limitを超える
                if (!questionSentence.getTitle().equals(text)) {//間違いだったら
                    if (stoper == false) {
                        Sound.fall_fin();
                        stoper = true;
                    }
                    fin(this);
                }
            }
            if (y > GameFall.disp_h) {//正解
                init();
            }
            if (isHit(bou)) {
                initnotTextChange();
            }
            y += movey;
        }

        boolean isHit(Bou bou[]) {
            for (Bou b : bou) {
                if (b.y + GameFall.bou_h >= y - 20 && b.y <= y + GameFall.bou_h + 20 && b.x == x) {
                    if (b.level > level)
                        return true;
                }
            }
            return false;
        }

        int setX() {
            switch (myx) {
                case 0:
                    return 0;
                case 1:
                    return GameFall.disp_w / 4;
                case 2:
                    return GameFall.disp_w / 4 * 2;
                case 3:
                    return GameFall.disp_w / 4 * 3;
                case 4:
                    int random = (int) Math.floor(Math.random() * 4);
                    switch (random) {
                        case 0:
                            return 0;
                        case 1:
                            return GameFall.disp_w / 4;
                        case 2:
                            return GameFall.disp_w / 4 * 2;
                        case 3:
                            return GameFall.disp_w / 4 * 3;
                    }
            }
            return 0;
        }
    }


}

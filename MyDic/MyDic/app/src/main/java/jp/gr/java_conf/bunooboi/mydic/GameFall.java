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

    static TextView questionText;//問題文
    static TextView countText;//正解カウント文
    FrameLayout gameMain;//メインレイアウト
    static TextView randomNumberText;//ランダム番号文
    static int disp_w;// 画面幅
    static int disp_h;// 画面高さ
    static int bou_w;//棒の幅
    static int bou_h;//棒ぼ高さ
    static long t1;
    static long t2;
    static int count;//正解数カウント
    static String randomNumber;//ランダム番号
    static String color;//ランダム色
    static Timer mainTimer;

    static Bou bou[] = new Bou[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.gamefall);

        t1 = System.currentTimeMillis() / 1000;
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
        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setTextColor(Color.parseColor(color));
        questionText.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        questionSentence = getQuestion();
        questionText.setText(questionSentence.getDescription());
        countText = (TextView) findViewById(R.id.countText);
        countText.setTextColor(Color.parseColor(color));
        countText.setShadowLayer(5, 0, 0, Color.BLACK);
        countText.setTextSize(30 * Main.getScaleSize(getApplicationContext()));
        countText.setText(String.valueOf(count));
        gameMain = (FrameLayout) findViewById(R.id.gameMain);
        randomNumberText = new TextView(this);
        randomNumberText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        randomNumberText.setTextSize(100 * Main.getScaleSize(getApplicationContext()));
        randomNumberText.setTextColor(Color.parseColor(color));
        randomNumberText.setShadowLayer(5, 0, 0, Color.BLACK);
        randomNumberText.setGravity(Gravity.CENTER);
        gameMain.addView(randomNumberText);

        bou[0] = new Bou(this, 0);
        bou[1] = new Bou(this, 1);
        bou[2] = new Bou(this, 2);
        bou[3] = new Bou(this, 3);
        bou[4] = new Bou(this, 4);
        bou[5] = new Bou(this, 5);
        bou[6] = new Bou(this, 6);
        bou[7] = new Bou(this, 7);
        gameMain.addView(bou[0].button);
        gameMain.addView(bou[1].button);
        gameMain.addView(bou[2].button);
        gameMain.addView(bou[3].button);
        gameMain.addView(bou[4].button);
        gameMain.addView(bou[5].button);
        gameMain.addView(bou[6].button);
        gameMain.addView(bou[7].button);
        for (int i = 0; i < bou.length; i++) {
            final int finalI = i;
            bou[i].button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionSentence.getTitle().equals(bou[finalI].text)) {//ゲーム終了
                        bou[finalI].button.setBackgroundResource(R.drawable.fall_bou_2);
                        bou[finalI].button.layout(bou[finalI].x, bou[finalI].y, bou[finalI].x + GameFall.bou_w, bou[finalI].y + GameFall.bou_h);
                        randomNumberText.setText("");
                        randomNumberText.setBackgroundResource(R.drawable.no);
                        if (mainTimer != null) {
                            mainTimer.cancel();
                            mainTimer = null;
                        }
                        Timer t = new Timer();
                        t.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), GameFin.class));
                                finish();
                            }
                        }, 3000);
                    } else {//棒けす
                        countText.setText(String.valueOf(++count));
                        bou[finalI].init();
                    }
                }
            });
        }
        start();
    }

    static void start() {
        final Handler handler = new Handler();
        mainTimer = new Timer();
        mainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        t2 = System.currentTimeMillis() / 1000;
                        for (Bou b : bou) {
                            b.move();
                        }
                        if ((t2 - t1) % 30 == 0) {
                            randomNumberText.setText(randomNumber);
                            questionSentence = getQuestion();
                            questionText.setText(questionSentence.getDescription());
                        } else {
                            randomNumberText.setText("");
                        }
                    }
                });
            }
        }, 0, 32);
    }

    public static Sentence getQuestion() {
        Sentence sentence;
        do {
            int random1 = (int) Math.floor(Math.random() * GameFall.participationConfig.size());
            int random2 = (int) Math.floor(Math.random() * Sentences.sentences.get(GameFall.participationConfig.get
                    (random1)).size());
            sentence = Sentences.sentences.get(GameFall.participationConfig.get(random1)).get(random2);
            System.out.println(sentence.getTitle());
        } while (sentence.getLevel() == 0);
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
            if (mainTimer != null) {
                mainTimer.cancel();
                mainTimer = null;
            }
            View_MyDialog.id = 1;
            View_MyDialog.text = "メインに戻りますか？";
            View_MyDialog dialog = new View_MyDialog();
            dialog.show(getFragmentManager(), "test");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

class Bou {
    Button button;
    int x;
    int y;
    int level;
    String text;

    public Bou(Context context, int level) {
        button = new Button(context);
        button.setLayoutParams(new LinearLayout.LayoutParams(GameFall.bou_w, GameFall.bou_h));
        button.setAllCaps(false);
        button.setBackgroundResource(R.drawable.fall_bou_1);
        button.setTextSize(20 * Main.getScaleSize(context));
        button.setTextColor(Color.WHITE);
        button.setPadding(Main.getPctX(context, 2), Main.getPctY(context, 2), Main.getPctX(context, 2), Main.getPctY
                (context, 2));
        this.level = level;
        init();
    }

    void init() {
        x = randomX();
        y = 0 - GameFall.bou_h - 200;
        button.layout(x, y, x + GameFall.bou_w, y + GameFall.bou_h);
        text = GameFall.getQuestion().getTitle();
        button.setText(text);
    }

    void move() {
        y += 10;
        button.layout(x, y, x + GameFall.bou_w, y + GameFall.bou_h);
        if (y > GameFall.disp_h) {
            init();
        }
        if (isHit(GameFall.bou)) {
            init();
        }
    }

    boolean isHit(Bou bou[]) {
        for (Bou b : bou) {
            if (b.y + GameFall.bou_h >= y && b.y <= y + GameFall.bou_h) {
                if (b.level > level)
                    return true;
            }
        }
        return false;
    }

    int randomX() {
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
        return 0;
    }
}


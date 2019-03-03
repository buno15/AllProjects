package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.annotation.SuppressLint;
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

import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.mydic.DisplayManager;
import jp.gr.java_conf.bunooboi.mydic.Game;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sound;
import jp.gr.java_conf.bunooboi.mydic.Word;

/**
 * Created by hiro on 2016/08/19.
 */
public class GameFall extends Activity {
    TextView typeText;
    TextView questionText;//問題文
    TextView countDownText;
    FrameLayout gameMain;//メインレイアウト

    int display_w;// 画面幅
    int display_h;// 画面高さ
    int bou_w;//棒の幅
    int bou_h;//棒の高さ
    int limit_w;//bou設置のx座標限界
    int limit_h;//ゲームオーバーする位置
    int startCount;//始まりまでのカウントダウン
    long t1;
    long t2;
    static int answerIndex = 0;//答え番号
    boolean answerTagMode = false;//答えがdescriptionかtagか
    boolean move_y_accelerate = false;
    boolean question_stopper = false;//終了音重複無効
    boolean first_sound = true;

    String answerString;//答えの出題のテキスト

    Timer mainTimer;//メインタイマー
    Timer startTimer;//スタートタイマー
    Bou bou[] = new Bou[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.gamefall);


        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        display_w = size.x;
        display_h = size.y;
        bou_w = display_w / 4;
        bou_h = display_h / 6;

        limit_w = display_w - bou_w;

        typeText = findViewById(R.id.typeText);
        questionText = findViewById(R.id.questionText);
        questionText.post(new Runnable() {
            @Override
            public void run() {
                limit_h = bou_h * 5 - questionText.getHeight() - Main.getPctY(getApplicationContext(), 2) * 2;
            }
        });

        final View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(display_w, Main.getPctY(getApplicationContext(), 1)));
        view.setBackgroundResource(R.drawable.gamefall_limitt);
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setTranslationY(limit_h - view.getHeight());
            }
        });
        gameMain = findViewById(R.id.gameMain);
        gameMain.addView(view);

        for (int i = 0; i < bou.length; i++) {
            bou[i] = new Bou(this, i);
            bou[i].button.setVisibility(View.INVISIBLE);
            gameMain.addView(bou[i].button);
        }

        countDownText = new TextView(this);
        countDownText.setLayoutParams(new LinearLayout.LayoutParams(display_w, display_h / 9 * 7));
        countDownText.setTextSize(100 * DisplayManager.getScaleSize(getApplicationContext()));
        countDownText.setShadowLayer(5, 0, 0, Color.BLACK);
        countDownText.setGravity(Gravity.CENTER);
        gameMain.addView(countDownText);

        for (int i = 0; i < bou.length; i++) {
            final int finalI = i;
            bou[i].button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (judge(bou[finalI])) {
                        Sound.fall_touch();
                        bou[finalI].init();
                    } else {
                        Sound.fall_fin();
                        fin(bou[finalI]);
                    }
                }
            });
        }
        t1 = System.currentTimeMillis() / 1000 + 5;
    }

    void makeQuestion() {
        answerIndex = (int) Math.floor(Math.random() * Game.words.size());
        Game.answer = Game.words.get(answerIndex);
        answerSelect();
    }

    void answerSelect() {//答えがdescriptionかtagか選択
        if (Game.answer.getTag().size() == 0) {
            answerTagMode = false;
            typeText.setText("Description");
            while (true) {
                int index = (int) Math.floor(Math.random() * 3);
                if (!Game.answer.getDescription()[index].equals("")) {
                    answerString = Game.answer.getDescription()[index];
                    break;
                }
            }
        } else {
            if ((int) Math.floor(Math.random() * 2) == 0) {
                answerTagMode = false;
                typeText.setText("Description");
                while (true) {
                    int index = (int) Math.floor(Math.random() * 3);
                    if (!Game.answer.getDescription()[index].equals("")) {
                        answerString = Game.answer.getDescription()[index];
                        break;
                    }
                }
            } else {
                answerTagMode = true;
                typeText.setText("Tag");
                int index = (int) Math.floor(Math.random() * Game.answer.getTag().size());
                answerString = Game.answer.getTag().get(index);
            }
        }
    }

    boolean judge(Bou bou) {//正解判定
        if (answerTagMode) {
            return bou.word.searchTag(answerString);
        } else {
            return bou.word.searchDescription(answerString);
        }
    }

    public void start() {//カウントダウンタイマースタート
        Sound.game_start();
        startCount = 3;
        final Handler handler = new Handler();
        startTimer = new Timer();
        startTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if (startCount <= -1) {
                            if (startTimer != null) {
                                startTimer.cancel();
                                startTimer = null;
                            }
                            countDownText.setText("");
                            Sound.startMediaPlayer(1);
                            makeQuestion();
                            questionText.setText(answerString);
                            mainStart();
                        } else if (startCount <= 0) {
                            countDownText.setText("Start");
                        } else {
                            countDownText.setText(String.valueOf(startCount));
                        }
                        startCount--;
                    }
                });
            }
        }, 0, 1000);
    }

    void mainStart() {//メインタイマースタート
        final Handler handler = new Handler();
        mainTimer = new Timer();
        mainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        t2 = System.currentTimeMillis() / 1000;
                        if ((t2 - t1) % 20 == 0 || t2 - t1 <= -1) {
                            makeQuestion();
                            questionText.setText(answerString);
                            for (Bou b : bou) {
                                b.button.setEnabled(false);
                                b.init();
                                if (!move_y_accelerate) {
                                    b.move_y++;
                                }
                            }
                            move_y_accelerate = true;
                            if (!question_stopper) {
                                if (first_sound) {
                                    Sound.fall_question_long();
                                    first_sound = false;
                                } else
                                    Sound.fall_question();
                                question_stopper = true;
                            }
                        } else {
                            for (Bou b : bou) {
                                b.move();
                            }
                            move_y_accelerate = false;
                            question_stopper = false;
                        }
                    }
                });
            }
        }, 0, 16);
    }

    void stop() {
        if (startTimer != null) {
            startTimer.cancel();
            startTimer = null;
        }
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer = null;
        }
        for (Bou b : bou) {
            b.button.setEnabled(false);
        }
        Sound.soundPool.stop(Sound.game_start_keep);
    }

    void fin(Bou b) {//終了
        b.button.setBackgroundResource(R.drawable.bou_batu);
        if (startTimer != null) {
            startTimer.cancel();
            startTimer = null;
        }
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer = null;
        }
        for (Bou bo : bou) {
            bo.button.setEnabled(false);
        }
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), GameFin.class));
                finish();
                overridePendingTransition(0, 0);
            }
        }, 3000);
        Sound.stopMediaPlayer();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
        Sound.stopMediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Sound.releaseMediaPlayer();
    }

    class Bou {
        Button button;
        int x = 0;
        int y = 0;
        int move_y;
        int id;
        String text = "";
        Word word;
        boolean soundStopper = false;

        private Bou(Context context, int id) {
            button = new Button(context);
            button.setLayoutParams(new LinearLayout.LayoutParams(bou_w, bou_h));
            button.setAllCaps(false);
            button.setTextColor(Color.BLACK);
            button.setPadding(Main.getPctX(context, 3), Main.getPctY(context, 2), Main.getPctX(context, 3), Main.getPctY
                    (context, 2));
            button.setBackgroundResource(R.drawable.bou_normal);
            this.id = id;
            move_y = 5;
            init();
        }

        synchronized void init() {
            int j = (int) Math.floor(Math.random() * Game.words.size());
            word = Game.words.get(j);
            text = word.getWord();
            button.setText(text);
            x = setX();
            y = 0 - bou_h - id * 100;
            button.layout(x, y, x + bou_w, y + bou_h);
        }

        synchronized void initNotTextChange() {
            x = setX();
            y = 0 - bou_h - id * 100;
            button.layout(x, y, x + bou_w, y + bou_h);
            button.setText(text);
        }

        synchronized void move() {
            button.layout(x, y, x + bou_w, y + bou_h);
            if (button.getVisibility() == View.INVISIBLE) {
                button.setVisibility(View.VISIBLE);
            }
            if (!button.isEnabled()) {
                button.setEnabled(true);
            }
            if (y > limit_h) {//limitを超える
                if (judge(this)) {//limitを超えたのが正解だったら(不正解)
                    if (!soundStopper) {
                        soundStopper = true;
                        Sound.fall_fin();
                        fin(this);
                    }
                }
            }
            if (y > display_h) {//limitを超える(正解)
                init();
            }
            if (isHit(bou)) {
                initNotTextChange();
            }
            y += move_y;
        }

        boolean isHit(Bou bou[]) {
            for (Bou b : bou) {
                if (b.y + bou_h >= y - 20 && b.y <= y + bou_h + 20 && b.x == x) {
                    if (b.id > id)
                        return true;
                }
            }
            return false;
        }

        int setX() {
            switch (id) {
                case 0:
                    return 0;
                case 1:
                    return display_w / 4;
                case 2:
                    return display_w / 4 * 2;
                case 3:
                    return display_w / 4 * 3;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    int random = (int) Math.floor(Math.random() * 4);
                    switch (random) {
                        case 0:
                            return 0;
                        case 1:
                            return display_w / 4;
                        case 2:
                            return display_w / 4 * 2;
                        case 3:
                            return display_w / 4 * 3;
                    }
            }
            return 0;
        }
    }
}

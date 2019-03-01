package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import jp.gr.java_conf.bunooboi.mydic.DisplayManager;
import jp.gr.java_conf.bunooboi.mydic.Game;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Word;

/**
 * Created by hiro on 2016/08/19.
 */
public class GameFall extends Activity {
    TextView questionText;//問題文
    TextView countDownText;
    FrameLayout gameMain;//メインレイアウト

    int display_w;// 画面幅
    int display_h;// 画面高さ
    int bou_w;//棒の幅
    int bou_h;//棒の高さ
    int limit_h;//ゲームオーバーする位置
    int startCount;//始まりまでのカウントダウン
    int count = 0;//ゲーム時間カウント
    static int answerIndex = 0;//答え番号
    boolean move_y_stopper = false;
    boolean question_stopper = false;

    ArrayList<Word> question = new ArrayList<>();
    String tagAnswer;

    Timer mainTimer;//メインタイマー
    Timer startTimer;//スタートタイマー
    Bou bou[] = new Bou[6];

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

        questionText = findViewById(R.id.questionText);
        questionText.post(new Runnable() {
            @Override
            public void run() {
                limit_h = bou_h * 5 - questionText.getHeight() - Main.getPctY(getApplicationContext(), 2) * 2;
            }
        });

        final View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(display_w, Main.getPctY(getApplicationContext(), 1)));
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setTranslationY(limit_h - view.getHeight());
            }
        });
        gameMain = findViewById(R.id.gameMain);
        gameMain.addView(view);

        countDownText = new TextView(this);
        countDownText.setLayoutParams(new LinearLayout.LayoutParams(display_w, display_h / 9 * 7));
        countDownText.setTextSize(100 * DisplayManager.getScaleSize(getApplicationContext()));
        countDownText.setShadowLayer(5, 0, 0, Color.BLACK);
        countDownText.setGravity(Gravity.CENTER);
        gameMain.addView(countDownText);

        makeQuestion();
      /*  for (int i = 0; i < bou.length; i++) {
            final int finalI = i;
            bou[i].button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (answer.getWord().equals(bou[finalI].text)) {//ゲーム終了
                        fin(bou[finalI]);
                    } else {//棒けす
                        bou[finalI].init();
                    }
                }
            });
            bou[i].button.setVisibility(View.INVISIBLE);
        }*/
    }

    void makeQuestion() {
        question.clear();
        for (int i = 0; i < bou.length; i++) {
            bou[i] = new Bou(this);
            gameMain.addView(bou[i].button);
        }
        for (int i = 0; i < 6; i++) {
            while (true) {
                int j = (int) Math.floor(Math.random() * Game.words.size());
                Word word = Game.words.get(j);
                if (question.indexOf(word) == -1) {
                    question.add(word);
                    bou[i].text = word.getWord();
                    bou[i].button.setText(bou[i].text);
                    break;
                }
            }
        }
        answerIndex = (int) Math.floor(Math.random() * 6);
        Game.answer = question.get(answerIndex);
        for (int i = 0; i < Game.answer.getTag().size(); i++)
            tagAnswer = Game.answer.getTag().get(i);
    }

    public void start() {//カウントダウンタイマースタート
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
                            if (startTimer != null) {
                                startTimer.cancel();
                                startTimer = null;
                            }
                            gameMain.removeAllViews();
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
                        if (count % 30 == 0) {
                            questionText.setText(tagAnswer);
                            for (Bou b : bou) {
                                b.button.setEnabled(false);
                                b.init();
                                if (move_y_stopper == false) {
                                    b.move_y++;
                                }
                            }
                            move_y_stopper = true;
                            if (question_stopper == false) {
                                question_stopper = true;
                            }
                        } else {
                            for (Bou b : bou) {
                                b.move();
                            }
                            move_y_stopper = false;
                            question_stopper = false;
                        }
                        count++;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            stop();
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(GameFall.this);
            alertDlg.setMessage("Will you finish the game?");
            alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), GameStart.class));
                    finish();
                }
            });
            alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    makeQuestion();
                }
            });
            alertDlg.create().show();
            return true;
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class Bou {
        Button button;
        int x = 0;
        int y = 0;
        int move_y = 0;
        String text = "";
        boolean stopper = false;

        public Bou(Context context) {
            button = new Button(context);
            button.setLayoutParams(new LinearLayout.LayoutParams(bou_w, bou_h));
            button.setAllCaps(false);
            button.setTextColor(Color.BLACK);
            button.setPadding(Main.getPctX(context, 3), Main.getPctY(context, 2), Main.getPctX(context, 3), Main.getPctY
                    (context, 2));
            button.setBackgroundResource(R.drawable.fall_bou_1);
            move_y = 5;
            init();
        }

        synchronized void init() {
            text = "";
            y = 0 - bou_h;
            button.layout(x, y, x + bou_w, y + bou_h);
        }

        synchronized void move() {
            button.layout(x, y, x + bou_w, y + bou_h);
            if (button.getVisibility() == View.INVISIBLE) {
                button.setVisibility(View.VISIBLE);
            }
            if (button.isEnabled() == false) {
                button.setEnabled(true);
            }
            if (y > limit_h) {//limitを超える
                if (!Game.answer.getWord().equals(text)) {//間違いだったら
                    if (stopper == false) {
                        stopper = true;
                    }
                    fin(this);
                }
            }
            if (y > display_h) {//正解
                init();
            }
            if (isHit(bou)) {
                init();
            }
            y += move_y;
        }

        boolean isHit(Bou bou[]) {
            for (Bou b : bou) {
                if (b.y + bou_h >= y - 20 && b.y <= y + bou_h + 20 && b.x == x) {
                    return true;
                }
            }
            return false;
        }
    }
}

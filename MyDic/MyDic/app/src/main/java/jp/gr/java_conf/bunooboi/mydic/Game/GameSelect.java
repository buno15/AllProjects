package jp.gr.java_conf.bunooboi.mydic.Game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.mydic.GameFin;
import jp.gr.java_conf.bunooboi.mydic.GameValue;
import jp.gr.java_conf.bunooboi.mydic.Main;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sentence;
import jp.gr.java_conf.bunooboi.mydic.Sound;
import jp.gr.java_conf.bunooboi.mydic.View.FitTextView;
import jp.gr.java_conf.bunooboi.mydic.View.MyDialog;

/**
 * Created by hiro on 2016/08/21.
 */
public class GameSelect extends Activity {
    public static Sentence answerSentence;//問題兼答え
    public static int question = 1;// 何問目か
    static int startCount;//始まりまでのカウントダウン
    static int timeCount;//制限時間

    TextView questionCountText;// 出題数テキスト
    TextView timeCountText;//制限時間テキスト
    LinearLayout questionLayout;// 問題レイアウト
    static TextView randomNumberText;// ランダム番号テキスト

    Timer timer_time;//制限時間タイマー
    static Timer timer_randomnumber;//ランダム番号タイマー
    Timer timer_yesno;// まるばつタイマー
    static Timer timer_start;//スタートタイマー
    static long t1;
    static long t2;
    static String randomNumber;
    boolean countstoper = false;
    selectQuestion sq;//問題オブジェクト

    @Override
    protected void onCreate(Bundle savedInstaneState) {
        super.onCreate(savedInstaneState);
        setContentView(R.layout.gameselect);

        t1 = System.currentTimeMillis() / 1000;
        randomNumber = GameValue.createRandomNumber();

        questionLayout = (LinearLayout) findViewById(R.id.questionlayout);

        questionCountText = (TextView) findViewById(R.id.questionCountText);
        questionCountText.setTextSize(40 * Main.getScaleSize(getApplicationContext()));
        questionCountText.setTextColor(Color.WHITE);
        timeCountText = (TextView) findViewById(R.id.timeCountText);
        timeCountText.setTextSize(40 * Main.getScaleSize(getApplicationContext()));
        timeCountText.setTextColor(Color.WHITE);

        randomNumberText = (TextView) findViewById(R.id.randomNumberText);
        randomNumberText.setText(randomNumber);
        randomNumberText.setTextSize(100 * Main.getScaleSize(getApplicationContext()));
        randomNumberText.setTextColor(Color.parseColor(GameValue.createRandomColor()));
        randomNumberText.setShadowLayer(5, 0, 0, Color.BLACK);

        invalidateQuestionCountText();
        createQuestion();
    }

    void sqButtonClick() {//selectQuestion.choicesButtonクリック処理
        for (int i = 0; i < sq.choicesButton.length; i++) {
            final int j = i;
            sq.choicesButton[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sound.select_enabled();
                    sqButtonClash(j);
                    nextAction(checkAnswer(sq.questions[j].getTitle()));
                }
            });
        }
    }

    void sqButtonClash(int id) {// selectQuestion.choicesButton壊す
        sq.choicesButton[id].setEnabled(false);
        sq.choicesButton[id].setText("");
    }

    void createQuestion() {//問題オブジェクト作成
        int random = (int) Math.floor(Math.random() * 4);
        switch (random) {
            case 0:
                sq = new selectQuestion(2);
                break;
            case 1:
                sq = new selectQuestion(4);
                break;
            case 2:
                sq = new selectQuestion(6);
                break;
            case 3:
                sq = new selectQuestion(8);
                break;
        }
        sqButtonClick();
    }

    void invalidateQuestionCountText() {// 問題数書き換え
        questionCountText.setText(String.valueOf(question) + "問目");
    }

    boolean checkAnswer(String answer) {//正解かどうか
        if (answerSentence.getTitle().equals(answer)) {
            return true;
        }
        return false;
    }

    void nextAction(boolean answer) {// 次のアクション選択
        if (timer_time != null) {
            timer_time.cancel();
            timer_time = null;
        }
        if (timer_yesno != null) {
            timer_yesno.cancel();
            timer_yesno = null;
        }
        timer_yesno = new Timer();
        if (answer) {// 正解
            Sound.select_yes();
            Sound.stopselect_count();
            timer_yesno.schedule(new YesNoTimer(R.drawable.yes), 0, 32);
        } else {
            Sound.select_no();
            Sound.stopselect_count();
            Sound.stopMediaPlayer1();
            timer_yesno.schedule(new YesNoTimer(R.drawable.no), 0, 32);
        }
    }

    public static void start() {
        startCount = 3;
        final Handler handler = new Handler();
        timer_start = new Timer();
        timer_start.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (startCount <= -1) {
                            Sound.startMediaPlayer1(0);
                            randomNumberText.setText("");
                            timer_start.cancel();
                            timer_start = null;
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
        Sound.gamestart();
        startTimerRandomNumber();
    }

    static void startTimerRandomNumber() {
        final Handler handler = new Handler();
        timer_randomnumber = new Timer();
        timer_randomnumber.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        t2 = System.currentTimeMillis() / 1000;
                        if ((t2 - t1) % 30 == 0) {
                            randomNumberText.setText(randomNumber);
                        } else {
                            randomNumberText.setText("");
                        }
                    }
                });
            }
        }, 4000, 1000);
    }

    void stop() {
        if (timer_time != null) {
            timer_time.cancel();
            timer_time = null;
        }
        if (timer_randomnumber != null) {
            timer_randomnumber.cancel();
            timer_randomnumber = null;
        }
        if (timer_yesno != null) {
            timer_yesno.cancel();
            timer_yesno = null;
        }
    }

    void fin() {
        Sound.select_no();
        timer_yesno = new Timer();
        timer_yesno.schedule(new YesNoTimer(R.drawable.no), 0, 32);
    }

    class YesNoTimer extends TimerTask {// まるばつタイマー
        long t1;
        long t2;
        boolean no;
        Handler handler = new Handler();

        public YesNoTimer(int image) {
            randomNumberText.setBackgroundResource(image);
            t1 = System.currentTimeMillis() / 1000;
            if (image == R.drawable.no) {
                no = true;
            }
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    t2 = System.currentTimeMillis() / 1000;
                    if (no) {
                        if (t2 - t1 >= 3) {
                            if (timer_yesno != null) {
                                timer_yesno.cancel();
                                timer_yesno = null;
                            }
                            startActivity(new Intent(getApplicationContext(), GameFin.class));
                            finish();
                        }
                    } else {
                        if (t2 - t1 >= 1) {
                            if (timer_yesno != null) {
                                timer_yesno.cancel();
                                timer_yesno = null;
                            }
                            timeCount = 7;
                            timer_time = new Timer();
                            timer_time.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (timeCount <= 0) {
                                                fin();
                                                timeCountText.setText("0");
                                                if (timer_time != null) {
                                                    timer_time.cancel();
                                                    timer_time = null;
                                                }
                                            } else if (timeCount <= 3) {
                                                if (countstoper == false) {
                                                    Sound.select_count();
                                                    countstoper = true;
                                                }
                                                timeCountText.setText(String.valueOf(timeCount));
                                                timeCount--;
                                            } else {
                                                countstoper = false;
                                                timeCountText.setText(String.valueOf(timeCount));
                                                timeCount--;
                                            }
                                        }
                                    });
                                }
                            }, 0, 1000);
                            randomNumberText.setBackgroundResource(R.drawable.empty);
                            questionLayout.removeAllViews();
                            question++;
                            invalidateQuestionCountText();
                            createQuestion();
                        }
                    }
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            stop();
            MyDialog.id = 0;
            MyDialog.text = "メインに戻りますか？";
            MyDialog dialog = new MyDialog();
            dialog.show(getFragmentManager(), "test");
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
        Sound.stopMediaPlayer1();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
        Sound.releaseMediaPlayer1();
    }

    class selectQuestion {
        Context context = GameSelect.this;
        FitTextView questionText;
        LinearLayout choicesLayout[];
        Button choicesButton[];
        Sentence questions[];

        public selectQuestion(int length) {
            if (length % 2 != 0)
                return;
            questionText = new FitTextView(context);
            choicesLayout = new LinearLayout[length / 2];
            choicesButton = new Button[length];
            questions = new Sentence[length];
            for (int i = 0; i < choicesLayout.length; i++)
                choicesLayout[i] = new LinearLayout(context);
            for (int i = 0; i < choicesButton.length; i++)
                choicesButton[i] = new Button(context);

            createQuestion();
            createQuestionText();
            createChoicesLayout();
            createChoicesButton();
        }

        void createQuestionText() {
            questionText.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
            questionText.setGravity(Gravity.CENTER);
            questionText.setBackgroundResource(R.drawable.fall_question);
            questionText.setPadding(10, 0, 10, 0);
            questionLayout.addView(questionText);
        }

        void createChoicesLayout() {
            for (LinearLayout cl : choicesLayout) {
                cl.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
                cl.setOrientation(LinearLayout.HORIZONTAL);
                questionLayout.addView(cl);
            }
        }

        void createChoicesButton() {
            for (int i = 0; i < choicesButton.length; i++) {
                choicesButton[i].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams
                        .MATCH_PARENT, 1));
                choicesButton[i].setTextSize(22 * Main.getScaleSize(getApplicationContext()));
                choicesButton[i].setTextColor(Color.WHITE);
                choicesButton[i].setAllCaps(false);
                choicesButton[i].setShadowLayer(5, 0, 0, Color.BLACK);
                choicesButton[i].setBackgroundResource(R.drawable.select_button_custom);
                switch (i) {
                    case 0:
                    case 1:
                        choicesLayout[0].addView(choicesButton[i]);
                        break;
                    case 2:
                    case 3:
                        choicesLayout[1].addView(choicesButton[i]);
                        break;
                    case 4:
                    case 5:
                        choicesLayout[2].addView(choicesButton[i]);
                        break;
                    case 6:
                    case 7:
                        choicesLayout[3].addView(choicesButton[i]);
                        break;
                }
            }
        }

        void createQuestion() {//問題作成
            HashSet<Sentence> before = new HashSet<>();
            for (int i = 0; i < questions.length; i++) {
                while (true) {
                    questions[i] = GameValue.getQuestion(true);
                    if (!(before.contains(questions[i]))) {
                        before.add(questions[i]);
                        break;
                    }
                }
            }
            answerSentence = questions[(int) Math.floor(Math.random() * questions.length)];
            questionText.setText(answerSentence.getDescription());
            for (int i = 0; i < choicesButton.length; i++) {
                choicesButton[i].setText(questions[i].getTitle());
            }
        }
    }
}

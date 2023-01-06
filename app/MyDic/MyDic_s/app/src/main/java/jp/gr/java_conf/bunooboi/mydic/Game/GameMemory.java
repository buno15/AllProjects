package jp.gr.java_conf.bunooboi.mydic.Game;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.mydic.Activity.GameFin;
import jp.gr.java_conf.bunooboi.mydic.GameValue;
import jp.gr.java_conf.bunooboi.mydic.Activity.Main;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sentence;
import jp.gr.java_conf.bunooboi.mydic.Sound;
import jp.gr.java_conf.bunooboi.mydic.View.MyDialog;

/**
 * Created by hiro on 2016/08/27.
 */
public class GameMemory extends Activity {
    ArrayList<Sentence> questions = new ArrayList<>();
    static HashSet<Sentence> previousQuestions = new HashSet<>();
    LinearLayout root;
    FrameLayout gameMain;
    TextView questionText;
    TextView randomNumberText;
    Timer mainTimer;//メインタイマー
    Timer startTimer;//始めのタイマー
    int startCount;//始まりまでのカウントダウン
    public static int questionNumber;//問題数
    int questionTimeSpace;//問題変更間隔
    int questionCurrentQuestion;//現在の問題番号
    int answerCurrentQuestion;//解答番号
    int timeCount;//時間
    boolean mainStoper = false;
    Answer answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.gamememory);

        questions = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            while (true) {
                Sentence sentence = GameValue.getQuestion(true);
                if (!previousQuestions.contains(sentence)) {
                    questions.add(sentence);
                    break;
                }
            }
        }
        setPreviousQuestions();
        questionNumber = 2;
        questionCurrentQuestion = 0;
        root = (LinearLayout) findViewById(R.id.root);
        gameMain = (FrameLayout) findViewById(R.id.gameMain);
        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setTextSize(30 * Main.getScaleSize(getApplicationContext()));
        randomNumberText = (TextView) findViewById(R.id.randomNumberText);
        randomNumberText.setTextSize(100 * Main.getScaleSize(getApplicationContext()));
        randomNumberText.setTextColor(Color.parseColor(GameValue.createRandomColor()));
        randomNumberText.setShadowLayer(5, 0, 0, Color.BLACK);
        answer = new Answer();
        answer.addView();
        answer.setVisible(View.GONE);
    }

    public void start() {
        gameMain.setVisibility(View.VISIBLE);
        answer.setVisible(View.GONE);
        questionText.setText("");
        questionTimeSpace = 30 % questionNumber == 0 ? 30 / questionNumber : 30 / questionNumber + 1;
        if (questionTimeSpace > 10) {
            questionTimeSpace = 10;
        }
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
                            if (questionNumber == 2)
                                Sound.startMediaPlayer1(2);
                            Sound.memory_change();
                            randomNumberText.setText("");
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
        Sound.game_start();
        mainTimer = new Timer();
        mainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        timeCount++;
                        if (timeCount % (questionTimeSpace * questionNumber) == 0) {
                            stop();
                            gotoAnswer();
                        } else if (timeCount % questionTimeSpace == 0) {
                            if (mainStoper == false) {
                                Sound.memory_change();
                                questionCurrentQuestion++;
                                mainStoper = true;
                            }
                        } else {
                            mainStoper = false;
                        }
                        questionText.setText(questions.get(questionCurrentQuestion).getSelector().replaceAll("%%", "\n"));
                    }
                });
            }
        }, 4000, 1000);
    }

    void gotoAnswer() {
        gameMain.setVisibility(View.GONE);
        answer.setVisible(View.VISIBLE);
        answer.changeAnswerText();
        questionCurrentQuestion = 0;
        answerCurrentQuestion = 0;
    }

    void stop() {
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer = null;
        }
        Sound.soundPool.stop(Sound.gamestart);
    }

    void fin(int index) {
        Sound.stopMediaPlayer1();
        GameFin.answer = questions.get(index).getSelector() + "\n" + questions.get(index).getDescription();
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

    void setPreviousQuestions() {
        previousQuestions = new HashSet<>();
        for (int i = 0; i < questions.size(); i++) {
            previousQuestions.add(questions.get(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onPause() {
        super.onPause();
        Sound.stopMediaPlayer1();
        stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Sound.releaseMediaPlayer1();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyDialog.id = 2;
            MyDialog.text = "メインに戻りますか？";
            MyDialog dialog = new MyDialog();
            dialog.show(getFragmentManager(), "test");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class Answer {
        LinearLayout answerLayout[] = new LinearLayout[4];
        Button answerButton[] = new Button[12];

        public Answer() {
            createAnswerLayout();
            createAnswerButton();
        }

        public void addView() {
            for (int i = 0; i < answerLayout.length; i++) {
                root.addView(answerLayout[i]);
            }
        }

        void createAnswerLayout() {
            for (int i = 0; i < answerLayout.length; i++) {
                answerLayout[i] = new LinearLayout(GameMemory.this);
                answerLayout[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
                answerLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            }
        }

        void createAnswerButton() {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < answerButton.length; i++) {
                final int j = i;
                int random;
                while (true) {
                    random = (int) Math.floor(Math.random() * 12);
                    if (!set.contains(random)) {
                        set.add(random);
                        break;
                    }
                }
                answerButton[i] = new Button(GameMemory.this);
                answerButton[i].setText(questions.get(random).getDescription());
                answerButton[i].setTextSize(20 * Main.getScaleSize(getApplicationContext()));
                answerButton[i].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
                answerButton[i].setAllCaps(false);
                answerButton[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answerButton[j].getText().toString().equals(questions.get(answerCurrentQuestion).getDescription())) {
                            Sound.yes();
                            answerButton[j].setEnabled(false);
                            answerButton[j].setBackgroundResource(R.drawable.fall_bou_3);
                            if (answerCurrentQuestion >= questionNumber - 1) {
                                for (int i = 0; i < answerButton.length; i++) {
                                    answerButton[i].setEnabled(false);
                                }
                                questionNumber++;
                                final Handler handler = new Handler();
                                Timer t = new Timer();
                                t.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (questionNumber == 11) {
                                                    Sound.memory_clear();
                                                    startActivity(new Intent(getApplicationContext(), GameFin.class));
                                                    finish();
                                                } else {
                                                    timeCount = 0;
                                                    start();
                                                }
                                                overridePendingTransition(0, 0);
                                                for (int i = 0; i < answerButton.length; i++) {
                                                    answerButton[i].setEnabled(true);
                                                }
                                            }
                                        });
                                    }
                                }, 2000);
                            }
                        } else {
                            answerButton[j].setBackgroundResource(R.drawable.fall_bou_2);
                            for (int i = 0; i < answerButton.length; i++) {
                                answerButton[i].setEnabled(false);
                            }
                            Sound.no();
                            fin(answerCurrentQuestion);
                        }
                        answerCurrentQuestion++;
                    }
                });
                if (i >= 0 && i < 3) {
                    answerLayout[0].addView(answerButton[i]);
                } else if (i >= 3 && i < 6) {
                    answerLayout[1].addView(answerButton[i]);
                } else if (i >= 6 && i < 9) {
                    answerLayout[2].addView(answerButton[i]);
                } else if (i >= 9 && i < 12) {
                    answerLayout[3].addView(answerButton[i]);
                }
            }
        }

        public void setVisible(int view) {
            for (int i = 0; i < answerLayout.length; i++) {
                answerLayout[i].setVisibility(view);
            }
            for (int i = 0; i < answerButton.length; i++) {
                answerButton[i].setVisibility(view);
            }
        }

        public void changeAnswerText() {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < answerButton.length; i++) {
                int random;
                while (true) {
                    random = (int) Math.floor(Math.random() * 12);
                    if (!set.contains(random)) {
                        set.add(random);
                        break;
                    }
                }
                answerButton[i].setText(questions.get(random).getDescription());
                answerButton[i].setBackgroundResource(R.drawable.fall_bou_1);
            }
        }
    }
}

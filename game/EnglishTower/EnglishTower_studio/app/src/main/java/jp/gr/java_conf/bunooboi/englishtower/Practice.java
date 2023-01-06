package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Practice extends Activity {
    int format;// 問題形式
    int index = 0;//問題インデックス
    static int minlevel = 1;// レベル（最低）
    static int nowquestion = 1;//今の問題番号
    static int question = 1;// 出題数

    boolean answer = false;// 正解かどうか
    boolean clear = false;// ゲームクリア
    static boolean practicemode;//練習モードか
    static boolean randommode;//ランダムモードか

    HashSet<Integer> indextemp = new HashSet<>();//ランダムモードでの過去の出題インデックス保存用

    final int SELECT_QUESTION_QIMAGE = 0;// 問題形式、選択問題、問題画像
    final int SELECT_QUESTION_QTEXT = 1;// 問題形式、選択問題、問題テキスト
    final int COMBINATION_QUESTION_QWORD = 2;// 問題形式、組み合わせ問題、問題単語
    final int COMBINATION_QUESTION_QPHRASE = 3;// 問題形式、組み合わせ問題、問題フレーズ、日本語
    final int COMBINATION_QUESTION_QIMAGES = 4;// 問題形式、組み合わせ問題、問題フレーズ、画像

    LinearLayout UILayout;// UIの親View
    TextView LevelText;// レベルテキスト
    TextView QuestionText;// 出題数テキスト

    FrameLayout MainFrame;// メインフレーム（まるばつを表示するため）
    LinearLayout QuestionLayout;// 問題レイアウト
    TextView NextText;// 次の階テキスト
    LinearLayout YesNo;// まるばつ

    Timer timer_move;// 次の問題タイマー
    Timer timer_yesno;// まるばつタイマー
    Timer timer_hint;//ヒントタイマー
    Handler handler = new Handler();// Handler

    Question_Select sq;// 選択問題
    Question_Combination cq;// 組み合わせ問題

    String myAnswer;//自分の答え

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.practice);

        Sound.practicebackplay();
        Values.soundLoad(getApplicationContext());

        UILayout = (LinearLayout) findViewById(R.id.uilayout);
        LevelText = (TextView) findViewById(R.id.level);
        QuestionText = (TextView) findViewById(R.id.question);
        MainFrame = (FrameLayout) findViewById(R.id.mainframe);
        QuestionLayout = (LinearLayout) findViewById(R.id.questionlayout);
        YesNo = new LinearLayout(this);
        YesNo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        MainFrame.addView(YesNo);
        NextText = new TextView(this);
        NextText.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        NextText.setTextSize(30 * Tower.getScaleSize(getApplicationContext()));
        NextText.setTextColor(Color.WHITE);
        NextText.setShadowLayer(5, 0, 0, Color.BLACK);
        NextText.setGravity(Gravity.CENTER);

        LevelText.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
        QuestionText.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
        LevelText.setTextColor(Color.WHITE);
        QuestionText.setTextColor(Color.WHITE);

        invalidateText();
        selectQuestions();
    }

    @SuppressWarnings("deprecation")
    private void textSpeech(String text) {
        if (Main.tts.isSpeaking()) {
            Main.tts.stop();
        }
        Main.tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void sqClick() {// 選択問題Click設定
        for (int i = 0; i < sq.SQButton.length; i++) {
            final int j = i;
            if (sq.format == Question_Select.QIMAGE) {
                sq.SQButton[j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Sound.enabled();
                        myAnswer = sq.SQButton[j].getWord();
                        answer = sq.ChecktheAnswer(sq.SQButton[j].getWord());
                        sqClash(j);
                        nextAction();
                    }
                });
            } else if (sq.format == Question_Select.QTEXT) {
                sq.SQImageButton[j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Sound.enabled();
                        answer = sq.ChecktheAnswer(sq.SQImageButton[j].getWord());
                        sqClash(j);
                        nextAction();
                    }
                });
            }
        }
    }

    public void sqClash(int id) {// SelectButton壊す
        if (sq.format == Question_Select.QIMAGE) {
            sq.SQButton[id].setEnabled(false);
            sq.SQButton[id].setText("");
        } else if (sq.format == Question_Select.QTEXT) {
            sq.SQImageButton[id].setEnabled(false);
            sq.SQImageButton[id].setImageResource(R.drawable.zzz_empty);
        }
    }

    public void cqClick() {// 組み合わせ問題Click設定
        cq.TransmissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.button();
                cq.setMyAnswer();
                myAnswer = cq.MyAnswer;
                answer = cq.ChecktheAnswer(cq.MyAnswer);
                nextAction();
            }
        });
    }

    public void invalidateText() {// テキスト上書き
        if (practicemode)
            LevelText.setText("レベル" + String.valueOf(minlevel + 1) + "～" + String.valueOf(minlevel + 10));
        else
            LevelText.setText("レベル" + String.valueOf(minlevel + 1));
        QuestionText.setText(String.valueOf(nowquestion) + "/" + String.valueOf(question));
    }

    public void nextAction() {// 次のアクション選択
        if (answer) {// 正解
            Sound.yes();
            if (timer_hint != null) {
                timer_hint.cancel();
                timer_hint = null;
            }
            if (timer_yesno != null) {
                timer_yesno.cancel();
                timer_yesno = null;
            }
            if (timer_yesno == null) {
                timer_yesno = new Timer();
                timer_yesno.schedule(new YesNoTimer(R.drawable.zzz_yes), 0, 32);
            }
            ++nowquestion;
            if (timer_move != null) {
                timer_move.cancel();
                timer_move = null;
            }
            timer_move = new Timer();
            timer_move.schedule(new MoveTimer(), 0, 32);
        } else {
            Sound.no();
            if (timer_yesno != null) {
                timer_yesno.cancel();
                timer_yesno = null;
            }
            if (timer_yesno == null) {
                timer_yesno = new Timer();
                timer_yesno.schedule(new YesNoTimer(R.drawable.zzz_no), 0, 32);
            }
        }
    }

    public void selectQuestions() {// 問題選択（ランダム）
        //format = (int) Math.floor(Math.random() * 5);
        int random = (int) Math.floor(Math.random() * 4);
        int level = 0;
        if (practicemode) {//練習モード
            format = (int) Math.floor(Math.random() * 3);
            level = new Random().nextInt(10) + minlevel;
        } else {//暗記モード
            format = (int) Math.floor(Math.random() * 3);
            level = minlevel;
            if (randommode) {//ランダムモード
                while (true) {
                    index = (int) Math.floor(Math.random() * DataBase.word.get(level).size());
                    if (!indextemp.contains(index)) {
                        indextemp.add(index);
                        break;
                    }
                }
            }
        }
        int length = 0;
        switch (random) {
            case 0:
                if (format == SELECT_QUESTION_QIMAGE || format == SELECT_QUESTION_QTEXT) {
                    length = 2;
                } else if (format == COMBINATION_QUESTION_QWORD || format == COMBINATION_QUESTION_QPHRASE || format == COMBINATION_QUESTION_QIMAGES) {
                    length = 4;
                }
                break;
            case 1:
                if (format == SELECT_QUESTION_QIMAGE || format == SELECT_QUESTION_QTEXT) {
                    length = 4;
                } else if (format == COMBINATION_QUESTION_QWORD || format == COMBINATION_QUESTION_QPHRASE || format == COMBINATION_QUESTION_QIMAGES) {
                    length = 8;
                }
                break;
            case 2:
                if (format == SELECT_QUESTION_QIMAGE || format == SELECT_QUESTION_QTEXT) {
                    length = 6;
                } else if (format == COMBINATION_QUESTION_QWORD || format == COMBINATION_QUESTION_QPHRASE || format == COMBINATION_QUESTION_QIMAGES) {
                    length = 12;
                }
                break;
            case 3:
                if (format == SELECT_QUESTION_QIMAGE || format == SELECT_QUESTION_QTEXT) {
                    length = 8;
                } else if (format == COMBINATION_QUESTION_QWORD || format == COMBINATION_QUESTION_QPHRASE || format == COMBINATION_QUESTION_QIMAGES) {
                    length = 16;
                }
                break;
        }
        if (practicemode) {
            switch (format) {
                case SELECT_QUESTION_QIMAGE:
                    sq = new Question_Select(Practice.this, QuestionLayout, length, Question_Select.QIMAGE, level);
                    sqClick();
                    break;
                case SELECT_QUESTION_QTEXT:
                    sq = new Question_Select(Practice.this, QuestionLayout, length, Question_Select.QIMAGE, level);
                    sqClick();
                    break;
                case COMBINATION_QUESTION_QWORD:
                    cq = new Question_Combination(Practice.this, QuestionLayout, length, Question_Combination.QWORD, level);
                    cqClick();
                    break;
                case COMBINATION_QUESTION_QPHRASE:
                    cq = new Question_Combination(Practice.this, QuestionLayout, length, Question_Combination.QPHRASE_JAPANESE, level);
                    cqClick();
                    break;
                case COMBINATION_QUESTION_QIMAGES:
                    cq = new Question_Combination(Practice.this, QuestionLayout, length, Question_Combination.QPHRASE_IMAGES, level);
                    cqClick();
                    break;
            }
        } else {
            switch (format) {
                case SELECT_QUESTION_QIMAGE:
                    sq = new Question_Select(Practice.this, QuestionLayout, length, Question_Select.QIMAGE, level, index);
                    sqClick();
                    break;
                case SELECT_QUESTION_QTEXT:
                    // sq = new Question_Select(Tower.this, QuestionLayout, length, Question_Select.QTEXT, Values.level - 1);
                    sq = new Question_Select(Practice.this, QuestionLayout, length, Question_Select.QIMAGE, level, index);
                    sqClick();
                    break;
                case COMBINATION_QUESTION_QWORD:
                    cq = new Question_Combination(Practice.this, QuestionLayout, length, Question_Combination.QWORD, level, index);
                    cqClick();
                    break;
                case COMBINATION_QUESTION_QPHRASE:
                    cq = new Question_Combination(Practice.this, QuestionLayout, length, Question_Combination.QPHRASE_JAPANESE,
                            level, index);
                    cqClick();
                    break;
                case COMBINATION_QUESTION_QIMAGES:
                    cq = new Question_Combination(Practice.this, QuestionLayout, length, Question_Combination.QPHRASE_IMAGES,
                            level, index);
                    cqClick();
                    break;
            }
            if (randommode == false)//順番モードならインデックスインクリメント
                index++;
        }
        if (timer_hint != null) {
            timer_hint.cancel();
            timer_hint = null;
        }
        timer_hint = new Timer();
        if (format == 0 || format == 1) {
            timer_hint.schedule(new HintTimer(sq.Answer.WORD), 10000, 10000);
        } else if (format == 2 || format == 3) {
            timer_hint.schedule(new HintTimer(cq.Answer), 10000, 10000);
        }
    }

    class YesNoTimer extends TimerTask {// まるばつアニメーションタイマー
        long t1;
        long t2;

        public YesNoTimer(int image) {
            YesNo.setBackgroundResource(image);
            t1 = System.currentTimeMillis();
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    t2 = System.currentTimeMillis();
                    if (t2 - t1 >= 1000) {
                        YesNo.setBackgroundResource(R.drawable.zzz_empty);
                        if (timer_yesno != null) {
                            timer_yesno.cancel();
                            timer_yesno = null;
                        }
                    }
                }
            });
        }
    }

    class HintTimer extends TimerTask {
        String hint;

        public HintTimer(String hint) {
            this.hint = hint;
        }

        @Override
        public void run() {
            textSpeech(hint);
        }
    }

    class MoveTimer extends TimerTask {// 次の問題アニメーション
        boolean stoper1 = true;
        boolean stoper2 = true;

        long t1;
        long t2;

        public MoveTimer() {
            t1 = System.currentTimeMillis();
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    t2 = System.currentTimeMillis();
                    if (t2 - t1 >= 3000) {
                        reset();
                    } else if (t2 - t1 >= 1000) {
                        if (stoper2) {
                            stoper2 = false;
                            QuestionLayout.removeAllViews();
                            QuestionLayout.setBackgroundResource(R.drawable.zzz_practice);
                            if (nowquestion >= question) {// 全問解いたら
                                Practice_End.t2 = System.currentTimeMillis();
                                Sound.mediaPlayer1.stop();
                                Sound.start4();
                                NextText.setText("終了");
                                clear = true;
                            } else {
                                Sound.practice();
                                NextText.setText("第" + String.valueOf(nowquestion) + "問");
                            }
                            QuestionLayout.addView(NextText);
                            invalidateText();
                        }
                    } else if (t2 - t1 >= 500) {
                        if (stoper1) {
                            stoper1 = false;
                            textSpeech(myAnswer);
                        }
                    }
                }
            });
        }

        void reset() {// 画面切り替え後
            if (timer_move != null) {
                timer_move.cancel();
                timer_move = null;
            }
            QuestionLayout.removeAllViews();
            if (clear) {// ゲームクリア
                Intent i = new Intent(getApplicationContext(), Practice_End.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
                overridePendingTransition(0, 0);
            } else {// クリアしてない
                QuestionLayout.setBackgroundResource(R.drawable.zzz_practice);
                selectQuestions();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
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
        Sound.mediaPlayer1.start();
        if (timer_hint == null) {
            timer_hint = new Timer();
            if (format == 0 || format == 1) {
                timer_hint.schedule(new HintTimer(sq.Answer.WORD), 10000, 10000);
            } else if (format == 2 || format == 3) {
                timer_hint.schedule(new HintTimer(cq.Answer), 10000, 10000);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Values.save(this);
        Sound.mediaPlayer1.pause();
        if (timer_hint != null) {
            timer_hint.cancel();
            timer_hint = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Tower extends Activity {
    int format;                                                // 問題形式

    boolean answer = false;            // 正解かどうか
    boolean clear = false;            // ゲームクリア

    final int SELECT_QUESTION_QIMAGE = 0;                // 問題形式、選択問題、問題画像
    final int SELECT_QUESTION_QTEXT = 1;                // 問題形式、選択問題、問題テキスト
    final int COMBINATION_QUESTION_QWORD = 2;                // 問題形式、組み合わせ問題、問題単語
    final int COMBINATION_QUESTION_QPHRASE = 3;                // 問題形式、組み合わせ問題、問題フレーズ、日本語
    final int COMBINATION_QUESTION_QIMAGES = 4;                // 問題形式、組み合わせ問題、問題フレーズ、画像

    LinearLayout UILayout;                                            // UIの親View
    TextView LevelText;                                            // レベルテキスト
    TextView FloorText;                                            // 階層テキスト
    ImageView Life[] = new ImageView[3];    // ライフ画像
    LinearLayout MeterLayout;                                        // メーター親View

    FrameLayout MainFrame;                                            // メインフレーム（まるばつを表示するため）
    LinearLayout MainLayout;                                            // 問題レイアウトメイン
    LinearLayout QuestionLayout;                                        // 問題レイアウト
    TextView NextText;                                            // 次の階テキスト
    LinearLayout YesNo;                                                // まるばつ

    Timer timer_scroll;                                        // 階を上がるタイマー
    Timer timer_meter;                                        // メータータイマー
    Timer timer_life;                                            // ライフアニメーションタイマー
    Timer timer_yesno;                                        // まるばつタイマー
    Handler handler = new Handler();    // Handler

    int ViewWidth;                                            // 全体の横幅
    int QuestionHeight;                                        // 問題の高さ
    int UIHeight;                                            // UIの高さ

    Meter m;                                                    // メータView
    Bitmap meter;                                                // メータ画像
    int MeterLayoutWidth;                                    // メータ親View幅
    int MeterLayoutHeight;                                    // メータ親View高さ
    int meterW = 1;                // メータ幅
    int meterH = 1;                // メータ高さ
    int meterR = 1;                // メータ右端
    int tk;                                                    // 時間計る
    boolean meterstoper = false;            // メータを再度作らないように
    boolean soundstoper = false;

    Question_Select sq;                                                    // 選択問題
    Question_Combination cq;                                                    // 組み合わせ問題

    String myAnswer;//自分の答え

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.tower);

        Sound.back1play();
        Values.soundLoad(getApplicationContext());

        UILayout = (LinearLayout) findViewById(R.id.uilayout);
        ViewTreeObserver uiobserver = UILayout.getViewTreeObserver();
        uiobserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                UIHeight = UILayout.getHeight();
            }
        });
        LevelText = (TextView) findViewById(R.id.level);
        FloorText = (TextView) findViewById(R.id.floor);

        Life[0] = (ImageView) findViewById(R.id.life1);
        Life[1] = (ImageView) findViewById(R.id.life2);
        Life[2] = (ImageView) findViewById(R.id.life3);

        MeterLayout = (LinearLayout) findViewById(R.id.meter);
        ViewTreeObserver meterobserver = MeterLayout.getViewTreeObserver();
        meterobserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (meterstoper == false) {
                    meterstoper = true;
                    MeterLayoutWidth = MeterLayout.getWidth();
                    MeterLayoutHeight = MeterLayout.getHeight();
                    NextText.setPadding(0, MeterLayoutHeight * 3, 0, 0);

                    m = new Meter(Tower.this);// メーター作成
                    MeterLayout.addView(m);

                    final Resources res = Tower.this.getResources();
                    meter = BitmapFactory.decodeResource(res, R.drawable.zzz_meter);
                    meter = Bitmap.createScaledBitmap(meter, MeterLayoutWidth - MeterLayoutHeight, MeterLayoutHeight / 2, true);
                    meterW = meter.getWidth();
                    meterH = meter.getHeight();
                    meterReset();
                    selectQuestions();
                }
            }
        });

        MainFrame = (FrameLayout) findViewById(R.id.mainframe);
        YesNo = new LinearLayout(this);
        YesNo.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        MainFrame.addView(YesNo);
        MainLayout = (LinearLayout) findViewById(R.id.mainlayout);
        NextText = new TextView(this);
        NextText.setTextSize(30 * getScaleSize(getApplicationContext()));
        NextText.setTextColor(Color.YELLOW);
        NextText.setShadowLayer(5, 0, 0, Color.BLACK);
        NextText.setGravity(Gravity.CENTER);
        MainLayout.addView(NextText);

        QuestionLayout = (LinearLayout) findViewById(R.id.questionlayout);
        ViewTreeObserver observer = QuestionLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewWidth = QuestionLayout.getWidth();
                QuestionHeight = QuestionLayout.getHeight();
            }
        });

        LevelText.setTextSize(10 * getScaleSize(getApplicationContext()));
        FloorText.setTextSize(10 * getScaleSize(getApplicationContext()));
        LevelText.setTextColor(Color.WHITE);
        FloorText.setTextColor(Color.WHITE);

        if (Values.life[0]) {
            Life[0].setBackgroundResource(R.drawable.zzz_life3);
        }
        if (Values.life[1]) {
            Life[1].setBackgroundResource(R.drawable.zzz_life3);
        }
        if (Values.life[2]) {
            Life[2].setBackgroundResource(R.drawable.zzz_life3);
        }

        invalidateText();
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
                sq.SQButton[j].setOnClickListener(new OnClickListener() {
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
                sq.SQImageButton[j].setOnClickListener(new OnClickListener() {
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
        cq.TransmissionButton.setOnClickListener(new OnClickListener() {
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
        LevelText.setText("レベル" + String.valueOf(Values.level + 1));
        FloorText.setText(String.valueOf(Values.floor) + "階");
    }

    public void LifeMinus(ImageView life) {// ライフマイナス
        life.setBackgroundResource(R.drawable.zzz_life2);
        if (timer_life != null) {
            timer_life.cancel();
            timer_life = null;
        }
        if (timer_life == null) {
            timer_life = new Timer();
            timer_life.schedule(new LifeTimer(life), 0, 16);
        }
    }

    void pointReset() {// 座標リセット
        NextText.setTop(0);
        NextText.setBottom(0);
        NextText.setLeft(0);
        NextText.setRight(0);
        QuestionLayout.setTop(0);
        QuestionLayout.setBottom(QuestionHeight);
        QuestionLayout.setLeft(0);
        QuestionLayout.setRight(ViewWidth);
    }

    void GameOver() {// ゲームオーバーIntent
        Sound.mediaPlayer1.stop();
        Intent i = new Intent(getApplicationContext(), Over.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
        overridePendingTransition(0, 0);
    }

    public void nextAction() {// 次のアクション選択
        if (Values.life[0] && Values.life[1] && Values.life[2]) {// ゲームオーバー
            GameOver();
        } else if (answer) {// 正解
            Sound.yes();
            Sound.up();
            if (timer_yesno != null) {
                timer_yesno.cancel();
                timer_yesno = null;
            }
            if (timer_yesno == null) {
                timer_yesno = new Timer();
                timer_yesno.schedule(new YesNoTimer(R.drawable.zzz_yes), 0, 32);
            }
            scroll();
        } else {// ライフ削る
            Sound.no();
            meterReset();
            Values.save(this);
            if (Values.life[0] == false) {
                Values.life[0] = true;
                Life[0].setBackgroundResource(R.drawable.zzz_life3);
            } else if (Values.life[1] == false) {
                Values.life[1] = true;
                Life[1].setBackgroundResource(R.drawable.zzz_life3);
            } else if (Values.life[2] == false) {
                Values.life[2] = true;
                Life[2].setBackgroundResource(R.drawable.zzz_life3);
                GameOver();
            }
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
        format = (int) Math.floor(Math.random() * 3);
        int length = 0;
        if (Values.levelfloor <= 10) {
            if (format == SELECT_QUESTION_QIMAGE || format == SELECT_QUESTION_QTEXT) {
                length = 2;
            } else if (format == COMBINATION_QUESTION_QWORD || format == COMBINATION_QUESTION_QPHRASE || format == COMBINATION_QUESTION_QIMAGES) {
                length = 4;
            }
        } else if (Values.levelfloor <= 20) {
            if (format == SELECT_QUESTION_QIMAGE || format == SELECT_QUESTION_QTEXT) {
                length = 4;
            } else if (format == COMBINATION_QUESTION_QWORD || format == COMBINATION_QUESTION_QPHRASE || format == COMBINATION_QUESTION_QIMAGES) {
                length = 8;
            }
        } else if (Values.levelfloor <= 30) {
            if (format == SELECT_QUESTION_QIMAGE || format == SELECT_QUESTION_QTEXT) {
                length = 6;
            } else if (format == COMBINATION_QUESTION_QWORD || format == COMBINATION_QUESTION_QPHRASE || format == COMBINATION_QUESTION_QIMAGES) {
                length = 12;
            }
        } else if (Values.levelfloor <= 50) {
            if (format == SELECT_QUESTION_QIMAGE || format == SELECT_QUESTION_QTEXT) {
                length = 8;
            } else if (format == COMBINATION_QUESTION_QWORD || format == COMBINATION_QUESTION_QPHRASE || format == COMBINATION_QUESTION_QIMAGES) {
                length = 16;
            }
        }
        switch (format) {
            case SELECT_QUESTION_QIMAGE:
                sq = new Question_Select(Tower.this, QuestionLayout, length, Question_Select.QIMAGE, Values.level);
                sqClick();
                break;
            case SELECT_QUESTION_QTEXT:
                sq = new Question_Select(Tower.this, QuestionLayout, length, Question_Select.QIMAGE, Values.level);
                sqClick();
                break;
            case COMBINATION_QUESTION_QWORD:
                cq = new Question_Combination(Tower.this, QuestionLayout, length, Question_Combination.QWORD, Values.level);
                cqClick();
                break;
            case COMBINATION_QUESTION_QPHRASE:
                cq = new Question_Combination(Tower.this, QuestionLayout, length, Question_Combination.QPHRASE_JAPANESE, Values.level);
                cqClick();
                break;
            case COMBINATION_QUESTION_QIMAGES:
                cq = new Question_Combination(Tower.this, QuestionLayout, length, Question_Combination.QPHRASE_IMAGES, Values.level);
                cqClick();
                break;
        }
    }

    public void scroll() {// 階を上がる
        NextText.setText(String.valueOf(++Values.floor) + "階");
        if (Values.floor >= 5000) {// 階が5000階になったら
            NextText.setBackgroundResource(R.drawable.zzz_max);
            NextText.setText("頂上");
            clear = true;
        } else {
            NextText.setBackgroundResource(R.drawable.zzz_nextfloor);
        }
        pointReset();
        if (timer_meter != null) {
            timer_meter.cancel();
            timer_meter = null;
        }
        if (timer_scroll != null) {
            timer_scroll.cancel();
            timer_scroll = null;
        }
        if (timer_scroll == null) {
            timer_scroll = new Timer();
            timer_scroll.schedule(new MoveTimer(), 0, 16);
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

    class LifeTimer extends TimerTask {// ライフアニメーション
        ImageView Life;
        long t1;
        long t2;

        public LifeTimer(ImageView life) {
            Life = life;
            t1 = System.currentTimeMillis();
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    t2 = System.currentTimeMillis();
                    if (t2 - t1 >= 500) {
                        Life.setBackgroundResource(R.drawable.zzz_life3);
                        if (timer_life != null) {
                            timer_life.cancel();
                            timer_life = null;
                        }
                    }
                }
            });
        }
    }

    class MoveTimer extends TimerTask {// 階を上がるアニメーション

        boolean stoper1 = true;
        boolean timerstoper = false;

        long t1;
        long t2;
        long t3;

        public MoveTimer() {
            t1 = System.currentTimeMillis();
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int top = QuestionLayout.getTop();
                    int left = QuestionLayout.getLeft();
                    int width = QuestionLayout.getWidth();
                    int height = QuestionLayout.getHeight();
                    if (timerstoper) {
                        t3 = System.currentTimeMillis();
                        if (t3 - t1 >= t2 - t1 + 1000) {
                            reset();
                        }
                    } else {
                        t2 = System.currentTimeMillis();
                        QuestionLayout.layout(left, top + 10, left + width, top + height + 10);
                        NextText.layout(left, top + 10 - height, left + width, top + 10);
                        if (top >= QuestionHeight) {// 画面をスクロールし終わったら
                            NextText.setTop(0);
                            NextText.setBottom(QuestionHeight);
                            NextText.setLeft(0);
                            NextText.setRight(ViewWidth);
                            timerstoper = true;
                        }
                    }
                    if (t2 - t1 >= 200) {
                        if (stoper1) {
                            stoper1 = false;
                            textSpeech(myAnswer);
                        }
                    }
                }
            });
        }

        void reset() {// 画面スクロール後
            pointReset();
            if (timer_scroll != null) {
                timer_scroll.cancel();
                timer_scroll = null;
            }
            if (timer_life != null) {
                timer_life.cancel();
                timer_life = null;
            }
            QuestionLayout.removeAllViews();
            if (clear) {// ゲームクリア
                Intent i = new Intent(getApplicationContext(), Clear.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
                overridePendingTransition(0, 0);
            } else {// クリアしてない
                QuestionLayout.setBackgroundResource(R.drawable.zzz_backc1);
                Values.levelfloor++;
                if (Values.floor % 50 == 0) {
                    Values.level++;
                    Values.levelfloor = 1;
                    if (Values.life[2]) {
                        Values.life[2] = false;
                        Life[2].setBackgroundResource(R.drawable.zzz_life1);
                    } else if (Values.life[1]) {
                        Values.life[1] = false;
                        Life[1].setBackgroundResource(R.drawable.zzz_life1);
                    } else if (Values.life[0]) {
                        Values.life[0] = false;
                        Life[0].setBackgroundResource(R.drawable.zzz_life1);
                    }
                    Sound.powerup();
                }
                selectQuestions();
            }
            if (Values.record < Values.floor)
                Values.record = Values.floor;
            Values.save(Tower.this);
            invalidateText();
            meterReset();
        }
    }

    public void meterReset() {// メータリセット
        meterR = meterW;
        if (timer_meter != null) {
            timer_meter.cancel();
            timer_meter = null;
        }
        if (timer_meter == null) {
            timer_meter = new Timer();
            timer_meter.schedule(new MeterTimer(), 0, 16);
        }
    }

    class MeterTimer extends TimerTask {// メータータイマー

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tk++;
                    m.invalidate();
                }
            });
        }
    }

    class Meter extends View {// メーターView
        Paint meterP = new Paint();

        public Meter(Context context) {
            super(context);

            meterP.setColor(Color.RED);
            meterP.setAntiAlias(true);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (Values.levelfloor <= 10) {
                if (tk % 5 == 0) {// メーターマイナス
                    meterR--;
                }
            } else if (Values.levelfloor <= 20) {
                if (tk % 4 == 0) {
                    meterR--;
                }
            } else if (Values.levelfloor <= 30) {
                if (tk % 3 == 0) {
                    meterR--;
                }
            } else if (Values.levelfloor <= 50) {
                if (tk % 2 == 0) {
                    meterR--;
                }
            }
            canvas.drawRect(MeterLayoutWidth / 2 - meterW / 2, MeterLayoutHeight / 2 - meterH / 2, MeterLayoutWidth / 2 - meterW / 2 + meterR,
                    MeterLayoutHeight / 2 - meterH / 2 + meterH, meterP);
            canvas.drawBitmap(meter, MeterLayoutWidth / 2 - meterW / 2, MeterLayoutHeight / 2 - meterH / 2, null);
            if (meterR <= meterW / 2) {
                if (soundstoper == false) {
                    soundstoper = true;
                    Sound.media2play(2);
                }
            } else {
                if (soundstoper) {
                    soundstoper = false;
                    Sound.mediaPlayer2.stop();
                }
            }
            if (meterR <= 0) {// メーターがなくなったら
                if (Values.life[0] && Values.life[1] && Values.life[2]) { // ゲームオーバー
                    Sound.mediaPlayer2.stop();
                    if (timer_meter != null) {
                        timer_meter.cancel();
                        timer_meter = null;
                    }
                    GameOver();
                } else {
                    Values.save(Tower.this);
                    if (Values.life[0] == false) {
                        Sound.life();
                        Values.life[0] = true;
                        LifeMinus(Life[0]);
                        meterR = meterW;
                    } else if (Values.life[1] == false) {
                        Sound.life();
                        Values.life[1] = true;
                        LifeMinus(Life[1]);
                        meterR = meterW;
                    } else if (Values.life[2] == false) {
                        Sound.mediaPlayer2.stop();
                        Values.life[2] = true;
                        LifeMinus(Life[2]);
                        if (timer_meter != null) {
                            timer_meter.cancel();
                            timer_meter = null;
                        }
                        GameOver();
                    }
                }
            }
        }
    }

    public static float getScaleSize(Context context) {// 文字サイズ調整
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        float width = 0;
        float scale = 0;
        width = size.x;
        scale = (float) width / (float) bm.getWidth();
        return scale;
    }

    public static int getPctX(Context context, int value) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        return size.x / 100 * value;
    }

    public static int getPctY(Context context, int value) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        return size.y / 100 * value;
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
        Sound.mediaPlayer2.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        Values.save(this);
        Sound.mediaPlayer1.pause();
        Sound.mediaPlayer2.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

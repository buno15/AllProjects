package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.mydic.Game;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sound;
import jp.gr.java_conf.bunooboi.mydic.Word;

public class GameSelect extends AppCompatActivity {
    LinearLayout rootLayout;

    Question q;
    Word answer;
    ArrayList<Word> question = new ArrayList<>();

    Timer judgeTimer;//正解の表示タイマー

    int answerIndex = 0;//答え番号
    int questionIndex[] = new int[6];

    int judgeTime = 0;//正解の表示時間
    int questionCount = 0;//問題数
    int timeCount = 100;//制限時間

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameselect);

        rootLayout = findViewById(R.id.rootLayout);


    }

    void makeQuestion() {//問題Word作成
        rootLayout.removeAllViews();
        question.clear();
        questionCount++;
        q = new Question();
        for (int i = 0; i < 6; i++) {
            while (true) {
                int j = (int) Math.floor(Math.random() * Game.words.size());
                Word word = Game.words.get(j);
                if (question.indexOf(word) == -1) {
                    questionIndex[i] = j;
                    question.add(word);
                    q.button[i].setText(word.getWord());
                    break;
                }
            }
        }
        answerIndex = (int) Math.floor(Math.random() * 6);
        answer = question.get(answerIndex);
        Game.answer = answer;
        while (true) {
            int index = (int) Math.floor(Math.random() * 3);
            if (!answer.getDescription()[index].equals("")) {
                q.questionText.setText(answer.getDescription()[index]);
                break;
            }
        }
        for (int i = 0; i < q.button.length; i++) {
            final int finalI = i;
            q.button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    judge(finalI);
                }
            });
        }
        if (questionCount > 60) {
            timeCount = 1;
        } else if (questionCount > 50) {
            timeCount = 5;
        } else if (questionCount > 40) {
            timeCount = 10;
        } else if (questionCount > 30) {
            timeCount = 20;
        } else if (questionCount > 20) {
            timeCount = 40;
        } else if (questionCount > 10) {
            timeCount = 60;
        }
    }

    void stop() {//停止
        Sound.soundPool.stop(Sound.game_start_keep);
        rootLayout.removeAllViews();
        question.clear();
        q.timeView.stop();
        if (judgeTimer != null) {
            judgeTimer.cancel();
            judgeTimer = null;
        }
    }

    void judge(int index) {//正解確認
        q.timeView.stop();
        if (index == answerIndex) {
            q.button[index].setBackgroundResource(R.drawable.button_maru);
            judgeTimer(true);
        } else {
            if (Game.words.get(questionIndex[index]).searchDescription(q.questionText.getText().toString())) {
                q.button[index].setBackgroundResource(R.drawable.button_maru);
                judgeTimer(true);
            } else {
                Sound.stopMediaPlayer();
                q.button[index].setBackgroundResource(R.drawable.button_batu);
                judgeTimer(false);
            }
        }
        for (int i = 0; i < q.button.length; i++) {
            q.button[i].setEnabled(false);
        }
    }

    void judgeTimer(final boolean judge) {//正解確認タイマーの設定 judge=合否
        if (judge)
            Sound.yes();
        else
            Sound.no();
        final Handler handler = new Handler();
        judgeTimer = new Timer();
        judgeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (judgeTime >= 2) {
                            if (judgeTimer != null) {
                                judgeTime = 0;
                                judgeTimer.cancel();
                                judgeTimer = null;
                            }
                            if (judge) {
                                makeQuestion();
                            } else {
                                stop();
                                Intent i = new Intent(getApplicationContext(), GameFin.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                                finish();
                            }
                        }
                        judgeTime++;
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        makeQuestion();
        Sound.startMediaPlayer(0);
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
        Sound.stopMediaPlayer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                if (q.timeView.timer_time != null) {
                    stop();
                    AlertDialog.Builder alertDlg = new AlertDialog.Builder(GameSelect.this);
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
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (q.timeView.timer_time != null) {
                stop();
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(GameSelect.this);
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
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    class TimeView extends View {//時間計測のview
        int view_w;
        int view_h;

        int circle_x;
        int circle_y;
        int circle_r;
        int circle_ac = 360;


        Timer timer_time;//制限時間タイマー

        Canvas canvas;
        Paint timeRect;
        RectF rect;

        public TimeView(Context context) {
            super(context);
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);
            view_w = size.x;
            view_h = (size.y / 16) * 4;

            circle_r = view_w / 10;
            circle_x = (view_w / 2);
            circle_y = (view_h / 2) - (circle_r / 2);

            rect = new RectF(circle_x - circle_r, circle_y - circle_r, circle_x + circle_r, circle_y + circle_r);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            this.canvas = canvas;
            canvas.drawColor(Color.parseColor("#f5deb3"));

            Paint paint1 = new Paint();
            paint1.setColor(Color.parseColor("#87cefa"));
            paint1.setStrokeWidth(5);
            paint1.setAntiAlias(true);
            paint1.setStyle(Paint.Style.FILL);
            canvas.drawCircle(circle_x, circle_y, circle_r, paint1);

            timeRect = new Paint();
            timeRect.setColor(Color.parseColor("#ff69b4"));
            timeRect.setStrokeWidth(5);
            timeRect.setAntiAlias(true);
            canvas.drawArc(rect, 270, -circle_ac, true, timeRect);

            Paint paint2 = new Paint();
            paint2.setColor(Color.parseColor("#f5deb3"));
            paint2.setStrokeWidth(5);
            paint2.setAntiAlias(true);
            paint2.setStyle(Paint.Style.FILL);
            canvas.drawCircle(circle_x, circle_y, circle_r / 2, paint2);

            Paint paint3 = new Paint();
            paint3.setColor(Color.parseColor("#87cefa"));
            paint3.setStrokeWidth(5);
            paint3.setAntiAlias(true);
            paint3.setStyle(Paint.Style.FILL);
            canvas.drawCircle(circle_x, circle_y, circle_r / 6, paint3);
        }

        public void start() {
            final Handler handler = new Handler();
            timer_time = new Timer();
            timer_time.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (circle_ac <= 0) {
                                circle_ac = 0;
                                if (timer_time != null) {
                                    timer_time.cancel();
                                    timer_time = null;
                                    for (int i = 0; i < q.button.length; i++) {
                                        q.button[i].setEnabled(false);
                                    }
                                    q.button[answerIndex].setBackgroundResource(R.drawable.button_maru);
                                    Sound.stopMediaPlayer();
                                    judgeTimer(false);
                                }
                            } else {
                                invalidate();
                                circle_ac--;
                            }
                        }
                    });
                }
            }, 0, timeCount);
        }

        public void stop() {
            if (timer_time != null) {
                timer_time.cancel();
                timer_time = null;
            }
        }
    }

    class Question {//問題をつくるview
        TextView questionText;
        LinearLayout questionLayout;
        LinearLayout buttonLayout[] = new LinearLayout[3];
        Button button[] = new Button[6];
        TimeView timeView;
        Context context;

        public Question() {
            context = GameSelect.this;

            questionText = new TextView(context);
            questionText.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 5);
            lp.setMargins(30, 30, 30, 30);
            questionText.setLayoutParams(lp);
            questionText.setBackgroundColor(Color.parseColor("#fdfdfd"));
            rootLayout.addView(questionText);

            questionLayout = new LinearLayout(context);
            questionLayout.setOrientation(LinearLayout.VERTICAL);
            lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 7);
            lp.setMargins(30, 0, 30, 0);
            questionLayout.setLayoutParams(lp);
            rootLayout.addView(questionLayout);

            lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
            for (int i = 0; i < buttonLayout.length; i++) {
                buttonLayout[i] = new LinearLayout(context);
                buttonLayout[i].setOrientation(LinearLayout.HORIZONTAL);
                buttonLayout[i].setLayoutParams(lp);
                questionLayout.addView(buttonLayout[i]);
            }
            lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            for (int i = 0; i < button.length; i++) {
                button[i] = new Button(context);
                button[i].setLayoutParams(lp);
                if (i == 0 || i == 1) {
                    buttonLayout[0].addView(button[i]);
                } else if (i == 2 || i == 3) {
                    buttonLayout[1].addView(button[i]);
                } else if (i == 4 || i == 5) {
                    buttonLayout[2].addView(button[i]);
                }
            }
            lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 4);
            timeView = new TimeView(context);
            timeView.setLayoutParams(lp);
            rootLayout.addView(timeView);
            timeView.start();
        }
    }
}




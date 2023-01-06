package jp.gr.java_conf.bunooboi.englishtower;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.util.HashSet;

public class Question_Select {
    Context context;        // コンテキスト
    ViewGroup view;            // 親View

    FitTextView QuestionText;
    ImageButton QuestionImage;
    LinearLayout SelectLayout[];
    SQButton SQButton[];
    SQImageButton SQImageButton[];

    Word Questions[];    // 全選択肢
    Word Answer;            // 答え
    int format;            // 問題形式
    int level;            // レベル
    final int index;//答えインデックス

    static final int QIMAGE = 0;    // 問題が画像
    static final int QTEXT = 1;    // 問題がテキスト

    public Question_Select(Context context, ViewGroup view, int length, int format, int level) {
        this.context = context;
        this.view = view;
        this.format = format;
        this.level = level;
        index = 10000;
        SelectLayout = new LinearLayout[length / 2];
        SQButton = new SQButton[length];
        SQImageButton = new SQImageButton[length];
        Questions = new Word[length];

        QuestionText = new FitTextView(context);
        for (int i = 0; i < SQButton.length; i++) {
            if (format == QIMAGE) {
                SQButton[i] = new SQButton(context);
            } else if (format == QTEXT) {
                SQImageButton[i] = new SQImageButton(context);
            }
        }
        createQuestion();
        createQuestionText();
        createSelectLayout();
        createSelectButton();
    }

    public Question_Select(Context context, ViewGroup view, int length, int format, int level, int index) {
        this.context = context;
        this.view = view;
        this.format = format;
        this.level = level;
        this.index = index;
        SelectLayout = new LinearLayout[length / 2];
        SQButton = new SQButton[length];
        SQImageButton = new SQImageButton[length];
        Questions = new Word[length];

        QuestionText = new FitTextView(context);
        for (int i = 0; i < SQButton.length; i++) {
            if (format == QIMAGE) {
                SQButton[i] = new SQButton(context);
            } else if (format == QTEXT) {
                SQImageButton[i] = new SQImageButton(context);
            }
        }
        createQuestion();
        createQuestionText();
        createSelectLayout();
        createSelectButton();
    }

    public void createQuestionText() {
        LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
        if (format == QIMAGE) {
            /*QuestionImage = new ImageButton(context);
            QuestionImage.setLayoutParams(lp);
            QuestionImage.setPadding(0, Tower.getMargin(context) + 1, 0, Tower.getMargin(context) + 1);
            QuestionImage.setBackgroundResource(R.drawable.zzz_backq);
            QuestionImage.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
            view.addView(QuestionImage);*/
            QuestionText.setLayoutParams(lp);
            QuestionText.setTextSize(10 * Tower.getScaleSize(context));
            QuestionText.setBackgroundResource(R.drawable.zzz_backq);
            QuestionText.setGravity(Gravity.CENTER);
            if (QuestionText.getText().toString().length() >= 15) {
                StringBuilder sb = new StringBuilder();
                int va = QuestionText.getText().toString().length() / 14;
                int am = QuestionText.getText().toString().length() % 14;
                for (int i = 0; i < va * 14; i += 14) {
                    sb.append(QuestionText.getText().toString().substring(i, i + 14) + "\n");
                }
                if (am != 0)
                    sb.append(QuestionText.getText().toString().substring(QuestionText.getText().toString().length() - am, QuestionText.getText().toString().length()));
                QuestionText.setText(sb);
            }
            view.addView(QuestionText);
        } else if (format == QTEXT) {
            QuestionText.setLayoutParams(lp);
            QuestionText.setTextSize(20 * Tower.getScaleSize(context));
            QuestionText.setBackgroundResource(R.drawable.zzz_backq);
            QuestionText.setGravity(Gravity.CENTER);
            view.addView(QuestionText);
            ViewTreeObserver observer = QuestionText.getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int ViewWidth = QuestionText.getWidth();
                    QuestionText.setPadding(ViewWidth / 20, 0, ViewWidth / 20, 0);
                    if (QuestionText.length() >= 30) {
                        QuestionText.setTextSize(4 * Tower.getScaleSize(context));
                    } else if (QuestionText.length() >= 25) {
                        QuestionText.setTextSize(5 * Tower.getScaleSize(context));
                    } else if (QuestionText.length() >= 20) {
                        QuestionText.setTextSize(7 * Tower.getScaleSize(context));
                    } else if (QuestionText.length() >= 10) {
                        QuestionText.setTextSize(10 * Tower.getScaleSize(context));
                    }
                }
            });
        }
    }

    public void createSelectLayout() {
        for (int i = 0; i < SelectLayout.length; i++) {
            SelectLayout[i] = new LinearLayout(context);
            SelectLayout[i].setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
            SelectLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            view.addView(SelectLayout[i]);
        }
    }

    public void createSelectButton() {
        LayoutParams lp = new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        for (int i = 0; i < SQButton.length; i++) {
            if (format == QIMAGE) {
                SQButton[i].setLayoutParams(lp);
                SQButton[i].setTextSize(12 * Tower.getScaleSize(context));
                SQButton[i].setTextColor(Color.WHITE);
                SQButton[i].setShadowLayer(5, 0, 0, Color.BLACK);
                SQButton[i].setBackgroundResource(R.drawable.zzz_q1_button_custom);
                switch (i) {
                    case 0:
                    case 1:
                        SelectLayout[0].addView(SQButton[i]);
                        break;
                    case 2:
                    case 3:
                        SelectLayout[1].addView(SQButton[i]);
                        break;
                    case 4:
                    case 5:
                        SelectLayout[2].addView(SQButton[i]);
                        break;
                    case 6:
                    case 7:
                        SelectLayout[3].addView(SQButton[i]);
                        break;
                }
            } else if (format == QTEXT) {
                SQImageButton[i].setLayoutParams(lp);
                SQImageButton[i].setBackgroundResource(R.drawable.zzz_q1_button_custom);
                SQImageButton[i].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                switch (i) {
                    case 0:
                    case 1:
                        SelectLayout[0].addView(SQImageButton[i]);
                        break;
                    case 2:
                    case 3:
                        SelectLayout[1].addView(SQImageButton[i]);
                        break;
                    case 4:
                    case 5:
                        SelectLayout[2].addView(SQImageButton[i]);
                        break;
                    case 6:
                    case 7:
                        SelectLayout[3].addView(SQImageButton[i]);
                        break;
                }
            }
        }
    }

    public void createQuestion() {
        if (index == 10000) {
            HashSet<Word> before = new HashSet<Word>();
            for (int i = 0; i < Questions.length; i++) {
                while (true) {
                    Questions[i] = DataBase.word.get(level).get((int) Math.floor(Math.random() * DataBase.word.get(level).size()));
                    if (!(before.contains(Questions[i]))) {
                        before.add(Questions[i]);
                        break;
                    }
                }
            }
            Answer = Questions[(int) Math.floor(Math.random() * Questions.length)];
        } else {
            Answer = DataBase.word.get(level).get(index);
            Questions[(int) Math.floor(Math.random() * Questions.length)] = Answer;
            HashSet<Word> before = new HashSet<Word>();
            before.add(Answer);
            for (int i = 0; i < Questions.length; i++) {
                if (Questions[i] != null)
                    continue;
                while (true) {
                    Questions[i] = DataBase.word.get(level).get((int) Math.floor(Math.random() * DataBase.word.get(level).size()));
                    if (!(before.contains(Questions[i]))) {
                        before.add(Questions[i]);
                        break;
                    }
                }
            }
        }
        Over.answer = Answer.WORD;
        for (int i = 0; i < Questions.length; i++) {
            if (format == QIMAGE) {
                /*SQButton[i].setWord(Questions[i].WORD);
                QuestionImage.setImageResource(Answer.IMAGE);
                SQButton[i].setText(Questions[i].WORD);*/
                SQButton[i].setWord(Questions[i].WORD);
                SQButton[i].setText(Questions[i].WORD);
                QuestionText.setText(Answer.JAPANESE);
            } else if (format == QTEXT) {
                SQImageButton[i].setWord(Questions[i].WORD);
                QuestionText.setText(Answer.WORD);
                SQImageButton[i].setImageResource(Questions[i].IMAGE);
            }
        }
    }

    public boolean ChecktheAnswer(String id) {
        if (id.equals(Answer.WORD)) {
            return true;
        } else {
            return false;
        }
    }
}

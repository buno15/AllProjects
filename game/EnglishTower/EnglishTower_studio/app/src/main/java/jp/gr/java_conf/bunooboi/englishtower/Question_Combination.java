package jp.gr.java_conf.bunooboi.englishtower;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Question_Combination {
    Context context; // コンテキスト
    ViewGroup view; // 親View

    String Answer; // 答え
    String MyAnswer; // 自分の答え
    int length; // ボタン数
    int format; // 形式
    int level; // レベル
    final int index;//インデックス

    ArrayList<String> Engrave = new ArrayList<String>(); // 文字の区切る
    boolean qimagestoper = false;

    FitTextView QuestionText; // 問題テキスト
    ImageButton QuestionImage; // 問題画像
    LinearLayout QuestionLayout; // 問題レイアウト（問題が画像の場合作成）
    ArrayList<ImageView> QuestionImages = new ArrayList<ImageView>(); // 問題画像（フレーズ）
    LinearLayout AnswerLayout; // 自分の答えレイアウト
    ArrayList<TextView> AnswerText = new ArrayList<TextView>(); // 自分の答えテキスト

    LinearLayout SelectLayout[]; // 選択肢レイアウト
    FitButton SelectButton[]; // 選択肢ボタン

    LinearLayout ButtonLayout; // UIボタンレイアウト
    Button TransmissionButton; // 決定ボタン
    Button ResetButton; // リセットボタン

    static final int QWORD = 0; // 問題単語
    static final int QPHRASE_JAPANESE = 1; // 問題フレーズ、日本語
    static final int QPHRASE_IMAGES = 2; // 問題フレーズ、画像

    public Question_Combination(Context context, ViewGroup view, int length, int format, int level) {
        this.context = context;
        this.view = view;
        this.length = length;
        this.format = format;
        this.level = level;
        index = 10000;
        if (format == QWORD) {
            //QuestionImage = new ImageButton(context);
            QuestionText = new FitTextView(context);
        } else if (format == QPHRASE_JAPANESE) {
            QuestionText = new FitTextView(context);
        }
        createQuestion();
        createQuestionView();
        createAnswerLayout();
        SelectLayout = new LinearLayout[this.length / 4];
        SelectButton = new FitButton[this.length];
        createSelectLayout();
        createSelectButton();
        createButtonLayout();
        createTransmissionButton();
        createResetButton();
    }

    public Question_Combination(Context context, ViewGroup view, int length, int format, int level, int index) {
        this.context = context;
        this.view = view;
        this.length = length;
        this.format = format;
        this.level = level;
        this.index = index;
        if (format == QWORD) {
            //QuestionImage = new ImageButton(context);
            QuestionText = new FitTextView(context);
        } else if (format == QPHRASE_JAPANESE) {
            QuestionText = new FitTextView(context);
        }
        createQuestion();
        createQuestionView();
        createAnswerLayout();
        SelectLayout = new LinearLayout[this.length / 4];
        SelectButton = new FitButton[this.length];
        createSelectLayout();
        createSelectButton();
        createButtonLayout();
        createTransmissionButton();
        createResetButton();
    }

    public void createQuestionView() {
        LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 2);
        if (format == QWORD) {
            /*QuestionImage.setLayoutParams(lp);
            QuestionImage.setBackgroundResource(R.drawable.zzz_backq);
			QuestionImage.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
			view.addView(QuestionImage);*/
            QuestionText.setLayoutParams(lp);
            QuestionText.setTextSize(10 * Tower.getScaleSize(context));
            QuestionText.setTextColor(Color.BLACK);
            QuestionText.setGravity(Gravity.CENTER);
            QuestionText.setBackgroundResource(R.drawable.zzz_backq);
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
        } else if (format == QPHRASE_JAPANESE) {
            QuestionText.setLayoutParams(lp);
            if (QuestionText.getText().toString().length() >= 17) {
                StringBuilder sb = new StringBuilder();
                int va = QuestionText.getText().toString().length() / 16;
                int am = QuestionText.getText().toString().length() % 16;
                for (int i = 0; i < va * 16; i += 16) {
                    sb.append(QuestionText.getText().toString().substring(i, i + 16) + "\n");
                }
                if (am != 0)
                    sb.append(QuestionText.getText().toString().substring(QuestionText.getText().toString().length() - am, QuestionText.getText().toString().length()));
                QuestionText.setText(sb);
            }
            QuestionText.setTextSize(8 * Tower.getScaleSize(context));
            QuestionText.setTextColor(Color.BLACK);
            QuestionText.setGravity(Gravity.CENTER);
            QuestionText.setBackgroundResource(R.drawable.zzz_backq);
            view.addView(QuestionText);
        } else if (format == QPHRASE_IMAGES) {
            QuestionLayout = new LinearLayout(context);
            QuestionLayout.setLayoutParams(lp);
            QuestionLayout.setOrientation(LinearLayout.HORIZONTAL);
            QuestionLayout.setBackgroundResource(R.drawable.zzz_backq);
            view.addView(QuestionLayout);
            for (int i = 0; i < QuestionImages.size(); i++) {
                QuestionImages.get(i).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                QuestionLayout.addView(QuestionImages.get(i));
            }
            ViewTreeObserver observer = QuestionLayout.getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (qimagestoper == false) {
                        qimagestoper = true;
                        int width = QuestionLayout.getWidth() / 18;
                        int height = QuestionLayout.getHeight() / 3;
                        for (int i = 0; i < QuestionImages.size(); i++) {
                            LayoutParams qilp = new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                            MarginLayoutParams mlp = (MarginLayoutParams) qilp;
                            if (i == 0)
                                mlp.setMargins(width, height, 0, height);
                            else if (i == QuestionImages.size() - 1)
                                mlp.setMargins(0, height, width, height);
                            else
                                mlp.setMargins(0, height, 0, height);
                            QuestionImages.get(i).setLayoutParams(mlp);
                        }
                    }
                }
            });
        }
    }

    public void createAnswerLayout() {
        AnswerLayout = new LinearLayout(context);
        AnswerLayout.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        AnswerLayout.setOrientation(LinearLayout.HORIZONTAL);
        view.addView(AnswerLayout);
    }

    public void createAnswerText(String text) {
        TextView answertext = new TextView(context);
        answertext.setText(text);
        if (length >= 32) {
            answertext.setTextSize(5 * Tower.getScaleSize(context));
        } else {
            answertext.setTextSize(10 * Tower.getScaleSize(context));
        }
        if (text.length() > 2) {
            answertext.setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        } else {
            answertext.setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.5f));
        }
        if (text.length() >= 14) {
            answertext.setTextSize(3 * Tower.getScaleSize(context));
        } else if (text.length() >= 10) {
            answertext.setTextSize(4 * Tower.getScaleSize(context));
        } else if (text.length() >= 9) {
            answertext.setTextSize(5 * Tower.getScaleSize(context));
        } else if (text.length() >= 8) {
            answertext.setTextSize(6 * Tower.getScaleSize(context));
        } else {
            answertext.setTextSize(8 * Tower.getScaleSize(context));
        }
        answertext.setTextColor(Color.WHITE);
        answertext.setShadowLayer(5, 0, 0, Color.BLACK);
        answertext.setGravity(Gravity.CENTER);
        answertext.setBackgroundResource(R.drawable.zzz_word);
        AnswerLayout.addView(answertext);
        AnswerText.add(answertext);
    }

    public void createSelectLayout() {
        LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
        for (int i = 0; i < SelectLayout.length; i++) {
            SelectLayout[i] = new LinearLayout(context);
            SelectLayout[i].setLayoutParams(lp);
            SelectLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            view.addView(SelectLayout[i]);
        }
    }

    public void createSelectButton() {
        LayoutParams lp = new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        for (int i = 0; i < SelectButton.length; i++) {
            final int j = i;
            SelectButton[j] = new FitButton(context);
            SelectButton[j].setLayoutParams(lp);
            SelectButton[j].setText(Engrave.get(j));
            if (format == QWORD) {
                if (length >= 32)
                    SelectButton[j].setTextSize(5 * Tower.getScaleSize(context));
                else
                    SelectButton[j].setTextSize(10 * Tower.getScaleSize(context));
            } else if (format == QPHRASE_JAPANESE || format == QPHRASE_IMAGES) {
                if (length >= 32)
                    SelectButton[j].setTextSize(4 * Tower.getScaleSize(context));
                else {
                    if (Engrave.get(j).length() >= 14) {
                        SelectButton[j].setTextSize(3 * Tower.getScaleSize(context));
                    } else if (Engrave.get(j).length() >= 10) {
                        SelectButton[j].setTextSize(4 * Tower.getScaleSize(context));
                    } else if (Engrave.get(j).length() >= 9) {
                        SelectButton[j].setTextSize(5 * Tower.getScaleSize(context));
                    } else if (Engrave.get(j).length() >= 8) {
                        SelectButton[j].setTextSize(6 * Tower.getScaleSize(context));
                    } else {
                        SelectButton[j].setTextSize(8 * Tower.getScaleSize(context));
                    }
                }
            }
            SelectButton[j].setTextColor(Color.WHITE);
            SelectButton[j].setShadowLayer(5, 0, 0, Color.BLACK);
            SelectButton[j].setBackgroundResource(R.drawable.zzz_q1_button_custom);
            SelectButton[j].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sound.enabled();
                    createAnswerText(SelectButton[j].getText().toString());
                    SelectButton[j].setEnabled(false);
                    SelectButton[j].setText("");
                }
            });
            switch (j) {
                case 0:
                case 1:
                case 2:
                case 3:
                    SelectLayout[0].addView(SelectButton[i]);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    SelectLayout[1].addView(SelectButton[i]);
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                    SelectLayout[2].addView(SelectButton[i]);
                    break;
                case 12:
                case 13:
                case 14:
                case 15:
                    SelectLayout[3].addView(SelectButton[i]);
                    break;
                case 16:
                case 17:
                case 18:
                case 19:
                    SelectLayout[4].addView(SelectButton[i]);
                    break;
                case 20:
                case 21:
                case 22:
                case 23:
                    SelectLayout[5].addView(SelectButton[i]);
                    break;
                case 24:
                case 25:
                case 26:
                case 27:
                    SelectLayout[6].addView(SelectButton[i]);
                    break;
                case 28:
                case 29:
                case 30:
                case 31:
                    SelectLayout[7].addView(SelectButton[i]);
                    break;
                case 32:
                case 33:
                case 34:
                case 35:
                    SelectLayout[8].addView(SelectButton[i]);
                    break;
                case 36:
                case 37:
                case 38:
                case 39:
                    SelectLayout[9].addView(SelectButton[i]);
                    break;
            }
        }
    }

    public void createButtonLayout() {
        ButtonLayout = new LinearLayout(context);
        ButtonLayout.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        ButtonLayout.setOrientation(LinearLayout.HORIZONTAL);
        view.addView(ButtonLayout);
    }

    public void createTransmissionButton() {
        TransmissionButton = new Button(context);
        TransmissionButton.setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TransmissionButton.setText("決定");
        if (length >= 32)
            TransmissionButton.setTextSize(5 * Tower.getScaleSize(context));
        else
            TransmissionButton.setTextSize(10 * Tower.getScaleSize(context));
        TransmissionButton.setTextColor(Color.BLUE);
        TransmissionButton.setShadowLayer(5, 0, 0, Color.WHITE);
        TransmissionButton.setBackgroundResource(R.drawable.zzz_q2_button_custom);
        ButtonLayout.addView(TransmissionButton);
    }

    public void createResetButton() {
        ResetButton = new Button(context);
        ResetButton.setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        ResetButton.setText("リセット");
        if (length >= 32)
            ResetButton.setTextSize(5 * Tower.getScaleSize(context));
        else
            ResetButton.setTextSize(10 * Tower.getScaleSize(context));
        ResetButton.setTextColor(Color.RED);
        ResetButton.setShadowLayer(5, 0, 0, Color.WHITE);
        ResetButton.setBackgroundResource(R.drawable.zzz_q2_button_custom);
        ResetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.button();
                reset();
            }
        });
        ButtonLayout.addView(ResetButton);
    }

    public void createQuestion() {
        int answerid = 0;// 答えのindex番号
        if (format == QWORD) {
            if (index == 10000)
                answerid = (int) Math.floor(Math.random() * DataBase.word.get(level).size());
            else
                answerid = index;
            Word word = DataBase.word.get(level).get(answerid);
            Answer = word.WORD;// 単語代入
            Over.answer = Answer;
            for (int i = 0; i < Answer.length(); i++) {
                Engrave.add(String.valueOf(Answer.charAt(i)));// 一文字ずつ配列に代入
            }
            if (Answer.length() < length) {// 余った容量にランダムな文字代入
                for (int i = 0; i < length - Answer.length(); i++) {
                    Engrave.add(DataBase.character.get((int) Math.floor(Math.random() * DataBase.character.size())).Char);
                }
            } else if (Answer.length() > length) {// 足りなければ追加
                while (length != Answer.length()) {
                    length++;
                }
                while (length % 4 != 0) {
                    length += 1;
                    Engrave.add(DataBase.character.get((int) Math.floor(Math.random() * DataBase.character.size())).Char);
                }
            }
            // QuestionImage.setImageResource(word.IMAGE);// 問題画像貼り付け
            QuestionText.setText(word.JAPANESE);
        } else if (format == QPHRASE_JAPANESE || format == QPHRASE_IMAGES) {
            Phrase phrase = DataBase.phrase.get(level).get(answerid);
            Answer = phrase.PHRASE;
            Over.answer = Answer;
            for (int i = 0; i < phrase.WORD.length; i++) {
                Engrave.add(phrase.WORD[i]);
            }
            if (phrase.WORD.length < length) {
                for (int i = 0; i < length - phrase.WORD.length; i++) {
                    if (Values.levelfloor >= 50) {
                        int rnd = (int) Math.floor(Math.random() * 2);
                        if (rnd == 0)
                            Engrave.add(DataBase.character.get((int) Math.floor(Math.random() * DataBase.character.size())).Char);
                        else
                            Engrave.add(DataBase.word.get(level).get((int) Math.floor(Math.random() * DataBase.word.get(level).size())).WORD);

                    } else
                        Engrave.add(DataBase.word.get(level).get((int) Math.floor(Math.random() * DataBase.word.get(level).size())).WORD);
                }
            } else if (phrase.WORD.length > length) {
                while (length != phrase.WORD.length) {
                    length++;
                }
                while (length % 4 != 0) {
                    length += 1;
                    if (Values.levelfloor >= 50) {
                        int rnd = (int) Math.floor(Math.random() * 2);
                        if (rnd == 0)
                            Engrave.add(DataBase.character.get((int) Math.floor(Math.random() * DataBase.character.size())).Char);
                        else
                            Engrave.add(DataBase.word.get(level).get((int) Math.floor(Math.random() * DataBase.word.get(level).size())).WORD);

                    } else
                        Engrave.add(DataBase.word.get(level).get((int) Math.floor(Math.random() * DataBase.word.get(level).size())).WORD);
                }
            }
            if (format == QPHRASE_JAPANESE) {
                QuestionText.setText(phrase.JAPANESE);
            } else if (format == QPHRASE_IMAGES) {
                for (int i = 0; i < phrase.IMAGES.length; i++) {
                    QuestionImages.add(new ImageView(context));
                    QuestionImages.get(i).setBackgroundResource(phrase.IMAGES[i]);
                }
            }
        }
        Collections.shuffle(Engrave);// 配列シャッフル
    }

    public void reset() {// リセット
        AnswerLayout.removeAllViews();
        AnswerText.clear();
        for (int i = 0; i < length; i++) {
            SelectButton[i].setEnabled(true);
            SelectButton[i].setText(Engrave.get(i));
        }
    }

    public void setMyAnswer() {// 自分の答え作成
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < AnswerText.size(); i++) {
            if (format == QWORD) {
                sb.append(AnswerText.get(i).getText().toString());
            } else if (format == QPHRASE_JAPANESE || format == QPHRASE_IMAGES) {
                if (i == AnswerText.size() - 1) {
                    sb.append(AnswerText.get(i).getText().toString());
                } else {
                    sb.append(AnswerText.get(i).getText().toString() + " ");
                }
            }
        }
        MyAnswer = new String(sb);
    }

    public boolean ChecktheAnswer(String myanswer) {// 答え確認
        if (myanswer.equals(Answer)) {
            return true;
        } else {
            reset();
            return false;
        }
    }
}

package jp.gr.java_conf.bunooboi.bmp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import jp.gr.java_conf.bunooboi.gams.FitTextView;
import jp.gr.java_conf.bunooboi.gams.Manager;

public class Overview extends Activity {
    FitTextView GameName;                            // 試合名
    EditText TeamName[] = new EditText[2];    // チーム名
    EditText PlayerName[] = new EditText[4];    // 選手名
    EditText memo;                                // メモ

    Button button[] = new Button[5];    // UI

    boolean nametf = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.overview);

        GameName = (FitTextView) findViewById(R.id.GameName);
        GameName.setTextSize(7 * Manager.getScaleSize(getApplicationContext()));

        TeamName[0] = (EditText) findViewById(R.id.team1);
        TeamName[0].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        TeamName[0].setNextFocusDownId(R.id.team2);

        TeamName[1] = (EditText) findViewById(R.id.team2);
        TeamName[1].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        TeamName[1].setNextFocusDownId(R.id.player1);

        for (int i = 0; i < PlayerName.length; i++) {
            switch (i) {
                case 0:
                    PlayerName[i] = (EditText) findViewById(R.id.player1);
                    PlayerName[i].setNextFocusDownId(R.id.player3);
                    break;
                case 1:
                    PlayerName[i] = (EditText) findViewById(R.id.player2);
                    PlayerName[i].setNextFocusDownId(R.id.player4);
                    break;
                case 2:
                    PlayerName[i] = (EditText) findViewById(R.id.player3);
                    PlayerName[i].setNextFocusDownId(R.id.player2);
                    break;
                case 3:
                    PlayerName[i] = (EditText) findViewById(R.id.player4);
                    break;
            }
            PlayerName[i].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        }
        GameName.setText(jp.gr.java_conf.bunooboi.bmp.Calculation.GameName.replaceAll(".csv", ""));
        TeamName[0].setText(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[0]);
        TeamName[1].setText(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[1]);
        if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
            PlayerName[0].setText(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0]);
            PlayerName[1].setText(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[1]);
            PlayerName[2].setText(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0]);
            PlayerName[3].setText(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[1]);
        } else {
            PlayerName[0].setText(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0]);
            PlayerName[2].setText(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0]);
        }
        button[0] = (Button) findViewById(R.id.button1);
        button[1] = (Button) findViewById(R.id.button2);
        button[2] = (Button) findViewById(R.id.button3);
        button[3] = (Button) findViewById(R.id.button4);
        button[4] = (Button) findViewById(R.id.button5);
        LayoutParams lp = button[0].getLayoutParams();
        MarginLayoutParams mlp = (MarginLayoutParams) lp;
        mlp.setMargins(Manager.getMargin(getApplicationContext()) + 2, Manager.getMargin(getApplicationContext()) + 2,
                Manager.getMargin(getApplicationContext()) + 2, Manager.getMargin(getApplicationContext()) + 2);
        for (int i = 0; i < button.length; i++) {
            button[i].setTextSize(4 * Manager.getScaleSize(getApplicationContext()));
            button[i].setLayoutParams(mlp);
        }
        button[0].setText("Score");
        button[1].setText("Larry");
        button[2].setText("Gallery");
        button[3].setText("Save");
        button[4].setText("Stop");
        button[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ScoreTable.class);
                startActivity(i);
            }
        });
        button[1].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), jp.gr.java_conf.bunooboi.bmp.Larry.class);
                startActivity(i);
            }
        });
        button[2].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Gallery.class);
                startActivity(i);
            }
        });
        button[3].setOnClickListener(new OnClickListener() {// 保存
            @Override
            public void onClick(View v) {
                if (!(TeamName[0].getText().toString().equals("")) && !(TeamName[1].getText().toString().equals(""))) {
                    if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
                        if (!(PlayerName[0].getText().toString().equals("")) && !(PlayerName[1].getText().toString().equals(""))
                                && !(PlayerName[2].getText().toString().equals("")) && !(PlayerName[3].getText().toString().equals(""))) {
                            nametf = true;
                        } else {
                            nametf = false;
                        }
                    } else {
                        if (!(PlayerName[0].getText().toString().equals("")) && !(PlayerName[2].getText().toString().equals(""))) {
                            nametf = true;
                        } else {
                            nametf = false;
                        }
                    }
                } else {
                    nametf = false;
                }
                if (nametf) {
                    getName();
                    File file = new File(jp.gr.java_conf.bunooboi.bmp.Calculation.FilePath + jp.gr.java_conf.bunooboi.bmp.Calculation.GameName + ".csv");
                    file.delete();
                    jp.gr.java_conf.bunooboi.bmp.Export e = new jp.gr.java_conf.bunooboi.bmp.Export(Overview.this);
                    e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0], jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[0], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                    e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0], jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[0], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                    if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
                        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[1], jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[1], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[1], jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[1], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                    }
                    e.newLine();
                    e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0], jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[0], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                    e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0], jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[0], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                    if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
                        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[1], jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[1], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[1], jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[1], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                    }
                    e.newLine();
                    e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[0], jp.gr.java_conf.bunooboi.bmp.Calculation.Score1, jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                    e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[1], jp.gr.java_conf.bunooboi.bmp.Calculation.Score2, jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
                    e.newLine();
                    for (int i = 0; i < jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame; i++) {
                        e.addLongDate(jp.gr.java_conf.bunooboi.bmp.Calculation.Event.get(i));
                    }
                    e.newLine();
                    for (int i = 0; i < jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame; i++) {
                        e.addLongDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PressTime.get(i));
                    }
                    e.newLine();
                    e.Save();
                    e.createMemo(memo.getText().toString(), jp.gr.java_conf.bunooboi.bmp.Calculation.FilePath + jp.gr.java_conf.bunooboi.bmp.Calculation.GameName + ".txt");
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "名前を入力してください", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[4].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        memo = (EditText) findViewById(R.id.memo);// メモ作成
        memo.setTextSize(4 * Manager.getScaleSize(getApplicationContext()));
        memo.setPadding(Manager.getMargin(getApplicationContext()) + 10, Manager.getMargin(getApplicationContext()) + 5,
                Manager.getMargin(getApplicationContext()) + 10, 0);
        LayoutParams lp2 = memo.getLayoutParams();
        MarginLayoutParams mlp2 = (MarginLayoutParams) lp2;
        mlp2.setMargins(Manager.getMargin(getApplicationContext()), Manager.getMargin(getApplicationContext()),
                Manager.getMargin(getApplicationContext()), Manager.getMargin(getApplicationContext()));
        memo.setLayoutParams(mlp2);
        memo.setText(jp.gr.java_conf.bunooboi.bmp.Calculation.Memo);
    }

    void getName() {// 名前入手
        jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[0] = TeamName[0].getText().toString();
        jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[1] = TeamName[1].getText().toString();
        if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
            jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0] = PlayerName[0].getText().toString();
            jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[1] = PlayerName[1].getText().toString();
            jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0] = PlayerName[2].getText().toString();
            jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[1] = PlayerName[3].getText().toString();
        } else {
            jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0] = PlayerName[0].getText().toString();
            jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0] = PlayerName[2].getText().toString();
        }
    }
}

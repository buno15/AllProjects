package jp.gr.java_conf.bunooboi.bmp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.gams.FitTextView;
import jp.gr.java_conf.bunooboi.gams.Manager;
import jp.gr.java_conf.bunooboi.gams.Table;

public class ScoreTable extends Activity {
    LinearLayout Title; // ゲーム１、ゲーム２、ゲーム３
    LinearLayout Team1; // チーム１メンバー名前等、表
    LinearLayout Team2; // チーム２メンバー名前等、表
    LinearLayout Score1; // チーム１スコア
    LinearLayout Score2; // チーム２スコア
    LinearLayout Meaning; // 各値説明

    Table table[] = new Table[3];
    Table table1[];
    Table table2[];

    LinearLayout PlayerGraphs[] = new LinearLayout[jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople];

    Button button[] = new Button[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.scoretable);

        Title = (LinearLayout) findViewById(R.id.title);
        Team1 = (LinearLayout) findViewById(R.id.team1);
        Team2 = (LinearLayout) findViewById(R.id.team2);
        Score1 = (LinearLayout) findViewById(R.id.score1);
        Score2 = (LinearLayout) findViewById(R.id.score2);
        Meaning = (LinearLayout) findViewById(R.id.MeaningLayout);

        table[0] = new Table(this, Title, Calculation.MaxGame + 1, 1);
        table[1] = new Table(this, Score1, Calculation.MaxGame + 1, 1);
        table[2] = new Table(this, Score2, Calculation.MaxGame + 1, 1);
        for (int i = 0; i < table.length; i++) {
            table[i].createTable();
            if (Calculation.MaxGame == 1)
                table[i].setWeight(0, 0, 0.5f);
        }
        if (Calculation.MaxPeople == 4) {
            table1 = new Table[2];
            table2 = new Table[2];
            if (Calculation.MaxGame == 3) {
                table1[0] = new Table(this, Team1, 14, 2);
                table1[1] = new Table(this, Team1, 14, 2);
                table2[0] = new Table(this, Team2, 14, 2);
                table2[1] = new Table(this, Team2, 14, 2);
            } else {
                table1[0] = new Table(this, Team1, 6, 2);
                table1[1] = new Table(this, Team1, 6, 2);
                table2[0] = new Table(this, Team2, 6, 2);
                table2[1] = new Table(this, Team2, 6, 2);
            }
            table1[0].createTable();
            table2[0].createTable();
            table1[1].createTable();
            table2[1].createTable();
            table1[1].getViewGroup(0, 0).setBackgroundResource(TeamObject.getWare(Calculation.TeamColor[0]));
            table2[1].getViewGroup(0, 0).setBackgroundResource(TeamObject.getWare(Calculation.TeamColor[1]));
        } else {
            table1 = new Table[1];
            table2 = new Table[1];
            if (Calculation.MaxGame == 3) {
                table1[0] = new Table(this, Team1, 14, 2);
                table2[0] = new Table(this, Team2, 14, 2);
            } else {
                table1[0] = new Table(this, Team1, 6, 2);
                table2[0] = new Table(this, Team2, 6, 2);
            }
            table1[0].createTable();
            table2[0].createTable();
        }
        table1[0].getViewGroup(0, 0).setBackgroundResource(TeamObject.getWare(Calculation.TeamColor[0]));
        table2[0].getViewGroup(0, 0).setBackgroundResource(TeamObject.getWare(Calculation.TeamColor[1]));
        if (Calculation.MaxGame == 3) {
            table1[0].setWeight(0, 0, 2);
            table1[0].setWeight(1, 0, 2);
            table1[0].setWeight(0, 1, 2);
            table1[0].setWeight(1, 1, 2);
            table2[0].setWeight(0, 0, 2);
            table2[0].setWeight(1, 0, 2);
            table2[0].setWeight(0, 1, 2);
            table2[0].setWeight(1, 1, 2);
            if (Calculation.MaxPeople == 4) {
                table1[1].setWeight(0, 0, 2);
                table1[1].setWeight(1, 0, 2);
                table1[1].setWeight(0, 1, 2);
                table1[1].setWeight(1, 1, 2);
                table2[1].setWeight(0, 0, 2);
                table2[1].setWeight(1, 0, 2);
                table2[1].setWeight(0, 1, 2);
                table2[1].setWeight(1, 1, 2);
            }
        }
        FitTextView GameName = new FitTextView(this); // 試合名
        FitTextView Team1Name = new FitTextView(this); // チーム１名前
        FitTextView Team2Name = new FitTextView(this); // チーム２名前
        FitTextView game[] = new FitTextView[Calculation.MaxGame];
        for (int i = 0; i < game.length; i++) {
            game[i] = new FitTextView(this);
            createName(game[i]);
            game[i].setTextSize(3 * Manager.getScaleSize(getApplicationContext()));
            game[i].setText("Game" + String.valueOf(i + 1));
            table[0].getViewGroup(i + 1, 0).addView(game[i]);
        }
        createName(GameName);
        createName(Team1Name);
        createName(Team2Name);
        GameName.setTextSize(3 * Manager.getScaleSize(getApplicationContext()));
        GameName.setText(Calculation.GameName);
        Team1Name.setText(Calculation.TeamName[0]);
        Team2Name.setText(Calculation.TeamName[1]);
        table[0].getViewGroup(0, 0).addView(GameName);
        table[1].getViewGroup(0, 0).addView(Team1Name);
        table[2].getViewGroup(0, 0).addView(Team2Name);

        FitTextView Player[] = new FitTextView[Calculation.MaxPeople];
        for (int i = 0; i < Player.length; i++) {
            Player[i] = new FitTextView(this);
            Player[i].setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
            Player[i].setGravity(Gravity.CENTER);
            switch (i) {
                case 0:
                    Player[i].setText(Calculation.PlayerName1[0]);
                    table1[0].getViewGroup(0, 1).addView(Player[i]);
                    break;
                case 1:
                    Player[i].setText(Calculation.PlayerName2[0]);
                    table2[0].getViewGroup(0, 1).addView(Player[i]);
                    break;
                case 2:
                    Player[i].setText(Calculation.PlayerName1[1]);
                    table1[1].getViewGroup(0, 1).addView(Player[i]);
                    break;
                case 3:
                    Player[i].setText(Calculation.PlayerName2[1]);
                    table2[1].getViewGroup(0, 1).addView(Player[i]);
                    break;
            }
        }
        createAceMissImage(table1[0]);
        createAceMissImage(table2[0]);
        if (Calculation.MaxPeople == 4) {
            createAceMissImage(table1[1]);
            createAceMissImage(table2[1]);
        }
        createScore(table[1], Calculation.Score1);
        createScore(table[2], Calculation.Score2);
        createAceMiss(table1[0], Calculation.Ace1[0], Calculation.Miss1[0], new int[]{2, 3, 4, 5, 6, 7});
        createAceMiss(table2[0], Calculation.Ace2[0], Calculation.Miss2[0], new int[]{8, 9, 10, 11, 12, 13});
        if (Calculation.MaxPeople == 4) {
            createAceMiss(table1[1], Calculation.Ace1[1], Calculation.Miss1[1], new int[]{14, 15, 16, 17, 18, 19});
            createAceMiss(table2[1], Calculation.Ace2[1], Calculation.Miss2[1], new int[]{20, 21, 22, 23, 24, 25});
        }

        Meaning m = new Meaning(this);
        m.addView(Meaning);

        button[0] = (Button) findViewById(R.id.button1);
        button[1] = (Button) findViewById(R.id.button2);
        button[2] = (Button) findViewById(R.id.button3);
        button[0].setText("Overview");
        button[1].setText("Larry");
        button[2].setText("Gallery");
        button[0].setTextSize(4 * Manager.getScaleSize(getApplicationContext()));
        button[1].setTextSize(4 * Manager.getScaleSize(getApplicationContext()));
        button[2].setTextSize(4 * Manager.getScaleSize(getApplicationContext()));
        button[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Overview.class);
                startActivity(i);
                finish();
            }
        });
        button[1].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Larry.class);
                startActivity(i);
                finish();
            }
        });
        button[2].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Gallery.class);
                startActivity(i);
                finish();
            }
        });
    }

    void createName(FitTextView ftv) {
        ftv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ftv.setTextSize(4 * Manager.getScaleSize(getApplicationContext()));
        ftv.setGravity(Gravity.CENTER);
        ftv.setBackgroundResource(R.drawable.text_white);
    }

    void createAceMissImage(Table table) {
        ImageView ace = new ImageView(this);
        ImageView miss = new ImageView(this);
        ace.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        miss.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ace.setBackgroundResource(R.drawable.ace_button1);
        miss.setBackgroundResource(R.drawable.miss_button1);
        table.getViewGroup(1, 0).addView(ace);
        table.getViewGroup(1, 1).addView(miss);
    }

    void createAceMiss(Table table, int ace[], int miss[], int id[]) {
        TextView acetext[] = new TextView[Calculation.MaxGame * 4];
        TextView misstext[] = new TextView[Calculation.MaxGame * 4];
        for (int i = 0; i < acetext.length; i++) {
            acetext[i] = new TextView(this);
            acetext[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            acetext[i].setGravity(Gravity.CENTER);
            acetext[i].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
            acetext[i].setBackgroundResource(R.drawable.text_white);
            misstext[i] = new TextView(this);
            misstext[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            misstext[i].setGravity(Gravity.CENTER);
            misstext[i].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
            misstext[i].setBackgroundResource(R.drawable.text_white);
            switch (i) {
                case 0:
                    acetext[i].setText(String.valueOf(ace[0]));
                    misstext[i].setText(String.valueOf(miss[0]));
                    break;
                case 1:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(0), id[0])));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(0), id[3])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_GOOD));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_BAD));
                    break;
                case 2:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(0), id[1])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_NORMAL));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_NORMAL));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(0), id[4])));
                    break;
                case 3:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(0), id[2])));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(0), id[5])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_LUCKY));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_UNLUCKY));
                    break;
                case 4:
                    acetext[i].setText(String.valueOf(ace[1]));
                    misstext[i].setText(String.valueOf(miss[1]));
                    break;
                case 5:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(1), id[0])));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(1), id[3])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_GOOD));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_BAD));
                    break;
                case 6:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(1), id[1])));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(1), id[4])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_NORMAL));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_NORMAL));
                    break;
                case 7:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(1), id[2])));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(1), id[5])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_LUCKY));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_UNLUCKY));
                    break;
                case 8:
                    acetext[i].setText(String.valueOf(ace[2]));
                    misstext[i].setText(String.valueOf(miss[2]));
                    break;
                case 9:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(2), id[0])));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(2), id[3])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_GOOD));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_BAD));
                    break;
                case 10:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(2), id[1])));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(2), id[4])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_NORMAL));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_NORMAL));
                    break;
                case 11:
                    acetext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(2), id[2])));
                    misstext[i].setText(String.valueOf(readAceMiss(Calculation.Event.get(2), id[5])));
                    acetext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.ACE_LUCKY));
                    misstext[i].setBackgroundResource(AceMissObject.getGraph(AceMissObject.MISS_UNLUCKY));
                    break;
            }
            table.getViewGroup(i + 2, 0).addView(acetext[i]);
            table.getViewGroup(i + 2, 1).addView(misstext[i]);
        }
    }

    int readAceMiss(ArrayList<Integer> event, int id) {
        int value = 0;
        for (int i = 0; i < Calculation.MaxEvent; i++) {
            if (event.get(i) == id) {
                value++;
            }
        }
        return value;
    }

    void createScore(Table table, int score[]) {
        TextView textview[] = new TextView[Calculation.MaxGame];
        for (int i = 0; i < textview.length; i++) {
            textview[i] = new TextView(this);
            textview[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            textview[i].setText(String.valueOf(score[i]));
            textview[i].setTextSize(4 * Manager.getScaleSize(getApplicationContext()));
            textview[i].setBackgroundResource(R.drawable.score_text);
            textview[i].setGravity(Gravity.CENTER);
            table.getViewGroup(i + 1, 0).addView(textview[i]);
        }
    }
}

package jp.gr.java_conf.bunooboi.bmp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.gams.AceMiss;
import jp.gr.java_conf.bunooboi.gams.Capture;
import jp.gr.java_conf.bunooboi.gams.Graph;
import jp.gr.java_conf.bunooboi.gams.Manager;
import jp.gr.java_conf.bunooboi.gams.PressTime;
import jp.gr.java_conf.bunooboi.gams.Recording;
import jp.gr.java_conf.bunooboi.gams.ScoreBoard;
import jp.gr.java_conf.bunooboi.gams.Table;

public class Main extends Activity {
    LinearLayout GraphText;
    LinearLayout GraphLayout;
    LinearLayout Meaning;
    LinearLayout AceMiss1;
    LinearLayout AceMiss2;
    LinearLayout Score1;
    LinearLayout Score2;
    HorizontalScrollView HSV;

    Graph graph1[];
    Graph graph2[];
    Table table1;
    Table table2;
    Table table3;
    Table table4;
    AceMiss am1[];
    AceMiss am2[];
    ScoreBoard sb[] = new ScoreBoard[2];
    Button button[] = new Button[8];

    int ID1[];
    int ID2[];

    AlertDialog.Builder alb;// ダイアログ
    Recording r = new Recording();

    int scrollw;// スクロール幅
    boolean recording = false;// 録音しているか
    boolean fin = false;// 終わりか
    boolean returns1 = false;// 第一ゲームデータロード
    boolean returns2 = false;// 第二ゲームデータロード


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);

        alb = new AlertDialog.Builder(this);
        GraphText = (LinearLayout) findViewById(R.id.GraphText);
        GraphLayout = (LinearLayout) findViewById(R.id.GraphLayout);
        Meaning = (LinearLayout) findViewById(R.id.MeaningLayout);
        HSV = (HorizontalScrollView) findViewById(R.id.HSV);
        AceMiss1 = (LinearLayout) findViewById(R.id.AceMiss1);
        AceMiss2 = (LinearLayout) findViewById(R.id.AceMiss2);
        Score1 = (LinearLayout) findViewById(R.id.Score1);
        Score2 = (LinearLayout) findViewById(R.id.Score2);
        button[0] = (Button) findViewById(R.id.button1);
        button[1] = (Button) findViewById(R.id.button2);
        button[2] = (Button) findViewById(R.id.button3);
        button[3] = (Button) findViewById(R.id.button4);
        button[4] = (Button) findViewById(R.id.button5);
        button[5] = (Button) findViewById(R.id.button6);
        button[6] = (Button) findViewById(R.id.button7);
        button[7] = (Button) findViewById(R.id.button8);

        for (Button btn : button)
            createButton(btn);

        table1 = new Table(this, AceMiss1, 3, 2);
        table2 = new Table(this, AceMiss2, 3, 2);
        table1.createTable();
        table2.createTable();

        if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
            ID1 = new int[13];
            ID2 = new int[13];
            ID1[7] = 14;
            ID1[8] = 15;
            ID1[9] = 16;
            ID1[10] = 17;
            ID1[11] = 18;
            ID1[12] = 19;
            ID2[7] = 20;
            ID2[8] = 21;
            ID2[9] = 22;
            ID2[10] = 23;
            ID2[11] = 24;
            ID2[12] = 25;
        } else {
            ID1 = new int[7];
            ID2 = new int[7];
        }
        ID1[0] = 0;
        ID2[0] = 1;
        ID1[1] = 2;
        ID1[2] = 3;
        ID1[3] = 4;
        ID1[4] = 5;
        ID1[5] = 6;
        ID1[6] = 7;
        ID2[1] = 8;
        ID2[2] = 9;
        ID2[3] = 10;
        ID2[4] = 11;
        ID2[5] = 12;
        ID2[6] = 13;


        if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {//ダブルス
            table3 = new Table(this, AceMiss1, 3, 2);
            table4 = new Table(this, AceMiss2, 3, 2);
            table3.createTable();
            table4.createTable();
            graph1 = new Graph[3];
            graph2 = new Graph[3];
            am1 = new AceMiss[2];
            am2 = new AceMiss[2];
            graph1[0] = new Graph(this, GraphText, GraphLayout);
            graph1[0].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
            graph1[0].createGraph();
            graph2[0] = new Graph(this, GraphText, GraphLayout);
            graph2[0].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
            graph2[0].createGraph();
            graph1[1] = new Graph(this, GraphText, GraphLayout);
            graph1[1].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
            graph1[1].createGraph();
            graph1[2] = new Graph(this, GraphText, GraphLayout);
            graph1[2].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
            graph1[2].createGraph();
            graph2[1] = new Graph(this, GraphText, GraphLayout);
            graph2[1].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
            graph2[1].createGraph();
            graph2[2] = new Graph(this, GraphText, GraphLayout);
            graph2[2].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
            graph2[2].createGraph();
            graph1[2].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[1]);
            graph2[2].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[1]);
            graph1[2].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0]));
            graph2[2].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));
            am1[0] = new AceMiss(this);
            am2[0] = new AceMiss(this);
            am1[1] = new AceMiss(this);
            am2[1] = new AceMiss(this);
            am1[1].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[1]);
            am2[1].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[1]);
            am1[1].setWearImage(TeamObject.getWare(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0]));
            am2[1].setWearImage(TeamObject.getWare(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));
            am1[1].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[1][0]);
            am1[1].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[1][0]);
            am2[1].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[1][0]);
            am2[1].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[1][0]);
            setAdapter(am1[1], ID1, 7);
            setAdapter(am2[1], ID2, 7);
            addTable(table3, am1[1]);
            addTable(table4, am2[1]);
        } else {//シングルス
            graph1 = new Graph[2];
            graph2 = new Graph[2];
            am1 = new AceMiss[1];
            am2 = new AceMiss[1];
            for (int i = 0; i < graph1.length; i++) {
                graph1[i] = new Graph(this, GraphText, GraphLayout);
                graph1[i].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
                graph1[i].createGraph();
                graph2[i] = new Graph(this, GraphText, GraphLayout);
                graph2[i].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
                graph2[i].createGraph();
            }
            am1[0] = new AceMiss(this);
            am2[0] = new AceMiss(this);
        }
        graph1[0].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[0]);
        graph2[0].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[1]);
        graph1[1].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0]);
        graph2[1].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0]);
        graph1[0].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0]));
        graph2[0].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));
        graph1[1].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0]));
        graph2[1].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));
        am1[0].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0]);
        am2[0].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0]);
        am1[0].setWearImage(TeamObject.getWare(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0]));
        am2[0].setWearImage(TeamObject.getWare(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));
        am1[0].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[0][0]);
        am1[0].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[0][0]);
        am2[0].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[0][0]);
        am2[0].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[0][0]);
        setAdapter(am1[0], ID1, 1);
        setAdapter(am2[0], ID2, 1);
        addTable(table1, am1[0]);
        addTable(table2, am2[0]);

        if (Calculation.MaxGame != 1) {// ゲーム数が１以上ならスコア表作成
            sb[0] = new ScoreBoard(this, Score1, 2);
            sb[1] = new ScoreBoard(this, Score2, 2);
            for (int i = 0; i < 2; i++) {
                sb[0].setPastScore(i, jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[i]);//過去データ得点設定
                sb[1].setPastScore(i, jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[i]);
            }
        } else {
            sb[0] = new ScoreBoard(this, Score1);
            sb[1] = new ScoreBoard(this, Score2);
        }
        sb[0].setTextColor(TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0]));
        sb[1].setTextColor(TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));
        sb[0].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[0]);// ScoreBoard得点設定
        sb[1].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[0]);

        Meaning m = new Meaning(this);
        m.addView(Meaning);

        button[1].setEnabled(false);// Next使用禁止
        button[6].setEnabled(false);//Game1使用禁止
        button[7].setEnabled(false);//Game2使用禁止

        button[0].setText("Back");
        button[1].setText("Next");
        button[2].setText("Cap");
        button[3].setText("Rec");
        button[4].setText("Help");
        button[5].setText("Data");
        button[6].setText("Game1");
        button[7].setText("Game2");

        button[0].setOnClickListener(new OnClickListener() {// 戻る
            @Override
            public void onClick(View v) {
                Back();
            }
        });
        button[1].setOnClickListener(new OnClickListener() {// Next
            @Override
            public void onClick(View v) {
                jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount++;
                jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount = 0;
                if (fin) {// 試合終了なら
                    jp.gr.java_conf.bunooboi.bmp.Calculation.GameEndTime = PressTime.getPressTimeStringAlls();// 試合終了時間取得
                    Save();
                } else if ((jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount >= Calculation.MaxGame) || (jp.gr.java_conf.bunooboi.bmp.Calculation.Team1Win >= 2 && jp.gr.java_conf.bunooboi.bmp.Calculation.Team2Win == 0)
                        || (jp.gr.java_conf.bunooboi.bmp.Calculation.Team2Win >= 2 && jp.gr.java_conf.bunooboi.bmp.Calculation.Team1Win == 0)) {// ゲームカウントがマックスになったら、どちらかが２点取ったら
                    Toast.makeText(getApplicationContext(), "試合終了", Toast.LENGTH_SHORT).show();
                    button[1].setText("Save");
                    button[0].setEnabled(false);
                    button[6].setEnabled(false);
                    button[7].setEnabled(false);
                    fin = true;
                } else {// NextGame
                    button[1].setEnabled(false);
                    Escape();
                }
            }
        });
        button[2].setOnClickListener(new OnClickListener() {// 画面保存
            @Override
            public void onClick(View v) {
                Capture c = new Capture();
                c.saveCapture(findViewById(R.id.root), getApplicationContext(), jp.gr.java_conf.bunooboi.bmp.Calculation.GameName);
            }
        });
        button[3].setOnClickListener(new OnClickListener() {// 録音
            @Override
            public void onClick(View v) {
                if (recording == false) {
                    r.start(getApplicationContext(), jp.gr.java_conf.bunooboi.bmp.Calculation.GameName);
                    button[3].setText("Stop");
                    recording = true;
                } else {
                    r.stop();
                    button[3].setText("Rec");
                    recording = false;
                }
            }
        });
        button[4].setOnClickListener(new OnClickListener() {//ヘルプ
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://bunooboi.sakura.ne.jp/src/html/app/bmp.html");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
        button[5].setOnClickListener(new OnClickListener() {// 履歴
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), jp.gr.java_conf.bunooboi.bmp.History.class);
                startActivity(i);
            }
        });
        button[6].setOnClickListener(new OnClickListener() {//第一ゲーム閲覧
            @Override
            public void onClick(View v) {
                if (returns1 == false) {
                    jp.gr.java_conf.bunooboi.bmp.Calculation.ReadEvent(Calculation.Event.get(0), graph1, graph2);
                    button[7].setText("Game2");
                    returns2 = false;
                    button[6].setText("Back");
                    returns1 = true;
                    allViewEnabled(false);
                    button[0].setEnabled(false);
                    sb[0].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[0]);
                    sb[1].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[0]);
                    for (int i = 0; i < jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople / 2; i++) {
                        am1[i].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[i][0]);
                        am1[i].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[i][0]);
                        am2[i].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[i][0]);
                        am2[i].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[i][0]);
                    }
                } else {
                    jp.gr.java_conf.bunooboi.bmp.Calculation.ReadEvent(Calculation.Event.get(jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount), graph1, graph2);
                    button[6].setText("Game1");
                    returns1 = false;
                    allViewEnabled(true);
                    button[0].setEnabled(true);
                    sb[0].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount]);
                    sb[1].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount]);
                    for (int i = 0; i < jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople / 2; i++) {
                        am1[i].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[i][Calculation.GameCount]);
                        am1[i].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[i][Calculation.GameCount]);
                        am2[i].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[i][Calculation.GameCount]);
                        am2[i].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[i][Calculation.GameCount]);
                    }
                }
            }
        });
        button[7].setOnClickListener(new OnClickListener() {//第二ゲーム閲覧
            @Override
            public void onClick(View v) {
                if (returns2 == false) {
                    jp.gr.java_conf.bunooboi.bmp.Calculation.ReadEvent(Calculation.Event.get(1), graph1, graph2);
                    button[6].setText("Game1");
                    returns1 = false;
                    button[7].setText("Back");
                    returns2 = true;
                    allViewEnabled(false);
                    button[0].setEnabled(false);
                    sb[0].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[1]);
                    sb[1].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[1]);
                    for (int i = 0; i < jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople / 2; i++) {
                        am1[i].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[i][1]);
                        am1[i].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[i][1]);
                        am2[i].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[i][1]);
                        am2[i].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[i][1]);
                    }
                } else {
                    jp.gr.java_conf.bunooboi.bmp.Calculation.ReadEvent(Calculation.Event.get(jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount), graph1, graph2);
                    button[7].setText("Game2");
                    returns2 = false;
                    allViewEnabled(true);
                    button[0].setEnabled(true);
                    sb[0].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount]);
                    sb[1].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount]);
                    for (int i = 0; i < jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople / 2; i++) {
                        am1[i].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[i][Calculation.GameCount]);
                        am1[i].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[i][Calculation.GameCount]);
                        am2[i].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[i][Calculation.GameCount]);
                        am2[i].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[i][Calculation.GameCount]);
                    }
                }
            }
        });

        sb[0].ScoreButton.setOnClickListener(new OnClickListener() {//Scoreボタン（チーム１）Click
            @Override
            public void onClick(View v) {
                if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent()) {
                    Scroll();// スクロール
                    jp.gr.java_conf.bunooboi.bmp.Calculation.Event.get(Calculation.GameCount).set(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ID1[0]);
                    jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount]++;
                    graph1[0].setCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, Calculation
                            .Score1[jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount]);
                    sb[0].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount]);
                    jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount++;
                    if (jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount] >= jp.gr.java_conf.bunooboi.bmp.Calculation.WinScore) {// チーム１勝利
                        if (jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount] - jp.gr.java_conf.bunooboi.bmp.Calculation.WinDif >= Calculation.Score2[jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount]) {// 点差計算
                            allViewEnabled(false);// 全View使用禁止
                            button[1].setEnabled(true);// Next解禁
                            jp.gr.java_conf.bunooboi.bmp.Calculation.Team1Win++;// Winプラス
                            Toast.makeText(getApplicationContext(), "第" + (jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount + 1) + "ゲーム終了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        sb[1].ScoreButton.setOnClickListener(new OnClickListener() {//Scoreボタン（チーム２）Click
            @Override
            public void onClick(View v) {
                if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent()) {
                    Scroll();// スクロール
                    jp.gr.java_conf.bunooboi.bmp.Calculation.Event.get(jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount).set(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ID2[0]);
                    jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount]++;
                    graph2[0].setCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, Calculation
                            .Score2[jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount]);
                    sb[1].setScore(jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount]);
                    jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount++;
                    if (jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount] >= jp.gr.java_conf.bunooboi.bmp.Calculation.WinScore) {// チーム１勝利
                        if (jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount] - jp.gr.java_conf.bunooboi.bmp.Calculation.WinDif >= Calculation
                                .Score1[jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount]) {// 点差計算
                            allViewEnabled(false);// 全View使用禁止
                            button[1].setEnabled(true);// Next解禁
                            jp.gr.java_conf.bunooboi.bmp.Calculation.Team2Win++;// Winプラス
                            Toast.makeText(getApplicationContext(), "第" + (jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount + 1) + "ゲーム終了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        setScroll(getApplicationContext());
        jp.gr.java_conf.bunooboi.bmp.Calculation.GameStartTime = PressTime.getPressTimeStringAlls();// 試合開始時間取得
    }

    public void createButton(Button btn) {//下のメニューのボタン作成
        if (btn.equals(button[6]) || btn.equals(button[7]))
            btn.setTextSize(2 * Manager.getScaleSize(getApplicationContext()));
        else
            btn.setTextSize(3 * Manager.getScaleSize(getApplicationContext()));
    }


    void allViewEnabled(boolean b) {//全View（button,spinner）Enableかどうか
        for (int i = 0; i < am1.length; i++) {
            am1[i].setEnabled(b);
            am2[i].setEnabled(b);
        }
        for (int i = 0; i < 2; i++) {
            sb[i].setEnabled(b);
        }
    }

    void addTable(Table table, AceMiss am) {//TableにAceMiss貼り付け
        table.getViewGroup(0, 0).addView(am.getWearImage());
        table.getViewGroup(1, 0).addView(am.getAceSpinner());
        table.getViewGroup(2, 0).addView(am.getAceText());
        table.getViewGroup(0, 1).addView(am.getPlayerText());
        table.getViewGroup(1, 1).addView(am.getMissSpinner());
        table.getViewGroup(2, 1).addView(am.getMissText());
    }


    void Back() {// 戻る
        if (jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount > 0) {
            int ID = jp.gr.java_conf.bunooboi.bmp.Calculation.Event.get(Calculation.GameCount).get(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1);// 一個前のID取得
            if (jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount] >= jp.gr.java_conf.bunooboi.bmp.Calculation.WinScore) {// チーム１が勝っていたら
                if (jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount] - jp.gr.java_conf.bunooboi.bmp.Calculation.WinDif >=
                        jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount]) {
                    jp.gr.java_conf.bunooboi.bmp.Calculation.Team1Win--;// Win--
                    button[1].setEnabled(false);// Next使用禁止
                    allViewEnabled(true);// 全View解禁
                }
            }
            if (jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount] >= jp.gr.java_conf.bunooboi.bmp.Calculation.WinScore) {//チーム２が勝っていたら
                if (jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount] - jp.gr.java_conf.bunooboi.bmp.Calculation.WinDif >=
                        jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount]) {
                    jp.gr.java_conf.bunooboi.bmp.Calculation.Team2Win--;
                    button[1].setEnabled(false);
                    allViewEnabled(true);
                }
            }
            switch (ID) {// IDがどれか
                case 0:
                    graph1[0].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    sb[0].setScore(--jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[Calculation.GameCount]);
                    break;
                case 1:
                    graph2[0].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    sb[1].setScore(--jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[Calculation.GameCount]);
                    break;
                case 2:
                case 3:
                case 4:
                    graph1[1].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    am1[0].setAce(--jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[0][Calculation.GameCount]);
                    break;
                case 5:
                case 6:
                case 7:
                    graph1[1].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    am1[0].setMiss(--jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[0][Calculation.GameCount]);
                    break;
                case 8:
                case 9:
                case 10:
                    graph2[1].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    am2[0].setAce(--jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[0][Calculation.GameCount]);
                    break;
                case 11:
                case 12:
                case 13:
                    graph2[1].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    am2[0].setMiss(--jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[0][Calculation.GameCount]);
                    break;
                case 14:
                case 15:
                case 16:
                    graph1[2].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    am1[1].setAce(--jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[1][Calculation.GameCount]);
                    break;
                case 17:
                case 18:
                case 19:
                    graph1[2].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    am1[1].setMiss(--jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[1][Calculation.GameCount]);
                    break;
                case 20:
                case 21:
                case 22:
                    graph2[2].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    am2[1].setAce(--jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[1][Calculation.GameCount]);
                    break;
                case 23:
                case 24:
                case 25:
                    graph2[2].setUnCheckScoreUnit(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 0);
                    am2[1].setMiss(--jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[1][Calculation.GameCount]);
                    break;
            }
            jp.gr.java_conf.bunooboi.bmp.Calculation.Event.get(Calculation.GameCount).set(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount - 1, 100);// 前のイベントデータ消す
            jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount--;// EventCount一つ戻る
            if (jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount > 50) {// スクロール左に
                HSV.post(new Runnable() {
                    public void run() {
                        HSV.scrollTo(scrollw - (int) Manager.getDispW(getApplicationContext()) / 70, 0);
                        scrollw -= (int) Manager.getDispW(getApplicationContext()) / 70;
                    }
                });
            }
            if (jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount == 50) {// スクロール一番左に
                ScrollLeft();
            }
        }
    }

    void Escape() {// Next
        jp.gr.java_conf.bunooboi.bmp.Calculation.clearGraph(graph1, graph2);
        sb[0].setScore(0);// テキストリセット
        sb[1].setScore(0);
        for (int i = 0; i < jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame - 1; i++) {// スコア表セットテキスト
            sb[0].setPastScore(i, jp.gr.java_conf.bunooboi.bmp.Calculation.Score1[i]);
            sb[1].setPastScore(i, jp.gr.java_conf.bunooboi.bmp.Calculation.Score2[i]);
        }
        for (int i = 0; i < jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople / 2; i++) {// AceMissリセット
            am1[i].setAce(0);
            am1[i].setMiss(0);
            am2[i].setAce(0);
            am2[i].setMiss(0);
        }
        if (Calculation.MaxGame == 3) {
            if (Calculation.GameCount >= 1)
                button[6].setEnabled(true);
            if (Calculation.GameCount >= 2)
                button[7].setEnabled(true);
        }
        scrollw = (int) Manager.getDispW(getApplicationContext()) / 70;// スクロール値リセット
        ScrollLeft();// スクロール右に戻す
        allViewEnabled(true);// View開放
    }

    void Save() {// 保存
        jp.gr.java_conf.bunooboi.bmp.Export e = new Export(this);
        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0], Calculation.Ace1[0], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0], Calculation.Ace2[0], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
        if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
            e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[1], Calculation.Ace1[1], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
            e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[1], Calculation.Ace2[1], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
        }
        e.newLine();
        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0], Calculation.Miss1[0], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0], Calculation.Miss2[0], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
        if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
            e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[1], Calculation.Miss1[1], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
            e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[1], Calculation.Miss2[1], jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
        }
        e.newLine();
        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[0], Calculation.Score1, jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
        e.addScoreDate(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[1], Calculation.Score2, jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame);
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
        button[1].setEnabled(false);
    }

    public void Finish() {//終了
        alb.setTitle("Warning");
        alb.setMessage("保存していないデータは失われます。\nアプリを終了しますか?");
        alb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch) {
                jp.gr.java_conf.bunooboi.bmp.Calculation.Remove();
                finish();
            }
        });
        alb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch) {

            }
        });
        alb.create().show();
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {//Backキー押した時
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Finish();
        }
        return super.onKeyDown(keycode, event);
    }


    public void Scroll() {//スクロール
        if (jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount > 50) {
            System.out.println(scrollw);
            HSV.post(new Runnable() {
                public void run() {
                    HSV.scrollTo(scrollw += (int) Manager.getDispW(getApplicationContext()) / 70, 0);
                }
            });
        } else {
            HSV.post(new Runnable() {
                public void run() {
                    ScrollLeft();
                }
            });
        }
    }

    public void ScrollLeft() {//左端までスクロール
        HSV.post(new Runnable() {
            public void run() {
                HSV.fullScroll(HorizontalScrollView.FOCUS_LEFT);
            }
        });
    }

    public void setScroll(Context context) {// スクロール距離計算
        scrollw = (int) Manager.getDispW(getApplicationContext()) / 30;
    }

    void setAdapter(final AceMiss am, final int ID[], int start) {//SpinnerにAdapterセット
        final int ids[] = new int[6];
        System.arraycopy(ID, start, ids, 0, 6);//ID取得
        ArrayList<String> ace = new ArrayList<String>();
        ace.add("Ace");
        ace.add(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getInitials(AceMissObject.ACE_GOOD));
        ace.add(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getInitials(AceMissObject.ACE_NORMAL));
        ace.add(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getInitials(AceMissObject.ACE_LUCKY));
        jp.gr.java_conf.bunooboi.bmp.AdapterAce aa = new jp.gr.java_conf.bunooboi.bmp.AdapterAce(this);
        aa.setData(ace);
        am.getAceSpinner().setAdapter(aa);
        am.getAceSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent()) {// 上限確認
                    switch (position) {
                        case 0:
                            break;
                        case 1:
                            setSelect(ids[0]);
                            break;
                        case 2:
                            setSelect(ids[1]);
                            break;
                        case 3:
                            setSelect(ids[2]);
                            break;
                    }
                }
                am.getAceSpinner().setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayList<String> miss = new ArrayList<String>();
        miss.add("Miss");
        miss.add(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getInitials(jp.gr.java_conf.bunooboi.bmp.AceMissObject.MISS_BAD));
        miss.add(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getInitials(jp.gr.java_conf.bunooboi.bmp.AceMissObject.MISS_NORMAL));
        miss.add(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getInitials(AceMissObject.MISS_UNLUCKY));
        jp.gr.java_conf.bunooboi.bmp.AdapterMiss mi = new AdapterMiss(this);
        mi.setData(miss);
        am.getMissSpinner().setAdapter(mi);
        am.getMissSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent()) {
                    switch (position) {
                        case 0:
                            break;
                        case 1:
                            setSelect(ids[3]);
                            break;
                        case 2:
                            setSelect(ids[4]);
                            break;
                        case 3:
                            setSelect(ids[5]);
                            break;
                    }
                }
                am.getMissSpinner().setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    void setSelect(int id) {// Spinner選択した時の処理
        jp.gr.java_conf.bunooboi.bmp.Calculation.Event.get(Calculation.GameCount).set(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, id);// イベントにID書き込み
        jp.gr.java_conf.bunooboi.bmp.Calculation.PressTime.get(Calculation.GameCount).set(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, PressTime.getPressTimeString());// 押した時刻セット
        switch (id) {
            case 2:
                graph1[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace1[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 0);
                am1[0].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[0][Calculation.GameCount]);
                break;
            case 3:
                graph1[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace1[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 1);
                am1[0].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[0][Calculation.GameCount]);
                break;
            case 4:
                graph1[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace1[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 2);
                am1[0].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[0][Calculation.GameCount]);
                break;
            case 5:
                graph1[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss1[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 3);
                am1[0].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[0][Calculation.GameCount]);
                break;
            case 6:
                graph1[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss1[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 4);
                am1[0].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[0][Calculation.GameCount]);
                break;
            case 7:
                graph1[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss1[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 5);
                am1[0].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[0][Calculation.GameCount]);
                break;
            case 8:
                graph2[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace2[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 0);
                am2[0].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[0][Calculation.GameCount]);
                break;
            case 9:
                graph2[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace2[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 1);
                am2[0].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[0][Calculation.GameCount]);
                break;
            case 10:
                graph2[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace2[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 2);
                am2[0].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[0][Calculation.GameCount]);
                break;
            case 11:
                graph2[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss2[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 3);
                am2[0].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[0][Calculation.GameCount]);
                break;
            case 12:
                graph2[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss2[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 4);
                am2[0].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[0][Calculation.GameCount]);
                break;
            case 13:
                graph2[1].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss2[0][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 5);
                am2[0].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[0][Calculation.GameCount]);
                break;
            case 14:
                graph1[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace1[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 0);
                am1[1].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[1][Calculation.GameCount]);
                break;
            case 15:
                graph1[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace1[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 1);
                am1[1].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[1][Calculation.GameCount]);
                break;
            case 16:
                graph1[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace1[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 2);
                am1[1].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace1[1][Calculation.GameCount]);
                break;
            case 17:
                graph1[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss1[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 3);
                am1[1].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[1][Calculation.GameCount]);
                break;
            case 18:
                graph1[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss1[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 4);
                am1[1].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[1][Calculation.GameCount]);
                break;
            case 19:
                graph1[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss1[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 5);
                am1[1].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss1[1][Calculation.GameCount]);
                break;
            case 20:
                graph2[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace2[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 0);
                am2[1].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[1][Calculation.GameCount]);
                break;
            case 21:
                graph2[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace2[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 1);
                am2[1].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[1][Calculation.GameCount]);
                break;
            case 22:
                graph2[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Ace2[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 2);
                am2[1].setAce(jp.gr.java_conf.bunooboi.bmp.Calculation.Ace2[1][Calculation.GameCount]);
                break;
            case 23:
                graph2[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss2[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 3);
                am2[1].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[1][Calculation.GameCount]);
                break;
            case 24:
                graph2[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss2[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 4);
                am2[1].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[1][Calculation.GameCount]);
                break;
            case 25:
                graph2[2].setCheckScoreUnitAttribute(jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount, ++Calculation
                        .Miss2[1][jp.gr.java_conf.bunooboi.bmp.Calculation.GameCount], 5);
                am2[1].setMiss(jp.gr.java_conf.bunooboi.bmp.Calculation.Miss2[1][Calculation.GameCount]);
                break;
        }
        jp.gr.java_conf.bunooboi.bmp.Calculation.EventCount++;// イベントカウント加算
        Scroll();// スクロール
    }

    @Override
    public void onResume() {
        super.onResume();
        if (fin == false) {
            Calculation.ReadEvent(Calculation.Event.get(Calculation.GameCount), graph1, graph2);
            button[6].setText("Game1");
            button[7].setText("Game2");
            returns1 = false;
            returns2 = false;
            allViewEnabled(true);
            button[0].setEnabled(true);
            sb[0].setScore(Calculation.Score1[Calculation.GameCount]);
            sb[1].setScore(Calculation.Score2[Calculation.GameCount]);
            for (int i = 0; i < Calculation.MaxPeople / 2; i++) {
                am1[i].setAce(Calculation.Ace1[i][Calculation.GameCount]);
                am1[i].setMiss(Calculation.Miss1[i][Calculation.GameCount]);
                am2[i].setAce(Calculation.Ace2[i][Calculation.GameCount]);
                am2[i].setMiss(Calculation.Miss2[i][Calculation.GameCount]);
            }
            if (Calculation.Score1[Calculation.GameCount] >= Calculation.WinScore) {// チーム１勝利
                if (Calculation.Score1[Calculation.GameCount] - Calculation.WinDif >= Calculation.Score2[Calculation.GameCount]) {// 点差計算
                    allViewEnabled(false);// 全View使用禁止
                    button[1].setEnabled(true);// Next解禁
                }
            }
            if (Calculation.Score2[Calculation.GameCount] >= Calculation.WinScore) {// チーム１勝利
                if (Calculation.Score2[Calculation.GameCount] - Calculation.WinDif >= Calculation.Score1[Calculation.GameCount]) {// 点差計算
                    allViewEnabled(false);// 全View使用禁止
                    button[1].setEnabled(true);// Next解禁
                }
            }
            if (Calculation.MaxGame == 3) {
                if (Calculation.GameCount >= 1)
                    button[6].setEnabled(true);
                if (Calculation.GameCount >= 2)
                    button[7].setEnabled(true);
            }
            if (Calculation.MaxGame == 3) {
                switch (Calculation.GameCount) {
                    case 0:
                        for (int i = 0; i < Calculation.MaxGame - 1; i++) {// スコア表セットテキスト
                            sb[0].setPastScore(i, 0);
                            sb[1].setPastScore(i, 0);
                        }
                        break;
                    case 1:
                        for (int i = 0; i < Calculation.MaxGame - 2; i++) {// スコア表セットテキスト
                            sb[0].setPastScore(i, Calculation.Score1[i]);
                            sb[1].setPastScore(i, Calculation.Score2[i]);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < Calculation.MaxGame - 1; i++) {// スコア表セットテキスト
                            sb[0].setPastScore(i, Calculation.Score1[i]);
                            sb[1].setPastScore(i, Calculation.Score2[i]);
                        }
                        break;
                }
            }
        }
    }
}

package jp.gr.java_conf.bunooboi.bmp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.gams.AttributeGraph;
import jp.gr.java_conf.bunooboi.gams.Manager;

public class Config extends Activity {
    EditText GameName; // 試合名
    EditText TeamName[] = new EditText[2]; // チーム名
    EditText PlayerName[] = new EditText[4]; // 選手名

    Spinner Team1Color; // チーム１色
    Spinner Team2Color; // チーム２色

    ArrayList<String> teamcolor = new ArrayList<String>(); // Spinner色一覧

    RadioGroup singlesdoubles; // シングルス、ダブルスグループ
    RadioGroup score; // 得点上限グループ
    RadioGroup game; // ゲーム数グループ

    RadioButton singles; // シングルス
    RadioButton doubles; // ダブルス

    RadioButton eleven; // １１点
    RadioButton fifteen; // １５点
    RadioButton twentyone; // ２１点

    RadioButton one; // １ゲーム
    RadioButton three; // ３ゲーム

    boolean nametf = false; // 名前入力したかどうか

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.config);

        startSetting();

        AttributeGraph.addAttribute(0, R.drawable.ace_good);
        AttributeGraph.addAttribute(1, R.drawable.ace_normal);
        AttributeGraph.addAttribute(2, R.drawable.ace_lucky);
        AttributeGraph.addAttribute(3, R.drawable.miss_bad);
        AttributeGraph.addAttribute(4, R.drawable.miss_normal);
        AttributeGraph.addAttribute(5, R.drawable.miss_unlucky);

        GameName = (EditText) findViewById(R.id.GameName);
        GameName.setTextSize(8 * Manager.getScaleSize(getApplicationContext()));
        GameName.setNextFocusDownId(R.id.team1);
        GameName.setText("GameName");

        TeamName[0] = (EditText) findViewById(R.id.team1);
        TeamName[0].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        TeamName[0].setNextFocusDownId(R.id.team2);
        TeamName[0].setText("Team1");

        TeamName[1] = (EditText) findViewById(R.id.team2);
        TeamName[1].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        TeamName[1].setNextFocusDownId(R.id.player1);
        TeamName[1].setText("Team2");

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
            PlayerName[i].setText("Player" + String.valueOf(i + 1));
            PlayerName[i].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        }

        singlesdoubles = (RadioGroup) findViewById(R.id.singlesdoubles);
        singles = (RadioButton) findViewById(R.id.singles);
        doubles = (RadioButton) findViewById(R.id.doubles);
        singles.setText("Singles");
        doubles.setText("Doubles");
        singles.setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        doubles.setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        singlesdoubles.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (singles.isChecked()) {
                    Calculation.MaxPeople = 2;
                } else if (doubles.isChecked()) {
                    Calculation.MaxPeople = 4;
                }
            }
        });

        score = (RadioGroup) findViewById(R.id.score);
        eleven = (RadioButton) findViewById(R.id.eleven);
        fifteen = (RadioButton) findViewById(R.id.fifteen);
        twentyone = (RadioButton) findViewById(R.id.twentyone);
        eleven.setText("11Point");
        fifteen.setText("15Point");
        twentyone.setText("21Point");
        eleven.setTextSize(3.5f * Manager.getScaleSize(getApplicationContext()));
        fifteen.setTextSize(3.5f * Manager.getScaleSize(getApplicationContext()));
        twentyone.setTextSize(3.5f * Manager.getScaleSize(getApplicationContext()));
        score.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (eleven.isChecked()) {
                    Calculation.WinScore = 11;
                    Calculation.MaxEvent = 120;
                } else if (fifteen.isChecked()) {
                    Calculation.WinScore = 15;
                    Calculation.MaxEvent = 150;
                } else if (twentyone.isChecked()) {
                    Calculation.WinScore = 21;
                    Calculation.MaxEvent = 180;
                }
            }
        });

        game = (RadioGroup) findViewById(R.id.game);
        one = (RadioButton) findViewById(R.id.one);
        three = (RadioButton) findViewById(R.id.three);
        one.setText("1Game");
        three.setText("3Game");
        one.setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        three.setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        game.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (one.isChecked()) {
                    Calculation.MaxGame = 1;
                } else if (three.isChecked()) {
                    Calculation.MaxGame = 3;
                }
            }
        });

        teamcolor.add("Black");
        teamcolor.add("White");
        teamcolor.add("Blue");
        teamcolor.add("Green");
        teamcolor.add("Orange");
        teamcolor.add("Pink");
        teamcolor.add("Purple");

        AdapterColor adapter = new AdapterColor(this);
        adapter.setData(teamcolor);

        Team1Color = (Spinner) findViewById(R.id.team1color);
        Team1Color.setAdapter(adapter);
        Team1Color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectColor(pos, 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Team2Color = (Spinner) findViewById(R.id.team2color);
        Team2Color.setAdapter(adapter);
        Team2Color.setSelection(1);
        Team2Color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectColor(pos, 2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setText("Start");
        button1.setTextSize(6 * Manager.getScaleSize(getApplicationContext()));
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(GameName.getText().toString().equals("")) && !(TeamName[0].getText().toString().equals("")) && !(TeamName[1].getText().toString().equals(""))) {
                    if (Calculation.MaxPeople == 4) {
                        if (!(PlayerName[0].getText().toString().equals("")) && !(PlayerName[1].getText().toString().equals("")) && !(PlayerName[2].getText().toString().equals(""))
                                && !(PlayerName[3].getText().toString().equals(""))) {
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
                    Calculation.GameName = GameName.getText().toString();
                    if (Calculation.Exists()) {
                        Toast.makeText(getApplicationContext(), "同じデータが存在します", Toast.LENGTH_LONG).show();
                    } else {
                        Calculation.init();
                        Calculation.TeamName[0] = TeamName[0].getText().toString();
                        Calculation.TeamName[1] = TeamName[1].getText().toString();
                        if (Calculation.MaxPeople == 4) {
                            Calculation.PlayerName1[0] = PlayerName[0].getText().toString();
                            Calculation.PlayerName1[1] = PlayerName[1].getText().toString();
                            Calculation.PlayerName2[0] = PlayerName[2].getText().toString();
                            Calculation.PlayerName2[1] = PlayerName[3].getText().toString();
                        } else {
                            Calculation.PlayerName1[0] = PlayerName[0].getText().toString();
                            Calculation.PlayerName2[0] = PlayerName[2].getText().toString();
                        }
                        Intent i = new Intent(getApplicationContext(), Main.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "名前を入力してください", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setText("Stop");
        button2.setTextSize(6 * Manager.getScaleSize(getApplicationContext()));
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setText("Data");
        button3.setTextSize(6 * Manager.getScaleSize(getApplicationContext()));
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), History.class);
                startActivity(i);
            }
        });
    }

    void startSetting() {// 初期設定
        Calculation.TeamColor[0] = TeamObject.BLACK;
        Calculation.TeamColor[1] = TeamObject.WHITE;
        Calculation.MaxPeople = 2;
        Calculation.WinScore = 21;
        Calculation.MaxEvent = 180;
        Calculation.MaxGame = 3;
    }

    @Override
    public void onResume() {
        super.onResume();
        startSetting();
    }

    void selectColor(int pos, int id) {// 色選択
        switch (pos) {
            case 0:
                if (id == 1)
                    Calculation.TeamColor[0] = TeamObject.BLACK;
                else
                    Calculation.TeamColor[1] = TeamObject.BLACK;
                break;
            case 1:
                if (id == 1)
                    Calculation.TeamColor[0] = TeamObject.WHITE;
                else
                    Calculation.TeamColor[1] = TeamObject.WHITE;
                break;
            case 2:
                if (id == 1)
                    Calculation.TeamColor[0] = TeamObject.BLUE;
                else
                    Calculation.TeamColor[1] = TeamObject.BLUE;
                break;
            case 3:
                if (id == 1)
                    Calculation.TeamColor[0] = TeamObject.GREEN;
                else
                    Calculation.TeamColor[1] = TeamObject.GREEN;
                break;
            case 4:
                if (id == 1)
                    Calculation.TeamColor[0] = TeamObject.ORANGE;
                else
                    Calculation.TeamColor[1] = TeamObject.ORANGE;
                break;
            case 5:
                if (id == 1)
                    Calculation.TeamColor[0] = TeamObject.PINK;
                else
                    Calculation.TeamColor[1] = TeamObject.PINK;
                break;
            case 6:
                if (id == 1)
                    Calculation.TeamColor[0] = TeamObject.PURPLE;
                else
                    Calculation.TeamColor[1] = TeamObject.PURPLE;
                break;
        }
    }
}

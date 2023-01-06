package jp.gr.java_conf.bunooboi.bmp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import jp.gr.java_conf.bunooboi.gams.Graph;
import jp.gr.java_conf.bunooboi.gams.Manager;

public class Larry extends Activity {
    LinearLayout GraphText;
    LinearLayout GraphLayout;
    LinearLayout MeaningLayout;
    LinearLayout GameButtonL;

    Graph graph1[];
    Graph graph2[];

    Meaning meaning;
    MarginLayoutParams mlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.larry);

        GraphText = (LinearLayout) findViewById(R.id.GraphText);
        GraphLayout = (LinearLayout) findViewById(R.id.GraphLayout);
        MeaningLayout = (LinearLayout) findViewById(R.id.MeaningLayout);
        GameButtonL = (LinearLayout) findViewById(R.id.GameButtonL);

        meaning = new Meaning(this);
        meaning.addView(MeaningLayout);

        if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople == 4) {
            graph1 = new Graph[3];
            graph2 = new Graph[3];
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
            graph1[2].setGraphColor(TeamObject.getGraphColor(Calculation.TeamColor[0]));
            graph2[2].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));
        } else {
            graph1 = new Graph[2];
            graph2 = new Graph[2];
            for (int i = 0; i < graph1.length; i++) {
                graph1[i] = new Graph(this, GraphText, GraphLayout);
                graph1[i].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
                graph1[i].createGraph();
                graph2[i] = new Graph(this, GraphText, GraphLayout);
                graph2[i].setLength(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent);
                graph2[i].createGraph();
            }
        }
        graph1[0].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[0]);
        graph2[0].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamName[1]);
        graph1[1].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName1[0]);
        graph2[1].setName(jp.gr.java_conf.bunooboi.bmp.Calculation.PlayerName2[0]);
        graph1[0].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0]));
        graph2[0].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));
        graph1[1].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0]));
        graph2[1].setGraphColor(TeamObject.getGraphColor(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1]));

        Button GameButton[] = new Button[3];
        Button UnderButton[] = new Button[3];

        for (int i = 0; i < GameButton.length; i++) {
            GameButton[i] = new Button(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            mlp = (MarginLayoutParams) lp;
            mlp.setMargins(Manager.getMargin(getApplicationContext()) + 7, Manager.getMargin(getApplicationContext()) + 7,
                    Manager.getMargin(getApplicationContext()) + 7, Manager.getMargin(getApplicationContext()) + 7);
            GameButton[i].setLayoutParams(mlp);
            GameButton[i].setBackgroundResource(R.drawable.button_custom);
            GameButton[i].setTextSize(6 * Manager.getScaleSize(getApplicationContext()));
            GameButton[i].setText("Game" + (i + 1));
            GameButtonL.addView(GameButton[i]);
        }
        GameButton[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jp.gr.java_conf.bunooboi.bmp.Calculation.ReadEvent(Calculation.Event.get(0), graph1, graph2);
            }
        });
        GameButton[1].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jp.gr.java_conf.bunooboi.bmp.Calculation.ReadEvent(Calculation.Event.get(1), graph1, graph2);
            }
        });
        GameButton[2].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jp.gr.java_conf.bunooboi.bmp.Calculation.ReadEvent(Calculation.Event.get(2), graph1, graph2);
            }
        });

        if (jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame == 1) {
            GameButton[1].setEnabled(false);
            GameButton[2].setEnabled(false);
        }

        UnderButton[0] = (Button) findViewById(R.id.button1);
        UnderButton[1] = (Button) findViewById(R.id.button2);
        UnderButton[2] = (Button) findViewById(R.id.button3);

        UnderButton[0].setText("Overview");
        UnderButton[1].setText("Score");
        UnderButton[2].setText("Gallery");

        for (int i = 0; i < UnderButton.length; i++) {
            UnderButton[i].setTextSize(6 * Manager.getScaleSize(getApplicationContext()));
            UnderButton[i].setLayoutParams(mlp);
        }

        UnderButton[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Overview.class);
                startActivity(i);
                finish();
            }
        });
        UnderButton[1].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ScoreTable.class);
                startActivity(i);
                finish();
            }
        });
        UnderButton[2].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Gallery.class);
                startActivity(i);
                finish();
            }
        });
    }
}

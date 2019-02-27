package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jp.gr.java_conf.bunooboi.mydic.Game.GameFall;
import jp.gr.java_conf.bunooboi.mydic.Game.GameMemory;
import jp.gr.java_conf.bunooboi.mydic.Game.GameSelect;
import jp.gr.java_conf.bunooboi.mydic.GameValue;
import jp.gr.java_conf.bunooboi.mydic.R;

/**
 * Created by hiro on 2016/08/19.
 */
public class GameFin extends Activity {
    public static String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("結果");
        setContentView(R.layout.gamefin);

        TextView textview1 = (TextView) findViewById(R.id.textview1);
        textview1.setTextSize(50 * Main.getScaleSize(getApplicationContext()));
        switch (GameValue.id) {
            case 0:
                textview1.setText(GameSelect.question + "問");
                break;
            case 1:
                textview1.setText(String.valueOf(GameFall.t2 - GameFall.t1) + "秒");
                break;
            case 2:
                textview1.setText("問題数" + (GameMemory.questionNumber - 1));
        }
        TextView textview2 = (TextView) findViewById(R.id.textview2);
        textview2.setText("答え：" + answer);
        if (GameValue.id == 2 && GameMemory.questionNumber == 10)
            textview2.setText("");
        textview2.setTextSize(30 * Main.getScaleSize(getApplicationContext()));

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        button1.setText("リトライ");
        button2.setText("メイン");
        button1.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button2.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (GameValue.id) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), GameSelect.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), GameFall.class));
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), GameMemory.class));
                        break;
                }
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GameStart.class));
                finish();
            }
        });
    }
}

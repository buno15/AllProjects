package jp.gr.java_conf.bunooboi.mydic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jp.gr.java_conf.bunooboi.mydic.Game.GameFall;
import jp.gr.java_conf.bunooboi.mydic.Game.GameSelect;

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
        }
        TextView textview2 = (TextView) findViewById(R.id.textview2);
        textview2.setText("答え："+answer);
        textview2.setTextSize(40 * Main.getScaleSize(getApplicationContext()));

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        button1.setText("リトライ");
        button2.setText("メイン");
        button1.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button2.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GameSelectDic.class));
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

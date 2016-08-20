package jp.gr.java_conf.bunooboi.mydic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hiro on 2016/08/19.
 */
public class GameFin extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("結果");
        setContentView(R.layout.gamefin);

        TextView textview = (TextView) findViewById(R.id.textview);
        textview.setText(String.valueOf(GameFall.t2 - GameFall.t1) + "秒");
        textview.setTextSize(40 * Main.getScaleSize(getApplicationContext()));

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

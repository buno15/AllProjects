package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jp.gr.java_conf.bunooboi.mydic.Game;
import jp.gr.java_conf.bunooboi.mydic.R;

/**
 * Created by hiro on 2016/08/19.
 */
public class GameFin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("結果");
        setContentView(R.layout.gamefin);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);

        StringBuffer sb = new StringBuffer();
        sb.append(Game.answer.getWord());
        for (int i = 0; i < 3; i++) {
            sb.append("\n" + Game.answer.getDescription()[i]);
        }
        textView1.setText(sb.toString());

        sb = new StringBuffer();
        for (int i = 0; i < Game.answer.getTag().size(); i++) {
            sb.append(":" + Game.answer.getTag().get(i) + "\n");
        }
        textView2.setText(sb.toString());

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        button1.setText("Retry");
        button2.setText("Menu");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (Game.id) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), GameSelect.class));
                        break;
                    case 1:
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

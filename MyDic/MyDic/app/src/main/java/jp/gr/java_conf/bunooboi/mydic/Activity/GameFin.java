package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
        setContentView(R.layout.gamefin);

        TextView word = findViewById(R.id.word);
        TextView textView1 = findViewById(R.id.textView1);
        TextView description = findViewById(R.id.description);
        TextView textView2 = findViewById(R.id.textView2);
        TextView tag = findViewById(R.id.tag);
        TextView textView3 = findViewById(R.id.textView3);

        word.setTextSize(26);
        word.setText("Word");
        tag.setTextSize(30);
        tag.setText("Tags");
        description.setTextSize(30);
        description.setText("Descriptions");
        textView1.setTextSize(18);
        textView2.setTextSize(16);
        textView3.setTextSize(16);

        textView1.setText(Game.answer.getWord());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            if (!Game.answer.getDescription()[i].equals(""))
                sb.append(":" + Game.answer.getDescription()[i] + "\n");
        }
        textView2.setText(sb.toString());

        sb = new StringBuffer();
        for (int i = 0; i < Game.answer.getTag().size(); i++) {
            sb.append(":" + Game.answer.getTag().get(i) + "\n");
        }
        textView3.setText(sb.toString());

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
                        startActivity(new Intent(getApplicationContext(), GameFall.class));
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), GameStart.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import jp.gr.java_conf.bunooboi.mydic.GameValue;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sound;

/**
 * Created by hiro on 2016/08/18.
 */
public class GameStart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ゲーム一覧");
        setContentView(R.layout.gamestart);
        Sound.init(getApplicationContext());

        ImageButton imagebutton[] = new ImageButton[3];
        imagebutton[0] = (ImageButton) findViewById(R.id.imagebutton1);
        imagebutton[1] = (ImageButton) findViewById(R.id.imagebutton2);
        imagebutton[2] = (ImageButton) findViewById(R.id.imagebutton3);
        imagebutton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameValue.id = 0;
                startActivity(new Intent(getApplicationContext(), GameSelectDic.class));
                finish();
            }
        });
        imagebutton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameValue.id = 1;
                startActivity(new Intent(getApplicationContext(), GameSelectDic.class));
                finish();
            }
        });
        imagebutton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameValue.id = 2;
                startActivity(new Intent(getApplicationContext(), GameSelectDic.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                startActivity(new Intent(getApplicationContext(), Main.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Main.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

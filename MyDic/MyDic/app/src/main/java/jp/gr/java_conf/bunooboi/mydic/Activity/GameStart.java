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

import jp.gr.java_conf.bunooboi.mydic.Game;
import jp.gr.java_conf.bunooboi.mydic.R;

public class GameStart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Game");
        setContentView(R.layout.gamestart);

        ImageButton imagebutton[] = new ImageButton[3];
        imagebutton[0] = findViewById(R.id.imageButton1);
        imagebutton[1] = findViewById(R.id.imageButton2);
        imagebutton[2] = findViewById(R.id.imageButton3);
        imagebutton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.id = 0;
                finish();
            }
        });
        imagebutton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.id = 1;
                finish();
            }
        });
        imagebutton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.id = 2;
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
            case R.id.back:
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

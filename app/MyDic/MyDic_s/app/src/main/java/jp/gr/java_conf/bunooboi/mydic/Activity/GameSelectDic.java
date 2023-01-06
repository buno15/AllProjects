package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.mydic.Game.GameFall;
import jp.gr.java_conf.bunooboi.mydic.Game.GameMemory;
import jp.gr.java_conf.bunooboi.mydic.Game.GameSelect;
import jp.gr.java_conf.bunooboi.mydic.GameValue;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sentences;

/**
 * Created by hiro on 2016/08/19.
 */
public class GameSelectDic extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameselectdic);

        GameValue.participationConfig = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_multiple_choice, Sentences.getConfigList());

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listlink, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_all:
                SparseBooleanArray checked = listview.getCheckedItemPositions();

                for (int i = 0; i < checked.size(); i++) {
                    if (checked.valueAt(i)) {
                        GameValue.participationConfig.add(checked.keyAt(i));
                    }
                }
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
                break;
            case R.id.action_back:
                startActivity(new Intent(getApplicationContext(), GameStart.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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

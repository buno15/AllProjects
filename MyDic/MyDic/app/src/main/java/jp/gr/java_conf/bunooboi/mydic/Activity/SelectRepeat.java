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

import jp.gr.java_conf.bunooboi.mydic.Input;
import jp.gr.java_conf.bunooboi.mydic.MainService;
import jp.gr.java_conf.bunooboi.mydic.Output;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sentence;
import jp.gr.java_conf.bunooboi.mydic.Sentences;

/**
 * Created by hiro on 2016/09/02.
 */
public class SelectRepeat extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("定期読み上げ選択");
        setContentView(R.layout.selectrepeat);

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
                ArrayList<Sentence> sentences = new ArrayList<>();
                for (int i = 0; i < checked.size(); i++)
                    sentences.addAll(Sentences.sentences.get(checked.keyAt(i)));
                Output.getOutput().repeatWrite(sentences);
                MainService.putRepeat();
                MainService.sentences = Input.getInput().repeatRead();
                startActivity(new Intent(getApplicationContext(), Setting.class));
                finish();
                break;
            case R.id.action_back:
                startActivity(new Intent(getApplicationContext(), Setting.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Setting.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

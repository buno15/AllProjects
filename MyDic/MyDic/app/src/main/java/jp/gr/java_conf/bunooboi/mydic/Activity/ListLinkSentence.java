package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import jp.gr.java_conf.bunooboi.mydic.Input;
import jp.gr.java_conf.bunooboi.mydic.MainService;
import jp.gr.java_conf.bunooboi.mydic.Output;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Sentences;
import jp.gr.java_conf.bunooboi.mydic.Values;

/**
 * Created by hiro on 2016/08/07.
 */
public class ListLinkSentence extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("データ一覧");
        setContentView(R.layout.listlink);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.addAll(Sentences.getSentencesList(Sentences.ConfigIndex));
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Main.tts.stop();
                Sentences.link = Sentences.sentences.get(Sentences.ConfigIndex).get(i).getLink();
                Sentences.text = Sentences.sentences.get(Sentences.ConfigIndex).get(i).getText();
                startActivity(new Intent(getApplicationContext(), Main.class));
                finish();
            }
        });
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
                Main.tts.stop();
                stopService(new Intent(getApplicationContext(), MainService.class));
                Values.setIndexCount(0);
                MainService.putAllSpeak();
                Output.getOutput().allSpeakWrite(Sentences.sentences.get(Sentences.ConfigIndex));
                MainService.sentences = Input.getInput().allSpeakRead();
                startService(new Intent(getApplicationContext(), MainService.class));
                startActivity(new Intent(getApplicationContext(), Main.class));
                finish();
                break;
            case R.id.action_back:
                startActivity(new Intent(getApplicationContext(), ListLinkConfig.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

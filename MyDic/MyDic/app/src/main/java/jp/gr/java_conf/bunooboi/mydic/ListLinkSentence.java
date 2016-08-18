package jp.gr.java_conf.bunooboi.mydic;

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
                Main.all = false;
                Main.allCount = 0;
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
                Sentences.link = "/none";
                Sentences.text = "";
                Main.all = true;
                Main.allCount = 0;
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

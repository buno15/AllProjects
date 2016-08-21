package jp.gr.java_conf.bunooboi.mydic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by hiro on 2016/08/04.
 */
public class ListEditSentence extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("データ一覧");
        setContentView(R.layout.listeditsentence);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.addAll(Sentences.getSentencesList(Sentences.ConfigIndex));

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Edit.title = Sentences.sentences.get(Sentences.ConfigIndex).get(i).getTitle();
                Edit.level = Sentences.sentences.get(Sentences.ConfigIndex).get(i).getLevel();
                Edit.key = Sentences.sentences.get(Sentences.ConfigIndex).get(i).getKey();
                Edit.link = Sentences.sentences.get(Sentences.ConfigIndex).get(i).getLink();
                Edit.description = Sentences.sentences.get(Sentences.ConfigIndex).get(i).getDescription();
                Edit.text = Sentences.sentences.get(Sentences.ConfigIndex).get(i).getText();
                Edit.index = i;
                startActivity(new Intent(getApplicationContext(), Edit.class));
                finish();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                ListView listview = (ListView) adapterView;
                String item = (String) listview.getItemAtPosition(i);
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(ListEditSentence.this);
                alertDlg.setMessage(item + "を削除しますか？");
                alertDlg.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Sentences.sentences.get(Sentences.ConfigIndex).remove(i);
                        Output.getOutput().write(false, Sentences.ConfigIndex);
                        reload();
                    }
                });
                alertDlg.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
                return true;
            }
        });
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setText("新規データ作成");
        button1.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edit.title = "";
                Edit.level = 1;
                Edit.key = new String[0];
                Edit.link = "";
                Edit.description = "";
                Edit.text = "";
                Edit.index = -1;
                startActivity(new Intent(getApplicationContext(), Edit.class));
                finish();
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setText("並び替え");
        button2.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Sort.class));
                finish();
            }
        });
        listheight();
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
                startActivity(new Intent(getApplicationContext(), ListEditConfig.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void listheight() {// リスト高さ調節
        ListAdapter la = listview.getAdapter();
        if (la == null) {
            return;
        }

        int i;
        int h = 0; // ListView トータルの高さ

        for (i = 0; i < la.getCount(); i++) {
            View item = la.getView(i, null, listview);
            item.measure(0, 0);
            h += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams p = listview.getLayoutParams();
        p.height = h + (listview.getDividerHeight() * (la.getCount() - 1));
        listview.setLayoutParams(p);
    }

    private void reload() {// 画面リロード
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), ListEditConfig.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

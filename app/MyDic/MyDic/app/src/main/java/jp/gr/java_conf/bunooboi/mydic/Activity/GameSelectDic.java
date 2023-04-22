package jp.gr.java_conf.bunooboi.mydic.Activity;

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
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.mydic.Game;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Values;

/**
 * Created by hiro on 2016/08/19.
 */
public class GameSelectDic extends AppCompatActivity {
    ListView listView;
    ImageButton dictionary;
    ImageButton tag;
    ArrayAdapter adapter;
    ArrayList<String> clicked = new ArrayList<>();
    int listType = 0;//0=dictionary,1=tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameselectdic);


        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_multiple_choice);
        listView = findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        dictionary = findViewById(R.id.list);
        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDictionaryList();
            }
        });

        tag = findViewById(R.id.tag);
        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTagList();
            }
        });
        setDictionaryList();
        listHeight();
    }

    public void setDictionaryList() {//辞書リストの構築
        listType = 0;
        clicked.clear();
        Game.words.clear();
        setEnable(dictionary);
        clearEnable(tag);
        adapter.clear();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Values.dictionaries.size(); i++) {
            list.add(Values.dictionaries.get(i));

        }
        adapter.addAll(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listview = (ListView) parent;
                String item = (String) listview.getItemAtPosition(position);
                for (int i = 0; i < Values.words.size(); i++) {
                    if (Values.words.get(i).searchDic(item)) {
                        if (Game.words.indexOf(Values.words.get(i)) == -1) {
                            Game.words.add(Values.words.get(i));
                        } else if (clicked.indexOf(item) >= 1) {
                            Game.words.remove(Game.words.indexOf(Values.words.get(i)));
                        }
                    }
                }
                if (clicked.indexOf(item) == -1)
                    clicked.add(item);
                else
                    clicked.remove(item);
            }
        });
        listHeight();
    }

    public void setTagList() {//タグリストの構築
        listType = 1;
        clicked.clear();
        Game.words.clear();
        setEnable(tag);
        clearEnable(dictionary);
        adapter.clear();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Values.tags.size(); i++) {
            list.add(Values.tags.get(i));
        }
        adapter.addAll(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listview = (ListView) parent;
                String item = (String) listview.getItemAtPosition(position);
                for (int i = 0; i < Values.words.size(); i++) {
                    if (Values.words.get(i).searchTag(item)) {
                        if (Game.words.indexOf(Values.words.get(i)) == -1) {
                            Game.words.add(Values.words.get(i));
                        } else if (clicked.indexOf(item) >= 1) {
                            Game.words.remove(Game.words.indexOf(Values.words.get(i)));
                        }
                    }
                }
                if (clicked.indexOf(item) == -1)
                    clicked.add(item);
                else
                    clicked.remove(item);
                System.out.println(clicked);
                System.out.println(Game.words.size());
            }
        });
        listHeight();
    }

    public void setEnable(ImageButton button) {//ボタン使用不可
        button.setEnabled(false);
        button.setAlpha(.5f);
    }

    public void clearEnable(ImageButton button) {//ボタン使用可能
        button.setEnabled(true);
        button.setAlpha(1f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.play, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.play:
                if (Game.id == 0) {
                    if (Game.words.size() < 6) {
                        Toast.makeText(GameSelectDic.this, "The number of data is not enough.", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(getApplicationContext(), GameSelect.class));
                        finish();
                    }
                } else if (Game.id == 1) {
                    if (Game.words.size() < 6) {
                        Toast.makeText(GameSelectDic.this, "The number of data is not enough.", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(getApplicationContext(), GameFall.class));
                        finish();
                    }
                }
                break;
            case R.id.back:
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

    public void listHeight() {// リスト高さ調節
        ListAdapter la = listView.getAdapter();
        if (la == null) {
            return;
        }

        int i;
        int h = 0; // ListView トータルの高さ

        for (i = 0; i < la.getCount(); i++) {
            View item = la.getView(i, null, listView);
            item.measure(0, 0);
            h += item.getMeasuredHeight() + 56;
        }

        ViewGroup.LayoutParams p = listView.getLayoutParams();
        p.height = h + (listView.getDividerHeight() * (la.getCount() - 1));
        listView.setLayoutParams(p);
    }
}
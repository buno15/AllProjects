package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.mydic.Output;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Values;
import jp.gr.java_conf.bunooboi.mydic.Word;

public class Edit extends AppCompatActivity {
    ListView listView;
    Spinner spinner;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    static ArrayList<String> tag = new ArrayList<>();
    static String dictionary = "";
    static String word = "";
    static String description[] = new String[3];
    static boolean fix = false;
    static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (fix)
            setTitle("Fix Data");
        else
            setTitle("Create Data");
        setContentView(R.layout.edit);

        spinner = findViewById(R.id.dictionary);
        ArrayAdapter<String> dicAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        dicAdapter.addAll(Values.dictionaries);
        spinner.setAdapter(dicAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                dictionary = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editText1 = findViewById(R.id.word);
        editText2 = findViewById(R.id.description1);
        editText3 = findViewById(R.id.description2);
        editText4 = findViewById(R.id.description3);

        editText1.setText(word);
        editText2.setText(description[0]);
        editText3.setText(description[1]);
        editText4.setText(description[2]);

        Button button1 = findViewById(R.id.button1);
        button1.setText("add tag");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = editText1.getText().toString();
                description[0] = editText2.getText().toString();
                description[1] = editText3.getText().toString();
                description[2] = editText4.getText().toString();
                startActivity(new Intent(getApplicationContext(), Tag.class));
                finish();
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setText("OK");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description[0] = editText2.getText().toString();
                description[1] = editText3.getText().toString();
                description[2] = editText4.getText().toString();
                if (fix)
                    Values.words.set(index, new Word(dictionary, editText1.getText().toString(), description, tag));
                else
                    Values.words.add(new Word(dictionary, editText1.getText().toString(), description, tag));
                Output.getOutput().writeWord();
                clear();
                startActivity(new Intent(getApplicationContext(), Main.class));
                finish();
            }
        });

        listView = findViewById(R.id.listView);
        if (fix) {
            MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1);
            adapter.addAll(tag);
            listView.setAdapter(adapter);
        } else {
            MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1);
            adapter.addAll(tag);
            listView.setAdapter(adapter);
        }
        listHeight();
    }

    void clear() {
        dictionary = "";
        word = "";
        description[0] = "";
        description[1] = "";
        description[2] = "";
        tag.clear();
        index = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//アクションバー作成
        if (fix) {
            getMenuInflater().inflate(R.menu.fix, menu);
        } else {
            getMenuInflater().inflate(R.menu.base, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//アクションバーのイベント
        switch (item.getItemId()) {
            case R.id.back:
                clear();
                startActivity(new Intent(getApplicationContext(), Main.class));
                finish();
                break;
            case R.id.delete:
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
                alertDlg.setMessage(Values.words.get(index).getWord() + " Delete?");
                alertDlg.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Values.words.remove(index);
                        Output.getOutput().writeWord();
                        clear();
                        startActivity(new Intent(getApplicationContext(), Main.class));
                        finish();
                    }
                });
                alertDlg.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            clear();
            startActivity(new Intent(getApplicationContext(), Main.class));
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
            h += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams p = listView.getLayoutParams();
        p.height = h + (listView.getDividerHeight() * (la.getCount() - 1));
        listView.setLayoutParams(p);
    }
}

class MyAdapter extends ArrayAdapter<String> {//クリックさせないための継承

    public MyAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}

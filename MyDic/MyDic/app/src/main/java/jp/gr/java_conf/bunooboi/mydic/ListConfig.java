package jp.gr.java_conf.bunooboi.mydic;

import android.app.AlertDialog;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by hiro on 2016/08/18.
 */
public class ListConfig extends AppCompatActivity {
    ListView listview;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("辞書一覧");
        setContentView(R.layout.listconfig);

        edittext = (EditText) findViewById(R.id.edittext);
        edittext.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    inputMethodManager.hideSoftInputFromWindow(edittext.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    GO();
                    return true;
                }
                return false;
            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setText("作成");
        button.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GO();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.addAll(Sentences.getConfigList());

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sentences.ConfigIndex = i;
                startActivity(new Intent(getApplicationContext(), ListEdit.class));
                finish();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                ListView listview = (ListView) adapterView;
                String item = (String) listview.getItemAtPosition(i);
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(ListConfig.this);
                alertDlg.setMessage(item + "を削除しますか？");
                alertDlg.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new File(Values.ConfigPath + Sentences.config.get(i)).delete();
                        Sentences.config.remove(i);
                        Sentences.sentences.remove(i);
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
        listheight();

    }

    void GO() {
        if (edittext.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "名前が入力されていません", Toast.LENGTH_SHORT).show();
        } else if (Sentences.serchDic(edittext.getText().toString())) {
            Toast.makeText(getApplicationContext(), "既に存在します", Toast.LENGTH_SHORT).show();
        } else {
            Output.getOutput().createDic(edittext.getText().toString());
            reload();
        }
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
                startActivity(new Intent(getApplicationContext(), Setting.class));
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
            startActivity(new Intent(getApplicationContext(), Setting.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


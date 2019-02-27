package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.mydic.DisplayManager;
import jp.gr.java_conf.bunooboi.mydic.Output;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Values;

public class Tag extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tags");
        setContentView(R.layout.tag);

        Button button = findViewById(R.id.button);
        button.setText("New");
        button.setTextSize(20 * DisplayManager.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(Tag.this);
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(Tag.this);
                alertDlg.setMessage("New");
                alertDlg.setView(editText);
                alertDlg.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Values.tags.add(editText.getText().toString());
                        Output.getOutput().writeTags(false);
                        reload();
                    }
                });
                alertDlg.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice);
        adapter.addAll(Values.tags);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listview = (ListView) adapterView;
                String item = (String) listview.getItemAtPosition(i);
                if (Edit.tag.size() == 0) {
                    Edit.tag.add(item);
                } else {
                    int j = Edit.tag.indexOf(item);
                    if (j == -1)
                        Edit.tag.add(item);
                    else
                        Edit.tag.remove(j);
                }
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ListView listview = (ListView) parent;
                String item = (String) listview.getItemAtPosition(position);
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(Tag.this);
                alertDlg.setMessage(item+" Delete?");
                alertDlg.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Values.tags.remove(position);
                        Output.getOutput().writeTags(false);
                        reload();
                    }
                });
                alertDlg.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
                return true;
            }
        });


        for (int i = 0; i < Edit.tag.size(); i++) {
            for (int j = 0; j < Values.tags.size(); j++) {
                if (Edit.tag.get(i).equals(Values.tags.get(j))) {
                    listView.setItemChecked(j, true);
                }
            }
        }
        listHeight();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                startActivity(new Intent(getApplicationContext(), Edit.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Edit.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void reload() {// 画面リロード
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
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

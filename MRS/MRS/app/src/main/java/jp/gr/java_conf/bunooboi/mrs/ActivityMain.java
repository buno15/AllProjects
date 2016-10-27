package jp.gr.java_conf.bunooboi.mrs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityMain extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("復習データ");

        Memorys.init();

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.addAll(Memorys.getNotMemorysTitle());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int j = i;
                ListView listview = (ListView) adapterView;
                String item = (String) listview.getItemAtPosition(i);
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(ActivityMain.this);
                alertDlg.setMessage("\"" + item + "\"を復習しましたか？");
                alertDlg.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Memorys.setNotMemory(j);
                        reload();
                    }
                });
                alertDlg.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "データ追加");
        menu.add(Menu.NONE, 1, Menu.NONE, "データ一覧");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startActivity(new Intent(getApplicationContext(), ActivityEdit.class));
                finish();
                break;
            case 1:
                startActivity(new Intent(getApplicationContext(), ActivityList.class));
                finish();
                break;
        }
        return false;
    }
}

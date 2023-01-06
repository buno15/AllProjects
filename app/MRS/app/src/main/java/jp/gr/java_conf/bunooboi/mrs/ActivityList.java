package jp.gr.java_conf.bunooboi.mrs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bunooboi on 2016/10/27.
 */

public class ActivityList extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("データ一覧");

        ArrayList<String> titles = Memorys.getMemorysTitle();
        ArrayList<String> dates = Memorys.getMemorysDate();

        List<Map<String, String>> contactList = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Map<String, String> conMap = new HashMap<>();
            conMap.put("Title", titles.get(i));
            conMap.put("Date", dates.get(i));
            contactList.add(conMap);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, contactList, android.R.layout.simple_list_item_2, new String[]{"Title", "Date"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ActivityDetail.memory = Memorys.memorys.get(i);
                startActivity(new Intent(getApplicationContext(), ActivityDetail.class));
                finish();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final int j = i;
                Map<String, String> conMap = (Map<String, String>) adapterView.getItemAtPosition(i);
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(ActivityList.this);
                alertDlg.setMessage("\"" + conMap.get("Title") + "\"を削除しますか？");
                alertDlg.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Memorys.memorys.remove(j);
                        Output.getInstance().write();
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
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), ActivityMain.class));
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
}

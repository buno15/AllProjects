package jp.gr.java_conf.bunooboi.bmp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.bunooboi.gams.Manager;


public class History extends Activity {
    ListView listview;
    AlertDialog.Builder alertDlg;

    ArrayList<File> files = new ArrayList<File>();
    int selectID = 0;

    boolean read = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.history);

        alertDlg = new AlertDialog.Builder(this);

        final List<String> list = Manager.LibraryList;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        String filePath = String.valueOf(Environment.getExternalStorageDirectory() + "/BadmintonManager");
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f[] = new File(filePath).listFiles();

        for (int id = 0; id < f.length; id++) {// ディレクトリリスト取得
            if (f[id].isDirectory() && f[id].getName().endsWith("")) {
                // adapter.add(file[id].getName().replaceAll(".csv", ""));
                adapter.add(f[id].getName());
                files.add(f[id]);
            }
        }
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {// 選択
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                ListView listview = (ListView) parent;
                String item = (String) listview.getItemAtPosition(pos);
                if (item.equals(files.get(pos).getName())) {
                    selectID = pos;
                    Read();
                    if (read) {
                        Intent i = new Intent(getApplicationContext(), Overview.class);
                        startActivity(i);
                    }
                }
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {// 長押し
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                ListView listview = (ListView) parent;
                final String item = (String) listview.getItemAtPosition(pos);
                if (item.equals(files.get(pos).getName())) {
                    selectID = pos;
                }
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(70);
                alertDlg.setTitle("Warning");
                alertDlg.setMessage(files.get(selectID).getName() + "を削除します。\nよろしいですか？");
                alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        delete(files.get(selectID));
                        for (int i = 0; i < list.size(); i++) {
                            if (item.equals(list.get(i))) {
                                new File(Environment.getExternalStorageDirectory() + "/Download/sample" + (i + 1) + "" + ".zip").delete();
                            }
                        }
                        reload();
                    }
                });
                alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
                return true;
            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setText("Library");
        button.setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Download.class));
                finish();
            }
        });
        listheight();
    }

    public void Read() {
        Import i = new Import(this, files.get(selectID).getPath() + "/" + files.get(selectID).getName() + "" +
                ".csv");
        read = i.Read();
        if (files.get(selectID).exists()) {
            i.readMemo(files.get(selectID).getPath() + "/" + files.get(selectID).getName() + ".txt");
        }
    }

    private void delete(File f) {// ディレクトリ削除
        if (f.exists() == false) {
            return;
        }

        if (f.isFile()) {
            f.delete();
        }

        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
                delete(files[i]);
            }
            f.delete();
        }
    }

    private void reload() {// 画面リロード
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
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

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keycode, event);
    }
}

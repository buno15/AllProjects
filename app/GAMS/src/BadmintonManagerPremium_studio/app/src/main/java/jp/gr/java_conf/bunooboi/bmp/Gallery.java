package jp.gr.java_conf.bunooboi.bmp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.gams.Manager;

public class Gallery extends Activity {
    ListView listview;
    AlertDialog.Builder alertDlg;

    ArrayList<File> files = new ArrayList<File>();
    int selectID = 0;
    Button button[] = new Button[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.gallery);

        alertDlg = new AlertDialog.Builder(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        String sdPath = String.valueOf(Environment.getExternalStorageDirectory() + "/BadmintonManager/" + jp.gr.java_conf.bunooboi.bmp.Calculation.GameName);
        File f[] = new File(sdPath).listFiles();

        for (int id = 0; id < f.length; id++) {// ディレクトリリスト取得
            if (f[id].getName().endsWith(".jpeg") /* || files[id].getName().endsWith(".3gpp") */) {
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
                System.out.println(item + pos);
                if (item.equals(files.get(pos).getName())) {
                    selectID = pos;
                    if (files.get(selectID).getName().endsWith(".jpeg")) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(files.get(selectID)), "image/*");
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(files.get(selectID)));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else if (files.get(selectID).getName().endsWith(".3gpp")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(files.get(selectID)), "audio/*");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {// 長押し
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                ListView listview = (ListView) parent;
                String item = (String) listview.getItemAtPosition(pos);
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
        button[0] = (Button) findViewById(R.id.button1);
        button[1] = (Button) findViewById(R.id.button2);
        button[2] = (Button) findViewById(R.id.button3);

        button[0].setText("Overview");
        button[1].setText("Score");
        button[2].setText("Larry");

        LayoutParams lp = button[0].getLayoutParams();
        MarginLayoutParams mlp = (MarginLayoutParams) lp;
        mlp.setMargins(Manager.getMargin(getApplicationContext()) + 10, Manager.getMargin(getApplicationContext()) + 5, Manager.getMargin(getApplicationContext()) + 10,
                Manager.getMargin(getApplicationContext()) + 5);
        for (int i = 0; i < button.length; i++) {
            button[i].setTextSize(5 * Manager.getScaleSize(getApplicationContext()));
            button[i].setLayoutParams(mlp);
        }
        button[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Overview.class);
                startActivity(i);
                finish();
            }
        });
        button[1].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ScoreTable.class);
                startActivity(i);
                finish();
            }
        });
        button[2].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Larry.class);
                startActivity(i);
                finish();
            }
        });

        listheight();

    }

    private void delete(File f) {// ディレクトリ削除
        if (f.exists() != false) {
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
}

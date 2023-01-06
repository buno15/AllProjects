package jp.gr.java_conf.bunooboi.bmf;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

import jp.gr.java_conf.bunooboi.gams.Manager;

/**
 * Created by hiro on 2016/07/15.
 */
public class Download extends Activity {
    static ListView listview;
    int selectID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        String filePath = String.valueOf(Environment.getExternalStorageDirectory() + "/BadmintonManager");
        final File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        adapter.addAll(Manager.LibraryList);
        if (Manager.coment.equals("Library TimeOut") || Manager.coment.equals("Library Error")) {
            Toast.makeText(getApplicationContext(), Manager.coment, Toast.LENGTH_SHORT).show();
        }
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {// 選択
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                listview.setEnabled(false);
                ListView listview = (ListView) parent;
                String item = (String) listview.getItemAtPosition(pos);
                selectID = pos;
                String filename = "/sample" + (selectID + 1) + ".zip";

                if (new File(Environment.getExternalStorageDirectory() + "/Download" + filename).exists() && new File(Environment.getExternalStorageDirectory() + "/BadmintonManager/" + item).exists()) {
                    Toast.makeText(getApplicationContext(), "ダウンロード済み", Toast.LENGTH_SHORT).show();
                    listview.setEnabled(true);
                } else if (new File(Environment.getExternalStorageDirectory() + "/Download" + filename).exists() && new File(Environment.getExternalStorageDirectory() + "/BadmintonManager/" + item).exists() == false) {
                    Broadcast.filename = filename;
                    Broadcast.item = item;
                    Broadcast.context = getApplicationContext();
                    Broadcast.extract(Environment.getExternalStorageDirectory() + "/Download" + filename, item);
                } else {
                    DownloadManager downLoadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    Uri.Builder uriBuilder = new Uri.Builder();
                    uriBuilder.scheme("http");
                    uriBuilder.authority("www.bunooboi.sakura.ne.jp");
                    uriBuilder.path("/res" + filename);
                    DownloadManager.Request request = new DownloadManager.Request(uriBuilder.build());
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    request.setMimeType("application/zip");

                    Broadcast.filename = filename;
                    Broadcast.item = item;
                    Broadcast.context = getApplicationContext();

                    downLoadManager.enqueue(request);
                }
            }
        });
        listheight();
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
            startActivity(new Intent(getApplicationContext(), History.class));
            finish();
        }
        return super.onKeyDown(keycode, event);
    }
}

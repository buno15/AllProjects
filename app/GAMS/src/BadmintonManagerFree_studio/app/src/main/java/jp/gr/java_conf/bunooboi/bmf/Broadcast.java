package jp.gr.java_conf.bunooboi.bmf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by hiro on 2016/07/15.
 */
public class Broadcast extends BroadcastReceiver {
    static String filename;
    static String item;
    static Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        extract(Environment.getExternalStorageDirectory() + "/Download" + filename, item);
    }

    public static void extract(String filename, String destFileName) {
        ZipInputStream in = null;
        BufferedOutputStream out = null;

        ZipEntry zipEntry = null;
        int len = 0;

        try {
            new File(Environment.getExternalStorageDirectory() + "/BadmintonManager/" + destFileName).mkdir();
            in = new ZipInputStream(new FileInputStream(filename));

            // ZIPファイルに含まれるエントリに対して順にアクセス
            while ((zipEntry = in.getNextEntry()) != null) {
                File newfile = new File(zipEntry.getName());
                // 出力用ファイルストリームの生成
                out = new BufferedOutputStream(
                        new FileOutputStream(Environment.getExternalStorageDirectory() +
                                "/BadmintonManager/" + destFileName + "/" + newfile.getName())
                );
                // エントリの内容を出力
                byte[] buffer = new byte[1024];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }

                in.closeEntry();
                out.close();
                out = null;
            }
            Download.listview.setEnabled(true);
            Toast.makeText(context, "ダウンロードしました", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

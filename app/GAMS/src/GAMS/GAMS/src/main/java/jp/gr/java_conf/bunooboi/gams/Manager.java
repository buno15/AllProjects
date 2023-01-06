package jp.gr.java_conf.bunooboi.gams;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hiro on 2016/07/10.
 */
public class Manager {
    static String FilePath;//試合データまでのパス(例　/BadmintonManager/SampleMatch/)
    public static final List<String> LibraryList = Collections.synchronizedList(new ArrayList<String>());
    public static String coment;

    public static void init(String FilePath) {
        Manager.FilePath = FilePath;
    }

    public static synchronized void getLibrary() {
        new Thread() {
            @Override
            public void run() {
                BufferedReader br = null;
                try {
                    final URL url = new URL("http://www.bunooboi.sakura.ne.jp/res/BMlist.txt");
                    final HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(10000);    //接続タイムアウト
                    con.setRequestMethod("GET");
                    con.setInstanceFollowRedirects(false);
                    con.connect();
                    br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (!LibraryList.contains(line)) {
                            LibraryList.add(line);
                        }
                    }
                    coment="OK";
                } catch (SocketTimeoutException e) {
                    LibraryList.clear();
                    coment = "Library TimeOut";
                } catch (IOException e) {
                    LibraryList.clear();
                    coment = "Library Error";
                } finally {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static int getDispW(Context context) {// ディスプレイサイズ取得
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();                            // ディスプレイサイズ
        disp.getSize(size);
        return size.x;
    }

    public static int getDispH(Context context) {// ディスプレイサイズ取得
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();                            // ディスプレイサイズ
        disp.getSize(size);
        return size.y;
    }

    public static float getScaleSize(Context context) {
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        return (float) size.x / (float) bm.getWidth();
    }

    public static int getMargin(Context context) {// 余白計算
        int marginmoto = 4;
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (marginmoto * scale + 0.5f);
    }
}

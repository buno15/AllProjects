package jp.gr.java_conf.bunooboi.mydic;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.mydic.Activity.Stop;

/**
 * Created by hiro on 2016/09/05.
 */
public class CheckService extends Service {
    Timer timer;
    int timeCount;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        Toast.makeText(getApplicationContext(), "YouTubeCheckStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startid) {
        final Handler handler = new Handler();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<String> list = new ArrayList<String>();
                        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        // 起動中のアプリ情報を取得
                        List<ActivityManager.RunningAppProcessInfo> runningApp = activityManager.getRunningAppProcesses();
                        PackageManager packageManager = getPackageManager();
                        if (runningApp != null) {
                            for (ActivityManager.RunningAppProcessInfo app : runningApp) {
                                try {
                                    // アプリ名をリストに追加
                                    ApplicationInfo appInfo = packageManager.getApplicationInfo(app.processName, 0);
                                    list.add((String) packageManager.getApplicationLabel(appInfo));
                                } catch (PackageManager.NameNotFoundException e) {

                                }
                            }
                        }
                        if (list.contains("YouTube")) {
                            Intent i = new Intent(getApplicationContext(), Stop.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        } else {
                            if (timeCount >= 600) {
                                stopSelf();
                            }
                        }
                    }
                });
            }
        }, 0, 10000);
        return START_STICKY;
    }

    private void showLog() {
        try {
            ArrayList<String> commandLine = new ArrayList<String>();
            // コマンドの作成
            commandLine.add("logcat");
            commandLine.add("-d");
            Process process = Runtime.getRuntime().exec(
                    commandLine.toArray(new String[commandLine.size()]));
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(process.getInputStream()), 1024);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.indexOf("android.intent.action.MAIN") != -1) {
                    System.out.println("main");
                }
            }
        } catch (IOException e) {
            // 例外処理
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        Toast.makeText(getApplicationContext(), "YouTubeCheckStop", Toast.LENGTH_SHORT).show();
    }
}

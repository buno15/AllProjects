package jp.gr.java_conf.bunooboi.appcheck;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hiro on 2016/09/07.
 */
public class MainService extends Service {
    Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        Toast.makeText(getApplicationContext(), "CheckStart", Toast.LENGTH_SHORT).show();
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
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (list.contains("YouTube")) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        }
                    }
                });
            }
        }, 0, 10000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "CheckStop", Toast.LENGTH_SHORT).show();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}


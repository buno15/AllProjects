package jp.gr.java_conf.bunooboi.appcheck;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        stopService(new Intent(getApplicationContext(), MainService.class));
        startService(new Intent(getApplicationContext(), MainService.class));

        TextView textview = (TextView) findViewById(R.id.textview);
        textview.setText("禁止アプリを起動");
        textview.setTextSize(40 * getScaleSize(getApplicationContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        ActivityManager activityManager = ((ActivityManager) getSystemService(ACTIVITY_SERVICE));
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> appList = pm.getInstalledApplications(0);

        for (int i = 0; i < appList.size(); i++) {
            String packageName = appList.get(i).packageName;
            if (packageName.equals("com.google.android.youtube")) {
                activityManager.killBackgroundProcesses(packageName);
            }
        }
    }

    public static float getScaleSize(Context context) {// 文字サイズ調整
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        float width = 0;
        float scale = 0;
        width = size.x;
        scale = (float) width / (float) bm.getWidth();
        return scale;
    }
}

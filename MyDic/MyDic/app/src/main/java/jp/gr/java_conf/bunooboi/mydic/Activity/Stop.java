package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import jp.gr.java_conf.bunooboi.mydic.CheckService;
import jp.gr.java_conf.bunooboi.mydic.R;

/**
 * Created by hiro on 2016/09/05.
 */
public class Stop extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop);

        TextView textview = (TextView) findViewById(R.id.textview);
        textview.setText("YouTubeを起動");
        textview.setTextSize(40 * Main.getScaleSize(getApplicationContext()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                startActivity(new Intent(getApplicationContext(), Main.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Main.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();
        stopService(new Intent(getApplicationContext(), CheckService.class));
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

    @Override
    public void onPause() {
        super.onPause();
        startService(new Intent(getApplicationContext(), CheckService.class));
    }
}

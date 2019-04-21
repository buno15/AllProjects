package jp.gr.java_conf.bunooboi.timesignal;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

/**
 * Created by hiro on 2016/09/02.
 */
public class StartupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ContextCompat.startForegroundService(context, new Intent(context, MainService.class));
    }
}
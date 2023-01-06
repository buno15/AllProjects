package jp.gr.java_conf.bunooboi.appcheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hiro on 2016/09/02.
 */
public class StartupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, MainService.class));
    }
}

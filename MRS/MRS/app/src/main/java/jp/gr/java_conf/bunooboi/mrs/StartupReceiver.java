package jp.gr.java_conf.bunooboi.mrs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.NotificationCompat;

/**
 * Created by hiro on 2016/09/02.
 */
public class StartupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Memorys.init();
        Memorys.serch();
        if (Memorys.indexs.size() != 0) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.icon_notification);
            builder.setColor(Color.parseColor("#3333ff"));
            builder.setContentTitle("MRS");
            builder.setContentText("復習する必要のあるデータが存在します");
            NotificationManager manager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
            Notification note = builder.build();
            manager.notify(R.string.app_name, note);
        }
    }
}

package jp.gr.java_conf.bunooboi.mrs;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

/**
 * Created by hiro on 2016/09/02.
 */
public class StartupReceiver extends BroadcastReceiver {
    NotificationCompat.Builder builder;
    String id = "Run";
    String name = "TimeSignal";

    @Override
    public void onReceive(Context context, Intent intent) {
        Memorys.init();
        Memorys.serch();
        if (Memorys.indexs.size() != 0) {

            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(mChannel);

            builder = new NotificationCompat.Builder(context, id);
            builder.setSmallIcon(R.drawable.icon_notification);
            builder.setColor(Color.parseColor("#3333ff"));
            builder.setContentTitle("MRS");
            builder.setContentText("復習する必要があるデータが存在します");
            Notification note = builder.build();
            nm.notify(R.string.app_name, note);
        }
    }
}

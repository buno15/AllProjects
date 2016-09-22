package jp.gr.java_conf.bunooboi.mydic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;

import java.util.Calendar;

/**
 * Created by hiro on 2016/09/02.
 */
public class StartupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Values.init(context);
        if (Values.startservice) {
            Values.setIndexCount(0);
            if (isChangeDate()) {
                MainService.putNew();
                setChengeDate();
            } else {
                MainService.putRepeat();
            }
            context.startService(new Intent(context, MainService.class));

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.icon_notification);
            builder.setColor(Color.parseColor("#ffa312"));
            builder.setContentTitle("MyDic");
            builder.setContentText("タップして検索開始");
            Intent i = new Intent(context, BackService.class).putExtra("service", true);
            PendingIntent contentIntent = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(contentIntent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
            Notification note = builder.build();
            note.flags = Notification.FLAG_ONGOING_EVENT;
            manager.notify(R.string.app_name, note);
        }
    }

    public static boolean isChangeDate() {
        Calendar c = Calendar.getInstance();
        int nowDay = c.get(Calendar.DATE);
        int beforeDay = PreferenceManager.getDefaultSharedPreferences(App.getInstance().getApplicationContext()).getInt("changedate", -1);
        if (nowDay == beforeDay) {
            return false;
        } else {
            return true;
        }
    }

    public static void setChengeDate() {
        Calendar c = Calendar.getInstance();
        int nowDay = c.get(Calendar.DATE);
        PreferenceManager.getDefaultSharedPreferences(App.getInstance().getApplicationContext()).edit().putInt("changedate", nowDay).commit();
    }
}

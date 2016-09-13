package jp.gr.java_conf.bunooboi.myconfirmation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by hiro on 2016/09/11.
 */
public class StartupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, NotificationReceived.class); // ReceivedActivityを呼び出すインテントを作成
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, i, 0); // ブロードキャストを投げるPendingIntentの作成

        Calendar calendar = Calendar.getInstance(); // Calendar取得
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 18, 0, 0);

        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE); // AlramManager取得
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 0, 0);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }
}

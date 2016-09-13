package jp.gr.java_conf.bunooboi.myconfirmation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by hiro on 2016/09/11.
 */
public class NotificationReceived extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(minute);
        if (minute == 23) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("english", false).commit();
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("math", false).commit();
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("aizu", false).commit();
            Toast.makeText(context, "解除", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "やることやりましたか？", Toast.LENGTH_SHORT).show();
        }
        Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }
}

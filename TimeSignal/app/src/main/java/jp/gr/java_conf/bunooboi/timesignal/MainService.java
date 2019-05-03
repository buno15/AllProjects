package jp.gr.java_conf.bunooboi.timesignal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainService extends Service {
    Timer timer;
    Handler handler = new Handler();

    MediaPlayer mediaPlayer;
    NotificationCompat.Builder builder;
    Vibrator mVibrator;

    int timeCount = 0;
    String id = "Run";
    String name = "TimeSignal";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.start);

        NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.createNotificationChannel(mChannel);

        builder = new NotificationCompat.Builder(getApplicationContext(), id);
        builder.setSmallIcon(R.drawable.icon);
        builder.setContentTitle("TimeSignal");
        builder.setContentText("Run TimeSignal");
        Notification note = builder.build();
        note.flags = Notification.FLAG_ONGOING_EVENT;

        startForeground(1, note);

        Toast.makeText(getApplicationContext(), "Start TimeSignal", Toast.LENGTH_SHORT).show();

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (++timeCount % 180 == 0) {
                            mediaPlayer.start();

                            long pattern[] = {3000, 300, 700, 300, 700, 300, 700, 800};
                            mVibrator.vibrate(pattern, -1);

                            timeCount = 0;

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
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mVibrator != null) {
            mVibrator.cancel();
            mVibrator = null;
        }
        stopForeground(Service.STOP_FOREGROUND_DETACH);
        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.deleteNotificationChannel(id);
        Toast.makeText(getApplicationContext(), "Stop TimeSignal", Toast.LENGTH_SHORT).show();
    }
}
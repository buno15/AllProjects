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

    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2[] = new MediaPlayer[3];
    NotificationCompat.Builder builder;
    Vibrator mVibrator;

    int timeCount = 0;
    int randomCount = 0;
    int random = 0;
    int mp2Rnd = 0;
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
        mediaPlayer1 = MediaPlayer.create(getApplicationContext(), R.raw.start);
        mediaPlayer2[0] = MediaPlayer.create(getApplicationContext(), R.raw.golgo13);
        mediaPlayer2[1] = MediaPlayer.create(getApplicationContext(), R.raw.re);
        mediaPlayer2[2] = MediaPlayer.create(getApplicationContext(), R.raw.revoice2);

        NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.createNotificationChannel(mChannel);

        random = (int) Math.floor(Math.random() * 180) + 60;
        mp2Rnd = (int) Math.floor(Math.random() * 4);

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
                            mediaPlayer1.start();

                            long pattern[] = {3000, 200, 800, 200, 800, 200, 800, 1000};
                            mVibrator.vibrate(pattern, -1);

                            timeCount = 0;

                        }
                        if (++randomCount % random == 0) {
                            switch (mp2Rnd) {
                                case 0:
                                case 1:
                                    mediaPlayer2[0].start();
                                    break;
                                case 2:
                                    mediaPlayer2[1].start();
                                    break;
                                case 3:
                                    mediaPlayer2[2].start();
                            }
                            mp2Rnd = (int) Math.floor(Math.random() * 4);

                            long pattern[] = {0, 1500};
                            mVibrator.vibrate(pattern, -1);

                            randomCount = 0;
                            random = (int) Math.floor(Math.random() * 180) + 60;
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
        if (mediaPlayer1 != null) {
            mediaPlayer1.release();
            mediaPlayer1 = null;
        }
        if (mediaPlayer2[0] != null) {
            mediaPlayer2[0].release();
            mediaPlayer2[0] = null;
        }
        if (mediaPlayer2[1] != null) {
            mediaPlayer2[1].release();
            mediaPlayer2[1] = null;
        }
        if (mediaPlayer2[2] != null) {
            mediaPlayer2[2].release();
            mediaPlayer2[2] = null;
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
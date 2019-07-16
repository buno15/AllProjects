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

    MediaPlayer mediaPlayer1[] = new MediaPlayer[4];
    MediaPlayer mediaPlayer2[] = new MediaPlayer[8];
    NotificationCompat.Builder builder;
    Vibrator mVibrator;

    int timeCount = 0;
    int quarterCount = 0;
    int randomCount = 0;
    int random = 0;
    int mp2Rnd = 0;
    String id = "Run";
    String name = "TimeSignal";

    long pattern1[] = {0, 200};
    long pattern2[] = {0, 200, 800, 500};
    long pattern3[] = {0, 200, 800, 200, 800, 500};
    long pattern4[] = {0, 200, 800, 200, 800, 200, 800, 500};

    @Override
    public IBinder onBind(Intent intent) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer1[0] = MediaPlayer.create(getApplicationContext(), R.raw.count1);
        mediaPlayer1[1] = MediaPlayer.create(getApplicationContext(), R.raw.count2);
        mediaPlayer1[2] = MediaPlayer.create(getApplicationContext(), R.raw.count3);
        mediaPlayer1[3] = MediaPlayer.create(getApplicationContext(), R.raw.count4);

        mediaPlayer2[0] = MediaPlayer.create(getApplicationContext(), R.raw.golgo13);
        mediaPlayer2[1] = MediaPlayer.create(getApplicationContext(), R.raw.goglo132);
        mediaPlayer2[2] = MediaPlayer.create(getApplicationContext(), R.raw.sherlocka);
        mediaPlayer2[3] = MediaPlayer.create(getApplicationContext(), R.raw.sherlockb);
        mediaPlayer2[4] = MediaPlayer.create(getApplicationContext(), R.raw.sherlockc);
        mediaPlayer2[5] = MediaPlayer.create(getApplicationContext(), R.raw.sherlockd);
        mediaPlayer2[6] = MediaPlayer.create(getApplicationContext(), R.raw.re);
        mediaPlayer2[7] = MediaPlayer.create(getApplicationContext(), R.raw.revoice2);

        NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.createNotificationChannel(mChannel);

        random = (int) Math.floor(Math.random() * 600) + 1200;
        mp2Rnd = (int) Math.floor(Math.random() * 8);

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
                        if (++timeCount % 900 == 0) {

                            switch (quarterCount) {
                                case 0:
                                    mediaPlayer1[0].start();
                                    mVibrator.vibrate(pattern1, -1);
                                    break;
                                case 1:
                                    mediaPlayer1[1].start();
                                    mVibrator.vibrate(pattern2, -1);
                                    break;
                                case 2:
                                    mediaPlayer1[2].start();
                                    mVibrator.vibrate(pattern3, -1);
                                    break;
                                case 3:
                                    mediaPlayer1[3].start();
                                    mVibrator.vibrate(pattern4, -1);
                                    break;
                            }

                            if (quarterCount < 3)
                                quarterCount++;
                            else quarterCount = 0;
                            timeCount = 0;

                        }
                        if (++randomCount % random == 0) {
                            switch (mp2Rnd) {
                                case 0:
                                    mediaPlayer2[0].start();
                                    break;
                                case 1:
                                    mediaPlayer2[1].start();
                                    break;
                                case 2:
                                    mediaPlayer2[2].start();
                                    break;
                                case 3:
                                    mediaPlayer2[3].start();
                                    break;
                                case 4:
                                    mediaPlayer2[4].start();
                                    break;
                                case 5:
                                    mediaPlayer2[5].start();
                                    break;
                                case 6:
                                    mediaPlayer2[6].start();
                                    break;
                                case 7:
                                    mediaPlayer2[7].start();
                                    break;
                            }
                            mp2Rnd = (int) Math.floor(Math.random() * 8);

                            randomCount = 0;
                            random = (int) Math.floor(Math.random() * 600) + 1200;
                        }
                    }
                });
            }
        }, 0, 1000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        for (int i = 0; i < mediaPlayer1.length; i++) {
            if (mediaPlayer1[i] != null) {
                mediaPlayer1[i].release();
                mediaPlayer1[i] = null;
            }
        }
        for (int i = 0; i < mediaPlayer2.length; i++) {
            if (mediaPlayer2[i] != null) {
                mediaPlayer2[i].release();
                mediaPlayer2[i] = null;
            }
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
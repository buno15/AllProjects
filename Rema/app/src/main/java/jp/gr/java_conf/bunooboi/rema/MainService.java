package jp.gr.java_conf.bunooboi.rema;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainService extends Service implements TextToSpeech.OnInitListener {
    Timer timer;
    Handler handler = new Handler();
    TextToSpeech tts;
    NotificationCompat.Builder builder;
    int timeCount = 0;
    int random = 0;
    String id = "Run";
    String name = "Rema";


    @Override
    public IBinder onBind(Intent intent) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.createNotificationChannel(mChannel);

        random = (int) Math.floor(Math.random() * App.titles.size());
        App.indexes.add(random);

        builder = new NotificationCompat.Builder(getApplicationContext(), id);
        builder.setSmallIcon(R.drawable.icon);
        builder.setContentTitle("Rema");
        builder.setContentText("Start Rema");
        Notification note = builder.build();
        note.flags = Notification.FLAG_ONGOING_EVENT;
        startForeground(1, note);

        tts = new TextToSpeech(this, this);

        Toast.makeText(getApplicationContext(), "Start Rema", Toast.LENGTH_SHORT).show();

        App.init();
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
                        if (++timeCount % 30 == 0) {

                            builder = new NotificationCompat.Builder(getApplicationContext(), id);
                            builder.setSmallIcon(R.drawable.icon);
                            builder.setContentTitle(App.titles.get(random));
                            Notification note = builder.build();
                            startForeground(1, note);

                            speechText(App.titles.get(random));

                            timeCount = 0;
                            if (App.indexes.size() == App.titles.size()) {
                                App.indexes.clear();
                            }
                            while (true) {
                                random = (int) Math.floor(Math.random() * App.titles.size());
                                if (App.indexes.indexOf(random) == -1) {
                                    App.indexes.add(random);
                                    break;
                                }
                            }


                        }
                    }
                });
            }
        }, 0, 10000);
        return START_STICKY;
    }

    @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
            //言語選択
            Locale locale = Locale.JAPAN;
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
            } else {
                Log.d("Error", "Locale");
            }
        } else {
            Log.d("Error", "Init");
        }
    }

    private void speechText(String contents) {
        if (0 < contents.length()) {
            if (tts.isSpeaking()) {
                // 読み上げ中なら停止
                tts.stop();
            }
            //読み上げ開始
            tts.speak(contents, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (null != tts) {
            //ttsのリソース解放する
            tts.shutdown();
        }
        stopForeground(Service.STOP_FOREGROUND_DETACH);
        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.deleteNotificationChannel(id);
        Toast.makeText(getApplicationContext(), "Stop Rema", Toast.LENGTH_SHORT).show();
    }
}
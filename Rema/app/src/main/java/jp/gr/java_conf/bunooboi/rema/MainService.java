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
    TextToSpeech ttsJ;
    TextToSpeech ttsE;
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

        ttsJ = new TextToSpeech(this, this);
        ttsE = new TextToSpeech(this, this);

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
                        if (++timeCount % 6 == 0) {

                            speechJapanText(App.titles.get(random));

                        }
                        if (timeCount % 7 == 0) {
                            builder = new NotificationCompat.Builder(getApplicationContext(), id);
                            builder.setSmallIcon(R.drawable.icon);
                            builder.setContentTitle(App.answers.get(random));
                            Notification note = builder.build();
                            startForeground(1, note);

                            speechEngText(App.answers.get(random));

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
            if (ttsJ.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                ttsJ.setLanguage(locale);
            } else {
                Log.d("Error", "Locale");
            }
            locale = Locale.ENGLISH;
            if (ttsE.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                ttsE.setLanguage(locale);
            } else {
                Log.d("Error", "Locale");
            }
        } else {
            Log.d("Error", "Init");
        }
    }

    private void speechJapanText(String contents) {
        if (0 < contents.length()) {
            if (ttsJ.isSpeaking()) {
                // 読み上げ中なら停止
                ttsJ.stop();
            }
            //読み上げ開始
            ttsJ.speak(contents, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void speechEngText(String contents) {
        if (0 < contents.length()) {
            if (ttsE.isSpeaking()) {
                // 読み上げ中なら停止
                ttsE.stop();
            }
            //読み上げ開始
            ttsE.speak(contents, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (null != ttsJ) {
            //ttsのリソース解放する
            ttsJ.shutdown();
        }
        if (null != ttsE) {
            //ttsのリソース解放する
            ttsE.shutdown();
        }
        stopForeground(Service.STOP_FOREGROUND_DETACH);
        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.deleteNotificationChannel(id);
        Toast.makeText(getApplicationContext(), "Stop Rema", Toast.LENGTH_SHORT).show();
    }
}
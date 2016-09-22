package jp.gr.java_conf.bunooboi.mydic;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.mydic.Activity.Main;

/**
 * Created by hiro on 2016/09/02.
 */
public class MainService extends Service {
    public static ArrayList<String> sentences;
    Timer mainTimer;
    TextToSpeech tts;
    int minuteCount;
    int minuteLimit;
    int mood;
    public static final int REPEAT = 0;
    public static final int ALLSPEAK = 1;
    public static final int NEW = 2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Values.init(getApplicationContext());
        mainTimer = new Timer();
        minuteCount = 0;
        minuteLimit = (int) Math.floor(Math.random() * 5) + 1;
        mood = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("mood", mood);
        if (mood == REPEAT) {
            sentences = Input.getInput().repeatRead();
        } else if (mood == ALLSPEAK) {
            sentences = Input.getInput().allSpeakRead();
        } else if (mood == NEW) {
            sentences = Input.getInput().getNew();
        }
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = Locale.JAPANESE;
                    if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                        tts.setLanguage(locale);
                        tts.setPitch(Values.pitch);
                        if (mood == REPEAT)
                            tts.setSpeechRate(0.75f);
                        else
                            tts.setSpeechRate(Values.speechrate);
                        Main.params.put(TextToSpeech.Engine.KEY_PARAM_VOLUME, String.valueOf(Values.volume));
                    } else {
                        System.out.println("Error Language");
                    }
                } else {
                    System.out.println("Error Init");
                }
            }
        });
        Toast.makeText(getApplicationContext(), "MyDicStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startid) {
        final Handler handler = new Handler();
        if (mood == REPEAT) {
            mainTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int index = Values.getIndexCount();
                            minuteCount++;
                            if (minuteCount >= minuteLimit) {
                                if (index < sentences.size()) {
                                    textSpeech(sentences.get(index));
                                    Values.setIndexCount(++index);
                                    Toast.makeText(getApplicationContext(), "MyDic" + index, Toast.LENGTH_SHORT).show();
                                } else {
                                    Values.setIndexCount(0);
                                }
                                minuteCount = 0;
                                minuteLimit = (int) Math.floor(Math.random() * 5) + 1;
                            }
                        }
                    });
                }
            }, 3000, 60000);
        } else if (mood == ALLSPEAK || mood == NEW) {
            mainTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int index = Values.getIndexCount();
                            if (!tts.isSpeaking()) {
                                if (index < sentences.size()) {
                                    textSpeech(sentences.get(index));
                                    Values.setIndexCount(++index);
                                    Toast.makeText(getApplicationContext(), "MyDic" + index, Toast.LENGTH_SHORT).show();
                                } else {
                                    putRepeat();
                                    stopService(new Intent(getApplicationContext(), MainService.class));
                                }
                            }
                        }
                    });
                }
            }, 3000, 1000);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer = null;
        }
        if (tts != null) {
            tts.shutdown();
            tts = null;
        }
    }

    public static void putRepeat() {
        PreferenceManager.getDefaultSharedPreferences(App.getInstance().getApplicationContext()).edit().putInt("mood", MainService.REPEAT).commit();
    }

    public static void putAllSpeak() {
        PreferenceManager.getDefaultSharedPreferences(App.getInstance().getApplicationContext()).edit().putInt("mood", MainService.ALLSPEAK).commit();
    }

    public static void putNew() {
        PreferenceManager.getDefaultSharedPreferences(App.getInstance().getApplicationContext()).edit().putInt("mood", MainService.NEW).commit();
    }

    void textSpeech(String text) {
        if (tts.isSpeaking()) {
            tts.stop();
        }
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, Main.params);
    }
}

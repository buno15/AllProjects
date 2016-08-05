package jp.gr.java_conf.bunooboi.mydic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hiro on 2016/08/05.
 */
public class BackService extends Service {
    SpeechRecognizer recognizer;

    @Override
    public IBinder onBind(Intent intent) {
        startSpeech();
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Sentences.init();
        Values.init(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startid) {
        if (intent.getBooleanExtra("service", false)) {
            startSpeech();
        } else {
            stopSelf();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (recognizer != null) {
            recognizer.cancel();
            recognizer.destroy();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.notification);
        builder.setColor(Color.parseColor("#ffa312"));
        builder.setContentTitle("MyDic");
        builder.setContentText("タップして検索開始");
        Intent intent = new Intent(getApplicationContext(), BackService.class).putExtra("service", true);
        PendingIntent contentIntent = PendingIntent.getService(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        Notification note = builder.build();
        note.flags = Notification.FLAG_ONGOING_EVENT;
        manager.notify(R.string.app_name, note);
    }

    public void startSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        if (Values.recognition)
            intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);
        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        recognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            // 音声入力開始
            @Override
            public void onBeginningOfSpeech() {
                Toast.makeText(getApplicationContext(), "入力開始", Toast.LENGTH_SHORT).show();
            }

            // 録音データのフィードバック用
            @Override
            public void onBufferReceived(byte[] buffer) {
            }

            // 入力音声のdBが変化した
            @Override
            public void onRmsChanged(float rmsdB) {
            }

            // 音声入力終了
            @Override
            public void onEndOfSpeech() {
                Toast.makeText(getApplicationContext(), "入力終了", Toast.LENGTH_SHORT).show();
            }

            // ネットワークエラー又は、音声認識エラー
            @Override
            public void onError(int error) {
                switch (error) {
                    case SpeechRecognizer.ERROR_AUDIO:
                        // 音声データ保存失敗
                        break;
                    case SpeechRecognizer.ERROR_CLIENT:
                        // Android端末内のエラー(その他)
                        break;
                    case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                        // 権限無し
                        break;
                    case SpeechRecognizer.ERROR_NETWORK:
                        // ネットワークエラー(その他)
                        break;
                    case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                        // ネットワークタイムアウトエラー
                        break;
                    case SpeechRecognizer.ERROR_NO_MATCH:
                        // 音声認識結果無し
                        Toast.makeText(getApplicationContext(), "認識結果なし", Toast.LENGTH_SHORT).show();
                        break;
                    case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                        // RecognitionServiceへ要求出せず
                        break;
                    case SpeechRecognizer.ERROR_SERVER:
                        // Server側からエラー通知
                        break;
                    case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                        // 音声入力無し
                        Toast.makeText(getApplicationContext(), "入力なし", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                stopSelf();
            }

            // イベント発生時に呼び出される
            @Override
            public void onEvent(int eventType, Bundle params) {
            }

            // 部分的な認識結果が得られる場合に呼び出される
            @Override
            public void onPartialResults(Bundle partialResults) {
            }

            // 認識結果
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> recData = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (Sentences.serch(recData.get(0)) == false) {
                    if (Sentences.serch(recData.get(1)) == false) {
                        if (Sentences.serch(recData.get(2))) {
                            startActivity(new Intent(getApplicationContext(), Main.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            Main.fromService = true;
                        }
                    } else {
                        startActivity(new Intent(getApplicationContext(), Main.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        Main.fromService = true;
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), Main.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    Main.fromService = true;
                }
                Toast.makeText(BackService.this, recData.toString(), Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        });
        recognizer.startListening(intent);
    }
}

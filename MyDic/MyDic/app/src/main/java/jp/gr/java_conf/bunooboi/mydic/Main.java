package jp.gr.java_conf.bunooboi.mydic;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class Main extends AppCompatActivity {
    WebView webview;
    TextToSpeech tts;
    SpeechRecognizer recognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Values.init(getApplicationContext());

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = Locale.JAPANESE;
                    if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                        tts.setLanguage(locale);
                        tts.setPitch(Values.pitch);
                        tts.setSpeechRate(Values.speechrate);
                        textSpeech("");
                    } else {
                        System.out.println("Error Language");
                    }
                } else {
                    System.out.println("Error Init");
                }
            }
        });

        webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        if (Values.display)
            webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setProgressBarIndeterminateVisibility(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setProgressBarIndeterminateVisibility(false);
                Log.v("URL", webview.getUrl());
            }
        });
        webview.loadUrl("file:///" + Values.RootPath + "/java_se_8_api/api/overview-summary.html");

        final EditText edittext = (EditText) findViewById(R.id.edittext);
        edittext.setTextSize(20 * getScaleSize(getApplicationContext()));
        edittext.setTextColor(Color.BLACK);
        Button button1 = (Button) findViewById(R.id.button1);
        ImageButton button2 = (ImageButton) findViewById(R.id.button2);
        button1.setText("検索");
        button1.setTextSize(20 * getScaleSize(getApplicationContext()));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Sentences.serch(edittext.getText().toString())) {
                    webview.loadUrl("file:///" + Values.RootPath + Sentences.link);
                }
                textSpeech(Sentences.text);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Setting.class));
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getIntent().getBooleanExtra("flag", false)) {
            startSpeech();
            setIntent(new Intent());
        }
        if (Values.notification) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.drawable.notification);
            builder.setColor(Color.parseColor("#ffa312"));
            builder.setContentTitle("MyDic");
            builder.setContentText("タップして検索開始");
            Intent intent = new Intent(getApplicationContext(), Main.class);
            intent.putExtra("flag", true);
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(contentIntent);
            NotificationManager manager = (NotificationManager)
                    getSystemService(Service.NOTIFICATION_SERVICE);
            Notification note = builder.build();
            note.flags = Notification.FLAG_ONGOING_EVENT;
            manager.notify(0, note);
        } else {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotificationManager.cancel(R.string.app_name);
        }
    }


    @SuppressWarnings("deprecation")
    private void textSpeech(String text) {
        if (tts.isSpeaking()) {
            tts.stop();
        }
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_rec:
                if (recognizer != null) {
                    recognizer.cancel();
                    recognizer.destroy();
                }
                startSpeech();
                break;
            case R.id.action_left:
                webview.goBack();
                break;
            case R.id.action_right:
                webview.goForward();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {
                webview.goBack();
                return true;
            } else {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
                alertDlg.setMessage("アプリを終了しますか？");
                alertDlg.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        mNotificationManager.cancel(R.string.app_name);
                        finish();
                    }
                });
                alertDlg.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void startSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        if (jp.gr.java_conf.bunooboi.mydic.Values.recognition)
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
                        textSpeech("認識結果がありません");
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
                        textSpeech("おんせい入力がされていません");
                        break;
                    default:
                }
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
                            webview.loadUrl("file:///" + Values.RootPath + Sentences.link);
                        } else {

                        }
                    }
                }
                textSpeech(Sentences.text);
                Toast.makeText(Main.this, recData.toString(), Toast.LENGTH_SHORT).show();
                recognizer.destroy();
            }
        });
        recognizer.startListening(intent);
    }

    public static float getScaleSize(Context context) {// 文字サイズ調整
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        float width = 0;
        float scale = 0;
        width = size.x;
        scale = (float) width / (float) bm.getWidth();
        return scale;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tts != null)
            tts.stop();
        tts.shutdown();
        if (recognizer != null) {
            recognizer.cancel();
            recognizer.destroy();
        }
    }
}

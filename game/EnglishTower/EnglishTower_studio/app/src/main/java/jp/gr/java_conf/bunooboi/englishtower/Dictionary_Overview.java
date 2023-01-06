package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Dictionary_Overview extends Activity {
    static String word;
    static String japanese;
    static int image;
    static int id;

    TextView part;
    FitTextView wordtext;
    TextView japanesetext;
    ImageView img;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.dictionaryoverview);

        part = (TextView) findViewById(R.id.part);
        wordtext = (FitTextView) findViewById(R.id.word);
        japanesetext = (TextView) findViewById(R.id.japanese);
        img = (ImageView) findViewById(R.id.img);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        // japantext.setPaintFlags(wordtext.getPaintFlags() |
        // Paint.UNDERLINE_TEXT_FLAG);

        if (word.length() >= 20) {
            wordtext.setTextSize(4 * Tower.getScaleSize(getApplicationContext()));
        } else if (word.length() >= 15) {
            wordtext.setTextSize(5 * Tower.getScaleSize(getApplicationContext()));
        } else if (word.length() >= 10) {
            wordtext.setTextSize(6 * Tower.getScaleSize(getApplicationContext()));
        } else {
            wordtext.setTextSize(20 * Tower.getScaleSize(getApplicationContext()));
        }
        part.setTextSize(20 * Tower.getScaleSize(getApplicationContext()));
        japanesetext.setTextSize(12 * Tower.getScaleSize(getApplicationContext()));
        button1.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
        button2.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));

        japanesetext.setText(japanese);
        img.setBackgroundResource(image);
        button1.setText("戻る");
        button2.setText("検索");
        wordtext.setText(word);
        switch (id) {
            case 0:
                part.setText("名詞");
                part.setTextColor(Color.RED);
                break;
            case 1:
                part.setText("代名詞");
                part.setTextColor(Color.parseColor("#ff4f02"));
                break;
            case 2:
                part.setText("動詞");
                part.setTextColor(Color.YELLOW);
                break;
            case 3:
                part.setText("形容詞");
                part.setTextColor(Color.BLUE);
                break;
            case 4:
                part.setText("副詞");
                part.setTextColor(Color.GREEN);
                break;
            case 5:
                part.setText("冠詞");
                part.setTextColor(Color.WHITE);
                break;
            case 6:
                part.setText("助動詞");
                part.setTextColor(Color.CYAN);
                break;
            case 7:
                part.setText("前置詞");
                part.setTextColor(Color.parseColor("#800080"));
                break;
            case 8:
                part.setText("疑問詞");
                part.setTextColor(Color.BLACK);
                break;
            case 9:
                part.setText("感動詞");
                part.setTextColor(Color.parseColor("#ff55ff"));
                break;
            case 10:
                part.setText("接続詞");
                part.setTextColor(Color.GRAY);
                break;
        }
        part.setShadowLayer(5, 0, 0, Color.BLACK);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.start2();
                Intent i = new Intent(getApplicationContext(), Dictionary_List.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
                overridePendingTransition(0, 0);
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.start2();
                Uri uri = Uri.parse("http://www.google.com/search?q=" + word + "&tbm= isch");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

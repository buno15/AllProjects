package jp.gr.java_conf.bunooboi.zbs;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bunooboi on 16/10/08.
 */
public class ActivityMain extends Activity {
    TextView textView;
    LinearLayout linearLayout;
    Button button[] = new Button[6];
    int page = 1;
    static int point[] = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        Sound.init(getApplicationContext());

        textView = (TextView) findViewById(R.id.textView);
        textView.setTextSize(50 * Mine.getScaleSize(getApplicationContext()));
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        button[0] = (Button) findViewById(R.id.button1);
        button[1] = (Button) findViewById(R.id.button2);
        button[2] = (Button) findViewById(R.id.button3);
        button[3] = (Button) findViewById(R.id.button4);
        button[4] = (Button) findViewById(R.id.button5);
        button[5] = (Button) findViewById(R.id.button6);
        for (int i = 0; i < button.length; i++) {
            final int j = i;
            button[i].setText(String.valueOf(i));
            button[i].setTextSize(30 * Mine.getScaleSize(getApplicationContext()));
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (page == 1) {
                        point[0] += j;
                    } else if (page == 2) {
                        point[1] += j;
                    } else if (page == 3) {
                        point[2] += j;
                    } else if (page == 4) {
                        point[3] += j;
                    } else if (page == 5) {
                        point[4] += j;
                    } else if (page == 6) {
                        point[5] += j;
                    }
                    if (page == 6) {
                        startActivity(new Intent(getApplicationContext(), ActivityAnswer.class));
                        overridePendingTransition(0, 0);
                        finish();
                    } else {
                        nextPage(page++);
                    }
                    Sound.click();
                }
            });
        }
        nextPage(0);
    }

    void nextPage(int page) {
        String text = "";
        switch (page) {
            case 0:
                text = "禁欲";
                break;
            case 1:
                text = "忍耐";
                break;
            case 2:
                text = "冷静";
                break;
            case 3:
                text = "集中";
                break;
            case 4:
                text = "視野";
                break;
            case 5:
                text = "幸福";
                break;
        }
        textView.setText(text);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

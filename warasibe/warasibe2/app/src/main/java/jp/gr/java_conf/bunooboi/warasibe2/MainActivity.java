package jp.gr.java_conf.bunooboi.warasibe2;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static LinearLayout playbg, buttonLayout;
    public static TextView console, status;
    public static Button button1, button2;
    int enemyhp, enemypower, enemydamage = 0;
    int random1, random2, random3;
    int rn, margin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        playbg = (LinearLayout) findViewById(R.id.playbg);
        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);

        console = (TextView) findViewById(R.id.console);
        console.setTextSize(8 * DisplayManager.getScaleSize(getApplicationContext()));
        console.setTextColor(Color.BLACK);

        status = (TextView) findViewById(R.id.status);
        status.setTextSize(7 * DisplayManager.getScaleSize(getApplicationContext()));
        status.setTextColor(Color.WHITE);
        status.setShadowLayer(5, 0, 0, Color.BLACK);

        button1 = (Button) findViewById(R.id.button1);
        button1.setTextSize(8 * DisplayManager.getScaleSize(getApplicationContext()));
        button2 = (Button) findViewById(R.id.button2);
        button2.setTextSize(8 * DisplayManager.getScaleSize(getApplicationContext()));

        int marginmoto = 5;
        float scale = getResources().getDisplayMetrics().density;
        margin = (int) (marginmoto * scale + 0.5f);
    }
}

package jp.gr.java_conf.bunooboi.itcorrect;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        Button button[] = new Button[3];
        button[0] = (Button) findViewById(R.id.button1);
        button[1] = (Button) findViewById(R.id.button2);
        button[2] = (Button) findViewById(R.id.button3);
        ViewGroup.LayoutParams lp = button[0].getLayoutParams();
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
        mlp.setMargins(DisplayManager.getPctX(getApplicationContext(), 25), 0, DisplayManager.getPctX(getApplicationContext(), 25), 0);
        for (Button btn : button) {
            btn.setLayoutParams(mlp);
            btn.setAllCaps(false);
            btn.setTextSize(40 * DisplayManager.getScaleSize(getApplicationContext()));
        }
        button[0].setText("a > b");
        button[1].setText("Day");
        button[2].setText("");
    }
}

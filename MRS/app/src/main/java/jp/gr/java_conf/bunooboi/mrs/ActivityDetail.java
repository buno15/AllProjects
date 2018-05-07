package jp.gr.java_conf.bunooboi.mrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bunooboi on 2016/10/27.
 */

public class ActivityDetail extends AppCompatActivity {
    TextView textView;
    static Memory memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(memory.title);

        textView = (TextView) findViewById(R.id.textView);
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        mlp.setMargins(DisplayManager.getPctX(getApplicationContext(), 19), 0, 0, 0);
        textView.setLayoutParams(mlp);
        textView.setTextSize(30 * DisplayManager.getScaleSize(getApplicationContext()));
        textView.setText("１週間後：" + memory.getDateStr(0) + "\n１ヶ月後：" + memory
                .getDateStr(1) + "\n３か月後：" + memory.getDateStr(2) + "\n６か月後：" + memory.getDateStr(3));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), ActivityList.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

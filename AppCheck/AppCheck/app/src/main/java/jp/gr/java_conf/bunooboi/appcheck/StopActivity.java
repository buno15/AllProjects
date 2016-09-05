package jp.gr.java_conf.bunooboi.appcheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by hiro on 2016/09/05.
 */
public class StopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop);

        TextView textview = (TextView) findViewById(R.id.textview);
        textview.setText("禁止アプリを起動中");
        textview.setTextSize(40 * MainActivity.getScaleSize(getApplicationContext()));
    }
}

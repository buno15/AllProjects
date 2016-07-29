package jp.gr.java_conf.bunooboi.TemporaryStorage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hiro on 2016/07/29.
 */
public class Question extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.question);

        final EditText edittext = (EditText) findViewById(R.id.edittext);
        edittext.setTextSize(30 * Start.getScaleSize(getApplicationContext()));

        Button button = (Button) findViewById(R.id.button);
        button.setText("Answer");
        button.setTextSize(24 * Start.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer.answer = edittext.getText().toString();
                startActivity(new Intent(getApplicationContext(), Answer.class));
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

package jp.gr.java_conf.bunooboi.mydic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hiro on 2016/08/04.
 */
public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        TextView textview1 = (TextView) findViewById(R.id.textview1);
        TextView textview2 = (TextView) findViewById(R.id.textview2);
        TextView textview3 = (TextView) findViewById(R.id.textview3);
        TextView textview4 = (TextView) findViewById(R.id.textview4);
        TextView textview5 = (TextView) findViewById(R.id.textview5);
        textview1.setText("音声スピード");
        textview2.setText("音声ピッチ");
        textview3.setText("PC版の画面を使用");
        textview4.setText("オフラインでの音声認識");
        textview5.setText("通知へのアクセス");
        textview1.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        textview2.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        textview3.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        textview4.setTextSize(20 * Main.getScaleSize(getApplicationContext()));

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        button1.setText("データベースを更新");
        button2.setText("データベースを編集");
        button1.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button2.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sentences.init();
                Toast.makeText(getApplicationContext(), "更新しました", Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), List.class));
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.add("遅い");
        adapter1.add("普通");
        adapter1.add("速い");
        adapter2.add("低い");
        adapter2.add("普通");
        adapter2.add("高い");
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        jp.gr.java_conf.bunooboi.mydic.Values.setPitch(0.5f);
                        break;
                    case 1:
                        jp.gr.java_conf.bunooboi.mydic.Values.setPitch(1.0f);
                        break;
                    case 2:
                        jp.gr.java_conf.bunooboi.mydic.Values.setPitch(1.5f);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Values.setSpeechrate(0.5f);
                        break;
                    case 1:
                        Values.setSpeechrate(1.0f);
                        break;
                    case 2:
                        Values.setSpeechrate(1.5f);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        Switch switch2 = (Switch) findViewById(R.id.switch2);
        Switch switch3 = (Switch) findViewById(R.id.switch3);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Values.setDisplay(true);
                } else {
                    Values.setDisplay(false);
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Values.setRecognition(true);
                } else {
                    Values.setRecognition(false);
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Values.setNotification(true);
                } else {
                    Values.setNotification(false);
                }
            }
        });
        if (Values.pitch == 0.5f) {
            spinner1.setSelection(0);
        } else if (jp.gr.java_conf.bunooboi.mydic.Values.pitch == 1.0f) {
            spinner1.setSelection(1);
        } else if (jp.gr.java_conf.bunooboi.mydic.Values.pitch == 1.5f) {
            spinner1.setSelection(2);
        }
        if (jp.gr.java_conf.bunooboi.mydic.Values.speechrate == 0.5f) {
            spinner2.setSelection(0);
        } else if (jp.gr.java_conf.bunooboi.mydic.Values.speechrate == 1.0f) {
            spinner2.setSelection(1);
        } else if (jp.gr.java_conf.bunooboi.mydic.Values.speechrate == 1.5f) {
            spinner2.setSelection(2);
        }
        if (jp.gr.java_conf.bunooboi.mydic.Values.display) {
            switch1.setChecked(true);
        } else {
            switch1.setChecked(false);
        }
        if (jp.gr.java_conf.bunooboi.mydic.Values.recognition) {
            switch2.setChecked(true);
        } else {
            switch2.setChecked(false);
        }
        if (Values.notification) {
            switch3.setChecked(true);
        } else {
            switch3.setChecked(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                startActivity(new Intent(getApplicationContext(), Main.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Main.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

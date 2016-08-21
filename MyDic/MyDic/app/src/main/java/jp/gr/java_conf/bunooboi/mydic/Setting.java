package jp.gr.java_conf.bunooboi.mydic;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.SeekBar;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("設定");
        setContentView(R.layout.setting);

        final TextView textview[] = new TextView[7];
        textview[0] = (TextView) findViewById(R.id.textview1);
        textview[1] = (TextView) findViewById(R.id.textview2);
        textview[2] = (TextView) findViewById(R.id.textview3);
        textview[3] = (TextView) findViewById(R.id.textview4);
        textview[4] = (TextView) findViewById(R.id.textview5);
        textview[5] = (TextView) findViewById(R.id.textview6);
        textview[6] = (TextView) findViewById(R.id.textview7);
        textview[0].setText("音声スピード");
        textview[1].setText("音声ピッチ");
        textview[2].setText("音声検索時間");
        textview[3].setText("");
        textview[4].setText("画面を広げる");
        textview[5].setText("オフラインでの音声認識");
        textview[6].setText("通知へのアクセス");
        for (TextView text : textview)
            text.setTextSize(20 * Main.getScaleSize(getApplicationContext()));

        final SeekBar seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textview[3].setText("音量：" + seekbar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Values.setVolume((float) seekbar.getProgress() / 10);
            }
        });

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
                startActivity(new Intent(getApplicationContext(), ListEditConfig.class));
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.add("遅い");
        adapter1.add("普通");
        adapter1.add("速い");
        adapter2.add("低い");
        adapter2.add("普通");
        adapter2.add("高い");
        adapter3.add("短い");
        adapter3.add("普通");
        adapter3.add("長い");
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
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Values.setTime(3000);
                        break;
                    case 1:
                        Values.setTime(4000);
                        break;
                    case 2:
                        Values.setTime(6000);
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
        if (Values.time == 3000) {
            spinner3.setSelection(0);
        } else if (Values.time == 4000) {
            spinner3.setSelection(1);
        } else if (Values.time == 6000) {
            spinner3.setSelection(2);
        }
        seekbar.setProgress((int) (Values.volume * 10));
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
        textview[3].setText("音量：" + seekbar.getProgress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base, menu);
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

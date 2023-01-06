package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_TImesOfDay extends Activity {
	EditText edittext1;
	EditText edittext2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.setting_timesofday);
		setTitle("時刻設定");

		edittext1 = (EditText) findViewById(R.id.edittext1);// 時間テキスト
		edittext1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));

		edittext2 = (EditText) findViewById(R.id.edittext2);// 分テキスト
		edittext2.setTextSize(16 * Main.setScaleSize(getApplicationContext()));

		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(14 * Main.setScaleSize(getApplicationContext()));
		textview1.setText("設定時刻：" + Values.hour + "時" + Values.minute + "分");
		textview1.setTextColor(Color.BLACK);

		TextView textview2 = (TextView) findViewById(R.id.textview2);
		textview2.setTextSize(10 * Main.setScaleSize(getApplicationContext()));
		textview2.setText("※24時間表記、24時は0時と入力");
		textview2.setTextColor(Color.BLACK);

		Button button1 = (Button) findViewById(R.id.button1);// 決定
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("決定");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Values.hour = Integer.parseInt(edittext1.getText().toString());
				Values.minute = Integer.parseInt(edittext2.getText().toString());
				if (Values.hour >= 24 || Values.minute >= 60) {
					reset();
				} else {
					IO.hour(true);
					IO.minute(true);
					Toast.makeText(getApplicationContext(), Values.hour + "時" + Values.minute + "分に設定しました", Toast.LENGTH_SHORT).show();
					startActivity(new Intent(getApplicationContext(), Main.class));
					finish();
				}
			}
		});

		Button button2 = (Button) findViewById(R.id.button2);// 戻る
		button2.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button2.setText("戻る");
		button2.setTextColor(Color.WHITE);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Main.class));
				finish();
			}
		});
	}

	void reset() {
		edittext1.setText("");
		edittext2.setText("");
		edittext1.requestFocus();
		Values.hour = 0;
		Values.minute = 0;
		Toast.makeText(getApplicationContext(), "正しく入力してください", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(getApplicationContext(), Main.class));
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}

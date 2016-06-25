package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_TImesOfDay extends Activity {
	
	public static int zikan;
	public static int hunn;
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
		
		Button button1 = (Button) findViewById(R.id.button1);// 決定
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("決定");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					zikan = Integer.parseInt(edittext1.getText().toString());
					hunn = Integer.parseInt(edittext2.getText().toString());
					if (zikan >= 24) {
						edittext1.setText("");
						edittext2.setText("");
						edittext1.requestFocus();
						zikan = 0;
						Toast.makeText(getApplicationContext(), "正しく入力してください", Toast.LENGTH_SHORT).show();
					} else if (hunn >= 60) {
						edittext1.setText("");
						edittext2.setText("");
						edittext1.requestFocus();
						hunn = 0;
						Toast.makeText(getApplicationContext(), "正しく入力してください", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), zikan + "時" + hunn + "分に設定しました", Toast.LENGTH_SHORT).show();
						savezikan();
						savehunn();
						startActivity(new Intent(getApplicationContext(), Main.class));
						finish();
					}
				} catch (Exception e) {
					edittext1.setText("");
					edittext2.setText("");
					edittext1.requestFocus();
					Toast.makeText(getApplicationContext(), "正しく入力してください", Toast.LENGTH_SHORT).show();
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
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(14 * Main.setScaleSize(getApplicationContext()));
		textview1.setText("設定時刻:" + zikan + "時" + hunn + "分");
		textview1.setTextColor(Color.BLACK);
		
		TextView textview2 = (TextView) findViewById(R.id.textview2);
		textview2.setTextSize(10 * Main.setScaleSize(getApplicationContext()));
		textview2.setText("※24時間表記、24時は0時と入力");
		textview2.setTextColor(Color.BLACK);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(getApplicationContext(), Main.class));
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void savezikan() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("Savezikan", zikan);
		editor.commit();
	}
	
	private void savehunn() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("Savehunn", hunn);
		editor.commit();
	}
}

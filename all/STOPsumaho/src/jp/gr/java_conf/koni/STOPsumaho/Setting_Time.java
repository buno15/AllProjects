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
import android.widget.TextView;
import android.widget.Toast;

public class Setting_Time extends Activity {
	
	public static long ZIKANTIME;
	public static long ZIKANHYOUZITIME;
	public static String ZIKANZI;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.setting_time);
		setTitle("時間設定");
		
		Button button1 = (Button) findViewById(R.id.button1);// 1分
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("1分");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setClick(0);
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);// 45分
		button2.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button2.setText("45分");
		button2.setTextColor(Color.WHITE);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setClick(1);
			}
		});
		Button button3 = (Button) findViewById(R.id.button3);// 90分
		button3.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button3.setText("90分");
		button3.setTextColor(Color.WHITE);
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setClick(2);
			}
		});
		Button button4 = (Button) findViewById(R.id.button4);// 1時間
		button4.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button4.setText("1時間");
		button4.setTextColor(Color.WHITE);
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setClick(3);
			}
		});
		Button button5 = (Button) findViewById(R.id.button5);// 5時間
		button5.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button5.setText("5時間");
		button5.setTextColor(Color.WHITE);
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setClick(4);
			}
		});
		Button button6 = (Button) findViewById(R.id.button6);// 10時間
		button6.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button6.setText("10時間");
		button6.setTextColor(Color.WHITE);
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setClick(5);
			}
		});
		Button button7 = (Button) findViewById(R.id.button7);// 決定
		button7.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button7.setText("決定");
		button7.setTextColor(Color.WHITE);
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				savezikantime();
				savezikanhyouzitime();
				savezikanzi();
				Toast.makeText(getApplicationContext(), ZIKANHYOUZITIME + ZIKANZI + "に設定しました", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(getApplicationContext(), Main.class));
				finish();
			}
		});
		
		Button button8 = (Button) findViewById(R.id.button8);// 戻る
		button8.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button8.setText("戻る");
		button8.setTextColor(Color.WHITE);
		button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Main.class));
				finish();
			}
		});
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		textview1.setText("設定時間:" + ZIKANHYOUZITIME + ZIKANZI);
		textview1.setTextColor(Color.BLACK);
		
	}
	
	void setClick(int id) {
		switch (id) {
			case 0:
				ZIKANHYOUZITIME = 1;
				ZIKANTIME = 60;
				ZIKANZI = "分";
				break;
			case 1:
				ZIKANHYOUZITIME = 45;
				ZIKANTIME = 2700;
				ZIKANZI = "分";
				break;
			case 2:
				ZIKANHYOUZITIME = 90;
				ZIKANTIME = 5400;
				ZIKANZI = "分";
				break;
			case 3:
				ZIKANHYOUZITIME = 1;
				ZIKANTIME = 3600;
				ZIKANZI = "時間";
				break;
			case 4:
				ZIKANHYOUZITIME = 5;
				ZIKANTIME = 18000;
				ZIKANZI = "時間";
				break;
			case 5:
				ZIKANHYOUZITIME = 10;
				ZIKANTIME = 36000;
				ZIKANZI = "時間";
				break;
		}
		Toast.makeText(getApplicationContext(), ZIKANHYOUZITIME + ZIKANZI, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(getApplicationContext(), Main.class));
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void savezikantime() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = sp.edit();
		editor.putLong("Savezikantime", ZIKANTIME);
		editor.commit();
	}
	
	private void savezikanhyouzitime() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = sp.edit();
		editor.putLong("Savezikanhyouzitime", ZIKANHYOUZITIME);
		editor.commit();
	}
	
	private void savezikanzi() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("Savezikanzi", ZIKANZI);
		editor.commit();
	}
}

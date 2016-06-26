package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Select_Time extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.select_time);
		setTitle("時間で停止");
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(14 * Main.setScaleSize(getApplicationContext()));
		textview1.setText("※警告※");
		textview1.setTextColor(Color.BLACK);
		
		TextView textview2 = (TextView) findViewById(R.id.textview2);
		textview2.setTextSize(9 * Main.setScaleSize(getApplicationContext()));
		textview2.setText("\n指定した時間");
		textview2.setTextColor(Color.BLACK);
		
		TextView textview3 = (TextView) findViewById(R.id.textview3);
		textview3.setTextSize(9 * Main.setScaleSize(getApplicationContext()));
		textview3.setText("スマートフォンの");
		textview3.setTextColor(Color.BLACK);
		
		TextView textview4 = (TextView) findViewById(R.id.textview4);
		textview4.setTextSize(9 * Main.setScaleSize(getApplicationContext()));
		textview4.setText("機能を停止します");
		textview4.setTextColor(Color.BLACK);
		
		TextView textview5 = (TextView) findViewById(R.id.textview5);
		textview5.setTextSize(9 * Main.setScaleSize(getApplicationContext()));
		textview5.setText("よろしいですか?\n");
		textview5.setTextColor(Color.BLACK);
		
		TextView textview6 = (TextView) findViewById(R.id.textview6);
		textview6.setTextSize(12 * Main.setScaleSize(getApplicationContext()));
		textview6.setText("指定時間:" + Setting_Time.displaytime + Setting_Time.timeword);
		textview6.setTextColor(Color.BLACK);
		
		Button button1 = (Button) findViewById(R.id.button1);// はい
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("はい");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Main.releasecount != 0) {
					Toast.makeText(getApplicationContext(), "ロックします", Toast.LENGTH_SHORT).show();
					Stop_Time.startTime = System.currentTimeMillis();
					startActivity(new Intent(getApplicationContext(), Stop_Time.class));
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "強制解除のためのBACKキー回数を指定してください", Toast.LENGTH_SHORT).show();
				}
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);// いいえ
		button2.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button2.setText("いいえ");
		button2.setTextColor(Color.WHITE);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Main.class));
				finish();
			}
		});
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

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

public class Setting_Pass extends Activity {
	public static int pass;
	EditText edittext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.setting_pass);
		setTitle("パスワード設定");
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(10 * Main.setScaleSize(getApplicationContext()));
		textview1.setText("任意の数字を入力してください");
		textview1.setTextColor(Color.BLACK);
		
		edittext = (EditText) findViewById(R.id.edittext1);
		edittext.setTextSize(20 * Main.setScaleSize(getApplicationContext()));
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("決定");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					pass = Integer.parseInt(edittext.getText().toString());
					int keta = Integer.toString(pass).length();
					if (keta == 4) {
						savep();
						Toast.makeText(getApplicationContext(), String.valueOf(pass) + "に設定しました。", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(getApplicationContext(), Main.class));
						finish();
					} else {
						Toast.makeText(getApplicationContext(), "4桁で設定してください", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					edittext.setText("");
					edittext.requestFocus();
					Toast.makeText(getApplicationContext(), "正しく入力してください", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(getApplicationContext(), Main.class));
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void savep() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("Savep", pass);
		editor.commit();
	}
}

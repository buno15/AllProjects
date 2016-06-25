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

public class ConfirmationPass extends Activity {
	EditText edittext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.confirmationpass);
		setTitle("パスワード確認");
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(10 * Main.setScaleSize(getApplicationContext()));
		textview1.setText("パスワードを入力してください");
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
					if (Integer.parseInt(edittext.getText().toString()) == Setting_Pass.pass) {
						startActivity(new Intent(getApplicationContext(), Setting_Pass.class));
						finish();
					} else {
						edittext.setText("");
						edittext.requestFocus();
						Toast.makeText(getApplicationContext(), "パスワードが違います", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
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

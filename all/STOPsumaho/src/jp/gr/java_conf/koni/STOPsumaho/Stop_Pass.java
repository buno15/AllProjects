package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Stop_Pass extends Activity {
	EditText edittext;
	public int backcount = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.stop_pass);
		
		Main.passcount = 1;
		savepasucount();
		
		edittext = (EditText) findViewById(R.id.edittext1);
		edittext.setTextSize(20 * Main.setScaleSize(getApplicationContext()));
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setText("停止中");
		textview1.setTextSize(30 * Main.setScaleSize(getApplicationContext()));
		textview1.setTextColor(Color.RED);
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("決定");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (Integer.parseInt(edittext.getText().toString()) == Setting_Pass.pass) {
						Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
						vibrator.vibrate(1000);
						Toast.makeText(getApplicationContext(), "解除します", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(getApplicationContext(), Main.class));
						Main.passcount = 0;
						savepasucount();
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
		button2.setText("電話");
		button1.setTextColor(Color.WHITE);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent i = new Intent(Intent.ACTION_DIAL);
					startActivity(i);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "電話がありません", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			backcount += 1;
			if (backcount == Main.releasecount) {
				Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
				vibrator.vibrate(1000);
				Toast.makeText(getApplicationContext(), "強制解除します", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(getApplicationContext(), Main.class));
				Main.passcount = 0;
				savepasucount();
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void savepasucount() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		sp.edit().putInt("Savepasucount", Main.passcount).commit();
	}
}

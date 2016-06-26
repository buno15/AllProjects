package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Stop_Pass extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.stop_pass);

		Values.passtf = true;
		IO.passtf(true);

		TextView textview = (TextView) findViewById(R.id.textview);
		textview.setText("停止中");
		textview.setTextSize(20 * Main.setScaleSize(getApplicationContext()));
		textview.setTextColor(Color.RED);
		
		final EditText edittext = (EditText) findViewById(R.id.edittext);
		edittext.setTextSize(20 * Main.setScaleSize(getApplicationContext()));

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("決定");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (Integer.parseInt(edittext.getText().toString()) == Values.pass) {
						Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
						vibrator.vibrate(1000);
						Toast.makeText(getApplicationContext(), "解除します", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(getApplicationContext(), Main.class));
						Values.passtf = false;
						IO.passtf(true);
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
				Intent i = new Intent(Intent.ACTION_DIAL);
				startActivity(i);
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Values.stopreleasecount += 1;
			if (Values.stopreleasecount == Values.releasecount) {
				Values.stopreleasecount = 0;
				Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
				vibrator.vibrate(1000);
				Toast.makeText(getApplicationContext(), "強制解除します", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(getApplicationContext(), Main.class));
				Values.passtf = false;
				IO.passtf(true);
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

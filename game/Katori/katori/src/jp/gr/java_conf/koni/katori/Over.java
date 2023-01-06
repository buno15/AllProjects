package jp.gr.java_conf.koni.katori;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Over extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.over);

		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setText("GAME OVER");
		textview1.setTextSize(20 * Play.setScaleSize(getApplicationContext()));

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Sound.hitSE();
				Preparation.nakaCount = 0;
				Intent i = new Intent(getApplicationContext(), Preparation.class);
				startActivity(i);
				finish();
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Sound.hitSE();
				Intent i = new Intent(getApplicationContext(), Start.class);
				startActivity(i);
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent i = new Intent(getApplicationContext(), Start.class);
			startActivity(i);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}

package jp.gr.java_conf.koni.warasibe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.over);
		MoveEvent.tf = 0;
		savetf();

		BattleEvent.bosstf = false;
		CORE.boss = false;
		CORE.moneyclear = false;

		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setText("GameOver");
		textview1.setTextColor(Color.RED);
		textview1.setTextSize(26 * CORE.setScaleSize(getApplicationContext()));
		textview1.setShadowLayer(5, 0, 0, Color.BLACK);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setText("メイン");
		button1.setTextSize(14 * CORE.setScaleSize(getApplicationContext()));
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), GameStart.class);
				startActivity(i);
				finish();
			}
		});
	}

	public void savetf() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("savetf", MoveEvent.tf);
		editor.commit();
	}
}

package jp.gr.java_conf.koni.warasibe;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class GameClear extends Activity {
	Timer	t;
	Handler	handle	= new Handler();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.clear);
		MoveEvent.tf = 0;
		savetf();
		SOUND.Clearinit(getApplicationContext());

		BattleEvent.bosstf = false;
		CORE.boss = false;
		CORE.moneyclear = false;

		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setText("Congratulations");
		textview1.setTextColor(Color.parseColor("#aaaa00"));
		textview1.setTextSize(20 * CORE.setScaleSize(getApplicationContext()));
		textview1.setShadowLayer(5, 0, 0, Color.BLACK);

		t = new Timer();
		t.schedule(new ClearTimer(), 10000);
	}

	class ClearTimer extends TimerTask {
		@Override
		public void run() {
			handle.post(new Runnable() {
				@Override
				public void run() {
					Intent i = new Intent(getApplicationContext(), GameStart.class);
					startActivity(i);
					finish();
				}
			});
		}
	}

	public void savetf() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("savetf", MoveEvent.tf);
		editor.commit();
	}

	@Override
	public void onResume() {
		super.onResume();
		SOUND.soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if (0 == status) {
					SOUND.recovery();
				}
			}
		});
	}
}

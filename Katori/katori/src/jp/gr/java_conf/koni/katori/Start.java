package jp.gr.java_conf.koni.katori;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Start extends Activity {
	boolean startSEtf = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.start);

		Sound.init(this);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Preparation.nakaCount = 0;
				Sound.hitSE();
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
				Intent i = new Intent(getApplicationContext(), Help.class);
				startActivity(i);
				finish();
			}
		});

		Sound.soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if (startSEtf) {
					Sound.startSE();
					startSEtf = false;
				}
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Sound.soundPool.stop(Sound.startSEs);
	}
}

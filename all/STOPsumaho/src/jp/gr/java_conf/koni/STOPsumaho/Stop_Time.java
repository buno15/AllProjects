package jp.gr.java_conf.koni.STOPsumaho;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Stop_Time extends Activity {
	public static long startTime; // 開始時間
	public static long remainedTime; // 残り時間
	Timer timer;
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.stop_time);

		TextView textview1 = (TextView) findViewById(R.id.textview1);
		final TextView textview2 = (TextView) findViewById(R.id.textview2);
		textview1.setTextSize(30 * Main.setScaleSize(getApplicationContext()));
		textview2.setTextSize(20 * Main.setScaleSize(getApplicationContext()));
		textview1.setTextColor(Color.RED);
		textview2.setTextColor(Color.BLACK);
		textview1.setText("停止中");
		textview2.setText(remainedTime / 3600 + "時間" + remainedTime % 3600 / 60 + "分" + remainedTime % 60 + "秒");

		PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
		WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakelockTag");
		wl.acquire();
		timer = new Timer(false);
		timer.schedule(new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						remainedTime = Values.sstime - (System.currentTimeMillis() - startTime) / 1000;
						textview2.setText(remainedTime / 3600 + "時間" + remainedTime % 3600 / 60 + "分" + remainedTime % 60 + "秒");
						if (remainedTime <= 0) {
							Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
							vibrator.vibrate(1000);

							Toast.makeText(getApplicationContext(), "解除します", Toast.LENGTH_SHORT).show();
							startActivity(new Intent(getApplicationContext(), Main.class));
							finish();
							timer.cancel();
						}
					}
				});
			}
		}, 0, 150);
		wl.release();

		Button button = (Button) findViewById(R.id.button);
		button.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button.setText("電話");
		button.setTextColor(Color.WHITE);
		button.setOnClickListener(new OnClickListener() {
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
				finish();
				timer.cancel();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

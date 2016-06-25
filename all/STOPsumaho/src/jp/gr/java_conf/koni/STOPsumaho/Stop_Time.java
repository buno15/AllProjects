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
	
	public static long startTime;// 開始時間
	public static long remainedTime;// 残り時間
	public final static long MSEC = 150;
	public int modoru = 0;
	TextView textview2;
	Timer timer1;
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
		textview2 = (TextView) findViewById(R.id.textview2);
		textview1.setTextSize(30 * Main.setScaleSize(getApplicationContext()));
		textview2.setTextSize(20 * Main.setScaleSize(getApplicationContext()));
		textview1.setTextColor(Color.RED);
		textview2.setTextColor(Color.BLACK);
		textview1.setText("停止中");
		textview2.setText("残り:" + Setting_Time.ZIKANTIME + "秒");
		
		PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
		WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakelockTag");
		wl.acquire();
		timer1 = new Timer(false);
		timer1.schedule(new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						remainedTime = Setting_Time.ZIKANTIME - (System.currentTimeMillis() - startTime) / 1000;
						textview2.setText("残り:" + remainedTime + "秒");
						if (remainedTime <= 0) {
							Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
							vibrator.vibrate(1000);
							
							Toast.makeText(getApplicationContext(), "解除します", Toast.LENGTH_SHORT).show();
							startActivity(new Intent(getApplicationContext(), Main.class));
							finish();
							timer1.cancel();
						}
					}
				});
			}
		}, 0, MSEC);
		wl.release();
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("電話");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new OnClickListener() {
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
			modoru += 1;
			if (modoru == Main.releasecount) {
				Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
				vibrator.vibrate(1000);
				Toast.makeText(getApplicationContext(), "強制解除します", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(getApplicationContext(), Main.class));
				finish();
				timer1.cancel();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

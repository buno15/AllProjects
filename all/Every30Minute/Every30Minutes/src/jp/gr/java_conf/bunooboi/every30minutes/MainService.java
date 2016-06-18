package jp.gr.java_conf.bunooboi.every30minutes;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MainService extends Service {
	Timer		t;
	Handler		handler	= new Handler();
	SoundPool	soundpool;
	int			tk		= 0;
	boolean		maintf	= true;

	int			start;
	int			end;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		SoundPool.Builder builder = new SoundPool.Builder();
		soundpool = builder.build();
		start = soundpool.load(getApplicationContext(), R.raw.start, 1);
		end = soundpool.load(getApplicationContext(), R.raw.end, 1);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (maintf) {
							if (++tk % 30 == 0) {
								soundpool.play(start, 1.0f, 1.0f, 0, 0, 1);
								Toast.makeText(getApplicationContext(), "30分経過", Toast.LENGTH_SHORT).show();
								maintf = false;
							}
						} else {
							if (++tk % 5 == 0) {
								soundpool.play(end, 1.0f, 1.0f, 0, 0, 1);
								Toast.makeText(getApplicationContext(), "5分経過", Toast.LENGTH_SHORT).show();
								maintf = true;
							}
						}
					}
				});
			}
		}, 0, 600000);
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (t != null) {
			t.cancel();
			t = null;
		}
	}
}

package jp.gr.java_conf.bunooboi.every30minutes;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MainService extends Service {
	Timer					t;
	Handler					handler	= new Handler();
	SoundPool				soundpool;

	Notification.Builder	nb;
	NotificationManager		manager;

	int						tk		= 0;
	boolean					maintf	= true;

	int						start;
	int						end;

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

		nb = new Notification.Builder(this);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		nb.setSmallIcon(R.drawable.ic_launcher);
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
								nb.setContentTitle("30分経過");
								nb.setContentText("体を動かしましょう");
								manager.notify(1, nb.build());
							}
						} else {
							if (++tk % 5 == 0) {
								soundpool.play(end, 1.0f, 1.0f, 0, 0, 1);
								Toast.makeText(getApplicationContext(), "5分経過", Toast.LENGTH_SHORT).show();
								maintf = true;
								nb.setContentTitle("5分経過");
								nb.setContentText("休憩終了");
								manager.notify(1, nb.build());
							}
						}
					}
				});
			}
		}, 0, 60000);
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

package jp.gr.java_conf.koni.warasibe;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CORE extends Activity {
	public static LinearLayout	playbg, buttonLayout;
	public static TextView		console, status;
	public static Button		button1, button2;
	int							enemyhp, enemypower, enemydamage = 0;
	int							random1, random2, random3;
	int							rn, margin;
	boolean						tf1, tf2, tf3;
	static boolean				nekutaru, boss, moneyclear;
	String						item, enemyname;
	Intent						i;
	Random						rnd		= new Random();
	Timer						t;
	Handler						handle	= new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.core);

		if (PLAYER.getdamage() <= 0) {
			rn = 1;
			if (t != null) {
				t.cancel();
				t = null;
			}
			if (t == null) {
				t = new Timer();
				t.schedule(new CORETimer(), 2000);
			}
		}
		if (PLAYER.gethp() >= 100)
			PLAYER.sethp(100);
		if ((PLAYER.getdamage() - PLAYER.getadd(0)) >= 100)
			PLAYER.setdamage(100 + PLAYER.getadd(0));
		if ((PLAYER.getpower() - PLAYER.getadd(1)) >= 100) {
			PLAYER.setpower(100 + PLAYER.getadd(1));
			boss = true;
		}
		if ((PLAYER.getinsight() - PLAYER.getadd(2)) >= 100)
			PLAYER.setinsight(100 + PLAYER.getadd(2));
		if (PLAYER.getmoney() >= 10000) {
			moneyclear = true;
		}
		playbg = (LinearLayout) findViewById(R.id.playbg);
		buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);

		console = (TextView) findViewById(R.id.console);
		console.setTextSize(8 * setScaleSize(getApplicationContext()));
		console.setTextColor(Color.BLACK);

		status = (TextView) findViewById(R.id.status);
		status.setTextSize(7 * setScaleSize(getApplicationContext()));
		status.setTextColor(Color.WHITE);
		status.setShadowLayer(5, 0, 0, Color.BLACK);

		button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(8 * setScaleSize(getApplicationContext()));
		button2 = (Button) findViewById(R.id.button2);
		button2.setTextSize(8 * setScaleSize(getApplicationContext()));

		int marginmoto = 5;
		float scale = getResources().getDisplayMetrics().density;
		margin = (int) (marginmoto * scale + 0.5f);
	}

	public void moveevent() {
		if (PLAYER.gethp() > (PLAYER.getdamage() - PLAYER.getadd(0)))
			PLAYER.setdamage(PLAYER.getdamage() + 1);
		i = new Intent(getApplicationContext(), MoveEvent.class);
		startActivity(i);
		finish();
	}

	public void status() {
		status.setText(Html.fromHtml("体力：<font color=\"#00cc00\">" + (PLAYER.getdamage() - PLAYER.getadd(0)) + "</font>" + PLAYER.addadd[0]
				+ "/<font color=\"#00cc00\">" + PLAYER.gethp() + "</font>　力：<font color=\"#ff0033\">" + (PLAYER.getpower() - PLAYER.getadd(1)) + "</font>"
				+ PLAYER.addadd[1] + "　洞察力：<font color=\"#00a0dd\">" + (PLAYER.getinsight() - PLAYER.getadd(2)) + "</font>" + PLAYER.addadd[2]
				+ "<br/>所持金：<font color=\"#aaaa00\">" + PLAYER.getmoney() + "</font>GSB" + "<br/>アイテム：<font color=\"#a67e4e\">" + PLAYER.getitem()
				+ "</font>"));
	}

	public void buttonoff() {
		button1.setText("");
		button2.setText("");
		button1.setEnabled(false);
		button2.setEnabled(false);
	}

	public void exitevent() {
		buttonoff();
		if (t != null) {
			t.cancel();
			t = null;
		}
		if (t == null) {
			rn = 0;
			t = new Timer();
			t.schedule(new CORETimer(), 2000);
		}
	}

	public boolean statusme(int meb, int met) {
		if ((PLAYER.gethp() >= meb && PLAYER.gethp() <= met) || (PLAYER.getpower() >= meb && PLAYER.getpower() <= met)
				|| (PLAYER.getinsight() >= meb && PLAYER.getinsight() <= met)) {
			return true;
		} else
			return false;
	}

	class CORETimer extends TimerTask {
		@Override
		public void run() {
			handle.post(new Runnable() {
				@Override
				public void run() {
					if (rn == 0) {// 移動
						moveevent();
						button1.setEnabled(true);
						button2.setEnabled(true);
						t.cancel();
						t = null;
					} else if (rn == 1) {// 死
						if (t != null) {
							t.cancel();
							t = null;
						}
						if ((PLAYER.getdamage() - PLAYER.getadd(0)) <= 0)
							PLAYER.setdamage(0 + PLAYER.getadd(0));
						status();
						SOUND.stopBGM1();
						SOUND.stopBGM2();
						SOUND.death();
						console.setText("あなたは死んだ");
						if (t == null) {
							rn = 2;
							t = new Timer();
							t.schedule(new CORETimer(), 3000);
						}
					} else if (rn == 2) {// ゲームオーバー
						i = new Intent(getApplicationContext(), GameOver.class);
						startActivity(i);
						finish();
					}
				}
			});
		}
	}

	public static float setScaleSize(Context context) {
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
		float width = 0;
		float scale = 0;

		WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();

		Point size = new Point();
		disp.getSize(size);
		width = size.x;

		scale = (float) width / (float) bm.getWidth();

		return scale;
	}
}
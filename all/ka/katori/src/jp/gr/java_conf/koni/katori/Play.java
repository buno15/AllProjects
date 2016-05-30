package jp.gr.java_conf.koni.katori;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class Play extends Activity {
	SurfaceHolder				holder;
	static Canvas				canvas;
	static Resources			res;
	ScheduledExecutorService	ses;

	static int					stage;						// ステージ設定
	static int					killCount;					// 殺した蚊の数
	int							killGoal;					// 殺す蚊の数
	static int					disp_w;						// 画面幅
	static int					disp_h;						// 画面高さ
	static int					touch_x;					// タッチ座標x
	static int					touch_y;					// タッチ座標y
	static int					ui_h;						// UIの高さ

	int							meter_h;					// メーターの高さ
	int							meter_l;					// メーターの左座標
	static int					meter_m_r1;					// メーターの右座標1（余白あり）
	static int					meter_m_r2;					// メーターの右座標2（余白あり）
	static int					meter_m_r3;					// メーターの右座標3（余白あり）

	static int					skin_w;						// 肌幅

	static int					kaCount;					// 蚊の数

	int							mode;						// finishモード

	final int					OVER		= 1;
	final int					PREPARATION	= 2;
	final int					FIN			= 3;

	static long					t1;
	static long					t2;
	static long					t3;

	static int					tk;
	boolean						powerdownSE	= true;			// 吸われている効果音

	static boolean				boss		= false;		// ボス出すかどうか
	int							bossclick	= 0;			// ボスタッチ回数

	Bitmap						back;						// 背景
	Bitmap						number;						// 番号
	Bitmap						skin[]		= new Bitmap[2];// 肌
	Bitmap						meter;						// メーター

	Paint						ui_p		= new Paint();	// UIのペイント
	Paint						blood_p		= new Paint();	// メーターのペイント
	static Paint				button_p	= new Paint();	// ボタンの扇のペイント
	Paint						text_p		= new Paint();	// カウンターのペイント

	float						textWidth;					// カウンター幅

	static KA					ka[]		= new KA[20];	// 全ての蚊
	static Spray				spray;						// スプレー
	Cream						cream;						// クリーム
	static Incense				incense;					// 線香

	Rect						blood;						// 血のメーター
	static int					blood_x;					// 血のメーターX
	static int					blood_vx;					// 血のメータの動き
	static int					skinframeIndex;				// 肌のアニメーション

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(new Core(this, stage));
	}

	public static float setScaleSize(Context context) {
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
		float width = 0;
		float scale = 0;

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		Point size = new Point();
		disp.getSize(size);

		width = size.x;

		scale = (float) width / (float) bm.getWidth();

		return scale;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	class Core extends SurfaceView implements SurfaceHolder.Callback {

		public Core(Context context, int id) {
			super(context);
			res = this.getContext().getResources();
			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			Display disp = wm.getDefaultDisplay();
			Point size = new Point();
			disp.getSize(size);
			disp_w = size.x;
			disp_h = size.y;

			ui_p.setColor(Color.parseColor("#f8b500"));
			ui_p.setAntiAlias(true);
			button_p.setColor(Color.RED);
			button_p.setAntiAlias(true);
			blood_p.setColor(Color.RED);
			blood_p.setAntiAlias(true);
			text_p.setColor(Color.WHITE);
			text_p.setTextSize(10 * setScaleSize(context));
			text_p.setTextAlign(Align.LEFT);
			text_p.setAntiAlias(true);
			text_p.setShadowLayer(5, 0, 0, Color.BLACK);

			skin[0] = BitmapFactory.decodeResource(res, R.drawable.skin1);
			skin[1] = BitmapFactory.decodeResource(res, R.drawable.skin2);
			skin[0] = Bitmap.createScaledBitmap(skin[0], disp_w / 6, disp_h - disp_h / 8, true);
			skin[1] = Bitmap.createScaledBitmap(skin[1], disp_w / 6, disp_h - disp_h / 8, true);
			meter = BitmapFactory.decodeResource(res, R.drawable.meter);
			meter = Bitmap.createScaledBitmap(meter, disp_w / 2, disp_h / 12, true);

			meter_h = meter.getHeight();
			meter_l = disp_w / 10;
			blood_x = meter_l;
			blood_vx = 0;
			skin_w = skin[0].getWidth();
			ui_h = disp_h / 8;
			skinframeIndex = 0;
			killCount = 0;
			boss = false;
			if (spray != null)
				spray = null;
			if (cream != null)
				cream = null;
			if (incense != null)
				incense = null;

			stage(id);

			back = Bitmap.createScaledBitmap(back, disp_w, disp_h - (ui_h), true);
			number = Bitmap.createScaledBitmap(number, disp_h / 10, disp_h / 10, true);

			textWidth = text_p.measureText("あと1000/1000");

			Sound.play();
			Sound.playfly();

			holder = getHolder();
			holder.addCallback(this);
			setFocusable(true);
			requestFocus();
		}

		public void start() {
			ses = Executors.newSingleThreadScheduledExecutor();
			ses.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					long t1 = 0;
					long t2 = 0;
					t1 = System.currentTimeMillis();
					Draw();
					tk++;
					t2 = System.currentTimeMillis();
					if (t2 - t1 < 32) {
						try {
							Thread.sleep(32 - (t2 - t1));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}, 0, 32, TimeUnit.MILLISECONDS);
		}

		public void Draw() {
			canvas = holder.lockCanvas();
			DrawUI();
			for (int i = 0; i < kaCount; i++) {
				KAmove(ka[i]);
			}
			if (spray != null) {
				spray.move();
			}
			if (cream != null) {
				cream.move();
			}
			if (incense != null) {
				incense.move();
			}
			if (killCount >= killGoal) {
				if (stage == 7) {
					boss = true;
				} else
					fin(PREPARATION);
			}
			holder.unlockCanvasAndPost(canvas);
		}

		public void DrawUI() {
			canvas.drawBitmap(back, 0, disp_h / 8, null);
			canvas.drawRect(disp_w, ui_h, 0, 0, ui_p);
			canvas.drawBitmap(number, meter_l / 2 - number.getWidth() / 2, ui_h / 2 - number.getHeight() / 2, null);
			canvas.drawRect(meter_l, (ui_h / 2) - (meter_h / 2), blood_x, (ui_h / 2) + (meter_h / 2), blood_p);
			canvas.drawBitmap(meter, meter_l, (ui_h / 2) - (meter_h / 2), null);
			canvas.drawBitmap(skin[skinframeIndex], 0, disp_h / 8, null);
			if (boss)
				canvas.drawText("あと" + bossclick + "/100", disp_w - (int) textWidth, disp_h / 6 + (int) text_p.getTextSize(), text_p);
			else
				canvas.drawText(killCount + "/" + killGoal, disp_w - (int) textWidth, disp_h / 6 + (int) text_p.getTextSize(), text_p);
			setBloodMeter();
		}

		public boolean clickKA(KA ka) {
			if (ka.ka_x - 1 <= touch_x && (ka.ka_x + ka.ka_w) + 1 >= touch_x && ka.ka_y - 1 <= touch_y && (ka.ka_y + ka.ka_h) + 1 >= touch_y) {
				return true;
			} else {
				return false;
			}
		}

		public boolean clickButton(int w, int h, int x, int y) {
			if (x <= touch_x && (x + w) >= touch_x && y <= touch_y && (y + h) >= touch_y) {
				return true;
			} else {
				return false;
			}
		}

		public void hitSpray(KA ka) {
			if (ka.ID != 5) {
				if (spray.smoke_tf) {
					if (ka.ka_x <= (disp_w / 6) * 5) {
						if (ka.dead == false) {
							if ((ka.ka_x + ka.ka_w) >= spray.smoke_x && ka.ka_x <= (spray.smoke_x + spray.smoke_w) && (ka.ka_y + ka.ka_h) >= spray.smoke_y
									&& ka.ka_y <= (spray.smoke_y + spray.smoke_h)) {
								ka.dead();
							}
						}
					}
				}
			}
		}

		public void hitCream(KA ka) {
			if (ka.ID != 5) {
				if (cream.cream_tf) {
					if (ka.dead == false) {
						if (ka.ka_x <= skin_w && ka.ka_x > skin_w / 8)
							ka.ka_x = skin_w;
						else if (ka.ka_x <= skin_w / 8)
							ka.dead();
					}
				}
			}
		}

		public void setBloodMeter() {
			int intake = 0;
			for (int i = 0; i < kaCount; i++) {
				intake += ka[i].intake();
			}
			blood_vx = intake;
			if (blood_vx <= 0) {
				blood_vx = -(disp_w / 500);
				Sound.soundPool.stop(Sound.powerdownSEs);
				powerdownSE = true;
			} else if (blood_vx > 0) {
				if (powerdownSE) {
					Sound.powerdownSE();
					powerdownSE = false;
				}
			}
			blood_x += blood_vx;
			if (blood_x < meter_l) {
				blood_x = meter_l;
			}
			if (blood_x > meter_l + meter.getWidth() - meter.getWidth() / 20) {
				blood_x = meter_l + meter.getWidth() - meter.getWidth() / 20;
				fin(OVER);
			}
		}

		public void KAmove(KA ka) {
			ka.move(Play.ka);
			if (spray != null) {
				hitSpray(ka);
			}
			if (cream != null) {
				hitCream(ka);
			}
		}

		public boolean onTouchEvent(MotionEvent me) {
			touch_x = (int) me.getX();
			touch_y = (int) me.getY();
			if (me.getAction() == MotionEvent.ACTION_DOWN) {
				boolean hit = true;
				for (int i = 0; i < kaCount; i++) {
					if (clickKA(ka[i])) {
						if (hit) {
							Sound.hitSE();
							hit = false;
						}
						if (ka[i].ID == 5) {
							bossclick++;
							if (bossclick >= 100) {
								fin(FIN);
							}
						} else
							ka[i].clickCount++;
						if (ka[i].deadCount == ka[i].clickCount)
							ka[i].dead();
					}
				}
				if (spray != null) {
					if (clickButton(spray.button_w, spray.button_h, spray.button_x, spray.button_y)) {
						if (spray.button_tf == false) {
							Sound.spraySE();
							spray.button_tf = true;
							spray.smoke_tf = true;
						}
					}
				}
				if (cream != null) {
					if (clickButton(cream.button_w, cream.button_h, cream.button_x, cream.button_y)) {
						if (cream.button_tf == false) {
							Sound.creamSE();
							cream.button_tf = true;
							skinframeIndex = 1;
							t1 = System.currentTimeMillis();
						}
					}
				}
				if (incense != null) {
					if (clickButton(incense.button_w, incense.button_h, incense.button_x, incense.button_y)) {
						if (incense.button_tf == false) {
							Sound.inccenseSE();
							incense.button_tf = true;
							incense.smoke_tf = true;
						}
					}
				}
			}
			return true;
		}

		public void stage(int id) {
			switch (id) {
			case 0:
				back = BitmapFactory.decodeResource(res, R.drawable.play1);
				number = BitmapFactory.decodeResource(res, R.drawable.number_1);
				kaCount = 2;
				killGoal = 10;
				itemContext(0);
				break;
			case 1:
				back = BitmapFactory.decodeResource(res, R.drawable.play2);
				number = BitmapFactory.decodeResource(res, R.drawable.number_2);
				kaCount = 4;
				killGoal = 20;
				itemContext(1);
				break;
			case 2:
				back = BitmapFactory.decodeResource(res, R.drawable.play3);
				number = BitmapFactory.decodeResource(res, R.drawable.number_3);
				kaCount = 6;
				killGoal = 30;
				itemContext(1);
				break;
			case 3:
				back = BitmapFactory.decodeResource(res, R.drawable.play4);
				number = BitmapFactory.decodeResource(res, R.drawable.number_4);
				kaCount = 10;
				killGoal = 50;
				itemContext(2);
				break;
			case 4:
				back = BitmapFactory.decodeResource(res, R.drawable.play5);
				number = BitmapFactory.decodeResource(res, R.drawable.number_5);
				kaCount = 12;
				killGoal = 70;
				itemContext(2);
				break;
			case 5:
				back = BitmapFactory.decodeResource(res, R.drawable.play6);
				number = BitmapFactory.decodeResource(res, R.drawable.number_6);
				kaCount = 14;
				killGoal = 100;
				itemContext(3);
				break;
			case 6:
				back = BitmapFactory.decodeResource(res, R.drawable.play7);
				number = BitmapFactory.decodeResource(res, R.drawable.number_7);
				kaCount = 18;
				killGoal = 300;
				itemContext(3);
				break;
			case 7:
				back = BitmapFactory.decodeResource(res, R.drawable.play8);
				number = BitmapFactory.decodeResource(res, R.drawable.number_8);
				kaCount = 20;
				killGoal = 500;
				itemContext(3);
				break;
			case 8:
				back = BitmapFactory.decodeResource(res, R.drawable.play8);
				number = BitmapFactory.decodeResource(res, R.drawable.number_8);
				kaCount = 20;
				killGoal = 500;
				itemContext(3);
			}
			kaContext();
		}

		public void fin(int mode) {
			ses.shutdown();
			Sound.mediaPlayer1.stop();
			Sound.mediaPlayer2.stop();
			Sound.soundPool.stop(Sound.powerdownSEs);
			switch (kaCount) {
			case 2:
				Preparation.nakaCount = 1;
				break;
			case 4:
				Preparation.nakaCount = 2;
				break;
			case 6:
				Preparation.nakaCount = 3;
				break;
			case 10:
				Preparation.nakaCount = 4;
				break;
			case 12:
				Preparation.nakaCount = 5;
				break;
			case 14:
				Preparation.nakaCount = 6;
				break;
			case 18:
				Preparation.nakaCount = 7;
				break;
			}
			Intent i = new Intent();
			switch (mode) {
			case OVER:
				Sound.overSE();
				i = new Intent(getApplicationContext(), Over.class);
				break;
			case PREPARATION:
				Sound.clapping_shortSE();
				i = new Intent(getApplicationContext(), Preparation.class);
				break;
			case FIN:
				Sound.clappingSE();
				i = new Intent(getApplicationContext(), Clear.class);
				break;
			}
			startActivity(i);
			finish();
		}

		public void kaContext() {
			for (int i = 0; i < kaCount; i++) {
				switch (i) {
				case 0:
					ka[i] = new KA(0);
					break;
				case 1:
					ka[i] = new KA(0);
					break;
				case 2:
					ka[i] = new KA(0);
					break;
				case 3:
					ka[i] = new KA(0);
					break;
				case 4:
					ka[i] = new KA(0);
					break;
				case 5:
					ka[i] = new KA(0);
					break;
				case 6:
					ka[i] = new KA(1);
					break;
				case 7:
					ka[i] = new KA(1);
					break;
				case 8:
					ka[i] = new KA(1);
					break;
				case 9:
					ka[i] = new KA(1);
					break;
				case 10:
					ka[i] = new KA(2);
					break;
				case 11:
					ka[i] = new KA(2);
					break;
				case 12:
					ka[i] = new KA(2);
					break;
				case 13:
					ka[i] = new KA(3);
					break;
				case 14:
					ka[i] = new KA(3);
					break;
				case 15:
					ka[i] = new KA(4);
					break;
				case 16:
					ka[i] = new KA(4);
					break;
				case 17:
					ka[i] = new KA(4);
					break;
				case 18:
					ka[i] = new KA(4);
					break;
				case 19:
					ka[i] = new KA(5);
					break;
				}
			}
		}

		public void itemContext(int id) {
			switch (id) {
			case 0:
				break;
			case 1:
				meter_m_r1 = meter_l + meter.getWidth() + meter_l / 2;
				spray = new Spray();
				break;
			case 2:
				meter_m_r1 = meter_l + meter.getWidth() + meter_l / 2;
				spray = new Spray();
				meter_m_r2 = meter_m_r1 + spray.button_w + meter_l / 2;
				cream = new Cream();
				break;
			case 3:
				meter_m_r1 = meter_l + meter.getWidth() + meter_l / 2;
				spray = new Spray();
				meter_m_r2 = meter_m_r1 + spray.button_w + meter_l / 2;
				cream = new Cream();
				meter_m_r3 = meter_m_r2 + cream.button_w + meter_l / 2;
				incense = new Incense();
				break;
			}
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			start();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {

		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Sound.mediaPlayer1.start();
		Sound.mediaPlayer2.start();
		if (blood_vx > 0) {
			Sound.powerdownSE();
			powerdownSE = false;
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		Sound.mediaPlayer1.pause();
		Sound.mediaPlayer2.pause();
		Sound.soundPool.stop(Sound.powerdownSEs);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

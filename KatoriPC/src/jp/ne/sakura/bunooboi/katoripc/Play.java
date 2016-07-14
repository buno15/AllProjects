package jp.ne.sakura.bunooboi.katoripc;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.w3c.dom.css.Rect;

public class Play {
	static MyPanel panel;
	BufferStrategy bstrategy;
	static Graphics g;
	Timer t;

	static int stage; // ステージ設定
	static int killCount; // 殺した蚊の数
	int killGoal; // 殺す蚊の数
	static int disp_w; // 画面幅
	static int disp_h; // 画面高さ
	static int touch_x; // タッチ座標x
	static int touch_y; // タッチ座標y
	static int ui_h; // UIの高さ

	int meter_h; // メーターの高さ
	int meter_l; // メーターの左座標
	static int meter_m_r1; // メーターの右座標1（余白あり）
	static int meter_m_r2; // メーターの右座標2（余白あり）
	static int meter_m_r3; // メーターの右座標3（余白あり）

	static int skin_w; // 肌幅

	static int kaCount; // 蚊の数

	int mode; // finishモード

	final int OVER = 1;
	final int PREPARATION = 2;
	final int FIN = 3;

	static long t1;
	static long t2;
	static long t3;

	static int tk;
	boolean powerdownSE = true; // 吸われている効果音

	static boolean boss = false; // ボス出すかどうか
	int bossclick = 0; // ボスタッチ回数

	BufferedImage back; // 背景
	BufferedImage number; // 番号
	BufferedImage skin[] = new BufferedImage[2];// 肌
	BufferedImage meter; // メーター

	float textWidth; // カウンター幅

	static KA ka[] = new KA[20]; // 全ての蚊
	static Spray spray; // スプレー
	Cream cream; // クリーム
	static Incense incense; // 線香

	Rect blood; // 血のメーター
	static int blood_x; // 血のメーターX
	static int blood_vx; // 血のメータの動き
	static int skinframeIndex; // 肌のアニメーション

	static int id;

	public Play() {
		panel = new MyPanel("");
		Main.addContent(panel);
		bstrategy = Main.getBufferStrategy();
		g = bstrategy.getDrawGraphics();
		init();

		if (t != null) {
			t.cancel();
			t = null;
		}
		t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				g = bstrategy.getDrawGraphics();
				if (bstrategy.contentsLost() == false) {
					g.translate(Main.InsetsLeft, Main.InsetsTop);
					Draw();
					bstrategy.show();
					g.dispose();
				}
			}
		}, 0, 32);
	}

	public void init() {
		disp_w = Main.getFrameWidth();
		disp_h = Main.getFrameHeight();

		skin[0] = ImageIO.read(getClass().getResource("/res/skin1.png"));
		skin[0] = ImageIO.read(getClass().getResource("/res/skin2.png"));
		meter = ImageIO.read(getClass().getResource("/res/meter.png"));

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

		Sound.play();
		Sound.playfly();
	}

	public void Draw() {
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
	}

	public void DrawUI() {
		g.drawImage(back, 0, disp_h / 8, null);
		g.drawRect(disp_w, ui_h, 0, 0);
		g.drawImage(number, meter_l / 2 - number.getWidth() / 2, ui_h / 2 - number.getHeight() / 2, null);
		g.drawRect(meter_l, (ui_h / 2) - (meter_h / 2), blood_x, (ui_h / 2) + (meter_h / 2));
		g.drawImage(meter, meter_l, (ui_h / 2) - (meter_h / 2), null);
		g.drawImage(skin[skinframeIndex], 0, disp_h / 8, null);
		if (boss)
			g.drawString("あと" + bossclick + "/100", 100, 1000);
		else
			g.drawString(killCount + "/" + killGoal, 100, 1000);
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

	public void stage(int id) throws IOException {
		switch (id) {
			case 0:
				back = ImageIO.read(getClass().getResource("/res/play1.png"));
				number = ImageIO.read(getClass().getResource("/res/number1.png"));
				kaCount = 2;
				killGoal = 10;
				itemContext(0);
				break;
			case 1:
				back = ImageIO.read(getClass().getResource("/res/play2.png"));
				number = ImageIO.read(getClass().getResource("/res/number2.png"));
				kaCount = 4;
				killGoal = 20;
				itemContext(1);
				break;
			case 2:
				back = ImageIO.read(getClass().getResource("/res/play3.png"));
				number = ImageIO.read(getClass().getResource("/res/number3.png"));
				kaCount = 6;
				killGoal = 30;
				itemContext(1);
				break;
			case 3:
				back = ImageIO.read(getClass().getResource("/res/play4.png"));
				number = ImageIO.read(getClass().getResource("/res/number4.png"));
				kaCount = 10;
				killGoal = 50;
				itemContext(2);
				break;
			case 4:
				back = ImageIO.read(getClass().getResource("/res/play5.png"));
				number = ImageIO.read(getClass().getResource("/res/number5.png"));
				kaCount = 12;
				killGoal = 70;
				itemContext(2);
				break;
			case 5:
				back = ImageIO.read(getClass().getResource("/res/play6.png"));
				number = ImageIO.read(getClass().getResource("/res/number6.png"));
				kaCount = 14;
				killGoal = 100;
				itemContext(3);
				break;
			case 6:
				back = ImageIO.read(getClass().getResource("/res/play7.png"));
				number = ImageIO.read(getClass().getResource("/res/number7.png"));
				kaCount = 18;
				killGoal = 300;
				itemContext(3);
				break;
			case 7:
				back = ImageIO.read(getClass().getResource("/res/play8.png"));
				number = ImageIO.read(getClass().getResource("/res/number8.png"));
				kaCount = 20;
				killGoal = 500;
				itemContext(3);
				break;
			case 8:
				back = ImageIO.read(getClass().getResource("/res/play8.png"));
				number = ImageIO.read(getClass().getResource("/res/number8.png"));
				kaCount = 20;
				killGoal = 500;
				itemContext(3);
		}
		kaContext();
	}

	public void fin(int mode) {
		t.cancel();
		t = null;
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
		switch (mode) {
			case OVER:
				Sound.overSE();
				panel.setVisible(false);
				Over.panel.setVisible(true);
				break;
			case PREPARATION:
				Sound.clapping_shortSE();
				panel.setVisible(false);
				Preparation.panel.setVisible(true);
				break;
			case FIN:
				Sound.clappingSE();
				panel.setVisible(false);
				Clear.panel.setVisible(true);
				break;
		}
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
}

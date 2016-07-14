package jp.ne.sakura.bunooboi.katoripc;

import java.awt.image.BufferedImage;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class KA {
	final int X = 1;
	final int Y = -1;

	final int PLUS = 1;
	final int MINUS = -1;

	BufferedImage ka[] = new BufferedImage[6];
	final int ID;
	int ka_w;
	int ka_h;
	int ka_x;
	int ka_y;
	int ka_vx;
	int ka_vy;
	final int Harvesting; // 血の採取量
	final int deadCount; // タッチする回数
	int clickCount; // タッチした回数
	int frameIndex; // アニメーション
	boolean dead = false; // 生死確認
	boolean intake = false; // 血を摂取中か確認
	boolean notback = false; // 右端に行かない

	BUNOOBOI bunooboi;

	KA other_ka[] = new KA[20];
	Random rnd = new Random();

	public KA(int id) {// id0=レベル1、id5=レベル6
		int dst = 1;// サイズ変更割合
		int resid[] = new int[6];
		ID = id;
		switch (ID) {
			case 0:
				dst = 18;
				deadCount = 1;
				resid[0] = R.drawable.ka1_1;
				resid[1] = R.drawable.ka1_4;
				resid[2] = R.drawable.ka1_2;
				resid[3] = R.drawable.ka1_3;
				resid[4] = R.drawable.ka1_5;
				resid[5] = R.drawable.ka1_6;
				break;
			case 1:
				dst = 16;
				deadCount = 2;
				resid[0] = R.drawable.ka2_1;
				resid[1] = R.drawable.ka2_4;
				resid[2] = R.drawable.ka2_2;
				resid[3] = R.drawable.ka2_3;
				resid[4] = R.drawable.ka2_5;
				resid[5] = R.drawable.ka2_6;
				break;
			case 2:
				dst = 14;
				deadCount = 3;
				resid[0] = R.drawable.ka3_1;
				resid[1] = R.drawable.ka3_4;
				resid[2] = R.drawable.ka3_2;
				resid[3] = R.drawable.ka3_3;
				resid[4] = R.drawable.ka3_5;
				resid[5] = R.drawable.ka3_6;
				break;
			case 3:
				dst = 12;
				deadCount = 7;
				resid[0] = R.drawable.ka4_1;
				resid[1] = R.drawable.ka4_4;
				resid[2] = R.drawable.ka4_2;
				resid[3] = R.drawable.ka4_3;
				resid[4] = R.drawable.ka4_5;
				resid[5] = R.drawable.ka4_6;
				break;
			case 4:
				dst = 14;
				deadCount = 5;
				resid[0] = R.drawable.ka5_1;
				resid[1] = R.drawable.ka5_4;
				resid[2] = R.drawable.ka5_2;
				resid[3] = R.drawable.ka5_3;
				resid[4] = R.drawable.ka5_5;
				resid[5] = R.drawable.ka5_6;
				break;
			case 5:
				dst = 6;
				deadCount = 99;
				resid[0] = R.drawable.ka6_1;
				resid[1] = R.drawable.ka6_4;
				resid[2] = R.drawable.ka6_2;
				resid[3] = R.drawable.ka6_3;
				resid[4] = R.drawable.ka6_5;
				resid[5] = R.drawable.ka6_6;
				break;
			default:
				dst = 1;
				deadCount = 1;
				break;
		}
		for (int i = 0; i < 6; i++) {
			ka[i] = BitmapFactory.decodeResource(Play.res, resid[i]);
			ka[i] = Bitmap.createScaledBitmap(ka[i], Play.disp_w / dst, Play.disp_w / dst, true);
		}
		ka_w = ka[0].getWidth();
		ka_h = ka[0].getHeight();
		ka_x = Play.disp_w + ka_w;
		reset_ka_y();
		kav(X, 0);
		kav(Y, 0);
		Harvesting = Play.disp_w / 500;
		bunooboi = new BUNOOBOI(this, Play.kaCount, rnd.nextInt(100) + 100, rnd.nextInt(100) + 100, rnd.nextInt(100) + 100, rnd.nextInt(100) + 100, rnd.nextInt(10) + 10,
				rnd.nextInt(10) + 20, rnd.nextInt(30) + 50, rnd.nextInt(10) + 20, 1);
	}

	public void move(KA other_ka[]) {// 蚊の動き
		this.other_ka = other_ka;
		if (ID == 5) {
			if (Play.boss) {
				if (dead) {
					ka_y += ka_vy;
					if (ka_y > Play.disp_h) {
						reset();
						dead = false;
					}
				} else {
					ka_x += ka_vx;
					ka_y += ka_vy;
					frameIndex++;
					if (frameIndex == 4)
						frameIndex = 0;
					if (intake) {
						ka_vx = 0;
						ka_vy = 0;
						ka_x = Play.skin_w / 8;
						frameIndex = 1;
						if (Play.tk % 2 == 0)
							frameIndex = 4;
					} else {
						kav(X, MINUS);
					}
					if (ka_x < 0) {
						ka_x = 0;
					}
					if (ka_x + ka_w > Play.disp_w) {
						if ((int) Math.signum(ka_vx) == 1)
							ka_x = Play.disp_w - ka_w;
					}
					if (ka_y < Play.ui_h) {
						ka_y = Play.ui_h;
					}
					if (ka_y + ka_h > Play.disp_h) {
						ka_y = Play.disp_h - ka_h;
					}
				}
				Play.canvas.drawBitmap(ka[frameIndex], ka_x, ka_y, null);
			}
		} else {
			if (dead) {
				ka_y += ka_vy;
				if (ka_y > Play.disp_h) {
					reset();
					dead = false;
				}
			} else {
				ka_x += ka_vx;
				ka_y += ka_vy;
				frameIndex++;
				if (frameIndex == 4)
					frameIndex = 0;
				if (intake) {
					ka_vx = 0;
					ka_vy = 0;
					ka_x = Play.skin_w / 8;
					frameIndex = 1;
					if (Play.tk % 2 == 0)
						frameIndex = 4;
				} else if (notback == false) {
					kav(X, MINUS);
				} else {
					bunooboi.move(other_ka);
				}
				if (ka_x < 0) {
					ka_x = 0;
				}
				if (ka_x + ka_w < Play.disp_w)
					notback = true;
				if (ka_x + ka_w > Play.disp_w)
					if (notback)
						ka_x = Play.disp_w - ka_w;
				if (ka_y < Play.ui_h)
					ka_y = Play.ui_h;
				if (ka_y + ka_h > Play.disp_h)
					ka_y = Play.disp_h - ka_h;
				notClash();
			}
			Play.canvas.drawBitmap(ka[frameIndex], ka_x, ka_y, null);
		}
	}

	void notClash() {
		for (int i = 0; i < Play.kaCount; i++) {
			if (!(other_ka[i].equals(this))) {
				if (ka_x - (other_ka[i].ka_x + other_ka[i].ka_w) == 0) {
					ka_x = other_ka[i].ka_x + other_ka[i].ka_w;
				}
				if (ka_y - (other_ka[i].ka_y + other_ka[i].ka_h) == 0) {
					ka_y = other_ka[i].ka_y + other_ka[i].ka_h;
				}

			}
		}
	}

	void reset() {
		ka_x = Play.disp_w + ka_w;
		reset_ka_y();
		kav(X, 0);
		kav(Y, 0);
		frameIndex = 0;
		clickCount = 0;
		notback = false;
		bunooboi = new BUNOOBOI(this, Play.kaCount, rnd.nextInt(100) + 100, rnd.nextInt(100) + 100, rnd.nextInt(100) + 100, rnd.nextInt(100) + 100, rnd.nextInt(10) + 10,
				rnd.nextInt(10) + 20, rnd.nextInt(30) + 50, rnd.nextInt(10) + 20, 1);
	}

	void reset_ka_y() {
		while (true) {
			ka_y = (int) Math.floor(Math.random() * Play.disp_h);
			if (ka_y > Play.disp_h / 8 && ka_y + ka_h < Play.disp_h) {
				break;
			}
		}
	}

	public void dead() {// 死亡処理
		dead = true;
		ka_vx = 0;
		ka_vy = 30;
		frameIndex = 5;
		Play.killCount += 1;
		if (bunooboi != null)
			bunooboi = null;
	}

	public int intake() {// 血の摂取
		if (ka_x <= Play.skin_w / 8) {
			if (dead == false) {
				intake = true;
				return Harvesting;
			} else {
				return 0;
			}
		} else {
			intake = false;
			return 0;
		}
	}

	public void kav(int xy, int pm) {// 移動変更
		switch (xy) {
			case X:
				if (pm == PLUS)
					ka_vx = vunit();
				else if (pm == MINUS)
					ka_vx = -vunit();
				else
					ka_vx = 0;
				break;
			case Y:
				if (pm == PLUS)
					ka_vy = vunit();
				else if (pm == MINUS)
					ka_vy = -vunit();
				else
					ka_vy = 0;
				break;
		}
	}

	int vunit() {// 各移動数値
		int kav = 0;
		switch (ID) {
			case 0:
				kav = (Play.disp_w / 300) - 1;
				break;
			case 1:
				kav = (Play.disp_w / 300);
				break;
			case 2:
				kav = (Play.disp_w / 300) + 2;
				break;
			case 3:
				kav = (Play.disp_w / 300) + 1;
				break;
			case 4:
				kav = (Play.disp_w / 300) + 5;
				break;
			case 5:
				kav = (Play.disp_w / 250) - 3;
				break;
		}
		return kav;
	}
}

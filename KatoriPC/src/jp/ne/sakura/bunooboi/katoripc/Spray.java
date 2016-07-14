package jp.ne.sakura.bunooboi.katoripc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import android.graphics.RectF;

public class Spray {
	BufferedImage smoke;
	BufferedImage button;

	int smoke_w;
	int smoke_h;
	int smoke_x;
	int smoke_y;
	int smoke_vx;
	boolean smoke_tf = false;

	int button_w;
	int button_h;
	int button_x;
	int button_y;
	int button_ac = 360;
	boolean button_tf = false;

	public Spray() {
		try {
			smoke = ImageIO.read(getClass().getResource("/res/smoke_spray.png"));
			button = ImageIO.read(getClass().getResource("/res/button_spray.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		smoke_w = smoke.getWidth();
		smoke_h = smoke.getHeight();
		smoke_x = 0 - smoke_w;
		smoke_y();
		smoke_vx = Play.disp_w / 50;

		button_w = button.getWidth();
		button_h = button.getHeight();
		button_x = Play.meter_m_r1;
		button_y = Play.disp_h / 16 - button_h / 2;

	}

	public void move() {
		button();
		smoke();
	}

	void smoke() {
		if (smoke_tf) {
			if (button_tf) {
				smoke_x += smoke_vx;
				if (smoke_x > Play.disp_w) {
					smoke_x = 0 - smoke_w;
					smoke_y();
					smoke_tf = false;
				}
				Play.g.drawImage(smoke, smoke_x, smoke_y, null);
			}
		}
	}

	void smoke_y() {
		while (true) {
			smoke_y = (int) Math.floor(Math.random() * Play.disp_h - Play.disp_h / 8);
			if (smoke_y < (Play.disp_h - Play.disp_h / 8) - smoke_h && smoke_y > Play.disp_h / 8) {
				break;
			}
		}
	}

	void button() {
		Play.g.drawImage(button, button_x, button_y, null);
		if (button_tf) {
			RectF r = new RectF(button_x, button_y, button_x + button_w, button_y + button_h);
			Play.canvas.drawArc(r, 270, -button_ac, true, Play.button_p);
			button_ac--;
			if (button_ac == 0) {
				button_tf = false;
				button_ac = 360;
			}
		}
	}
}

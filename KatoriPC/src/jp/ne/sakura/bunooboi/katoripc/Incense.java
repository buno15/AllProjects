package jp.ne.sakura.bunooboi.katoripc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import android.graphics.RectF;

public class Incense {
	BufferedImage smoke;
	BufferedImage button;

	int smoke_h;
	int smoke_y;
	int smoke_vy;
	boolean smoke_tf = false;

	int button_w;
	int button_h;
	int button_x;
	int button_y;
	int button_ac = 360;
	boolean button_tf = false;

	public Incense() {
		try {
			smoke = ImageIO.read(getClass().getResource("/res/smoke_incense.png"));
			button = ImageIO.read(getClass().getResource("/res/button_incense.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		smoke_h = smoke.getHeight();
		smoke_y = Play.disp_h;
		smoke_vy = -(Play.disp_h / 200);

		button_w = button.getWidth();
		button_h = button.getHeight();
		button_x = Play.meter_m_r3;
		button_y = Play.disp_h / 16 - button_h / 2;
	}

	public void move() {
		smoke();
		button();
	}

	void smoke() {
		if (smoke_tf)
			if (button_tf) {
				smoke_y += smoke_vy;
				if (smoke_y + smoke_h < Play.ui_h) {
					smoke_tf = false;
					smoke_y = Play.disp_h;
				}
				Play.g.drawImage(smoke, 0, smoke_y, null);
			}
	}

	void button() {
		Play.g.drawImage(button, button_x, button_y, null);
		if (button_tf) {
			RectF r = new RectF(button_x, button_y, button_x + button_w, button_y + button_h);
			Play.canvas.drawArc(r, 270, -button_ac, true, Play.button_p);
			if (Play.tk % 3 == 0)
				button_ac--;
			if (button_ac == 0) {
				button_tf = false;
				button_ac = 360;
			}
		}
	}
}

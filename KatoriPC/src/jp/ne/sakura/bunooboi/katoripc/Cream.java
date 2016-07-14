package jp.ne.sakura.bunooboi.katoripc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cream {
	BufferedImage cream;
	BufferedImage button;

	int button_w;
	int button_h;
	int button_x;
	int button_y;
	int button_ac = 360;
	boolean button_tf = false;
	boolean cream_tf = false;

	public Cream() {
		try {
			button = ImageIO.read(getClass().getResource("/res/button_cream.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		button_w = button.getWidth();
		button_h = button.getHeight();
		button_x = Play.meter_m_r2;
		button_y = Play.disp_h / 16 - button_h / 2;
	}

	public void move() {
		button();
	}

	void button() {
		Play.g.drawImage(button, button_x, button_y, null);
		if (button_tf) {
			RectF r = new RectF(button_x, button_y, button_x + button_w, button_y + button_h);
			Play.canvas.drawArc(r, 270, -button_ac, true, Play.button_p);
			Play.t2 = System.currentTimeMillis();
			Play.t3 = (Play.t2 - Play.t1) / 1000;
			if (Play.tk % 2 == 0)
				button_ac--;
			if (Play.t3 <= 10) {
				cream_tf = true;
			} else if (Play.t3 > 10) {
				Play.skinframeIndex = 0;
				cream_tf = false;
			}
			if (button_ac == 0) {
				button_tf = false;
				button_ac = 360;
			}
		}
	}
}

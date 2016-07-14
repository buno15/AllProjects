package jp.ne.sakura.bunooboi.katoripc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Preparation {
	static int nakaCount;
	static MyPanel panel;

	public Preparation() {
		MyLabel label = null;
		switch (nakaCount) {
			case 0:
				label = new MyLabel("/res/stage1.png");
				panel = new MyPanel("/res/play1.png");
				Play.id = 0;
				break;
			case 1:
				label = new MyLabel("/res/stage2.png");
				panel = new MyPanel("/res/play2.png");
				Play.id = 1;
				break;
			case 2:
				label = new MyLabel("/res/stage3.png");
				panel = new MyPanel("/res/play3.png");
				Play.id = 2;
				break;
			case 3:
				label = new MyLabel("/res/stage4.png");
				panel = new MyPanel("/res/play4.png");
				Play.id = 3;
				break;
			case 4:
				label = new MyLabel("/res/stage5.png");
				panel = new MyPanel("/res/play5.png");
				Play.id = 4;
				break;
			case 5:
				label = new MyLabel("/res/stage6.png");
				panel = new MyPanel("/res/play6.png");
				Play.id = 5;
				break;
			case 6:
				label = new MyLabel("/res/stage7.png");
				panel = new MyPanel("/res/play7.png");
				Play.id = 6;
				break;
			case 7:
				label = new MyLabel("/res/stage8.png");
				panel = new MyPanel("/res/play8.png");
				Play.id = 7;
				break;
		}
		MyButton button = new MyButton("/res/button_start_notpressed.png", "/res/button_start_pressed.png");
		button.setBounds(Main.getFrameWidth() / 2 - 150, Main.getFrameHeight() / 2 + 100, 300, 100);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				Play.panel.setVisible(true);
			}
		});
		panel.add(label);
		panel.add(button);
		Main.addContent(panel);
	}
}

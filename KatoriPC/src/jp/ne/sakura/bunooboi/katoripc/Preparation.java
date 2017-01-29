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
				break;
			case 1:
				label = new MyLabel("/res/stage2.png");
				panel = new MyPanel("/res/play2.png");
				break;
			case 2:
				label = new MyLabel("/res/stage3.png");
				panel = new MyPanel("/res/play3.png");
				break;
			case 3:
				label = new MyLabel("/res/stage4.png");
				panel = new MyPanel("/res/play4.png");
				break;
			case 4:
				label = new MyLabel("/res/stage5.png");
				panel = new MyPanel("/res/play5.png");
				break;
			case 5:
				label = new MyLabel("/res/stage6.png");
				panel = new MyPanel("/res/play6.png");
				break;
			case 6:
				label = new MyLabel("/res/stage7.png");
				panel = new MyPanel("/res/play7.png");
				break;
			case 7:
				label = new MyLabel("/res/stage8.png");
				panel = new MyPanel("/res/play8.png");
				break;
		}
		MyButton button = new MyButton("/res/button_start_notpressed.png", "/res/button_start_pressed.png");
		button.setBounds(Main.getFrameWidth() / 2 - 150, Main.getFrameHeight() / 2 + 100, 300, 100);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
			}
		});
		panel.add(label);
		panel.add(button);
		Main.addContent(panel);
	}
}

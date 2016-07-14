package jp.ne.sakura.bunooboi.katoripc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clear {
	static MyPanel panel;

	public Clear() {
		panel = new MyPanel("/res/clear.png");
		MyLabel label = new MyLabel("/res/clearstring.png");
		MyButton button = new MyButton("/res/button_menu_notpressed.png", "/res/button_menu_pressed.png");
		button.setBounds(Main.getFrameWidth() / 2 - 150, Main.getFrameHeight() / 2 + 50, 300, 100);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				Start.panel.setVisible(true);
			}
		});
		panel.add(label);
		panel.add(button);
		Main.addContent(panel);
	}
}

package jp.ne.sakura.bunooboi.katoripc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help {
	static MyPanel panel;

	public Help() {
		panel = new MyPanel(Color.WHITE);
		MyButton button = new MyButton("/res/button_back_notpressed.png", "/res/button_back_pressed.png");
		button.setBounds(Main.getFrameWidth() / 2 - 150, Main.getFrameHeight() - 200, 300, 100);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				Start.panel.setVisible(true);
			}
		});
		panel.add(button);
		Main.addContent(panel);
	}
}

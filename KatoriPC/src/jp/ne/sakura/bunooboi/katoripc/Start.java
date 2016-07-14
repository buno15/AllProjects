package jp.ne.sakura.bunooboi.katoripc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
	static MyPanel panel;

	public Start() {
		Main.init();
		panel = new MyPanel("/res/start.png");
		MyLabel label = new MyLabel("/res/startstring.png");
		MyButton button1 = new MyButton("/res/button_start_notpressed.png", "/res/button_start_pressed.png");
		MyButton button2 = new MyButton("/res/button_help_notpressed.png", "/res/button_help_pressed.png");
		button1.setBounds(Main.getFrameWidth() / 2 - 150, Main.getFrameHeight() / 2, 300, 100);
		button2.setBounds(Main.getFrameWidth() / 2 - 150, Main.getFrameHeight() / 2 + 150, 300, 100);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				Preparation.panel.setVisible(true);
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				Help.panel.setVisible(true);
			}
		});
		panel.add(label);
		panel.add(button1);
		panel.add(button2);
		Main.addContent(panel);
	}

	public static void main(String args[]) {
		new Start();
		new Help();
		new Preparation();
		new Over();
		new Play();
		new Clear();
		Help.panel.setVisible(false);
		Preparation.panel.setVisible(false);
		Over.panel.setVisible(false);
		Play.panel.setVisible(false);
		Clear.panel.setVisible(false);
	}
}

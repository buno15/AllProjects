package jp.ne.sakura.bunooboi.katoripc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Over {
	static MyPanel panel;

	public Over() {
		panel = new MyPanel(Color.WHITE);
		MyButton button1 = new MyButton("/res/button_restart_notpressed.png", "/res/button_restart_pressed.png");
		MyButton button2 = new MyButton("/res/button_menu_notpressed.png", "/res/button_menu_pressed.png");
		button1.setBounds(Main.getFrameWidth() / 3 - 150, Main.getFrameHeight() / 2 + 50, 300, 100);
		button2.setBounds((Main.getFrameWidth() / 3 * 2) - 150, Main.getFrameHeight() / 2 + 50, 300, 100);
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
				Start.panel.setVisible(true);
			}
		});
		JLabel label = new JLabel();
		label.setText("GAME OVER");
		label.setFont(new Font("Serif", Font.BOLD, 125));
		label.setBounds(Main.getFrameWidth() / 2 - 400, Main.getFrameHeight() / 2 - 300, 800, 200);
		panel.add(label);
		panel.add(button1);
		panel.add(button2);
		Main.addContent(panel);
	}
}

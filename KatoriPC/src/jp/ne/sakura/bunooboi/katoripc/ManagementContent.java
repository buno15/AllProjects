package jp.ne.sakura.bunooboi.katoripc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManagementContent {
	static MyFrame frame;

	public static void init() {
		frame = new MyFrame();
		frame.setVisible(true);
	}

	public static void setVisible() {
		frame.pack();
		frame.setVisible(true);
	}
}

@SuppressWarnings("serial")
class MyFrame extends JFrame {
	public MyFrame() {
		setTitle("蚊取り達人");
		getContentPane().setPreferredSize(new Dimension(1280, 800));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		Image img = null;
		URL url = this.getClass().getClassLoader().getResource("res/icon.png");
		try {
			img = this.createImage((ImageProducer) url.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(img);
		setIconImage(icon.getImage());
	}
}

@SuppressWarnings("serial")
class MyPanel extends JPanel {
	MyPanel() {
		setBounds(0, 0, 1280, 800);
		setBackground(Color.BLACK);
	}
}

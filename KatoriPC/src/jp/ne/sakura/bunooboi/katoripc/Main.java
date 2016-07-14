package jp.ne.sakura.bunooboi.katoripc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	private static MyFrame frame;
	public static int InsetsTop;
	public static int InsetsBottom;
	public static int InsetsLeft;
	public static int InsetsRight;
	public static int InsetsWIdth;
	public static int InsetsHeight;

	public static void init() {
		frame = new MyFrame();
		frame.setVisible(true);
	}

	public static int getFrameWidth() {
		System.out.println(frame.getWidth());
		return frame.getWidth();
	}

	public static int getFrameHeight() {
		System.out.println(frame.getHeight());
		return frame.getHeight();
	}

	public static void addContent(Component comp) {
		frame.add(comp);
	}

	public static BufferStrategy getBufferStrategy() {
		frame.createBufferStrategy(2);
		return frame.getBufferStrategy();
	}

	public static void Visible(boolean b) {
		frame.setVisible(b);
	}

	public static void repaint() {
		frame.repaint();
	}

	public static void removeAll() {
		frame.removeAll();
	}
}

@SuppressWarnings("serial")
class MyFrame extends JFrame {
	public MyFrame() {
		setTitle("蚊取り達人");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		Insets insets = this.getInsets();
		Main.InsetsTop = insets.top;
		Main.InsetsBottom = insets.bottom;
		Main.InsetsLeft = insets.left;
		Main.InsetsRight = insets.right;
		Main.InsetsWIdth = Main.InsetsTop + Main.InsetsBottom;
		Main.InsetsHeight = Main.InsetsLeft + Main.InsetsRight;
		setSize(1280 + insets.left + insets.right, 800 + insets.top + insets.bottom);
		setLocationRelativeTo(null);
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
	BufferedImage image;
	Color color = Color.WHITE;

	MyPanel(String path) {
		super();
		setBounds(0, 0, 1280 + Main.InsetsWIdth, 800 + Main.InsetsHeight);
		setLayout(null);
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	MyPanel(Color color) {
		this.color = color;
		setBounds(0, 0, 1280 + Main.InsetsWIdth, 800 + Main.InsetsHeight);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(color);
		if (image != null)
			g.drawImage(image, 0, 0, 1280 + Main.InsetsWIdth, 800 + Main.InsetsHeight, this);
		else
			g.fillRect(0, 0, 1280 + Main.InsetsWIdth, 800 + Main.InsetsHeight);
	}
}

@SuppressWarnings("serial")
class MyLabel extends JLabel {
	BufferedImage image;

	MyLabel(String path) {
		super();
		setBounds(Main.getFrameWidth() / 2 - 400, Main.getFrameHeight() / 2 - 300, 800, 200);
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, 800, 200, this);
	}
}

@SuppressWarnings("serial")
class MyButton extends JButton {
	BufferedImage pressimage;
	BufferedImage notpressimage;

	MyButton(String notpressimg, String pressimg) {
		super();
		this.setVerticalAlignment(JButton.CENTER);
		this.setBorderPainted(false);
		this.setSize(300, 100);
		ImageIcon icon1 = new ImageIcon(getClass().getResource(notpressimg));
		ImageIcon icon2 = new ImageIcon(getClass().getResource(pressimg));
		MediaTracker tracker = new MediaTracker(this);
		Image img1 = icon1.getImage().getScaledInstance((int) (icon1.getIconWidth() + 10), -1, Image.SCALE_SMOOTH);
		Image img2 = icon2.getImage().getScaledInstance((int) (icon2.getIconWidth() + 10), -1, Image.SCALE_SMOOTH);
		tracker.addImage(img1, 1);
		tracker.addImage(img2, 2);
		icon1 = new ImageIcon(img1);
		icon2 = new ImageIcon(img2);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setIcon(icon1);
		this.setRolloverIcon(icon1);
		this.setPressedIcon(icon2);
	}
}

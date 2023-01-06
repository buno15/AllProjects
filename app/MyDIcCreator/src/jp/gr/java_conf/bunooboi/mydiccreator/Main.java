package jp.gr.java_conf.bunooboi.mydiccreator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	static MyFrame frame;
	static String filePath;
	static String mydicPath = "C:\\Users\\hiro\\Documents\\MyDic";

	public static void main(String args[]) {
		frame = new MyFrame();
		frame.setTitle("MyDicCreator");
		frame.getContentPane().setPreferredSize(new Dimension(1200, 800));
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mydicPath + "\\config.txt"), "UTF-8"))) {
			filePath = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void save(String title, int level, String key, String link, String selector, String description, String text) {
		String finallink = link;
		String finaltext = text;
		if (title.equals("") && key.equals("") && selector.equals("") && description.equals("")) {
			JOptionPane.showMessageDialog(frame, "未入力の必須項目がります");
		} else {
			if (finallink.equals("")) {
				finallink = "/none";
			}
			if (finaltext.equals("")) {
				finaltext = "none";
			}
			try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8")) {
				String str = finaltext.replaceAll("\n", "%%").replaceAll(",", "、");
				osw.write(title + "," + level + "," + key + "," + finallink + "," + selector.replaceAll(",", "、") + "," + description.replaceAll(",", "、") + "," + str + "\n");
				frame.clear();
				JOptionPane.showMessageDialog(frame, "保存しました");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "失敗しました");
			}
		}
	}

	static String[] getKey(String key) {
		return key.split("%");
	}
}

@SuppressWarnings("serial")
class MyFrame extends JFrame {
	JTextField field[] = new JTextField[6];
	JTextArea area = new JTextArea();
	JLabel label[] = new JLabel[7];
	JButton button = new JButton("作成");
	JMenuBar menubar = new JMenuBar();
	JMenu menu1 = new JMenu("File");
	JMenuItem menuitem1 = new JMenuItem("New");
	JMenuItem menuitem2 = new JMenuItem("Select");
	Image img;

	public MyFrame() {
		setLayout(null);
		URL url = this.getClass().getClassLoader().getResource("res/icon.png");
		try {
			img = this.createImage((ImageProducer) url.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(img);
		setIconImage(icon.getImage());

		menu1.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		menuitem1.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		menuitem2.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		menuitem1.addActionListener(e -> {
			String filename = JOptionPane.showInputDialog(MyFrame.this, "新規ファイル名を入力してください");
			if (filename != null && !filename.equals("")) {
				File file = new File(Main.mydicPath + "\\" + filename + ".csv");
				if (!file.exists()) {
					try {
						file.createNewFile();
						JOptionPane.showMessageDialog(MyFrame.this, "ファイルを作成しました");
					} catch (IOException exception) {
						JOptionPane.showMessageDialog(MyFrame.this, "エラー");
					}
				} else {
					JOptionPane.showMessageDialog(MyFrame.this, "ファイルはすでに存在します");
				}
			}
		});
		menuitem2.addActionListener(e -> {
			JFileChooser filechooser = new JFileChooser(Main.mydicPath);
			filechooser.setFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
			filechooser.setDialogTitle("保存ファイル");
			filechooser.setApproveButtonText("決定");
			filechooser.setPreferredSize(new Dimension(800, 600));
			int selected = filechooser.showOpenDialog(this);
			if (selected == JFileChooser.APPROVE_OPTION) {
				Main.filePath = filechooser.getSelectedFile().getPath();
				try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(Main.mydicPath + "\\config.txt"), "UTF-8")) {
					osw.write(Main.filePath);
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		});

		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel();
			label[i].setVerticalAlignment(JLabel.CENTER);
			label[i].setHorizontalAlignment(JLabel.CENTER);
			label[i].setFont(new Font(Font.SERIF, Font.BOLD, 30));
		}
		for (int i = 0; i < field.length; i++) {
			field[i] = new JTextField();
			field[i].setFont(new Font(Font.SERIF, Font.BOLD, 30));
			field[i].setColumns(40);
		}
		button.setFont(new Font(Font.SERIF, Font.BOLD, 100));
		button.setBounds(20, 650, 570, 148);
		button.addActionListener(e -> {
			String field1 = field[1].getText().replaceAll(" ", "");
			if (field1.equals("") || (field1.equals("0") == false && field1.equals("1") == false && field1.equals("2") == false && field1.equals("3") == false)) {
				field1 = "1";
			}
			Main.save(field[0].getText(), Integer.parseInt(field1), field[2].getText(), field[3].getText(), field[4].getText(), field[5].getText(), area.getText());
		});
		area.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		area.setBorder(new LineBorder(Color.BLACK, 1, false));
		area.setLineWrap(true);
		JScrollPane scrollpane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(600, 50, 590, 750);

		label[0].setText("タイトル(必須。改行は%%)");
		label[1].setText("出題レベル(0~3)");
		label[2].setText("検索キー(必須。複数の場合、%で区切る)");
		label[2].setFont(new Font(Font.SERIF, Font.BOLD, 20));
		label[3].setText("ファイルパス・URL");
		label[4].setText("選択肢(必須。改行は%%)");
		label[5].setText("説明(必須)");
		label[6].setText("読み上げ文(全文日本語推奨)");
		label[0].setBounds(20, 0, 570, 50);
		label[1].setBounds(20, 100, 570, 50);
		label[2].setBounds(20, 200, 570, 50);
		label[3].setBounds(20, 300, 570, 50);
		label[4].setBounds(20, 400, 570, 50);
		label[5].setBounds(20, 500, 570, 50);
		label[6].setBounds(600, 0, 590, 50);
		field[0].setBounds(20, 50, 570, 50);
		field[1].setBounds(20, 150, 570, 50);
		field[2].setBounds(20, 250, 570, 50);
		field[3].setBounds(20, 350, 570, 50);
		field[4].setBounds(20, 450, 570, 50);
		field[5].setBounds(20, 550, 570, 50);

		for (int i = 0; i < label.length; i++) {
			add(label[i]);
		}
		for (int i = 0; i < field.length; i++) {
			add(field[i]);
		}
		add(button);
		add(scrollpane);

		menu1.add(menuitem1);
		menu1.add(menuitem2);
		menubar.add(menu1);
		setJMenuBar(menubar);
		field[0].requestFocusInWindow();
		setFocusTraversalPolicy(new MyFocusPolicy());
	}

	void clear() {
		for (int i = 0; i < field.length; i++) {
			field[i].setText("");
		}
		area.setText("");
		field[0].requestFocusInWindow();
	}

	class MyFocusPolicy extends LayoutFocusTraversalPolicy {
		@Override
		public Component getComponentAfter(Container container, Component component) {
			if (component == field[0]) {
				return field[1];
			} else {
				return super.getComponentAfter(container, component);
			}
		}

		@Override
		public Component getComponentBefore(Container container, Component component) {
			if (component == field[1]) {
				return field[0];
			} else {
				return super.getComponentBefore(container, component);
			}
		}
	}
}

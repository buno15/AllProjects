package jp.ne.sakura.bunooboi.katoripc;

public class Start {
	public static void main(String args[]) {
		ManagementContent.init();
		MyPanel panel = new MyPanel();
		ManagementContent.frame.add(panel);
		ManagementContent.setVisible();
	}
}

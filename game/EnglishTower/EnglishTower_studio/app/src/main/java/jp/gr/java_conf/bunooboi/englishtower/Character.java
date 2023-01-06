package jp.gr.java_conf.bunooboi.englishtower;

public class Character {
	final String	Char;		// 文字
	final String	CharUpper;	// 大文字
	final int		ID;			// ID

	public Character(String Char, int id) {
		this.Char = Char;
		CharUpper = Char.toUpperCase();
		ID = id;
	}

	public int getID() {
		return ID;
	}
}

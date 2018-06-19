package jp.gr.java_conf.bunooboi.warasibe2;

public class Item {
	static int			sell;
	static int			level;
	static int			Endurance;

	final static String	itemName0	= "木の枝";
	final static String	itemName1	= "パン";
	final static String	itemName2	= "イモ";
	final static String	itemName3	= "魚";
	final static String	itemName4	= "肉";
	final static String	itemName5	= "イス";
	final static String	itemName6	= "机";
	final static String	itemName7	= "ナベ";
	final static String	itemName8	= "ボロイ剣";
	final static String	itemName9	= "ボロイ鎧";
	final static String	itemName10	= "インサイト";

	final static String	itemName11	= "牛";
	final static String	itemName12	= "豚";
	final static String	itemName13	= "馬";
	final static String	itemName14	= "タンス";
	final static String	itemName15	= "ピアノ";
	final static String	itemName16	= "槍";
	final static String	itemName17	= "弓矢";
	final static String	itemName18	= "フツウノ剣";
	final static String	itemName19	= "フツウノ鎧";
	final static String	itemName20	= "メガインサイト";

	final static String	itemName21	= "金";
	final static String	itemName22	= "家";
	final static String	itemName23	= "牧場";
	final static String	itemName24	= "農場";
	final static String	itemName25	= "漁場";
	final static String	itemName26	= "ソレナリの槍";
	final static String	itemName27	= "ソレナリの弓矢";
	final static String	itemName28	= "ツヨイ剣";
	final static String	itemName29	= "ツヨイ鎧";
	final static String	itemName30	= "ギガインサイト";

	final static String	itemName31	= "ダイヤモンド";
	final static String	itemName32	= "城";
	final static String	itemName33	= "港";
	final static String	itemName34	= "国";
	final static String	itemName35	= "ネクタル";
	final static String	itemName36	= "ランギヌスの槍";
	final static String	itemName37	= "エルフの弓矢";
	final static String	itemName38	= "デンセツノ剣";
	final static String	itemName39	= "デンセツノ鎧";
	final static String	itemName40	= "テラインサイト";

	public static void item(int itemid) {
		if (itemid <= 10) {
			if (itemid == 0) {
				setlevel(1);
				setsell(0);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName0);

			} else if (itemid == 1) {
				setlevel(1);
				setsell(2);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName1);

			} else if (itemid == 2) {
				setlevel(1);
				setsell(2);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName2);

			} else if (itemid == 3) {
				setlevel(1);
				setsell(4);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName3);

			} else if (itemid == 4) {
				setlevel(1);
				setsell(4);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName4);

			} else if (itemid == 5) {
				setlevel(1);
				setsell(8);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName5);

			} else if (itemid == 6) {
				setlevel(1);
				setsell(8);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName6);

			} else if (itemid == 7) {
				setlevel(1);
				setsell(8);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName7);

			} else if (itemid == 8) {
				setlevel(1);
				setsell(10);
				setEndurance(5);
				Player.setadd(5, 1);
				Player.setitem(itemName8);

			} else if (itemid == 9) {
				setlevel(1);
				setsell(10);
				setEndurance(5);
				Player.setadd(5, 0);
				Player.setitem(itemName9);

			} else if (itemid == 10) {
				setlevel(1);
				setsell(10);
				setEndurance(5);
				Player.setadd(5, 2);
				Player.setitem(itemName10);
			}
		} else if (itemid > 10 && itemid <= 20) {
			if (itemid == 11) {
				setlevel(2);
				setsell(45);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName11);
			} else if (itemid == 12) {
				setlevel(2);
				setsell(40);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName12);
			} else if (itemid == 13) {
				setlevel(2);
				setsell(43);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName13);
			} else if (itemid == 14) {
				setlevel(2);
				setsell(33);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName14);
			} else if (itemid == 15) {
				setlevel(2);
				setsell(35);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName15);
			} else if (itemid == 16) {
				setlevel(2);
				setsell(20);
				setEndurance(15);
				Player.setadd(15, 1);
				Player.setitem(itemName16);
			} else if (itemid == 17) {
				setlevel(2);
				setsell(25);
				setEndurance(13);
				Player.setadd(13, 1);
				Player.setitem(itemName17);
			} else if (itemid == 18) {
				setlevel(2);
				setsell(30);
				setEndurance(20);
				Player.setadd(20, 1);
				Player.setitem(itemName18);
			} else if (itemid == 19) {
				setlevel(2);
				setsell(30);
				setEndurance(20);
				Player.setadd(20, 0);
				Player.setitem(itemName19);
			} else if (itemid == 20) {
				setlevel(2);
				setsell(30);
				setEndurance(20);
				Player.setadd(20, 2);
				Player.setitem(itemName20);
			}
		} else if (itemid > 20 && itemid <= 30) {
			if (itemid == 21) {
				setlevel(3);
				setsell(170);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName21);
			} else if (itemid == 22) {
				setlevel(3);
				setsell(130);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName22);
			} else if (itemid == 23) {
				setlevel(3);
				setsell(145);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName23);
			} else if (itemid == 24) {
				setlevel(3);
				setsell(140);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName24);
			} else if (itemid == 25) {
				setlevel(3);
				setsell(135);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName25);
			} else if (itemid == 26) {
				setlevel(3);
				setsell(85);
				setEndurance(25);
				Player.setadd(25, 1);
				Player.setitem(itemName26);
			} else if (itemid == 27) {
				setlevel(3);
				setsell(75);
				setEndurance(23);
				Player.setadd(23, 1);
				Player.setitem(itemName27);
			} else if (itemid == 28) {
				setlevel(3);
				setsell(100);
				setEndurance(30);
				Player.setadd(30, 1);
				Player.setitem(itemName28);
			} else if (itemid == 29) {
				setlevel(3);
				setsell(100);
				setEndurance(30);
				Player.setadd(30, 0);
				Player.setitem(itemName29);
			} else if (itemid == 30) {
				setlevel(3);
				setsell(100);
				setEndurance(30);
				Player.setadd(30, 2);
				Player.setitem(itemName30);
			}
		} else if (itemid > 30 && itemid <= 40) {
			if (itemid == 31) {
				setlevel(4);
				setsell(640);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName31);
			} else if (itemid == 32) {
				setlevel(4);
				setsell(730);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName32);
			} else if (itemid == 33) {
				setlevel(4);
				setsell(520);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName33);
			} else if (itemid == 34) {
				setlevel(4);
				setsell(2000);
				setEndurance(-1);
				Player.setadd(0, 3);
				Player.setitem(itemName34);
			} else if (itemid == 35) {
				setlevel(4);
				setsell(250);
				setEndurance(-1);
				Player.setadd(0, 4);
				Player.setitem(itemName35);
			} else if (itemid == 36) {
				setlevel(4);
				setsell(320);
				setEndurance(38);
				Player.setadd(38, 1);
				Player.setitem(itemName36);
			} else if (itemid == 37) {
				setlevel(4);
				setsell(300);
				setEndurance(37);
				Player.setadd(37, 1);
				Player.setitem(itemName37);
			} else if (itemid == 38) {
				setlevel(4);
				setsell(500);
				setEndurance(40);
				Player.setadd(40, 1);
				Player.setitem(itemName38);
			} else if (itemid == 39) {
				setlevel(4);
				setsell(500);
				setEndurance(40);
				Player.setadd(40, 0);
				Player.setitem(itemName39);
			} else if (itemid == 40) {
				setlevel(4);
				setsell(500);
				setEndurance(40);
				Player.setadd(40, 2);
				Player.setitem(itemName40);
			}
		}
	}

	public static void setnull() {
		Player.setitem("なし");
		Player.setadd(0, 3);
		setlevel(0);
		setsell(0);
		setEndurance(-1);
	}

	public static void setsell(int sell) {
		Item.sell = sell;
	}

	public static void setlevel(int level) {
		Item.level = level;
	}

	public static void setEndurance(int Endurance) {
		Item.Endurance = Endurance;
	}

	public static int getsell() {
		return sell;
	}

	public static int getlevel() {
		return level;
	}

	public static int getEndurance() {
		return Endurance;
	}
}

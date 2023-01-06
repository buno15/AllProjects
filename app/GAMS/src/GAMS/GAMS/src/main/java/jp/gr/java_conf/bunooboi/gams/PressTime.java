package jp.gr.java_conf.bunooboi.gams;

public class PressTime {
	public static final int	YEAR	= 0;// 年
	public static final int	MONTH	= 1;// 月
	public static final int	DAY		= 2;// 日
	public static final int	HOUR	= 3;// 時
	public static final int	MINUTE	= 4;// 分
	public static final int	SECOND	= 5;// 秒

	public static String getPressTimeString() {// 書き込み用文字列作成
		CurrentTime c = new CurrentTime();
		return c.HOUR + "_" + c.MINUTE + "_" + c.SECOND;
	}

	public static String getPressTimeStringAlls() {// 書き込み用文字列作成、フルバージョン
		CurrentTime c = new CurrentTime();
		return c.YEAR + "_" + c.MONTH + "_" + c.DAY + "_" + c.HOUR + "_" + c.MINUTE + "_" + c.SECOND;
	}

	public static int getPressTimeInteger(String s, int id) {// 読み込み用整数作成、保存データとほしい単位設定
		String str[] = s.split("_");
		for (int i = 0; i < str.length; i++) {
			switch (id) {
			case HOUR:
				if (i == 0)
					return Integer.parseInt(str[i]);
			case MINUTE:
				if (i == 1)
					return Integer.parseInt(str[i]);
			case SECOND:
				if (i == 2)
					return Integer.parseInt(str[i]);
			}
		}
		return 0;
	}

	public static int getPressTimeIntegerAll(String s, int id) {// 読み込み用整数作成、保存データとほしい単位設定、フルバージョン
		String str[] = s.split("_");
		for (int i = 0; i < str.length; i++) {
			switch (id) {
			case YEAR:
				if (i == 0)
					return Integer.parseInt(str[i]);
			case MONTH:
				if (i == 1)
					return Integer.parseInt(str[i]);
			case DAY:
				if (i == 2)
					return Integer.parseInt(str[i]);
			case HOUR:
				if (i == 3)
					return Integer.parseInt(str[i]);
			case MINUTE:
				if (i == 4)
					return Integer.parseInt(str[i]);
			case SECOND:
				if (i == 5)
					return Integer.parseInt(str[i]);
			}
		}
		return 0;
	}
}

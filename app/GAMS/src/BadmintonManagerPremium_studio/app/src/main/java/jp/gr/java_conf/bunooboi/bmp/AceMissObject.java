package jp.gr.java_conf.bunooboi.bmp;

import android.graphics.Color;

public class AceMissObject {
	public static final int	ACE_GOOD		= 0;// エースグッド
	public static final int	ACE_NORMAL		= 1;// エースノーマル
	public static final int	ACE_LUCKY		= 2;// エースラッキー
	public static final int	MISS_BAD		= 3;// ミスバッド
	public static final int	MISS_NORMAL		= 4;// ミスノーマル
	public static final int	MISS_UNLUCKY	= 5;// ミスアンラッキー

	static int getColor(int id) {// 色取得
		switch (id) {
		case ACE_GOOD:
			return Color.BLUE;
		case ACE_NORMAL:
			return Color.parseColor("#00ccff");
		case ACE_LUCKY:
			return Color.parseColor("#ccccff");
		case MISS_BAD:
			return Color.RED;
		case MISS_NORMAL:
			return Color.parseColor("#ff6600");
		case MISS_UNLUCKY:
			return Color.parseColor("#ff9966");
		}
		return 0;
	}

	static int getGraph(int id) {// グラフチェック取得
		switch (id) {
		case ACE_GOOD:
			return jp.gr.java_conf.bunooboi.gams.R.drawable.ace_good;
		case ACE_NORMAL:
			return jp.gr.java_conf.bunooboi.gams.R.drawable.ace_normal;
		case ACE_LUCKY:
			return jp.gr.java_conf.bunooboi.gams.R.drawable.ace_lucky;
		case MISS_BAD:
			return jp.gr.java_conf.bunooboi.gams.R.drawable.miss_bad;
		case MISS_NORMAL:
			return jp.gr.java_conf.bunooboi.gams.R.drawable.miss_normal;
		case MISS_UNLUCKY:
			return jp.gr.java_conf.bunooboi.gams.R.drawable.miss_unlucky;
		}
		return 0;
	}

	static String getInitials(int id) {// 頭文字取得
		switch (id) {
		case ACE_GOOD:
			return "G";
		case ACE_NORMAL:
			return "N";
		case ACE_LUCKY:
			return "L";
		case MISS_BAD:
			return "B";
		case MISS_NORMAL:
			return "N";
		case MISS_UNLUCKY:
			return "UL";
		}
		return "";
	}

	static String getHullName(int id) {// フルネーム取得
		switch (id) {
		case ACE_GOOD:
			return "Good";
		case ACE_NORMAL:
			return "Normal";
		case ACE_LUCKY:
			return "Lucky";
		case MISS_BAD:
			return "Bad";
		case MISS_NORMAL:
			return "Normal";
		case MISS_UNLUCKY:
			return "UnLucky";
		}
		return "";
	}

}

package jp.gr.java_conf.koni.STOPsumaho;

public class Values {
	static int		releasecount;	// 強制解除タッチ回数
	static boolean	passcount;		// パスワード停止時に電源を切ったかカウント
	static boolean	termstf	= true;	// 初回起動か調べる

	static long		sstime;			// 時間停止秒での時間
	static long		displaytime;	// 時間停止表示時間
	static String	timeword;		// 時間停止単位 分、時間
}

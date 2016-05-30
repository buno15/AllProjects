package jp.gr.java_conf.koni.warasibe;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class ScamEvent extends CORE {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SOUND.Scaminit(getApplicationContext());
		random2 = (int) Math.floor(Math.random() * 2);
		PLAYER.getprobability(random2);
		if (statusme(76, 200)) {
			random3 = (int) Math.floor(Math.random() * 2);
			if (random3 == 0) {
				playbg.setBackgroundResource(R.drawable.kenngou);
				tf1 = false;
				random1 = rnd.nextInt(1001) + 500;
				console.setText(Html.fromHtml(
						"剣豪「ここらで有名なおまえに決闘を<br/>　　　申し込む。お前が勝てば私の<br/>　　　全財産である<font color=\"#aaaa00\">"
								+ random1
								+ "</font>GSBを<br/>　　　くれてやる。決闘するか？」<br/><font color=\"#00a0dd\">"
								+ PLAYER.probailitytext2 + "</font>"));
				button1.setText("命かける");
				button2.setText("やめとく");
			} else if (random3 == 1) {
				playbg.setBackgroundResource(R.drawable.ruretto);
				tf1 = true;
				random1 = rnd.nextInt(301) + 200;
				console.setText(Html.fromHtml(
						"支配人「ルーレットやっていきませんか<br/>　　　　かけ金は<font color=\"#aaaa00\">" + random1
								+ "</font>GSBで固定してい<br/>　　　　ます。当たればかけ金の倍を<br/>　　　　差し上げます。」<br/><font color=\"#00a0dd\">"
								+ PLAYER.probailitytext2 + "</font>"));
				button1.setText("やる");
				button2.setText("やらない");
			}
		} else if (statusme(51, 75)) {
			playbg.setBackgroundResource(R.drawable.ruretto);
			tf1 = true;
			random1 = rnd.nextInt(301) + 200;
			console.setText(Html
					.fromHtml("支配人「ルーレットやっていきませんか<br/>　　　　かけ金は<font color=\"#aaaa00\">" + random1
							+ "</font>GSBで固定してい<br/>　　　　ます。当たればかけ金の倍を<br/>　　　　差し上げます。」<br/><font color=\"#00a0dd\">"
							+ PLAYER.probailitytext2 + "</font>"));
			button1.setText("やる");
			button2.setText("やらない");
		} else if (statusme(26, 50)) {
			random1 = rnd.nextInt(51) + 100;
			random3 = (int) Math.floor(Math.random() * 2);
			if (random3 == 0 && !PLAYER.getitem().equals("なし")) {
				playbg.setBackgroundResource(R.drawable.urabuguya);
				tf1 = false;
				console.setText(Html.fromHtml("裏武具屋「あんたの持っている<br/>　　　　　<font color=\"#a67e4e\">"
						+ PLAYER.getitem() + "</font>を<font color=\"#aaaa00\">" + random1
						+ "<font>GSBで<br/>　　　　　売ってくれないかね<br/>　　　　　今は持ち合わせがないから<br/>　　　　　支払いは後日になるが」<br/><font color=\"#00a0dd\">"
						+ PLAYER.probailitytext1 + "</font>"));
				button1.setText("売った");
				button2.setText("信用できん");
			} else {
				playbg.setBackgroundResource(R.drawable.syounenn);
				random3 = 1;
				tf1 = true;
				console.setText(Html.fromHtml(
						"少年「本がほしいんだけど<br/>　　　今持ち合わせがなくて、旅のお方<br/>　　　代わりに<font color=\"#aaaa00\">"
								+ random1
								+ "</font>GSB払ってくれますか<br/>　　　代金は後で持ってくるから」<br/><font color=\"#00a0dd\">"
								+ PLAYER.probailitytext1 + "</font>"));
				button1.setText("いいよ");
				button2.setText("いやだ");
			}
		} else if (statusme(0, 25)) {
			playbg.setBackgroundResource(R.drawable.man);
			tf1 = true;
			random1 = rnd.nextInt(11) + 20;
			console.setText(
					Html.fromHtml("男「今度ここに店を建てるんだが<br/>　　あと<font color=\"#aaaa00\">" + random1
							+ "</font>GSBほど必要なんだ<br/>　　倍にして返すから<br/>　　あんた俺を助けてくれないか」<br/><font color=\"#00a0dd\">"
							+ PLAYER.probailitytext1 + "</font>"));
			button1.setText("いいよ");
			button2.setText("いやだ");
		}
		status();
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (PLAYER.getmoney() < random1 && tf1) {
					SOUND.no();
					if (statusme(76, 200))
						console.setText("支配人「お金が足りません」");
					else if (statusme(51, 75))
						console.setText("支配人「お金が足りません」");
					else if (statusme(26, 50))
						console.setText("少年「お金ないのか」");
					else if (statusme(0, 25))
						console.setText("男「金が足りないな」");
					exitevent();
				} else {
					SOUND.itemup();
					if (statusme(76, 200)) {
						if (random3 == 0)
							console.setText("剣豪「まいる！！！」");
						else if (random3 == 1) {
							console.setText("支配人「それではいきますよ」");
							PLAYER.setmoney(PLAYER.getmoney() - random1);
						}
					} else if (statusme(51, 75)) {
						console.setText("支配人「それではいきますよ」");
						PLAYER.setmoney(PLAYER.getmoney() - random1);
					} else if (statusme(26, 50)) {
						if (random3 == 0) {
							console.setText("裏武具屋「ありがとよ\n　　　　　また後日に・・・」");
							ITEM.setnull();
						} else if (random3 == 1) {
							console.setText("少年「わーい、旅のお方ありがとう\n　　　お金持ってくるから待ってて」");
							PLAYER.setmoney(PLAYER.getmoney() - random1);
						}
					} else if (statusme(0, 25)) {
						console.setText("男「ありがとう助かるよ・・・」");
						PLAYER.setmoney(PLAYER.getmoney() - random1);
					}
					status();
					if (t == null) {
						rn = 0;
						t = new Timer();
						t.schedule(new ScamTimer(), 1500);
					}
				}
				buttonoff();
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				moveevent();
			}
		});
	}

	class ScamTimer extends TimerTask {
		@Override
		public void run() {
			handle.post(new Runnable() {
				@Override
				public void run() {
					if (rn == 0) {
						if (statusme(76, 200)) {
							if (random3 == 0) {
								SOUND.kill();
								console.setText("バシュッ");
							} else if (random3 == 1)
								console.setText("ガラガラガラガラガラガラ");
						} else if (statusme(51, 75))
							console.setText("ガラガラガラガラガラガラ");
						else if (statusme(26, 50)) {
							if (random3 == 0)
								console.setText("数日後");
							else if (random3 == 1)
								console.setText("それからしばらくして");
						} else if (statusme(0, 25))
							console.setText("数日後");
						playbg.setBackgroundResource(R.drawable.attack);
						if (t != null) {
							t.cancel();
							t = null;
						}
						if (t == null) {
							rn = 1;
							t = new Timer();
							t.schedule(new ScamTimer(), 1500);
						}
					} else if (rn == 1) {
						if (statusme(76, 200)) {
							if (random3 == 0) {
								if (random2 == 0) {
									ScamVOID();
									exitevent();
								} else if (random2 == 1) {
									rn = 1;
									if (t != null) {
										t.cancel();
										t = null;
									}
									if (t == null) {
										t = new Timer();
										t.schedule(new CORETimer(), 0);
									}
								}

							} else if (random3 == 1) {
								if (random2 == 0)
									ScamVOID();
								else if (random2 == 1) {
									playbg.setBackgroundResource(R.drawable.ruretto);
									console.setText("支配人「残念、はずれです」");
									SOUND.no();
								}
								exitevent();
							}
						} else if (statusme(51, 75)) {
							if (random2 == 0) {
								ScamVOID();
							} else if (random2 == 1) {
								playbg.setBackgroundResource(R.drawable.ruretto);
								console.setText("支配人「残念、はずれです」");
								SOUND.no();
							}
							exitevent();
						} else if (statusme(26, 50)) {
							if (random2 == 0) {
								ScamVOID();
							} else if (random2 == 1) {
								if (random3 == 0) {
									console.setText("裏武具屋はどこにもいなかった・・・");
									SOUND.stopBGM1();
									SOUND.scam();
								} else if (random3 == 1) {
									console.setText("少年は戻ってこなかった・・・");
									SOUND.stopBGM1();
									SOUND.scam();
								}
							}
							exitevent();
						} else if (statusme(0, 25)) {
							if (random2 == 0) {
								ScamVOID();
							} else if (random2 == 1) {
								console.setText("男はどこにもいなかった・・・");
								SOUND.stopBGM1();
								SOUND.scam();
							}
							exitevent();
						}
					}
				}
			});
		}

	}

	public void ScamVOID() {
		SOUND.getmoney();
		if (statusme(76, 200)) {
			if (random3 == 0)
				console.setText(Html.fromHtml(
						"あなたは剣豪を倒した<br/><font color=\"#aaaa00\">" + random1 + "</font>GSBもらった"));
			else if (random3 == 1) {
				playbg.setBackgroundResource(R.drawable.ruretto);
				random1 = random1 * 2;
				console.setText(Html.fromHtml(
						"支配人「大当たり！」<br/><font color=\"#aaaa00\">" + random1 + "</font>GSBもらった"));
			}
		} else if (statusme(51, 75)) {
			playbg.setBackgroundResource(R.drawable.ruretto);
			random1 = random1 * 2;
			console.setText(Html.fromHtml(
					"支配人「大当たり！」<br/><font color=\"#aaaa00\">" + random1 + "</font>GSBもらった"));
		} else if (statusme(26, 50)) {
			if (random3 == 0) {
				playbg.setBackgroundResource(R.drawable.urabuguya);
				console.setText(
						Html.fromHtml("裏武具屋「あんたか<br/>　　　　　この間の支払いだ」<br/><font color=\"#aaaa00\">"
								+ random1 + "</font>GSBもらった"));
			} else if (random3 == 1) {
				playbg.setBackgroundResource(R.drawable.syounenn);
				random1 = random1 + (random1 / 5);
				console.setText(Html.fromHtml(
						"少年「母ちゃんが買ってくれた<br/>　　　御礼に少し多く持ってけって」<br/><font color=\"#aaaa00\">"
								+ random1 + "</font>GSBもらった"));
			}
		} else if (statusme(0, 25)) {
			playbg.setBackgroundResource(R.drawable.man);
			random1 = random1 * 2;
			console.setText(Html
					.fromHtml("男「おかげで店を建てることができた<br/>　　約束通り金は倍にして返そう」<br/><font color=\"#aaaa00\">"
							+ random1 + "</font>GSBもらった"));
		}

		PLAYER.setmoney(PLAYER.getmoney() + random1);
		status();
	}

	@Override
	public void onResume() {
		super.onResume();
		SOUND.mediaPlayer1.start();
	}

	@Override
	public void onPause() {
		super.onPause();
		SOUND.mediaPlayer1.pause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		SOUND.stopBGM1();
	}
}

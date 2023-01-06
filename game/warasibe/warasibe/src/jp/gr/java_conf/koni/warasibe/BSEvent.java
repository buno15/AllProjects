package jp.gr.java_conf.koni.warasibe;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class BSEvent extends CORE {
	int buy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SOUND.BSinit(getApplicationContext());
		random1 = (int) Math.floor(Math.random() * 2);
		if (PLAYER.getitem().equals("なし") || ITEM.getsell() == 0 || ITEM.getlevel() == 0)
			random1 = 1;
		if (random1 == 0) {
			if (statusme(76, 200)) {
				playbg.setBackgroundResource(R.drawable.sessyou);
				enemyname = "摂政";
				console.setText(Html.fromHtml(
						enemyname + "「あなたの持っている<br/>　　　<font color=\"#a67e4e\">" + PLAYER.getitem()
								+ "</font>を売ってもらえま<br/>　　　せぬか<font color=\"#aaaa00\">"
								+ ITEM.getsell() + "</font>GSBでいかがです？」"));
			} else if (statusme(51, 75)) {
				playbg.setBackgroundResource(R.drawable.zinusi);
				enemyname = "地主";
				console.setText(Html.fromHtml(
						enemyname + "「きみの持っている<br/>　　　<font color=\"#a67e4e\">" + PLAYER.getitem()
								+ "</font>を売って<br/>　　　くれんか<font color=\"#aaaa00\">" + ITEM.getsell()
								+ "</font>GSBでどうだ？」"));
			} else if (statusme(26, 50)) {
				playbg.setBackgroundResource(R.drawable.bukiya);
				enemyname = "武具屋";
				console.setText(Html.fromHtml(
						enemyname + "「あなたの持っている<br/>　　　　<font color=\"#a67e4e\">" + PLAYER.getitem()
								+ "</font>を売ってくれませ<br/>　　　　んか<font color=\"#aaaa00\">"
								+ ITEM.getsell() + "</font>GSBでどうでしょう？」"));
			} else if (statusme(0, 25)) {
				playbg.setBackgroundResource(R.drawable.douguya);
				enemyname = "道具屋";
				console.setText(Html.fromHtml(
						enemyname + "「あんたの持っている<br/>　　　　<font color=\"#a67e4e\">" + PLAYER.getitem()
								+ "</font>を売ってくれんかね<br/>　　　　<font color=\"#aaaa00\">"
								+ ITEM.getsell() + "</font>GSBぐらいでどうだろう？」"));
			}
			button1.setText("売る");
		} else if (random1 == 1) {
			if (statusme(76, 200)) {
				playbg.setBackgroundResource(R.drawable.dennsetunokaziya);
				random2 = (int) Math.floor(Math.random() * 5);
				buy = 500;
				if (random2 == 0)
					item = ITEM.itemName36;
				else if (random2 == 1)
					item = ITEM.itemName37;
				else if (random2 == 2)
					item = ITEM.itemName38;
				else if (random2 == 3)
					item = ITEM.itemName39;
				else if (random2 == 4)
					item = ITEM.itemName40;
				enemyname = "伝説の鍛冶屋";
				console.setText(Html.fromHtml(enemyname + "「<font color=\"#a67e4e\">" + item
						+ "</font>が<br/>　　　　　　　ほしいなら<font color=\"#aaaa00\">" + buy
						+ "</font>GSBだ！」"));
			} else if (statusme(51, 75)) {
				playbg.setBackgroundResource(R.drawable.itiryuubuguya);
				random2 = (int) Math.floor(Math.random() * 5);
				buy = 200;
				if (random2 == 0)
					item = ITEM.itemName26;
				else if (random2 == 1)
					item = ITEM.itemName27;
				else if (random2 == 2)
					item = ITEM.itemName28;
				else if (random2 == 3)
					item = ITEM.itemName29;
				else if (random2 == 4)
					item = ITEM.itemName30;
				enemyname = "一級武具屋";
				console.setText(Html.fromHtml(enemyname + "「<font color=\"#a67e4e\">" + item
						+ "</font>は<br/>　　　　　　どうでしょう<br/>　　　　　　<font color=\"#aaaa00\">" + buy
						+ "</font>GSBでございます」"));
			} else if (statusme(26, 50)) {
				playbg.setBackgroundResource(R.drawable.bukiya);
				random2 = (int) Math.floor(Math.random() * 5);
				buy = 50;
				if (random2 == 0)
					item = ITEM.itemName16;
				else if (random2 == 1)
					item = ITEM.itemName17;
				else if (random2 == 2)
					item = ITEM.itemName18;
				else if (random2 == 3)
					item = ITEM.itemName19;
				else if (random2 == 4)
					item = ITEM.itemName20;
				enemyname = "武具屋";
				console.setText(Html.fromHtml(enemyname + "「<font color=\"#a67e4e\">" + item
						+ "</font>はどうだね<br/>　　　　今なら<font color=\"#aaaa00\">" + buy
						+ "</font>GSBだよ」"));
			} else if (statusme(0, 25)) {
				playbg.setBackgroundResource(R.drawable.douguya);
				random2 = (int) Math.floor(Math.random() * 3);
				buy = 20;
				if (random2 == 0)
					item = ITEM.itemName8;
				else if (random2 == 1)
					item = ITEM.itemName9;
				else if (random2 == 2)
					item = ITEM.itemName10;
				enemyname = "道具屋";
				console.setText(Html.fromHtml(enemyname + "「<font color=\"#a67e4e\">" + item
						+ "</font>はいらんかね<br/>　　　　<font color=\"#aaaa00\">" + buy
						+ "</font>GSBでどうだ？」"));
			}
			button1.setText("買う");
		}

		status();
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (random1 == 0) {
					SOUND.getmoney();
					if (statusme(76, 200)) {
						console.setText(
								Html.fromHtml(enemyname + "「礼を申します」<br/><font color=\"#aaaa00\">"
										+ ITEM.getsell() + "</font>GSBもらった"));
					} else if (statusme(51, 75)) {
						console.setText(
								Html.fromHtml(enemyname + "「ありがとう」<br/><font color=\"#aaaa00\">"
										+ ITEM.getsell() + "</font>GSBもらった"));
					} else if (statusme(26, 50)) {
						console.setText(Html
								.fromHtml(enemyname + "「ありがとうございます」<br/><font color=\"#aaaa00\">"
										+ ITEM.getsell() + "</font>GSBもらった"));
					} else if (statusme(0, 25)) {
						console.setText(
								Html.fromHtml(enemyname + "「ありがとよ」<br/><font color=\"#aaaa00\">"
										+ ITEM.getsell() + "</font>GSBもらった"));
					}
					PLAYER.setmoney(PLAYER.getmoney() + ITEM.getsell());
					ITEM.setnull();
				} else if (random1 == 1) {
					if (PLAYER.getmoney() < buy) {
						SOUND.no();
						console.setText(enemyname + "「金が足りないな」");
					} else {
						SOUND.itemget();
						if (PLAYER.getitem().equals("なし")) {
							if (statusme(76, 200)) {
								console.setText(
										Html.fromHtml(enemyname + "「そら<font color=\"#a67e4e\">"
												+ item + "</font>だ」<br/><font color=\"#a67e4e\">"
												+ item + "</font>を手に入れた"));
							} else if (statusme(51, 75)) {
								console.setText(Html.fromHtml(
										enemyname + "「ありがとうございました」<br/><font color=\"#a67e4e\">"
												+ item + "</font>を手に入れた"));
							} else if (statusme(26, 50)) {
								console.setText(Html
										.fromHtml(enemyname + "「毎度あり！」<br/><font color=\"#a67e4e\">"
												+ item + "</font>を手に入れた"));
							} else if (statusme(0, 25)) {
								console.setText(Html
										.fromHtml(enemyname + "「毎度～」<br/><font color=\"#a67e4e\">"
												+ item + "</font>を手に入れた"));
							}
						} else {
							if (statusme(76, 200)) {
								console.setText(
										Html.fromHtml(enemyname + "「そら<font color=\"#a67e4e\">"
												+ item + "</font>だ」<br/><font color=\"#a67e4e\">"
												+ PLAYER.getitem()
												+ "</font>を捨てた<br/><font color=\"#a67e4e\">" + item
												+ "</font>を手に入れた"));
							} else if (statusme(51, 75)) {
								console.setText(Html.fromHtml(
										enemyname + "「ありがとうございました」<br/><font color=\"#a67e4e\">"
												+ PLAYER.getitem()
												+ "</font>を捨てた<br/><font color=\"#a67e4e\">" + item
												+ "</font>を手に入れた"));
							} else if (statusme(26, 50)) {
								console.setText(Html.fromHtml(enemyname
										+ "「毎度あり！」<br/><font color=\"#a67e4e\">" + PLAYER.getitem()
										+ "</font>を捨てた<br/><font color=\"#a67e4e\">" + item
										+ "</font>を手に入れた"));
							} else if (statusme(0, 25)) {
								console.setText(Html.fromHtml(enemyname
										+ "「毎度～」<br/><font color=\"#a67e4e\">" + PLAYER.getitem()
										+ "</font>を捨てた<br/><font color=\"#a67e4e\">" + item
										+ "</font>を手に入れた"));
							}
						}
						PLAYER.setmoney(PLAYER.getmoney() - buy);
						if (statusme(76, 200)) {
							if (random2 == 0)
								ITEM.item(36);
							else if (random2 == 1)
								ITEM.item(37);
							else if (random2 == 2)
								ITEM.item(38);
							else if (random2 == 3)
								ITEM.item(39);
							else if (random2 == 4)
								ITEM.item(40);
						} else if (statusme(51, 75)) {
							if (random2 == 0)
								ITEM.item(26);
							else if (random2 == 1)
								ITEM.item(27);
							else if (random2 == 2)
								ITEM.item(28);
							else if (random2 == 3)
								ITEM.item(29);
							else if (random2 == 4)
								ITEM.item(30);
						} else if (statusme(26, 50)) {
							if (random2 == 0)
								ITEM.item(16);
							else if (random2 == 1)
								ITEM.item(17);
							else if (random2 == 2)
								ITEM.item(18);
							else if (random2 == 3)
								ITEM.item(19);
							else if (random2 == 4)
								ITEM.item(20);
						} else if (statusme(0, 25)) {
							if (random2 == 0)
								ITEM.item(8);
							else if (random2 == 1)
								ITEM.item(9);
							else if (random2 == 2)
								ITEM.item(10);
						}
					}
				}
				status();
				exitevent();
			}
		});
		button2.setText("やめる");
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				moveevent();
			}
		});
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
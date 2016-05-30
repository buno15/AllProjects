package jp.gr.java_conf.koni.warasibe;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class ExchangeEvent extends CORE {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SOUND.Exchangeinit(getApplicationContext());

		do {
			random1 = (int) Math.floor(Math.random() * 10);
			if (ITEM.getlevel() == 0 || ITEM.getlevel() == 1) {
				if (random1 == 0)
					item = ITEM.itemName1;
				else if (random1 == 1)
					item = ITEM.itemName2;
				else if (random1 == 2)
					item = ITEM.itemName3;
				else if (random1 == 3)
					item = ITEM.itemName4;
				else if (random1 == 4)
					item = ITEM.itemName5;
				else if (random1 == 5)
					item = ITEM.itemName6;
				else if (random1 == 6)
					item = ITEM.itemName7;
				else if (random1 == 7)
					item = ITEM.itemName8;
				else if (random1 == 8)
					item = ITEM.itemName9;
				else if (random1 == 9)
					item = ITEM.itemName10;
			} else if (ITEM.getlevel() == 2) {
				if (random1 == 0)
					item = ITEM.itemName11;
				else if (random1 == 1)
					item = ITEM.itemName12;
				else if (random1 == 2)
					item = ITEM.itemName13;
				else if (random1 == 3)
					item = ITEM.itemName14;
				else if (random1 == 4)
					item = ITEM.itemName15;
				else if (random1 == 5)
					item = ITEM.itemName16;
				else if (random1 == 6)
					item = ITEM.itemName17;
				else if (random1 == 7)
					item = ITEM.itemName18;
				else if (random1 == 8)
					item = ITEM.itemName19;
				else if (random1 == 9)
					item = ITEM.itemName20;
			} else if (ITEM.getlevel() == 3) {
				if (random1 == 0)
					item = ITEM.itemName21;
				else if (random1 == 1)
					item = ITEM.itemName22;
				else if (random1 == 2)
					item = ITEM.itemName23;
				else if (random1 == 3)
					item = ITEM.itemName24;
				else if (random1 == 4)
					item = ITEM.itemName25;
				else if (random1 == 5)
					item = ITEM.itemName26;
				else if (random1 == 6)
					item = ITEM.itemName27;
				else if (random1 == 7)
					item = ITEM.itemName28;
				else if (random1 == 8)
					item = ITEM.itemName29;
				else if (random1 == 9)
					item = ITEM.itemName30;
			} else if (ITEM.getlevel() == 4) {
				if (random1 == 0)
					item = ITEM.itemName31;
				else if (random1 == 1)
					item = ITEM.itemName32;
				else if (random1 == 2)
					item = ITEM.itemName33;
				else if (random1 == 3)
					item = ITEM.itemName34;
				else if (random1 == 4)
					item = ITEM.itemName35;
				else if (random1 == 5)
					item = ITEM.itemName36;
				else if (random1 == 6)
					item = ITEM.itemName37;
				else if (random1 == 7)
					item = ITEM.itemName38;
				else if (random1 == 8)
					item = ITEM.itemName39;
				else if (random1 == 9)
					item = ITEM.itemName40;
			}
		} while (PLAYER.getitem().equals(item));
		if (ITEM.getlevel() == 0 || ITEM.getlevel() == 1) {
			random2 = 1;
			playbg.setBackgroundResource(R.drawable.tabibito);
			console.setText(Html.fromHtml("旅人「あなたの持っている<br/>　　　<font color=\"#a67e4e\">"
					+ PLAYER.getitem() + "</font>と私の<font color=\"#a67e4e\">" + item
					+ "</font>を<br/>　　　交換しませんか？」"));
			button1.setText("はい");
			button2.setText("いいえ");
		} else if (ITEM.getlevel() == 2) {
			random2 = (int) Math.floor(Math.random() * 2);
			playbg.setBackgroundResource(R.drawable.kodomo);
			console.setText(Html.fromHtml("子供「<font color=\"#a67e4e\">" + PLAYER.getitem()
					+ "</font>がどうしても<br/>　　　必要なんだ<br/>　　　おいらにくれないか」"));
			button1.setText("あげる");
			button2.setText("やらん");
		} else if (ITEM.getlevel() == 3) {
			random2 = 1;
			playbg.setBackgroundResource(R.drawable.hugou);
			console.setText(Html.fromHtml("富豪「<font color=\"#a67e4e\">" + PLAYER.getitem()
					+ "</font>が必要なの<br/>　　　譲ってくれるなら<br/>　　　代わりのものをやるが」"));
			button1.setText("いいよ");
			button2.setText("いやだ");
		} else if (ITEM.getlevel() == 4) {
			random2 = 1;
			playbg.setBackgroundResource(R.drawable.king);
			console.setText(Html.fromHtml("王様「<font color=\"#a67e4e\">" + PLAYER.getitem()
					+ "</font>がわしには<br/>　　　必要なのじゃ<br/>　　　譲ると申すなら代わりに<br/>　　　<font color=\"#a67e4e\">"
					+ item + "</font>を授ける」"));
			button1.setText("どうぞ");
			button2.setText("ご勘弁を");
		}
		status();
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (random2 == 0) {
					SOUND.itemup();
				} else if (random2 == 1) {
					SOUND.itemget();
				}
				if (ITEM.getlevel() == 0 || ITEM.getlevel() == 1) {
					console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + PLAYER.getitem()
							+ "</font>と<font color=\"#a67e4e\">" + item
							+ "</font>を交換しました<br/><font color=\"#a67e4e\">" + item
							+ "</font>を手に入れた。"));
					if (random1 == 0)
						ITEM.item(1);
					else if (random1 == 1)
						ITEM.item(2);
					else if (random1 == 2)
						ITEM.item(3);
					else if (random1 == 3)
						ITEM.item(4);
					else if (random1 == 4)
						ITEM.item(5);
					else if (random1 == 5)
						ITEM.item(6);
					else if (random1 == 6)
						ITEM.item(7);
					else if (random1 == 7)
						ITEM.item(8);
					else if (random1 == 8)
						ITEM.item(9);
					else if (random1 == 9)
						ITEM.item(10);
				} else if (ITEM.getlevel() == 2) {
					if (random2 == 0) {
						console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + PLAYER.getitem()
								+ "</font>を子供に手渡した<br/>子供「ありがとう<br/>　　　本当にありがとう」"));
						ITEM.setnull();
					} else if (random2 == 1) {
						console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + PLAYER.getitem()
								+ "</font>を子供に手渡した<br/>子供「ありがとう、代わりに<br/>　　　<font color=\"#a67e4e\">"
								+ item + "</font>をあげる」<br/><font color=\"#a67e4e\">" + item
								+ "</font>を手に入れた"));
						if (random1 == 0) {
							ITEM.item(11);
						} else if (random1 == 1) {
							ITEM.item(12);
						} else if (random1 == 2) {
							ITEM.item(13);
						} else if (random1 == 3) {
							ITEM.item(14);
						} else if (random1 == 4) {
							ITEM.item(15);
						} else if (random1 == 5) {
							ITEM.item(16);
						} else if (random1 == 6) {
							ITEM.item(17);
						} else if (random1 == 7) {
							ITEM.item(18);
						} else if (random1 == 8) {
							ITEM.item(19);
						} else if (random1 == 9) {
							ITEM.item(20);
						}
					}
				} else if (ITEM.getlevel() == 3) {
					console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + PLAYER.getitem()
							+ "</font>を富豪に渡した<br/>富豪「ありがとう、代わりに<br/>　　　<font color=\"#a67e4e\">"
							+ item + "</font>をあげよう」<br/><font color=\"#a67e4e\">" + item
							+ "</font>を手に入れた"));
					if (random1 == 0) {
						ITEM.item(21);
					} else if (random1 == 1) {
						ITEM.item(22);
					} else if (random1 == 2) {
						ITEM.item(23);
					} else if (random1 == 3) {
						ITEM.item(24);
					} else if (random1 == 4) {
						ITEM.item(25);
					} else if (random1 == 5) {
						ITEM.item(26);
					} else if (random1 == 6) {
						ITEM.item(27);
					} else if (random1 == 7) {
						ITEM.item(28);
					} else if (random1 == 8) {
						ITEM.item(29);
					} else if (random1 == 9) {
						ITEM.item(30);
					}
				} else if (ITEM.getlevel() == 4) {
					console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + PLAYER.getitem()
							+ "</font>を王様に献上した<br/><font color=\"#a67e4e\">" + item
							+ "</font>を手に入れた"));
					if (random1 == 0)
						ITEM.item(31);
					else if (random1 == 1)
						ITEM.item(32);
					else if (random1 == 2)
						ITEM.item(33);
					else if (random1 == 3)
						ITEM.item(34);
					else if (random1 == 4)
						ITEM.item(35);
					else if (random1 == 5)
						ITEM.item(36);
					else if (random1 == 6)
						ITEM.item(37);
					else if (random1 == 7)
						ITEM.item(38);
					else if (random1 == 8)
						ITEM.item(39);
					else if (random1 == 9)
						ITEM.item(40);
				}
				exitevent();
				status();
			}
		});
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

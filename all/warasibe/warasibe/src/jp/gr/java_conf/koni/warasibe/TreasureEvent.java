package jp.gr.java_conf.koni.warasibe;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class TreasureEvent extends CORE {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SOUND.Treasureinit(getApplicationContext());
		if (statusme(76, 200)) {
			playbg.setBackgroundResource(R.drawable.meikyuu1);
			console.setText("大迷宮がある。\n入りますか？");
			button1.setText("入る");
		} else if (statusme(51, 75)) {
			playbg.setBackgroundResource(R.drawable.mori1);
			console.setText("迷いの森がある。\n進みますか？");
			button1.setText("進む");
		} else if (statusme(26, 50)) {
			playbg.setBackgroundResource(R.drawable.takarabako1);
			console.setText("宝箱がある。\n開けますか？");
			button1.setText("開ける");
		} else if (statusme(0, 25)) {
			playbg.setBackgroundResource(R.drawable.ana1);
			console.setText("何かが埋まっている。\n掘りますか？");
			button1.setText("掘る");
		}
		status();
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				random1 = (int) Math.floor(Math.random() * 10);
				if (statusme(76, 200)) {
					playbg.setBackgroundResource(R.drawable.meikyuu2);
					if (tf1) {
						SOUND.itemget();
						if (random2 == 0)
							ITEM.item(31);
						else if (random2 == 1)
							ITEM.item(35);
						console.setText(Html
								.fromHtml("<font color=\"#a67e4e\">" + item + "</font>を手に入れた。"));
						exitevent();
					} else if (random1 == 1 || random1 == 5) {
						random2 = (int) Math.floor(Math.random() * 2);
						if (random2 == 0)
							item = ITEM.itemName31;
						else if (random2 == 1)
							item = ITEM.itemName35;
						if (PLAYER.getitem().equals("なし")) {
							SOUND.itemget();
							if (random2 == 0)
								ITEM.item(31);
							else if (random2 == 1)
								ITEM.item(35);
							console.setText(Html.fromHtml(
									"<font color=\"#a67e4e\">" + item + "</font>を手に入れた。"));
							exitevent();
						} else {
							SOUND.itemup();
							console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + item
									+ "</font>を見つけた。<br/><font color=\"#a67e4e\">"
									+ PLAYER.getitem() + "</font>と交換しますか？"));
							button1.setText("はい");
							button2.setText("いいえ");
							tf1 = true;
						}
					} else {
						SOUND.no();
						console.setText("行き止まりだ・・・");
						exitevent();
					}
				} else if (statusme(51, 75)) {
					playbg.setBackgroundResource(R.drawable.mori2);
					if (tf1) {
						SOUND.itemget();
						if (random2 == 0)
							ITEM.item(21);
						else if (random2 == 1)
							ITEM.item(22);
						else if (random2 == 2)
							ITEM.item(23);
						else if (random2 == 3)
							ITEM.item(24);
						console.setText(Html
								.fromHtml("<font color=\"#a67e4e\">" + item + "</font>を手に入れた。"));
						exitevent();
					} else if (random1 == 1 || random1 == 5) {
						random2 = (int) Math.floor(Math.random() * 4);
						if (random2 == 0)
							item = ITEM.itemName21;
						else if (random2 == 1)
							item = ITEM.itemName22;
						else if (random2 == 2)
							item = ITEM.itemName23;
						else if (random2 == 3)
							item = ITEM.itemName24;
						if (PLAYER.getitem().equals("なし")) {
							SOUND.itemget();
							if (random2 == 0)
								ITEM.item(21);
							else if (random2 == 1)
								ITEM.item(22);
							else if (random2 == 2)
								ITEM.item(23);
							else if (random2 == 3)
								ITEM.item(24);
							console.setText(Html.fromHtml(
									"<font color=\"#a67e4e\">" + item + "</font>を手に入れた。"));
							exitevent();
						} else {
							SOUND.itemup();
							console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + item
									+ "</font>を見つけた。<br/><font color=\"#a67e4e\">"
									+ PLAYER.getitem() + "</font>と交換しますか？"));
							button1.setText("はい");
							button2.setText("いいえ");
							tf1 = true;
						}
					} else {
						SOUND.no();
						console.setText("迷った・・・");
						exitevent();
					}
				} else if (statusme(26, 50)) {
					playbg.setBackgroundResource(R.drawable.takarabako2);
					if (tf1) {
						SOUND.itemget();
						if (random2 == 0)
							ITEM.item(11);
						else if (random2 == 1)
							ITEM.item(12);
						else if (random2 == 2)
							ITEM.item(13);
						else if (random2 == 3)
							ITEM.item(14);
						else if (random2 == 4)
							ITEM.item(15);
						console.setText(Html
								.fromHtml("<font color=\"#a67e4e\">" + item + "</font>を手に入れた。"));
						exitevent();
					} else if (random1 == 1 || random1 == 5) {
						random2 = (int) Math.floor(Math.random() * 5);
						if (random2 == 0)
							item = ITEM.itemName11;
						else if (random2 == 1)
							item = ITEM.itemName12;
						else if (random2 == 2)
							item = ITEM.itemName13;
						else if (random2 == 3)
							item = ITEM.itemName14;
						else if (random2 == 4)
							item = ITEM.itemName15;
						if (PLAYER.getitem().equals("なし")) {
							SOUND.itemget();
							if (random2 == 0)
								ITEM.item(11);
							else if (random2 == 1)
								ITEM.item(12);
							else if (random2 == 2)
								ITEM.item(13);
							else if (random2 == 3)
								ITEM.item(14);
							else if (random2 == 4)
								ITEM.item(15);
							console.setText(Html.fromHtml(
									"<font color=\"#a67e4e\">" + item + "</font>を手に入れた。"));
							exitevent();
						} else {
							SOUND.itemup();
							console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + item
									+ "</font>を見つけた。<br/><font color=\"#a67e4e\">"
									+ PLAYER.getitem() + "</font>と交換しますか？"));
							button1.setText("はい");
							button2.setText("いいえ");
							tf1 = true;
						}
					} else {
						SOUND.no();
						console.setText("空っぽだった・・・");
						exitevent();
					}
				} else if (statusme(0, 25)) {
					playbg.setBackgroundResource(R.drawable.ana2);
					if (tf1) {
						SOUND.itemget();
						if (random2 == 0)
							ITEM.item(0);
						else if (random2 == 1)
							ITEM.item(2);
						else if (random2 == 2)
							ITEM.item(5);
						else if (random2 == 3)
							ITEM.item(6);
						else if (random2 == 4)
							ITEM.item(7);
						console.setText(Html
								.fromHtml("<font color=\"#a67e4e\">" + item + "</font>を手に入れた。"));
						exitevent();
					} else if (random1 == 1 || random1 == 5) {
						random2 = (int) Math.floor(Math.random() * 5);
						if (random2 == 0)
							item = ITEM.itemName0;
						else if (random2 == 1)
							item = ITEM.itemName2;
						else if (random2 == 2)
							item = ITEM.itemName5;
						else if (random2 == 3)
							item = ITEM.itemName6;
						else if (random2 == 4)
							item = ITEM.itemName7;
						if (PLAYER.getitem().equals("なし")) {
							SOUND.itemget();
							if (random2 == 0)
								ITEM.item(0);
							else if (random2 == 1)
								ITEM.item(2);
							else if (random2 == 2)
								ITEM.item(5);
							else if (random2 == 3)
								ITEM.item(6);
							else if (random2 == 4)
								ITEM.item(7);
							console.setText(Html.fromHtml(
									"<font color=\"#a67e4e\">" + item + "</font>を手に入れた。"));
							exitevent();
						} else {
							SOUND.itemup();
							console.setText(Html.fromHtml("<font color=\"#a67e4e\">" + item
									+ "</font>を見つけた。<br/><font color=\"#a67e4e\">"
									+ PLAYER.getitem() + "</font>と交換しますか？"));
							button1.setText("はい");
							button2.setText("いいえ");
							tf1 = true;
						}
					} else {
						SOUND.no();
						console.setText("何もなかった・・・");
						exitevent();
					}
				}
			}
		});
		button2.setText("やめとく");
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

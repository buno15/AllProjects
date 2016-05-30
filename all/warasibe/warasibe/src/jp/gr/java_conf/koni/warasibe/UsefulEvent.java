package jp.gr.java_conf.koni.warasibe;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class UsefulEvent extends CORE {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SOUND.Usefulinit(getApplicationContext());

		if (ITEM.getlevel() == 4) {
			playbg.setBackgroundResource(R.drawable.himenokerai);
			console.setText(Html.fromHtml(
					"家来「<font color=\"#a67e4e\">" + PLAYER.getitem() + "</font>を姫がご所望だ」"));
			button1.setText("仰せのままに");
			button2.setText("ご勘弁を");

		} else if (ITEM.getlevel() == 3) {
			playbg.setBackgroundResource(R.drawable.reizyou);
			if (PLAYER.getitem().equals(ITEM.itemName21)) {
				console.setText(Html.fromHtml("令嬢「<font color=\"#a67e4e\">" + PLAYER.getitem()
						+ "</font>を譲って<br/>　　　宝石には目がなくて」"));
			} else if (PLAYER.getitem().equals(ITEM.itemName22)
					|| PLAYER.getitem().equals(ITEM.itemName23)
					|| PLAYER.getitem().equals(ITEM.itemName24)
					|| PLAYER.getitem().equals(ITEM.itemName25)) {
				console.setText(Html.fromHtml(
						"令嬢「<font color=\"#a67e4e\">" + PLAYER.getitem() + "</font>を譲っていただける？」"));
			}
			button1.setText("どうそ");
			button2.setText("お断り");

		} else if (ITEM.getlevel() == 2) {
			playbg.setBackgroundResource(R.drawable.wakamono);
			if (PLAYER.getitem().equals(ITEM.itemName11) || PLAYER.getitem().equals(ITEM.itemName12)
					|| PLAYER.getitem().equals(ITEM.itemName13)) {
				console.setText(Html.fromHtml("若者「<font color=\"#a67e4e\">" + PLAYER.getitem()
						+ "</font>を譲ってくれないかい<br/>　　　飼いたいんだ」"));
			} else if (PLAYER.getitem().equals(ITEM.itemName14)
					|| PLAYER.getitem().equals(ITEM.itemName15)) {
				console.setText(Html.fromHtml("若者「<font color=\"#a67e4e\">" + PLAYER.getitem()
						+ "</font>を譲ってくれ<br/>　　　家で使いたいんだ」"));
			}
			button1.setText("あげる");
			button2.setText("あげない");

		} else if (ITEM.getlevel() == 1) {
			playbg.setBackgroundResource(R.drawable.huzinn);
			if (PLAYER.getitem().equals(ITEM.itemName1) || PLAYER.getitem().equals(ITEM.itemName2)
					|| PLAYER.getitem().equals(ITEM.itemName3)
					|| PLAYER.getitem().equals(ITEM.itemName4)) {
				console.setText(Html.fromHtml("ご婦人「<font color=\"#a67e4e\">" + PLAYER.getitem()
						+ "</font>をくれないかね<br/>　　　　腹ペコで動けないんだ」"));
			} else if (PLAYER.getitem().equals(ITEM.itemName5)
					|| PLAYER.getitem().equals(ITEM.itemName6)
					|| PLAYER.getitem().equals(ITEM.itemName7)) {
				console.setText(Html.fromHtml("ご婦人「<font color=\"#a67e4e\">" + PLAYER.getitem()
						+ "</font>をくれないかね<br/>　　　　家で使いたいんだ」"));
			}
			button1.setText("いいよ");
			button2.setText("だめ");

		}
		status();
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				random1 = (int) Math.floor(Math.random() * 3);
				if (ITEM.getlevel() == 4) {

					if (random1 == 0) {
						ITEM.setnull();
						SOUND.getmoney();
						random2 = rnd.nextInt(101) + 300;
						console.setText(
								Html.fromHtml("家来「では、褒美をつかわす」<br/><br/><font color=\"#aaaa00\">"
										+ random2 + "</font>GSB手に入れた"));
						PLAYER.setmoney(PLAYER.getmoney() + random2);
					} else if (random1 == 1) {
						SOUND.itemget();
						random2 = (int) Math.floor(Math.random() * 3);
						if (random2 == 0)
							ITEM.item(38);
						else if (random2 == 1)
							ITEM.item(39);
						else if (random2 == 2)
							ITEM.item(40);
						console.setText(Html.fromHtml(
								"家来「では代わりに<br/>　　　<font color=\"#a67e4e\">" + PLAYER.getitem()
										+ "</font>を与える」<br/><br/><font color=\"#a67e4e\">"
										+ PLAYER.getitem() + "</font>を手に入れた"));
					} else if (random1 == 2) {
						ITEM.setnull();
						SOUND.itemup();
						console.setText("家来「ご苦労」");
					}

				} else if (ITEM.getlevel() == 3) {

					if (random1 == 0) {
						ITEM.setnull();
						SOUND.getmoney();
						random2 = rnd.nextInt(51) + 100;
						console.setText(Html.fromHtml(
								"令嬢「ありがとう<br/>　　　これはお礼よ」<br/><br/><font color=\"#aaaa00\">"
										+ random2 + "</font>GSB手に入れた"));
						PLAYER.setmoney(PLAYER.getmoney() + random2);
					} else if (random1 == 1) {
						SOUND.itemget();
						random2 = (int) Math.floor(Math.random() * 3);
						if (random2 == 0)
							ITEM.item(28);
						else if (random2 == 1)
							ITEM.item(29);
						else if (random2 == 2)
							ITEM.item(30);
						console.setText(Html.fromHtml(
								"令嬢「ありがとう、では代わりに<br/>　　　<font color=\"#a67e4e\">" + PLAYER.getitem()
										+ "</font>を受け取って」<br/><br/><font color=\"#a67e4e\">"
										+ PLAYER.getitem() + "</font>を手に入れた"));
					} else if (random1 == 2) {
						ITEM.setnull();
						SOUND.itemup();
						console.setText("令嬢「ありがとう、うれしいわ」");
					}

				} else if (ITEM.getlevel() == 2) {

					if (random1 == 0) {
						ITEM.setnull();
						SOUND.getmoney();
						random2 = rnd.nextInt(11) + 30;
						console.setText(Html.fromHtml(
								"若者「ありがとう<br/>　　　これはお礼だ」<br/><br/><font color=\"#aaaa00\">"
										+ random2 + "</font>GSB手に入れた"));
						PLAYER.setmoney(PLAYER.getmoney() + random2);
					} else if (random1 == 1) {
						SOUND.itemget();
						random2 = (int) Math.floor(Math.random() * 3);
						if (random2 == 0)
							ITEM.item(18);
						else if (random2 == 1)
							ITEM.item(19);
						else if (random2 == 2)
							ITEM.item(20);
						console.setText(Html.fromHtml(
								"若者「ありがとう、代わりに<br/>　　　<font color=\"#a67e4e\">" + PLAYER.getitem()
										+ "</font>をもらってくれ」<br/><br/><font color=\"#a67e4e\">"
										+ PLAYER.getitem() + "</font>を手に入れた"));
					} else if (random1 == 2) {
						ITEM.setnull();
						SOUND.itemup();
						console.setText("若者「どうもありがとう」");
					}

				} else if (ITEM.getlevel() == 1) {

					if (random1 == 0) {
						ITEM.setnull();
						SOUND.getmoney();
						random2 = rnd.nextInt(11) + 5;
						console.setText(Html.fromHtml(
								"ご婦人「ありがとう<br/>　　　　お礼と言っては何だけど」<br/><br/><font color=\"#aaaa00\">"
										+ random2 + "</font>GSB手に入れた"));
						PLAYER.setmoney(PLAYER.getmoney() + random2);
					} else if (random1 == 1) {
						SOUND.itemget();
						random2 = (int) Math.floor(Math.random() * 3);
						if (random2 == 0)
							ITEM.item(8);
						else if (random2 == 1)
							ITEM.item(9);
						else if (random2 == 2)
							ITEM.item(10);
						console.setText(Html.fromHtml(
								"ご婦人「ありがとう、代わりに<br/>　　　　<font color=\"#a67e4e\">" + PLAYER.getitem()
										+ "</font>をあげるよ」<br/><br/><font color=\"#a67e4e\">"
										+ PLAYER.getitem() + "</font>を手に入れた"));
					} else if (random1 == 2) {
						ITEM.setnull();
						SOUND.itemup();
						console.setText("ご婦人「ありがとね」");
					}

				}
				status();
				exitevent();
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

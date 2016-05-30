package jp.gr.java_conf.koni.warasibe;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class HotelEvent extends CORE {
	static boolean hotel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SOUND.Hotelinit(getApplicationContext());

		playbg.setBackgroundResource(R.drawable.yadoya);
		console.setText("宿屋「いらっしゃいませ\n　　　ご宿泊ですか？」");
		button1.setText("はい");
		button2.setText("いいえ");
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				console.setText("宿屋「どのお部屋になさいますか？」\n\n　　　豪華な部屋：500 GSB\n　　　普通の部屋：100 GSB");
				button1.setText("豪華な部屋");
				button1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (PLAYER.getmoney() < 500) {
							SOUND.no();
							console.setText("宿屋「冷やかしかい」");
						} else {
							SOUND.powerup();
							console.setText(Html.fromHtml(
									"あなたは休んだ<br/>体力が<font color=\"#00cc00\">40</font>回復した"));
							PLAYER.setmoney(PLAYER.getmoney() - 500);
							PLAYER.setdamage(PLAYER.getdamage() + 40);
							if (PLAYER.getdamage() - PLAYER.getadd(0) > PLAYER.gethp())
								PLAYER.setdamage(PLAYER.gethp() + PLAYER.getadd(0));
							status();
						}
						exitevent();
					}
				});
				button2.setText("普通の部屋");
				button2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (PLAYER.getmoney() < 100) {
							SOUND.no();
							console.setText("宿屋「金がない？\n　　　二度と来るな！」");
						} else {
							SOUND.powerup();
							console.setText(Html.fromHtml(
									"あなたは休んだ<br/>体力が<font color=\"#00cc00\">20</font>回復した"));
							PLAYER.setmoney(PLAYER.getmoney() - 100);
							PLAYER.setdamage(PLAYER.getdamage() + 20);
							if (PLAYER.getdamage() - PLAYER.getadd(0) > PLAYER.gethp())
								PLAYER.setdamage(PLAYER.gethp() + PLAYER.getadd(0));
							status();
						}
						exitevent();
					}
				});
				status();
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				moveevent();
			}
		});
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

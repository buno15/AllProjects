package jp.gr.java_conf.koni.warasibe;

import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class RecoveryEvent extends CORE {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		status();
		random2 = (int) Math.floor(Math.random() * 2);
		SOUND.Recoveryinit(getApplicationContext());
		SOUND.soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if (0 == status) {
					if (random2 == 0)
						SOUND.recovery();
					else if (random2 == 1)
						SOUND.powerup();
				}
			}
		});
		if (random2 == 0) {
			playbg.setBackgroundResource(R.drawable.sister);
			random1 = PLAYER.gethp() - (PLAYER.getdamage() - PLAYER.add[0]);
			console.setText(Html.fromHtml(
					"シスター「傷だらけですね<br/>　　　　　休んでいくとよいでしょう」<br/>体力が<font color=\"#00cc00\">全回復</font>した。"));
			PLAYER.setdamage(PLAYER.getdamage() + random1);
		} else if (random2 == 1) {
			playbg.setBackgroundResource(R.drawable.gohuzinn);
			random1 = PLAYER.gethp() / 2;
			console.setText(Html.fromHtml(
					"優しいご婦人「あんた疲れているね<br/>　　　　　　　休んでくといいよ」<br/>体力が<font color=\"#00cc00\">"
							+ random1 + "</font>回復した。"));
			PLAYER.setdamage(PLAYER.getdamage() + random1);
		}
		button1.setText("ありがとう");
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				moveevent();
			}
		});
		button2.setText("Thank you");
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

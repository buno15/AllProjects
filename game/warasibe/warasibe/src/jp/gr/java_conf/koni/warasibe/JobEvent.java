package jp.gr.java_conf.koni.warasibe;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class JobEvent extends CORE {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SOUND.Jobinit(getApplicationContext());
		random1 = (int) Math.floor(Math.random() * 3);
		if (statusme(76, 200)) {
			button1.setText("お任せを");
			playbg.setBackgroundResource(R.drawable.gyokuza);
			if (random1 == 0) {
				console.setText("王様「そなたを近衛隊長に任命する\n　　　王宮を守るのじゃ！」");
			} else if (random1 == 1) {
				console.setText("王様「そなたを将軍に任命する\n　　　国の平和を守るのじゃ！」");
			} else if (random1 == 2) {
				console.setText("王様「そなたを大臣に任命する\n　　　国の維持に努めるのじゃ！」");
			}
			button2.setText("お断りする");
		} else if (statusme(51, 75)) {
			button1.setText("引き受けた");
			if (random1 == 0) {
				playbg.setBackgroundResource(R.drawable.yadoyanosyuzinn);
				console.setText("一流宿屋「最近サービスが悪いと\n　　　　　言われるんだ\n　　　　　監督してくれないかね？」");
				button2.setText("お役に立てない");
			} else if (random1 == 1) {
				playbg.setBackgroundResource(R.drawable.syougunn);
				console.setText("将軍「この度きみに召集がかかった\n　　　隣国に攻め入るのだ\n　　　ともに行こう！」");
				button2.setText("戦えません");
			} else if (random1 == 2) {
				playbg.setBackgroundResource(R.drawable.kazinonosyuzinn);
				console.setText("カジノの支配人「警備が不足していて\n　　　　　　　きみやってくれない？」");
				button2.setText("務まりません");
			}
		} else if (statusme(26, 50)) {
			button1.setText("まかせて");
			if (random1 == 0) {
				playbg.setBackgroundResource(R.drawable.sakanaya);
				console.setText("魚屋「最近売り上げが悪いんだ\n　　　呼び込みをやってくれないか？」");
				button2.setText("役に立てない");
			} else if (random1 == 1) {
				playbg.setBackgroundResource(R.drawable.taityou);
				console.setText("隊長「君はなかなか強そうだな\n　　　蛮族を退治するのだが\n　　　参加するかね？」");
				button2.setText("私は弱いです");
			} else if (random1 == 2) {
				playbg.setBackgroundResource(R.drawable.sakabanosyuzinn);
				console.setText("酒場の主人「人手が足りなくて困って\n　　　　　　いるんだ、あんた店を\n　　　　　　手伝ってくれないかね？」");
				button2.setText("人見知りだから");
			}
		} else if (statusme(0, 25)) {
			button1.setText("いいよ");
			if (random1 == 0) {
				playbg.setBackgroundResource(R.drawable.kouzann);
				console.setText("鉱員「人手が足りなくて困っているんだ\n　　　あんた手伝ってくれないかね？」");
				button2.setText("用事があるから");
			} else if (random1 == 1) {
				playbg.setBackgroundResource(R.drawable.ryousi);
				console.setText("猟師「狩りに行きたいのだが\n　　　一人じゃ不安なんだ\n　　　一緒に来てくれないかね？」");
				button2.setText("そんな時間はない");
			} else if (random1 == 2) {
				playbg.setBackgroundResource(R.drawable.zakkaya);
				console.setText("雑貨屋「雇っていたのが急に休んじまっ\n　　　　て店番してくれないかね？」");
				button2.setText("人見知りだから");
			}
		}
		status();
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SOUND.powerup();
				if (statusme(76, 200)) {
					random2 = rnd.nextInt(501) + 100;
					if (random1 == 0) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.sethp(PLAYER.gethp() + 5);
						console.setText(Html.fromHtml(
								"あなたは王宮の警備をした<br/><font color=\"#00cc00\">体力</font>がついた<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					} else if (random1 == 1) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.setpower(PLAYER.getpower() + 5);
						console.setText(Html.fromHtml(
								"あなたは軍を指揮した<br/><font color=\"#ff0033\">力</font>が上がった<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					} else if (random1 == 2) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.setinsight(PLAYER.getinsight() + 5);
						console.setText(Html.fromHtml(
								"あなたは大臣を務めた<br/><font color=\"#00a0dd\">洞察力</font>が上がった<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					}
				} else if (statusme(51, 75)) {
					random2 = rnd.nextInt(51) + 50;
					if (random1 == 0) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.sethp(PLAYER.gethp() + 3);
						console.setText(Html.fromHtml(
								"あなたは監督をした<br/>おかげで宿屋は繁盛<br/><font color=\"#00cc00\">体力</font>がついた<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					} else if (random1 == 1) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.setpower(PLAYER.getpower() + 3);
						console.setText(Html.fromHtml(
								"あなたは隣国へ向かった<br/><font color=\"#ff0033\">力</font>が上がった<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					} else if (random1 == 2) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.setinsight(PLAYER.getinsight() + 3);
						console.setText(Html.fromHtml(
								"あなたはカジノで警備をした<br/><font color=\"#00a0dd\">洞察力</font>が上がった<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					}
				} else if (statusme(26, 50)) {
					random2 = rnd.nextInt(11) + 20;
					if (random1 == 0) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.sethp(PLAYER.gethp() + 2);
						console.setText(Html.fromHtml(
								"あなたは呼び込みをした<br/>おかげで店は繁盛<br/><font color=\"#00cc00\">体力</font>がついた<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					} else if (random1 == 1) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.setpower(PLAYER.getpower() + 2);
						console.setText(Html.fromHtml(
								"あなたは蛮族と戦った<br/><font color=\"#ff0033\">力</font>が上がった<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					} else if (random1 == 2) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.setinsight(PLAYER.getinsight() + 2);
						console.setText(Html.fromHtml(
								"あなたはしばらく酒場で働いた<br/><font color=\"#00a0dd\">洞察力</font>が上がった<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					}
				} else if (statusme(0, 25)) {
					random2 = rnd.nextInt(10) + 1;
					if (random1 == 0) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.sethp(PLAYER.gethp() + 1);
						console.setText(Html.fromHtml(
								"あなたは一生懸命働いた<br/><font color=\"#00cc00\">体力</font>がついた<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					} else if (random1 == 1) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.setpower(PLAYER.getpower() + 1);
						console.setText(Html.fromHtml(
								"あなたは猛獣と戦った<br/><font color=\"#ff0033\">力</font>が上がった<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
					} else if (random1 == 2) {
						PLAYER.setmoney(PLAYER.getmoney() + random2);
						PLAYER.setinsight(PLAYER.getinsight() + 1);
						console.setText(Html.fromHtml(
								"あなたはしばらく店番をした<br/><font color=\"#00a0dd\">洞察力</font>が上がった<br/>報酬として<font color=\"#aaaa00\">"
										+ random2 + "</font>GSBもらった"));
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

package jp.gr.java_conf.koni.warasibe;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

public class BattleEvent extends CORE {
	int				id	= 0, enemyanimeid;
	static boolean	a	= false, bosstf, escapetf = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SOUND.Battleinit(getApplicationContext());
		if (bosstf) {
			enemyanimeid = 16;
			enemyanime(enemyanimeid);
			enemyhp = 900;
			enemypower = 20;
			enemyname = "恐怖の大王";
		} else {
			random2 = (int) Math.floor(Math.random() * 4);
			if (statusme(76, 200)) {
				enemyhp = (rnd.nextInt(25) + 86) + (rnd.nextInt(25) + 76) + (rnd.nextInt(25) + 76);
				if (random2 == 0) {
					enemyanimeid = 15;
					enemyanime(enemyanimeid);
					enemypower = 17;
					enemyname = "リヴァイアサン";
				} else if (random2 == 1) {
					enemyanimeid = 14;
					enemyanime(enemyanimeid);
					enemypower = 16;
					enemyname = "ベヒーモス";
				} else if (random2 == 2) {
					enemyanimeid = 13;
					enemyanime(enemyanimeid);
					enemypower = 14;
					enemyname = "ヨルムンガンド";
				} else if (random2 == 3) {
					enemyanimeid = 12;
					enemyanime(enemyanimeid);
					enemypower = 15;
					enemyname = "ヒュドラー";
				}
			} else if (statusme(51, 75)) {
				enemyhp = (rnd.nextInt(25) + 61) + (rnd.nextInt(25) + 51) + (rnd.nextInt(25) + 51);
				if (random2 == 0) {
					enemyanimeid = 11;
					enemyanime(enemyanimeid);
					enemypower = 10;
					enemyname = "吸血鬼";
				} else if (random2 == 1) {
					enemyanimeid = 10;
					enemyanime(enemyanimeid);
					enemypower = 5;
					enemyname = "サイクロプス";
				} else if (random2 == 2) {
					enemyanimeid = 9;
					enemyanime(enemyanimeid);
					enemypower = 5;
					enemyname = "ミノタウロス";
				} else if (random2 == 3) {
					enemyanimeid = 8;
					enemyanime(enemyanimeid);
					enemypower = 7;
					enemyname = "キマイラ";
				}
			} else if (statusme(26, 50)) {
				enemyhp = (rnd.nextInt(25) + 36) + (rnd.nextInt(25) + 26) + (rnd.nextInt(25) + 26);
				if (random2 == 0) {
					enemyanimeid = 7;
					enemyanime(enemyanimeid);
					enemypower = 5;
					enemyname = "バイス将軍";
				} else if (random2 == 1) {
					enemyanimeid = 6;
					enemyanime(enemyanimeid);
					enemypower = 3;
					enemyname = "ならず者のボス";
				} else if (random2 == 2) {
					enemyanimeid = 5;
					enemyanime(enemyanimeid);
					enemypower = 4;
					enemyname = "オーガ";
				} else if (random2 == 3) {
					enemyanimeid = 4;
					enemyanime(enemyanimeid);
					enemypower = 4;
					enemyname = "ゴブリン";
				}
			} else if (statusme(0, 25)) {
				enemyhp = (rnd.nextInt(25) + 11) + (rnd.nextInt(25) + 1);
				if (random2 == 0) {
					enemyanimeid = 3;
					enemyanime(enemyanimeid);
					enemypower = 2;
					enemyname = "追い剥ぎ";
				} else if (random2 == 1) {
					enemyanimeid = 2;
					enemyanime(enemyanimeid);
					enemypower = 1;
					enemyname = "酔っぱらい";
				} else if (random2 == 2) {
					enemyanimeid = 1;
					enemyanime(enemyanimeid);
					enemypower = 2;
					enemyname = "異国の兵士";
				} else if (random2 == 3) {
					enemyanimeid = 0;
					enemyanime(enemyanimeid);
					enemypower = 3;
					enemyname = "クマ";
				}
			}
			if (statusme(26, 50)) {
				switch (random2) {
				case 0:
				case 1:
					a = true;
					break;
				case 2:
				case 3:
					a = false;
				}
			} else if (statusme(0, 25)) {
				switch (random2) {
				case 0:
				case 1:
				case 2:
					a = true;
					break;
				case 3:
					a = false;
				}
			}
		}
		console.setText(enemyname + "があらわれた");
		status();
		button1.setText("戦う");
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (tf3) {// アイテム入手
					SOUND.itemget();
					buttonoff();
					ITEM.item(id);
					console.setText(Html
							.fromHtml("<font color=\"#a67e4e\">" + item + "</font>" + "を手に入れた"));
					exitevent();
				} else if (tf2) { // 旅を続ける
					if (bosstf) {
						i = new Intent(getApplicationContext(), GameClear.class);
						startActivity(i);
						finish();
					} else
						moveevent();
				} else {// 戦闘
					enemydamage += PLAYER.getpower();
					if (enemydamage >= enemyhp) {// 倒す
						SOUND.attack();
						console.setText(Html.fromHtml("攻撃！<br/>" + enemyname
								+ "に<font color=\"#ff0033\">" + PLAYER.getpower()
								+ "</font>ダメージ<br/>" + enemyname + "を倒した"));
						if (t == null) {
							rn = 6;
							t = new Timer();
							t.schedule(new BattleTimer(), 450);
						}
						PLAYER.sethp(PLAYER.gethp() + 1);
						PLAYER.setpower(PLAYER.getpower() + 1);
						PLAYER.setinsight(PLAYER.getinsight() + 1);
						if ((PLAYER.gethp() - PLAYER.getadd(0)) >= 100)
							PLAYER.sethp(100 + PLAYER.getadd(0));
						if ((PLAYER.getpower() - PLAYER.getadd(1)) >= 100)
							PLAYER.setpower(100 + PLAYER.getadd(1));
						if ((PLAYER.getinsight() - PLAYER.getadd(2)) >= 100)
							PLAYER.setinsight(100 + PLAYER.getadd(2));
						if (bosstf) {
							button1.setText("勇者になる");
							button2.setText("旅を終わる");
						} else {
							button1.setText("旅を続ける");
							button2.setText("身ぐるみ剥ぐ");
						}
						tf2 = true;

					} else {// ダメージ
						if (nekutaru) {
							PLAYER.setdamage(PLAYER.getdamage() + 10);
							if ((PLAYER.getdamage() - PLAYER.getadd(0)) >= 100)
								PLAYER.setdamage(100 + PLAYER.getadd(0));
							if (PLAYER.getdamage() - PLAYER.getadd(0) > PLAYER.gethp())
								PLAYER.setdamage(PLAYER.gethp() + PLAYER.getadd(0));
						}
						status();
						SOUND.attack();
						console.setText(Html.fromHtml("攻撃！<br/>" + enemyname
								+ "に<font color=\"#ff0033\">" + PLAYER.getpower() + "</font>ダメージ"));
						buttonoff();
						if (t == null) {
							rn = 4;
							t = new Timer();
							t.schedule(new BattleTimer(), 380);
						}
					}
				}
			}
		});

		button2.setText("逃げる");
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (tf3) {
					moveevent();
				} else if (tf2) {// 倒す
					if (bosstf) {
						i = new Intent(getApplicationContext(), GameClear.class);
						startActivity(i);
						finish();
					} else {
						random1 = (int) Math.floor(Math.random() * 5);
						if (random1 == 0 || random1 == 3) {// 金
							SOUND.getmoney();
							buttonoff();
							if (statusme(76, 200))
								random2 = rnd.nextInt(101) + 300;
							else if (statusme(51, 75))
								random2 = rnd.nextInt(51) + 100;
							else if (statusme(26, 50))
								random2 = rnd.nextInt(11) + 30;
							else if (statusme(0, 25))
								random2 = rnd.nextInt(11) + 5;
							console.setText(Html.fromHtml(enemyname + "から<font color=\"#aaaa00\">"
									+ random2 + "</font>GSB手に入れた"));
							PLAYER.setmoney(PLAYER.getmoney() + random2);
							exitevent();
						} else if (random1 == 1 || random1 == 4) {// アイテム
							tf3 = true;
							if (statusme(76, 200)) {
								random1 = (int) Math.floor(Math.random() * 4);
								if (random1 == 0) {
									item = ITEM.itemName31;
									id = 31;
								} else if (random1 == 1) {
									item = ITEM.itemName35;
									id = 35;
								} else if (random1 == 2) {
									item = ITEM.itemName37;
									id = 37;
								} else if (random1 == 3) {
									item = ITEM.itemName40;
									id = 40;
								}
							} else if (statusme(51, 75)) {
								random1 = (int) Math.floor(Math.random() * 4);
								if (random1 == 0) {
									item = ITEM.itemName21;
									id = 21;
								} else if (random1 == 1) {
									item = ITEM.itemName26;
									id = 26;
								} else if (random1 == 2) {
									item = ITEM.itemName27;
									id = 27;
								} else if (random1 == 3) {
									item = ITEM.itemName30;
									id = 30;
								}
							} else if (statusme(26, 50)) {
								random1 = (int) Math.floor(Math.random() * 4);
								if (random1 == 0) {
									item = ITEM.itemName14;
									id = 14;
								} else if (random1 == 1) {
									item = ITEM.itemName15;
									id = 15;
								} else if (random1 == 2) {
									item = ITEM.itemName18;
									id = 18;
								} else if (random1 == 3) {
									item = ITEM.itemName19;
									id = 19;
								}
							} else if (statusme(0, 25)) {
								if (random2 == 0 || random2 == 1 || random2 == 2) {
									random1 = (int) Math.floor(Math.random() * 3);
									if (random1 == 0) {
										item = ITEM.itemName1;
										id = 1;
									} else if (random1 == 1) {
										item = ITEM.itemName7;
										id = 7;
									} else if (random1 == 2) {
										item = ITEM.itemName8;
										id = 8;
									}
								} else if (random2 == 3) {
									item = ITEM.itemName4;
									id = 4;
								}
							}
							if (PLAYER.getitem().equals("なし")) {
								SOUND.itemget();
								ITEM.item(id);
								console.setText(Html.fromHtml(
										"<font color=\"#a67e4e\">" + item + "</font>" + "を手に入れた"));
								exitevent();
							} else {
								SOUND.itemup();
								console.setText(
										Html.fromHtml(enemyname + "は<font color=\"#a67e4e\">" + item
												+ "</font>を持っていた<br/><font color=\"#a67e4e\">"
												+ PLAYER.getitem() + "</font>と交換しますか？"));
								button1.setText("はい");
								button2.setText("いいえ");
							}
						} else if (random1 == 2) {// 生き返る
							console.setText(enemyname + "が息を吹き返した");
							SOUND.playBGM1();
							SOUND.mediaPlayer1.start();
							SOUND.revive();
							enemyanime(enemyanimeid);
							tf2 = false;
							enemydamage = 0;
							buttonoff();
							if (t == null) {
								rn = 2;
								t = new Timer();
								t.schedule(new BattleTimer(), 1000);
							}
						}
					}
				} else if (tf1) {// 降参
					if (a) {
						SOUND.stopBGM1();
						SOUND.scam();
						console.setText(Html.fromHtml(
								"あなたは降参した<br/><font color=\"#aaaa00\">所持金</font>と<font color=\"#a67e4e\">アイテム</font>を奪われた"));
						PLAYER.setmoney(0);
						ITEM.setnull();
						exitevent();
					} else {
						escape();
					}
				} else {// 逃げる
					escape();
				}
			}
		});

	}

	class BattleTimer extends TimerTask {
		@Override
		public void run() {
			handle.post(new Runnable() {
				@Override
				public void run() {
					if (rn == 0) {// 選択
						if (statusme(51, 75) || statusme(76, 200))
							tf1 = false;
						else if (statusme(0, 25) || statusme(26, 50))
							tf1 = true;
						console.setText("選択してください。");
						status();
						button1.setEnabled(true);
						button2.setEnabled(true);
						button1.setText("戦う");
						if (a)
							button2.setText("降参する");
						if (!a || statusme(51, 75) || statusme(76, 200))
							button2.setText("逃げる");
						t.cancel();
						t = null;
					} else if (rn == 1) {// 逃げる
						SOUND.no();
						console.setText("あなたは逃げようとした。\nしかし回り込まれてしまった！");
						t.cancel();
						t = null;
						rn = 2;
						t = new Timer();
						t.schedule(new BattleTimer(), 1000);
					} else if (rn == 2) {// 敵攻撃
						PLAYER.setdamage(PLAYER.getdamage() - enemypower);
						SOUND.damage();
						console.setText(
								Html.fromHtml(enemyname + "の攻撃！<br/><font color=\"#00cc00\">"
										+ enemypower + "</font>ダメージ"));
						t.cancel();
						t = null;
						rn = 7;
						t = new Timer();
						t.schedule(new BattleTimer(), 600);
					} else if (rn == 3) {// ゲームクリア

					} else if (rn == 4) {// 敵消す→敵表示
						playbg.setBackgroundResource(R.drawable.attack);
						t.cancel();
						t = null;
						rn = 5;
						t = new Timer();
						t.schedule(new BattleTimer(), 100);
					} else if (rn == 5) {// 敵表示→攻撃
						enemyanime(enemyanimeid);
						t.cancel();
						t = null;
						rn = 2;
						t = new Timer();
						t.schedule(new BattleTimer(), 200);
					} else if (rn == 6) {// 敵消す→倒す
						SOUND.stopBGM1();
						SOUND.knock_down();
						playbg.setBackgroundResource(R.drawable.attack);
						t.cancel();
						t = null;
					} else if (rn == 7) {// ダメージ→敵表示
						playbg.setBackgroundResource(R.drawable.damage);
						t.cancel();
						t = null;
						rn = 8;
						t = new Timer();
						t.schedule(new BattleTimer(), 100);
					} else if (rn == 8) {// 敵表示→選択
						enemyanime(enemyanimeid);
						t.cancel();
						t = null;
						if (PLAYER.getdamage() <= 0) {
							rn = 1;
							t = new Timer();
							t.schedule(new CORETimer(), 100);
						} else {
							rn = 0;
							t = new Timer();
							t.schedule(new BattleTimer(), 100);
						}
					}
				}
			});
		}
	}

	public void escape() {
		if (nekutaru) {
			PLAYER.setdamage(PLAYER.getdamage() + 10);
			if ((PLAYER.getdamage() - PLAYER.getadd(0)) >= 100)
				PLAYER.setdamage(100 + PLAYER.getadd(0));
			if (PLAYER.getdamage() - PLAYER.getadd(0) > PLAYER.gethp())
				PLAYER.setdamage(PLAYER.gethp() + PLAYER.getadd(0));
		}
		random1 = (int) Math.floor(Math.random() * 10);
		if (random1 == 0 || random1 == 1 || random1 == 2 || random1 == 3 || random1 == 4) {
			bosstf = false;
			SOUND.escape();
			console.setText("逃げきれた！");
			exitevent();
		} else {// 逃げられない
			buttonoff();
			if (t == null) {
				rn = 1;
				t = new Timer();
				t.schedule(new BattleTimer(), 0);
			}
		}
	}

	public void enemyanime(int id) {
		if (id == 0) {
			playbg.setBackgroundResource(R.drawable.kuma);
		} else if (id == 1) {
			playbg.setBackgroundResource(R.drawable.heisi);
		} else if (id == 2) {
			playbg.setBackgroundResource(R.drawable.yopparai);
		} else if (id == 3) {
			playbg.setBackgroundResource(R.drawable.oihagi);
		} else if (id == 4) {
			playbg.setBackgroundResource(R.drawable.goburinn);
		} else if (id == 5) {
			playbg.setBackgroundResource(R.drawable.ooga);
		} else if (id == 6) {
			playbg.setBackgroundResource(R.drawable.naraboss);
		} else if (id == 7) {
			playbg.setBackgroundResource(R.drawable.vice);
		} else if (id == 8) {
			playbg.setBackgroundResource(R.drawable.kimaira);
		} else if (id == 9) {
			playbg.setBackgroundResource(R.drawable.minotaurosu);
		} else if (id == 10) {
			playbg.setBackgroundResource(R.drawable.saikuropusu);
		} else if (id == 11) {
			playbg.setBackgroundResource(R.drawable.kyuuketuki);
		} else if (id == 12) {
			playbg.setBackgroundResource(R.drawable.hyudora);
		} else if (id == 13) {
			playbg.setBackgroundResource(R.drawable.yorumunnganndo);
		} else if (id == 14) {
			playbg.setBackgroundResource(R.drawable.behimosu);
		} else if (id == 15) {
			playbg.setBackgroundResource(R.drawable.ribaiasann);
		} else if (id == 16) {
			playbg.setBackgroundResource(R.drawable.daiou);
		}
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
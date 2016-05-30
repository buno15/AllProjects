package jp.gr.java_conf.koni.warasibe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MoveEvent extends CORE {
	int			event, eventcp;
	static int	tf;
	Button		button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		savehp();
		savedamage();
		savepower();
		saveinsight();
		savemoney();
		saveitem();
		tf = 1;
		savetf();
		SOUND.evid = 0;
		SOUND.playBGM2();
		if (moneyclear) {
			playbg.setBackgroundResource(R.drawable.moneyclear);
			console.setText("あなたは大富豪だ");
			button1.setText("旅を続ける");
			button2.setText("隠居する");
		} else if (boss) {
			playbg.setBackgroundResource(R.drawable.bosstf);
			console.setText("恐怖の大王がやって来た");
			button1.setText("旅を続ける");
			button2.setText("討伐する");
		} else {
			random1 = (int) Math.floor(Math.random() * 6);
			if (random1 == 0) {
				playbg.setBackgroundResource(R.drawable.tiheisenn);
				console.setText("どこへ行こう？");
				button1.setText("地平線に向かって\n走り続ける");
				button2.setText("暗雲に向かって\n走り続ける");
			} else if (random1 == 1) {
				playbg.setBackgroundResource(R.drawable.matinaka);
				console.setText("町にやって来た");
				button1.setText("３歩進む");
				button2.setText("２歩戻る");
			} else if (random1 == 2) {
				playbg.setBackgroundResource(R.drawable.wakaremiti);
				console.setText("分かれ道だ");
				button1.setText("左");
				button2.setText("右");
			} else if (random1 == 3) {
				playbg.setBackgroundResource(R.drawable.sougenn);
				console.setText("どこまでも草原だ");
				button1.setText("駆け抜ける");
				button2.setText("ゆっくりする");
			} else if (random1 == 4) {
				playbg.setBackgroundResource(R.drawable.umi);
				if (PLAYER.getitem().equals("なし")) {
					console.setText("ネクタルという伝説の薬があるらしい");
					button1.setText("伝説さ");
					button2.setText("噂さ");
				} else {
					console.setText(PLAYER.getitem() + "は私の宝");
					button1.setText("いつまでも一緒");
					button2.setText("別のアイテムを探す");
				}
			} else if (random1 == 5) {
				playbg.setBackgroundResource(R.drawable.yama);
				console.setText("山が見える");
				button1.setText("山へ行く");
				button2.setText("引き返す");
			}
		}
		if (!(ITEM.getEndurance() == -1)) {
			ITEM.setEndurance(ITEM.getEndurance() - 1);
			if (PLAYER.getadd(0) != 0) {
				PLAYER.setadd(PLAYER.getadd(0) - 1, 0);
			} else if (PLAYER.getadd(1) != 0) {
				PLAYER.setadd(PLAYER.getadd(1) - 1, 1);
			} else if (PLAYER.getadd(2) != 0) {
				PLAYER.setadd(PLAYER.getadd(2) - 1, 2);
			}
			if (PLAYER.getdamage() <= 0) {

			} else if (ITEM.getEndurance() == 0) {
				console.setText(console.getText() + "\n" + PLAYER.getitem() + "がこわれた");
				ITEM.setnull();
			}
		}
		status();
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				event();
				startActivity(i);
				finish();
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (moneyclear) {
					i = new Intent(getApplicationContext(), GameClear.class);
					startActivity(i);
					finish();
				} else if (boss) {
					BattleEvent.bosstf = true;
					i = new Intent(getApplicationContext(), BattleEvent.class);
					startActivity(i);
					finish();
				} else {
					event();
					startActivity(i);
					finish();
				}
			}
		});
		if (moneyclear && boss) {
			playbg.setBackgroundResource(R.drawable.bosstf);
			console.setText("恐怖の大王がやって来た");
			button3 = new Button(getApplicationContext());
			button3.setText("討伐する");
			button3.setTextColor(Color.BLACK);
			button3.setTextSize(8 * setScaleSize(getApplicationContext()));
			LayoutParams lp = new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
			MarginLayoutParams mlp = (MarginLayoutParams) lp;
			mlp.setMargins(margin, margin, margin, margin);
			button3.setLayoutParams(mlp);
			button3.setBackgroundResource(R.drawable.button_custom);
			button3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					BattleEvent.bosstf = true;
					i = new Intent(getApplicationContext(), BattleEvent.class);
					startActivity(i);
					finish();
				}
			});
			buttonLayout.addView(button3);
		}
	}

	public void event() {
		event = rnd.nextInt(10) + 1;
		loadeventcount();
		while (eventcp == event) {
			event = rnd.nextInt(10) + 1;
		}
		eventcp = event;
		saveeventcount();
		if (event == 1) {
			i = new Intent(getApplicationContext(), BattleEvent.class);
		} else if (event == 2)
			i = new Intent(getApplicationContext(), JobEvent.class);
		else if (event == 3)
			i = new Intent(getApplicationContext(), TreasureEvent.class);
		else if (event == 4) {
			if (!PLAYER.getitem().equals("なし"))
				i = new Intent(getApplicationContext(), ExchangeEvent.class);
			else
				i = new Intent(getApplicationContext(), TreasureEvent.class);
		} else if (event == 5)
			i = new Intent(getApplicationContext(), ScamEvent.class);
		else if (event == 6) {
			if ((PLAYER.getdamage() - PLAYER.getadd(0)) <= PLAYER.gethp() / 2)
				i = new Intent(getApplicationContext(), RecoveryEvent.class);
			else
				i = new Intent(getApplicationContext(), BattleEvent.class);
		} else if (event == 7)
			i = new Intent(getApplicationContext(), BSEvent.class);
		else if (event == 8)
			i = new Intent(getApplicationContext(), JobEvent.class);
		else if (event == 9) {
			if ((PLAYER.getdamage() - PLAYER.getadd(0)) < PLAYER.gethp())
				i = new Intent(getApplicationContext(), HotelEvent.class);
			else
				i = new Intent(getApplicationContext(), BattleEvent.class);
		} else if (event == 10) {
			if (PLAYER.getitem().equals(ITEM.itemName1) || PLAYER.getitem().equals(ITEM.itemName2)
					|| PLAYER.getitem().equals(ITEM.itemName3)
					|| PLAYER.getitem().equals(ITEM.itemName4)
					|| PLAYER.getitem().equals(ITEM.itemName5)
					|| PLAYER.getitem().equals(ITEM.itemName6)
					|| PLAYER.getitem().equals(ITEM.itemName7)
					|| PLAYER.getitem().equals(ITEM.itemName11)
					|| PLAYER.getitem().equals(ITEM.itemName12)
					|| PLAYER.getitem().equals(ITEM.itemName13)
					|| PLAYER.getitem().equals(ITEM.itemName14)
					|| PLAYER.getitem().equals(ITEM.itemName15)
					|| PLAYER.getitem().equals(ITEM.itemName21)
					|| PLAYER.getitem().equals(ITEM.itemName22)
					|| PLAYER.getitem().equals(ITEM.itemName23)
					|| PLAYER.getitem().equals(ITEM.itemName24)
					|| PLAYER.getitem().equals(ITEM.itemName25)
					|| PLAYER.getitem().equals(ITEM.itemName31)
					|| PLAYER.getitem().equals(ITEM.itemName32)
					|| PLAYER.getitem().equals(ITEM.itemName33)) {
				i = new Intent(getApplicationContext(), UsefulEvent.class);
			} else
				i = new Intent(getApplicationContext(), TreasureEvent.class);
		}
	}

	public void loadeventcount() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		eventcp = sp.getInt("saveeventcount", eventcp);
	}

	public void loadhp() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		PLAYER.hp = sp.getInt("savehp", PLAYER.hp);
	}

	public void loaddamage() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		PLAYER.damage = sp.getInt("savedamage", PLAYER.damage);
	}

	public void loadpower() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		PLAYER.power = sp.getInt("savepower", PLAYER.power);
	}

	public void loadinsight() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		PLAYER.insight = sp.getInt("saveinsight", PLAYER.insight);
	}

	public void loadmoney() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		PLAYER.money = sp.getInt("savemoney", PLAYER.money);
	}

	public void loaditem() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		PLAYER.item = sp.getString("saveitem", PLAYER.item);
		if (PLAYER.item.equals("なし")) {
			ITEM.setnull();
		} else if (PLAYER.item.equals(ITEM.itemName0)) {
			ITEM.item(0);
		} else if (PLAYER.item.equals(ITEM.itemName1)) {
			ITEM.item(1);
		} else if (PLAYER.item.equals(ITEM.itemName2)) {
			ITEM.item(2);
		} else if (PLAYER.item.equals(ITEM.itemName3)) {
			ITEM.item(3);
		} else if (PLAYER.item.equals(ITEM.itemName4)) {
			ITEM.item(4);
		} else if (PLAYER.item.equals(ITEM.itemName5)) {
			ITEM.item(5);
		} else if (PLAYER.item.equals(ITEM.itemName6)) {
			ITEM.item(6);
		} else if (PLAYER.item.equals(ITEM.itemName7)) {
			ITEM.item(7);
		} else if (PLAYER.item.equals(ITEM.itemName8)) {
			ITEM.item(8);
		} else if (PLAYER.item.equals(ITEM.itemName9)) {
			ITEM.item(9);
		} else if (PLAYER.item.equals(ITEM.itemName10)) {
			ITEM.item(10);
		} else if (PLAYER.item.equals(ITEM.itemName11)) {
			ITEM.item(11);
		} else if (PLAYER.item.equals(ITEM.itemName12)) {
			ITEM.item(12);
		} else if (PLAYER.item.equals(ITEM.itemName13)) {
			ITEM.item(13);
		} else if (PLAYER.item.equals(ITEM.itemName14)) {
			ITEM.item(14);
		} else if (PLAYER.item.equals(ITEM.itemName15)) {
			ITEM.item(15);
		} else if (PLAYER.item.equals(ITEM.itemName16)) {
			ITEM.item(16);
		} else if (PLAYER.item.equals(ITEM.itemName17)) {
			ITEM.item(17);
		} else if (PLAYER.item.equals(ITEM.itemName18)) {
			ITEM.item(18);
		} else if (PLAYER.item.equals(ITEM.itemName19)) {
			ITEM.item(19);
		} else if (PLAYER.item.equals(ITEM.itemName20)) {
			ITEM.item(20);
		} else if (PLAYER.item.equals(ITEM.itemName21)) {
			ITEM.item(21);
		} else if (PLAYER.item.equals(ITEM.itemName22)) {
			ITEM.item(22);
		} else if (PLAYER.item.equals(ITEM.itemName23)) {
			ITEM.item(23);
		} else if (PLAYER.item.equals(ITEM.itemName24)) {
			ITEM.item(24);
		} else if (PLAYER.item.equals(ITEM.itemName25)) {
			ITEM.item(25);
		} else if (PLAYER.item.equals(ITEM.itemName26)) {
			ITEM.item(26);
		} else if (PLAYER.item.equals(ITEM.itemName27)) {
			ITEM.item(27);
		} else if (PLAYER.item.equals(ITEM.itemName28)) {
			ITEM.item(28);
		} else if (PLAYER.item.equals(ITEM.itemName29)) {
			ITEM.item(29);
		} else if (PLAYER.item.equals(ITEM.itemName30)) {
			ITEM.item(30);
		} else if (PLAYER.item.equals(ITEM.itemName31)) {
			ITEM.item(31);
		} else if (PLAYER.item.equals(ITEM.itemName32)) {
			ITEM.item(32);
		} else if (PLAYER.item.equals(ITEM.itemName33)) {
			ITEM.item(33);
		} else if (PLAYER.item.equals(ITEM.itemName34)) {
			ITEM.item(34);
		} else if (PLAYER.item.equals(ITEM.itemName35)) {
			ITEM.item(35);
		} else if (PLAYER.item.equals(ITEM.itemName36)) {
			ITEM.item(36);
		} else if (PLAYER.item.equals(ITEM.itemName37)) {
			ITEM.item(37);
		} else if (PLAYER.item.equals(ITEM.itemName38)) {
			ITEM.item(38);
		} else if (PLAYER.item.equals(ITEM.itemName39)) {
			ITEM.item(39);
		} else if (PLAYER.item.equals(ITEM.itemName40)) {
			ITEM.item(40);
		}
	}

	public void saveeventcount() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("saveeventcount", eventcp);
		editor.commit();
	}

	public void savehp() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("savehp", PLAYER.hp);
		editor.commit();
	}

	public void savedamage() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("savedamage", PLAYER.damage - PLAYER.getadd(0));
		editor.commit();
	}

	public void savepower() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("savepower", PLAYER.power - PLAYER.getadd(1));
		editor.commit();
	}

	public void saveinsight() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("saveinsight", PLAYER.insight - PLAYER.getadd(2));
		editor.commit();
	}

	public void savemoney() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("savemoney", PLAYER.money);
		editor.commit();
	}

	public void saveitem() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("saveitem", PLAYER.item);
		editor.commit();
	}

	public void savetf() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("savetf", tf);
		editor.commit();
	}

	@Override
	public void onResume() {
		super.onResume();
		SOUND.mediaPlayer2.start();
	}

	@Override
	public void onPause() {
		super.onPause();
		SOUND.mediaPlayer2.pause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		SOUND.stopBGM2();
	}
}
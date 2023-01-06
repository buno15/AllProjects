package jp.gr.java_conf.koni.warasibe;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GameStart extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.start);
		SOUND.Startinit(getApplicationContext());

		ITEM.item(0);
		Random rnd = new Random();
		int random = rnd.nextInt(11) + 5;
		PLAYER.setdamage(random);
		PLAYER.sethp(random);
		random = rnd.nextInt(11) + 5;
		PLAYER.setpower(random);
		random = rnd.nextInt(11) + 5;
		PLAYER.setinsight(random);
		PLAYER.setmoney(0);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setText("New");
		button1.setTextSize(14 * CORE.setScaleSize(getApplicationContext()));
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), MoveEvent.class);
				startActivity(i);
				finish();
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setText("Load");
		button2.setTextSize(14 * CORE.setScaleSize(getApplicationContext()));
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadhp();
				loaddamage();
				loadpower();
				loadinsight();
				loadmoney();
				loaditem();
				Intent i = new Intent(getApplicationContext(), MoveEvent.class);
				startActivity(i);
				finish();
			}
		});
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setText("Help");
		button3.setTextSize(14 * CORE.setScaleSize(getApplicationContext()));
		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), GameHelp.class);
				startActivity(i);
				finish();
			}
		});
		loadtf();
		if (MoveEvent.tf == 0)
			button2.setEnabled(false);
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

	public void loadtf() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		MoveEvent.tf = sp.getInt("savetf", MoveEvent.tf);
	}

	@Override
	public void onResume() {
		super.onResume();
		SOUND.soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if (0 == status) {
					SOUND.start();
				}
			}
		});
	}
}

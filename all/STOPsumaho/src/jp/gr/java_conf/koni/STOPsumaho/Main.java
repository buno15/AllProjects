package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	static int releasecount; // 強制解除タッチ回数
	static int passcount; // パスワード停止時に電源を切ったかカウント
	static boolean termstf; // 初回起動か調べる
	String currentpackagename;
	String mypackagename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.main);
		
		loadkiyakucount();
		if (termstf) {
			startActivity(new Intent(getApplicationContext(), TermsStart.class));
			finish();
		}
		loadpasucount();
		if (passcount == 1) {
			startActivity(new Intent(getApplicationContext(), Stop_Pass.class));
			finish();
		}
		if (Setting_Time.ZIKANZI.equals("") || Setting_Time.ZIKANZI == null) {
			Setting_Time.ZIKANZI = "分";
		}
		
		loadzikan();
		loadhunn();
		loadp();
		loadzikantime();
		loadzikanhyouzitime();
		loadzikanzi();
		loadkaizyocount();
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(12 * setScaleSize(getApplicationContext()));
		button1.setText("時間で停止");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kaisi(Select_Time.class);
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setTextSize(12 * setScaleSize(getApplicationContext()));
		button2.setText("時刻で停止");
		button2.setTextColor(Color.WHITE);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kaisi(Select_TimesOfDay.class);
			}
		});
		
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setTextSize(12 * setScaleSize(getApplicationContext()));
		button3.setText("パスワード\nで停止");
		button3.setTextColor(Color.WHITE);
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kaisi(Select_Pass.class);
			}
		});
		Button button4 = (Button) findViewById(R.id.button4);
		button4.setTextSize(12 * setScaleSize(getApplicationContext()));
		button4.setText("時間の設定");
		button4.setTextColor(Color.WHITE);
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Setting_Time.class));
				finish();
			}
		});
		Button button5 = (Button) findViewById(R.id.button5);
		button5.setTextSize(12 * setScaleSize(getApplicationContext()));
		button5.setText("時刻の設定");
		button5.setTextColor(Color.WHITE);
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Setting_TImesOfDay.class));
				finish();
			}
		});
		Button button6 = (Button) findViewById(R.id.button6);
		button6.setTextSize(12 * setScaleSize(getApplicationContext()));
		button6.setText("パスワード\nの設定");
		button6.setTextColor(Color.WHITE);
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Setting_Pass.pass == 0) {
					startActivity(new Intent(getApplicationContext(), Setting_Pass.class));
					finish();
				} else {
					startActivity(new Intent(getApplicationContext(), ConfirmationPass.class));
					finish();
				}
			}
			
		});
		Button button7 = (Button) findViewById(R.id.button7);
		button7.setTextSize(12 * setScaleSize(getApplicationContext()));
		button7.setText("強制解除\nの設定");
		button7.setTextColor(Color.WHITE);
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Setting.class));
				finish();
			}
		});
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(8 * setScaleSize(getApplicationContext()));
		textview1.setText(" ---開始-----------------------------------------------------------");
		textview1.setTextColor(Color.BLACK);
		
		TextView textview2 = (TextView) findViewById(R.id.textview2);
		textview2.setTextSize(7 * setScaleSize(getApplicationContext()));
		textview2.setText(" 設定時間:" + Setting_Time.ZIKANHYOUZITIME + Setting_Time.ZIKANZI + " \n 設定時刻:" + Setting_TImesOfDay.zikan + "時" + Setting_TImesOfDay.hunn + "分" + "\n BACKキー回数:"
				+ releasecount + "回");
		textview2.setTextColor(Color.BLACK);
		
		TextView textview3 = (TextView) findViewById(R.id.textview3);
		textview3.setTextSize(8 * setScaleSize(getApplicationContext()));
		textview3.setText(" ---設定-----------------------------------------------------------");
		textview3.setTextColor(Color.BLACK);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 0, Menu.NONE, "ヘルプ");
		menu.add(Menu.NONE, 1, Menu.NONE, "終了");
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case 0:
				startActivity(new Intent(getApplicationContext(), Help.class));
				finish();
				return true;
			case 1:
				getPackageManager().clearPackagePreferredActivities(getPackageName());
				finish();
		}
		return false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			getPackageManager().clearPackagePreferredActivities(getPackageName());
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void loadzikan() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Setting_TImesOfDay.zikan = sp.getInt("Savezikan", Setting_TImesOfDay.zikan);
	}
	
	public void loadhunn() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Setting_TImesOfDay.hunn = sp.getInt("Savehunn", Setting_TImesOfDay.hunn);
	}
	
	public void loadp() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Setting_Pass.pass = sp.getInt("Savep", Setting_Pass.pass);
	}
	
	public void loadzikantime() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Setting_Time.ZIKANTIME = sp.getLong("Savezikantime", Setting_Time.ZIKANTIME);
	}
	
	public void loadzikanhyouzitime() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Setting_Time.ZIKANHYOUZITIME = sp.getLong("Savezikanhyouzitime", Setting_Time.ZIKANHYOUZITIME);
	}
	
	public void loadzikanzi() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Setting_Time.ZIKANZI = sp.getString("Savezikanzi", Setting_Time.ZIKANZI);
	}
	
	public void loadkaizyocount() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		releasecount = sp.getInt("Savekaizyocount", releasecount);
	}
	
	public void loadkiyakucount() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		termstf = sp.getBoolean("Savekiyakucount", termstf);
	}
	
	public void loadpasucount() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		passcount = sp.getInt("Savepasucount", passcount);
	}
	
	public void savepasucount() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("Savepasucount", passcount);
		editor.commit();
	}
	
	public static float setScaleSize(Context context) {
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
		
		WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		
		Point size = new Point();
		disp.getSize(size);
		
		return (float) size.x / (float) bm.getWidth();
	}
	
	public void kaisi(Class<?> cls) {
		PackageManager pm = getPackageManager();
		Intent i = new Intent(Intent.ACTION_MAIN);
		i.addCategory(Intent.CATEGORY_HOME);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ResolveInfo resolveInfo = pm.resolveActivity(i, 0);
		ActivityInfo activityInfo = resolveInfo.activityInfo;
		Uri uri = Uri.fromParts("package", activityInfo.packageName, null);
		i.setData(uri);
		ComponentName cn = ComponentName.unflattenFromString("com.android.settings/.applications.InstalledAppDetails");
		i.setComponent(cn);
		currentpackagename = activityInfo.packageName;
		mypackagename = getPackageName();
		if (currentpackagename.equals("android")) {
			if (Build.VERSION.SDK_INT <= 16) {
				Toast.makeText(getApplicationContext(), "[常にこの操作で使用]にチェックを入れ[STOPスマホ]を選択してください。", Toast.LENGTH_LONG).show();
			} else if (Build.VERSION.SDK_INT >= 17) {
				Toast.makeText(getApplicationContext(), "[STOPスマホ]を[常時]で選択してください。", Toast.LENGTH_LONG).show();
			}
			i = new Intent(Intent.ACTION_MAIN);
			i.addCategory(Intent.CATEGORY_HOME);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			finish();
		} else if (!currentpackagename.equals(mypackagename)) {
			if (Build.VERSION.SDK_INT <= 15) {
				Toast.makeText(getApplicationContext(), "[初期状態で起動]→[初期設定に戻す]を選択してください。", Toast.LENGTH_LONG).show();
			} else if (Build.VERSION.SDK_INT >= 16) {
				Toast.makeText(getApplicationContext(), "[デフォルトでの起動]→[設定を消去]を選択してください。", Toast.LENGTH_LONG).show();
			}
			startActivity(i);
			finish();
		} else {
			startActivity(new Intent(getApplicationContext(), cls));
			finish();
		}
	}
}

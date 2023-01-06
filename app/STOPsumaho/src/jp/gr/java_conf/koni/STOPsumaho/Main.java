package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.main);

		IO.init(getApplicationContext());
		Values.init();

		if (Values.termstf) {
			startActivity(new Intent(getApplicationContext(), TermsStart.class));
			finish();
		}
		if (Values.passtf) {
			startActivity(new Intent(getApplicationContext(), Stop_Pass.class));
			finish();
		}
		if (Values.timeword == null || Values.timeword.equals("")) {
			Values.timeword = "分";
		}

		Button button[] = new Button[7];
		button[0] = (Button) findViewById(R.id.button1);
		button[1] = (Button) findViewById(R.id.button2);
		button[2] = (Button) findViewById(R.id.button3);
		button[3] = (Button) findViewById(R.id.button4);
		button[4] = (Button) findViewById(R.id.button5);
		button[5] = (Button) findViewById(R.id.button6);
		button[6] = (Button) findViewById(R.id.button7);
		for (int i = 0; i < button.length; i++) {
			button[i].setTextSize(12 * setScaleSize(getApplicationContext()));
			button[i].setTextColor(Color.WHITE);
		}
		button[0].setText("時間で停止");
		button[0].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kaisi(Select_Time.class);
			}
		});
		button[1].setText("時刻で停止");
		button[1].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kaisi(Select_TimesOfDay.class);
			}
		});
		button[2].setText("パスワード\nで停止");
		button[2].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kaisi(Select_Pass.class);
			}
		});
		button[3].setText("時間の設定");
		button[3].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Setting_Time.class));
				finish();
			}
		});
		button[4].setText("時刻の設定");
		button[4].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Setting_TImesOfDay.class));
				finish();
			}
		});
		button[5].setText("パスワード\nの設定");
		button[5].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Values.pass == 0) {
					startActivity(new Intent(getApplicationContext(), Setting_Pass.class));
					finish();
				} else {
					startActivity(new Intent(getApplicationContext(), ConfirmationPass.class));
					finish();
				}
			}

		});
		button[6].setText("強制解除\nの設定");
		button[6].setOnClickListener(new View.OnClickListener() {
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
		textview2.setText(" 設定時間：" + Values.displaytime + Values.timeword + " \n 設定時刻：" + Values.hour + "時" + Values.minute + "分" + "\n BACKキー回数：" + Values.releasecount + "回");
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
		String currentpackagename = activityInfo.packageName;
		String mypackagename = getPackageName();
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

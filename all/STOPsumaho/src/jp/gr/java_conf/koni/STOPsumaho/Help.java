package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.help);
		setTitle("ヘルプ");

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("ヘルプ");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Detail.class));
				finish();
			}
		});

		Button button2 = (Button) findViewById(R.id.button2);
		button2.setTextSize(10 * Main.setScaleSize(getApplicationContext()));
		button2.setText("現在のデフォルトホームアプリ");
		button2.setTextColor(Color.WHITE);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_MAIN);
				i.addCategory(Intent.CATEGORY_HOME);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				PackageManager pm = getPackageManager();
				ResolveInfo resolveInfo = pm.resolveActivity(i, 0);
				ActivityInfo activityInfo = resolveInfo.activityInfo;
				Uri uri = Uri.fromParts("package", activityInfo.packageName, null);
				i.setData(uri);
				ComponentName cn = ComponentName.unflattenFromString("com.android.settings/.applications.InstalledAppDetails");
				i.setComponent(cn);
				startActivity(i);
			}
		});

		Button button3 = (Button) findViewById(R.id.button3);
		button3.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button3.setText("利用規約");
		button3.setTextColor(Color.WHITE);
		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Terms.class));
				finish();
			}
		});

		Button button4 = (Button) findViewById(R.id.button4);
		button4.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button4.setText("戻る");
		button4.setTextColor(Color.WHITE);
		button4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Main.class));
				finish();
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(getApplicationContext(), Main.class));
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}

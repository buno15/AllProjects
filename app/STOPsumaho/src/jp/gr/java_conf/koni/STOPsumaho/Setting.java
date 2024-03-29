package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends Activity {
	EditText edittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.setting);
		setTitle("設定");

		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setText("BACKキー回数");
		textview1.setTextSize(8 * Main.setScaleSize(getApplicationContext()));
		textview1.setTextColor(Color.BLACK);

		TextView textview2 = (TextView) findViewById(R.id.textview2);
		textview2.setText("回");
		textview2.setTextSize(8 * Main.setScaleSize(getApplicationContext()));
		textview2.setTextColor(Color.BLACK);

		TextView textview3 = (TextView) findViewById(R.id.textview3);
		textview3.setText("※BACKキーを設定回数押すことで\n強制解除することができます。");
		textview3.setTextSize(8 * Main.setScaleSize(getApplicationContext()));
		textview3.setTextColor(Color.BLACK);

		edittext = (EditText) findViewById(R.id.edittext);
		edittext.setTextSize(16 * Main.setScaleSize(getApplicationContext()));

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("決定");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Values.releasecount = Integer.parseInt(edittext.getText().toString());
					IO.releasecount(true);
					Toast.makeText(getApplicationContext(), "BACKキー" + String.valueOf(Values.releasecount) + "回", Toast.LENGTH_LONG).show();
					startActivity(new Intent(getApplicationContext(), Main.class));
					finish();
				} catch (Exception e) {
					edittext.setText("");
					Toast.makeText(getApplicationContext(), "正しく入力してください", Toast.LENGTH_SHORT).show();
				}
			}
		});

		Button button2 = (Button) findViewById(R.id.button2);
		button2.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button2.setText("戻る");
		button2.setTextColor(Color.WHITE);
		button2.setOnClickListener(new View.OnClickListener() {
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

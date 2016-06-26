package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Select_Pass extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.select_pass);
		setTitle("パスワードで停止");

		TextView textview[] = new TextView[5];
		textview[0] = (TextView) findViewById(R.id.textview1);
		textview[1] = (TextView) findViewById(R.id.textview2);
		textview[2] = (TextView) findViewById(R.id.textview3);
		textview[3] = (TextView) findViewById(R.id.textview4);
		textview[4] = (TextView) findViewById(R.id.textview5);

		for (int i = 0; i < textview.length; i++) {
			textview[i].setTextSize(9 * Main.setScaleSize(getApplicationContext()));
			textview[i].setTextColor(Color.BLACK);
		}
		textview[0].setTextSize(14 * Main.setScaleSize(getApplicationContext()));
		textview[0].setText("※警告※");
		textview[1].setText("\nパスワードを入力するまで");
		textview[2].setText("スマートフォンの");
		textview[3].setText("機能を停止します");
		textview[4].setText("よろしいですか?\n");

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("はい");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Values.releasecount != 0) {
					if (Values.pass == 0) {
						Toast.makeText(getApplicationContext(), "パスワードを設定してください", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "ロックします", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(getApplicationContext(), Stop_Pass.class));
						finish();
					}
				} else {
					Toast.makeText(getApplicationContext(), "強制解除の設定を終了してください", Toast.LENGTH_SHORT).show();
				}
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button2.setText("いいえ");
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

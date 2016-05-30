package jp.gr.java_conf.koni.katori;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.help);

		TextView textview = (TextView) findViewById(R.id.textview1);
		textview.setText(
				"\n1　ゲーム概要\n\nこのゲームは、画面に出で来る蚊を退治するゲームです。\n蚊はタップすることで、退治できます。\n左に表示されている肌に蚊が止まると、血が吸われはじめます。\n上部のゲージがいっぱいになるとゲームオーバーです。\n各ステージごとに、退治する指定の数があり、退治することによりステージクリアとなります。\n\n\n"
						+ "2　ゲームクリア\n\nステージ８でボスを倒すとゲームクリアとなります。\n\n\n"
						+ "3　蚊の種類\n\n蚊の種類は全部で６種類あり、それぞれで強さが違います。\n\n　色：タップ回数\n\n　黒：１回\n\n　白：２回\n\n　赤：３回\n\n　紫：７回\n\n　青：５回\n\n　ボス：１００回\n\n\n"
						+ "4　アイテム\n\nアイテムを使用することで、蚊の退治をサポートします。\n\n アイテム名：効果\n\n 虫よけスプレー：煙に触れた蚊を倒す。\n\n 虫よけクリーム：一定時間蚊を寄せ付けない。\n\n 蚊取り線香：一定時間蚊の思考を止める。\n\n");
		textview.setTextSize(7 * Play.setScaleSize(this));

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Sound.hitSE();
				Intent i = new Intent(getApplicationContext(), Start.class);
				startActivity(i);
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent i = new Intent(getApplicationContext(), Start.class);
			startActivity(i);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}

package jp.gr.java_conf.koni.warasibe;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class GameHelp extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.help);

		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(8 * CORE.setScaleSize(getApplicationContext()));
		textview1.setText("このゲームは物々交換などをしながら\n勇者や富豪になることを目的とします。\n\n\n"
				+ "1　ゲームクリア\n\n力を100にすることで恐怖の大王が出現します。\n恐怖の大王を倒せばクリアできます。\nまた所持金を10000GSBためることでも\nクリアできます。\n\n\n"
				+ "2　ゲームオーバー\n\n体力が0になると死にます。\n死ぬとゲームオーバーになります。\n\n\n"
				+ "3　ステータス\n\n体力　　　敵の攻撃でダメージを受ける\n\n力　　　　自身の攻撃力\n\n洞察力　　嘘を見抜くことができる\n\n\n"
				+ "4　アイテム\n\n剣　槍　弓　　力に加算される\n\n鎧　　　体力（ダメージ）に加算される\n\nインサイト　　洞察力に加算される\n\n※体力がマイナス（例、-10+20/40）\nのときに鎧を捨てると死にます。\n\n\nそれ以外のアイテムは\n売るとお金になります。\n\n\n"
				+ "5　武具の耐久\n\n武器　鎧　インサイト　は付加される\n数値ぶんのイベントが経過すると\n壊れます。\n\n\n");

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextSize(14 * CORE.setScaleSize(getApplicationContext()));
		button1.setText("戻る");
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), GameStart.class);
				startActivity(i);
				finish();
			}
		});
	}
}

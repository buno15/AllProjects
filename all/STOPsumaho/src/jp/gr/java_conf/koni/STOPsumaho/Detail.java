package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.detail);
		setTitle("各説明");
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(8 * Main.setScaleSize(getApplicationContext()));
		textview1.setText("1.STOPスマホの利用シーンについて\n\n" + "STOPスマホは、子供さんのスマホ利用時間の制限、学生さんの勉強中のスマホ制限、食事時間中のスマホ制限、デートの時間のスマホ利用の制限など、幅広い用途に活用が可能です。\n"
				+ "「時間停止」「時刻停止」「パスワード停止」の３種類により、任意の利用制限が可能です。\n\n\n" + "2.利用開始前の設定\n\n" + "STOPスマホは、デフォルトのランチャー画面を、一時的に「STOPスマホ」に変更することで実現しています。利用終了後は、ご自身のランチャーに戻す必要があります。\n\n"
				+ "  (a)「開始」ボタンを押下し、アプリケーション情報にて、デフォルト画面の設定を行います。\n\n" + "  (b)「デフォルトの起動」もしくは「初期状態で起動」から、「設定を消去」もしくは「初期設定に戻す」を選択し、デフォルトの起動から、ご自身の通常のランチャーの取り消しを実施します。（終了時に戻します。）\n");
		
		TextView textview2 = (TextView) findViewById(R.id.textview2);
		textview2.setTextSize(8 * Main.setScaleSize(getApplicationContext()));
		textview2.setText("\n  (c)STOPスマホのアイコンに戻り、「開始」ボタンを押下します。\n\n" + "  (d)ホームアプリケーションの選択画面で、「STOPスマホ」で「常時」を設定します。\n");
		
		TextView textview3 = (TextView) findViewById(R.id.textview3);
		textview3.setTextSize(8 * Main.setScaleSize(getApplicationContext()));
		textview3.setText("5.スマホ停止の緊急解除設定\n\n" + "あらかじめ、BACKキー入力回数を設定しておくことで、強制解除を設定することが可能です。緊急の場合、BACKキーにより、スマホ制限を解除できます。\n\n\n" + "6.終了の手順\n\n"
				+ "「終了」ボタンを押下し、ホームアプリケーションの選択で、通常利用するホーム画      面を選択し、「毎回」を押下し、ホーム画面のご自身の元の設定をもとに戻してください。\n\n");
		Button button = (Button) findViewById(R.id.button);
		button.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button.setText("戻る");
		button.setTextColor(Color.WHITE);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Help.class));
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

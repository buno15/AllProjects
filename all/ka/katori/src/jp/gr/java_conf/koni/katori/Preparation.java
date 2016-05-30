package jp.gr.java_conf.koni.katori;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Preparation extends Activity {
	static int		nakaCount;
	LinearLayout	preparationback;
	ImageView		stage;
	Intent			i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.preparation);

		preparationback = (LinearLayout) findViewById(R.id.preparationback);
		stage = (ImageView) findViewById(R.id.stage);
		switch (nakaCount) {
		case 0:
			Play.stage = 0;
			preparationback.setBackgroundResource(R.drawable.play1);
			stage.setBackgroundResource(R.drawable.stage1);
			break;
		case 1:
			Play.stage = 1;
			preparationback.setBackgroundResource(R.drawable.play2);
			stage.setBackgroundResource(R.drawable.stage2);
			break;
		case 2:
			Play.stage = 2;
			preparationback.setBackgroundResource(R.drawable.play3);
			stage.setBackgroundResource(R.drawable.stage3);
			break;
		case 3:
			Play.stage = 3;
			preparationback.setBackgroundResource(R.drawable.play4);
			stage.setBackgroundResource(R.drawable.stage4);
			break;
		case 4:
			Play.stage = 4;
			preparationback.setBackgroundResource(R.drawable.play5);
			stage.setBackgroundResource(R.drawable.stage5);
			break;
		case 5:
			Play.stage = 5;
			preparationback.setBackgroundResource(R.drawable.play6);
			stage.setBackgroundResource(R.drawable.stage6);
			break;
		case 6:
			Play.stage = 6;
			preparationback.setBackgroundResource(R.drawable.play7);
			stage.setBackgroundResource(R.drawable.stage7);
			break;
		case 7:
			Play.stage = 7;
			preparationback.setBackgroundResource(R.drawable.play8);
			stage.setBackgroundResource(R.drawable.stage8);
			break;
		}
		i = new Intent(getApplicationContext(), Play.class);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTextColor(Color.RED);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Sound.hitSE();
				startActivity(i);
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

package jp.gr.java_conf.bunooboi.bunooboi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
	EditText edittext1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		edittext1 = (EditText) findViewById(R.id.edittext1);
		edittext1.setTextSize(14 * setScaleSize(getApplicationContext()));

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setText("送信");
		button1.setTextSize(14 * setScaleSize(getApplicationContext()));
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	public static float setScaleSize(Context context) {
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
		float width = 0;
		float scale = 0;

		WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();

		Point size = new Point();
		disp.getSize(size);
		width = size.x;

		scale = (float) width / (float) bm.getWidth();

		return scale;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

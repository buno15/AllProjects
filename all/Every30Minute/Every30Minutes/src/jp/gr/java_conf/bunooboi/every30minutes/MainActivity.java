package jp.gr.java_conf.bunooboi.every30minutes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button	startbutton;
	Button	stopbutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		startbutton = (Button) findViewById(R.id.button1);
		stopbutton = (Button) findViewById(R.id.button2);

		startbutton.setText("Start");
		stopbutton.setText("Stop");

		startbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(getApplicationContext(), MainService.class));
			}
		});
		stopbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(getApplicationContext(), MainService.class));
			}
		});
	}
}

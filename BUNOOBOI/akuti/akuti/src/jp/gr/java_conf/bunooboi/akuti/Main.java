package jp.gr.java_conf.bunooboi.akuti;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
	MediaRecorder	recorder;

	Button			button1, button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);

		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				start();
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stop();
			}
		});
	}

	public void start() {

		// 録音準備＆録音開始
		if (recorder != null) {
			recorder.release();
			recorder = null;
		}
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		recorder.setOutputFile(getExternalFilesDir(null) + "/aaa.3gpp");
		try {
			recorder.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		recorder.start();
		Toast.makeText(this, "録音開始", Toast.LENGTH_SHORT).show();
	}

	public void stop() {
		try {
			recorder.stop();
			recorder.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		recorder.release();
		recorder = null;
		Toast.makeText(this, "録音終了", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

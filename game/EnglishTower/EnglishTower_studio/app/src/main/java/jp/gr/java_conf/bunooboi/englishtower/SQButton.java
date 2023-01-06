package jp.gr.java_conf.bunooboi.englishtower;

import android.content.Context;
import android.widget.Button;

public class SQButton extends Button {
	private String WORD;

	public SQButton(Context context) {
		super(context);
	}

	public void setWord(String word) {
		WORD = word;
	}

	public String getWord() {
		return WORD;
	}
}

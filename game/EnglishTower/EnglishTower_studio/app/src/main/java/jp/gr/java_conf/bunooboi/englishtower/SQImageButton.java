package jp.gr.java_conf.bunooboi.englishtower;

import android.content.Context;
import android.widget.ImageButton;

public class SQImageButton extends ImageButton {
	private String WORD;

	public SQImageButton(Context context) {
		super(context);
	}

	public void setWord(String word) {
		WORD = word;
	}

	public String getWord() {
		return WORD;
	}
}

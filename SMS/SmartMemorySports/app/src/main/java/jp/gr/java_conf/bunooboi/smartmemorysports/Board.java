package jp.gr.java_conf.bunooboi.smartmemorysports;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Board extends LinearLayout {
    TextView textView;
    ImageButton imageButton;

    public Board(Context context) {
        this(context, null);
    }

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.board, this);
        textView = findViewById(R.id.boardText);
        imageButton = findViewById(R.id.boardImage);
    }
}

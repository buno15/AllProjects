package com.example.minutes15;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class List extends LinearLayout {
    TextView textView;
    Switch aSwitch;

    public List(Context context) {
        this(context, null);
    }

    public List(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_item, this);
        textView = findViewById(R.id.text);
        aSwitch = findViewById(R.id.aswitch);
    }
}

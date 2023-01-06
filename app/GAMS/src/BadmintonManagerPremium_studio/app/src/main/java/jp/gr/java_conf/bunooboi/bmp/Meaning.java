package jp.gr.java_conf.bunooboi.bmp;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import jp.gr.java_conf.bunooboi.gams.Manager;

public class Meaning {
    LinearLayout MeaningLayout;
    TextView textview[];
    ImageView imageview[];

    public Meaning(Context context) {
        textview = new TextView[6];
        imageview = new ImageView[6];
        MeaningLayout = new LinearLayout(context);
        MeaningLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
        MeaningLayout.setOrientation(LinearLayout.HORIZONTAL);

        View view1 = new View(context);// 余白View
        view1.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
        MeaningLayout.addView(view1);

        for (int i = 0; i < 6; i++) {
            imageview[i] = new ImageView(context);
            imageview[i].setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            textview[i] = new TextView(context);
            textview[i].setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 3));
            textview[i].setGravity(Gravity.CENTER);
            textview[i].setTextSize(2 * Manager.getScaleSize(context));
            switch (i) {
                case 0:
                    textview[i].setText(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getHullName(jp.gr.java_conf.bunooboi.bmp.AceMissObject.ACE_GOOD));
                    imageview[i].setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.ACE_GOOD));
                    break;
                case 1:
                    textview[i].setText(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getHullName(jp.gr.java_conf.bunooboi.bmp.AceMissObject.ACE_NORMAL));
                    imageview[i].setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.ACE_NORMAL));
                    break;
                case 2:
                    textview[i].setText(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getHullName(jp.gr.java_conf.bunooboi.bmp.AceMissObject.ACE_LUCKY));
                    imageview[i].setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.ACE_LUCKY));
                    break;
                case 3:
                    textview[i].setText(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getHullName(jp.gr.java_conf.bunooboi.bmp.AceMissObject.MISS_BAD));
                    imageview[i].setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.MISS_BAD));
                    break;
                case 4:
                    textview[i].setText(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getHullName(jp.gr.java_conf.bunooboi.bmp.AceMissObject.MISS_NORMAL));
                    imageview[i].setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.MISS_NORMAL));
                    break;
                case 5:
                    textview[i].setText(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getHullName(jp.gr.java_conf.bunooboi.bmp.AceMissObject.MISS_UNLUCKY));
                    textview[i].setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 4));
                    imageview[i].setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmp.AceMissObject.MISS_UNLUCKY));
                    break;
            }
            MeaningLayout.addView(imageview[i]);
            MeaningLayout.addView(textview[i]);
            View viewlast = new View(context);// 余白View
            viewlast.setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.5f));
            MeaningLayout.addView(viewlast);
        }
    }

    public void addView(ViewGroup view) {
        view.addView(MeaningLayout);
    }
}

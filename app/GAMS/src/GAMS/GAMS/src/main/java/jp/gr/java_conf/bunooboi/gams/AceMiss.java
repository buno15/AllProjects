package jp.gr.java_conf.bunooboi.gams;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by hiro on 2016/07/10.
 */
public class AceMiss extends Manager {
    private ImageView WearImage;            // ウェアイメージ
    private FitTextView PlayerText;            // 選手名

    private Spinner AceSpinner;            // エースSpinner
    private Spinner MissSpinner;        // ミスSpinner

    private TextView AceText;            // エーステキスト
    private TextView MissText;            // ミステキスト

    private Context context;

    public AceMiss(Context context) {
        this.context = context;
        AceText = new TextView(context);
        MissText = new TextView(context);
        createWearImage();
        createPlayerText();
        createAceSpinner();
        createMissSpinner();
        createAceMissText(AceText);
        createAceMissText(MissText);
    }

    void createWearImage() {
        WearImage = new ImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 2);
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
        mlp.setMargins(getMargin(context) + 1, getMargin(context) + 1, getMargin(context) + 1,
                getMargin(context));
        WearImage.setLayoutParams(mlp);
    }

    void createPlayerText() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
        mlp.setMargins(getMargin(context) + 1, 0, getMargin(context) + 1, getMargin(context));
        PlayerText = new FitTextView(context);
        PlayerText.setLayoutParams(mlp);
        PlayerText.setTextSize(3 * getScaleSize(context));
        PlayerText.setTextColor(Color.BLACK);
        PlayerText.setGravity(Gravity.CENTER);
    }


    void createAceSpinner() {
        AceSpinner = new Spinner(context);
        AceSpinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
    }

    void createMissSpinner() {
        MissSpinner = new Spinner(context);
        MissSpinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .MATCH_PARENT, 0, 1));
    }


    void createAceMissText(TextView textview) {
        textview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        textview.setTextSize(6 * getScaleSize(context));
        textview.setGravity(Gravity.CENTER);
        if (textview.equals(AceText)) {
            textview.setTextColor(Color.BLUE);
        } else if (textview.equals(MissText)) {
            textview.setTextColor(Color.RED);
        }
    }

    public void setName(String name) {// 選手名セット
        PlayerText.setText(name);
    }

    public void setWearImage(int wear) {
        WearImage.setBackgroundResource(wear);
    }

    public void setAce(int value) {// エースミス、テキストセット
        AceText.setText(String.valueOf(value));
    }

    public void setMiss(int value) {
        MissText.setText(String.valueOf(value));
    }

    public void setEnabled(boolean b) {
        AceSpinner.setEnabled(b);
        MissSpinner.setEnabled(b);
    }

    public ImageView getWearImage() {
        return WearImage;
    }

    public FitTextView getPlayerText() {
        return PlayerText;
    }

    public TextView getAceText() {
        return AceText;
    }

    public TextView getMissText() {
        return MissText;
    }

    public Spinner getAceSpinner() {
        return AceSpinner;
    }

    public Spinner getMissSpinner() {
        return MissSpinner;
    }
}

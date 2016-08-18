package jp.gr.java_conf.bunooboi.mydic;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by hiro on 2016/07/21.
 */
public class MyDialog extends DialogFragment {
    FitTextView textview;
    Button button1;
    Button button2;
    static String text;

    public MyDialog() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.mydialog, null, false);
        textview = (FitTextView) view.findViewById(R.id.text);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        textview.setText(text);
        textview.setTextSize(16 * Main.getScaleSize(getActivity().getApplicationContext()));
        textview.setPadding(Main.getPctX(getActivity().getApplicationContext(), 12), Main.getPctY(getActivity()
                .getApplicationContext(), 10), Main.getPctX(getActivity().getApplicationContext(), 12), Main.getPctY
                (getActivity().getApplicationContext(), 10));
        button1.setText("いいえ");
        button2.setText("はい");
        button1.setTextSize(18 * Main.getScaleSize(getActivity().getApplicationContext()));
        button2.setTextSize(18 * Main.getScaleSize(getActivity().getApplicationContext()));
        button1.setPadding(Main.getPctX(getActivity().getApplicationContext(), 5), Main.getPctY(getActivity()
                .getApplicationContext(), 2), Main.getPctX(getActivity().getApplicationContext(), 5), Main.getPctY
                (getActivity().getApplicationContext(), 2));
        button2.setPadding(Main.getPctX(getActivity().getApplicationContext(), 5), Main.getPctY(getActivity()
                .getApplicationContext(), 2), Main.getPctX(getActivity().getApplicationContext(), 5), Main.getPctY
                (getActivity().getApplicationContext(), 2));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), GameStart.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                getActivity().finish();
                getActivity().overridePendingTransition(0, 0);
            }
        });

        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        // 背景を透明にする dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(view);
        return dialog;
    }
}

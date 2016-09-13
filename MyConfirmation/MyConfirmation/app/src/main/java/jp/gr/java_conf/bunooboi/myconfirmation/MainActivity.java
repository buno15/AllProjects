package jp.gr.java_conf.bunooboi.myconfirmation;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout root;
    AlertDialog.Builder alertDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        root = (LinearLayout) findViewById(R.id.root);
        alertDlg = new AlertDialog.Builder(MainActivity.this);
        Check check[] = new Check[3];
        check[0] = new Check("英語学習", "english");
        check[1] = new Check("数学学習", "math");
        check[2] = new Check("AIZU", "aizu");
        for (int i = 0; i < check.length; i++) {
            if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(check[i].key, false)) {
                check[i].button.setEnabled(false);
            }
        }
    }

    public static float getScaleSize(Context context) {// 文字サイズ調整
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        float width = 0;
        float scale = 0;
        width = size.x;
        scale = width / (float) bm.getWidth();
        return scale;
    }

    class Check {
        Button button;
        String text;
        String key;

        public Check(String text, String key) {
            this.text = text;
            this.key = key;
            createButton();
        }

        void createButton() {
            button = new Button(MainActivity.this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(text);
            button.setTextSize(20 * getScaleSize(getApplicationContext()));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDlg.setTitle("確認");
                    alertDlg.setMessage("本当にやりましたか？");
                    alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            button.setEnabled(false);
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean(key, true).commit();
                        }
                    });
                    alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDlg.create().show();
                }
            });
            root.addView(button);
        }
    }
}

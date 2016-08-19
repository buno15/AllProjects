package jp.gr.java_conf.bunooboi.mydic;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.FrameLayout;

import java.util.ArrayList;

/**
 * Created by hiro on 2016/08/18.
 */
public class GameMain extends Activity {
    static ArrayList<Integer> participationConfig = new ArrayList<>();//出題する辞書番号
    static int disp_w;// 画面幅
    static int disp_h;// 画面高さ
    static String thisNumber;//ランダム番号
    static FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            View_MyDialog.text = "メインに戻りますか？";
            View_MyDialog dialog = new View_MyDialog();
            dialog.show(getFragmentManager(), "test");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

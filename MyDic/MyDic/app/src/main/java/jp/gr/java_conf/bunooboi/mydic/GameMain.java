package jp.gr.java_conf.bunooboi.mydic;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

/**
 * Created by hiro on 2016/08/18.
 */
public class GameMain extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyDialog.text = "メインに戻りますか？";
            MyDialog dialog = new MyDialog();
            dialog.show(getFragmentManager(), "test");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

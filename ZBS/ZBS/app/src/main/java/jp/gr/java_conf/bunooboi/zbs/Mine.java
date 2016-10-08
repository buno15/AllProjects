package jp.gr.java_conf.bunooboi.zbs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by bunooboi on 16/10/08.
 */
public class Mine {
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

    public static int getPctX(Context context, int value) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        return size.x / 100 * value;
    }

    public static int getPctY(Context context, int value) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        return size.y / 100 * value;
    }
}

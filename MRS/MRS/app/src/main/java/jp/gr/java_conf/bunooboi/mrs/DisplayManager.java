package jp.gr.java_conf.bunooboi.mrs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by hiro on 2016/09/16.
 */
public class DisplayManager {
    public static float getScaleSize(Context context) {// 文字サイズ調整
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.saizu);
        float width = 0;
        float scale = 0;
        width = getPoint(context).x;
        scale = width / (float) bm.getWidth();
        return scale;
    }

    public static int getPctX(Context context, int value) {
        return getPoint(context).x / 100 * value;
    }

    public static int getPctY(Context context, int value) {
        return getPoint(context).y / 100 * value;
    }

    public static Point getPoint(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = wm.getDefaultDisplay();
        Point size = new Point();
        d.getSize(size);
        return size;
    }
}

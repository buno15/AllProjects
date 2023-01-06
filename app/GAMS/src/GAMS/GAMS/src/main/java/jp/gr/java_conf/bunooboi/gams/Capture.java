package jp.gr.java_conf.bunooboi.gams;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Capture {

    public Bitmap getViewCapture(View view) {
        view.setDrawingCacheEnabled(true);

        Bitmap cache = view.getDrawingCache();
        Bitmap screenShot = Bitmap.createBitmap(cache);
        view.setDrawingCacheEnabled(false);
        return screenShot;
    }

    public void saveCapture(View view, Context context, String fileName) {
        File file = new File(Manager.FilePath + fileName + ":" + PressTime
                .getPressTimeString() + ".jpeg");
        Bitmap capture = getViewCapture(view);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, false);
            capture.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    Toast.makeText(context, "画面を保存しました", Toast.LENGTH_SHORT).show();
                } catch (IOException ie) {
                    fos = null;
                }
            }
        }
    }
}

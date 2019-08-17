package jp.gr.java_conf.bunooboi.rema;

import android.app.Application;
import android.os.Environment;

import java.util.ArrayList;

/**
 * Created by hiro on 2016/09/02.
 */
public class App extends Application {
    private static App instance = null;

    public static ArrayList<String> titles = new ArrayList<>();
    public static ArrayList<Integer> indexes = new ArrayList<>();

    public static final String DataPath = Environment.getExternalStorageDirectory() + "/Rema";

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static void init() {
        titles = Input.getInstance().read();
    }

    public static synchronized App getInstance() {
        return instance;
    }
}

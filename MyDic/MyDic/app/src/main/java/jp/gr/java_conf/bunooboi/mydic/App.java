package jp.gr.java_conf.bunooboi.mydic;

import android.app.Application;

/**
 * Created by hiro on 2016/09/02.
 */
public class App extends Application {
    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static synchronized App getInstance() {
        return instance;
    }
}

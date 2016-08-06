package jp.gr.java_conf.bunooboi.mydic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

/**
 * Created by hiro on 2016/08/04.
 */
public class Values {
    static float pitch;//音声読み上げ高さ
    static float speechrate;//音声読み上げ速さ
    static int time;//音声検索長さ
    static boolean display;//html表示形式
    static boolean recognition;//音声認識オフラインか
    static boolean notification;//通知へのアクセス
    static Context context;
    static final String RootPath = Environment.getExternalStorageDirectory() + "/MyDic";
    static final String ConfigPath = RootPath + "/config";

    public static void init(Context context) {
        Values.context = context;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        pitch = sp.getFloat("pitch", pitch);
        speechrate = sp.getFloat("speechrate", speechrate);
        time = sp.getInt("time", time);
        display = sp.getBoolean("display", display);
        recognition = sp.getBoolean("recognition", recognition);
        notification = sp.getBoolean("notification", notification);
    }

    public static void setPitch(float pitch) {
        Values.pitch = pitch;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putFloat("pitch", pitch).commit();
    }

    public static void setSpeechrate(float speechrate) {
        Values.speechrate = speechrate;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putFloat("speechrate", speechrate).commit();
    }

    public static void setTime(int time) {
        Values.time = time;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt("time", time).commit();
    }

    public static void setDisplay(boolean display) {
        Values.display = display;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("display", display).commit();
    }

    public static void setRecognition(boolean recognition) {
        Values.recognition = recognition;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("recognition", recognition).commit();
    }

    public static void setNotification(boolean notification) {
        Values.notification = notification;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("notification", notification).commit();
    }
}

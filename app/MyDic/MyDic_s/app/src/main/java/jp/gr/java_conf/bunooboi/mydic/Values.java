package jp.gr.java_conf.bunooboi.mydic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

/**
 * Created by hiro on 2016/08/04.
 */
public class Values {
    public static float pitch;//音声読み上げ高さ
    public static float speechrate;//音声読み上げ速さ
    public static int time;//音声検索長さ
    public static float volume;//音量
    public static boolean display;//html表示形式
    public static boolean recognition;//音声認識オフラインか
    public static boolean notification;//通知へのアクセス
    public static boolean startservice;//端末起動時にサービス起動か
    public static boolean readtext;//定期読み上げ本文かどうか

    public static int indexCount;//リピート機能インデックス

    public static Context context;
    public static final String RootPath = Environment.getExternalStorageDirectory() + "/MyDic";
    public static final String DataPath = RootPath + "/data";
    public static final String ConfigPath = RootPath + "/config";

    public static void init(Context context) {
        Values.context = context;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        pitch = sp.getFloat("pitch", pitch);
        speechrate = sp.getFloat("speechrate", speechrate);
        time = sp.getInt("time", time);
        volume = sp.getFloat("volume", volume);
        display = sp.getBoolean("display", display);
        recognition = sp.getBoolean("recognition", recognition);
        notification = sp.getBoolean("notification", notification);
        startservice = sp.getBoolean("startservice", startservice);
        readtext = sp.getBoolean("readtext", readtext);
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

    public static void setVolume(float volume) {
        Values.volume = volume;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putFloat("volume", volume).commit();
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

    public static void setStartservice(boolean startservice) {
        Values.startservice = startservice;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("startservice", startservice).commit();
    }

    public static void setReadText(boolean readtext) {
        Values.readtext = readtext;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("readtext", readtext).commit();
    }

    public static int getIndexCount() {
        return (Values.indexCount = PreferenceManager.getDefaultSharedPreferences(context).getInt("indexcount", indexCount));
    }

    public static void setIndexCount(int indexCount) {
        Values.indexCount = indexCount;
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("indexcount", indexCount).commit();
    }
}

package jp.gr.java_conf.bunooboi.englishtower;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Values {
    static int level = 0;                // レベル
    static int floor = 1;                // 階層
    static int levelfloor = 1;                // レベルでの階層
    static int record;

    static boolean life[] = new boolean[3];    // 残りライフ
    // falseあり
    // trueなし

    static float ttsPitch;//テキスト読み上げ音の高さ
    static float ttsSpeechRate;//テクスト読み上げスピード

    public static void save(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt("floor", floor).commit();
        sp.edit().putInt("level", level).commit();
        sp.edit().putInt("levelfloor", levelfloor).commit();
        sp.edit().putInt("record", record).commit();
        sp.edit().putBoolean("life1", life[0]).commit();
        sp.edit().putBoolean("life2", life[1]).commit();
        sp.edit().putBoolean("life3", life[2]).commit();
    }

    public static void soundSave(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putFloat("ttsPitch", ttsPitch).commit();
        sp.edit().putFloat("ttsSpeechRate", ttsSpeechRate).commit();
    }

    public static void floor(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt("floor", floor).commit();
    }

    public static void level(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt("level", level);
    }

    public static void levelfloor(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt("levelfloor", levelfloor).commit();
    }

    public static void life1(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("life1", life[0]).commit();
    }

    public static void life2(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("life2", life[1]).commit();
    }

    public static void life3(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("life3", life[2]).commit();
    }

    public static void load(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        floor = sp.getInt("floor", floor);
        levelfloor = sp.getInt("levelfloor", levelfloor);
        level = sp.getInt("level", level);
        record = sp.getInt("record", record);
        life[0] = sp.getBoolean("life1", life[0]);
        life[1] = sp.getBoolean("life2", life[1]);
        life[2] = sp.getBoolean("life3", life[2]);
    }

    public static void soundLoad(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        ttsPitch = sp.getFloat("ttsPitch", ttsPitch);
        ttsSpeechRate = sp.getFloat("ttsSpeechRate", ttsSpeechRate);
    }

    public static boolean Midstream() {
        if (floor != 0 && floor != 1) {
            return true;
        } else {
            return false;
        }
    }
}

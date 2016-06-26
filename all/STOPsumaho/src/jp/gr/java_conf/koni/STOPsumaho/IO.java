package jp.gr.java_conf.koni.STOPsumaho;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class IO {
	static Context context;

	public static void init(Context context) {
		IO.context = context;
	}

	public void loadzikan() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Setting_TImesOfDay.zikan = sp.getInt("Savezikan", Setting_TImesOfDay.zikan);
	}

	public void loadhunn() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Setting_TImesOfDay.hunn = sp.getInt("Savehunn", Setting_TImesOfDay.hunn);
	}

	public void loadp() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Setting_Pass.pass = sp.getInt("Savep", Setting_Pass.pass);
	}

	public static void sstime(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putLong("Savezikantime", Values.sstime).commit();
		else
			Values.sstime = sp.getLong("Savezikantime", Values.sstime);
	}

	public static void displaytime(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putLong("Savezikanhyouzitime", Values.displaytime).commit();
		else
			Values.displaytime = sp.getLong("Savezikanhyouzitime", Values.displaytime);
	}

	public static void timeword(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putString("Savezikanzi", Values.timeword).commit();
		else
			Values.timeword = sp.getString("Savezikanzi", Values.timeword);
	}

	public void loadkaizyocount() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Values.releasecount = sp.getInt("Savekaizyocount", Values.releasecount);
	}

	public static void termstf() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Values.termstf = sp.getBoolean("Savekiyakucount", Values.termstf);
	}

	public static void passcount(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putBoolean("Savepasucount", Values.passcount).commit();
		else
			Values.passcount = sp.getBoolean("Savepasucount", Values.passcount);
	}
}

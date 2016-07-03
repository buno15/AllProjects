package jp.gr.java_conf.koni.STOPsumaho;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class IO {
	static Context context;

	public static void init(Context context) {
		IO.context = context;
	}

	public static void termstf(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putBoolean("termstf", Values.termstf).commit();
		else
			Values.termstf = sp.getBoolean("termstf", Values.termstf);
	}

	public static void passtf(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putBoolean("passtf", Values.passtf).commit();
		else
			Values.passtf = sp.getBoolean("passtf", Values.passtf);
	}

	public static void sstime(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putLong("sstime", Values.sstime).commit();
		else
			Values.sstime = sp.getLong("sstime", Values.sstime);
	}

	public static void displaytime(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putLong("displaytime", Values.displaytime).commit();
		else
			Values.displaytime = sp.getLong("displaytime", Values.displaytime);
	}

	public static void timeword(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putString("timeword", Values.timeword).commit();
		else
			Values.timeword = sp.getString("timeword", Values.timeword);
	}

	public static void hour(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putInt("hour", Values.hour).commit();
		else
			Values.hour = sp.getInt("hour", Values.hour);
	}

	public static void minute(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putInt("minute", Values.minute).commit();
		else
			Values.minute = sp.getInt("minute", Values.minute);
	}

	public static void pass(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putInt("pass", Values.pass).commit();
		else
			Values.pass = sp.getInt("pass", Values.pass);
	}

	public static void releasecount(boolean save) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		if (save)
			sp.edit().putInt("releasecount", Values.releasecount).commit();
		else
			Values.releasecount = sp.getInt("releasecount", Values.releasecount);
	}
}

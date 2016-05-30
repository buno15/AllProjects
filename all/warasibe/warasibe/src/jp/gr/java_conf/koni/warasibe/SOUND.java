package jp.gr.java_conf.koni.warasibe;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SOUND {
	public static Context		context;
	public static MediaPlayer	mediaPlayer1, mediaPlayer2;
	public static SoundPool		soundPool;
	public static int			start, death, getmoney, powerup, itemup, itemget, no, attack,
			damage, knock_down, revive, escape, scam, kill, recovery;
	public static int			evid;

	@SuppressWarnings("deprecation")
	public static void Startinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		start = soundPool.load(context, R.raw.start, 1);
	}

	@SuppressWarnings("deprecation")
	public static void Clearinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		recovery = soundPool.load(context, R.raw.recovery, 1);
	}

	@SuppressWarnings("deprecation")
	public static void Battleinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		death = soundPool.load(context, R.raw.death, 1);
		getmoney = soundPool.load(context, R.raw.getmoney, 1);
		itemup = soundPool.load(context, R.raw.itemup, 1);
		itemget = soundPool.load(context, R.raw.itemget, 1);
		no = soundPool.load(context, R.raw.no, 1);
		attack = soundPool.load(context, R.raw.attack, 1);
		damage = soundPool.load(context, R.raw.damage, 1);
		knock_down = soundPool.load(context, R.raw.knock_down, 1);
		revive = soundPool.load(context, R.raw.revive, 1);
		escape = soundPool.load(context, R.raw.escape, 1);
		scam = soundPool.load(context, R.raw.scam, 1);
		if (BattleEvent.bosstf) {
			evid = 2;
			SOUND.playBGM1();
		} else {
			evid = 1;
			SOUND.playBGM1();
		}
	}

	@SuppressWarnings("deprecation")
	public static void BSinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		getmoney = soundPool.load(context, R.raw.getmoney, 1);
		itemget = soundPool.load(context, R.raw.itemget, 1);
		no = soundPool.load(context, R.raw.no, 1);
		evid = 0;
		SOUND.playBGM1();
	}

	@SuppressWarnings("deprecation")
	public static void Exchangeinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		itemget = soundPool.load(context, R.raw.itemget, 1);
		itemup = soundPool.load(context, R.raw.itemup, 1);
		evid = 0;
		SOUND.playBGM1();
	}

	@SuppressWarnings("deprecation")
	public static void Jobinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		powerup = soundPool.load(context, R.raw.powerup, 1);
		evid = 0;
		SOUND.playBGM1();
	}

	@SuppressWarnings("deprecation")
	public static void Recoveryinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		recovery = soundPool.load(context, R.raw.recovery, 1);
		powerup = soundPool.load(context, R.raw.powerup, 1);
		evid = 0;
		SOUND.playBGM1();
	}

	@SuppressWarnings("deprecation")
	public static void Scaminit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		scam = soundPool.load(context, R.raw.scam, 1);
		kill = soundPool.load(context, R.raw.kill, 1);
		no = soundPool.load(context, R.raw.no, 1);
		getmoney = soundPool.load(context, R.raw.getmoney, 1);
		itemup = soundPool.load(context, R.raw.itemup, 1);
		evid = 0;
		SOUND.playBGM1();
	}

	@SuppressWarnings("deprecation")
	public static void Treasureinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		no = soundPool.load(context, R.raw.no, 1);
		itemup = soundPool.load(context, R.raw.itemup, 1);
		itemget = soundPool.load(context, R.raw.itemget, 1);
		evid = 0;
		SOUND.playBGM1();
	}

	@SuppressWarnings("deprecation")
	public static void Hotelinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		no = soundPool.load(context, R.raw.no, 1);
		powerup = soundPool.load(context, R.raw.powerup, 1);
		evid = 0;
		SOUND.playBGM1();
	}

	@SuppressWarnings("deprecation")
	public static void Usefulinit(final Context context) {
		SOUND.context = context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		getmoney = soundPool.load(context, R.raw.getmoney, 1);
		itemget = soundPool.load(context, R.raw.itemget, 1);
		itemup = soundPool.load(context, R.raw.itemup, 1);
		evid = 0;
		SOUND.playBGM1();
	}

	public static void start() {
		soundPool.play(start, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void death() {
		soundPool.play(death, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void getmoney() {
		soundPool.play(getmoney, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void powerup() {
		soundPool.play(powerup, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void itemup() {
		soundPool.play(itemup, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void itemget() {
		soundPool.play(itemget, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void no() {
		soundPool.play(no, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void attack() {
		soundPool.play(attack, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void damage() {
		soundPool.play(damage, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void knock_down() {
		soundPool.play(knock_down, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void revive() {
		soundPool.play(revive, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void escape() {
		soundPool.play(escape, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void scam() {
		soundPool.play(scam, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void kill() {
		soundPool.play(kill, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void recovery() {
		soundPool.play(recovery, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void stopBGM1() {
		if (mediaPlayer1 != null)
			mediaPlayer1.stop();
	}

	public static void stopBGM2() {
		if (mediaPlayer2 != null)
			mediaPlayer2.stop();
	}

	public static synchronized void playBGM1() {
		if (mediaPlayer1 != null) {
			mediaPlayer1.release();
			mediaPlayer1 = null;
		}
		if (evid == 0)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.eventbgm);
		else if (evid == 1)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.battlebgm);
		else if (evid == 2)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.bossbgm);
		mediaPlayer1.setLooping(true);
		mediaPlayer1.setVolume(1.0F, 1.0F);
	}

	public static synchronized void playBGM2() {
		if (mediaPlayer2 != null) {
			mediaPlayer2.release();
			mediaPlayer2 = null;
		}
		mediaPlayer2 = MediaPlayer.create(context, R.raw.movebgm);
		mediaPlayer2.setLooping(true);
		mediaPlayer2.setVolume(1.0F, 1.0F);
	}
}

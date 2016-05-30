package jp.gr.java_conf.koni.katori;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public final class Sound {
	public static Context		context;
	public static MediaPlayer	mediaPlayer1;
	public static MediaPlayer	mediaPlayer2;
	public static SoundPool		soundPool;

	public static int			startSE;
	public static int			startSEs;
	public static int			hitSE;
	public static int			clappingSE;
	public static int			clapping_shortSE;
	public static int			powerdownSE;
	public static int			powerdownSEs;
	public static int			overSE;
	public static int			spraySE;
	public static int			creamSE;
	public static int			incenseSE;

	@SuppressWarnings("deprecation")
	public static void init(final Context context) {
		Sound.context = context;
		soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		startSE = soundPool.load(context, R.raw.start, 1);
		hitSE = soundPool.load(context, R.raw.hit, 1);
		clapping_shortSE = soundPool.load(context, R.raw.clapping_short, 1);
		clappingSE = soundPool.load(context, R.raw.clapping, 1);
		powerdownSE = soundPool.load(context, R.raw.powerdown, 1);
		overSE = soundPool.load(context, R.raw.over, 1);
		spraySE = soundPool.load(context, R.raw.spray, 1);
		creamSE = soundPool.load(context, R.raw.cream, 1);
		incenseSE = soundPool.load(context, R.raw.incense, 1);
	}

	public static void startSE() {
		startSEs = soundPool.play(startSE, 1.0f, 1.0f, 0, 0, 1.0f);
	}

	public static void hitSE() {
		soundPool.play(hitSE, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void clappingSE() {
		soundPool.play(clappingSE, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void clapping_shortSE() {
		soundPool.play(clapping_shortSE, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void powerdownSE() {
		powerdownSEs = soundPool.play(powerdownSE, 1.0F, 1.0F, 0, -1, 1.0F);
	}

	public static void overSE() {
		soundPool.play(overSE, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void spraySE() {
		soundPool.play(spraySE, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void creamSE() {
		soundPool.play(creamSE, 1.0f, 1.0f, 0, 0, 1.0f);
	}

	public static void inccenseSE() {
		soundPool.play(incenseSE, 1.0F, 1.0F, 0, 0, 1.0F);
	}

	public static void playfly() {
		if (mediaPlayer2 != null) {
			mediaPlayer2.release();
			mediaPlayer2 = null;
		}
		mediaPlayer2 = MediaPlayer.create(context, R.raw.fly);
		mediaPlayer2.setLooping(true);
		mediaPlayer2.setVolume(1.0F, 1.0F);
		mediaPlayer2.start();
	}

	public static synchronized void play() {
		if (mediaPlayer1 != null) {
			mediaPlayer1.release();
			mediaPlayer1 = null;
		}
		if (Preparation.nakaCount == 0)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.play1);
		else if (Preparation.nakaCount == 1)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.play2);
		else if (Preparation.nakaCount == 2)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.play3);
		else if (Preparation.nakaCount == 3)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.play4);
		else if (Preparation.nakaCount == 4)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.play5);
		else if (Preparation.nakaCount == 5)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.play6);
		else if (Preparation.nakaCount == 6)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.play7);
		else if (Preparation.nakaCount == 7)
			mediaPlayer1 = MediaPlayer.create(context, R.raw.play8);
		mediaPlayer1.setLooping(true);
		mediaPlayer1.setVolume(1.0F, 1.0F);
		mediaPlayer1.start();
	}
}

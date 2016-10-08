package jp.gr.java_conf.bunooboi.zbs;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * Created by hiro on 2016/08/20.
 */
public class Sound {
    public static Context context;
    public static MediaPlayer mediaPlayer;
    public static SoundPool soundPool;
    public static int click;

    @SuppressWarnings("deprecation")
    public static void init(final Context context) {
        Sound.context = context;
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        click = soundPool.load(context, R.raw.click, 1);
        initMediaPlayer();
    }

    public static synchronized void click() {
        soundPool.play(click, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void initMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.air);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(1.0F, 1.0F);
    }

    public static synchronized void restartMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public static synchronized void stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public static synchronized void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

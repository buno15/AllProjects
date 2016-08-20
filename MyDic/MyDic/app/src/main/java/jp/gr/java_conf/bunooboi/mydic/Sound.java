package jp.gr.java_conf.bunooboi.mydic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * Created by hiro on 2016/08/20.
 */
public class Sound {
    public static Context context;
    public static MediaPlayer mediaPlayer1;
    public static SoundPool soundPool;
    public static int fall_start;
    public static int fall_touch;
    public static int fall_question;
    public static int fall_fin;

    @SuppressWarnings("deprecation")
    public static void init(final Context context) {
        Sound.context = context;
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        fall_start = soundPool.load(context, R.raw.fall_start, 1);
        fall_touch = soundPool.load(context, R.raw.fall_touch, 1);
        fall_question = soundPool.load(context, R.raw.fall_question, 1);
        fall_fin = soundPool.load(context, R.raw.fall_fin, 1);
    }

    public static synchronized void fall_start() {
        soundPool.play(fall_start, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void fall_touch() {
        soundPool.play(fall_touch, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void fall_question() {
        soundPool.play(fall_question, 0.8F, 0.8F, 0, 0, 1.0F);
    }

    public static synchronized void fall_fin() {
        soundPool.play(fall_fin, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void startMediaPlayer1(int id) {
        if (mediaPlayer1 != null) {
            mediaPlayer1.release();
            mediaPlayer1 = null;
        }
        switch (id) {
            case 0:
                mediaPlayer1 = MediaPlayer.create(context, R.raw.fall_bgm);
                break;
            case 1:
                mediaPlayer1 = MediaPlayer.create(context, R.raw.fall_bgm);
                break;
        }
        mediaPlayer1.setLooping(true);
        mediaPlayer1.setVolume(1.0F, 1.0F);
        mediaPlayer1.start();
    }

    public static synchronized void stopMediaPlayer1() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.pause();
        }
    }

    public static synchronized void releaseMediaPlayer1() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.stop();
            mediaPlayer1.release();
            mediaPlayer1 = null;
        }
    }
}

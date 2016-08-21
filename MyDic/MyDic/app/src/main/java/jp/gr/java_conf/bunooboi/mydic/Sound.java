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
    public static int gamestart;
    public static int fall_touch;
    public static int fall_question;
    public static int fall_fin;
    public static int select_enabled;
    public static int select_yes;
    public static int select_no;
    public static int select_count;
    public static int selectcount;

    @SuppressWarnings("deprecation")
    public static void init(final Context context) {
        Sound.context = context;
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        gamestart = soundPool.load(context, R.raw.gamestart, 1);
        fall_touch = soundPool.load(context, R.raw.fall_touch, 1);
        fall_question = soundPool.load(context, R.raw.fall_question, 1);
        fall_fin = soundPool.load(context, R.raw.fall_fin, 1);
        select_enabled = soundPool.load(context, R.raw.select_enabled, 1);
        select_yes = soundPool.load(context, R.raw.select_yes, 1);
        select_no = soundPool.load(context, R.raw.select_no, 1);
        select_count = soundPool.load(context, R.raw.select_count, 1);
    }

    public static synchronized void gamestart() {
        soundPool.play(gamestart, 1.0F, 1.0F, 0, 0, 1.0F);
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

    public static synchronized void select_enabled() {
        soundPool.play(select_enabled, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void select_yes() {
        soundPool.play(select_yes, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void select_no() {
        soundPool.play(select_no, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void select_count() {
        selectcount = soundPool.play(select_count, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void stopselect_count() {
        soundPool.stop(selectcount);
    }

    public static synchronized void startMediaPlayer1(int id) {
        if (mediaPlayer1 != null) {
            mediaPlayer1.release();
            mediaPlayer1 = null;
        }
        switch (id) {
            case 0:
                mediaPlayer1 = MediaPlayer.create(context, R.raw.select_bgm);
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

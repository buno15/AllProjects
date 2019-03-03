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
    public static MediaPlayer mediaPlayer;
    public static SoundPool soundPool;
    public static int game_start;
    public static int game_start_keep;
    public static int fall_touch;
    public static int fall_question;
    public static int fall_question_long;
    public static int fall_fin;
    public static int yes;
    public static int no;

    @SuppressWarnings("deprecation")
    public static void init(final Context context) {
        Sound.context = context;
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        game_start = soundPool.load(context, R.raw.game_start, 1);
        fall_touch = soundPool.load(context, R.raw.fall_touch, 1);
        fall_question = soundPool.load(context, R.raw.fall_question, 1);
        fall_question_long = soundPool.load(context, R.raw.fall_question_long, 1);
        fall_fin = soundPool.load(context, R.raw.fall_fin, 1);
        yes = soundPool.load(context, R.raw.select_yes, 1);
        no = soundPool.load(context, R.raw.select_no, 1);
    }

    public static synchronized void game_start() {
        game_start_keep = soundPool.play(game_start, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void fall_touch() {
        soundPool.play(fall_touch, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void fall_question_long() {
        soundPool.play(fall_question_long, 0.8F, 0.8F, 0, 0, 1.0F);
    }

    public static synchronized void fall_question() {
        soundPool.play(fall_question, 0.8F, 0.8F, 0, 0, 1.0F);
    }

    public static synchronized void fall_fin() {
        soundPool.play(fall_fin, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void yes() {
        soundPool.play(yes, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void no() {
        soundPool.play(no, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void startMediaPlayer(int id) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        switch (id) {
            case 0:
                mediaPlayer = MediaPlayer.create(context, R.raw.select_bgm);
                break;
            case 1:
                mediaPlayer = MediaPlayer.create(context, R.raw.fall_bgm);
                break;
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(1.0F, 1.0F);
        mediaPlayer.start();
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

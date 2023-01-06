package jp.gr.java_conf.bunooboi.englishtower;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {
    public static Context context;
    public static MediaPlayer mediaPlayer1;
    public static MediaPlayer mediaPlayer2;
    public static SoundPool soundPool;

    public static int start1;
    public static int start2;
    public static int start3;
    public static int start4;
    public static int start5;

    public static int open;
    public static int yes;
    public static int no;
    public static int button;
    public static int enabled;
    public static int life;
    public static int powerup;
    public static int up;
    public static int practice;

    @SuppressWarnings("deprecation")
    public static void init(final Context context) {
        Sound.context = context;
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        start1 = soundPool.load(context, R.raw.start1, 1);
        start2 = soundPool.load(context, R.raw.start2, 1);
        start3 = soundPool.load(context, R.raw.start3, 1);
        start4 = soundPool.load(context, R.raw.start4, 1);
        start5 = soundPool.load(context, R.raw.start5, 1);

        open = soundPool.load(context, R.raw.open, 1);
        yes = soundPool.load(context, R.raw.yes, 1);
        no = soundPool.load(context, R.raw.no, 1);
        button = soundPool.load(context, R.raw.button, 1);
        enabled = soundPool.load(context, R.raw.enabled, 1);
        life = soundPool.load(context, R.raw.life, 1);
        powerup = soundPool.load(context, R.raw.powerup, 1);
        up = soundPool.load(context, R.raw.up, 1);
        practice = soundPool.load(context, R.raw.practice, 1);
    }

    public static synchronized void start1() {
        soundPool.play(start1, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void start2() {
        soundPool.play(start2, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void start3() {
        soundPool.play(start3, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void start4() {
        soundPool.play(start4, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public static synchronized void start5() {
        soundPool.play(start5, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void open() {
        soundPool.play(open, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void yes() {
        soundPool.play(yes, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void no() {
        soundPool.play(no, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void button() {
        soundPool.play(button, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void enabled() {
        soundPool.play(enabled, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void life() {
        soundPool.play(life, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void powerup() {
        soundPool.play(powerup, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void up() {
        soundPool.play(up, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void practice() {
        soundPool.play(practice, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static synchronized void media2play(int id) {
        if (mediaPlayer2 != null) {
            mediaPlayer2.stop();
            mediaPlayer2.release();
            mediaPlayer2 = null;
        }
        if (id == 0) {
            mediaPlayer2 = MediaPlayer.create(context, R.raw.main1);
            mediaPlayer2.setLooping(false);
        } else if (id == 1) {
            mediaPlayer2 = MediaPlayer.create(context, R.raw.over);
            mediaPlayer2.setLooping(false);
        } else if (id == 2) {
            mediaPlayer2 = MediaPlayer.create(context, R.raw.countdown);
            mediaPlayer2.setLooping(true);
        } else if (id == 3) {
            mediaPlayer2 = MediaPlayer.create(context, R.raw.main2);
            mediaPlayer2.setLooping(false);
        }
        mediaPlayer2.setVolume(1.0F, 1.0F);
        mediaPlayer2.start();
    }

    public static synchronized void back1play() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.release();
            mediaPlayer1 = null;
        }
        mediaPlayer1 = MediaPlayer.create(context, R.raw.back1);
        mediaPlayer1.setLooping(true);
        mediaPlayer1.setVolume(1.0F, 1.0F);
        mediaPlayer1.start();
    }

    public static synchronized void practicebackplay() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.release();
            mediaPlayer1 = null;
        }
        mediaPlayer1 = MediaPlayer.create(context, R.raw.practiceback);
        mediaPlayer1.setLooping(true);
        mediaPlayer1.setVolume(1.0F, 1.0F);
        mediaPlayer1.start();
    }
}

package jp.gr.java_conf.bunooboi.gams;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.media.MediaRecorder;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Audio.Media;
import android.widget.Toast;

public class Recording {

    MediaRecorder recorder;
    Context context;
    String fileName;

    public void start(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName + ":" + PressTime.getPressTimeString() + ".3gpp";

        // 録音準備＆録音開始
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(Manager.FilePath + this.fileName);
        System.out.println(Manager.FilePath + this.fileName);
        try {
            recorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        recorder.start();
        Toast.makeText(context, "録音開始", Toast.LENGTH_SHORT).show();
    }

    public void stop() {
        try {
            recorder.stop();
            recorder.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
        ContentValues values = new ContentValues();
        ContentResolver contentResolver = context.getContentResolver();
        values.put(Audio.Media.TITLE, fileName); // タイトル名（ファイル名にしてます）
        values.put(Audio.Media.DISPLAY_NAME, fileName);// ファイル名
        values.put(Audio.Media.MIME_TYPE, "audio/3gpp");// ファイルタイプ
        values.put(Audio.Media.DATA, Manager.FilePath + fileName); // 音声の保存されたフルパス
        contentResolver.insert(Media.EXTERNAL_CONTENT_URI, values);
        Toast.makeText(context, "録音終了", Toast.LENGTH_SHORT).show();
    }
}

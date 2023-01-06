package jp.gr.java_conf.bunooboi.bmp;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/*
 * データ形式
 * 
 * ファイル名：試合名
 * 
 * 試合開始時間、試合終了時間
 * 
 * 最大ゲーム数、最大人数、最大イベント数、勝利リミット得点
 * 
 * チーム１色、チーム２色
 * 
 * プレイヤー１、エース１、エース２、エース３
 * 
 * プレイヤー１、ミス１、ミス２、ミス３
 * 
 * チーム１、得点１、得点２、得点３
 * 
 * イベント１、イベント２、イベント３
 * 
 * 押した時刻１、押した時刻２、押した時刻３
 */
public class Export {
    public StringBuilder sb = new StringBuilder();
    Context context;

    public Export(Context context) {
        this.context = context;
        sb.append(jp.gr.java_conf.bunooboi.bmp.Calculation.GameStartTime + "," + jp.gr.java_conf.bunooboi.bmp.Calculation.GameEndTime + ",\n");
        sb.append(jp.gr.java_conf.bunooboi.bmp.Calculation.MaxGame + "," + jp.gr.java_conf.bunooboi.bmp.Calculation.MaxPeople + "," + jp.gr.java_conf.bunooboi.bmp.Calculation.MaxEvent + "," +
                jp.gr.java_conf.bunooboi.bmp.Calculation.WinScore + ",\n");
        sb.append(jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[0] + "," + jp.gr.java_conf.bunooboi.bmp.Calculation.TeamColor[1] + ",\n");
    }

    public void addScoreDate(String PlayerName, int Score[], int count) {// 得点データ追加
        sb.append(PlayerName + ",");
        for (int i = 0; i < count; i++) {
            sb.append(Score[i] + ",");
        }
    }

    public void addLongDate(ArrayList<?> LongDate) {// でかいデータ（Event、PressTime）追加
        for (int i = 0; i < LongDate.size(); i++) {
            sb.append(LongDate.get(i) + ",");
        }
    }

    public void newLine() {//改行
        sb.append("\n");
    }

    public void createMemo(String memo, String filepath) {//メモ書き込み
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
                    (filepath, false), "UTF-8"));
            bw.write(memo);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Save() {// 書き込み
        try {
            Toast.makeText(context, "ファイルに書き込んでいます・・・", Toast.LENGTH_SHORT).show();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
                    (jp.gr.java_conf.bunooboi.bmp.Calculation.FilePath + jp.gr.java_conf.bunooboi.bmp.Calculation.GameName + ".csv", false), "UTF-8"));
            bw.write(new String(sb));
            bw.flush();
            bw.close();
            Toast.makeText(context, "試合データを保存しました", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

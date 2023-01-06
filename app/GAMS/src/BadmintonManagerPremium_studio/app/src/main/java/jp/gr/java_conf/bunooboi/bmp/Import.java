package jp.gr.java_conf.bunooboi.bmp;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Import {
    BufferedReader br;
    String filepath;
    String line;    // 一行
    Context context;

    public Import(Context context, String filepath) {
        this.context = context;
        this.filepath = filepath;
    }

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

    public boolean Read() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"));
            Calculation.GameName = new File(filepath).getName();
            for (int i = 0; (line = br.readLine()) != null; i++) {
                StringTokenizer st = new StringTokenizer(line, ",");
                switch (i) {
                    case 0:
                        Calculation.GameStartTime = st.nextToken();
                        Calculation.GameEndTime = st.nextToken();
                        break;
                    case 1:
                        Calculation.MaxGame = Integer.parseInt(st.nextToken());
                        Calculation.MaxPeople = Integer.parseInt(st.nextToken());
                        Calculation.MaxEvent = Integer.parseInt(st.nextToken());
                        Calculation.WinScore = Integer.parseInt(st.nextToken());
                        break;
                    case 2:
                        Calculation.TeamColor[0] = Integer.parseInt(st.nextToken());
                        Calculation.TeamColor[1] = Integer.parseInt(st.nextToken());
                        Calculation.initMkdir();
                        break;
                    case 3:
                        for (int j = 0; j < Calculation.MaxPeople; j++) {
                            switch (j) {
                                case 0:
                                    Calculation.PlayerName1[0] = st.nextToken();
                                    for (int k = 0; k < Calculation.MaxGame; k++) {
                                        Calculation.Ace1[0][k] = Integer.parseInt(st.nextToken());
                                    }
                                    break;
                                case 1:
                                    Calculation.PlayerName2[0] = st.nextToken();
                                    for (int k = 0; k < Calculation.MaxGame; k++) {
                                        Calculation.Ace2[0][k] = Integer.parseInt(st.nextToken());
                                    }
                                    break;
                                case 2:
                                    Calculation.PlayerName1[1] = st.nextToken();
                                    for (int k = 0; k < Calculation.MaxGame; k++) {
                                        Calculation.Ace1[1][k] = Integer.parseInt(st.nextToken());
                                    }
                                    break;
                                case 3:
                                    Calculation.PlayerName2[1] = st.nextToken();
                                    for (int k = 0; k < Calculation.MaxGame; k++) {
                                        Calculation.Ace2[1][k] = Integer.parseInt(st.nextToken());
                                    }
                                    break;
                            }
                        }
                        break;
                    case 4:
                        for (int j = 0; j < Calculation.MaxPeople; j++) {
                            switch (j) {
                                case 0:
                                    Calculation.PlayerName1[0] = st.nextToken();
                                    for (int k = 0; k < Calculation.MaxGame; k++) {
                                        Calculation.Miss1[0][k] = Integer.parseInt(st.nextToken());
                                    }
                                    break;
                                case 1:
                                    Calculation.PlayerName2[0] = st.nextToken();
                                    for (int k = 0; k < Calculation.MaxGame; k++) {
                                        Calculation.Miss2[0][k] = Integer.parseInt(st.nextToken());
                                    }
                                    break;
                                case 2:
                                    Calculation.PlayerName1[1] = st.nextToken();
                                    for (int k = 0; k < Calculation.MaxGame; k++) {
                                        Calculation.Miss1[1][k] = Integer.parseInt(st.nextToken());
                                    }
                                    break;
                                case 3:
                                    Calculation.PlayerName2[1] = st.nextToken();
                                    for (int k = 0; k < Calculation.MaxGame; k++) {
                                        Calculation.Miss2[1][k] = Integer.parseInt(st.nextToken());
                                    }
                                    break;
                            }
                        }
                        break;
                    case 5:
                        for (int j = 0; j < 2; j++) {
                            Calculation.TeamName[j] = st.nextToken();
                            for (int k = 0; k < Calculation.MaxGame; k++) {
                                if (j == 0)
                                    Calculation.Score1[k] = Integer.parseInt(st.nextToken());
                                else
                                    Calculation.Score2[k] = Integer.parseInt(st.nextToken());
                            }
                        }
                        break;
                    case 6:
                        for (int j = 0; j < Calculation.MaxGame; j++) {
                            for (int k = 0; k < Calculation.MaxEvent; k++) {
                                Calculation.Event.get(j).set(k, Integer.parseInt(st.nextToken()));
                            }
                        }
                        break;
                    case 7:
                        for (int j = 0; j < Calculation.MaxGame; j++) {
                            for (int k = 0; k < Calculation.MaxEvent; k++) {
                                Calculation.PressTime.get(j).set(k, st.nextToken());
                            }
                        }
                        return true;
                }
            }
            br.close();
        } catch (IOException e) {
            Toast.makeText(context, "ファイルが壊れています", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    public void readMemo(String filepath) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream
                    (filepath), "UTF-8"));
            Calculation.Memo = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                Calculation.Memo.append(line + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

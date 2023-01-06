package jp.gr.java_conf.bunooboi.bmf;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.gams.Graph;
import jp.gr.java_conf.bunooboi.gams.Manager;

public class Calculation {
    public static String GameName;                                            // 試合名(read)
    public static String TeamName[] = new String[2];                        // チーム名(read)
    public static String PlayerName1[];                                        // 選手名(read)
    public static String PlayerName2[];                                        // 選手名(read)

    public static int Score1[];                                            // 得点[MaxGame](read)
    public static int Score2[];                                            // 得点[MaxGame](read)
    public static int Ace1[][];                                            // エース[MaxPeople/2][MaxGame](read)
    public static int Ace2[][];                                            // エース[MaxPeople/2][MaxPeople/2](read)
    public static int Miss1[][];                                            // ミス[MaxPeople/2][MaxGame](read)
    public static int Miss2[][];                                            // ミス[MaxPeople/2][MaxGame](read)

    public static int MaxGame;                                            // 最大ゲーム数(read)
    public static int MaxPeople;                                            // 最大人数(read)
    public static int MaxEvent;                                            // 最大イベント数(read)

    public static int GameCount;                                            // ゲームカウント
    public static int EventCount;                                            // イベントカウント

    public static int WinDif;                                                // 勝利点差
    public static int WinScore;                                            // 勝利得点(read)
    public static int ScoreUp;                                            // 得点上昇値

    public static int TeamColor[] = new int[2];                            // チーム１色(read)
    public static int Team1Win;                                            // チーム１勝利回数
    public static int Team2Win;                                            // チーム２勝利回数

    public static String GameStartTime;                                        // 試合開始時間(read)
    public static String GameEndTime;                                        // 試合終了時間(read)

    public static ArrayList<ArrayList<Integer>> Event = new ArrayList<ArrayList<Integer>>();    // イベント(read)
    public static ArrayList<ArrayList<String>> PressTime = new ArrayList<ArrayList<String>>();    // ボタン押した時間(read)

    public static String FilePath;                                            // ファイルパス
    public static StringBuilder Memo = new StringBuilder();                    // メモ

    public static void init() {//新しいデータ作成
        if (Event != null) {
            Event.clear();
        }
        if (PressTime != null) {
            PressTime.clear();
        }
        PlayerName1 = new String[MaxPeople / 2];
        PlayerName2 = new String[MaxPeople / 2];
        Score1 = new int[MaxGame];
        Score2 = new int[MaxGame];
        Ace1 = new int[MaxPeople / 2][MaxGame];
        Ace2 = new int[MaxPeople / 2][MaxGame];
        Miss1 = new int[MaxPeople / 2][MaxGame];
        Miss2 = new int[MaxPeople / 2][MaxGame];
        for (int i = 0; i < MaxGame; i++) {// イベント設定
            Event.add(new ArrayList<Integer>());
            PressTime.add(new ArrayList<String>());
            for (int j = 0; j < MaxEvent; j++) {
                Event.get(i).add(100);
                PressTime.get(i).add("none");
            }
        }
        GameCount = 0;
        EventCount = 0;
        Team1Win = 0;
        Team2Win = 0;
        WinDif = 2;
        ScoreUp = 1;
        FilePath = Environment.getExternalStorageDirectory() + "/BadmintonManager";
        File file = new File(FilePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(FilePath + "/" + GameName);
        if (!file.exists()) {
            file.mkdir();
        }
        FilePath = file.getPath() + "/";
        Manager.init(FilePath);
    }

    public static void initMkdir() {//履歴からのデータ読み込みのときに使用
        if (Event != null) {
            Event.clear();
        }
        if (PressTime != null) {
            PressTime.clear();
        }
        PlayerName1 = new String[MaxPeople / 2];
        PlayerName2 = new String[MaxPeople / 2];
        Score1 = new int[MaxGame];
        Score2 = new int[MaxGame];
        Ace1 = new int[MaxPeople / 2][MaxGame];
        Ace2 = new int[MaxPeople / 2][MaxGame];
        Miss1 = new int[MaxPeople / 2][MaxGame];
        Miss2 = new int[MaxPeople / 2][MaxGame];
        for (int i = 0; i < MaxGame; i++) {// イベント設定
            Event.add(new ArrayList<Integer>());
            PressTime.add(new ArrayList<String>());
            for (int j = 0; j < MaxEvent; j++) {
                Event.get(i).add(100);
                PressTime.get(i).add("none");
            }
        }
        GameCount = 0;
        EventCount = 0;
        Team1Win = 0;
        Team2Win = 0;
        WinDif = 2;
        ScoreUp = 1;
        Memo = new StringBuilder();
        Calculation.GameName = Calculation.GameName.replaceAll(".csv", "");
        FilePath = Environment.getExternalStorageDirectory() + "/BadmintonManager/" + GameName + "/";
    }

    static void clearGraph(Graph graph1[], Graph graph2[]) {//グラフ初期化
        for (int i = 0; i < graph1.length; i++) {
            graph1[i].clean();
            graph2[i].clean();
        }
    }

    static void ReadEvent(ArrayList<Integer> event, Graph graph1[], Graph graph2[]) {//過去データ読み込み
        clearGraph(graph1, graph2);//グラフ初期化
        int score[] = new int[2 + MaxPeople * 2];//得点数（スコア＋各プレイヤーエースミス）
        for (int i = 0; i < Calculation.MaxEvent; i++) {
            switch (event.get(i)) {
                case 0:
                    graph1[0].setCheckScoreUnit(i, ++score[0]);
                    break;
                case 1:
                    graph2[0].setCheckScoreUnit(i, ++score[1]);
                    break;
                case 2:
                    graph1[1].setCheckScoreUnitAttribute(i, ++score[2], 0);
                    break;
                case 3:
                    graph1[1].setCheckScoreUnitAttribute(i, ++score[2], 1);
                    break;
                case 4:
                    graph1[1].setCheckScoreUnitAttribute(i, ++score[2], 2);
                    break;
                case 5:
                    graph1[1].setCheckScoreUnitAttribute(i, ++score[3], 3);
                    break;
                case 6:
                    graph1[1].setCheckScoreUnitAttribute(i, ++score[3], 4);
                    break;
                case 7:
                    graph1[1].setCheckScoreUnitAttribute(i, ++score[3], 5);
                    break;
                case 8:
                    graph2[1].setCheckScoreUnitAttribute(i, ++score[4], 0);
                    break;
                case 9:
                    graph2[1].setCheckScoreUnitAttribute(i, ++score[4], 1);
                    break;
                case 10:
                    graph2[1].setCheckScoreUnitAttribute(i, ++score[4], 2);
                    break;
                case 11:
                    graph2[1].setCheckScoreUnitAttribute(i, ++score[5], 3);
                    break;
                case 12:
                    graph2[1].setCheckScoreUnitAttribute(i, ++score[5], 4);
                    break;
                case 13:
                    graph2[1].setCheckScoreUnitAttribute(i, ++score[5], 5);
                    break;
                case 14:
                    graph1[2].setCheckScoreUnitAttribute(i, ++score[6], 0);
                    break;
                case 15:
                    graph1[2].setCheckScoreUnitAttribute(i, ++score[6], 1);
                    break;
                case 16:
                    graph1[2].setCheckScoreUnitAttribute(i, ++score[6], 2);
                    break;
                case 17:
                    graph1[2].setCheckScoreUnitAttribute(i, ++score[7], 3);
                    break;
                case 18:
                    graph1[2].setCheckScoreUnitAttribute(i, ++score[7], 4);
                    break;
                case 19:
                    graph1[2].setCheckScoreUnitAttribute(i, ++score[7], 5);
                    break;
                case 20:
                    graph2[2].setCheckScoreUnitAttribute(i, ++score[8], 0);
                    break;
                case 21:
                    graph2[2].setCheckScoreUnitAttribute(i, ++score[8], 1);
                    break;
                case 22:
                    graph2[2].setCheckScoreUnitAttribute(i, ++score[8], 2);
                    break;
                case 23:
                    graph2[2].setCheckScoreUnitAttribute(i, ++score[9], 3);
                    break;
                case 24:
                    graph2[2].setCheckScoreUnitAttribute(i, ++score[9], 4);
                    break;
                case 25:
                    graph2[2].setCheckScoreUnitAttribute(i, ++score[9], 5);
                    break;
            }
        }
    }

    public static boolean Exists() {
        File file = new File(Environment.getExternalStorageDirectory() + "/BadmintonManager/" + GameName);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static void reSet() {// イベント、押した時間リセット
        for (int i = 0; i < MaxGame; i++) {
            Event.add(new ArrayList<Integer>());
            PressTime.add(new ArrayList<String>());
            for (int j = 0; j < MaxEvent; j++) {
                Event.get(i).add(100);
                PressTime.get(i).add("none");
            }
        }
    }

    public static void Remove() {// 全削除
        GameName = "";
        TeamName = new String[2];
        PlayerName1 = new String[2];
        PlayerName2 = new String[2];
        Score1 = new int[3];
        Score2 = new int[3];
        Ace1 = new int[3][2];
        Ace2 = new int[3][2];
        Miss1 = new int[3][2];
        Miss2 = new int[3][2];
        MaxGame = 0;
        MaxPeople = 0;
        MaxEvent = 0;
        GameCount = 0;
        EventCount = 0;
        WinDif = 0;
        WinScore = 0;
        ScoreUp = 0;
        TeamColor = new int[2];
        Team1Win = 0;
        Team2Win = 0;
        GameStartTime = "";
        GameEndTime = "";
        Event.clear();
        PressTime.clear();
        FilePath = "";
        Memo = null;
    }

    public static boolean MaxEvent() {
        if (EventCount < MaxEvent) {
            return true;
        } else {
            return false;
        }
    }
}

package jp.gr.java_conf.bunooboi.mydic;

import java.util.ArrayList;


/**
 * Created by hiro on 2016/08/04.
 */
public class Sentences {
    public static ArrayList<String> config = new ArrayList<>();//ファイルの名前
    public static ArrayList<ArrayList<Sentence>> sentences = new ArrayList<>();//各データ
    static int ConfigIndex = 0;//アクセスファイル番号
    static String text = "検索結果はありません";//読み上げテキスト
    static String link = "";//resリンク
    static String key = "";//検索キー

    public static void init() {
        config = Input.getInput().readConfig();
        sentences = new ArrayList<>();
        for (int i = 0; i < config.size(); i++) {
            sentences.add(Input.getInput(config.get(i)).read());
            System.out.println(sentences.get(i));
        }
    }

    public static ArrayList<String> getConfigList() {
        ArrayList<String> list = new ArrayList<>();
        for (String s : config) {
            s = s.replace("/", "");
            s = s.replace(".csv", "");
            list.add(s);
        }
        return list;
    }

    public static ArrayList<String> getSentencesList(int index) {
        ArrayList<String> list = new ArrayList<>();
        for (Sentence s : sentences.get(index)) {
            list.add(s.getTitle().replaceAll("&&", ""));
        }
        return list;
    }

    public static boolean serchDic(String serch) {
        for (int i = 0; i < config.size(); i++) {
            if (config.get(i).equals("/" + serch + ".csv")) {
                return true;
            }
        }
        return false;
    }

    public static boolean serchTitle(String serch, int index1, int index2) {
        for (int i = 0; i < sentences.size(); i++) {
            for (int j = 0; j < sentences.get(i).size(); j++) {
                if (i == index1 && j == index2)
                    continue;
                if (sentences.get(i).get(j).getTitle().equals(serch)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean serchKey(String serch, int index1, int index2) {
        for (int i = 0; i < sentences.size(); i++) {
            for (int j = 0; j < sentences.get(i).size(); j++) {
                if (i == index1 && j == index2)
                    continue;
                if (sentences.get(i).get(j).serchKey(serch)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean serchKey(String[] serch, int index1, int index2) {
        for (int i = 0; i < sentences.size(); i++) {
            for (int j = 0; j < sentences.get(i).size(); j++) {
                if (i == index1 && j == index2)
                    continue;
                for (String s : serch) {
                    if (sentences.get(i).get(j).serchKey(s)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean serch(String serch) {
        key = serch;
        for (int i = 0; i < sentences.size(); i++) {
            for (int j = 0; j < sentences.get(i).size(); j++) {
                if (sentences.get(i).get(j).serchKey(serch)) {
                    text = sentences.get(i).get(j).getText();
                    link = sentences.get(i).get(j).getLink();
                    return true;
                }
            }
        }
        text = "検索結果はありません";
        link = "/none";
        return false;
    }

    public static boolean serch(String serch, int index1, int index2) {
        key = serch;
        for (int i = 0; i < sentences.size(); i++) {
            for (int j = 0; j < sentences.get(i).size(); j++) {
                if (i == index1 && j == index2)
                    continue;
                if (sentences.get(i).get(j).serchKey(serch)) {
                    text = sentences.get(i).get(j).getText();
                    link = sentences.get(i).get(j).getLink();
                    return true;
                }
            }
        }
        text = "検索結果はありません";
        link = "/none";
        return false;
    }
}

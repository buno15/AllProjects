package jp.gr.java_conf.bunooboi.mydic;

import java.util.ArrayList;


/**
 * Created by hiro on 2016/08/04.
 */
public class Sentences {
    static ArrayList<Sentence> sentences = new ArrayList<>();
    static String text = "検索結果はありません";
    static String link = "";
    static String key = "";

    public static void init() {
        sentences = Input.getInput().read();
    }

    public static ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        for (Sentence s : sentences) {
            list.add(s.getTitle());
        }
        return list;
    }

    public static boolean serch(String serch) {
        key = serch;
        for (int i = 0; i < sentences.size(); i++) {
            if (sentences.get(i).serchKey(serch)) {
                text = sentences.get(i).getText();
                link = sentences.get(i).getLink();
                return true;
            }
        }
        text = "検索結果はありません";
        link = "none";
        return false;
    }

    public static boolean serch(String serch, int index) {
        key = serch;
        for (int i = 0; i < sentences.size(); i++) {
            if (i == index)
                continue;
            if (sentences.get(i).serchKey(serch)) {
                text = sentences.get(i).getText();
                link = sentences.get(i).getLink();
                return true;
            }
        }
        text = "検索結果はありません";
        link = "/none";
        return false;
    }
}

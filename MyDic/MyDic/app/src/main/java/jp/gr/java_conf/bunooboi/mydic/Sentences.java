package jp.gr.java_conf.bunooboi.mydic;

import java.util.ArrayList;


/**
 * Created by hiro on 2016/08/04.
 */
public class Sentences {
    static ArrayList<Sentence> sentence = new ArrayList<>();
    static String text;
    static String link;

    public static void init() {
        sentence = new ArrayList<>();
        sentence.add(new Sentence("Integer", new String[]{"int", "いんと", "イント"},
                "Integerクラスは、プリミティブ型intの値をオブジェクトにラップします。Integer型のオブジェクトには、型がintの単一フィールドが含まれます。" +
                        "さらにこのクラスは、intをStringに、Stringをintに変換する各種メソッドや、intの処理時に役立つ定数およびメソッドも提供します。",
                "/java_se_8_api/api/java/lang/Integer.html"));
    }

    public static ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        for (Sentence s : sentence) {
            list.add(s.getTitle());
        }
        return list;
    }

    public static boolean serch(String serch) {
        for (int i = 0; i < sentence.size(); i++) {
            if (sentence.get(i).serchKey(serch)) {
                text = sentence.get(i).getText();
                link = sentence.get(i).getLink();
                return true;
            }
        }
        text = "検索結果はありません";
        link = "none";
        return false;
    }
}

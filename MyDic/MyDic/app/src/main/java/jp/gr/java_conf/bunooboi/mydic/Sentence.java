package jp.gr.java_conf.bunooboi.mydic;

/**
 * Created by hiro on 2016/08/04.
 */
public class Sentence {
    private String title;
    private String key[];
    private String text;
    private String link;

    public Sentence(String title, String key[], String text, String link) {
        this.title = title;
        this.key = key;
        this.text = text;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String[] getKey() {
        return key;
    }

    public boolean serchKey(String serch) {
        for (int i = 0; i < key.length; i++) {
            if (key[i].equals(serch)) {
                return true;
            }
        }
        return false;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }
}

package jp.gr.java_conf.bunooboi.mydic;

import java.io.Serializable;

/**
 * Created by hiro on 2016/08/04.
 */
public class Sentence implements Serializable {
    private String title;
    private String key[];
    private String link;
    private String text;

    public Sentence(String title, String key[], String link, String text) {
        this.title = title;
        this.key = key;
        this.link = link;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String[] getKey() {
        return key;
    }

    public String getKeytoString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length; i++) {
            if (i == key.length - 1) {
                sb.append(key[i]);
            } else {
                sb.append(key[i] + "%");
            }
        }
        return new String(sb);
    }

    public boolean serchKey(String serch) {
        for (int i = 0; i < key.length; i++) {
            System.out.println(key[i]);
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

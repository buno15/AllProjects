package jp.gr.java_conf.bunooboi.mydic;

import java.io.Serializable;

/**
 * Created by hiro on 2016/08/04.
 */
public class Sentence implements Serializable {
    private String title;
    private int level;
    private String key[];
    private String link;
    private String selector;
    private String description;
    private String text;

    public Sentence(String title, int level, String key[], String link, String selector, String description, String
            text) {
        this.title = title;
        this.level = level;
        this.key = key;
        this.link = link;
        this.selector = selector;
        this.description = description;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public int getLevel() {
        return level;
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

    public String getLink() {
        return link;
    }

    public String getSelector() {
        return selector;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }
}

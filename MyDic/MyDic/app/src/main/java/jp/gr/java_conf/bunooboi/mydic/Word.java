package jp.gr.java_conf.bunooboi.mydic;

import java.util.ArrayList;

/**
 * Created by hiro on 2016/08/04.
 */
public class Word {
    private String word;
    private String description;
    private ArrayList<String> tag;

    public Word(String word, String description, ArrayList<String> tag) {
        this.word = word;
        this.description = description;
        this.tag = tag;
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getTag() {
        return tag;
    }


    public boolean searchTag(String search) {//Wordに該当タグが存在
        for (int i = 0; i < tag.size(); i++) {
            if (tag.get(i).equals(search)) {
                return true;
            }
        }
        return false;
    }
}

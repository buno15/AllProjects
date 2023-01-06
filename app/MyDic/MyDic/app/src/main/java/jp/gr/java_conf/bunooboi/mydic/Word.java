package jp.gr.java_conf.bunooboi.mydic;

import java.util.ArrayList;

/**
 * Created by hiro on 2016/08/04.
 */
public class Word {
    private String dictionary;
    private String word;
    private String[] description;
    private ArrayList<String> tag;

    public Word(String dictionary, String word, String[] description, ArrayList<String> tag) {
        this.dictionary = dictionary;
        this.word = word;
        this.description = description;
        this.tag = tag;
    }

    public String getDictionary() {
        return dictionary;
    }

    public String getWord() {
        return word;
    }

    public String[] getDescription() {
        return description;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public boolean searchDic(String search) {
        if (dictionary.equals(search)) {
            return true;
        }
        return false;
    }

    public boolean searchDescription(String search) {
        for (int i = 0; i < description.length; i++) {
            if (description[i].equals(search) && !description[i].equals(""))
                return true;
        }
        return false;
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

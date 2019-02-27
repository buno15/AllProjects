package jp.gr.java_conf.bunooboi.mydic;

import android.os.Environment;

import java.util.ArrayList;

public class Values {
    public static final String RootPath = Environment.getExternalStorageDirectory() + "/MyDic";
    public static final String TagPath = RootPath + "/tag.csv";
    public static final String WordPath = RootPath + "/word.csv";
    public static ArrayList<String> tags = new ArrayList<>();
    public static ArrayList<Word> words = new ArrayList<>();

    public static void init() {
        Input input = Input.getInput();
        tags = input.getTags();
        words = input.getWords();
    }

    public static int getIndex(String name) {
        for (int i = 0; i < Values.words.size(); i++) {
            if (Values.words.get(i).getWord().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean searchTags(String search) {
        for (int i = 0; i < Values.tags.size(); i++) {
            if (Values.tags.get(i).equals(search)) {
                return true;
            }
        }
        return false;
    }
}

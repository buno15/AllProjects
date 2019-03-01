package jp.gr.java_conf.bunooboi.mydic;

import java.util.ArrayList;

public class Game {
    public static int id;
    public static ArrayList<Word> words = new ArrayList<>();
    public static Word answer;

    public static void init() {
        id = 0;
        words = new ArrayList<>();
    }
}

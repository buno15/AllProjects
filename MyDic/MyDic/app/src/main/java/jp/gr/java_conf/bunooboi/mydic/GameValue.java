package jp.gr.java_conf.bunooboi.mydic;

import java.util.ArrayList;

/**
 * Created by hiro on 2016/08/21.
 */
public class GameValue {
    public static ArrayList<Integer> participationConfig = new ArrayList<>();//出題する辞書番号
    static int id;

    public static Sentence getQuestion(boolean levelLimit) {
        int configIndex = participationConfig.get((int) Math.floor(Math.random() * participationConfig.size()));
        Sentence sentence = new Sentence("", 1, new String[]{""}, "", "", "", "");
        if (levelLimit) {
            switch ((int) Math.floor(Math.random() * 6)) {
                case 0:
                    sentence = questionLevel(configIndex, 1);
                    break;
                case 1:
                case 2:
                    sentence = questionLevel(configIndex, 2);
                    break;
                case 3:
                case 4:
                case 5:
                    sentence = questionLevel(configIndex, 3);
                    break;
            }
        } else {
            do {
                sentence = Sentences.sentences.get(configIndex).get((int) Math.floor(Math.random() * Sentences.sentences.get(configIndex).size()));
            } while (sentence.getLevel() == 0);
        }
        return sentence;
    }

    static Sentence questionLevel(int configIndex, int level) {
        Sentence sentence;
        int count = 0;
        do {
            sentence = Sentences.sentences.get(configIndex).get((int) Math.floor(Math.random() * Sentences.sentences.get(configIndex).size()));
            if (count++ >= 9)
                break;
        } while (sentence.getLevel() != level && sentence.getLevel() == 0);
        return sentence;
    }

    public static String createRandomNumber() {
        String str[] = new String[4];
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf((int) Math.floor(Math.random() * 10));
            if (str[i].equals("10")) {
                str[i] = "・";
            }
        }
        return str[0] + str[1] + "-" + str[2] + str[3];
    }

    public static String createRandomColor() {
        char str[] = new char[6];
        String color = "0123456789ABCDEF";
        for (int i = 0; i < str.length; i++) {
            str[i] = color.charAt((int) Math.floor(Math.random() * color.length()));
        }
        String s = "#" + str[0] + str[1] + str[2] + str[3] + str[4] + str[5];
        if (s.equals("#000000")) {
            s = "#ffdd99";
        }
        return s;
    }
}

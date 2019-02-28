package jp.gr.java_conf.bunooboi.mydic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by hiro on 2016/08/04.
 */
public class Input {
    private static final Input input = new Input();

    private Input() {
    }

    public static Input getInput() {//Input初期化
        File root = new File(Values.RootPath);
        if (!root.exists()) {
            root.mkdirs();
        }
        return input;
    }

    public synchronized ArrayList<String> getDictionaries() {//dictionaries入手
        ArrayList<String> dictionaries = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(Values.DicPath), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String s[] = line.split(",");
                for (int i = 0; i < s.length; i++) {
                    dictionaries.add(s[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dictionaries;
        }
    }

    public synchronized ArrayList<String> getTags() {//Tags入手
        ArrayList<String> tags = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(Values.TagPath), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String s[] = line.split(",");
                for (int i = 0; i < s.length; i++) {
                    tags.add(s[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tags;
        }
    }

    public synchronized ArrayList<Word> getWords() {//Words入手
        ArrayList<Word> words = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(Values.WordPath), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String dictionary = st.nextToken();
                String word = st.nextToken();
                String[] description = new String[3];
                for (int i = 0; i < description.length; i++) {
                    description[i] = st.nextToken();
                    if (description[i].equals("?"))
                        description[i] = "";
                }
                int size = Integer.parseInt(st.nextToken());
                ArrayList<String> tag = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    tag.add(st.nextToken());
                }
                words.add(new Word(dictionary, word, description, tag));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return words;
        }
    }

}

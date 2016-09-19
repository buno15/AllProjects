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
    private static String dataPath = Values.DataPath;
    private static final Input input = new Input();

    private Input() {
    }

    public static Input getInput() {
        File file = new File(Values.DataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return input;
    }

    public static Input getInput(String configName) {
        dataPath = Values.DataPath + configName;
        File file = new File(dataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return input;
    }

    public synchronized ArrayList<String> readConfig() {
        File file[] = new File(Values.DataPath).listFiles();
        ArrayList<String> list = new ArrayList<>();
        for (File f : file) {
            list.add("/" + f.getName());
        }
        return list;
    }

    public synchronized ArrayList<Sentence> read() {
        ArrayList<Sentence> sentences = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(dataPath), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String title = st.nextToken();
                int level = Integer.parseInt(st.nextToken());
                String key[] = st.nextToken().split("%");
                String link = st.nextToken();
                String selector = st.nextToken();
                String description = st.nextToken();
                String text = st.nextToken();
                text = text.replaceAll("%%", "\n");
                sentences.add(new Sentence(title, level, key, link, selector, description, text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sentences;
        }
    }

    public synchronized ArrayList<String> repeatRead() {
        ArrayList<String> sentences = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(Values.ConfigPath + "/repeat.txt"), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                sentences.add(line.replaceAll("%%",""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sentences;
        }
    }

    public synchronized ArrayList<String> allSpeakRead() {
        ArrayList<String> sentences = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(Values.ConfigPath + "/allSpeak.txt"), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                sentences.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sentences;
        }
    }
}

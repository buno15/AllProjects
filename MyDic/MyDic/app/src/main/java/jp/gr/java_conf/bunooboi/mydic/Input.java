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
    String configPath = Values.ConfigPath + "/config.csv";
    private static final Input input = new Input();

    private Input() {
    }

    public static Input getInput() {
        File file = new File(Values.ConfigPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return input;
    }

    public synchronized ArrayList<Sentence> read() {
        ArrayList<Sentence> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(configPath), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String title = st.nextToken();
                String key[] = st.nextToken().split(" ");
                String link = st.nextToken();
                String text = st.nextToken();
                text = text.replaceAll("&&", "\n");
                list.add(new Sentence(title, key, link, text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}

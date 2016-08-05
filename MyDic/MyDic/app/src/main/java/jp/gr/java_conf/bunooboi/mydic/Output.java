package jp.gr.java_conf.bunooboi.mydic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by hiro on 2016/08/04.
 */
public class Output {
    String configPath = Values.ConfigPath + "/config.csv";
    private static final Output output = new Output();

    private Output() {
    }

    public static Output getOutput() {
        File file = new File(Values.ConfigPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return output;
    }

    public synchronized void write(boolean overwrite) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(configPath, overwrite), "UTF-8");
            for (int i = 0; i < Sentences.sentences.size(); i++) {
                Sentence s = Sentences.sentences.get(i);
                String text = s.getText().replaceAll("\n", "&&");
                osw.write(s.getTitle() + "," + s.getKeytoString() + "," + s.getLink() + "," + text+"\n");
            }
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                osw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void write(ArrayList<Sentence> sentences, boolean overwrite) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(configPath, overwrite), "UTF-8");
            for (int i = 0; i < sentences.size(); i++) {
                Sentence s = sentences.get(i);
                osw.write(s.getTitle() + "," + s.getKeytoString() + "," + s.getLink() + "," + s.getText());
            }
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                osw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

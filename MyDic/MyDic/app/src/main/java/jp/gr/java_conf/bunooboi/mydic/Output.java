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
    private static String dataPath = Values.DataPath;
    private static final Output output = new Output();

    private Output() {
    }

    public static Output getOutput() {
        File file = new File(Values.DataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return output;
    }

    public static Output getOutput(String configName) {
        dataPath = Values.DataPath + configName;
        File file = new File(dataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return output;
    }

    public synchronized void createDic(String text) {
        File file = new File(Values.DataPath + "/" + text + ".csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Sentences.data.add("/" + text + ".csv");
        Sentences.sentences.add(new ArrayList<Sentence>());
    }

    public synchronized void write(boolean overwrite, int index) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(dataPath, overwrite), "UTF-8");
            for (int i = 0; i < Sentences.sentences.get(index).size(); i++) {
                Sentence s = Sentences.sentences.get(index).get(i);
                String text = s.getText().replaceAll("\n", "%%");
                osw.write(s.getTitle() + "," + s.getLevel() + "," + s.getKeytoString() + "," + s.getLink() + "," + s.getSelector() + "," + s.getDescription() + "," + text + "\n");
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

    public synchronized void repeatWrite(ArrayList<Sentence> sentences) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(Values.ConfigPath + "/repeat.txt", false), "UTF-8");
            for (int i = 0; i < sentences.size(); i++) {
                String title = sentences.get(i).getTitle().replaceAll(" ", "");
                String description;
                if (Values.readtext) {
                    description = sentences.get(i).getText().replaceAll(" ", "");
                } else {
                    description = sentences.get(i).getDescription().replaceAll(" ", "");
                }
                osw.write(createSpeechText(title, description) + "\n");
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

    public synchronized void allSpeakWrite(ArrayList<Sentence> sentences) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(Values.ConfigPath + "/allSpeak.txt", false), "UTF-8");
            for (int i = 0; i < sentences.size(); i++) {
                String title = sentences.get(i).getTitle();
                String text = sentences.get(i).getText();
                if (text.equals("none")) {
                    text = "";
                }
                osw.write("「" + title + "」。" + text + "\n");
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

    String createSpeechText(String title, String description) {
        if(description.equals("none")){
            return title;
        }
        if (title.equals(description)) {
            return title;
        } else {
            return "「" + title + "」。" + description.replaceAll("\n","%%");
        }
    }
}

package jp.gr.java_conf.bunooboi.mydic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by hiro on 2016/08/04.
 */
public class Output {
    private static final Output output = new Output();

    private Output() {
    }

    public static Output getOutput() {
        File root = new File(Values.RootPath);
        if (!root.exists()) {
            root.mkdirs();
        }
        return output;
    }

    public synchronized void writeDictionaries() {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(Values.DicPath, false), "UTF-8");
            for (int i = 0; i < Values.dictionaries.size(); i++) {
                if (!(Values.dictionaries.size() == 0))
                    osw.write(Values.dictionaries.get(i) + ",");
                else
                    osw.write("");
            }
            osw.write("\n");
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

    public synchronized void writeTags() {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(Values.TagPath, false), "UTF-8");
            for (int i = 0; i < Values.tags.size(); i++) {
                osw.write(Values.tags.get(i) + ",");
            }
            osw.write("\n");
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

    public synchronized void writeWord() {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(Values.WordPath, false), "UTF-8");
            for (int i = 0; i < Values.words.size(); i++) {
                StringBuffer tagLine = new StringBuffer();
                for (int j = 0; j < Values.words.get(i).getTag().size(); j++) {
                    tagLine.append(Values.words.get(i).getTag().get(j));
                    tagLine.append(",");
                }
                StringBuffer descriptionLine = new StringBuffer();
                for (int j = 0; j < Values.words.get(i).getDescription().length; j++) {
                    String s = Values.words.get(i).getDescription()[j];
                    if (s.equals(""))
                        descriptionLine.append("?");
                    else
                        descriptionLine.append(Values.words.get(i).getDescription()[j]);
                    descriptionLine.append(",");
                }
                osw.write(Values.words.get(i).getDictionary() + "," + Values.words.get(i).getWord() + "," + descriptionLine + "" + Values.words.get(i).getTag().size() + "," + tagLine);
                osw.write("\n");
            }
            osw.write("\n");
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

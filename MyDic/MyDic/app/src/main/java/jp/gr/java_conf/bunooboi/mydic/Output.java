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

    public synchronized void writeTags(boolean overwrite) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(Values.TagPath, overwrite), "UTF-8");
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

    public synchronized void writeWord(boolean overwrite) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(Values.WordPath, overwrite), "UTF-8");
            for (int i = 0; i < Values.words.size(); i++) {
                StringBuffer tagLine = new StringBuffer();
                for (int j = 0; j < Values.words.get(i).getTag().size(); j++) {
                    tagLine.append(Values.words.get(i).getTag().get(j));
                    tagLine.append(",");
                }
                osw.write(Values.words.get(i).getWord() + "," + Values.words.get(i).getDescription() + "," + Values.words.get(i).getTag().size() + "," + tagLine);
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

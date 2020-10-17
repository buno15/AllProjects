package jp.gr.java_conf.bunooboi.rema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by bunooboi on 2016/10/27.
 */

public class Output {
    private static final Output output = new Output();


    private Output() {
    }

    public static Output getInstance() {
        File file = new File(App.DataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return output;
    }

    public void write() {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(App.DataPath + "/data.txt", false), "UTF-8");
            for (int i = 0; i < App.titles.size(); i++) {
                String m = App.answers.get(i) + "=" + App.titles.get(i);
                osw.write(m + "\n");
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

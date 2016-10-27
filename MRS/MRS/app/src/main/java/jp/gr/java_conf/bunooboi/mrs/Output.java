package jp.gr.java_conf.bunooboi.mrs;

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
        File file = new File(Memorys.DataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return output;
    }

    public void write() {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(Memorys.DataPath + "/data.csv", false), "UTF-8");
            for (int i = 0; i < Memorys.memorys.size(); i++) {
                Memory m = Memorys.memorys.get(i);
                int tryed[] = new int[4];
                for (int j = 0; j < 4; j++) {
                    if (m.tryed[j]) {
                        tryed[j] = 1;
                    } else {
                        tryed[j] = 0;
                    }
                }
                osw.write(m.title + "," + m.YEAR + "," + m.MONTH + "," + m.DAY + "," + tryed[0] + "," + tryed[1] + "," + tryed[2] + "," + tryed[3] + "\n");
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

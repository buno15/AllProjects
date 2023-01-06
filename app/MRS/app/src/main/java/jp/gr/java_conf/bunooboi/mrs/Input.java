package jp.gr.java_conf.bunooboi.mrs;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by bunooboi on 2016/10/27.
 */

public class Input {
    private static final Input input = new Input();

    private Input() {

    }

    public static Input getInstance() {
        File file = new File(Memorys.DataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return input;
    }

    public ArrayList<Memory> read() {
        ArrayList<Memory> memorys = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(Memorys.DataPath + "/data.csv"), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String title = st.nextToken();
                int year = Integer.parseInt(st.nextToken());
                int month = Integer.parseInt(st.nextToken());
                int day = Integer.parseInt(st.nextToken());
                boolean tryed[] = new boolean[4];
                for (int i = 0; i < 4; i++) {
                    if (Integer.parseInt(st.nextToken()) == 0) {
                        tryed[i] = false;
                    } else {
                        tryed[i] = true;
                    }
                }
                memorys.add(new Memory(title, year, month, day, tryed));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return memorys;
        }
    }
}

package jp.gr.java_conf.bunooboi.rema;

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
        File file = new File(App.DataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return input;
    }

    public ArrayList<String> readTitle() {
        ArrayList<String> titles = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(App.DataPath + "/data.txt"), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.equals(""))
                    continue;
                String s[] = line.split("=");
                titles.add(s[1]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return titles;
        }
    }

    public ArrayList<String> readAnswer() {
        ArrayList<String> answers = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(App.DataPath + "/data.txt"), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.equals(""))
                    continue;
                String s[] = line.split("=");
                answers.add(s[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return answers;
        }
    }
}

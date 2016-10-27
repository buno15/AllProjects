package jp.gr.java_conf.bunooboi.mrs;

import android.os.Environment;

import java.util.ArrayList;

/**
 * Created by bunooboi on 2016/10/27.
 */

public class Memorys {
    static ArrayList<Memory> memorys = new ArrayList<>();
    static ArrayList<Integer> indexs = new ArrayList<>();
    public static final String DataPath = Environment.getExternalStorageDirectory() + "/MRS";

    public static void init() {
        memorys = Input.getInstance().read();
        indexs = new ArrayList<>();
    }

    public static ArrayList<String> getMemorysTitle() {
        ArrayList<String> notMemorys = new ArrayList<>();
        serch();
        for (int i = 0; i < memorys.size(); i++) {
            notMemorys.add(memorys.get(i).title);
        }
        return notMemorys;
    }

    public static ArrayList<String> getMemorysDate() {
        ArrayList<String> notMemorys = new ArrayList<>();
        serch();
        for (int i = 0; i < memorys.size(); i++) {
            notMemorys.add(memorys.get(i).getDateStr(-1));
        }
        return notMemorys;
    }

    public static ArrayList<String> getNotMemorysTitle() {
        ArrayList<String> notMemorys = new ArrayList<>();
        serch();
        for (int i = 0; i < memorys.size(); i++) {
            for (int j = 0; j < indexs.size(); j++) {
                if (i == indexs.get(j)) {
                    notMemorys.add(memorys.get(i).title);
                    break;
                }
            }
        }
        return notMemorys;
    }

    public static ArrayList<String> getNotMemorysDate() {
        ArrayList<String> notMemorys = new ArrayList<>();
        serch();
        for (int i = 0; i < memorys.size(); i++) {
            for (int j = 0; j < indexs.size(); j++) {
                if (i == indexs.get(j)) {
                    notMemorys.add(memorys.get(i).getDateStr(-1));
                    break;
                }
            }
        }
        return notMemorys;
    }

    public static void setNotMemory(int index) {
        memorys.get(indexs.get(index)).check();
        indexs.remove(index);
        Output.getInstance().write();
    }

    public static void serch() {
        indexs = new ArrayList<>();
        for (int i = 0; i < memorys.size(); i++) {
            if (memorys.get(i).serch() >= 0) {
                indexs.add(i);
            }
        }
    }
}

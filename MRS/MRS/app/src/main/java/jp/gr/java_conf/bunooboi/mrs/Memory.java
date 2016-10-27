package jp.gr.java_conf.bunooboi.mrs;

import java.util.Calendar;

/**
 * Created by bunooboi on 2016/10/27.
 */

public class Memory {
    String title;
    final int YEAR;
    final int MONTH;
    final int DAY;
    boolean tryed[] = new boolean[4];

    public Memory(String title, int year, int month, int day, boolean tryed[]) {
        this.title = title;
        this.YEAR = year;
        this.MONTH = month;
        this.DAY = day;
        this.tryed = tryed;
    }

    public void check() {
        for (int i = 0; i < 4; i++) {
            if (tryed[i]) {
                continue;
            }
            tryed[i] = true;
            break;
        }
    }

    public int serch() {
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.MONTH, 1);
        for (int i = 0; i < 4; i++) {
            if (tryed[i]) {
                continue;
            }
            Calendar c2 = getDate(i);
            int dif = c1.compareTo(c2);
            if (dif >= 0) {
                return i;
            }
        }
        return -1;
    }

    public Calendar getDate(int count) {
        Calendar c = Calendar.getInstance();
        c.set(YEAR, MONTH, DAY);
        if (count == 0) {
            c.add(Calendar.DAY_OF_MONTH, 1);
        } else if (count == 1) {
            c.add(Calendar.DAY_OF_MONTH, 7);
        } else if (count == 2) {
            c.add(Calendar.MONTH, 1);
        } else if (count == 3) {
            c.add(Calendar.MONTH, 6);
        }
        return c;
    }

    public String getDateStr(int count) {
        Calendar c = Calendar.getInstance();
        c.set(YEAR, MONTH, DAY);
        if (count >= 0) {
            if (count == 0) {
                c.add(Calendar.DAY_OF_MONTH, 1);
            } else if (count == 1) {
                c.add(Calendar.DAY_OF_MONTH, 7);
            } else if (count == 2) {
                c.add(Calendar.MONTH, 1);
            } else if (count == 3) {
                c.add(Calendar.MONTH, 6);
            }
            return c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH);
        } else {
            return YEAR + "/" + MONTH + "/" + DAY;
        }
    }
}

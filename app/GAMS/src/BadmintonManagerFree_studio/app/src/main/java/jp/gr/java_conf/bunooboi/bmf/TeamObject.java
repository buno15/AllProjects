package jp.gr.java_conf.bunooboi.bmf;

import android.graphics.Color;


/**
 * Created by hiro on 2016/07/11.
 */
public class TeamObject {
    public static final int BLACK = 0;// 黒
    public static final int WHITE = 1;// 白
    public static final int BLUE = 2;// 青
    public static final int GREEN = 3;// 緑
    public static final int ORANGE = 4;// 橙
    public static final int PINK = 5;// 桃
    public static final int PURPLE = 6;// 紫

    public static int getColor(int id) {// 色取得
        switch (id) {
            case BLACK:
                return Color.BLACK;
            case WHITE:
                return Color.WHITE;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.parseColor("#039e40");
            case ORANGE:
                return Color.parseColor("#ff4f02");
            case PINK:
                return Color.parseColor("#ff55ff");
            case PURPLE:
                return Color.parseColor("#800080");
        }
        return 0;
    }

    static int getWare(int id) {// ウェア取得
        switch (id) {
            case BLACK:
                return R.drawable.ware_black;
            case WHITE:
                return R.drawable.ware_white;
            case BLUE:
                return R.drawable.ware_blue;
            case GREEN:
                return R.drawable.ware_green;
            case ORANGE:
                return R.drawable.ware_orange;
            case PINK:
                return R.drawable.ware_pink;
            case PURPLE:
                return R.drawable.ware_purple;
        }
        return 0;
    }

    static int getGraphColor(int id) {// グラフ用テキスト背景取得
        switch (id) {
            case BLACK:
                return R.drawable.text_black;
            case WHITE:
                return R.drawable.text_white;
            case BLUE:
                return R.drawable.text_blue;
            case GREEN:
                return R.drawable.text_green;
            case ORANGE:
                return R.drawable.text_orange;
            case PINK:
                return R.drawable.text_pink;
            case PURPLE:
                return R.drawable.text_purple;
        }
        return 0;
    }
}

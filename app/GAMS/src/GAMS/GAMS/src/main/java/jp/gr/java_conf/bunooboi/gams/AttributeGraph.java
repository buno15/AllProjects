package jp.gr.java_conf.bunooboi.gams;

import java.util.HashMap;

/**
 * Created by hiro on 2016/07/10.
 * Checkするためのグラフのxmlファイルをattributeに設定
 */
public class AttributeGraph {
    private static HashMap<Integer, Integer> CheckAttribute = new HashMap<>();//チェック属性

    public static void addAttribute(int key, int attribute) {//Check属性追加
        CheckAttribute.put(key, attribute);
    }

    public static int getAttribute(int key) {//Check属性入手
        return CheckAttribute.get(key);
    }
}

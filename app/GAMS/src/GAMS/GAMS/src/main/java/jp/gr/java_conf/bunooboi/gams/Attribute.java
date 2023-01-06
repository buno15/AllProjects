package jp.gr.java_conf.bunooboi.gams;

import java.util.HashMap;

/**
 * Created by hiro on 2016/07/11.
 */
public class Attribute {
    private HashMap<Integer, Object> attribute = new HashMap<>();

    public void addAttribute(int key, Object value) {
        attribute.put(key, value);
    }

    public Object getAttribute(int key) {
        return attribute.get(key);
    }
}

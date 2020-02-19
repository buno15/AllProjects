package jp.gr.java_conf.bunooboi.warasibe2;

import java.util.ArrayList;

public class Event {
    private ArrayList<Scene> event;
    private boolean flag = false;

    public Event() {
        event = new ArrayList<>();
    }

    public void startEvent() {
        if (event.get(0) != null && !flag) {
            MainActivity.nowScene = event.get(0);
            MainActivity.setNowScene(event.get(0));
            MainActivity.flag = true;
            flag = true;
        }
    }

    public void addScene(Scene s[]) {
        for (int i = 0; i < s.length; i++) {
            event.add(s[i]);
        }
    }

    public int getSceneSize() {
        return event.size();
    }

}

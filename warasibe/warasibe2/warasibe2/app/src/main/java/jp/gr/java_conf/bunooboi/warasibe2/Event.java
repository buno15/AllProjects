package jp.gr.java_conf.bunooboi.warasibe2;

import java.util.ArrayList;

public class Event {
    private ArrayList<Scene> event;
    private boolean flag = false;

    public Event(int size) {
        event = new ArrayList<>();
        event.add(new Scene());
        for (int i = 0; i < size; i++) {
            event.add(new Scene());
        }
    }

    public void start() {
        if (event.get(1) != null) {
            MainActivity.nowScene = event.get(1);
            MainActivity.setNowScene(event.get(1));
            MainActivity.flag = true;
            flag = true;
        }
        System.out.println(flag);
    }

    public void connect() {
        for (int i = 0; i <= event.size(); i++) {
            if (i < event.size() - 1)
                event.get(i).setScene(event.get(i + 1), null, null, null, null);
        }
    }

    public void connect(int i, Scene next, int nextDir) {
        event.get(i).setScene(next, nextDir);
    }

    public boolean done() {
        return flag;
    }

    public void preparation() {
        flag = false;
    }


    public void addScene(Scene s) {
        event.add(s);
    }

    public Scene getScene(int i) {
        return event.get(i);
    }

    public Scene getFirst() {
        return event.get(0);
    }


    public Scene getLast() {
        return event.get(event.size());
    }
}

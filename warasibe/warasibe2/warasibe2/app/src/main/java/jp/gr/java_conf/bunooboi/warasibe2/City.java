package jp.gr.java_conf.bunooboi.warasibe2;

public class City {
    private String name;
    private boolean request;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean getRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }
}

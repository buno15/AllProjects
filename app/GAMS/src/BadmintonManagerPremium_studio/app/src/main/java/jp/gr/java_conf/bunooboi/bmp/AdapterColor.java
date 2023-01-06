package jp.gr.java_conf.bunooboi.bmp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.gams.Manager;


public class AdapterColor extends BaseAdapter {
    private Context context;
    private ArrayList<String> DateList;
    float scale;

    public AdapterColor(Context context) {
        super();
        this.context = context;
        DateList = null;
        scale = Manager.getScaleSize(context);
    }

    public void setData(ArrayList<String> data) {
        DateList = data;
    }

    @Override
    public int getCount() {
        return DateList.size();
    }

    @Override
    public Object getItem(int position) {
        return DateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.teamcolor_selected, null);
        }
        String text = DateList.get(position);
        TextView tv = (TextView) convertView.findViewById(R.id.selected_text);
        tv.setText(text);
        tv.setTextSize(5 * scale);
        View image = (View) convertView.findViewById(R.id.selected_image);
        switch (position) {
            case 0:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.BLACK));
                break;
            case 1:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.WHITE));
                break;
            case 2:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.BLUE));
                break;
            case 3:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.GREEN));
                break;
            case 4:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.ORANGE));
                break;
            case 5:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.PINK));
                break;
            case 6:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.PURPLE));
                break;

        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.teamcolor_item, null);
        }
        String text = DateList.get(position);
        TextView tv = (TextView) convertView.findViewById(jp.gr.java_conf.bunooboi.gams.R.id.item_text);
        tv.setText(text);
        tv.setTextSize(5 * scale);

        View image = (View) convertView.findViewById(R.id.item_image);
        switch (position) {
            case 0:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.BLACK));
                break;
            case 1:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.WHITE));
                break;
            case 2:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.BLUE));
                break;
            case 3:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.GREEN));
                break;
            case 4:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.ORANGE));
                break;
            case 5:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.PINK));
                break;
            case 6:
                image.setBackgroundColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.getColor(jp.gr.java_conf.bunooboi.bmp.TeamObject.PURPLE));
                break;

        }
        return convertView;
    }

}

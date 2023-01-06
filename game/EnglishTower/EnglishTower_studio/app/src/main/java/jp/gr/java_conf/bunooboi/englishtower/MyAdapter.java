package jp.gr.java_conf.bunooboi.englishtower;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hiro on 2016/07/16.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list = new ArrayList<>();
    private float scale;

    public MyAdapter(Context context) {
        this.context = context;
        scale = Tower.getScaleSize(context);
    }

    public void setData(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.adapter, null);
        }
        String text = list.get(position);
        TextView textview = (TextView) convertView.findViewById(R.id.adapter_text);
        textview.setText(text);
        textview.setTextSize(14 * scale);
        textview.setBackgroundResource(R.drawable.zzz_button1);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.adapter, null);
        }
        String text = list.get(position);
        TextView textview = (TextView) convertView.findViewById(R.id.adapter_text);
        textview.setText(text);
        textview.setTextSize(14 * scale);
        textview.setBackgroundColor(Color.WHITE);
        return convertView;
    }
}

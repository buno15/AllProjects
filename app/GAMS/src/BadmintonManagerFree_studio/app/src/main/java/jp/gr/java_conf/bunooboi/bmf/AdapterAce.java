package jp.gr.java_conf.bunooboi.bmf;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.gams.Manager;

public class AdapterAce extends BaseAdapter {
    private Context context;
    private ArrayList<String> DataList;
    float scale;

    public AdapterAce(Context context) {
        super();
        this.context = context;
        DataList = null;
        scale = Manager.getScaleSize(context);
    }

    public void setData(ArrayList<String> data) {
        DataList = data;
    }

    @Override
    public int getCount() {
        return DataList.size();
    }

    @Override
    public Object getItem(int position) {
        return DataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.ace_selected, null);
        }
        View view = (View) convertView.findViewById(R.id.ace_selected);
        view.setBackgroundResource(R.drawable.ace_button);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.ace_item, null);
        }
        String text = DataList.get(position);
        TextView tv = (TextView) convertView.findViewById(R.id.ace_item_text);
        tv.setText(text);
        tv.setTextSize(6 * scale);
        switch (position) {
            case 0:
                tv.setBackgroundColor(Color.WHITE);
                break;
            case 1:
                tv.setBackgroundColor(jp.gr.java_conf.bunooboi.bmf.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmf.AceMissObject.ACE_GOOD));
                break;
            case 2:
                tv.setBackgroundColor(jp.gr.java_conf.bunooboi.bmf.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmf.AceMissObject.ACE_NORMAL));
                break;
            case 3:
                tv.setBackgroundColor(jp.gr.java_conf.bunooboi.bmf.AceMissObject.getColor(jp.gr.java_conf.bunooboi.bmf.AceMissObject.ACE_LUCKY));
                break;
        }
        return convertView;
    }
}

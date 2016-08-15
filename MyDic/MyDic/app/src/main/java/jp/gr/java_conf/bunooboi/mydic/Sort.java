package jp.gr.java_conf.bunooboi.mydic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by hiro on 2016/08/15.
 */
public class Sort extends AppCompatActivity {
    static String[] PREFS;
    int mDraggingPosition = -1;
    int startPosition = -1;
    SampleAdapter mAdapter;
    SortableListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort);
        PREFS = Sentences.getList().toArray(new String[]{});

        mAdapter = new SampleAdapter();
        mListView = (SortableListView) findViewById(R.id.list);
        mListView.setDragListener(new DragListener());
        mListView.setSortable(true);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                startActivity(new Intent(getApplicationContext(), ListEdit.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), ListEdit.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class SampleAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return PREFS.length;
        }

        @Override
        public String getItem(int position) {
            return PREFS[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        android.R.layout.simple_list_item_1, null);
            }
            final TextView view = (TextView) convertView;
            view.setText(PREFS[position]);
            view.setVisibility(position == mDraggingPosition ? View.INVISIBLE
                    : View.VISIBLE);
            return convertView;
        }
    }

    class DragListener extends SortableListView.SimpleDragListener {
        @Override
        public int onStartDrag(int position) {
            startPosition = position;
            mDraggingPosition = position;
            mListView.invalidateViews();
            return position;
        }

        @Override
        public int onDuringDrag(int positionFrom, int positionTo) {
            if (positionFrom < 0 || positionTo < 0
                    || positionFrom == positionTo) {
                return positionFrom;
            }
            int i;
            if (positionFrom < positionTo) {
                final int min = positionFrom;
                final int max = positionTo;
                final String data = PREFS[min];
                i = min;
                while (i < max) {
                    PREFS[i] = PREFS[++i];
                }
                PREFS[max] = data;
            } else if (positionFrom > positionTo) {
                final int min = positionTo;
                final int max = positionFrom;
                final String data = PREFS[max];
                i = max;
                while (i > min) {
                    PREFS[i] = PREFS[--i];
                }
                PREFS[min] = data;
            }
            mDraggingPosition = positionTo;
            mListView.invalidateViews();
            return positionTo;
        }

        @Override
        public boolean onStopDrag(int positionFrom, int positionTo) {
            Sentence sen = Sentences.sentences.get(startPosition);
            Sentences.sentences.remove(startPosition);
            Sentences.sentences.add(positionFrom, sen);
            Output.getOutput().write(false);
            mDraggingPosition = -1;
            mListView.invalidateViews();
            return super.onStopDrag(positionFrom, positionTo);
        }
    }

}

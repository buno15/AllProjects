package jp.gr.java_conf.bunooboi.englishtower;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Dictionary_List extends Activity {
    int PARENT_DATA = 11;                                            // 親リストの数
    List<Map<String, String>> ParentList = new ArrayList<Map<String, String>>();            // 親リスト
    List<List<Map<String, String>>> ChildList = new ArrayList<List<Map<String, String>>>();    // 子リスト
    ExpandableListView List;                                                        // list本体
    ArrayList<ArrayList<Word>> word = new ArrayList<ArrayList<Word>>();                // word全て
    int indexnumber;                                                // index番号

    LinearLayout root;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    Animation anim;
    Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.dictionarylist);

        List = (ExpandableListView) findViewById(R.id.list);

        root = (LinearLayout) findViewById(R.id.root);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button1.setText("あいうえお順");
        button2.setText("ABC順");
        button3.setText("品詞順");
        button4.setText("メイン");

        button1.setTextSize(6 * Tower.getScaleSize(getApplicationContext()));
        button2.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
        button3.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));
        button4.setTextSize(10 * Tower.getScaleSize(getApplicationContext()));

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
                AIUEO();
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
                ABC();
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
                PartofSpeech();
            }
        });
        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.start2();
                anim.setDuration(1000);
                root.startAnimation(anim);
                if (t != null) {
                    t.cancel();
                    t = null;
                }
                t = new Timer();
                t.schedule(new EndThisTimer(), 1000);
            }
        });
        anim = AnimationUtils.loadAnimation(this, R.anim.endthis);
        AIUEO();
        Sound.start2();
    }

    void remove() {
        Sound.start3();
        ParentList = new ArrayList<Map<String, String>>(); // 親リスト
        ChildList = new ArrayList<List<Map<String, String>>>(); // 子リスト
        word = new ArrayList<ArrayList<Word>>();
    }

    void AIUEO() {
        PARENT_DATA = 45;
        for (int i = 0; i < PARENT_DATA; i++) {
            Map<String, String> parentData = new HashMap<String, String>();
            switch (i) {
                case 0:
                    parentData.put("parent", "あ");
                    break;
                case 1:
                    parentData.put("parent", "い");
                    break;
                case 2:
                    parentData.put("parent", "う");
                    break;
                case 3:
                    parentData.put("parent", "え");
                    break;
                case 4:
                    parentData.put("parent", "お");
                    break;
                case 5:
                    parentData.put("parent", "か");
                    break;
                case 6:
                    parentData.put("parent", "き");
                    break;
                case 7:
                    parentData.put("parent", "く");
                    break;
                case 8:
                    parentData.put("parent", "け");
                    break;
                case 9:
                    parentData.put("parent", "こ");
                    break;
                case 10:
                    parentData.put("parent", "さ");
                    break;
                case 11:
                    parentData.put("parent", "し");
                    break;
                case 12:
                    parentData.put("parent", "す");
                    break;
                case 13:
                    parentData.put("parent", "せ");
                    break;
                case 14:
                    parentData.put("parent", "そ");
                    break;
                case 15:
                    parentData.put("parent", "た");
                    break;
                case 16:
                    parentData.put("parent", "ち");
                    break;
                case 17:
                    parentData.put("parent", "つ");
                    break;
                case 18:
                    parentData.put("parent", "て");
                    break;
                case 19:
                    parentData.put("parent", "と");
                    break;
                case 20:
                    parentData.put("parent", "な");
                    break;
                case 21:
                    parentData.put("parent", "に");
                    break;
                case 22:
                    parentData.put("parent", "ぬ");
                    break;
                case 23:
                    parentData.put("parent", "ね");
                    break;
                case 24:
                    parentData.put("parent", "の");
                    break;
                case 25:
                    parentData.put("parent", "は");
                    break;
                case 26:
                    parentData.put("parent", "ひ");
                    break;
                case 27:
                    parentData.put("parent", "ふ");
                    break;
                case 28:
                    parentData.put("parent", "へ");
                    break;
                case 29:
                    parentData.put("parent", "ほ");
                    break;
                case 30:
                    parentData.put("parent", "ま");
                    break;
                case 31:
                    parentData.put("parent", "み");
                    break;
                case 32:
                    parentData.put("parent", "む");
                    break;
                case 33:
                    parentData.put("parent", "め");
                    break;
                case 34:
                    parentData.put("parent", "も");
                    break;
                case 35:
                    parentData.put("parent", "や");
                    break;
                case 36:
                    parentData.put("parent", "ゆ");
                    break;
                case 37:
                    parentData.put("parent", "よ");
                    break;
                case 38:
                    parentData.put("parent", "ら");
                    break;
                case 39:
                    parentData.put("parent", "り");
                    break;
                case 40:
                    parentData.put("parent", "る");
                    break;
                case 41:
                    parentData.put("parent", "れ");
                    break;
                case 42:
                    parentData.put("parent", "ろ");
                    break;
                case 43:
                    parentData.put("parent", "わ");
                    break;
                case 44:
                    parentData.put("parent", "を");
                    break;
            }
            ParentList.add(parentData);
        }
        for (int i = 0; i < PARENT_DATA; i++) {
            List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
            word.add(DataBase.getHeadAllJp(i));
            for (int j = 0; j < DataBase.getHeadCountJp(i); j++) {
                Map<String, String> childData = new HashMap<String, String>();
                childData.put("child", word.get(i).get(j).JAPANESE);
                childList.add(childData);
            }
            ChildList.add(childList);
        }

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, ParentList, android.R.layout.simple_expandable_list_item_1,
                new String[]{"parent"}, new int[]{android.R.id.text1}, ChildList, android.R.layout.simple_expandable_list_item_1,
                new String[]{"child"}, new int[]{android.R.id.text1});

        List.setAdapter(adapter);
        List.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                Sound.start3();
                indexnumber = (int) id;
                return false;
            }
        });

        List.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    List.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });

        List.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                Dictionary_Overview.word = word.get(indexnumber).get((int) id).WORD;
                Dictionary_Overview.japanese = word.get(indexnumber).get((int) id).JAPANESE;
                Dictionary_Overview.image = word.get(indexnumber).get((int) id).IMAGE;
                Dictionary_Overview.id = word.get(indexnumber).get((int) id).ID;

                Intent i = new Intent(getApplicationContext(), Dictionary_Overview.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0, 0);

                Sound.start2();
                return false;
            }
        });
    }


    void ABC() {
        PARENT_DATA = 26;
        for (int i = 0; i < PARENT_DATA; i++) {
            Map<String, String> parentData = new HashMap<String, String>();
            switch (i) {
                case 0:
                    parentData.put("parent", "A");
                    break;
                case 1:
                    parentData.put("parent", "B");
                    break;
                case 2:
                    parentData.put("parent", "C");
                    break;
                case 3:
                    parentData.put("parent", "D");
                    break;
                case 4:
                    parentData.put("parent", "E");
                    break;
                case 5:
                    parentData.put("parent", "F");
                    break;
                case 6:
                    parentData.put("parent", "G");
                    break;
                case 7:
                    parentData.put("parent", "H");
                    break;
                case 8:
                    parentData.put("parent", "I");
                    break;
                case 9:
                    parentData.put("parent", "J");
                    break;
                case 10:
                    parentData.put("parent", "K");
                    break;
                case 11:
                    parentData.put("parent", "L");
                    break;
                case 12:
                    parentData.put("parent", "M");
                    break;
                case 13:
                    parentData.put("parent", "N");
                    break;
                case 14:
                    parentData.put("parent", "O");
                    break;
                case 15:
                    parentData.put("parent", "P");
                    break;
                case 16:
                    parentData.put("parent", "Q");
                    break;
                case 17:
                    parentData.put("parent", "R");
                    break;
                case 18:
                    parentData.put("parent", "S");
                    break;
                case 19:
                    parentData.put("parent", "T");
                    break;
                case 20:
                    parentData.put("parent", "U");
                    break;
                case 21:
                    parentData.put("parent", "V");
                    break;
                case 22:
                    parentData.put("parent", "W");
                    break;
                case 23:
                    parentData.put("parent", "X");
                    break;
                case 24:
                    parentData.put("parent", "Y");
                    break;
                case 25:
                    parentData.put("parent", "Z");
                    break;
            }
            ParentList.add(parentData);
        }
        for (int i = 0; i < PARENT_DATA; i++) {
            List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
            word.add(DataBase.getHeadAll(i));
            for (int j = 0; j < DataBase.getHeadCount(i); j++) {
                Map<String, String> childData = new HashMap<String, String>();
                childData.put("child", word.get(i).get(j).WORD);
                childList.add(childData);
            }
            ChildList.add(childList);
        }

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, ParentList, android.R.layout.simple_expandable_list_item_1,
                new String[]{"parent"}, new int[]{android.R.id.text1}, ChildList, android.R.layout.simple_expandable_list_item_1,
                new String[]{"child"}, new int[]{android.R.id.text1});

        List.setAdapter(adapter);
        List.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                Sound.start3();
                indexnumber = (int) id;
                return false;
            }
        });

        List.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    List.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });

        List.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                Dictionary_Overview.word = word.get(indexnumber).get((int) id).WORD;
                Dictionary_Overview.japanese = word.get(indexnumber).get((int) id).JAPANESE;
                Dictionary_Overview.image = word.get(indexnumber).get((int) id).IMAGE;
                Dictionary_Overview.id = word.get(indexnumber).get((int) id).ID;

                Intent i = new Intent(getApplicationContext(), Dictionary_Overview.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0, 0);

                Sound.start2();
                return false;
            }
        });
    }

    void PartofSpeech() {
        PARENT_DATA = 11;
        for (int i = 0; i < PARENT_DATA; i++) {
            Map<String, String> parentData = new HashMap<String, String>();
            switch (i) {
                case 0:
                    parentData.put("parent", "名詞");
                    break;
                case 1:
                    parentData.put("parent", "代名詞");
                    break;
                case 2:
                    parentData.put("parent", "動詞");
                    break;
                case 3:
                    parentData.put("parent", "形容詞");
                    break;
                case 4:
                    parentData.put("parent", "副詞");
                    break;
                case 5:
                    parentData.put("parent", "冠詞");
                    break;
                case 6:
                    parentData.put("parent", "助動詞");
                    break;
                case 7:
                    parentData.put("parent", "前置詞");
                    break;
                case 8:
                    parentData.put("parent", "疑問詞");
                    break;
                case 9:
                    parentData.put("parent", "感動詞");
                    break;
                case 10:
                    parentData.put("parent", "接続詞");
                    break;
            }
            ParentList.add(parentData);
        }
        for (int i = 0; i < PARENT_DATA; i++) {
            List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
            word.add(DataBase.getPartIDsAll(i));
            for (int j = 0; j < DataBase.getPartIDCount(i); j++) {
                Map<String, String> childData = new HashMap<String, String>();
                childData.put("child", word.get(i).get(j).WORD);
                childList.add(childData);
            }
            ChildList.add(childList);
        }

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, ParentList, android.R.layout.simple_expandable_list_item_1,
                new String[]{"parent"}, new int[]{android.R.id.text1}, ChildList, android.R.layout.simple_expandable_list_item_1,
                new String[]{"child"}, new int[]{android.R.id.text1});

        List.setAdapter(adapter);
        List.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                Sound.start3();
                indexnumber = (int) id;
                return false;
            }
        });

        List.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    List.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });

        List.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                Dictionary_Overview.word = word.get(indexnumber).get((int) id).WORD;
                Dictionary_Overview.japanese = word.get(indexnumber).get((int) id).JAPANESE;
                Dictionary_Overview.image = word.get(indexnumber).get((int) id).IMAGE;
                Dictionary_Overview.id = word.get(indexnumber).get((int) id).ID;

                Intent i = new Intent(getApplicationContext(), Dictionary_Overview.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0, 0);

                Sound.start2();
                return false;
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class EndThisTimer extends TimerTask {
        @Override
        public void run() {
            root.setAlpha(0);
            Intent i = new Intent(getApplicationContext(), Main.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            finish();
            overridePendingTransition(0, 0);
        }
    }
}

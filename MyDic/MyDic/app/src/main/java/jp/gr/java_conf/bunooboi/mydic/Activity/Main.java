package jp.gr.java_conf.bunooboi.mydic.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jp.gr.java_conf.bunooboi.mydic.Output;
import jp.gr.java_conf.bunooboi.mydic.R;
import jp.gr.java_conf.bunooboi.mydic.Values;

public class Main extends AppCompatActivity {
    ListView listView;
    EditText editText;
    ImageButton search;
    ImageButton dictionary;
    ImageButton tag;
    ImageButton edit;
    ImageButton game;
    FloatingActionButton add;
    ArrayAdapter adapter;
    static int listClick = 0;//0=dictionary,1=tag,2=詳細tag
    static String selectWord = "";//indexによらないTag
    static int clickState = 0;// clickState=0,dictionary clickState=1,tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        verifyStoragePermissions(this);
        Values.init();

        editText = findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    buttonFocus(0);
                    selectWord = editText.getText().toString();
                    search(selectWord);
                    return true;
                }
                return false;
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // フォーカスが外れた場合キーボードを非表示にする
                    InputMethodManager inputMethodMgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodMgr.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFocus(0);
                selectWord = editText.getText().toString();
                search(selectWord);
            }
        });

        dictionary = findViewById(R.id.list);
        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFocus(1);
                setDictionaryList();
            }
        });

        tag = findViewById(R.id.tag);
        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFocus(2);
                setTagList();
            }
        });

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Values.dictionaries.size() == 0) {
                    Toast.makeText(Main.this, "There is no dictionary data.", Toast.LENGTH_SHORT).show();
                } else {
                    newEdit();
                    startActivity(new Intent(getApplicationContext(), Edit.class));
                    finish();
                }
            }
        });
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(Main.this);
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(Main.this);
                alertDlg.setMessage("New Dictionary");
                alertDlg.setView(editText);
                alertDlg.setPositiveButton("edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Values.dictionaries.add(editText.getText().toString());
                        Output.getOutput().writeDictionaries();
                        reload();
                    }
                });
                alertDlg.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
            }
        });

        game = findViewById(R.id.game);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GameStart.class));
                finish();
            }
        });


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listView = findViewById(R.id.main);
        if (listClick == 0) {//リストの選択状態確認
            setDictionaryList();
        } else if (listClick == 1) {
            setTagList();
        } else if (listClick == 2) {
            setDetailList();
        } else if (listClick == 3) {
            search(selectWord);
        }
        listHeight();
    }

    public void search(String search) {//タグ検索
        clearEnable(dictionary);
        clearEnable(tag);
        adapter.clear();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Values.words.size(); i++) {
            if (Values.words.get(i).searchTag(search))
                list.add(Values.words.get(i).getWord());
        }
        if (list.size() == 0) {
            listClick = 0;
            Toast.makeText(this,
                    "There is no such tag.", Toast.LENGTH_SHORT).show();
        } else
            listClick = 3;
        adapter.addAll(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listview = (ListView) adapterView;
                String item = (String) listview.getItemAtPosition(i);
                fixEdit(item);
                startActivity(new Intent(getApplicationContext(), Edit.class));
                finish();
            }
        });
        listHeight();
    }

    public void setDictionaryList() {//辞書リストの構築
        setEnable(dictionary);
        clearEnable(tag);
        listClick = 0;
        adapter.clear();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Values.dictionaries.size(); i++) {
            list.add(Values.dictionaries.get(i));

        }
        adapter.addAll(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listview = (ListView) parent;
                selectWord = (String) listview.getItemAtPosition(position);
                clickState = 0;
                setDetailList();
            }
        });
        listHeight();
    }

    public void setTagList() {//タグリストの構築
        setEnable(tag);
        clearEnable(dictionary);
        listClick = 1;
        adapter.clear();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Values.tags.size(); i++) {
            list.add(Values.tags.get(i));
        }
        adapter.addAll(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listview = (ListView) parent;
                selectWord = (String) listview.getItemAtPosition(position);
                clickState = 1;
                setDetailList();
            }
        });
        listHeight();
    }

    public void setDetailList() {//詳細タグリストの構築
        listClick = 2;
        adapter.clear();
        ArrayList<String> list = new ArrayList<>();
        list.add("<-");
        if (clickState == 0) {
            setEnable(dictionary);
            clearEnable(tag);
            for (int i = 0; i < Values.words.size(); i++) {
                if (Values.words.get(i).searchDic(selectWord))
                    list.add(Values.words.get(i).getWord());
            }
        } else if (clickState == 1) {
            setEnable(tag);
            clearEnable(dictionary);
            for (int i = 0; i < Values.words.size(); i++) {
                if (Values.words.get(i).searchTag(selectWord))
                    list.add(Values.words.get(i).getWord());
            }
        }
        adapter.addAll(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    if (clickState == 0)
                        setDictionaryList();
                    else if (clickState == 1)
                        setTagList();
                } else {
                    ListView listview = (ListView) adapterView;
                    String item = (String) listview.getItemAtPosition(i);
                    fixEdit(item);
                    startActivity(new Intent(getApplicationContext(), Edit.class));
                    finish();
                }
            }

        });
        listHeight();
    }

    public void newEdit() {//Editが新規作成画面
        Edit.fix = false;
        Edit.dictionary = "";
        Edit.word = "";
        Edit.description[0] = "";
        Edit.description[1] = "";
        Edit.description[2] = "";
        Edit.tag = new ArrayList<>();
        Edit.index = -1;
    }

    public void fixEdit(String item) {//Editが編集画面
        int index = Values.getIndex(item);
        Edit.fix = true;
        Edit.dictionary = Values.words.get(index).getDictionary();
        Edit.word = Values.words.get(index).getWord();
        Edit.description = Values.words.get(index).getDescription();
        Edit.tag = Values.words.get(index).getTag();
        Edit.index = Values.getIndex(item);
    }

    public void buttonFocus(int i) {
        switch (i) {
            case 0:
                search.setFocusable(true);
                search.setFocusableInTouchMode(true);
                dictionary.setFocusable(false);
                dictionary.setFocusableInTouchMode(false);
                tag.setFocusable(false);
                tag.setFocusableInTouchMode(false);
                search.requestFocus();
                break;
            case 1:
                search.setFocusable(false);
                search.setFocusableInTouchMode(false);
                dictionary.setFocusable(true);
                dictionary.setFocusableInTouchMode(true);
                tag.setFocusable(false);
                tag.setFocusableInTouchMode(false);
                dictionary.requestFocus();
                break;
            case 2:
                search.setFocusable(false);
                search.setFocusableInTouchMode(false);
                dictionary.setFocusable(false);
                dictionary.setFocusableInTouchMode(false);
                tag.setFocusable(true);
                tag.setFocusableInTouchMode(true);
                tag.requestFocus();
                break;
        }
    }

    public void setEnable(ImageButton button) {//ボタン使用不可
        button.setEnabled(false);
        button.setAlpha(.5f);
    }

    public void clearEnable(ImageButton button) {//ボタン使用可能
        button.setEnabled(true);
        button.setAlpha(1f);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Main.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void listHeight() {// リスト高さ調節
        ListAdapter la = listView.getAdapter();
        if (la == null) {
            return;
        }

        int i;
        int h = 0; // ListView トータルの高さ

        for (i = 0; i < la.getCount(); i++) {
            View item = la.getView(i, null, listView);
            item.measure(0, 0);
            h += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams p = listView.getLayoutParams();
        p.height = h + (listView.getDividerHeight() * (la.getCount() - 1));
        listView.setLayoutParams(p);
    }

    private void reload() {// 画面リロード
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public static int getPctX(Context context, int value) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        return size.x / 100 * value;
    }

    public static int getPctY(Context context, int value) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        return size.y / 100 * value;
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;//パーミッションチェックの変数
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {//パーミッションチェック
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}

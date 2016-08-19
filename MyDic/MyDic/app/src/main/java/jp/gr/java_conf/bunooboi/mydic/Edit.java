package jp.gr.java_conf.bunooboi.mydic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by hiro on 2016/08/04.
 */
public class Edit extends AppCompatActivity {
    static String title;
    static int level;
    static String[] key;
    static String link;
    static String description;
    static String text;
    static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (index == -1)
            setTitle("データ作成");
        else
            setTitle("データ編集");
        setContentView(R.layout.edit);

        View_DetectableKeyboardEventLayout root = (View_DetectableKeyboardEventLayout) findViewById(R.id.root);
        final EditText edittext[] = new EditText[5];
        final Button button = (Button) findViewById(R.id.button);
        edittext[0] = (EditText) findViewById(R.id.edittext1);
        edittext[1] = (EditText) findViewById(R.id.edittext2);
        edittext[2] = (EditText) findViewById(R.id.edittext3);
        edittext[3] = (EditText) findViewById(R.id.edittext4);
        edittext[4] = (EditText) findViewById(R.id.edittext5);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add("0");
        adapter.add("1");
        adapter.add("2");
        adapter.add("3");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setSelection(level);

        final Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action)) {
            Sentences.init();
            Values.init(getApplicationContext());
            String uri = intent.getExtras().getCharSequence(Intent.EXTRA_TEXT).toString();
            Edit.title = "";
            Edit.level = 1;
            Edit.key = new String[0];
            Edit.link = "";
            Edit.description = "";
            Edit.text = "";
            Edit.index = -1;
            edittext[2].setText(uri);
        } else {
            edittext[2].setText(link);
        }
        edittext[0].setText(title);
        edittext[1].setText(getKey(key));
        edittext[3].setText(description);
        edittext[4].setText(text);
        for (EditText edit : edittext) {
            edit.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        }
        if (link.equals("/none")) {
            edittext[2].setText("");
        }
        if (text.equals("none")) {
            edittext[4].setText("");
        }
        if (index == -1)
            button.setText("作成");
        else
            button.setText("編集");
        button.setTextSize(24 * Main.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = edittext[0].getText().toString();
                key = setKey(edittext[1].getText().toString());
                link = edittext[2].getText().toString();
                description = edittext[3].getText().toString();
                text = edittext[4].getText().toString();
                if (title.equals("") || edittext[1].getText().toString().equals("") || description.equals("")) {
                    Toast.makeText(getApplicationContext(), "未入力の必須項目があります", Toast.LENGTH_SHORT).show();
                } else if (Sentences.serchTitle(title, Sentences.ConfigIndex, index)) {
                    Toast.makeText(getApplicationContext(), "タイトルが重複しています", Toast.LENGTH_SHORT).show();
                } else if (Sentences.serchKey(key, Sentences.ConfigIndex, index)) {
                    Toast.makeText(getApplicationContext(), "キーが重複しています", Toast.LENGTH_SHORT).show();
                } else {
                    if (link.equals("")) {
                        link = "none";
                    }
                    if (link.startsWith("http")) {
                    } else {
                        if (!link.startsWith("/")) {
                            link = "/" + link;
                        }
                    }
                    if (text.equals("")) {
                        text = "none";
                    }
                    if (index == -1) {
                        Sentences.sentences.get(Sentences.ConfigIndex).add(new Sentence(title, level, key, link, description, text));
                        Output.getOutput(Sentences.config.get(Sentences.ConfigIndex)).write(false, Sentences.ConfigIndex);
                        for (EditText edit : edittext) {
                            edit.setText("");
                        }
                        Sentences.init();
                    } else {
                        Sentences.sentences.get(Sentences.ConfigIndex).set(index, new Sentence(title, level, key, link, description, text));
                        Output.getOutput(Sentences.config.get(Sentences.ConfigIndex)).write(false, Sentences.ConfigIndex);
                        Sentences.init();
                        edittext[0].setText(title);
                        edittext[1].setText(getKey(key));
                        if (link.equals("/none")) {
                            edittext[2].setText("");
                        }
                        if (text.equals("none")) {
                            edittext[4].setText("");
                        }
                    }
                    if (index == -1)
                        Toast.makeText(getApplicationContext(), "データを作成しました", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "データを編集しました", Toast.LENGTH_SHORT).show();
                }
            }
        });
        for (int i = 0; i < 4; i++) {
            edittext[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {//フォーカスがあたっている
                        edittext[4].setVisibility(View.GONE);
                    }
                }
            });
        }
        edittext[4].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {//フォーカスがあたっている
                    findViewById(R.id.top).setVisibility(View.GONE);
                    edittext[0].setVisibility(View.GONE);
                    edittext[1].setVisibility(View.GONE);
                    edittext[2].setVisibility(View.GONE);
                    edittext[3].setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                }
            }
        });
        root.setKeyboardListener(new View_DetectableKeyboardEventLayout.KeyboardListener() {

            @Override
            public void onKeyboardShown() {
            }

            @Override
            public void onKeyboardHidden() {
                findViewById(R.id.top).setVisibility(View.VISIBLE);
                edittext[0].setVisibility(View.VISIBLE);
                edittext[1].setVisibility(View.VISIBLE);
                edittext[2].setVisibility(View.VISIBLE);
                edittext[3].setVisibility(View.VISIBLE);
                edittext[4].setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                findViewById(R.id.view).requestFocus();
            }
        });
    }

    public String getKey(String key[]) {
        if (key.length == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < key.length; i++) {
                if (i == key.length - 1) {
                    sb.append(key[i]);
                } else {
                    sb.append(key[i] + "%");
                }
            }
            return new String(sb);
        }
    }

    public String[] setKey(String key) {
        return key.split("$");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base, menu);
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
}

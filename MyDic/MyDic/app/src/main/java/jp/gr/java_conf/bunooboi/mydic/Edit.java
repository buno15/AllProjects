package jp.gr.java_conf.bunooboi.mydic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hiro on 2016/08/04.
 */
public class Edit extends AppCompatActivity {
    static String title;
    static String[] key;
    static String link;
    static String text;
    static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        final EditText edittext[] = new EditText[4];
        edittext[0] = (EditText) findViewById(R.id.edittext1);
        edittext[1] = (EditText) findViewById(R.id.edittext2);
        edittext[2] = (EditText) findViewById(R.id.edittext3);
        edittext[3] = (EditText) findViewById(R.id.edittext4);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action)) {
            Sentences.init();
            Values.init(getApplicationContext());
            String uri = intent.getExtras().getCharSequence(Intent.EXTRA_TEXT).toString();
            Edit.title = "";
            Edit.key = new String[0];
            Edit.link = "";
            Edit.text = "";
            Edit.index = -1;
            edittext[2].setText(uri);
        } else {
            edittext[2].setText(link);
        }
        edittext[0].setText(title);
        edittext[1].setText(getKey(key));
        edittext[3].setText(text);
        for (EditText edit : edittext) {
            edit.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        }
        if (link.equals("/none")) {
            edittext[2].setText("");
        }
        if (text.equals("none")) {
            edittext[3].setText("");
        }
        Button button = (Button) findViewById(R.id.button);
        if (index == -1)
            button.setText("作成");
        else
            button.setText("編集");
        button.setTextSize(20 * Main.getScaleSize(getApplicationContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = edittext[0].getText().toString();
                key = setKey(edittext[1].getText().toString());
                link = edittext[2].getText().toString();
                text = edittext[3].getText().toString();
                if (title.equals("") || edittext[1].getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "未入力の必須項目があります", Toast.LENGTH_SHORT).show();
                } else if (Sentences.serch(title, index)) {
                    Toast.makeText(getApplicationContext(), "タイトルが重複しています", Toast.LENGTH_SHORT).show();
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
                        Sentences.sentences.add(new Sentence(title, key, link, text));
                        Output.getOutput().write(false);
                        for (EditText edit : edittext) {
                            edit.setText("");
                        }
                        Sentences.init();
                    } else {
                        Sentences.sentences.set(index, new Sentence(title, key, link, text));
                        Output.getOutput().write(false);
                        Sentences.init();
                        edittext[0].setText(title);
                        edittext[1].setText(getKey(key));
                        if (link.equals("/none")) {
                            edittext[2].setText("");
                        }
                        if (text.equals("none")) {
                            edittext[3].setText("");
                        }
                    }
                    edittext[0].requestFocus();
                    if (index == -1)
                        Toast.makeText(getApplicationContext(), "データを作成しました", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "データを編集しました", Toast.LENGTH_SHORT).show();
                }
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
        inflater.inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                startActivity(new Intent(getApplicationContext(), List.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), List.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

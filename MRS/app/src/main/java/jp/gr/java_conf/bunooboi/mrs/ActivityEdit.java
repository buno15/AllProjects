package jp.gr.java_conf.bunooboi.mrs;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by bunooboi on 2016/10/27.
 */

public class ActivityEdit extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button1;
    Button button2;
    Button button3;

    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("データ追加");

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button1.setText("編集");
        button2.setText("今日");
        button3.setText("決定");

        editText.setTextSize(30 * DisplayManager.getScaleSize(getApplicationContext()));
        textView.setTextSize(30 * DisplayManager.getScaleSize(getApplicationContext()));
        button1.setTextSize(20 * DisplayManager.getScaleSize(getApplicationContext()));
        button2.setTextSize(20 * DisplayManager.getScaleSize(getApplicationContext()));
        button3.setTextSize(30 * DisplayManager.getScaleSize(getApplicationContext()));

        setText();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityEdit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        ActivityEdit.this.year = year;
                        ActivityEdit.this.month = monthOfYear + 1;
                        ActivityEdit.this.day = dayOfMonth;
                        setText();
                    }
                }, year, month - 1, day);
                datePickerDialog.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                setText();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Memory m = new Memory(editText.getText().toString(), year, month, day, new boolean[]{false,false, false, false});
                Memorys.memorys.add(m);
                Output.getInstance().write();
                Toast.makeText(getApplicationContext(), "データを追加しました", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ActivityMain.class));
                finish();
            }
        });
    }

    void setText() {
        textView.setText(year + "/" + month + "/" + day);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(ActivityEdit.this);
            alertDlg.setMessage("データの作成を中止しますか？");
            alertDlg.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), ActivityMain.class));
                    finish();
                }
            });
            alertDlg.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDlg.create().show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

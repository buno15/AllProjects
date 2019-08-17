package jp.gr.java_conf.bunooboi.rema;

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
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Add Data");

        editText = (EditText) findViewById(R.id.editText);
        button3 = (Button) findViewById(R.id.button3);
        button3.setText("Add");

        editText.setTextSize(20);
        button3.setTextSize(20);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m = editText.getText().toString();
                App.titles.add(m);
                Output.getInstance().write();
                Toast.makeText(getApplicationContext(), "Add Data", Toast.LENGTH_SHORT).show();
                stopService(new Intent(getApplicationContext(), MainService.class));
                startActivity(new Intent(getApplicationContext(), ActivityMain.class));
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(ActivityEdit.this);
            alertDlg.setMessage("Do you want to stop creating data?");
            alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), ActivityMain.class));
                    finish();
                }
            });
            alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDlg.create().show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

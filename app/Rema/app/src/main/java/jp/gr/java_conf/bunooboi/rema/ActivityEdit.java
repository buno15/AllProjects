package jp.gr.java_conf.bunooboi.rema;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by bunooboi on 2016/10/27.
 */

public class ActivityEdit extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Add Data");

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);
        button.setText("Add");

        editText1.setTextSize(20);
        editText2.setTextSize(20);
        button.setTextSize(20);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m = editText1.getText().toString();
                String n = editText2.getText().toString();
                App.titles.add(m);
                App.answers.add(n);
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

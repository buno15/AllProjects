package jp.gr.java_conf.bunooboi.warasibe2;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout buttonLayout;
    static TextView consoleText, consoleName;
    ImageView itemImage;
    static ImageView playImg;
    ImageView hp[] = new ImageView[4], stamina[] = new ImageView[4], powerImg, intelligenceImg;
    ImageView injury, fracture, sick;
    ImageView friendImg, soldierImg, horseImg, cartImg;

    TextView power, intelligence;
    TextView friend, soldier, horse, cart;
    static Button up, right, down, left;
    static ImageButton action;
    int margin;

    static Scene nowScene;
    static boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        playImg = findViewById(R.id.playImg);

        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);

        consoleText = (TextView) findViewById(R.id.console_text);
        consoleText.setTextSize(18 * DisplayManager.getScaleSize(getApplicationContext()));
        consoleText.setTextColor(Color.BLACK);

        consoleName = (TextView) findViewById(R.id.console_name);
        consoleName.setTextSize(18 * DisplayManager.getScaleSize(getApplicationContext()));
        consoleName.setTextColor(Color.BLACK);

        itemImage = (ImageView) findViewById(R.id.itemimg1);
        itemImage.setBackgroundResource(R.drawable.boss);

        hp[0] = (ImageView) findViewById(R.id.hp1);
        hp[0].setBackgroundResource(R.drawable.boss);

        hp[1] = (ImageView) findViewById(R.id.hp2);
        hp[1].setBackgroundResource(R.drawable.boss);

        hp[2] = (ImageView) findViewById(R.id.hp3);
        hp[2].setBackgroundResource(R.drawable.boss);

        hp[3] = (ImageView) findViewById(R.id.hp4);
        hp[3].setBackgroundResource(R.drawable.boss);

        stamina[0] = (ImageView) findViewById(R.id.stamina1);
        stamina[0].setBackgroundResource(R.drawable.boss);

        stamina[1] = (ImageView) findViewById(R.id.stamina2);
        stamina[1].setBackgroundResource(R.drawable.boss);

        stamina[2] = (ImageView) findViewById(R.id.stamina3);
        stamina[2].setBackgroundResource(R.drawable.boss);

        stamina[3] = (ImageView) findViewById(R.id.stamina4);
        stamina[3].setBackgroundResource(R.drawable.boss);

        powerImg = (ImageView) findViewById(R.id.powerimg);
        powerImg.setBackgroundResource(R.drawable.boss);

        power = (TextView) findViewById(R.id.power);
        power.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        power.setTextColor(Color.WHITE);
        power.setText("100");

        intelligence = (TextView) findViewById(R.id.intelligence);
        intelligence.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        intelligence.setTextColor(Color.WHITE);
        intelligence.setText("100");

        intelligenceImg = (ImageView) findViewById(R.id.intelligenceimg);
        intelligenceImg.setBackgroundResource(R.drawable.boss);

        injury = (ImageView) findViewById(R.id.injury);
        injury.setBackgroundResource(R.drawable.boss);

        fracture = (ImageView) findViewById(R.id.fracture);
        fracture.setBackgroundResource(R.drawable.boss);

        sick = (ImageView) findViewById(R.id.sick);
        sick.setBackgroundResource(R.drawable.boss);

        friendImg = (ImageView) findViewById(R.id.friendimg);
        friendImg.setBackgroundResource(R.drawable.boss);

        soldierImg = (ImageView) findViewById(R.id.soldierimg);
        soldierImg.setBackgroundResource(R.drawable.boss);

        horseImg = (ImageView) findViewById(R.id.horseimg);
        horseImg.setBackgroundResource(R.drawable.boss);

        cartImg = (ImageView) findViewById(R.id.cartimg);
        cartImg.setBackgroundResource(R.drawable.boss);

        friend = (TextView) findViewById(R.id.friend);
        friend.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        friend.setTextColor(Color.WHITE);
        friend.setText("100");

        soldier = (TextView) findViewById(R.id.soldier);
        soldier.setTextSize(18 * DisplayManager.getScaleSize(getApplicationContext()));
        soldier.setTextColor(Color.WHITE);
        soldier.setText("100000");

        horse = (TextView) findViewById(R.id.horse);
        horse.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        horse.setTextColor(Color.WHITE);
        horse.setText("100");

        cart = (TextView) findViewById(R.id.cart);
        cart.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        cart.setTextColor(Color.WHITE);
        cart.setText("100");

        up = (Button) findViewById(R.id.up);
        up.setTextSize(18 * DisplayManager.getScaleSize(getApplicationContext()));
        down = (Button) findViewById(R.id.down);
        down.setTextSize(18 * DisplayManager.getScaleSize(getApplicationContext()));
        left = (Button) findViewById(R.id.left);
        left.setTextSize(18 * DisplayManager.getScaleSize(getApplicationContext()));
        right = (Button) findViewById(R.id.right);
        right.setTextSize(18 * DisplayManager.getScaleSize(getApplicationContext()));
        action = (ImageButton) findViewById(R.id.action);

        int marginmoto = 5;
        float scale = getResources().getDisplayMetrics().density;
        margin = (int) (marginmoto * scale + 0.5f);


        Init init = new Init();
        init.connectScene();


        nowScene = init.meziha[1];
        setNowScene(nowScene);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowScene = nowScene.getUp();
                setNowScene(nowScene);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowScene = nowScene.getDown();
                setNowScene(nowScene);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowScene = nowScene.getLeft();
                setNowScene(nowScene);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowScene = nowScene.getRight();
                setNowScene(nowScene);
            }
        });
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowScene = nowScene.getAction();
            }
        });
    }

    static void setNowScene(Scene s) {
        s.getEffect();
        if (!flag) {
            setText(s);
            setConsole(s);
            setPlayImg(s);
            setButton(s);
        }
        flag = false;
    }

    static void setText(Scene s) {
        up.setText(s.getUpStr());
        down.setText(s.getDownStr());
        left.setText(s.getLeftStr());
        right.setText(s.getRightStr());

    }

    static void setConsole(Scene s) {
        consoleText.setText(s.getConsoleText());
        consoleName.setText(s.getConsoleName());
    }

    static void setPlayImg(Scene s) {
        playImg.setBackgroundResource(s.getPlayImg());
    }

    static void setButton(Scene s) {
        if (s.getUp() == null) {
            up.setEnabled(false);
        } else {
            up.setEnabled(true);
        }
        if (s.getDown() == null) {
            down.setEnabled(false);
        } else {
            down.setEnabled(true);
        }
        if (s.getLeft() == null) {
            left.setEnabled(false);
        } else {
            left.setEnabled(true);
        }
        if (s.getRight() == null) {
            right.setEnabled(false);
        } else {
            right.setEnabled(true);
        }
        if (s.getAction() == null) {
            action.setEnabled(false);
        } else {
            action.setEnabled(true);
        }
    }
}

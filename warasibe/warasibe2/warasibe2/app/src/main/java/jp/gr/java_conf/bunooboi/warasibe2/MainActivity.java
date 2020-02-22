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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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
    static Scene predScene;
    static boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);


        playImg = findViewById(R.id.playImg);

        consoleText = (TextView) findViewById(R.id.console_text);
        consoleText.setTextSize(20 * DisplayManager.getScaleSize(getApplicationContext()));
        consoleText.setTextColor(Color.BLACK);

        consoleName = (TextView) findViewById(R.id.console_name);
        consoleName.setTextSize(20 * DisplayManager.getScaleSize(getApplicationContext()));
        consoleName.setTextColor(Color.BLACK);

        itemImage = (ImageView) findViewById(R.id.itemimg1);
        itemImage.setBackgroundResource(R.drawable.boss);

        hp[0] = (ImageView) findViewById(R.id.hp1);

        hp[1] = (ImageView) findViewById(R.id.hp2);

        hp[2] = (ImageView) findViewById(R.id.hp3);

        hp[3] = (ImageView) findViewById(R.id.hp4);

        stamina[0] = (ImageView) findViewById(R.id.stamina1);
        stamina[0].setBackgroundResource(R.drawable.boss);

        stamina[1] = (ImageView) findViewById(R.id.stamina2);
        stamina[1].setBackgroundResource(R.drawable.boss);

        stamina[2] = (ImageView) findViewById(R.id.stamina3);
        stamina[2].setBackgroundResource(R.drawable.boss);

        stamina[3] = (ImageView) findViewById(R.id.stamina4);
        stamina[3].setBackgroundResource(R.drawable.boss);

        power = (TextView) findViewById(R.id.power);
        power.setTextSize(34 * DisplayManager.getScaleSize(getApplicationContext()));
        power.setTextColor(Color.WHITE);
        power.setText("100");
        power.setTextColor(Color.parseColor("#fa8072"));
        power.setShadowLayer(10, 0, 0, Color.BLACK);

        intelligence = (TextView) findViewById(R.id.intelligence);
        intelligence.setTextSize(34 * DisplayManager.getScaleSize(getApplicationContext()));
        intelligence.setTextColor(Color.parseColor("#00bfff"));
        intelligence.setShadowLayer(10, 0, 0, Color.BLACK);
        intelligence.setText("100");


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
        up.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        down = (Button) findViewById(R.id.down);
        down.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        left = (Button) findViewById(R.id.left);
        left.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        right = (Button) findViewById(R.id.right);
        right.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        action = (ImageButton) findViewById(R.id.action);

        int marginmoto = 5;
        float scale = getResources().getDisplayMetrics().density;
        margin = (int) (marginmoto * scale + 0.5f);


        System init = new System();


        nowScene = init.meziha[1];
        setNowScene(nowScene);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(Scene.UP);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(Scene.DOWN);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(Scene.LEFT);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(Scene.RIGHT);
            }
        });
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(Scene.ACTION);
            }
        });

    }

    static void next(int dir) {
        predScene = nowScene;
        nowScene = nowScene.finish(dir);
        nowScene.start(predScene, dir);
        setNowScene(nowScene);
    }

    static void setNowScene(Scene s) {
        if (!flag) {
            setText(s);
            setConsole(s);
            setPlayImg(s);
            setButton(s);
        }
        flag = false;
    }

    static void setText(Scene s) {
        up.setText(s.getUp());
        down.setText(s.getDown());
        left.setText(s.getLeft());
        right.setText(s.getRight());
    }

    static void setConsole(Scene s) {
        consoleText.setText(s.getConsoleText());
        consoleName.setText(s.getConsoleName());
    }

    static void setPlayImg(Scene s) {
        playImg.setBackgroundResource(s.getPlayImg());
    }

    static void setButtonUnable() {
        up.setEnabled(false);
        down.setEnabled(false);
        left.setEnabled(false);
        right.setEnabled(false);
        action.setEnabled(false);
        action.setImageResource(R.drawable.action1);

    }

    static void setButton(Scene s) {
        if (s.isNext(Scene.UP)) {
            up.setEnabled(true);
        } else {
            up.setEnabled(false);
        }
        if (s.isNext(Scene.DOWN)) {
            down.setEnabled(true);
        } else {
            down.setEnabled(false);
        }
        if (s.isNext(Scene.LEFT)) {
            left.setEnabled(true);
        } else {
            left.setEnabled(false);
        }
        if (s.isNext(Scene.RIGHT)) {
            right.setEnabled(true);
        } else {
            right.setEnabled(false);
        }
        if (s.isNext(Scene.ACTION)) {
            action.setEnabled(true);
            action.setImageResource(R.drawable.action2);
        } else {
            action.setEnabled(false);
            action.setImageResource(R.drawable.action1);
        }
    }
}

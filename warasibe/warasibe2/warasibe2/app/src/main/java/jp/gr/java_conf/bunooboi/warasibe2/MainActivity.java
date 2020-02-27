package jp.gr.java_conf.bunooboi.warasibe2;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
    static ImageView hp[] = new ImageView[4], stamina[] = new ImageView[4], powerImg, intelligenceImg;
    ImageView injury, fracture, sick;
    ImageView friendImg, soldierImg, horseImg, cartImg;

    static TextView date, time, weather;
    static TextView power, intelligence;
    static TextView friend, soldier, horse, cart;
    static Button up, right, down, left;
    static ImageButton action;
    int margin;

    static Scene nowScene;
    static Scene predScene;

    static Main main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);

        main = new Main();

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
        stamina[1] = (ImageView) findViewById(R.id.stamina2);
        stamina[2] = (ImageView) findViewById(R.id.stamina3);
        stamina[3] = (ImageView) findViewById(R.id.stamina4);

        date = findViewById(R.id.date);
        date.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        date.setTextColor(Color.WHITE);
        date.setText(main.date[0] + "年 " + main.date[1] + "/" + main.date[2]);
        time = findViewById(R.id.time);
        time.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        time.setTextColor(Color.WHITE);
        time.setText("10:27");
        weather = findViewById(R.id.weather);
        weather.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        weather.setTextColor(Color.WHITE);
        weather.setText("晴れ");

        power = (TextView) findViewById(R.id.power);
        power.setTextSize(34 * DisplayManager.getScaleSize(getApplicationContext()));
        power.setTextColor(Color.WHITE);
        power.setTextColor(Color.parseColor("#fa8072"));
        power.setShadowLayer(10, 0, 0, Color.BLACK);

        intelligence = (TextView) findViewById(R.id.intelligence);
        intelligence.setTextSize(34 * DisplayManager.getScaleSize(getApplicationContext()));
        intelligence.setTextColor(Color.parseColor("#00bfff"));
        intelligence.setShadowLayer(10, 0, 0, Color.BLACK);


        injury = (ImageView) findViewById(R.id.injury);
        fracture = (ImageView) findViewById(R.id.fracture);
        sick = (ImageView) findViewById(R.id.sick);

        friendImg = (ImageView) findViewById(R.id.friendimg);
        soldierImg = (ImageView) findViewById(R.id.soldierimg);
        horseImg = (ImageView) findViewById(R.id.horseimg);
        cartImg = (ImageView) findViewById(R.id.cartimg);

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


        nowScene = main.meziha[3];
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
        nowScene.finish(dir);
        nowScene = nowScene.next(dir);
        nowScene.start(predScene, dir);
        setNowScene(nowScene);
    }

    static void setNowScene(Scene s) {
        updateStatus(s);
        setText(s);
        setConsole(s);
        setPlayImg(s);
        setButton(s);
    }

    static void updateStatus(Scene s) {
        System.out.println(s.getConsoleText() + " " + I.HP + " " + I.Stamina);
        power.setText(String.valueOf(I.Power));
        intelligence.setText(String.valueOf(I.Intelligence));
        if (s.isChangeStatus()) {
            if (main.time[0] == 23 && main.time[1] == 3) {
                main.time[0] = 0;
                main.time[1] = 0;
                main.date[2]++;
                if (main.date[2] == 32) {
                    main.date[2] = 1;
                    main.date[1]++;
                    if (main.date[1] == 13) {
                        main.date[1] = 1;
                        main.date[0]++;
                    }
                }
                I.Stamina--;
                if (I.Stamina <= 0) {
                    I.HP--;
                    if (I.HP <= -1) {
                        dead();
                    } else {
                        I.Stamina = 20;
                    }
                }
            } else {
                main.time[1] += 3;
                if (main.time[1] == 6) {
                    main.time[0]++;
                    main.time[1] = 0;
                    I.Stamina--;
                    if (I.Stamina <= 0) {
                        I.HP--;
                        if (I.HP <= -1) {
                            dead();
                        } else {
                            I.Stamina = 20;
                        }
                    }
                }
            }
            date.setText(main.date[0] + "年 " + main.date[1] + "/" + main.date[2]);
            time.setText(main.time[0] + ":" + main.time[1] + "0");
            setStamina(I.Stamina);
            setHP(I.HP);
        }

    }

    static void dead() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setButtonUnable();
                up.setText("");
                down.setText("");
                left.setText("");
                right.setText("");

                consoleText.setText("あなたは死んだ");
                consoleName.setText("");
                setStamina(-1);
                setHP(-1);
            }
        });
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

    static void setPlayImg(int i) {
        playImg.setBackgroundResource(i);
    }

    static void setButtonUnable() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                up.setEnabled(false);
                down.setEnabled(false);
                left.setEnabled(false);
                right.setEnabled(false);
                action.setEnabled(false);
                action.setImageResource(R.drawable.action1);

            }
        });

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

    static void setHP(final int now) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                switch (now) {
                    case -1:
                    case 0:
                        hp[0].setImageResource(R.drawable.hp2);
                        hp[1].setImageResource(R.drawable.hp2);
                        hp[2].setImageResource(R.drawable.hp2);
                        hp[3].setImageResource(R.drawable.hp2);
                        break;
                    case 1:
                        hp[0].setImageResource(R.drawable.hp1);
                        hp[1].setImageResource(R.drawable.hp2);
                        hp[2].setImageResource(R.drawable.hp2);
                        hp[3].setImageResource(R.drawable.hp2);
                        break;
                    case 2:
                        hp[0].setImageResource(R.drawable.hp1);
                        hp[1].setImageResource(R.drawable.hp1);
                        hp[2].setImageResource(R.drawable.hp2);
                        hp[3].setImageResource(R.drawable.hp2);
                        break;
                    case 3:
                        hp[0].setImageResource(R.drawable.hp1);
                        hp[1].setImageResource(R.drawable.hp1);
                        hp[2].setImageResource(R.drawable.hp1);
                        hp[3].setImageResource(R.drawable.hp2);
                        break;
                    case 4:
                        hp[0].setImageResource(R.drawable.hp1);
                        hp[1].setImageResource(R.drawable.hp1);
                        hp[2].setImageResource(R.drawable.hp1);
                        hp[3].setImageResource(R.drawable.hp1);
                        break;

                }
            }
        });
    }

    static void setStamina(final int now) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                switch (now) {
                    case -1:
                    case 0:
                        stamina[3].setImageResource(R.drawable.stamina_no_4);
                        stamina[2].setImageResource(R.drawable.stamina_no_3);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 1:
                        stamina[3].setImageResource(R.drawable.stamina_1);
                        stamina[2].setImageResource(R.drawable.stamina_no_3);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 2:
                        stamina[3].setImageResource(R.drawable.stamina_2);
                        stamina[2].setImageResource(R.drawable.stamina_no_3);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 3:
                        stamina[3].setImageResource(R.drawable.stamina_3);
                        stamina[2].setImageResource(R.drawable.stamina_no_3);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 4:
                        stamina[3].setImageResource(R.drawable.stamina_4);
                        stamina[2].setImageResource(R.drawable.stamina_no_3);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 5:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_no_3);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 6:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_6);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 7:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_7);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 8:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_8);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 9:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_9);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 10:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_no_2);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 11:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_11);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 12:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_12);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 13:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_13);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 14:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_14);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 15:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_15);
                        stamina[0].setImageResource(R.drawable.stamina_no_1);
                        break;
                    case 16:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_15);
                        stamina[0].setImageResource(R.drawable.stamina_16);
                        break;
                    case 17:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_15);
                        stamina[0].setImageResource(R.drawable.stamina_17);
                        break;
                    case 18:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_15);
                        stamina[0].setImageResource(R.drawable.stamina_18);
                        break;
                    case 19:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_15);
                        stamina[0].setImageResource(R.drawable.stamina_19);
                        break;
                    case 20:
                        stamina[3].setImageResource(R.drawable.stamina_5);
                        stamina[2].setImageResource(R.drawable.stamina_10);
                        stamina[1].setImageResource(R.drawable.stamina_15);
                        stamina[0].setImageResource(R.drawable.stamina_20);
                        break;
                }
            }
        });

    }
}

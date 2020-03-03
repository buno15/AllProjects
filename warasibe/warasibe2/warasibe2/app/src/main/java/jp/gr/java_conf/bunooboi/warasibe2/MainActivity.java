package jp.gr.java_conf.bunooboi.warasibe2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static TextView consoleText, consoleName;
    static ImageView itemButton1, itemButton2;
    static ImageView playImg;
    static ImageView stamina[] = new ImageView[4];
    ImageView injury, fracture, sick;
    ImageView friendImg, soldierImg, horseImg, cartImg;

    static TextView karma;

    static TextView date, time, weather;
    static TextView power, defense, intelligence, hp;
    static TextView friend, soldier;
    static Button up, right, down, left;
    static ImageButton action;
    int margin;

    static Scene nowScene;
    static Scene predScene;

    static AlertDialog dialog;
    static ListView listView;

    static Main main;

    static Item predItem1;
    static Item predItem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);

        main = new Main(this);

        playImg = findViewById(R.id.playImg);

        consoleText = (TextView) findViewById(R.id.console_text);
        consoleText.setTextSize(20 * DisplayManager.getScaleSize(getApplicationContext()));
        consoleText.setTextColor(Color.BLACK);

        consoleName = (TextView) findViewById(R.id.console_name);
        consoleName.setTextSize(20 * DisplayManager.getScaleSize(getApplicationContext()));
        consoleName.setTextColor(Color.BLACK);

        itemButton1 = findViewById(R.id.item1);
        itemButton2 = findViewById(R.id.item2);


        stamina[0] = (ImageView) findViewById(R.id.stamina1);
        stamina[1] = (ImageView) findViewById(R.id.stamina2);
        stamina[2] = (ImageView) findViewById(R.id.stamina3);
        stamina[3] = (ImageView) findViewById(R.id.stamina4);

        date = findViewById(R.id.date);
        date.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        date.setTextColor(Color.WHITE);
        date.setText(main.year + "年 " + main.month + "/" + main.day);
        time = findViewById(R.id.time);
        time.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        time.setTextColor(Color.WHITE);
        time.setText("10:27");
        weather = findViewById(R.id.weather);
        weather.setTextSize(24 * DisplayManager.getScaleSize(getApplicationContext()));
        weather.setTextColor(Color.WHITE);
        weather.setText("晴れ");

        hp = (TextView) findViewById(R.id.hp);
        hp.setTextSize(34 * DisplayManager.getScaleSize(getApplicationContext()));
        hp.setTextColor(Color.parseColor("#00ff00"));
        hp.setShadowLayer(10, 0, 0, Color.BLACK);

        power = (TextView) findViewById(R.id.power);
        power.setTextSize(34 * DisplayManager.getScaleSize(getApplicationContext()));
        power.setTextColor(Color.WHITE);
        power.setTextColor(Color.parseColor("#fa8072"));
        power.setShadowLayer(10, 0, 0, Color.BLACK);


        defense = (TextView) findViewById(R.id.defense);
        defense.setTextSize(34 * DisplayManager.getScaleSize(getApplicationContext()));
        defense.setTextColor(Color.WHITE);
        defense.setTextColor(Color.parseColor("#cd853f"));
        defense.setShadowLayer(10, 0, 0, Color.BLACK);

        karma = (TextView) findViewById(R.id.karma);
        karma.setTextSize(34 * DisplayManager.getScaleSize(getApplicationContext()));
        karma.setTextColor(Color.WHITE);
        karma.setTextColor(Color.parseColor("#da70d6"));
        karma.setShadowLayer(10, 0, 0, Color.BLACK);


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

        itemButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (I.getItemSize() != 0) {
                    setDialog(MainActivity.this, "何を持ちますか？");
                    changeMyItem();
                }
            }
        });
        itemButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (I.getItemSize() != 0) {
                    setDialog(MainActivity.this, "何を持ちますか？");
                    changeMyItem();
                }
            }
        });

        updateHealth(0);
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
        setItemButtonUnable(false);
        if (s.isChangeTime()) {
            updateTime(Calendar.MINUTE, 30);
            setItemButtonUnable(true);
        }
        if (I.getItemSize() == 0) {
            I.dropItem(predItem1);
            I.dropItem(predItem2);
            predItem1 = new Item("", 0, 0, 0, 0);
            predItem2 = new Item("", 0, 0, 0, 0);
        }
        if (I.getItemSize() >= 1) {
            itemButton1.setBackgroundResource(I.getItem(I.ITEM1).getImg());
            if (!predItem1.equals(I.getItem(I.ITEM1))) {
                I.dropItem(predItem1);
                I.haveItem(I.getItem(I.ITEM1));
                predItem1 = I.getItem(I.ITEM1);
            }
        } else {
            itemButton1.setBackgroundResource(R.drawable.action0);
        }
        if (I.getItemSize() >= 2) {
            itemButton2.setBackgroundResource(I.getItem(I.ITEM2).getImg());
            if (!predItem2.equals(I.getItem(I.ITEM2))) {
                I.dropItem(predItem2);
                I.haveItem(I.getItem(I.ITEM2));
                predItem2 = I.getItem(I.ITEM2);
            }
        } else {
            itemButton2.setBackgroundResource(R.drawable.action0);
        }
        power.setText(String.valueOf(I.Power));
        defense.setText(String.valueOf(I.Defense));
        intelligence.setText(String.valueOf(I.Intelligence));
        karma.setText(String.valueOf(I.Karma));
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
                setStamina(0);
                setHP(0);
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
                action.setImageResource(R.drawable.action0);
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
            action.setImageResource(R.drawable.action1);
        } else {
            action.setEnabled(false);
            action.setImageResource(R.drawable.action0);
        }
    }

    static void setActionButton(final boolean on) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (on) {
                    action.setEnabled(true);
                    action.setImageResource(R.drawable.action2);
                } else {
                    action.setEnabled(true);
                    action.setImageResource(R.drawable.action1);
                }
            }
        });

    }

    static void setItemButtonUnable(final boolean on) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (on) {
                    itemButton1.setEnabled(true);
                    itemButton2.setEnabled(true);
                } else {
                    itemButton1.setEnabled(false);
                    itemButton2.setEnabled(false);
                }
            }
        });
    }

    static void setDialog(Context context, String title) {                                    //setOnDismissListenerでSceneごとに処理を決める
        CustomAdapter adapter = new CustomAdapter(context, 0, I.getItem());
        listView = new ListView(context);
        listView.setAdapter(adapter);
        listView.setVerticalScrollBarEnabled(false);

        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextSize(24 * DisplayManager.getScaleSize(context));
        titleView.setTextColor(Color.WHITE);
        titleView.setBackgroundColor(Color.BLACK);
        titleView.setPadding(20, 20, 20, 20);
        titleView.setGravity(Gravity.CENTER);

        MainActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.IHave = I.getItem().get(position);
                MainActivity.dialog.dismiss();
            }
        });

        dialog = new AlertDialog.Builder(context)
                .setCustomTitle(titleView)
                .setView(listView).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    static void changeMyItem() {                                                                    //手持ちの変更
        MainActivity.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Item item = main.IHave;
                I.removeItem(main.IHave);
                I.addItemFirst(item);

                if (I.getItemSize() >= 1) {
                    itemButton1.setBackgroundResource(I.getItem(I.ITEM1).getImg());
                } else {
                    itemButton1.setBackgroundResource(R.drawable.action0);
                }
                if (I.getItemSize() >= 2) {
                    itemButton2.setBackgroundResource(I.getItem(I.ITEM2).getImg());
                } else {
                    itemButton2.setBackgroundResource(R.drawable.action0);
                }
            }
        });
    }

    static void updateHealth(int changeStamina) {
        I.Stamina += changeStamina;
        if (I.Stamina > 20) {
            I.Stamina = I.Stamina - 20;
            I.HP += 10;
            if (I.HP > I.maxHP) {
                I.HP = I.maxHP;
            }
        } else if (I.Stamina <= 0) {
            I.HP /= 2;
            if (I.HP <= 0) {
                dead();
            } else {
                I.Stamina = 20;
            }
        }
        MainActivity.setStamina(I.Stamina);
        MainActivity.setHP(I.HP);
    }

    static void updateTime(int field, int add) {
        main.calendar.add(field, add);
        main.year = main.calendar.get(Calendar.YEAR);
        main.month = main.calendar.get(Calendar.MONTH) + 1;
        main.day = main.calendar.get(Calendar.DAY_OF_MONTH);
        main.hour = main.calendar.get(Calendar.HOUR_OF_DAY);
        main.minute = main.calendar.get(Calendar.MINUTE);

        if (field == Calendar.MINUTE && main.minute == 0) {
            updateHealth(-1);
        }
        setDateText();
    }

    static void setDateText() {
        date.setText(main.year + "年 " + main.month + "/" + main.day);
        if (main.minute == 0) {
            time.setText(main.hour + ":" + main.minute + "0");
        } else {
            time.setText(main.hour + ":" + main.minute);
        }

    }

    static void setHP(final int now) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                hp.setText(String.valueOf(I.HP));
                if (I.HP == I.maxHP) {
                    hp.setTextColor(Color.parseColor("#ffff00"));
                } else {
                    hp.setTextColor(Color.parseColor("#00ff00"));
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

    static class CustomAdapter extends ArrayAdapter<Item> {
        private LayoutInflater inflater;
        Context context;

        public CustomAdapter(Context context, int resource, ArrayList<Item> objects) {
            super(context, resource, objects);
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            Item item = (Item) getItem(position);
            if (null == v) v = inflater.inflate(R.layout.custom_listview, null);

            ImageView imageView = v.findViewById(R.id.img);
            imageView.setImageResource(item.getImg());

            TextView textView = (TextView) v.findViewById(R.id.name);
            textView.setText(item.getName());
            textView.setTextSize(30 * DisplayManager.getScaleSize(context));
            textView.setTextColor(Color.parseColor("#000000"));
            return v;
        }
    }
}

package jp.gr.java_conf.bunooboi.warasibe2;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static LinearLayout playbg, buttonLayout;
    public static TextView console;
    public static ImageView itemImage;
    ImageView hp, stamina, powerImg, intelligenceImg;
    ImageView injury, fracture, sick;
    ImageView friendImg, soldierImg, horseImg, cartImg;

    TextView power, intelligence;
    TextView friend, soldier, horse, cart;
    public static Button button1, button2;
    int enemyhp, enemypower, enemydamage = 0;
    int random1, random2, random3;
    int rn, margin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        playbg = (LinearLayout) findViewById(R.id.playbg);
        playbg.setBackgroundResource(R.drawable.main);
        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);

        console = (TextView) findViewById(R.id.console);
        console.setTextSize(8 * DisplayManager.getScaleSize(getApplicationContext()));
        console.setTextColor(Color.BLACK);

        itemImage = (ImageView) findViewById(R.id.itemImage1);
        itemImage.setBackgroundResource(R.drawable.boss);

        hp = (ImageView) findViewById(R.id.hp);
        hp.setBackgroundResource(R.drawable.boss);

        stamina = (ImageView) findViewById(R.id.stamina);
        stamina.setBackgroundResource(R.drawable.boss);

        powerImg = (ImageView) findViewById(R.id.powerimg);
        powerImg.setBackgroundResource(R.drawable.boss);

        power = (TextView) findViewById(R.id.power);
        power.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        power.setTextColor(Color.WHITE);
        power.setText("100");

        intelligence = (TextView) findViewById(R.id.intelligence);
        intelligence.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
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
        friend.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        friend.setTextColor(Color.WHITE);
        friend.setText("100");

        soldier = (TextView) findViewById(R.id.soldier);
        soldier.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        soldier.setTextColor(Color.WHITE);
        soldier.setText("100000");

        horse = (TextView) findViewById(R.id.horse);
        horse.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        horse.setTextColor(Color.WHITE);
        horse.setText("100");

        cart = (TextView) findViewById(R.id.cart);
        cart.setTextSize(22 * DisplayManager.getScaleSize(getApplicationContext()));
        cart.setTextColor(Color.WHITE);
        cart.setText("100");

        button1 = (Button) findViewById(R.id.button1);
        button1.setTextSize(8 * DisplayManager.getScaleSize(getApplicationContext()));
        button2 = (Button) findViewById(R.id.button2);
        button2.setTextSize(8 * DisplayManager.getScaleSize(getApplicationContext()));

        int marginmoto = 5;
        float scale = getResources().getDisplayMetrics().density;
        margin = (int) (marginmoto * scale + 0.5f);
    }

    public void buttonoff() {
        button1.setText("");
        button2.setText("");
        button1.setEnabled(false);
        button2.setEnabled(false);
    }
}

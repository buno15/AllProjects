package jp.gr.java_conf.bunooboi.bmf;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Timer;
import java.util.TimerTask;

import jp.gr.java_conf.bunooboi.gams.Manager;


public class Start extends Activity {
    private InterstitialAd interstitial;//広告

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.start);

        // インタースティシャルを作成する。
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-2096872993008006/3833916976");
        // 広告リクエストを作成する。
        AdRequest adRequest = new AdRequest.Builder().build();
        // インタースティシャルの読み込みを開始する。
        interstitial.loadAd(adRequest);


        Manager.getLibrary();

        Timer t = new Timer();
        t.schedule(new EndTimer(), 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        displayInterstitial();
    }


    // インタースティシャルを表示する準備ができたら、displayInterstitial() を呼び出す。
    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }


    class EndTimer extends TimerTask {
        Handler handler = new Handler();

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), Config.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                    overridePendingTransition(0, 0);
                }
            });
        }
    }
}

package jp.gr.java_conf.koni.STOPsumaho;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Terms extends Activity {
	Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation((ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
		setContentView(R.layout.terms);
		setTitle("利用規約");
		
		TextView textview1 = (TextView) findViewById(R.id.textview1);
		textview1.setTextSize(8 * Main.setScaleSize(getApplicationContext()));
		textview1.setText("利用規約\n" + "\n" + "\n"
				+ "この規約は(以下「本規約」という)、お客様にbunooboi(以下「bunooboi」という)が提供する「STOPスマホ」(以下「本アプリ」という)をご利用頂く際の取扱いにつき定めるものです。本規約に同意した上で本アプリをダウンロード、インストール又は利用してください。本規約にご同意いただけない場合、本アプリを利用することはできませんので、その場合は直ちに本アプリのダウンロード、インストール又は利用を中止してください。本アプリをダウンロード、インストール又は利用する場合、本規約に同意したものとみなします。\n"
				+ "\n1.サービス概要\n" + "本アプリは、お客様のAndroid搭載端末にダウンロードされたアプリ「STOPスマホ」を使用して、bunooboiのサービスをご利用いただくものです。\n" + "\n2.本アプリを利用できる対象機種・対象OSは、bunooboiが所定のものに限られます。\n" + "\n3.知的財産権\n"
				+ "本アプリで提供される情報（以下「情報等」という）及び本アプリの著作権等の知的財産権は、bunooboiまたは正当な権利を有する第三者に帰属します。お客様は、本アプリの利用に限り本アプリを利用頂けます。また、お客様は本アプリ及び情報等の情報の転載・複製・改変、および本アプリのリコンパイル・リバースエンジニアリングならびにこれに類する行為を行うことはできません。\n"
				+ "\n4.不保証\n" + "bunooboiが提供する情報等については、その内容を保証するものではありません。提供される情報に基づいてお客様が不利益を被った場合であっても、bunooboiおよび情報提供元は一切責任を負いません。\n" + "\n5.免責事項\n"
				+ "本アプリのご利用に関して、お客様の損害について、bunooboiは一切の責任を負わないものとします。\n" + "(a)本アプリの不具合(表示情報の誤謬・逸脱、取引依頼の不能等)による損害\n" + "(b)本アプリがお客様の携帯電話機器に与える影響・損害\n"
				+ "(c)お客様が本アプリを正常に利用できないことにより生じた損害\n" + "(d)通信回線及びシステム機器等の瑕疵または障害(天災地変等不可抗力によるものを含みます)、コンピュータウィルスや第三者からなされる請求により生じた損害\n" + "(e)bunooboiが、他に定める約款、取引説明書等にに免責事項として定める損害\n"
				+ "(f)その他、一切の損害\n" + "お客様は、本規約に定めるところに違反した場合、bunooboiは直ちにお客様の本アプリ利用を停止できるものとします。\n" + "\n6.規約変更\n"
				+ "bunooboiは、お客様の承諾およびお客様への通知なしに、いつでも本アプリの中止・内容変更、本アプリのバージョンアップ、本規約の変更を行うことができるものとします。\n" + "\n7.準拠法及び裁判管轄\n"
				+ "本アプリ利用に関し紛争が生じた場合には、東京地方裁判所を第一審の専属的合意管轄裁判所とします。また、本規約は日本国法に準じて解釈されます。\n" + "以上\n\n" + "平成27年11月20日制定\n");
		
		Button button1 = (Button) findViewById(R.id.button1);// 戻る
		button1.setTextSize(16 * Main.setScaleSize(getApplicationContext()));
		button1.setText("戻る");
		button1.setTextColor(Color.WHITE);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Help.class));
				finish();
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(getApplicationContext(), Main.class));
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}

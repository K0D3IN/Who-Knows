package com.czal.millionare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PuanActivity extends Activity {
	Boolean durum = false;
	TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10,
			txt11, txt12;
	Button btnSonraki,btnCekil;
	int gelenSoruSayisi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.puantablosu);
		txt1 = (TextView) findViewById(R.id.textView1);
		txt2 = (TextView) findViewById(R.id.textView2);
		txt3 = (TextView) findViewById(R.id.textView3);
		txt4 = (TextView) findViewById(R.id.textView4);
		txt5 = (TextView) findViewById(R.id.textView5);
		txt6 = (TextView) findViewById(R.id.textView6);
		txt7 = (TextView) findViewById(R.id.textView7);
		txt8 = (TextView) findViewById(R.id.textView8);
		txt9 = (TextView) findViewById(R.id.textView9);
		txt10 = (TextView) findViewById(R.id.textView10);
		txt11 = (TextView) findViewById(R.id.textView11);
		txt12 = (TextView) findViewById(R.id.textView12);
		btnSonraki = (Button) findViewById(R.id.btnSonraki);
		btnCekil = (Button)findViewById(R.id.btnCekil);

		
		gelenSoruSayisi = getIntent().getExtras().getInt("SoruSayisi");
		durumGoster(gelenSoruSayisi);
		//arkaPlaniDegistir(gelenSoruSayisi);

		btnSonraki.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				durum = true;
				final Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				intent.putExtra("Durum", durum);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		btnCekil.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PuanActivity.this,FinalEkranActivity.class);
				intent.putExtra("soruSirasi", gelenSoruSayisi);
				intent.putExtra("tur", 1);
				startActivity(intent);
				
			}
		});
	}
	
	private void durumGoster(int sayi)
	{
		if(sayi == 0)
		{
			txt12.setBackgroundColor(Color.RED);
		}
		if(sayi == 1)
		{
			txt11.setBackgroundColor(Color.RED);
		}
		if(sayi == 2)
		{
			txt10.setBackgroundColor(Color.RED);
		}
		if(sayi == 3)
		{
			txt9.setBackgroundColor(Color.RED);
		}
		if(sayi == 4)
		{
			txt8.setBackgroundColor(Color.RED);
		}
		if(sayi == 5)
		{
			txt7.setBackgroundColor(Color.RED);
		}
		if(sayi == 6)
		{
			txt6.setBackgroundColor(Color.RED);
		}
		if(sayi == 7)
		{
			txt5.setBackgroundColor(Color.RED);
		}
		if(sayi == 8)
		{
			txt4.setBackgroundColor(Color.RED);
		}
		if(sayi == 9)
		{
			txt3.setBackgroundColor(Color.RED);
		}
		if(sayi == 10)
		{
			txt2.setBackgroundColor(Color.RED);
		}
		if(sayi == 11)
		{
			txt1.setBackgroundColor(Color.RED);
		}
	}

	
	
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

	if (keyCode == KeyEvent.KEYCODE_BACK) {
		OyundanCikmeIstegi();
	}
	return super.onKeyDown(keyCode, event);
	}*/
	
	//@Override 
	/*protected void onPause() {
		media.release();
		super.onPause();
		finish();
	}*/
}
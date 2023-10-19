package com.czal.millionare;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FinalEkranActivity extends Activity {

	TextView txt1, txt2, txt3;
	Button FinalEkranYeniOyun, FinalEkranCikis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.final_ekran);
		txt1 = (TextView) findViewById(R.id.FinalEkranTxt1);
		txt2 = (TextView) findViewById(R.id.FinalEkranTxt2);
		txt3 = (TextView) findViewById(R.id.FinalEkranTxt3);
		FinalEkranYeniOyun = (Button) findViewById(R.id.FinalEkranYeniOyun);
		FinalEkranCikis = (Button) findViewById(R.id.FinalEkranCikis);

		Intent intent = getIntent();
		int sayi = intent.getIntExtra("soruSirasi", -1);
		int tur = intent.getIntExtra("tur", -1);
		if (tur == 0) {
			yanlisCevapBitir(sayi);
		}
		if (tur == 1) {
			cekilmeIstegi(sayi);
		}

		FinalEkranYeniOyun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(FinalEkranActivity.this,
						com.czal.millionare.MainActivity.class));
				finish();
			}
		});
		FinalEkranCikis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		 //Toast.makeText(getApplicationContext(), ""+sayi,
		 //Toast.LENGTH_SHORT).show();
	}

	private void cekilmeIstegi(int sayi) {
		com.czal.millionare.ParaDegeri pd = new com.czal.millionare.ParaDegeri();
		txt1.setText("Congratulations");
		txt2.setText("You Won");
		txt3.setText(pd.paraDegeri(sayi) + " TL");
	}

	private void yanlisCevapBitir(int sayi) {
		if (sayi <= 1) {
			txt1.setText("SORRY!");
			txt2.setText("Wrong Answer");
			txt3.setText("You Lose");
		} else if (sayi > 1 && sayi < 7) {
			txt1.setText("Wrong Answer!");
			txt2.setText("You Won");
			txt3.setText("1000 TL");
		} else if (sayi >= 7 && sayi < 11) {
			txt1.setText("Wrong Answer!");
			txt2.setText("You Won");
			txt3.setText("15000 TL");
		} else if (sayi == 11) {
			txt1.setText("Congratulations");
			txt2.setText("You Won");
			txt3.setText("1000000 TL");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.final_ekran, menu);
		return true;
	}

}

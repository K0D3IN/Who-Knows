package com.czal.millionare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends Activity implements OnClickListener {


	 ImageButton btnHakkimizda,btnYeniOyun,btnCikis;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);

		btnYeniOyun = (ImageButton)findViewById(R.id.btnNewGame);
		btnHakkimizda = (ImageButton)findViewById(R.id.btnHakkimizda);
		btnCikis = (ImageButton)findViewById(R.id.btnCikis);
		btnYeniOyun.setOnClickListener(this);
		btnHakkimizda.setOnClickListener(this);
		btnCikis.setOnClickListener(this);

	}

	public void onClick(View v) {
		int viewId = v.getId();
		if (viewId == R.id.btnNewGame) {
			Intent intent = new Intent(Menu.this, MainActivity.class);
			startActivity(intent);
			finish();
		} else if (viewId == R.id.btnHakkimizda) {
        /*AlertDialog.Builder mesajKutusu = new AlertDialog.Builder(this);
        mesajKutusu.setTitle("Hakkımızda");
        mesajKutusu.setIcon(com.anroid.millionare.R.drawable.hakkimizdaicon);
        mesajKutusu.setCancelable(true);
        mesajKutusu.setMessage("Bu program bunlar bunlar tarafından geliştirilmiştir.");
        mesajKutusu.show();*/
			Intent intent2 = new Intent(getApplicationContext(), HakkimizdaActivity.class);
			startActivity(intent2);
		} else if (viewId == R.id.btnCikis) {
			Menu.this.finish();
		}
	}

}

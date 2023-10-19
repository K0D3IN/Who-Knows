package com.czal.millionare;

import java.util.ArrayList;
import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	SQLiteDatabase ha;
	private database vt = null;
	private ArrayList<sorular> yarismaSoruListesi = null;
	private String dogru_cevap;
	private int soruSayisi = 0;
	MediaPlayer media;

	TextView soru;
	Button cevap1;
	Button cevap2;
	Button cevap3;
	Button cevap4;

	ImageButton imgPhone, imgYari, imgSeyirci;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		soru = (TextView) findViewById(R.id.textSoru);
		cevap1 = (Button) findViewById(R.id.buttonA);
		cevap2 = (Button) findViewById(R.id.buttonB);
		cevap3 = (Button) findViewById(R.id.buttonC);
		cevap4 = (Button) findViewById(R.id.buttonD);
		imgPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
		imgSeyirci = (ImageButton) findViewById(R.id.imageButtonSeyirci);
		imgYari = (ImageButton) findViewById(R.id.imageButtonYari);

		cevap1.setOnClickListener(this);
		cevap2.setOnClickListener(this);
		cevap3.setOnClickListener(this);
		cevap4.setOnClickListener(this);
		imgPhone.setOnClickListener(this);
		imgSeyirci.setOnClickListener(this);
		imgYari.setOnClickListener(this);

		vt = new database(getApplicationContext());

		SharedPreferences ayarlar = getSharedPreferences("tercihimmm", 0);
		if (ayarlar.getBoolean("ilkAcilisMi", true)) {
			SorulariEkle();
			ayarlar.edit().putBoolean("ilkAcilisMi", false).commit();
		}

		SorulariHazirla(); // Ekrana bas�lacak sorular haz�rlan�yor.

		cevaplariRastgeleAta(soruSayisi); // Cevaplar atan�yor.

	}

	@Override
	public void onClick(View v) {

		if (v.getId() == cevap1.getId()) {
			alertCagir("A");
		}
		if (v.getId() == cevap2.getId()) {
			alertCagir("B");
		}
		if (v.getId() == cevap3.getId()) {
			alertCagir("C");
		}
		if (v.getId() == cevap4.getId()) {
			alertCagir("D");
		}
		if (v.getId() == imgPhone.getId()) {
			alertCagirTelefon();
		}
		if (v.getId() == imgSeyirci.getId()) {
			alertCagirSeyirci();
		}
		if (v.getId() == imgYari.getId()) {
			YariYariyaJoker();
			imgYari.setVisibility(View.INVISIBLE);
		}

	}

	private void alertCagirSeyirci() {
		final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);
		alertbox2.setTitle("Seyirci Tahmini");
		alertbox2.setMessage(seyirciJoker());
		alertbox2.setIcon(R.drawable.hakkimizdaicon);

		alertbox2.setPositiveButton("Tamam",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						imgSeyirci.setVisibility(View.INVISIBLE);
					}
				});
		alertbox2.show();
	}

	private void oyunuBitir(int sayi) {
		Intent intent = new Intent(MainActivity.this, FinalEkranActivity.class);
		intent.putExtra("soruSirasi", sayi);
		intent.putExtra("tur", 0);
		startActivity(intent);
		finish();
	}

	private void alertCagirTelefon() {
		final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);
		alertbox2.setTitle("Aran�yor...");
		alertbox2.setMessage(telefonJokeri(dogru_cevap));
		alertbox2.setIcon(R.drawable.hakkimizdaicon);

		alertbox2.setPositiveButton("Tamam",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						imgPhone.setVisibility(View.INVISIBLE);
					}
				});
		alertbox2.show();
	}

	private String telefonJokeri(String cevap) {
		String sonuc = "";
		Random rnd = new Random();
		int tahmin = rnd.nextInt(4);
		if (tahmin == 0) {
			sonuc = "Cevap Kesinlikle " + cevap + " ��kk�";
		} else if (tahmin == 1) {
			sonuc = "Cevap B�y�k �htimalle " + cevap;
		} else if (tahmin == 2) {
			sonuc = "Cevap San�r�m " + cevap + " Olabilir";
		} else if (tahmin == 3) {
			sonuc = "Emin De�ilim ama " + cevap + " ��kk�d�r Bence";
		} else if (tahmin == 4) {
			sonuc = "�zg�n�m Cevap Veremiyorum. Konu Hakk�nda Pek Bilgim Yok";
		}

		return sonuc;
	}

	private void YariYariyaJoker() {
		Random rnd = new Random();
		int tahmin = rnd.nextInt(2);

		/********************************************************************************/
		if (dogru_cevap.equals("A")) {
			if (tahmin == 0) {
				cevap3.setVisibility(View.INVISIBLE);
				cevap4.setVisibility(View.INVISIBLE);
			} else if (tahmin == 1) {
				cevap2.setVisibility(View.INVISIBLE);
				cevap4.setVisibility(View.INVISIBLE);
			} else if (tahmin == 2) {
				cevap2.setVisibility(View.INVISIBLE);
				cevap3.setVisibility(View.INVISIBLE);
			}
		}
		/********************************************************************************/
		else if (dogru_cevap.equals("B")) {
			if (tahmin == 0) {
				cevap3.setVisibility(View.INVISIBLE);
				cevap4.setVisibility(View.INVISIBLE);
			} else if (tahmin == 1) {
				cevap1.setVisibility(View.INVISIBLE);
				cevap4.setVisibility(View.INVISIBLE);
			} else if (tahmin == 2) {
				cevap1.setVisibility(View.INVISIBLE);
				cevap3.setVisibility(View.INVISIBLE);
			}
		}
		/********************************************************************************/
		else if (dogru_cevap.equals("C")) {
			if (tahmin == 0) {
				cevap2.setVisibility(View.INVISIBLE);
				cevap4.setVisibility(View.INVISIBLE);
			} else if (tahmin == 1) {
				cevap1.setVisibility(View.INVISIBLE);
				cevap4.setVisibility(View.INVISIBLE);
			} else if (tahmin == 2) {
				cevap1.setVisibility(View.INVISIBLE);
				cevap4.setVisibility(View.INVISIBLE);
			}
		}
		/********************************************************************************/
		else if (dogru_cevap.equals("D")) {
			if (tahmin == 0) {
				cevap2.setVisibility(View.INVISIBLE);
				cevap3.setVisibility(View.INVISIBLE);
			} else if (tahmin == 1) {
				cevap1.setVisibility(View.INVISIBLE);
				cevap3.setVisibility(View.INVISIBLE);
			} else if (tahmin == 2) {
				cevap1.setVisibility(View.INVISIBLE);
				cevap2.setVisibility(View.INVISIBLE);
			}
		}
	}

	private String seyirciJoker() {
		String sonuc = "";
		Random rnd = new Random();

		/************************************************************************************************/
		if (soruSayisi <= 3) {
			int x1 = rnd.nextInt(10) + 50;
			int x2 = rnd.nextInt(5) + 20;
			int x3 = rnd.nextInt(5) + 10;
			int x4 = 100 - (x1 + x2 + x3);
			if (dogru_cevap.equals("A")) {
				sonuc = "%" + x1 + " A" + "\n" + "%" + x2 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("B")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x1 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("C")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x3 + " B" + "\n" + "%"
						+ x1 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("D")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x4 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x1 + " D";

			}
		}
		/************************************************************************************************/
		if (soruSayisi > 3 && soruSayisi <= 6) {
			int x1 = rnd.nextInt(3) + 40;
			int x2 = rnd.nextInt(5) + 30;
			int x3 = rnd.nextInt(5) + 15;
			int x4 = 100 - (x1 + x2 + x3);
			if (dogru_cevap.equals("A")) {
				sonuc = "%" + x1 + " A" + "\n" + "%" + x2 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("B")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x1 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("C")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x3 + " B" + "\n" + "%"
						+ x1 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("D")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x4 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x1 + " D";

			}
		}
		/************************************************************************************************/
		if (soruSayisi > 6 && soruSayisi <= 9) {
			int x1 = rnd.nextInt(3) + 32;
			int x2 = rnd.nextInt(5) + 29;
			int x3 = rnd.nextInt(5) + 22;
			int x4 = 100 - (x1 + x2 + x3);
			if (dogru_cevap.equals("A")) {
				sonuc = "%" + x1 + " A" + "\n" + "%" + x2 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("B")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x1 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("C")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x3 + " B" + "\n" + "%"
						+ x1 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("D")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x4 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x1 + " D";

			}
		}
		/************************************************************************************************/
		if (soruSayisi > 9 && soruSayisi <= 12) {
			int x1 = rnd.nextInt(5) + 24;
			int x2 = rnd.nextInt(4) + 23;
			int x3 = rnd.nextInt(3) + 22;
			int x4 = 100 - (x1 + x2 + x3);
			if (dogru_cevap.equals("A")) {
				sonuc = "%" + x1 + " A" + "\n" + "%" + x2 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("B")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x1 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("C")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x3 + " B" + "\n" + "%"
						+ x1 + " C" + "\n" + "%" + x4 + " D";

			}
			if (dogru_cevap.equals("D")) {
				sonuc = "%" + x2 + " A" + "\n" + "%" + x4 + " B" + "\n" + "%"
						+ x3 + " C" + "\n" + "%" + x1 + " D";

			}
		}
		/************************************************************************************************/
		return sonuc;

	}

	private void alertCagir(final String cevap) {
		final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);
		alertbox2.setTitle("Emin misiniz?");
		alertbox2.setIcon(R.drawable.hakkimizdaicon);
		alertbox2.setNegativeButton("Hay�r",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alertbox2.setPositiveButton("Evet Son Karar�m", // R.string.evet,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						CevapKontrol(cevap);

					}
				});
		alertbox2.show();
	}

	/* Geri Tu�una bas�ld���nda */
	private void OyundanCikmaIstegi() {
		final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);
		alertbox2.setTitle("Oyundan ��kmak istedi�inize emin misiniz?");
		alertbox2.setPositiveButton("Tamam",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
		alertbox2.setNegativeButton("Hay�r",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		alertbox2.show();
	}

	public void CevapKontrol(String cevap) {
		if (dogru_cevap.equals(cevap)) {
			/*
			 * Soru bir artt�r�l�yor. Cevaplar rastgele artt�r�l�yor.
			 */
			if (soruSayisi < 11) {
				Intent intent = new Intent(getApplicationContext(),
						PuanActivity.class);
				intent.putExtra("SoruSayisi", soruSayisi);
				startActivityForResult(intent, 0);
			}
			if (soruSayisi == 11) {
				Intent intent = new Intent(getApplicationContext(),
						FinalEkranActivity.class);
				intent.putExtra("soruSirasi", soruSayisi);
				intent.putExtra("tur", 0);
				//intent.putExtra("SoruSayisi", soruSayisi);
				startActivityForResult(intent, 0);
				finish();
			}
		} else {
			// Direk son sayfaya y�nel. Puan� da ge�erli puan� yazd�r
			oyunuBitir(soruSayisi);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Boolean result = data.getBooleanExtra("Durum", false);
			if (result) {
				soruSayisi++;
				cevaplariRastgeleAta(soruSayisi);
			}
		}
		if (resultCode == RESULT_CANCELED) {
			// Write your code if there's no result
		}

	}

	public void cevaplariRastgeleAta(int sayi) {
		cevap1.setVisibility(View.VISIBLE);
		cevap2.setVisibility(View.VISIBLE);
		cevap3.setVisibility(View.VISIBLE);
		cevap4.setVisibility(View.VISIBLE);
		/*******************************************************************/
		Random rnd = new Random();
		int yeni_sayi = rnd.nextInt(4);
		soru.setText(yarismaSoruListesi.get(sayi).getSoru());
		if (yeni_sayi == 0) {
			cevap1.setText(yarismaSoruListesi.get(sayi).getD_cevap());
			cevap2.setText(yarismaSoruListesi.get(sayi).getY_cevap1());
			cevap3.setText(yarismaSoruListesi.get(sayi).getY_cevap2());
			cevap4.setText(yarismaSoruListesi.get(sayi).getY_cevap3());
			dogru_cevap = "A";
		} else if (yeni_sayi == 1) {
			cevap2.setText(yarismaSoruListesi.get(sayi).getD_cevap());
			cevap1.setText(yarismaSoruListesi.get(sayi).getY_cevap1());
			cevap3.setText(yarismaSoruListesi.get(sayi).getY_cevap2());
			cevap4.setText(yarismaSoruListesi.get(sayi).getY_cevap3());
			dogru_cevap = "B";
		} else if (yeni_sayi == 2) {
			cevap3.setText(yarismaSoruListesi.get(sayi).getD_cevap());
			cevap2.setText(yarismaSoruListesi.get(sayi).getY_cevap1());
			cevap1.setText(yarismaSoruListesi.get(sayi).getY_cevap2());
			cevap4.setText(yarismaSoruListesi.get(sayi).getY_cevap3());
			dogru_cevap = "C";
		} else if (yeni_sayi == 3) {
			cevap4.setText(yarismaSoruListesi.get(sayi).getD_cevap());
			cevap2.setText(yarismaSoruListesi.get(sayi).getY_cevap1());
			cevap3.setText(yarismaSoruListesi.get(sayi).getY_cevap2());
			cevap1.setText(yarismaSoruListesi.get(sayi).getY_cevap3());
			dogru_cevap = "D";
		}
	}

	private String[] SELECT = { "soruID", "soru", "dogru_cevap", "y_cevap1",
			"y_cevap2", "y_cevap3", "kategori" };

	private ArrayList<sorular> KayitGetir(int KategoriId) {

		ArrayList<sorular> soruListesi = new ArrayList<sorular>();
		sorular ques;
		String Where = "kategori =" + KategoriId;
		SQLiteDatabase db = vt.getReadableDatabase();
		Cursor cursor = db.query("soru_cevap", SELECT, Where, null, null, null,
				null);

		while (cursor.moveToNext()) {

			int id = cursor.getInt(cursor.getColumnIndex("soruID"));

			String soru = cursor.getString(cursor.getColumnIndex("soru"));

			String d_cevap = cursor.getString(cursor
					.getColumnIndex("dogru_cevap"));

			String y_cevap1 = cursor.getString(cursor
					.getColumnIndex("y_cevap1"));

			String y_cevap2 = cursor.getString(cursor
					.getColumnIndex("y_cevap2"));

			String y_cevap3 = cursor.getString(cursor
					.getColumnIndex("y_cevap3"));

			int kategori = cursor.getInt(cursor.getColumnIndex("kategori"));

			ques = new sorular(soru, d_cevap, y_cevap1, y_cevap2, y_cevap3, id,
					kategori);
			soruListesi.add(ques);
		}

		return soruListesi;
	}

	private void SorulariHazirla() {

		yarismaSoruListesi = new ArrayList<sorular>();

		int[] dizi = new int[3];
		int soruSayisi = 0;

		if (0 <= soruSayisi && soruSayisi < 3) {

			ArrayList<sorular> birincikademe = KayitGetir(1);
			// Toast.makeText(getApplicationContext(), ""+birincikademe.size(),
			// Toast.LENGTH_SHORT).show();
			dizi = rastgeleIdUret(birincikademe.size());

			for (int k = 0; k < 3; k++) {
				yarismaSoruListesi.add(birincikademe.get(dizi[k]));
				soruSayisi++;
			}
		}
		if (3 <= soruSayisi && soruSayisi < 6) {

			ArrayList<sorular> ikincikademe = KayitGetir(2);
			// Toast.makeText(getApplicationContext(), ""+birincikademe.size(),
			// Toast.LENGTH_SHORT).show();
			dizi = rastgeleIdUret(ikincikademe.size());

			for (int k = 0; k < 3; k++) {
				yarismaSoruListesi.add(ikincikademe.get(dizi[k]));
				soruSayisi++;
			}
		}
		if (6 <= soruSayisi && soruSayisi < 9) {

			ArrayList<sorular> ucuncukademe = KayitGetir(3);
			// Toast.makeText(getApplicationContext(), ""+birincikademe.size(),
			// Toast.LENGTH_SHORT).show();
			dizi = rastgeleIdUret(ucuncukademe.size());

			for (int k = 0; k < 3; k++) {
				yarismaSoruListesi.add(ucuncukademe.get(dizi[k]));
				soruSayisi++;
			}
		}
		if (9 <= soruSayisi && soruSayisi < 12) {

			ArrayList<sorular> dorduncukademe = KayitGetir(4);
			// Toast.makeText(getApplicationContext(), ""+birincikademe.size(),
			// Toast.LENGTH_SHORT).show();
			dizi = rastgeleIdUret(dorduncukademe.size());

			for (int k = 0; k < 3; k++) {
				yarismaSoruListesi.add(dorduncukademe.get(dizi[k]));
				soruSayisi++;
			}
		}
		// Toast.makeText(getApplicationContext(), ""+yarismaSoruListesi.size(),
		// Toast.LENGTH_SHORT).show();
	}

	public static int[] rastgeleIdUret(int toplam) {

		Random rnd = new Random();
		int[] dizim = new int[3];
		int yeni_sayi;
		Boolean durum = true;

		for (int i = 0; i < dizim.length; i++) {

			while (durum) {
				yeni_sayi = rnd.nextInt(toplam);

				if (i == 0) {
					dizim[0] = yeni_sayi;
					break;
				}

				for (int k = 0; k < i; k++) {
					if (dizim[k] == yeni_sayi) {
						durum = true;
						break;
					} else
						durum = false;
				}

				if (durum == false)
					dizim[i] = yeni_sayi;
			}
			durum = true;
		}
		return dizim;
	}

	public int[] siralama(int[] dizi) {
		int gecici;
		for (int i = 0; i <= dizi.length - 1; i++) {
			for (int j = 1; j <= dizi.length - 1; j++) {
				if (dizi[j - 1] < dizi[j]) {
					gecici = dizi[j - 1];
					dizi[j - 1] = dizi[j];
					dizi[j] = gecici;
				}
			}
		}
		return dizi;
	}

	/* Oyundaki iken geri tu�una bas�l�rsa */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			OyundanCikmaIstegi();
		}
		return super.onKeyDown(keyCode, event);
	}

	private void SorulariEkle() {
		database.SoruEkle(ha, vt, "Türkiyenin Başkenti Neresidir?", "Ankara",
				"Erzurum", "Iğdır", "Hakkari", 1);
		database.SoruEkle(ha, vt, "Facebook'un kurucusu kimdir?",
				"Mark Zuckenberg", "Larry Page", "Steve Jobs", "Bill Gates", 1);
		database.SoruEkle(ha, vt, "Tiger Woods hangi sporla uğraşmaktadır?",
				"Golf", "Tenis", "Grekoromen G�re�", "Yüzme", 1);
		database.SoruEkle(ha, vt, "İnsanların Kaç Ayağı Vardır?", "2", "3",
				"5", "0", 1);
		// /
		database.SoruEkle(ha, vt, "�lk T�rk Psikolojik Roman ? ", "Eyl�l",
				"�al�ku�u", "Araba Sevdas�", "Sefiller", 2);
		database.SoruEkle(ha, vt, "Hz. Ali Efendimizin K�l�c�n�n Ad� Nedir?",
				"Z�lfikar", "Abuzer", "Ebu �uyh", "�smet", 2);
		database.SoruEkle(ha, vt, "Saltanat Ka� Y�l�nda Kald�r�lm��t�r?",
				"1 Kas�m 1922", "1 Kas�m 1923", "23 Kas�m 1922",
				"23 Nisan 1923", 2);
		database.SoruEkle(ha, vt, "At üstünde Oynananılan Türk Sporu?",
				"Cirit", "At yarışı", "Polo", "Kriket", 2);
		// //
		database.SoruEkle(ha, vt, "Lale Devri Kaç Yıl Sürmüştür?", "12", "15",
				"11", "Laleler Bitene Kadar", 3);
		database.SoruEkle(ha, vt, "Türkiye'de ilk Matbaayı Kim Kurmuştur?",
				"Ibrahim Müteferrika", "Yazar Hüseyin", "Ibn-i Galip",
				"Ismet Inönü", 3);
		database.SoruEkle(
				ha,
				vt,
				"Narlar ve Ayvalar Adl� Tablosu Hayattayken Louvre M�zeyisine Kabul Edilen �lk T�rk Ressam Kimdir?",
				"�eker Ahmet Pa�a", "Naci Kalmuko�lu", "Osman Hamdi Bey",
				"�brahim �all�", 3);
		database.SoruEkle(ha, vt,
				"Eurovision'a Kat�lan �lk T�rk Sanat�� Kimdir?",
				"Semiha Yank�", "Sertap Erener", "Ajda Pekkan", "Ergin Koray",
				3);
		// /
		database.SoruEkle(ha, vt, "Ba�kenti Astana Olan �lke ?", "Kazakistan",
				"�zbekistan", "K�rg�zistan", "Tacikistan", 4);
		database.SoruEkle(ha, vt, "Ashab� Kehf Nerededir?", "Tarsus",
				"Silifke", "Ma�ka", "Erdemli", 4);
		database.SoruEkle(ha, vt, "2013 Akdeniz Oyunlar� Nerede Yap�lm��t�r?",
				"Mersin", "Alanya", "Mu�la", "Antalya", 4);
		database.SoruEkle(ha, vt,
				"Kaplumba�a Terbiyecisi Resminde Ka� Kaplumba�a Vard�r?", "5",
				"4", "6", "7", 4);
		database.SoruEkle(ha, vt, "Bir �nsan Ne Kadar D�v�l�r?",
				"E�ek Sudan Gelene Kadar", "E�ek �ay�rdan Gelene Kadar",
				"Ar�lar Bal Yapana Kadar", "Fener Kupay� Alana Kadar", 4);
	}
}
package com.czal.millionare;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

	private static final int SURUM = 1;
	private static final String DATABASE = "uu";

	public database(Context con)
	{
		super(con,DATABASE,null,SURUM);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE soru_cevap ("
					+"soruID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+"soru TEXT  NOT NULL,"
					+"dogru_cevap TEXT  NOT NULL,"
					+"y_cevap1 TEXT  NOT NULL,"
					+"y_cevap2 TEXT  NOT NULL,"
					+"y_cevap3 TEXT  NOT NULL,"
					+"kategori INTEGER  NOT NULL);");		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXIST soru_cevap");
		onCreate(db);
	}
	
	public static void SoruEkle(SQLiteDatabase db, database dbHelp,String soru,String d_cevap,String y_c1,String y_c2,String y_c3,int kat) {
		
		ContentValues values = new ContentValues();
		values.put("soru", soru);
		values.put("dogru_cevap", d_cevap);
		values.put("y_cevap1", y_c1);
		values.put("y_cevap2", y_c2);
		values.put("y_cevap3", y_c3);
		values.put("kategori", kat);

		db = dbHelp.getWritableDatabase();
		db.insert("soru_cevap", null, values);
	}
	
}

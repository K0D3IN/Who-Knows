package com.czal.millionare;

public class sorular {
	private String soru,d_cevap,y_cevap1,y_cevap2,y_cevap3;
	private int soruId,kategori;
	
	
	public sorular(String soru, String d_cevap, String y_cevap1,
			String y_cevap2, String y_cevap3, int soruId, int kategori) {
		
		this.soru = soru;
		this.d_cevap = d_cevap;
		this.y_cevap1 = y_cevap1;
		this.y_cevap2 = y_cevap2;
		this.y_cevap3 = y_cevap3;
		this.soruId = soruId;
		this.kategori = kategori;
	}
	public String getSoru() {
		return soru;
	}
	public void setSoru(String soru) {
		this.soru = soru;
	}
	public String getD_cevap() {
		return d_cevap;
	}
	public void setD_cevap(String d_cevap) {
		this.d_cevap = d_cevap;
	}
	public String getY_cevap1() {
		return y_cevap1;
	}
	public void setY_cevap1(String y_cevap1) {
		this.y_cevap1 = y_cevap1;
	}
	public String getY_cevap2() {
		return y_cevap2;
	}
	public void setY_cevap2(String y_cevap2) {
		this.y_cevap2 = y_cevap2;
	}
	public String getY_cevap3() {
		return y_cevap3;
	}
	public void setY_cevap3(String y_cevap3) {
		this.y_cevap3 = y_cevap3;
	}
	public int getSoruId() {
		return soruId;
	}
	public void setSoruId(int soruId) {
		this.soruId = soruId;
	}
	public int getKategori() {
		return kategori;
	}
	public void setKategori(int kategori) {
		this.kategori = kategori;
	}

}

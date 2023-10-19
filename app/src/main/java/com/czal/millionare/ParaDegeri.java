package com.czal.millionare;

public class ParaDegeri {

	public static int paraDegeri(int sayi)
	{
		int deger=0;
		switch (sayi) {
		case -1:
			deger = 0;
			break;
		case 0:
			deger = 500;
			break;
		case 1:
			deger = 1000;
			break;
		case 2:
			deger = 2000;
			break;
		case 3:
			deger = 3000;
			break;
		case 4:
			deger = 5000;
			break;
		case 5:
			deger = 7500;
			break;
		case 6:
			deger = 15000;
			break;
		case 7:
			deger = 30000;
			break;
		case 8:
			deger = 60000;
			break;
		case 9:
			deger = 125000;
			break;
		case 10:
			deger = 250000;
			break;
		case 11:
			deger = 1000000;
			break;
		}
		return deger;
	} 
}

package com.czal.millionare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class AcilisEkrani extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acilis);
		
		Menu();
	}
	
	private void Menu()
	{
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        anim.reset();
        ImageView img = (ImageView)findViewById(R.id.imageView1);
        img.clearAnimation();
        img.startAnimation(anim);
        
        anim.setAnimationListener(new AnimationListener() {
			
        	@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
        		Intent intent = new Intent(AcilisEkrani.this,Menu.class);
				startActivity(intent);
				AcilisEkrani.this.finish();
			}
        	
			public void onAnimationStart(Animation animation) {}
			public void onAnimationRepeat(Animation animation) {}
		});
	}

}

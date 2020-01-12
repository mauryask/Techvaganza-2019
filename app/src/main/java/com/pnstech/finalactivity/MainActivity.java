
package com.pnstech.finalactivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity

{

   private Typeface myfont;
    private TextView tv1,tv2;
   private  ImageView img ;
    public static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //enable full screen mode

        requestWindowFeature(Window.FEATURE_NO_TITLE); // hides title
        getSupportActionBar().hide(); // hides title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// enables full screen

        setContentView(R.layout.activity_main);

        tv1= findViewById(R.id.tag);
        tv2= findViewById(R.id.year);
        img= findViewById(R.id.imageview);
        myfont=Typeface.createFromAsset(this.getAssets(),"fonts/Brandywine  Normal" +
                ".ttf");
        tv2.setTypeface(myfont);
        tv1.setTypeface(myfont);


    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_animation);
        img.setAnimation(animation);
        tv1.startAnimation(animation);
        tv2.startAnimation(animation);


       //splash

       new Handler().postDelayed(new Runnable(){
            @Override
            public void run()
            {
                Intent intent= new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}

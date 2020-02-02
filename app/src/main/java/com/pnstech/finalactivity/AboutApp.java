/*
 *@Author Shubham Maurya
 *@Date April 20, 2019
 *@Company  pnstech Inc.*/

package com.pnstech.finalactivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class AboutApp extends AppCompatActivity {
    private ImageView git, insta, fb, linkdn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //hides the title
        getSupportActionBar().hide(); //hides title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_app);
        insta= findViewById(R.id.insta_id);
        fb= findViewById(R.id.fb_id);
        linkdn= findViewById(R.id.link_id);
        git= findViewById(R.id.git_id);

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/shubham11krm/?hl=en"));
                startActivity(intent);

            }
        });

        linkdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/cyberthreatatnit/"));
                startActivity(intent);
            }
        });

       git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/pnstech"));
                startActivity(intent);

            }
        });

       fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cyberatnit"));
                startActivity(intent);
            }
        });
    }

}


package com.pnstech.finalactivity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends DarkMode implements  NavigationView.OnNavigationItemSelectedListener {
    public static final String CHANNEl1_ID = "channel1";
    private NotificationManagerCompat managerCompat;
    private Toolbar mtoolbar;
    private DrawerLayout mdrawerLayout;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private NavigationView navigationView;
    private TextView register;
    private ImageView imageView;
    //timer

    private TextView txtTimerDay, txtTimerHour, txtTimerMinute, txtTimerSecond;
    private TextView tvEvent;
    private Handler handler;
    private Runnable runnable;

    //timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hides title bar
        setContentView(R.layout.activity_home);

        //change image testing

        imageView= findViewById(R.id.image_View);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES)
        {
           imageView.setImageResource(R.drawable.nitsxrlogo);
        }
        else
        {
            imageView.setImageResource(R.drawable.nitlogo);
        }
        //change image testing

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        //hiding item demo
        hideItem();

        createNotificationChannel();
        managerCompat=NotificationManagerCompat.from(this);


        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart =prefs.getBoolean("firststart",true);


        //testing
       if(firstStart)
        {
            testing();
        }


        txtTimerDay = findViewById(R.id.txtTimerDay);
        txtTimerHour = findViewById(R.id.txtTimerHour);
        txtTimerMinute = findViewById(R.id.txtTimerMinute);
        txtTimerSecond = findViewById(R.id.txtTimerSecond);
        tvEvent = findViewById(R.id.tv_event);
        register= findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://techvaganza.org/register"));
                startActivity(intent);
            }
        });

        countDownStart();
        //timer

        if (currentUser != null) {
            headerUpdate();
        }

        //testing phase
        mtoolbar = findViewById(R.id.toolbarx);
        mtoolbar.inflateMenu(R.menu.menu_main);
        mtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.about:

                        startActivity(new Intent(HomeActivity.this, AboutApp.class));
                        break;

                    case R.id.notifications:
                        startActivity(new Intent(HomeActivity.this, PopupNotification.class));
                        Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });

        //test successful

        setUpToolbarMenu();
        setUpNavigationDrawerMenu();
    }

    //testing successful

    public void bugsAndGlitch(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] to = {"pnssoftwares7@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Have You Any Query Or Suggestion?");
        intent.putExtra(Intent.EXTRA_TEXT, "Write Here....");
        intent.setType("message/rfc822");// this is must
        Intent.createChooser(intent, "Choose Email"); //second argument is optional
        startActivity(intent);
    }

    //testing phase 2

    private void setUpToolbarMenu() {
        mtoolbar = findViewById(R.id.toolbarx);
        mtoolbar.setTitle("Techvaganza");
    }

    private void setUpNavigationDrawerMenu() {
        NavigationView navigationView = findViewById(R.id.navigation_id);
        navigationView.setNavigationItemSelectedListener(this);
        mdrawerLayout = findViewById(R.id.drawer_id);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ActionBarDrawerToggle actionbar_drawer_toogle = new ActionBarDrawerToggle(this, mdrawerLayout, mtoolbar, R.string.drawer_open, R.string.drawer_close);
        mdrawerLayout.addDrawerListener(actionbar_drawer_toogle);
        actionbar_drawer_toogle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        closeDrawer();
        switch (menuItem.getItemId()) {
            case R.id.item_event:
                startActivity(new Intent(HomeActivity.this, EventDetail.class));
                Toast.makeText(getApplicationContext(), "Loading.....", Toast.LENGTH_LONG).show();
                break;

            case R.id.item_login:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;

            case R.id.item_organizers:
                startActivity(new Intent(HomeActivity.this, OrganizerDetail.class));
                Toast.makeText(getApplicationContext(), "Loading.....", Toast.LENGTH_LONG).show();
                break;


            case R.id.item_rate:
                startActivity(new Intent(HomeActivity.this, RatingActivity.class));
            break;
            case R.id.item_register:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://techvaganza.org/register"));
                startActivity(intent);
                break;
            case R.id.item_logout:
                auth.signOut();
                finish();
               startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;

            case R.id.item_winner:
                startActivity(new Intent(HomeActivity.this,WinnerDetail.class));
                Toast.makeText(getApplicationContext(), "Loading.....", Toast.LENGTH_LONG).show();
                break;

            case R.id.item_works:
                startActivity(new Intent(HomeActivity.this,WorkshopDetail.class));
                Toast.makeText(getApplicationContext(), "Loading.....", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_notify:
                startActivity(new Intent(HomeActivity.this, SendNotification.class));
                break;

            case R.id.item_setting:
                startActivity(new Intent(HomeActivity.this, DarkMode.class));
                break;
                //share link
            case R.id.item_share:
            {
                final PackageManager pm=getPackageManager();
                final Intent a = new Intent(Intent.ACTION_SEND);
                Toast.makeText(getApplicationContext(), "Wait.....", Toast.LENGTH_LONG).show();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Link");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         String app_link = dataSnapshot.child("link").getValue(String.class);

                       //testing
                        a.setType("text/link");
                        String shareBody = "Download Techvaganza App For Regular Updates Of Your Techfest."+
                                "\n\t\t\t\t\t\t\t-Team Techvaganza"+
                                "\n"+"---------------------------------------------------"
                                +"\n"+app_link;
                        String shareSub = "Team Techvaganza";
                        a.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        a.putExtra(Intent.EXTRA_TEXT, shareBody);
                        try {

                            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                            a.setPackage("com.whatsapp");//important it will select only whatsapp
                           }catch (PackageManager.NameNotFoundException e) {
                            Toast.makeText(getApplicationContext(), "WhatsApp is not Installed", Toast.LENGTH_SHORT)
                                    .show();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        startActivity(a);
                        //testing
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //setting link for sharing

            }
                break;

        }
        return true;
    }



    //close drawer
    private void closeDrawer() {
        mdrawerLayout.closeDrawer(GravityCompat.START); //START can be replaced by END depending upon pupose
    }
    //open drawer

    private void showDrawer() {
        mdrawerLayout.openDrawer(GravityCompat.START);
    }

    public void onBackPress() {
        if (mdrawerLayout.isDrawerOpen(GravityCompat.START))
            closeDrawer();
        else
            super.onBackPressed();
    }

    //timer

    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2020-5-4");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtTimerDay.setText("" + String.format("%02d", days));
                        txtTimerHour.setText("" + String.format("%02d", hours));
                        txtTimerMinute.setText(""
                                + String.format("%02d", minutes));
                        txtTimerSecond.setText(""
                                + String.format("%02d", seconds));

                    } else {
                        tvEvent.setVisibility(View.VISIBLE);
                        tvEvent.setText("The Event Started!");
                        textViewGone();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    public void textViewGone() {
        findViewById(R.id.LinearLayout10).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout11).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout12).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout13).setVisibility(View.GONE);
    }

    //timer
    public void headerUpdate() {
        NavigationView navigationView = findViewById(R.id.navigation_id);
        View headView = navigationView.getHeaderView(0);
        TextView userEmail=headView.findViewById(R.id.email_id);


        for (UserInfo profile : currentUser.getProviderData()) {
            String providerId = profile.getProviderId();
            String uid = profile.getUid();
            String name = profile.getDisplayName();
            String email = profile.getEmail();
            userEmail.setText(email);
        }

    }


    //hide some items from drawer layout

    private void hideItem()
    {
        navigationView = findViewById(R.id.navigation_id);
        Menu nav_Menu = navigationView.getMenu();
        if(currentUser==null)
        {
            nav_Menu.findItem(R.id.item_logout).setVisible(false);
            nav_Menu.findItem(R.id.item_notify).setVisible(false);
        }
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O || Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT)
        {
            nav_Menu.findItem(R.id.item_setting).setVisible(true);
        }
        else
        {
            nav_Menu.findItem(R.id.item_setting).setVisible(false);
        }
    }


    public void facebook(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Techvaganza/"));
        startActivity(intent);
    }

    public void tweeter(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/techvaganzasxr"));
        startActivity(intent);
    }

    public void instagram(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/techvaganzanit/?hl=en"));
        startActivity(intent);
    }


    //tsting

    public void testing() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Notifications");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               // String title = dataSnapshot.child("Not").child("title").getValue(String.class);
              // String message = dataSnapshot.child("Not").child("message").getValue(String.class);

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    NotificationStore notificationStore = child.getValue(NotificationStore.class);
                    //Toast.makeText(getApplicationContext(), "data is chnaged", Toast.LENGTH_SHORT).show();
                    sendByChannel1(/*title, message*/);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void sendByChannel1(/*String title, String message*/)
    {
        //opening activity onclick notification

        Intent activityIntent = new Intent(this, PopupNotification.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,activityIntent,0);
        //Intent broadCastIntent =new Intent(this, NotificationReceiver.class);
//broadCastIntent.putExtra("toastMessage","Click Here");

//PendingIntent actionIntent =PendingIntent.getBroadcast(this,0,
      //  broadCastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        //opening activity onclick notification

        Notification notification = new NotificationCompat.Builder(this, CHANNEl1_ID)
                .setSmallIcon(R.drawable.techvaganza)
                .setContentTitle("Team Techvaganza")
                .setContentText("Click to see notifications")
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
               .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                //.setColor(Color.BLUE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
              // .setOnlyAlertOnce(true)
                //.addAction(R.drawable.notifyx,"Click",actionIntent)
                .build();
        managerCompat.notify(1, notification);

     SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firststart",false);
        editor.apply();
    }

    private void createNotificationChannel()
    {

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new NotificationChannel(CHANNEl1_ID,
                    "channel1",NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is Channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

}

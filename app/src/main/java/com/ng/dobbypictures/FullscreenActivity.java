package com.ng.dobbypictures;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Fullscreen Activity for the Slideshow
 * Allows user to :
 *      1. View the Slideshow
 *      2. Checks for updates for said show on the fly without interruptions
 *      3. Change channel by revisiting Main Activity
 */
public class FullscreenActivity extends AppCompatActivity {

    /**
     * TAG for debugging
     */
    private final String TAG = "FullscreenActivity";


    /**
     * ch used to store chosen Channel
     */
    private String ch;


    /**
     * Basic UI setup before pulling images
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        findViewById(R.id.dummy_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        WindowManager.LayoutParams attrib = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }


        Intent intent = getIntent();
        ch = intent.getStringExtra("channel");
        try {
            getSupportActionBar().setTitle(ch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StartShow();

    }


    /**
     * Begins download of the List of Pictures
     * @param channel The Channel from which Pictures are to be pulled
     */
    private void DownloadList(String channel) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference myRef = db.collection("channels").document(channel.toLowerCase().replaceAll(" ", "_"));


        myRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        ArrayList pics = (ArrayList) document.getData().get("pictures");

                        assert pics != null;
                        final Picture[] p = new Picture[pics.size()];

                        for (int i=0; i<pics.size(); i++) {
                            String param = pics.get(i).toString();

                            p[i] = new Picture(getURL(param), getTime(param)); //Setting up the Pictures Array for use

                            Log.d(TAG, "URL : " + p[i].url + " ; Time : " + p[i].time);
                        }

                        //Check if the Timer has run out before calling looper(p)
                        new CountDownTimer(2000, 20) {

                            public void onTick(long millisUntilFinished) {
                                if (ready) {
                                    looper(p);
                                    this.cancel();
                                }
                            }

                            public void onFinish() {
                                looper(p);
                            }
                        }.start();


                    } else {
                        Log.d(TAG, "No such document");
                        Toast.makeText(FullscreenActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                    Toast.makeText(FullscreenActivity.this, "Error : " + task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * ready is set to true once countdown timer has elapsed
     */
    private boolean ready = false;

    /**
     * Countdown timer to start the Slide Show
     */
    private void StartShow() {
        ready = false;
        DownloadList(ch);
        new CountDownTimer(2900, 1000) {

            public void onTick(long millisUntilFinished) {
                TextView t = findViewById(R.id.text_timer);
                t.setText("Show Begins in " + (millisUntilFinished / 1000 + 1));
            }

            public void onFinish() {
                TextView t = findViewById(R.id.text_timer);
                t.setText("");
                t.setVisibility(View.GONE);
                ready = true;
                //looper(p);
            }
        }.start();
    }

    private int CurrentIndex=0;

    /**
     * Recursively iterates through the list of Pictures
     * At the end of the loop, it Refreshes the list by calling DownloadList()
     * @param p Array of Pictures that are to be pulled
     */
    private void looper(final Picture... p) {

        // Imageview to show
        ImageView image = findViewById(R.id.fullscreen_content);

        // Image url
        String image_url = p[CurrentIndex].url.toString();
        final int time = p[CurrentIndex].time;

        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        imgLoader.DisplayImage(image_url, image, size.x, size.y);


        CountDownTimer p_counter = new CountDownTimer(time * 1000, 10) {

            public void onTick(long millisUntilFinished) {
                ProgressBar prg = findViewById(R.id.progressBar);
                prg.setProgress((int)millisUntilFinished/(time*10));
            }

            /**
             * Check for updates if at the end of Pictures Array
             */
            public void onFinish() {
                if (CurrentIndex == p.length-1) {
                    //Check For Updates
                    CurrentIndex++;
                    CurrentIndex = CurrentIndex%p.length;
                    DownloadList(ch);
                } else {
                    Log.d(TAG, "p.length : " + p.length);
                    Log.d(TAG, "CurrentIndex1 : " + CurrentIndex);
                    CurrentIndex++;
                    CurrentIndex = CurrentIndex%p.length;
                    Log.d(TAG, "CurrentIndex2 : " + CurrentIndex);

                    /**
                     * In the end, function looper(final Picture... p) calls itself indefinitely
                     * (Until user pressed 'Back' or 'Change Channel')
                     */
                    looper(p);
                }
            }
        };
        p_counter.start();
    }

    /**
     *
     */

    /**
     * The Time is given in the format :
     * {time=2,url="https://goo.....some.jpg"}
     *
     * This can be easily worked with String Manipulation
     * No need for a JSON parser
     * @param p String of the format {time=2,url="https://goo.....some.jpg"}
     * @return Time of corresponding picture
     */
    private int getTime(String p) {
        return Integer.parseInt(p.replace("{","").replace("}", "").replace("time=","").replace("url=","").split(",")[0]);
    }

    /**
     * The URL is given in the format :
     * {time=2,url="https://goo.....some.jpg"}
     *
     * This can be easily worked with String Manipulation
     * No need for a JSON parser
     * @param p String of the format {time=2,url="https://goo.....some.jpg"}
     * @return URL of corresponding picture
     */
    private URL getURL(String p) {
        URL u = null;

        try {
            u = new URL(p.replace("{","").replace("}", "").replace("time=","").replace("url=","").split(",")[1]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return u;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Picture is a class to handle the data pulled from the server
     */
    public class Picture {
        public URL url;
        public int time;

        Picture(URL u, int t) {
            this.url = u;
            this.time = t;
        }
    }

}

package com.ng.dobbypictures;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


/**
 * Landing Activity of the App
 * Allows user to :
 *      1. Chose from multiple Channels
 *      2. Start Slideshow
 *      3. Checks for Read/Write Permissions
 */
public class MainActivity extends AppCompatActivity {

    /**
     * TAG for debugging
     */
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Check for Read/Write Permissions
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //Granted
            } else {
                findViewById(R.id.permisssion_req).setVisibility(View.VISIBLE);
            }
        }

        Button startButton = findViewById(R.id.startButton);
        final Spinner channel = findViewById(R.id.channel);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check for Read/Write Permissions
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                        startSlideShow(channel.getSelectedItem().toString());

                    } else {
                        requestPermissionForExternalStorage();
                    }
                } else {
                    requestPermissionForExternalStorage();
                }
            }
        });
    }

    /**
     * Prompts the User to give Storage Access
     */
    public void requestPermissionForExternalStorage() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

    }

    /**
     * Starts the FullscreenActivity
     * @param channel the Channel that the user has selected
     */
    private void startSlideShow(String channel) {
        Intent i = new Intent(MainActivity.this, FullscreenActivity.class);
        i.putExtra("channel", channel);
        startActivity(i);
    }
}

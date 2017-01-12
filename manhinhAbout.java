package com.example.administrator.ssd;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class manhinhAbout extends AppCompatActivity {

    Sound sound;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh_about);
        sound = new Sound();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound_button);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (sound.load(sound.file).equals("true")) {
            mediaPlayer.start();
        }

        finish();
        startActivity(new Intent(getApplicationContext(), manhinhbatdau.class));
    }
}



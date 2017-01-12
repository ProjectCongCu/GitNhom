package com.example.administrator.ssd;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 6/3/2016.
 */
public class manhinhbatdau extends Activity {
    SQLiteDataGame sqLiteDataGame;
    Button btnPlayBatDau, btnAboutBatDau, btnSoundBatDau;
    ImageView imgLoGoBatDau;
    List<DataGame_DTO> ar;

    int width, height;

    MediaPlayer mediaPlayer;
    RelativeLayout RelativeBackGround1, RelativeBackGround2, RelativeBackGround3;

    Sound sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhbatdau);


        sound = new Sound();
        ar = new ArrayList<DataGame_DTO>();
        sqLiteDataGame = new SQLiteDataGame(getApplicationContext());
        createSqliteDataGame();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound_button);

        btnPlayBatDau = (Button)findViewById(R.id.btnPlayBatDau);
        btnAboutBatDau = (Button)findViewById(R.id.btnAboutBatDau);
        btnSoundBatDau = (Button)findViewById(R.id.btnSoundBatDau);

        RelativeBackGround1 = (RelativeLayout)findViewById(R.id.RelativeBackGround1);
        RelativeBackGround2 = (RelativeLayout)findViewById(R.id.RelativeBackGround2);
        RelativeBackGround3 = (RelativeLayout)findViewById(R.id.RelativeBackGround3);
        imgLoGoBatDau = (ImageView)findViewById(R.id.imgLogoBatDau);

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        String name = android.os.Build.MODEL;
        Log.d("test"," "+height+" "+width+" "+name);
        LinearLayout.LayoutParams paramsLinear = new LinearLayout.LayoutParams(width, (height * 10) / 100);
        RelativeBackGround1.setLayoutParams(paramsLinear);
        RelativeBackGround3.setLayoutParams(paramsLinear);

        paramsLinear = new LinearLayout.LayoutParams(width, (height * 80) / 100);
        RelativeBackGround2.setLayoutParams(paramsLinear);

        RelativeLayout.LayoutParams paramsRelative = new RelativeLayout.LayoutParams(RelativeBackGround2.getLayoutParams().width * 70 / 100, RelativeBackGround2.getLayoutParams().width * 70 / 100);
        paramsRelative.setMargins(RelativeBackGround2.getLayoutParams().width * 15 / 100, (RelativeBackGround2.getLayoutParams().height * 3) / 100, 0, 0);
        imgLoGoBatDau.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams(RelativeBackGround2.getLayoutParams().width * 50 / 100, RelativeBackGround2.getLayoutParams().height * 12 / 100);
        paramsRelative.setMargins(RelativeBackGround2.getLayoutParams().width * 25 / 100, RelativeBackGround2.getLayoutParams().height * 2 / 100, 0, 0);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.imgLogoBatDau);
        btnPlayBatDau.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams(RelativeBackGround2.getLayoutParams().width * 50 / 100, RelativeBackGround2.getLayoutParams().height * 12 / 100);
        paramsRelative.setMargins(RelativeBackGround2.getLayoutParams().width * 25 / 100, RelativeBackGround2.getLayoutParams().height * 4 / 100, 0, 0);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnPlayBatDau);
        btnAboutBatDau.setLayoutParams(paramsRelative);


        paramsRelative = new RelativeLayout.LayoutParams(RelativeBackGround2.getLayoutParams().width * 15 / 100, RelativeBackGround2.getLayoutParams().width * 9 / 100);
        paramsRelative.setMargins(width * 82 / 100, RelativeBackGround2.getLayoutParams().height / 100, 0, 0);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.RelativeBackGround1);
        btnSoundBatDau.setLayoutParams(paramsRelative);

        if(sound.load(sound.file).equals("false")){
            btnSoundBatDau.setBackgroundResource(R.drawable.sound_off);
        }
        else{
            btnSoundBatDau.setBackgroundResource(R.drawable.sound_on);
        }






        btnPlayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound.load(sound.file).equals("true")){
                    mediaPlayer.start();
                }
                finish();
                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
            }
        });

        btnAboutBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound.load(sound.file).equals("true")){
                    mediaPlayer.start();
                }

                finish();
                startActivity(new Intent(getApplicationContext(), manhinhAbout.class));
            }
        });



        btnSoundBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(changeText() == false){
                    btnSoundBatDau.setBackgroundResource(R.drawable.sound_off);
                }
                else{
                    btnSoundBatDau.setBackgroundResource(R.drawable.sound_on);
                }
            }
        });
    }

    public void createSqliteDataGame(){
        if(sqLiteDataGame.getAllDataGame().size() == 0){
            sqLiteDataGame.addDataGame();
            sound.CreateFolder();

        }
    }

    public boolean changeText(){
        String text = sound.load(sound.file);
        if(text.equals("true")){
            sound.save(sound.file, "false");
            return false;
        }
        else{
            sound.save(sound.file, "true");
            mediaPlayer.start();
            return true;
        }
    }

}

package com.example.administrator.ssd;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by Administrator on 6/3/2016.
 */
public class manhinhlevel extends AppCompatActivity {
    List<DataGame_DTO> arr;
    Button btnphai, btnLv1Level, btnLv2Level, btnLv3Level, btnLv4Level, btnLv5Level, btnLv6Level, btnLv7Level, btnLv8Level, btnLv9Level;
    Button[] arrButton = {btnLv1Level, btnLv2Level, btnLv3Level, btnLv4Level, btnLv5Level, btnLv6Level, btnLv7Level, btnLv8Level, btnLv9Level};
    RelativeLayout RelativeLayoutLevel;
    SQLiteDataGame sqLiteDataGame;
    int level, target, best, move;
    int height, width;
    float fromX, fromY, toX, toY;
    Sound sound;
    MediaPlayer mediaPlayer;
    static final int SWIPE_THRESHOLD = 130;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhlevel);

        sound = new Sound();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound_button);
        btnphai = (Button)findViewById(R.id.btnphai);
        btnLv1Level = (Button)findViewById(R.id.btnLv1Level);
        btnLv2Level = (Button)findViewById(R.id.btnLv2Level);
        btnLv3Level = (Button)findViewById(R.id.btnLv3Level);
        btnLv4Level = (Button)findViewById(R.id.btnLv4Level);
        btnLv5Level = (Button)findViewById(R.id.btnLv5Level);
        btnLv6Level = (Button)findViewById(R.id.btnLv6Level);
        btnLv7Level = (Button)findViewById(R.id.btnLv7Level);
        btnLv8Level = (Button)findViewById(R.id.btnLv8Level);
        btnLv9Level = (Button)findViewById(R.id.btnLv9Level);

        btnphai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
            }
        });

        RelativeLayoutLevel = (RelativeLayout)findViewById(R.id.RelativeLayoutLevel);


        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        RelativeLayout.LayoutParams paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.setMargins(width * 2 / 100, width * 10 / 100, width * 3 / 100, 0);
        btnLv1Level.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv1Level);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv2Level.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv2Level);

        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv3Level.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv1Level);
        paramsRelative.setMargins(width * 2 / 100, width * 10 / 100, width * 3 / 100, 0);
        btnLv4Level.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv2Level);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv4Level);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv5Level.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv3Level);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv5Level);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv6Level.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv4Level);
        paramsRelative.setMargins(width * 2 / 100, width * 10 / 100, width * 3 / 100, 0);
        btnLv7Level.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv5Level);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv7Level);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv8Level.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv6Level);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv8Level);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv9Level.setLayoutParams(paramsRelative);


        sqLiteDataGame = new SQLiteDataGame(getApplicationContext());
//        getmyIntent();

        arr = sqLiteDataGame.getAllDataGame();
        setBackGround();


        RelativeLayoutLevel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        if(getSwipe(diffX, diffY).equals("Left")){
                            finish();
                            startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                        }
                    }
                    catch (Exception ex) {}
                }
                return true;
            }
        });

        btnLv1Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {


                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }

                        if( checkOpenMap((int)btnLv1Level.getTag()) == true){
                            setIntent(1);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv2Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }

                        if( checkOpenMap((int)btnLv2Level.getTag()) == true){
                            setIntent(2);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv3Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }
                        if( checkOpenMap((int)btnLv3Level.getTag()) == true){
                            setIntent(3);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv4Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }
                        if( checkOpenMap((int)btnLv4Level.getTag()) == true){
                            setIntent(4);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv5Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }
                        if( checkOpenMap((int)btnLv5Level.getTag()) == true){
                            setIntent(5);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv6Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }
                        if( checkOpenMap((int)btnLv6Level.getTag()) == true){
                            setIntent(6);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv7Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }
                        if( checkOpenMap((int)btnLv7Level.getTag()) == true){
                            setIntent(7);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv8Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }
                        if( checkOpenMap((int)btnLv8Level.getTag()) == true){
                            setIntent(8);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv9Level.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();

                    Log.d("from x: ", ""+fromX);
                    Log.d("from y: ", ""+fromY);
                    Log.d("to x: ", ""+toX);
                    Log.d("to y: ", ""+toY);

                    if(Math.abs(fromX - toX) < 40 && Math.abs(fromY - toY) < 40){
                        if(sound.load(sound.file).equals("true")){
                            mediaPlayer.start();
                        }
                        if( checkOpenMap((int)btnLv9Level.getTag()) == true){
                            setIntent(9);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Left")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });
    }

    public void setBackGround(){
        arrButton[0] = btnLv1Level;
        arrButton[1] = btnLv2Level;
        arrButton[2] = btnLv3Level;
        arrButton[3] = btnLv4Level;
        arrButton[4] = btnLv5Level;
        arrButton[5] = btnLv6Level;
        arrButton[6] = btnLv7Level;
        arrButton[7] = btnLv8Level;
        arrButton[8] = btnLv9Level;
        int count = 0;
        int vitri = -1;
        for(int i = 0; i < arrButton.length; i++){
            if (sqLiteDataGame.getDataGame(i + 1).Star == 0) {
                arrButton[i].setBackgroundResource(R.drawable.levelrong);
                arrButton[i].setTag(R.drawable.levelrong);
                 count ++;
                 if(count == 1){
                     vitri = i;
                 }
            }
            else if (sqLiteDataGame.getDataGame(i + 1).Star == 1) {
                arrButton[i].setBackgroundResource(R.drawable.level0sao1);
                arrButton[i].setTag(R.drawable.level0sao1);
            }
            else if (sqLiteDataGame.getDataGame(i + 1).Star == 2) {
                arrButton[i].setBackgroundResource(R.drawable.level0sao2);
                arrButton[i].setTag(R.drawable.level0sao2);
            }
            else {

                arrButton[i].setBackgroundResource(R.drawable.level0sao3);
                arrButton[i].setTag(R.drawable.level0sao3);
            }
        }

        if(vitri == -1){
            return;
        }
        else{
            arrButton[vitri].setBackgroundResource(R.drawable.levelsao);
            arrButton[vitri].setTag(R.drawable.levelsao);
        }
    }

    public void setIntent(int level){
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        DataGame_DTO dataGame = new DataGame_DTO();
        dataGame = sqLiteDataGame.getDataGame(level);
        intent.putExtra("Level", dataGame.Level);
        intent.putExtra("Star", dataGame.Star);
        intent.putExtra("Best", dataGame.BestMove);
        startActivity(intent);
    }

    public String getSwipe(float a, float b) {
        String s = "";
        if (Math.abs(a) > Math.abs(b)) {
            if (Math.abs(a) > SWIPE_THRESHOLD) {
                if (a > 0) {
                    s = "Right";
                } else {
                    s = "Left";
                }
            }
        } else if (Math.abs(b) > SWIPE_THRESHOLD) {
            if (b > 0) {
                s = "Bottom";
            } else {
                s = "Top";
            }
        }
        return s;
    }

    boolean checkOpenMap(int res){
        if(res == (int)R.drawable.levelrong){
            return false;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        if (sound.load(sound.file).equals("true")) {
            mediaPlayer.start();
        }

        finish();
        startActivity(new Intent(getApplicationContext(), manhinhbatdau.class));
        super.onBackPressed();
    }
}

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

public class manhinhlevel2 extends AppCompatActivity {

    List<DataGame_DTO> arr;
    Button btntrai, btnLv10mhLevel, btnLv11mhLevel, btnLv12mhLevel, btnLv13mhLevel, btnLv14mhLevel, btnLv15mhLevel, btnLv16mhLevel, btnLv17mhLevel, btnLv18mhLevel;
    Button[] arrButton = {btnLv10mhLevel, btnLv11mhLevel, btnLv12mhLevel, btnLv13mhLevel, btnLv14mhLevel, btnLv15mhLevel, btnLv16mhLevel, btnLv17mhLevel, btnLv18mhLevel};
    RelativeLayout RelativeLayoutmhLevel;
    SQLiteDataGame sqLiteDataGame;
    int level, best, move;
    int height, width;
    float fromX, fromY, toX, toY;
    Sound sound;
    MediaPlayer mediaPlayer;
    static final int SWIPE_THRESHOLD = 130;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhlevel2);

        sound = new Sound();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound_button);

        btntrai = (Button)findViewById(R.id.btntrai);
        btnLv10mhLevel = (Button)findViewById(R.id.btnLv10mhLevel);
        btnLv11mhLevel = (Button)findViewById(R.id.btnLv11mhLevel);
        btnLv12mhLevel = (Button)findViewById(R.id.btnLv12mhLevel);
        btnLv13mhLevel = (Button)findViewById(R.id.btnLv13mhLevel);
        btnLv14mhLevel = (Button)findViewById(R.id.btnLv14mhLevel);
        btnLv15mhLevel = (Button)findViewById(R.id.btnLv15mhLevel);
        btnLv16mhLevel = (Button)findViewById(R.id.btnLv16mhLevel);
        btnLv17mhLevel = (Button)findViewById(R.id.btnLv17mhLevel);
        btnLv18mhLevel = (Button)findViewById(R.id.btnLv18mhLevel);
        btntrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
            }
        });

        RelativeLayoutmhLevel = (RelativeLayout)findViewById(R.id.RelativeLayoutmhLevel);


        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        RelativeLayout.LayoutParams paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.setMargins(width * 2 / 100, width * 10 / 100, width * 3 / 100, 0);
        btnLv10mhLevel.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
//        paramsRelative.addRule(RelativeLayout.LEFT_OF, R.id.btnLv1);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv10mhLevel);
//        paramsRelative.addRule(RelativeLayout.END_OF, R.id.btnLv1);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv11mhLevel.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv11mhLevel);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv12mhLevel.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv10mhLevel);
        paramsRelative.setMargins(width * 2 / 100, width * 10 / 100, width * 3 / 100, 0);
        btnLv13mhLevel.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv11mhLevel);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv13mhLevel);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv14mhLevel.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv12mhLevel);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv14mhLevel);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv15mhLevel.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv13mhLevel);
        paramsRelative.setMargins(width * 2 / 100, width * 10 / 100, width * 3 / 100, 0);
        btnLv16mhLevel.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv14mhLevel);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv16mhLevel);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv17mhLevel.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams((width * 27) / 100, (width * 27) / 100);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.btnLv15mhLevel);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLv17mhLevel);
        paramsRelative.setMargins(0, width * 10 / 100, width * 3 / 100, 0);
        btnLv18mhLevel.setLayoutParams(paramsRelative);


        sqLiteDataGame = new SQLiteDataGame(getApplicationContext());

        arr = sqLiteDataGame.getAllDataGame();
        setBackGround();


        RelativeLayoutmhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(getSwipe(diffX, diffY).equals("Right")){
                            finish();
                            startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                        }
                    }
                    catch (Exception ex) {}
                }
                return true;
            }
        });

        btnLv10mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv10mhLevel.getTag()) == true){
                            setIntent(10);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv11mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv11mhLevel.getTag()) == true){
                            setIntent(11);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv12mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv12mhLevel.getTag()) == true){
                            setIntent(12);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv13mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv13mhLevel.getTag()) == true){
                            setIntent(13);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv14mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv14mhLevel.getTag()) == true){
                            setIntent(14);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv15mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv15mhLevel.getTag()) == true){
                            setIntent(15);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv16mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv16mhLevel.getTag()) == true){
                            setIntent(16);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv17mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv17mhLevel.getTag()) == true){
                            setIntent(17);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                return true;
            }
        });

        btnLv18mhLevel.setOnTouchListener(new View.OnTouchListener() {
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
                        if(checkOpenMap((int)btnLv18mhLevel.getTag()) == true){
                            setIntent(18);
                        }
                    }
                    else{
                        try {
                            float diffX = toX - fromX;
                            float diffY = toY - fromY;
                            if(getSwipe(diffX, diffY).equals("Right")){
                                finish();
                                startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
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
        arrButton[0] = btnLv10mhLevel;
        arrButton[1] = btnLv11mhLevel;
        arrButton[2] = btnLv12mhLevel;
        arrButton[3] = btnLv13mhLevel;
        arrButton[4] = btnLv14mhLevel;
        arrButton[5] = btnLv15mhLevel;
        arrButton[6] = btnLv16mhLevel;
        arrButton[7] = btnLv17mhLevel;
        arrButton[8] = btnLv18mhLevel;

        int count = 0;
        int vitri = - 1;

        if(sqLiteDataGame.getDataGame(9).getStar() != 0){
            for(int i = 0; i < arrButton.length; i++){
                if (sqLiteDataGame.getDataGame(i + 10).Star == 0) {
                    arrButton[i].setBackgroundResource(R.drawable.levelrong);
                    arrButton[i].setTag(R.drawable.levelrong);
                    count ++;
                    if(count == 1){
                        vitri = i;
                    }
                }
                else if (sqLiteDataGame.getDataGame(i + 10).Star == 1) {
                    arrButton[i].setBackgroundResource(R.drawable.level0sao1);
                    arrButton[i].setTag(R.drawable.level0sao1);
                }
                else if (sqLiteDataGame.getDataGame(i + 10).Star == 2) {
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
        else{
            for(int i = 0; i < arrButton.length; i++){
                arrButton[i].setBackgroundResource(R.drawable.levelrong);
                arrButton[i].setTag(R.drawable.levelrong);
            }
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

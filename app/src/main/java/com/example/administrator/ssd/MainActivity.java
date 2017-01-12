package com.example.administrator.ssd;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.ssd.Customdialogclass.myOnClickListener;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public myOnClickListener myListener1;
    public myOnClickListener myListener2;
    public myOnClickListener myListener3;
    private Timer myTimer = new Timer();;
    AnimationSet animSet = new AnimationSet(true);
    AnimationSet animSet1 = new AnimationSet(true);
    TranslateAnimation anim;
    TranslateAnimation anim1;
    RotateAnimation ranim =new RotateAnimation(0, 360,
            Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);

    RotateAnimation ranim1 =new RotateAnimation(360, 0,
            Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);

    AddHinh addHinh;
    ImageView undo;
    Stack<String> s = new Stack();
    Stack<DiChuyen> st = new Stack();

    float fromX, fromY;
    float toX, toY;
    int SWIPE_THRESHOLD;
    String swipe;
    int a = 0, b = 0, c = 0;

    int mainStar, mainLevel, mainTarget, mainBest;
    int myLevel, myStar, myMove, myTarget;
    ImageView imgStarMain;
    TextView txtMoveMain, txtBestMain, txtLevelMain, txtTargetMain, txtTextMoveMain, txtTextTargetMain, txtTextBestMain, txtTextLevelMain;
    Button btnUndoMain, btnSoundMain, btnBackMain, btnRestartMain, btnSolveMain;
    RelativeLayout relativeLayout1Main, relativeLayout2Main, relativeLayout3Main, relativeLayout4Main, relativeLayout5Main;
    int countMove = 0;
    SQLiteDataGame sqLiteDataGame;
    int diembatdau;
    int openDBD; // hướng cái cổng của điểm bắt đầu
    MediaPlayer mediaPlayer, mediaIcon, mediaWinner;
    Sound sound;
    boolean checksolve = true;

    int height, width;
    int arrTarget[] = {1,9,8,7,6,6,8,4,6,10,6,3,5,6,7,8,6,7};

    String[] tenImage = {"rong", "rong", "rong", "rong",
                        "rong", "rong", "rong", "rong",
                        "rong", "rong", "rong", "rong",
                        "rong", "rong", "rong", "rong" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        SWIPE_THRESHOLD = width * 10 / 100;
        if(height==320 && width==240)
        {
            anim = new TranslateAnimation(0, width * 70 / 100, 0, height * 60 / 100);
            anim1 = new TranslateAnimation(0, -(width * 70 / 100), 0, height * 60 / 100);
        }
        else {
            anim = new TranslateAnimation(0, width * 60 / 100, 0, height * 50 / 100);
            anim1 = new TranslateAnimation(0, -(width * 60 / 100), 0, height * 50 / 100);
        }

        addHinh = new AddHinh();
        setArrayImage();
        addhinh();
        sound = new Sound();
        khoitaoanimation();
         animSet1.setAnimationListener(new Animation.AnimationListener() {
             @Override
             public void onAnimationStart(Animation animation) {

             }

             @Override
             public void onAnimationEnd(Animation animation) {

                     DisplayMetrics dm = new DisplayMetrics();
                     getWindowManager().getDefaultDisplay().getMetrics(dm);
                     int w = dm.widthPixels;
                     int h = dm.heightPixels;

                     sodem = 0;

                     if (sound.load(sound.file).equals("true")) {
                         mediaWinner.start();
                     }

                     Customdialogclass mydialog = new Customdialogclass(MainActivity.this, myListener1, myListener2, myListener3, height, myStar,width);
                     WindowManager.LayoutParams params = mydialog.getWindow().getAttributes();
                     params.y += height * 1 / 100;
                     mydialog.getWindow();
                     mydialog.show();

             }

             @Override
             public void onAnimationRepeat(Animation animation) {

             }
         });

        animSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int w = dm.widthPixels;
                int h = dm.heightPixels;

                sodem = 0;

                if(sound.load(sound.file).equals("true")){
                    mediaWinner.start();
                }

                Customdialogclass mydialog = new Customdialogclass(MainActivity.this, myListener1, myListener2, myListener3, height, myStar,width);
                WindowManager.LayoutParams params = mydialog.getWindow().getAttributes();
                params.y += height * 1 / 100;
                mydialog.getWindow();
                mydialog.show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        txtBestMain = (TextView)findViewById(R.id.txtBestMain);
        txtMoveMain = (TextView)findViewById(R.id.txtMoveMain);
        txtTargetMain = (TextView)findViewById(R.id.txtTargetMain);
        txtLevelMain = (TextView)findViewById(R.id.txtLevelMain);

        txtTextBestMain = (TextView)findViewById(R.id.txtTextBestMain);
        txtTextMoveMain = (TextView)findViewById(R.id.txtTextMoveMain);
        txtTextTargetMain = (TextView)findViewById(R.id.txtTextTargetMain);
        txtTextLevelMain = (TextView)findViewById(R.id.txtTextLevelMain);

        imgStarMain = (ImageView)findViewById(R.id.imgStarMain);
        btnUndoMain = (Button)findViewById(R.id.btnUndoMain);
        btnSoundMain = (Button)findViewById(R.id.btnSoundMain);
        btnBackMain = (Button)findViewById(R.id.btnBackMain);
        btnRestartMain = (Button)findViewById(R.id.btnRestartMain);
        btnSolveMain = (Button)findViewById(R.id.btnSolveMain);

        relativeLayout1Main = (RelativeLayout)findViewById(R.id.RelativeLayout1Main);
        relativeLayout2Main = (RelativeLayout)findViewById(R.id.RelativeLayout2Main);
        relativeLayout3Main = (RelativeLayout)findViewById(R.id.RelativeLayout3Main);
        relativeLayout4Main = (RelativeLayout)findViewById(R.id.RelativeLayout4Main);
        relativeLayout5Main = (RelativeLayout)findViewById(R.id.RelativeLayout5Main);




        //relativeLayout4Main
        RelativeLayout.LayoutParams paramsRelative = new RelativeLayout.LayoutParams(width, height * 20 / 100 );
        relativeLayout4Main.setLayoutParams(paramsRelative);

        //Relative1 gồm Move, Best, Target
        paramsRelative = new RelativeLayout.LayoutParams(width * 22 / 100, height * 14 / 100 );
        paramsRelative.setMargins(width * 4 / 100,(height * 25 / 100) * 11 / 100, 0 , 0);
        relativeLayout1Main.setLayoutParams(paramsRelative);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
        mediaIcon = MediaPlayer.create(getApplicationContext(), R.raw.sound_button);
        mediaWinner = MediaPlayer.create(getApplicationContext(), R.raw.winning);


        String name = android.os.Build.MODEL;
        String name3="GT-I9500";
        if(height==320 && width ==240)
        {
            txtTextMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
            txtMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
            txtTextTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
            txtTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
            txtTextBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
            txtBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);


            txtBestMain.setWidth((int)((width * 22 / 100)*.5));
            txtTextBestMain.setWidth((int)((width * 22 / 100)*.5));
            txtTargetMain.setWidth((int)((width * 22 / 100)*.5));
            txtTextTargetMain.setWidth((int)((width * 22 / 100)*.5));
        }
        else if(name.equals(name3))
        {
            txtMoveMain.setHeight(40); txtTextMoveMain.setHeight(40);
            txtTextMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            txtMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            txtTextTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            txtTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            txtTextBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            txtBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

            txtBestMain.setWidth((int)((width * 22 / 100)*.5));
            txtTextBestMain.setWidth((int)((width * 22 / 100)*.5));
            txtTargetMain.setWidth((int)((width * 22 / 100)*.5));
            txtTextTargetMain.setWidth((int)((width * 22 / 100)*.5));

        }

        else if(height <= 500){
            txtTextMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            txtMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            txtTextTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            txtTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            txtTextBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            txtBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        }

        else if(height > 500 && height <= 900){
            txtTextMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            txtMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            txtTextTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            txtTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            txtTextBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            txtBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        }
        else if( height > 900 && height < 1270){
            txtTextMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            txtMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            txtTextTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            txtTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            txtTextBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            txtBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        }
        else if( height >= 1270 && height < 1920){
            txtTextMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            txtMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            txtTextTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            txtTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            txtTextBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            txtBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }
        else if( height >= 1920){
            txtTextMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            txtMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            txtTextTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            txtTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            txtTextBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            txtBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        }
        else {
            txtTextMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            txtMoveMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            txtTextTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            txtTargetMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            txtTextBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            txtBestMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }

        //Image ngôi sao
        paramsRelative = new RelativeLayout.LayoutParams(width * 32 / 100, height * 16 / 100);
        paramsRelative.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imgStarMain.setLayoutParams(paramsRelative);


        //Relative2 gồm Level
        paramsRelative = new RelativeLayout.LayoutParams(width * 13 / 100, (height / 4) * 27 / 100);
//        paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_END);
        paramsRelative.setMargins(width * 83 / 100, (height * 25 / 100) * 42 / 100, 0, 0);
        relativeLayout2Main.setLayoutParams(paramsRelative);
        if(width==240 && height==320)
        {
            txtTextLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
            txtLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
        }
        else if(name.equals(name3)){
            txtLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            txtTextLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        }
        else if(height <= 900) {
            txtTextLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            txtLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        }

        else if( height > 900 && height <= 1280){
            txtTextLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            txtLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        }

        else if( height > 1280){
            txtTextLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            txtLevelMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }


        //Relative3 gồm 16 image

        int saveWidth = Math.abs(height / 2 - width);

        if(height==320 && width ==240){
            paramsRelative = new RelativeLayout.LayoutParams(height * 60 / 100 , height * 60 / 100);
            paramsRelative.setMargins(saveWidth / 3, height * 20 / 100, 0, 0);
            relativeLayout3Main.setLayoutParams(paramsRelative);

        }else
        {
            paramsRelative = new RelativeLayout.LayoutParams(height * 50 / 100 , height * 50 / 100);
            paramsRelative.setMargins(saveWidth / 2, height * 26 / 100, 0, 0);
            relativeLayout3Main.setLayoutParams(paramsRelative);
        }


        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.setMargins( (relativeLayout3Main.getLayoutParams().height * 3 / 100), (relativeLayout3Main.getLayoutParams().height * 3 / 100), 0, 0);
        addHinh.arrImageView[0].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img0Main);
        paramsRelative.setMargins( 0, (relativeLayout3Main.getLayoutParams().height * 3 / 100), 0, 0);
        addHinh.arrImageView[1].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img1Main);
        paramsRelative.setMargins( 0, (relativeLayout3Main.getLayoutParams().height * 3 / 100), 0, 0);
        addHinh.arrImageView[2].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img2Main);
        paramsRelative.setMargins( 0, (relativeLayout3Main.getLayoutParams().height * 3 / 100), 0, 0);
        addHinh.arrImageView[3].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img0Main);
        paramsRelative.setMargins( (relativeLayout3Main.getLayoutParams().height * 3 / 100), 0, 0, 0);
        addHinh.arrImageView[4].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img1Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img4Main);
        addHinh.arrImageView[5].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img2Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img5Main);
        addHinh.arrImageView[6].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img3Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img6Main);
        addHinh.arrImageView[7].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img4Main);
        addHinh.arrImageView[8].hinh.setLayoutParams(paramsRelative);
        paramsRelative.setMargins( (relativeLayout3Main.getLayoutParams().height * 3 / 100), 0, 0, 0);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img5Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img8Main);
        addHinh.arrImageView[9].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img6Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img9Main);
        addHinh.arrImageView[10].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img7Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img10Main);
        addHinh.arrImageView[11].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img8Main);
        addHinh.arrImageView[12].hinh.setLayoutParams(paramsRelative);
        paramsRelative.setMargins( (relativeLayout3Main.getLayoutParams().height * 3 / 100), 0, 0, 0);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img9Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img12Main);
        addHinh.arrImageView[13].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img10Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img13Main);
        addHinh.arrImageView[14].hinh.setLayoutParams(paramsRelative);

        paramsRelative = new RelativeLayout.LayoutParams( (relativeLayout3Main.getLayoutParams().height * 94 / 100)  / 4, (relativeLayout3Main.getLayoutParams().height * 94 / 100) / 4);
        paramsRelative.addRule(RelativeLayout.BELOW, R.id.img11Main);
        paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.img14Main);
        addHinh.arrImageView[15].hinh.setLayoutParams(paramsRelative);

//      Button Icon Sound
        paramsRelative = new RelativeLayout.LayoutParams(width * 8 / 100, width * 8 / 100);
        paramsRelative.setMargins(width * 88 / 100, width * 2 / 100, 0, 0);
        if(sound.load(sound.file).equals("true")){
            btnSoundMain.setBackgroundResource(R.drawable.icon_sound_on);
        }
        else{
            btnSoundMain.setBackgroundResource(R.drawable.icon_sound_off);
        }
        btnSoundMain.setLayoutParams(paramsRelative);


        //relativeLayout5Main gồm 4 button: back, restart, undo, solve.
        paramsRelative = new RelativeLayout.LayoutParams(width, height * 15 / 100);
//        paramsRelative.setMargins(0, height * 75 / 100, 0, 0);
        paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayout5Main.setLayoutParams(paramsRelative);

        //Button back
        if(height==320 && width==240)
        {
            paramsRelative = new RelativeLayout.LayoutParams(relativeLayout5Main.getLayoutParams().height * 50 / 100, relativeLayout5Main.getLayoutParams().height * 55 / 100);
            paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsRelative.setMargins(relativeLayout5Main.getLayoutParams().height * 60 / 100, 0, 0, relativeLayout5Main.getLayoutParams().height * 15 / 100);
            btnBackMain.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayout5Main.getLayoutParams().height * 69 / 100, relativeLayout5Main.getLayoutParams().height * 60 / 100);
            paramsRelative.setMargins(relativeLayout5Main.getLayoutParams().height * 50 / 100, 0, 0, relativeLayout5Main.getLayoutParams().height * 14 / 100);
            paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnBackMain);
            btnRestartMain.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayout5Main.getLayoutParams().height * 55 / 100, relativeLayout5Main.getLayoutParams().height * 55 / 100);
            paramsRelative.setMargins(relativeLayout5Main.getLayoutParams().height * 50 / 100, 0, 0, relativeLayout5Main.getLayoutParams().height * 14 / 100);
            paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnRestartMain);
            btnUndoMain.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayout5Main.getLayoutParams().height * 50 / 100, relativeLayout5Main.getLayoutParams().height * 55 / 100);
            paramsRelative.setMargins(relativeLayout5Main.getLayoutParams().height * 50 / 100, 0, 0, relativeLayout5Main.getLayoutParams().height * 16 / 100);
            paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnUndoMain);
            btnSolveMain.setLayoutParams(paramsRelative);
        }
        else {
            paramsRelative = new RelativeLayout.LayoutParams(relativeLayout5Main.getLayoutParams().height * 50 / 100, relativeLayout5Main.getLayoutParams().height * 50 / 100);
            paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsRelative.setMargins(relativeLayout5Main.getLayoutParams().height * 39 / 100, 0, 0, relativeLayout5Main.getLayoutParams().height * 15 / 100);
            btnBackMain.setLayoutParams(paramsRelative);
            paramsRelative = new RelativeLayout.LayoutParams(relativeLayout5Main.getLayoutParams().height * 60 / 100, relativeLayout5Main.getLayoutParams().height * 54 / 100);
            paramsRelative.setMargins(relativeLayout5Main.getLayoutParams().height * 37 / 100, 0, 0, relativeLayout5Main.getLayoutParams().height * 14 / 100);
            paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnBackMain);
            btnRestartMain.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayout5Main.getLayoutParams().height * 49 / 100, relativeLayout5Main.getLayoutParams().height * 52 / 100);
            paramsRelative.setMargins(relativeLayout5Main.getLayoutParams().height * 37 / 100, 0, 0, relativeLayout5Main.getLayoutParams().height * 14 / 100);
            paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnRestartMain);
            btnUndoMain.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayout5Main.getLayoutParams().height * 50 / 100, relativeLayout5Main.getLayoutParams().height * 50 / 100);
            paramsRelative.setMargins(relativeLayout5Main.getLayoutParams().height * 37 / 100, 0, 0, relativeLayout5Main.getLayoutParams().height * 16 / 100);
            paramsRelative.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnUndoMain);
            btnSolveMain.setLayoutParams(paramsRelative);
        }





        sqLiteDataGame = new SQLiteDataGame(getApplicationContext());
        Bundle bd = getIntent().getExtras();
        if(bd != null ){
            mainLevel = bd.getInt("Level");
            mainStar = bd.getInt("Star");
            mainBest = bd.getInt("Best");

            txtLevelMain.setText(""+mainLevel);
            txtTargetMain.setText(""+arrTarget[mainLevel - 1]);
            txtBestMain.setText(""+mainBest);

            if(mainStar == 0){
                imgStarMain.setImageResource(R.drawable.khongsaoplaygame);
            }
            else if(mainStar == 1){
                imgStarMain.setImageResource(R.drawable.motsaoplaygame);
            }
            else if(mainStar == 2){
                imgStarMain.setImageResource(R.drawable.haisaoplaygame);
            }
            else if(mainStar == 3){
                imgStarMain.setImageResource(R.drawable.basaoplaygame);
            }
        }

        //Button level
        myListener1 = new myOnClickListener() {
            @Override
            public void onButtonClick() {
                if(sound.load(sound.file).equals("true")){
                    mediaIcon.start();
                }

                finish();
                if(mainLevel<=9)
                    startActivity(new Intent(getApplicationContext(), manhinhlevel.class));
                else
                    startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));
            }
        };

        //Button replay
        myListener2 = new myOnClickListener() {
            @Override
            public void onButtonClick() {
                if(sound.load(sound.file).equals("true")){
                    mediaIcon.start();
                }

                finish();
                setIntent(mainLevel);
            }
        };

        //Button next
        myListener3 = new myOnClickListener() {
            @Override
            public void onButtonClick() {
                if(sound.load(sound.file).equals("true")){
                    mediaIcon.start();
                }

                if(mainLevel != 18){
                    finish();
                    setIntent(mainLevel + 1);

                }
                else{
                    finish();
                    startActivity(new Intent(getApplicationContext(), manhinhlevel2.class));

                }
            }
        };


        addHinh.tenImage = setTenImage(mainLevel);
        addHinh.addMan1();

        //vi tri điểm bắt đầu
        diembatdau=FindStart();
        //giá trị open2 vi tri diem bat dau
        openDBD=addHinh.arrImageView[diembatdau].open2;


        btnSoundMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sodem == 0 && animationcheck==0){
                    if(changeText() == false){
                        btnSoundMain.setBackgroundResource(R.drawable.icon_sound_off);
                    }
                    else{
                        btnSoundMain.setBackgroundResource(R.drawable.icon_sound_on);
                    }
                }
            }
        });


        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound.load(sound.file).equals("true")){
                    mediaIcon.start();
                }
                if(sodem == 0&& animationcheck==0) {
                    Log.d("abc: ", "" + mainLevel);
                    Intent intent;
                    if (mainLevel <= 9) {
                        finish();
                        intent = new Intent(getApplicationContext(), manhinhlevel.class);
                    } else {
                        finish();
                        intent = new Intent(getApplicationContext(), manhinhlevel2.class);
                    }
                    startActivity(intent);
                }
            }
        });

        btnRestartMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound.load(sound.file).equals("true")){
                    mediaIcon.start();
                }
                if(sodem == 0 && animationcheck==0) {
                    addHinh.addMan1();
                    txtMoveMain.setText("0");
                    countMove = 0;
                    st.clear();
                    s.clear();
                }
            }
        });

        btnUndoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound.load(sound.file).equals("true")){
                    mediaIcon.start();
                }
                if(sodem == 0 && animationcheck==0) {
                    if(countMove == 0){
                        return;
                    }
                    countMove--;
                    txtMoveMain.setText(""+countMove);
                    undo();
                }
            }
        });



        btnSolveMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound.load(sound.file).equals("true")){
                    mediaIcon.start();
                }

                if(sodem == 0 && animationcheck==0){

                    if(checksolve == true){
                        checksolve = false;
                        addHinh.addMan1();
                        txtMoveMain.setText("0");
                        countMove = 0;
                        st.clear();
                        s.clear();

                        setSolve(Integer.valueOf(txtLevelMain.getText().toString()));
                        myTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                TimerMethod();
                            }
                        }, 0, 500);
                    }
//                setSolve(Integer.valueOf(txtLevel.getText().toString()));
                }
            }
        });

        //Bắt sự kiện khi di chuyển ô hình
        addHinh.arrImageView[0].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 0;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[1].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 1;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[2].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 2;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[3].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 3;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[4].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 4;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[5].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 5;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[6].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 6;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    }
                    catch (Exception ex) {}
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[7].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 7;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[8].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 8;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[9].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 9;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[10].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 10;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[11].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 11;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[12].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 12;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[13].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 13;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[14].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 14;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
        addHinh.arrImageView[15].hinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 15;
                    fromX = event.getX();
                    fromY = event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    toX = event.getX();
                    toY = event.getY();
                    try {
                        float diffX = toX - fromX;
                        float diffY = toY - fromY;
                        swipe = getSwipe(diffX, diffY);
                        Log.d("swipe ", "" + swipe);
                    } catch (Exception ex) {
                    }
                    if (kiemtraHopLe(a, swipe) == true) {
                        setHinhThayDoi(a, b);
                    }
                }

                return true;
            }
        });
    }

    public void setArrayImage() {
        addHinh.arrImageView[0].hinh = (ImageView) findViewById(R.id.img0Main);
        addHinh.arrImageView[1].hinh = (ImageView) findViewById(R.id.img1Main);
        addHinh.arrImageView[2].hinh = (ImageView) findViewById(R.id.img2Main);
        addHinh.arrImageView[3].hinh = (ImageView) findViewById(R.id.img3Main);
        addHinh.arrImageView[4].hinh = (ImageView) findViewById(R.id.img4Main);
        addHinh.arrImageView[5].hinh = (ImageView) findViewById(R.id.img5Main);
        addHinh.arrImageView[6].hinh = (ImageView) findViewById(R.id.img6Main);
        addHinh.arrImageView[7].hinh = (ImageView) findViewById(R.id.img7Main);
        addHinh.arrImageView[8].hinh = (ImageView) findViewById(R.id.img8Main);
        addHinh.arrImageView[9].hinh = (ImageView) findViewById(R.id.img9Main);
        addHinh.arrImageView[10].hinh = (ImageView) findViewById(R.id.img10Main);
        addHinh.arrImageView[11].hinh = (ImageView) findViewById(R.id.img11Main);
        addHinh.arrImageView[12].hinh = (ImageView) findViewById(R.id.img12Main);
        addHinh.arrImageView[13].hinh = (ImageView) findViewById(R.id.img13Main);
        addHinh.arrImageView[14].hinh = (ImageView) findViewById(R.id.img14Main);
        addHinh.arrImageView[15].hinh = (ImageView) findViewById(R.id.img15Main);
    }

    public void addhinh() {
        for (int i = 0; i < 16; i++)
            addHinh.getanh(addHinh.arrImageView[i], tenImage[i]);
    }

    //Kiểm tra swipe và gán giá trị
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

    //Thay đổi 2 hình a và b
    public void setHinhThayDoi(int a, int b) {


        txtMoveMain.setText("" + (++countMove));

        DiChuyen d = new DiChuyen(a, b);
        st.push(d);

        hoanvi(a, b);

        if (checkwin(openDBD, diembatdau) == 1)
            Log.d("swipe ", "" + "1");
        else if (checkwin(openDBD, diembatdau) == 2) {
            myTimer.cancel();
            Log.d("swipe ", "" + "win gòi đừng có kéo nữa");
            myLevel = Integer.valueOf(txtLevelMain.getText().toString());
            myMove = Integer.valueOf(txtMoveMain.getText().toString());
            myTarget = Integer.valueOf(txtTargetMain.getText().toString());
            Intent intent = new Intent(getApplicationContext(), manhinhlevel.class);
            int saveMove, saveStar;

            if (myMove <= myTarget) {
                myStar = 3;
                saveStar = myStar;
            }
            else if ( (myMove - myTarget > 0) && (myMove - myTarget <= 2) ){
                myStar = 2;
                saveStar = myStar;
            }
            else if(myMove - myTarget > 2)
            {
                myStar = 1;
                saveStar = myStar;
            }


            if (mainStar < myStar) {
                intent.putExtra("playStar", myStar);
                saveStar = myStar;
            } else {
                intent.putExtra("playStar", mainStar);
                saveStar = mainStar;
            }

            if (mainBest == 0) {
                intent.putExtra("playMove", myMove);
                saveMove = myMove;
            } else {
                if (mainBest > myMove) {
                    intent.putExtra("playMove", myMove);
                    saveMove = myMove;
                } else {
                    intent.putExtra("playMove", mainBest);
                    saveMove = mainBest;
                }
            }

            sqLiteDataGame.updateDataGame(mainLevel, saveStar, saveMove);
            duyetduongdi(openDBD,diembatdau);
            nutconlai();
            runanimation();
            if(animationcheck==0) {
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int w = dm.widthPixels;
                int h = dm.heightPixels;

                sodem = 0;

                if (sound.load(sound.file).equals("true")) {
                    mediaWinner.start();
                }

                Customdialogclass mydialog = new Customdialogclass(MainActivity.this, myListener1, myListener2, myListener3, height, myStar,width);
                WindowManager.LayoutParams params = mydialog.getWindow().getAttributes();
                if(height==320 && width==240)
                {
                    mydialog.show();
                }else
                {
                    params.y += height * 1 / 100;
                    mydialog.getWindow();
                    mydialog.show();
                }
            }


        } else if (checkwin(openDBD, diembatdau) == 3)
            Log.d("swipe ", "" + "3");
        else if (checkwin(openDBD, diembatdau) == 4)
            Log.d("swipe ", "" + "4");
        else if (checkwin(openDBD, diembatdau) == 5)
            Log.d("swipe ", "" + "5");
        else if (checkwin(openDBD, diembatdau) == 6)
            Log.d("swipe ", "" + "6");


    }

    void undo()
    {
        if(st.empty()==false)
        {
            DiChuyen e=new DiChuyen();
             e = st.pop();
            int vitri1=e.Vitria;
            int vitri2=e.Vitrib;

            hoanvi(vitri1,vitri2);

        }
        else
            Log.d("swipe ", "" + "trống không");

    }

    //Hoán vị 2 hình a và b
    public void hoanvi(int a, int b)
    {
        if(sound.load(sound.file).equals("true")){
            mediaPlayer.start();
        }

        //lấy hình của vị trí a
        int hinhA = (int) addHinh.arrImageView[a].hinh.getTag();
        //lấy hình của vị trí b
        int hinhB = (int) addHinh.arrImageView[b].hinh.getTag();
        //set hình ở ô click đầu tiên
        addHinh.arrImageView[a].hinh.setImageResource(hinhB);
        addHinh.arrImageView[a].hinh.setTag(hinhB);
        //set hình ở ô click lần 2
        addHinh.arrImageView[b].hinh.setImageResource(hinhA);
        addHinh.arrImageView[b].hinh.setTag(hinhA);

        int tam=addHinh.arrImageView[a].open1;
        addHinh.arrImageView[a].open1=addHinh.arrImageView[b].open1;
        addHinh.arrImageView[b].open1=tam;

        tam=addHinh.arrImageView[a].open2;
        addHinh.arrImageView[a].open2=addHinh.arrImageView[b].open2;
        addHinh.arrImageView[b].open2=tam;

    }

    //Kiểm tra các hình rỗng
    public boolean kiemtraImageRong(int a, int b) {
        boolean ischeck = false;
        try {
            if ( (int) addHinh.arrImageView[a].hinh.getTag() == R.drawable.bt1 || (int) addHinh.arrImageView[b].hinh.getTag() == R.drawable.bt1 ||
                 (int) addHinh.arrImageView[a].hinh.getTag() == R.drawable.bt2 || (int) addHinh.arrImageView[b].hinh.getTag() == R.drawable.bt2 ||
                 (int) addHinh.arrImageView[a].hinh.getTag() == R.drawable.bt3 || (int) addHinh.arrImageView[b].hinh.getTag() == R.drawable.bt3 ||
                 (int) addHinh.arrImageView[a].hinh.getTag() == R.drawable.bt4 || (int) addHinh.arrImageView[b].hinh.getTag() == R.drawable.bt4 ||
                 (int) addHinh.arrImageView[a].hinh.getTag() == R.drawable.bt5 || (int) addHinh.arrImageView[b].hinh.getTag() == R.drawable.bt5 ||
                 (int) addHinh.arrImageView[a].hinh.getTag() == R.drawable.bt6 || (int) addHinh.arrImageView[b].hinh.getTag() == R.drawable.bt6 ||
                 (int) addHinh.arrImageView[a].hinh.getTag() == R.drawable.rong||(int) addHinh.arrImageView[b].hinh.getTag() == R.drawable.rong)
                ischeck = true;
            else  {
                ischeck = false;
            }

        } catch (Exception ex) {
        }
        return ischeck;
    }

    //kiểm tra tính hợp lệ khi thay đổi giữa 2 hình
    public boolean kiemtraHopLe(int a, String swipe) {
        if(sodem != 0 || animationcheck!=0){
            return false;
        }

        if (swipe == "Left") {
            b = a - 1;
        } else if (swipe == "Right") {
            b = a + 1;
        } else if (swipe == "Top") {
            b = a - 4;
        } else if (swipe == "Bottom") {
            b = a + 4;
        }

        boolean ischeck = false;
        if (kiemtraImageRong(a, b) == true && (int) addHinh.arrImageView[b].hinh.getTag() == R.drawable.abc ) {
            try {
                if (a + 1 == b || a - 1 == b || a + 4 == b || a - 4 == b) {
                    if (a % 4 == 0 && b == a - 1) {
                        ischeck = false;
                    } else if ((a + 1) % 4 == 0 && b == a + 1) {
                        ischeck = false;
                    } else {
                        ischeck = true;
                    }
                }
            } catch (Exception ex) {}
        }
        return ischeck;
    }


    int checkwin(int cong,int vitri) // để kiểu int vì nó trả về đc nhiều giá trị để dễ dễ dàng theo dõi code chỗ đó có hoạt động ko
    {
        int k = cong;
        int a = vitri;
        int lap = 0;
        c = 0;
        while (lap != 16) { //  lặp cho đến khi return mới thôi
            lap++;
            if (k == 1 && a % 4 != 0)
                c = a - 1;
            else if (k == 2 && a - 4 >= 0)
                c = a - 4;
            else if (k == 3 && a + 1 % 4 != 0)
                c = a + 1;
            else if (k == 4 && a + 4 < 16)
                c = a + 4;
            int congC1 = addHinh.arrImageView[c].open1;
            int congC2 = addHinh.arrImageView[c].open2;
            if (congC1 == 0 && congC2 == 0)
                return 1;
            else if (congC1 == 0 && congC2 != 0)
            {
                if (
                        (k==1 && congC2==3) || (k==2 && congC2==4) || (k==3 && congC2==1) || (k==4 && congC2==2)
                        ) {
                    return 2;
                }
                else
                    return 5;
            }
            else if (congC1 != 0 && congC2 != 0) {
                if (
                        ( k == 1 && (congC1 == 3 || congC2 == 3)) ||(
                                k == 2 && (congC1 == 4 || congC2 == 4)) ||
                                (  k == 3 && (congC1 == 1 || congC2 == 1)) ||
                                (   k == 4 && (congC1 == 2 || congC2 == 2))
                        ) {
                    int vitrike = congC1;
                    if ((k == 1 && vitrike == 3) || (k == 2 && vitrike == 4) || (k == 3 && vitrike == 1) || (k == 4 && vitrike == 2))
                        vitrike = addHinh.arrImageView[c].open2;
                    // cập nhật lại cho lần duyệt sau
                    k = vitrike;
                    a = c;
//                    if((k==4 && a+4>=16) || (k==1 && a<=3) || (k==2 && a%4==0) || (k==3 && (a+1)%4==0))//check xem dường đi có hướng về vách tường ko
//                        return 6;

                } else
                    return 3;
            }
        }
        return 4;
    }

    @Override
    public void onBackPressed() {
        if(sodem == 0 && animationcheck == 0) {
            super.onBackPressed();

            if (sound.load(sound.file).equals("true")) {
                mediaIcon.start();
            }
            Intent intent;
            if (mainLevel <= 9) {
                finish();
                intent = new Intent(getApplicationContext(), manhinhlevel.class);
            } else {
                finish();
                intent = new Intent(getApplicationContext(), manhinhlevel2.class);
            }
            startActivity(intent);
        }
    }

    public String[] setTenImage(int level){
        String []s = {""};
        if(level == 1){
            String[] tenImage = {"s3", "rong", "rong", "rong",
                    "bt2", "rong", "rong", "rong",
                    "bt2", "bt1", "rong", "rong",
                    "bt3", "abc", "k1", "f2" };
            s = tenImage;
        }
        else if(level == 2){
            String[] tenImage = {
                    "rong","s3","bt3","bt1",
                    "rong","bt2","bt6","abc",
                    "f3","bt1","bt2","bt4",
                    "k5","k1","abc","bt1"
            };
            s = tenImage;
        }
        else if(level == 3){
            String[] tenImage = {"k3", "s2", "rong", "bt1",
                    "abc", "bt1", "bt2", "rong",
                    "k5", "abc", "abc", "k4",
                    "rong", "rong", "rong", "f4" };
            s = tenImage;
        }
        else if(level == 4){
            String[] tenImage = {"bt5", "abc", "rong", "rong",
                    "rong", "rong", "bt1", "k4",
                    "rong", "abc", "bt2", "abc",
                    "s1", "bt4", "bt2", "f4" };
            s = tenImage;
        }
        else if(level == 5){
            String[] tenImage = {"s1", "bt3", "rong", "rong",
                    "rong", "abc", "bt1", "bt6",
                    "abc", "bt6", "f1", "k6",
                    "rong", "rong", "rong", "rong" };
            s = tenImage;
        }
        else if(level == 6){
            String[] tenImage = {"f3", "bt1", "bt1", "abc",
                    "bt3", "rong", "abc", "k4",
                    "rong", "rong", "s3", "k2",
                    "rong", "bt3", "bt4", "abc" };
            s = tenImage;
        }
        else if(level == 7){
            String[] tenImage = {"s1", "bt6", "rong", "rong",
                    "rong", "abc", "abc", "bt3",
                    "rong", "rong", "bt6", "bt6",
                    "rong", "bt3", "rong", "f4" };
            s = tenImage;
        }
        else if(level == 8){
            String[] tenImage = {"bt6", "abc", "rong", "rong",
                    "bt5", "bt2", "bt2", "rong",
                    "s4", "bt2", "rong", "rong",
                    "rong", "k5", "f2", "bt1" };
            s = tenImage;
        }
        else if(level == 9){
            String[] tenImage = {"bt5", "bt5", "s2", "rong",
                    "abc", "k6", "abc", "bt6",
                    "abc", "bt3", "abc", "bt1",
                    "bt1", "abc", "f1", "k6" };
            s = tenImage;
        }
        else if(level == 10){
            String[] tenImage = {
                    "abc","abc","abc","abc",
                    "s3","bt1","bt6","abc",
                    "bt3","bt3","bt2","bt5",
                    "bt2","bt4","bt6","f4"
            };
            s = tenImage;
        }
        else if(level == 11){
            String[] tenImage = {
                    "bt5","bt5","bt1","bt6",
                    "bt2","bt3","bt1","bt4",
                    "bt2","abc","abc","bt6",
                    "s4","bt3","abc","f4"
            };
            s = tenImage;
        }
        else if(level == 12){
            String[] tenImage = {
                    "bt5","bt4","bt6","abc",
                    "bt3","bt5","bt2","bt2",
                    "bt6","abc","abc","bt2",
                    "bt3","s4","f4","bt1"
            };
            s = tenImage;
        }
        else if(level == 13){
            String[] tenImage = {
                    "s3","bt2","bt6","abc",
                    "k5","bt5","abc","bt4",
                    "f3","bt2","bt2","rong",
                    "k5","abc","bt4","abc"
            };
            s = tenImage;
        }
        else if(level == 14){
            String[] tenImage ={
                    "bt5","bt1","abc","bt6",
                    "bt3","bt4","bt5","f4",
                    "abc","abc","bt6","bt2",
                    "rong","s4","bt4","abc"
            };
            s = tenImage;
        }
        else if(level == 15){
            String[] tenImage = {
                    "abc","bt3","abc","bt5",
                    "bt5","abc","bt6","s2",
                    "bt2","bt1","bt4","f2",
                    "bt3","bt5","abc","bt4"
            };
            s = tenImage;
        }
        else if(level == 16){
            String[] tenImage = {
                    "k3","bt1","abc","f2",
                    "k5","abc","bt6","bt1",
                    "abc","rong","abc","s2",
                    "bt3","bt2","bt5","bt4"
            };
            s = tenImage;
        }
        else if(level == 17){
            String[] tenImage = {
                    "s1","abc","bt6","bt6",
                    "bt5","abc","bt4","k4",
                    "bt2","k5","abc","bt2",
                    "rong","f1","bt1","bt4"
            };
            s = tenImage;
        }
        else if(level == 18){
            String[] tenImage = {
                    "bt1","bt5","abc","k4",
                    "bt3","bt1","k4","bt2",
                    "k3","bt1","k6","k2",
                    "k5","abc","s2","f4"
            };
            s = tenImage;
        }
        return s;
    }

    int FindStart()
    {
        for(int i=0;i<16;i++)
            if(addHinh.arrImageView[i].open1==0 && addHinh.arrImageView[i].open2!=0)
                return i;
        return 0; // không tìm thấy
    }

    public boolean changeText(){
        String text = sound.load(sound.file);
        if(text.equals("true")){
            sound.save(sound.file, "false");
            return false;
        }
        else{
            sound.save(sound.file, "true");
            mediaIcon.start();
            return true;
        }
    }

    int[] solve;

    public void setSolve(int level){
        switch (level){
            case 1: solve = new int[]{9, 13}; break;
            case 2: solve = new int[]{14,15,11,15,6,7,10,11,9,10,5,9,2,6,5,6,10,6}; break;
            case 3: solve = new int[]{5, 9, 5, 6, 4, 5, 7, 6, 6, 5, 3, 7, 6, 7, 6, 10}; break;
            case 4: solve = new int[]{10, 11, 0, 1, 0, 4, 5, 4, 1, 5, 14, 10, 10, 9}; break;
            case 5: solve = new int[]{4, 8, 1, 5, 5, 4, 9, 5, 5, 1, 4, 5}; break;
            case 6: solve = new int[]{14 , 15, 14, 13, 2, 6, 13, 9, 5, 9, 1, 5}; break;
            case 7: solve = new int[]{7, 6, 6, 5, 10, 6, 9, 10, 9, 13, 13, 14, 10, 14, 9, 10}; break;
            case 8: solve = new int[]{0,1,0,4,4,5,5,6}; break;
            case 9: solve = new int[]{0,4,8,9,12,13,13,9,11,10,7,11}; break;
            case 10: solve = new int[]{11,7,3,7,6,7,10,11,5,6,9,10,9,13,2,3,2,1,1,5}; break;
            case 11: solve = new int[]{5, 9,1,5,1,2,2,3,6,10,6,7}; break;
            case 12: solve = new int[]{8,9,9,10,5,9}; break;
            case 13: solve = new int[]{13,14,2,6,1,2,1,5,6,5}; break;
            case 14: solve = new int[]{6,2,4,8,9,10,5,6,1,5,0,4}; break;
            case 15: solve = new int[]{5,6,10,14,1,2,2,6,6,10,2,3,2,6}; break;
            case 16: solve = new int[]{5,6,7,6,2,6,9,8,9,13,12,13,14,10,15,14}; break;
            case 17: solve = new int[]{6,10,4,5,5,6,1,2,4,8,4,5}; break;
            case 18: solve = new int[]{9,13,5,9,1,5,0,1,1,2,1,5,4,5}; break;
        }
//        for(int i = 0; i < solve.length - 1; i += 2){
//            setHinhThayDoi(solve[i], solve[i + 1]);
//
//        }
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

    int sodem=0;
    int sodem2=0;


    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        sodem++;
        Log.d("swikkpe ","cái trên"+ sodem);
        this.runOnUiThread(Timer_Tick);
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            //This method runs in the same thread as the UI.

            //Do something to the UI thread here
            if(checkwin(openDBD,diembatdau)!=2 ) {
                if(sodem>=2) {
                    Log.d("swikkpe ", "cái dưới" + sodem2);
                    setHinhThayDoi(solve[sodem2], solve[sodem2 + 1]);
                    sodem2 += 2;
                }
            }
            else {
                myTimer.cancel();
            }
        }
    };

    void khoitaoanimation()
    {
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(1200);
        anim.setFillAfter(true);
        animSet.addAnimation(anim);

//        anim . setInterpolator ( interpolator );
        ranim . setDuration ( 700 );
//        anim . setFillEnabled ( true );
        ranim . setFillAfter ( true );

        animSet.addAnimation(ranim);
        animSet.setFillAfter(true);




        anim1.setInterpolator(new DecelerateInterpolator());
        anim1.setDuration(1200);
        anim1.setFillAfter(true);
        animSet1.addAnimation(anim1);

//        anim . setInterpolator ( interpolator );
        ranim1 . setDuration ( 700 );
//        anim . setFillEnabled ( true );
        ranim1 . setFillAfter ( true );

        animSet1.addAnimation(ranim1);
        animSet1.setFillAfter(true);

    }

    ArrayList<Integer> duongdi=new ArrayList<Integer>();
    void duyetduongdi(int cong,int vitri) // để kiểu int vì nó trả về đc nhiều giá trị để dễ dễ dàng theo dõi code chỗ đó có hoạt động ko
    {
        duongdi.clear();
        int k = cong;
        int a = vitri;
        int lap = 0;
        c = 0;
        duongdi.add(a);
        while (lap != 16) { //  lặp cho đến khi return mới thôi
            lap++;
            if (k == 1 && a % 4 != 0)
                c = a - 1;
            else if (k == 2 && a - 4 >= 0)
                c = a - 4;
            else if (k == 3 && a + 1 % 4 != 0)
                c = a + 1;
            else if (k == 4 && a + 4 < 16)
                c = a + 4;
            int congC1 = addHinh.arrImageView[c].open1;
            int congC2 = addHinh.arrImageView[c].open2;

            if (congC1 == 0 && congC2 != 0)
            {
                if (
                        (k==1 && congC2==3) || (k==2 && congC2==4) || (k==3 && congC2==1) || (k==4 && congC2==2)
                        ) {
                    duongdi.add(c);
                    break;
                }

            }
            else if (congC1 != 0 && congC2 != 0) {
                if (
                        ( k == 1 && (congC1 == 3 || congC2 == 3)) ||(
                                k == 2 && (congC1 == 4 || congC2 == 4)) ||
                                (  k == 3 && (congC1 == 1 || congC2 == 1)) ||
                                (   k == 4 && (congC1 == 2 || congC2 == 2))
                        ) {
                    int vitrike = congC1;
                    if ((k == 1 && vitrike == 3) || (k == 2 && vitrike == 4) || (k == 3 && vitrike == 1) || (k == 4 && vitrike == 2))
                        vitrike = addHinh.arrImageView[c].open2;
                    // cập nhật lại cho lần duyệt sau
                    k = vitrike;
                    a = c;
                    duongdi.add(a);
                }
            }
        }

    }

    ArrayList<Integer> nutconlai = new ArrayList<Integer>();
    void nutconlai()
    {
        for(int i=0;i<16;i++)
            if(check(i)==2  )
            {
                if((int) addHinh.arrImageView[i].hinh.getTag() != R.drawable.abc)
                {
                    nutconlai.add(i);
                }
                else
                {
                    addHinh.arrImageView[i].hinh.setImageBitmap(null);
                }
            }
    }

    int check(int so)
    {
        for(int i=0;i<duongdi.size();i++)
            if(so==duongdi.get(i))
                return 1;
        return 2;
    }

    int animationcheck=0;

    void runanimation()
    {
        animationcheck=0;
        for(int i=0;i<nutconlai.size();i++)
        {
            if((nutconlai.get(i)+1)%4==0) {
                Log.d("1","animation trên");
                animationcheck=1;
                addHinh.arrImageView[nutconlai.get(i)].hinh.startAnimation(animSet1);
            }
            else
            {
                animationcheck=2;
                Log.d("2","animation dưới");
                addHinh.arrImageView[nutconlai.get(i)].hinh.startAnimation(animSet);
            }

        }
    }
}

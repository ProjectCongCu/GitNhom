package com.example.administrator.ssd;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


/**
 * Created by Administrator on 6/16/2016.
 */
public class Customdialogclass extends Dialog {

    public Customdialogclass(Context context, myOnClickListener myclick1,myOnClickListener myclick2,myOnClickListener myclick3,int hight) {
        super(context);
        this.myListener1 = myclick1;
        this.myListener2 = myclick2;
        this.myListener3 = myclick3;
        h = hight;

    }

    public Customdialogclass(Context context, myOnClickListener myclick1,myOnClickListener myclick2,myOnClickListener myclick3,int hight,int star,int width) {
        super(context);
        this.myListener1 = myclick1;
        this.myListener2 = myclick2;
        this.myListener3 = myclick3;
        h = hight;
        this.star = star;
        w=width;
    }

    public Customdialogclass(Context context, myOnClickListener myclick1) {
        super(context);
        this.myListener1 = myclick1;

    }


    public myOnClickListener myListener1;
    public myOnClickListener myListener2;
    public myOnClickListener myListener3;
    public int h,w;
    public int star;


    // This is my interface //
    public interface myOnClickListener {
        void onButtonClick();
    }


    RelativeLayout relativeLayoutDia1, relativeLayoutDia2;
    ImageView imgsao1Dia, imgsao2Dia, imgsao3Dia, imgKhongSao1Dia, imgKhongSao2Dia, imgKhongSao3Dia;
    Button btnLevelDia, btnReplayDia, btnNextDia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.customdialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        int reallyHeight = (int)(h * 0.46);
        getWindow().setLayout(reallyHeight, reallyHeight);

        if(h==320 && w==240)
        {
            getWindow().setLayout((int)(w*.8),(int)(h*.65));
            reallyHeight=(int)(w*.8);
        }else{
            getWindow().setLayout(reallyHeight, reallyHeight);
        }


        btnLevelDia = (Button) findViewById(R.id.btnLevelDia);
        btnReplayDia = (Button) findViewById(R.id.btnReplayDia);
        btnNextDia = (Button) findViewById(R.id.btnNextDia);

        relativeLayoutDia1 = (RelativeLayout)findViewById(R.id.relativeLayoutDia1);
        relativeLayoutDia2 = (RelativeLayout)findViewById(R.id.relativeLayoutDia2);

        imgKhongSao1Dia = (ImageView)findViewById(R.id.imgsao1Dia);
        imgKhongSao2Dia = (ImageView)findViewById(R.id.imgsao2Dia);
        imgKhongSao3Dia = (ImageView)findViewById(R.id.imgsao3Dia);
        imgsao1Dia = (ImageView)findViewById(R.id.imgSaovang1Dia);
        imgsao2Dia = (ImageView)findViewById(R.id.imgSaovang2Dia);
        imgsao3Dia = (ImageView)findViewById(R.id.imgSaovang3Dia);
        loadsao();


        if(h==320 && w==240)
        {
            //relativeLayout1 gồm các ngôi sao
            RelativeLayout.LayoutParams paramsRelative = new RelativeLayout.LayoutParams(reallyHeight * 85 / 100, reallyHeight * 70 / 100);
            paramsRelative.setMargins(reallyHeight * 10 / 100,0, reallyHeight * 8 / 100, 0);
            relativeLayoutDia1.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia1.getLayoutParams().width * 18 / 100, relativeLayoutDia1.getLayoutParams().width * 18 / 100);
            paramsRelative.setMargins(relativeLayoutDia1.getLayoutParams().width * 12 / 100, relativeLayoutDia1.getLayoutParams().width * 24 / 100, 0, 0);
            imgsao1Dia.setLayoutParams(paramsRelative);
            imgKhongSao1Dia.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia1.getLayoutParams().width * 18 / 100, relativeLayoutDia1.getLayoutParams().width * 18 / 100);
            paramsRelative.setMargins(relativeLayoutDia1.getLayoutParams().width * 5 / 100, relativeLayoutDia1.getLayoutParams().width * 24 / 100, 0, 0);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.imgsao1Dia);
            imgsao2Dia.setLayoutParams(paramsRelative);
            imgKhongSao2Dia.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia1.getLayoutParams().width * 18 / 100, relativeLayoutDia1.getLayoutParams().width * 18 / 100);
            paramsRelative.setMargins(relativeLayoutDia1.getLayoutParams().width * 5 / 100, relativeLayoutDia1.getLayoutParams().width * 24 / 100, 0, 0);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.imgsao2Dia);
            imgsao3Dia.setLayoutParams(paramsRelative);
            imgKhongSao3Dia.setLayoutParams(paramsRelative);


//        relativeLayout2 gồm các button Next - Replay - Level
            paramsRelative = new RelativeLayout.LayoutParams(reallyHeight, reallyHeight * 30 / 100);
            paramsRelative.addRule(RelativeLayout.BELOW, R.id.relativeLayoutDia1);
            paramsRelative.setMargins(reallyHeight * 3 / 100, reallyHeight * 3 / 100, reallyHeight * 3 / 100, 0);
            relativeLayoutDia2.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia2.getLayoutParams().width * 18 / 100, relativeLayoutDia2.getLayoutParams().height * 64 / 100);
            paramsRelative.setMargins(relativeLayoutDia2.getLayoutParams().width * 8 / 100, relativeLayoutDia2.getLayoutParams().height * 18 / 100, 0, 0);
            btnReplayDia.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia2.getLayoutParams().width * 16 / 100, relativeLayoutDia2.getLayoutParams().height * 62 / 100);
            paramsRelative.setMargins(relativeLayoutDia2.getLayoutParams().width * 10 / 100, relativeLayoutDia2.getLayoutParams().height * 18 / 100, 0, 0);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnReplayDia);
            btnLevelDia.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia2.getLayoutParams().width * 16 / 100, relativeLayoutDia2.getLayoutParams().height * 62 / 100);
            paramsRelative.setMargins(relativeLayoutDia2.getLayoutParams().width * 10 / 100, relativeLayoutDia2.getLayoutParams().height * 18 / 100, 0, 0);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLevelDia);
            btnNextDia.setLayoutParams(paramsRelative);
        }
        else {
//        relativeLayout1 gồm các ngôi sao
            RelativeLayout.LayoutParams paramsRelative = new RelativeLayout.LayoutParams(reallyHeight * 90 / 100, reallyHeight * 72 / 100);
            paramsRelative.setMargins(reallyHeight * 8 / 100, 0, reallyHeight * 8 / 100, 0);
            relativeLayoutDia1.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia1.getLayoutParams().width * 22 / 100, relativeLayoutDia1.getLayoutParams().width * 22 / 100);
            paramsRelative.setMargins(relativeLayoutDia1.getLayoutParams().width * 5 / 100, relativeLayoutDia1.getLayoutParams().width * 24 / 100, 0, 0);
            imgsao1Dia.setLayoutParams(paramsRelative);
            imgKhongSao1Dia.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia1.getLayoutParams().width * 22 / 100, relativeLayoutDia1.getLayoutParams().width * 22 / 100);
            paramsRelative.setMargins(relativeLayoutDia1.getLayoutParams().width * 5 / 100, relativeLayoutDia1.getLayoutParams().width * 24 / 100, 0, 0);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.imgsao1Dia);
            imgsao2Dia.setLayoutParams(paramsRelative);
            imgKhongSao2Dia.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia1.getLayoutParams().width * 22 / 100, relativeLayoutDia1.getLayoutParams().width * 22 / 100);
            paramsRelative.setMargins(relativeLayoutDia1.getLayoutParams().width * 5 / 100, relativeLayoutDia1.getLayoutParams().width * 24 / 100, 0, 0);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.imgsao2Dia);
            imgsao3Dia.setLayoutParams(paramsRelative);
            imgKhongSao3Dia.setLayoutParams(paramsRelative);


//        relativeLayout2 gồm các button Next - Replay - Level
            paramsRelative = new RelativeLayout.LayoutParams(reallyHeight, reallyHeight * 20 / 100);
            paramsRelative.addRule(RelativeLayout.BELOW, R.id.relativeLayoutDia1);
            paramsRelative.setMargins(reallyHeight * 3 / 100, 0, reallyHeight * 3 / 100, 0);
            relativeLayoutDia2.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia2.getLayoutParams().width * 18 / 100, relativeLayoutDia2.getLayoutParams().height * 64 / 100);
            paramsRelative.setMargins(relativeLayoutDia2.getLayoutParams().width * 8 / 100, relativeLayoutDia2.getLayoutParams().height * 18 / 100, 0, 0);
            btnReplayDia.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia2.getLayoutParams().width * 16 / 100, relativeLayoutDia2.getLayoutParams().height * 62 / 100);
            paramsRelative.setMargins(relativeLayoutDia2.getLayoutParams().width * 10 / 100, relativeLayoutDia2.getLayoutParams().height * 18 / 100, 0, 0);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnReplayDia);
            btnLevelDia.setLayoutParams(paramsRelative);

            paramsRelative = new RelativeLayout.LayoutParams(relativeLayoutDia2.getLayoutParams().width * 16 / 100, relativeLayoutDia2.getLayoutParams().height * 62 / 100);
            paramsRelative.setMargins(relativeLayoutDia2.getLayoutParams().width * 10 / 100, relativeLayoutDia2.getLayoutParams().height * 18 / 100, 0, 0);
            paramsRelative.addRule(RelativeLayout.RIGHT_OF, R.id.btnLevelDia);
            btnNextDia.setLayoutParams(paramsRelative);
        }

        btnLevelDia.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myListener1.onButtonClick(); // I am giving the click to the
                // interface function which we need
                // to implements where we call this
                // class

            }
        });


        btnReplayDia.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myListener2.onButtonClick(); // I am giving the click to the
                // interface function which we need
                // to implements where we call this
                // class

            }
        });

        btnNextDia.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myListener3.onButtonClick(); // I am giving the click to the
                // interface function which we need
                // to implements where we call this
                // class

            }
        });
    }


    void loadsao()
    {
        if(star==1)
        {
            imgsao1Dia.setVisibility(View.VISIBLE);
        }
        else if(star==2)
        {
            imgsao1Dia.setVisibility(View.VISIBLE);
            imgsao2Dia.setVisibility(View.VISIBLE);
        }
        else if(star==3)
        {
            imgsao1Dia.setVisibility(View.VISIBLE);
            imgsao2Dia.setVisibility(View.VISIBLE);
            imgsao3Dia.setVisibility(View.VISIBLE);

        }
    }



}

package com.example.administrator.ssd;

/**
 * Created by Administrator on 6/1/2016.
 */
public class AddHinh {
    Hinh img0=new Hinh(), img1=new Hinh(), img2=new Hinh(), img3=new Hinh() ,img4=new Hinh() ,img5=new Hinh(), img6=new Hinh()
            , img7=new Hinh() ,img8=new Hinh(), img9=new Hinh() ,img10=new Hinh(), img11=new Hinh(), img12=new Hinh(),
            img13=new Hinh(), img14=new Hinh(), img15=new Hinh();

    Hinh[] arrImageView = {img0, img1, img2, img3 ,img4 ,img5, img6, img7 ,img8, img9 ,img10, img11, img12, img13, img14, img15};
    String[] tenImage;

    public void getanh(Hinh b, String a)
    {
        switch (a)
        {
            case "rong" : b.hinh.setImageResource(R.drawable.rong);b.hinh.setTag(R.drawable.rong);
                b.open1=0; b.open2=0;break;
            case "abc" : b.hinh.setImageResource(R.drawable.abc); b.hinh.setTag(R.drawable.abc);
                b.open1=0; b.open2=0;break;
            case "bt1" :  b.hinh.setImageResource(R.drawable.bt1); b.hinh.setTag(R.drawable.bt1);
                b.open1=1; b.open2=3;break;
            case "bt2" :  b.hinh.setImageResource(R.drawable.bt2); b.hinh.setTag(R.drawable.bt2);
                b.open1=2; b.open2=4;break;
            case "bt3" :  b.hinh.setImageResource(R.drawable.bt3); b.hinh.setTag(R.drawable.bt3);
                b.open1=2; b.open2=3;break;
            case "bt4" :  b.hinh.setImageResource(R.drawable.bt4); b.hinh.setTag(R.drawable.bt4);
                b.open1=1; b.open2=2;break;
            case "bt5" :  b.hinh.setImageResource(R.drawable.bt5); b.hinh.setTag(R.drawable.bt5);
                b.open1=3; b.open2=4;break;
            case "bt6" :  b.hinh.setImageResource(R.drawable.bt6); b.hinh.setTag(R.drawable.bt6);
                b.open1=1; b.open2=4;break;
            case "f1" :  b.hinh.setImageResource(R.drawable.f1); b.hinh.setTag(R.drawable.f1);
                b.open1=0; b.open2=3;break;
            case "f2" :  b.hinh.setImageResource(R.drawable.f2); b.hinh.setTag(R.drawable.f2);
                b.open1=0; b.open2=1;break;
            case "f3" :  b.hinh.setImageResource(R.drawable.f3); b.hinh.setTag(R.drawable.f3);
                b.open1=0; b.open2=4;break;
            case "f4" :  b.hinh.setImageResource(R.drawable.f4); b.hinh.setTag(R.drawable.f4);
                b.open1=0; b.open2=2;break;
            case "k1" :  b.hinh.setImageResource(R.drawable.k1); b.hinh.setTag(R.drawable.k1);
                b.open1=1; b.open2=3;break;
            case "k2" :  b.hinh.setImageResource(R.drawable.k2); b.hinh.setTag(R.drawable.k2);
                b.open1=2; b.open2=4;break;
            case "k3" :  b.hinh.setImageResource(R.drawable.k3); b.hinh.setTag(R.drawable.k3);
                b.open1=3; b.open2=4;break;
            case "k4" :  b.hinh.setImageResource(R.drawable.k4); b.hinh.setTag(R.drawable.k4);
                b.open1=1; b.open2=4;break;
            case "k5" :  b.hinh.setImageResource(R.drawable.k5); b.hinh.setTag(R.drawable.k5);
                b.open1=2; b.open2=3;break;
            case "k6" :  b.hinh.setImageResource(R.drawable.k6); b.hinh.setTag(R.drawable.k6);
                b.open1=1; b.open2=2;break;
            case "s1" :  b.hinh.setImageResource(R.drawable.s1); b.hinh.setTag(R.drawable.s1);
                b.open1=0; b.open2=3;break;
            case "s2" :  b.hinh.setImageResource(R.drawable.s2); b.hinh.setTag(R.drawable.s2);
                b.open1=0; b.open2=1;break;
            case "s3" :  b.hinh.setImageResource(R.drawable.s3); b.hinh.setTag(R.drawable.s3);
                b.open1=0; b.open2=4;break;
            case "s4" :  b.hinh.setImageResource(R.drawable.s4); b.hinh.setTag(R.drawable.s4);
                b.open1=0; b.open2=2;break;
        }
    }


    public void addMan1(){
        for(int i = 0; i < arrImageView.length; i++){
            getanh(arrImageView[i], tenImage[i]);
        }
    }
}

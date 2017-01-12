package com.example.administrator.ssd;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 14/6/2016.
 */
public class Sound {

    //Đường dẫn tạo folder trong bộ nhớ điện thoại
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/textSound";
    File file, dir;

    public Sound() {
        //đường dẫn file .txt
        file = new File(path + "/sound.txt");
    }

    public void CreateFolder(){
        dir = new File(path);
        dir.mkdirs();
        save(file, "true");
    }

    //Tạo file .txt với đường dẫn là file
    public void save (File file, String text){
        FileOutputStream fileOutputStream =  null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Đọc file text với đường dẫn là file
    public String load(File file){
        String result = "";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader =  new BufferedReader(inputStreamReader);

            String line;
            while((line = bufferedReader.readLine()) != null){
                result += line;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}

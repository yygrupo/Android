package com.testapplication.tool;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class Tool {
    public static File moveFile(File file, Activity activity) {
        String FILENAME = String.format("%1$s.png", randomNameImage());
        try {
            InputStream inputStream = new FileInputStream(file);
            //FileOutputStream fileOutputStream = activity.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            File file1 = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            //File file1 = new File(externalFilesDir);
            if (file1 != null && !file1.exists()) {
                file1.mkdirs();
            }
            file1 = new File(file1, FILENAME);
            //File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), FILENAME);
            FileOutputStream fileOutputStream = new FileOutputStream(file1);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();

//          /*String.format("%1$s/%2$s", activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), FILENAME)*/
            return file1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String randomNameImage() {
        return String.valueOf(Calendar.getInstance().getTime().getTime());
    }
}

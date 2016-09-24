package com.testapplication.tool;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class Tool {
    public static String moveFile(File file, Activity activity) {
        String FILENAME = String.format("$1%s.png", randomNameImage());
        try {
            InputStream inputStream = new FileInputStream(file);
            FileOutputStream fos = activity.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(inputStream.read());
            fos.close();
            return String.format("$1%s/$2%s", activity.getFilesDir().getPath(), FILENAME);
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

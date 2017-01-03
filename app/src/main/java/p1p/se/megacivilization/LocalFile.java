package p1p.se.megacivilization;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LocalFile {
    private static final String FILE_NAME = "data.json";

    public static String loadData(Context context) {
        StringBuilder fileContent = new StringBuilder();
        InputStream json = null;
        try {
            json = context.getAssets().open(FILE_NAME);

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                fileContent.append(str);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    void saveData(Context context, String data) {
        try {
            FileOutputStream outputStream;
            outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

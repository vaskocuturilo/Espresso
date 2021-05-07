package utils;

import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import model.RegisterModel;

public class CreateFile {

    public void createJsonDataFile(final String filename) {
        RegisterModel registerModel = new RegisterModel("Test Client", "qweeee@test.ru", "qwerty123456");

        OutputStreamWriter outputStreamWriter = null;
        Gson gson = new Gson();
        try {
            FileOutputStream fw = new FileOutputStream("/data/data/" + InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageName() + "/" + filename);
            outputStreamWriter = new OutputStreamWriter(fw);
            outputStreamWriter.write(gson.toJson(registerModel));
            outputStreamWriter.flush();
            fw.getFD().sync();
        } catch (IOException e) {
            Log.d("MDC_CREATE_JSON_FILE", "The method createJsonDataFile is down");
        } finally {
            try {
                outputStreamWriter.close();
            } catch (IOException ex) {
                Log.d("MDC_CLOSE_FILE", "Can't close file.");
            }
        }
    }
}

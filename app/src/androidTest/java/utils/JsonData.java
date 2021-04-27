package utils;

import androidx.test.platform.app.InstrumentationRegistry;

import com.google.gson.Gson;

import static java.lang.String.format;
import static utils.PropertiesReader.getJsonFromAssets;

public class JsonData {

    public static <T> T getJsonData(final String jsonFileName, final Class<T> tClass, final String folderName) {
        final String pathToFolder = getJsonFromAssets(InstrumentationRegistry.getInstrumentation().getTargetContext(), format(folderName + "/%s.json", jsonFileName));
        return new Gson().fromJson(pathToFolder, tClass);
    }
}

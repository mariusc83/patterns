package org.mariusconstantin.patterns.utils;

import android.support.annotation.NonNull;

import org.apache.commons.io.IOUtils;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowAssetManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by MConstantin on 10/3/2016.
 */

public class TestUtils {

    public static String fetchJson(@NonNull String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = TestUtils.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                final StringWriter writer = new StringWriter();
                IOUtils.copy(inputStream, writer);
                return writer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {//do nothing
            }
        }

        return null;
    }
}

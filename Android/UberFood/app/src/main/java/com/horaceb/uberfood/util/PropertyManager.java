package com.horaceb.uberfood.util;

import android.content.Context;
import android.content.res.Resources;

import java.io.IOException;
import java.util.Properties;

/**
 * Loads the c
 * Created by HoraceBG on 22/06/2015.
 */
public class PropertyManager {

    private static final String PROPERTIES_FILE_NAME = "uberfood.properties";

    private final Context context;
    private final Resources resources;
    private Properties properties;

    public PropertyManager(final Context context) {
        this.context = context;
        this.resources = context.getResources();
        this.properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try {
            properties.load(resources.getAssets().open(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
            // Hmmm ?
        }
    }
}

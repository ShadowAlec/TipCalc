package com.luisortiz.tipcalc;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by luis on 26/09/16.
 */

public class TipCalcApp extends Application {

    private final static String ABOUT_URL = "https://github.com/ShadowAlec/";
    public String getAboutUrl(){return ABOUT_URL;}

}

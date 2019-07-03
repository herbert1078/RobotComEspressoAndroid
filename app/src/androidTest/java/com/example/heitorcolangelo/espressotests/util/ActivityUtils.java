package com.example.heitorcolangelo.espressotests.util;

import android.app.Activity;
import android.content.Intent;

import java.lang.reflect.Field;

public class ActivityUtils {

    public static int getResultCode(Activity activity) {
        int returnCode = 0;
        if (activity != null) {
            try {
                Field resultCodeField = Activity.class.getDeclaredField("mResultCode");
                resultCodeField.setAccessible(true);
                returnCode = (int) resultCodeField.get(activity);
            } catch (Exception e) {
                returnCode = 0;
            }
        }
        return returnCode;
    }

    public static Intent getResultData(Activity activity) {
        Intent returnIntent = null;
        if (activity != null) {
            try {
                Field resultDataField = Activity.class.getDeclaredField("mResultData");
                resultDataField.setAccessible(true);
                returnIntent = (Intent) resultDataField.get(activity);
            } catch (Exception e) {
                returnIntent = null;
            }
        }
        return returnIntent;
    }
}
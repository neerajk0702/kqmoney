package com.kredivation.kqmoney.utility;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;


/**
 * Created by Neeraj on 3/8/2017.
 */

public class FontManager {

    // usage: yourTextView.setTypeface(FontManager.getTypeface(FontManager.YOURFONT));
    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    public static Typeface getFontTypeface(Context context, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(),
                            assetPath);

                    cache.put(assetPath, typeface);
                } catch (Exception e) {

                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }

    //get FontTypeface for Material Design Icons
    public static Typeface getFontTypefaceMaterialDesignIcons(Context context, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(),
                            assetPath);

                    cache.put(assetPath, typeface);
                } catch (Exception e) {

                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }
}

package com.gmonetix.truthdare.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * @author Gmonetix
 */

public class LightTextView extends android.support.v7.widget.AppCompatTextView {


    public LightTextView(Context context) {
        super(context);
    }

    public LightTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        applyRobotoFont(context);
    }

    public LightTextView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        applyRobotoFont(context);
    }

    private void applyRobotoFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/prime_light.ttf", context);
        setTypeface(customFont);
    }

}

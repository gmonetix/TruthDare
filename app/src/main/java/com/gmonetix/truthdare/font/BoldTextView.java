package com.gmonetix.truthdare.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * @author Gmonetix
 */

public class BoldTextView extends android.support.v7.widget.AppCompatTextView {


    public BoldTextView(Context context) {
        super(context);
    }

    public BoldTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        applyRobotoFont(context);
    }

    public BoldTextView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        applyRobotoFont(context);
    }

    private void applyRobotoFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/prime_regular.ttf", context);
        setTypeface(customFont);
    }

}

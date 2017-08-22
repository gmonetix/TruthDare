package com.gmonetix.truthdare.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * @author Gmonetix
 */

public class BoldEditText extends android.support.v7.widget.AppCompatEditText {

    public BoldEditText(Context context) {
        super(context);
    }

    public BoldEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        applyRobotoFont(context);
    }

    public BoldEditText(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        applyRobotoFont(context);
    }

    private void applyRobotoFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/prime_regular.ttf", context);
        setTypeface(customFont);
    }

}

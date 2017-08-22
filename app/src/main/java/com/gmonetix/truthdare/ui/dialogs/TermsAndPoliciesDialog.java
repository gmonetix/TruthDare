package com.gmonetix.truthdare.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.gmonetix.truthdare.R;

/**
 * @author Gmonetix
 */

public class TermsAndPoliciesDialog extends Dialog {

    public TermsAndPoliciesDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.terms_and_conditions_dialog);
    }

}

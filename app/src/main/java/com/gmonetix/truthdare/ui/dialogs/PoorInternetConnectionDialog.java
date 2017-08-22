package com.gmonetix.truthdare.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.gmonetix.truthdare.R;

/**
 * Created by gaura on 8/14/2017.
 */

public class PoorInternetConnectionDialog extends Dialog {

    public PoorInternetConnectionDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poor_internet_connection_dialog);
    }

}

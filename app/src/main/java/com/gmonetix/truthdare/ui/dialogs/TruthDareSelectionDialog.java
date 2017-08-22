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

public class TruthDareSelectionDialog extends Dialog {

    public TruthDareSelectionDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.truth_dare_selection_dialog);
    }
}

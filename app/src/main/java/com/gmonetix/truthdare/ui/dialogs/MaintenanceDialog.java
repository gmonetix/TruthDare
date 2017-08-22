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

public class MaintenanceDialog extends Dialog{



    public MaintenanceDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.maintenance_dialog);

    }

}

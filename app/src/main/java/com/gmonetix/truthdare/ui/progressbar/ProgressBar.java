package com.gmonetix.truthdare.ui.progressbar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.gmonetix.truthdare.R;

/**
 * @author Gmonetix
 */

public class ProgressBar extends Dialog {

    private SpinKitView progressBar;

    public ProgressBar(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);
        progressBar = (SpinKitView)findViewById(R.id.spin_kit);
        CubeGrid cubeGrid = new CubeGrid();
        progressBar.setIndeterminateDrawable(cubeGrid);

    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(listener);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
        progressBar.setVisibility(View.GONE);
    }

}

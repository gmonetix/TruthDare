package com.gmonetix.truthdare.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmonetix.truthdare.App;
import com.gmonetix.truthdare.R;
import com.gmonetix.truthdare.utils.Helper;
import com.gmonetix.truthdare.utils.SharedPref;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * @author Gmonetix
 */

public class ProfileDialog extends Dialog {

    private SharedPref sharedPref;

    private ImageView ivProfilePic;
    private TextView tvName, tvID;

    public ProfileDialog(@NonNull Context context) {
        super(context);
        sharedPref = App.getSharedPref();
        Helper.getUilInstance(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.profile_dialog);

        ivProfilePic =(ImageView) findViewById(R.id.profile_dialog_pic);
        tvName = (TextView) findViewById(R.id.profile_dialog_name);
        tvID = (TextView) findViewById(R.id.profile_dialog_id);

        tvName.setText(sharedPref.getFirstName() + " " + sharedPref.getLastName());
        ImageLoader.getInstance().displayImage(Helper.getFbImageLink(sharedPref.getFacebookId(),"200"), ivProfilePic, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Toast.makeText(getContext(), "failed loading image", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                Toast.makeText(getContext(), "loading image cancelled", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

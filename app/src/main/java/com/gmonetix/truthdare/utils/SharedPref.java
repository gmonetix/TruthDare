package com.gmonetix.truthdare.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Gmonetix
 */

public class SharedPref {

    private Context context;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;

    //app pref
    private static final String LOGGED_IN = "logged_in";

    //facebook user data
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String FACEBOOK_ID = "facebook_id";
    private static final String FACEBOOK_PROFILE_PIC_LINK = "facebook_profile_pic_link";

    //firebase data
    private static final String FIREBASE_UID = "firebase_uid";

    public SharedPref(Context context) {
        this.context = context;
        shared = context.getSharedPreferences("SHARED_PREF", MODE_PRIVATE);
        editor = shared.edit();
    }

    public void setLoggedIn(boolean value) {
        editor.putBoolean(LOGGED_IN,value);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return shared.getBoolean(LOGGED_IN,false);
    }

    public String getFacebookId() {
        return shared.getString(FACEBOOK_ID,"");
    }

    public void setFacebookId(String facebookId) {
        editor.putString(FACEBOOK_ID,facebookId);
        editor.commit();
    }

    public String getFirstName() {
        return shared.getString(FIRST_NAME,"");
    }

    public void setFirstName(String firstName) {
        editor.putString(FIRST_NAME,firstName);
        editor.commit();
    }

    public String getLastName() {
        return shared.getString(LAST_NAME,"");
    }

    public void setLastName(String lastName) {
        editor.putString(LAST_NAME,lastName);
        editor.commit();
    }

    public String getFacebookProfilePicLink() {
        return shared.getString(FACEBOOK_PROFILE_PIC_LINK,"");
    }

    public void setFacebookProfilePicLink(String link) {
        editor.putString(FACEBOOK_PROFILE_PIC_LINK,link);
        editor.commit();
    }

    public void setFirebaseUid(String uid) {
        editor.putString(FIREBASE_UID,uid);
        editor.commit();
    }

    public String getFirebaseUid() {
        return shared.getString(FIREBASE_UID,"");
    }

}









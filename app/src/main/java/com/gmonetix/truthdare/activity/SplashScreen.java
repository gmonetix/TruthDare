package com.gmonetix.truthdare.activity;

import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gmonetix.truthdare.App;
import com.gmonetix.truthdare.R;
import com.gmonetix.truthdare.model.Friend;
import com.gmonetix.truthdare.model.Server;
import com.gmonetix.truthdare.ui.dialogs.MaintenanceDialog;
import com.gmonetix.truthdare.ui.dialogs.TermsAndPoliciesDialog;
import com.gmonetix.truthdare.ui.progressbar.ProgressBar;
import com.gmonetix.truthdare.utils.SharedPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    private Button fb_login, play_as_guest;

    private CallbackManager callbackManager;
    FacebookCallback<LoginResult> callback;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private String firstName,lastName;
    private String profilePicture;
    private String userId;
    private String TAG = "LoginActivity";

    private SharedPref sharedPref;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        //hides the status bar on android 4.1 and above
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        sharedPref = App.getSharedPref();

        if (sharedPref.isLoggedIn()) {
            startActivity(new Intent(SplashScreen.this,Home.class));
            finish();
        }

        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.gmonetix.truthdare",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
        fb_login = (Button) findViewById(R.id.splash_screen_fb_login);
        play_as_guest = (Button) findViewById(R.id.splash_screen_play_as_guest);

        mAuth = FirebaseAuth.getInstance();
        /*mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("server").getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Server server = dataSnapshot.getValue(Server.class);
                if (server.getStatus().equals("DOWN")) {
                    new MaintenanceDialog(SplashScreen.this).show();
                } else {


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        callbackManager = CallbackManager.Factory.create();

        fb_login.animate().translationYBy(0)
                .translationY(-300)
                .setDuration(800)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        play_as_guest.animate().translationYBy(0)
                                .translationY(-300)
                                .setDuration(800)
                                .alpha(1.0f);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .alpha(1.0f);

        play_as_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreen.this,GameModeActivity.class));
                finish();
            }
        });

        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {

                final AccessToken token = loginResult.getAccessToken();

                GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {
                            userId = object.getString("id");
                            profilePicture = "https://graph.facebook.com/" + userId + "/picture?width=500&height=500";
                            if(object.has("first_name"))
                                firstName = object.getString("first_name");
                            if(object.has("last_name"))
                                lastName = object.getString("last_name");

                            sharedPref.setFacebookId(userId);
                            sharedPref.setFacebookProfilePicLink(profilePicture);
                            sharedPref.setFirstName(firstName);
                            sharedPref.setLastName(lastName);

                            handleFacebookAccessToken(token);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SplashScreen.this, "Some error occurred !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //Here we put the requested fields to be returned from the JSONObject
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        };

        fb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(SplashScreen.this,
                        Arrays.asList("email","user_posts","user_friends","public_profile"));
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,callback);

    }

    private List<Friend> getFriendsList() {
        final List<Friend> friendslist = new ArrayList<Friend>();
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/friends", null, HttpMethod.GET, new GraphRequest.Callback() {
            public void onCompleted(GraphResponse response) {
                try {
                    JSONObject responseObject = response.getJSONObject();
                    JSONArray dataArray = responseObject.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObject = dataArray.getJSONObject(i);
                        String fbId = dataObject.getString("id");
                        String fbName = dataObject.getString("name");
                        Friend friend = new Friend();
                        friend.setName(fbName);
                        friend.setUserId(fbId);
                        friendslist.add(friend);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    Log.e("SIZE",String.valueOf(friendslist.size()));
                    for (int i=0; i<friendslist.size(); i++) {
                        Log.e("TAG",friendslist.get(i).getName());
                        Log.e("TAG",friendslist.get(i).getUserId());
                    }
                }
            }
        }).executeAsync();
        return friendslist;
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            sharedPref.setFirebaseUid(user.getUid());
                            sharedPref.setLoggedIn(true);
                           // updateUI(user);
                            Intent i = new Intent(SplashScreen.this,Home.class);
                            startActivity(i);
                            SplashScreen.this.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SplashScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }

                        // ...
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        callbackManager.onActivityResult(requestCode, responseCode, intent);
    }

    public void termsAndPolicies(View view) {
        new TermsAndPoliciesDialog(this).show();
    }

    private void logout(){
        LoginManager.getInstance().logOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //  updateUI(currentUser);
    }

    //FirebaseAuth.getInstance().signOut();

}

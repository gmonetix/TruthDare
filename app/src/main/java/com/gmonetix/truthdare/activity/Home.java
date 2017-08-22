package com.gmonetix.truthdare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.gmonetix.truthdare.R;
import com.gmonetix.truthdare.fragments.Friends;
import com.gmonetix.truthdare.fragments.OnlineFriends;
import com.gmonetix.truthdare.model.Friend;
import com.gmonetix.truthdare.ui.dialogs.ProfileDialog;
import com.gmonetix.truthdare.ui.progressbar.ProgressBar;
import com.gmonetix.truthdare.utils.Helper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TextView createAGame, joinAGame, playOffline;
    private ImageView ivProfile, ivCoins, ivSettings;
    
    public static List<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        final ProgressBar progressBar = new ProgressBar(this);
        progressBar.show();
        friendList = new ArrayList<>();
        
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
                        friendList.add(friend);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    progressBar.dismiss();
                    setupViewPager(viewPager);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }
        }).executeAsync();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OnlineFriends(),"ONLINE");
        adapter.addFragment(new Friends(),"FRIENDS");
        viewPager.setAdapter(adapter);
    }

    private void init() {
        Helper.getUilInstance(this);
        tabLayout = (TabLayout) findViewById(R.id.home_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.home_tab_layout_viewpager);

        createAGame = (TextView) findViewById(R.id.home_create_a_game);
        joinAGame = (TextView) findViewById(R.id.home_join_a_game);
        playOffline = (TextView) findViewById(R.id.home_play_offline);
        ivProfile = (ImageView) findViewById(R.id.home_user_profile);
        ivCoins = (ImageView) findViewById(R.id.home_coin);
        ivSettings = (ImageView) findViewById(R.id.home_settings);

        createAGame.setOnClickListener(this);
        joinAGame.setOnClickListener(this);
        playOffline.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        ivCoins.setOnClickListener(this);
        ivSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_create_a_game:
                startActivity(new Intent(Home.this,GameModeActivity.class));
                break;
            case R.id.home_join_a_game:
                break;
            case R.id.home_play_offline:
                startActivity(new Intent(Home.this,GameModeActivity.class));
                break;

            //
            case R.id.home_user_profile:
                new ProfileDialog(Home.this).show();
                break;
            case R.id.home_coin:
                break;
            case R.id.home_settings:
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

package com.gmonetix.truthdare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmonetix.truthdare.R;
import com.gmonetix.truthdare.activity.Home;
import com.gmonetix.truthdare.adapter.FriendAdapter;
import com.gmonetix.truthdare.model.Friend;

import java.util.List;

/**
 * @author Gmonetix
 */

public class Friends extends Fragment {

    private View view;
    private RecyclerView recyclerView;

    private FriendAdapter friendAdapter;

    public Friends() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_online_friends, container, false);

        recyclerView = view.findViewById(R.id.friends_rv);
        recyclerView = view.findViewById(R.id.friends_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        friendAdapter = new FriendAdapter(getActivity(), Home.friendList);
        recyclerView.setAdapter(friendAdapter);
        friendAdapter.notifyDataSetChanged();

        return view;
    }

}
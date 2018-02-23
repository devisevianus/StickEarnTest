package com.example.devis.stickearn.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.devis.stickearn.R;
import com.example.devis.stickearn.adapter.ViewPageAdapter;

/**
 * Created by Devis on 21/02/2018.
 */

public class UserDetail extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPageAdapter viewPageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragments(new UserFragment(), "Profile");
        viewPageAdapter.addFragments(new FollowingFragment(), "Following");
        viewPageAdapter.addFragments(new FollowersFragment(), "Followers");
        viewPageAdapter.addFragments(new ReposFragment(), "Repositories");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

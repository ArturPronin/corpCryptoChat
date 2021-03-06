package com.example.testcryptochat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.testcryptochat.Adapter.MyPageViewerAdapter;
import com.google.android.material.tabs.TabLayout;

// Home Screen Activity
public class HomeScreen extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        init_view();
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.chat1));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.video));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.bot));
        MyPageViewerAdapter myPageViewerAdapter = new MyPageViewerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(myPageViewerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void init_view() {
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
    }
}
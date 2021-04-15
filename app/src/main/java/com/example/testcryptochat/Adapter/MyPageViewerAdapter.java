package com.example.testcryptochat.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.testcryptochat.Fragment.Addfragment;
import com.example.testcryptochat.Fragment.Botfragment;
import com.example.testcryptochat.Fragment.Videofragment;


public class MyPageViewerAdapter extends FragmentPagerAdapter {
    FragmentManager fragmentManager;
    int size;

    public MyPageViewerAdapter(@NonNull FragmentManager fm, int size) {
        super(fm);
        this.fragmentManager = fm;
        this.size = size;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Addfragment addfragment = new Addfragment();
                return addfragment;
            case 1:
                Videofragment videofragment = new Videofragment();
                return videofragment;
            case 2:
                Botfragment botfragment = new Botfragment();
                return botfragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}

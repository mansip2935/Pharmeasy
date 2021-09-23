package com.example.pharmeasy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behaviour){
        super(fm,behaviour);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment){
        mFragmentList.add(fragment);
    }
}

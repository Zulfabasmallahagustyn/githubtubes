package com.zulfabasmallahagustyn.myapplication.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zulfabasmallahagustyn.myapplication.model.search.ModelSearchData;
import com.zulfabasmallahagustyn.myapplication.ui.fragment.FragmentFollowers;
import com.zulfabasmallahagustyn.myapplication.ui.fragment.FragmentFollowing;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    ModelSearchData modelSearchData;

    public ViewPagerAdapter(FragmentManager fragmentManager, ModelSearchData modelSearchData) {
        super(fragmentManager);
        this.modelSearchData = modelSearchData;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("modelSearchData", modelSearchData);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentFollowers();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FragmentFollowing();
                fragment.setArguments(bundle);
                break;
        }
        assert fragment != null;
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Followers";
                break;
            case 1:
                title = "Following";
                break;
        }
        return title;
    }

}

package com.example.placement;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by AkshayeJH on 01/01/18.
 */

class PagerViewAdapter extends FragmentPagerAdapter{

    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;

            case 1:
                StudentHomeFragment home = new StudentHomeFragment();
                return home;

            case 2:
                StudentApplicationsFragment app = new StudentApplicationsFragment();
                return  app;

            case 3:
                StudentResultFragment res =new StudentResultFragment();
                return res;



            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 4;
    }

}

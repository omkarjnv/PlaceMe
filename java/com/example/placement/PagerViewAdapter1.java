package com.example.placement;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by AkshayeJH on 01/01/18.
 */

class PagerViewAdapter1 extends FragmentPagerAdapter{

    public PagerViewAdapter1(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                AdminCompanyFragment c=new AdminCompanyFragment();
                return  c;

            case 1:
                AdminJobsFragment j=new AdminJobsFragment();
                return  j;

            case 2:
                AdminApplicationsFragment a=new AdminApplicationsFragment();
                return  a;

            case 3:
                AdminResultsFragment r=new AdminResultsFragment();
                return  r;


            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 4;
    }

}

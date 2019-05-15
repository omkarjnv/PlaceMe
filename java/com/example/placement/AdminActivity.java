package com.example.placement;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminActivity extends AppCompatActivity {

    private TextView mCompanyLabel;
    private TextView mJobLabel;
    private TextView mApplicationLabel;
    private TextView mResultLabel;


    private ViewPager mMainPager;

    private PagerViewAdapter1 mPagerViewAdapter;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mAuth = FirebaseAuth.getInstance();

        mCompanyLabel = (TextView) findViewById(R.id.companiesLabel);
        mJobLabel = (TextView) findViewById(R.id.jobsLabel);
        mApplicationLabel = (TextView) findViewById(R.id.applicationLabel);
        mResultLabel = (TextView) findViewById(R.id.resultLabel);

        mMainPager = (ViewPager) findViewById(R.id.mainPager);
        mMainPager.setOffscreenPageLimit(2);

        mPagerViewAdapter = new PagerViewAdapter1(getSupportFragmentManager());
        mMainPager.setAdapter(mPagerViewAdapter);

        mCompanyLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMainPager.setCurrentItem(0);

            }
        });

        mJobLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMainPager.setCurrentItem(1);

            }
        });

        mApplicationLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMainPager.setCurrentItem(2);

            }
        });

        mResultLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMainPager.setCurrentItem(3);

            }
        });

        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                changeTabs(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void changeTabs(int position) {

        if (position == 0) {

            mCompanyLabel.setTextColor(getColor(R.color.textTabBright));
            mCompanyLabel.setTextSize(22);

            mJobLabel.setTextColor(getColor(R.color.textTabLight));
            mJobLabel.setTextSize(16);

            mApplicationLabel.setTextColor(getColor(R.color.textTabLight));
            mApplicationLabel.setTextSize(16);

            mResultLabel.setTextColor(getColor(R.color.textTabLight));
            mResultLabel.setTextSize(16);

        }

        if (position == 1) {

            mCompanyLabel.setTextColor(getColor(R.color.textTabLight));
            mCompanyLabel.setTextSize(16);

            mJobLabel.setTextColor(getColor(R.color.textTabBright));
            mJobLabel.setTextSize(22);

            mApplicationLabel.setTextColor(getColor(R.color.textTabLight));
            mApplicationLabel.setTextSize(16);

            mResultLabel.setTextColor(getColor(R.color.textTabLight));
            mResultLabel.setTextSize(16);

        }

        if (position == 2) {

            mCompanyLabel.setTextColor(getColor(R.color.textTabLight));
            mCompanyLabel.setTextSize(16);

            mJobLabel.setTextColor(getColor(R.color.textTabLight));
            mJobLabel.setTextSize(16);

            mApplicationLabel.setTextColor(getColor(R.color.textTabBright));
            mApplicationLabel.setTextSize(22);

            mResultLabel.setTextColor(getColor(R.color.textTabLight));
            mResultLabel.setTextSize(16);

        }

        if (position == 3) {

            mCompanyLabel.setTextColor(getColor(R.color.textTabLight));
            mCompanyLabel.setTextSize(16);

            mJobLabel.setTextColor(getColor(R.color.textTabLight));
            mJobLabel.setTextSize(16);

            mApplicationLabel.setTextColor(getColor(R.color.textTabLight));
            mApplicationLabel.setTextSize(16);

            mResultLabel.setTextColor(getColor(R.color.textTabBright));
            mResultLabel.setTextSize(22);

        }
    }
}
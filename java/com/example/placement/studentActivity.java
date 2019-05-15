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

public class studentActivity extends AppCompatActivity {

    private TextView mProfileLabel;
    private TextView mHomeLabel;
    private TextView mApplicationLabel;
    private TextView mResultLabel;


    private ViewPager mMainPager;

    private PagerViewAdapter mPagerViewAdapter;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {

            sendToLogin();

        }

    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(studentActivity.this, MainActivity.class);
        startActivity(loginIntent);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mAuth = FirebaseAuth.getInstance();

        mProfileLabel = (TextView) findViewById(R.id.profileLabel);
        mHomeLabel = (TextView) findViewById(R.id.homeLabel);
        mApplicationLabel = (TextView) findViewById(R.id.applicationLabel);
        mResultLabel = (TextView) findViewById(R.id.resultLabel);

        mMainPager = (ViewPager) findViewById(R.id.mainPager);
        mMainPager.setOffscreenPageLimit(2);

        mPagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mMainPager.setAdapter(mPagerViewAdapter);

        mProfileLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMainPager.setCurrentItem(0);

            }
        });

        mHomeLabel.setOnClickListener(new View.OnClickListener() {
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

            mProfileLabel.setTextColor(getColor(R.color.textTabBright));
            mProfileLabel.setTextSize(22);

            mHomeLabel.setTextColor(getColor(R.color.textTabLight));
            mHomeLabel.setTextSize(16);

            mApplicationLabel.setTextColor(getColor(R.color.textTabLight));
            mApplicationLabel.setTextSize(16);

            mResultLabel.setTextColor(getColor(R.color.textTabLight));
            mResultLabel.setTextSize(16);

        }

        if (position == 1) {

            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mHomeLabel.setTextColor(getColor(R.color.textTabBright));
            mHomeLabel.setTextSize(22);

            mApplicationLabel.setTextColor(getColor(R.color.textTabLight));
            mApplicationLabel.setTextSize(16);

            mResultLabel.setTextColor(getColor(R.color.textTabLight));
            mResultLabel.setTextSize(16);

        }

        if (position == 2) {

            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mHomeLabel.setTextColor(getColor(R.color.textTabLight));
            mHomeLabel.setTextSize(16);

            mApplicationLabel.setTextColor(getColor(R.color.textTabBright));
            mApplicationLabel.setTextSize(22);

            mResultLabel.setTextColor(getColor(R.color.textTabLight));
            mResultLabel.setTextSize(16);

        }

        if (position == 3) {

            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mHomeLabel.setTextColor(getColor(R.color.textTabLight));
            mHomeLabel.setTextSize(16);

            mApplicationLabel.setTextColor(getColor(R.color.textTabLight));
            mApplicationLabel.setTextSize(16);

            mResultLabel.setTextColor(getColor(R.color.textTabBright));
            mResultLabel.setTextSize(22);

        }
    }
}
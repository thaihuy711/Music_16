package com.framgia.music_16.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.framgia.music_16.R;
import com.framgia.music_16.screen.BaseActivity;
import com.framgia.music_16.utils.Constant;

public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;
    private UnSwipeViewpager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBottomNavigationView = findViewById(R.id.navigation_bottom);
        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainAdapter);
        mViewPager.setPagingEnabled(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                mViewPager.setCurrentItem(Constant.Tab.TAB_HOME);
                break;
            case R.id.action_my_song:
                mViewPager.setCurrentItem(Constant.Tab.TAB_MY_SONG);
                break;
            case R.id.action_search:
                mViewPager.setCurrentItem(Constant.Tab.TAB_SEARCH);
                break;
            case R.id.action_artist:
                mViewPager.setCurrentItem(Constant.Tab.TAB_ARTIST);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        for (Fragment frag : fm.getFragments()) {
            if (frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack();
                    return;
                }
            }
        }
        super.onBackPressed();
    }
}

package com.trungnguyen.android.houston123.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.trungnguyen.android.houston123.base.AbsFragmentPagerAdapter;
import com.trungnguyen.android.houston123.ui.main.home.HomeFragment;
import com.trungnguyen.android.houston123.ui.main.personal.PersonalFragment;
import com.trungnguyen.android.houston123.ui.main.tool.ToolFragment;

/**
 * Created by trungnd4 on 18/08/2018.
 */
public class MainPagerAdapter extends AbsFragmentPagerAdapter {

    private static final int TAB_COUNT = 3;
    public static final int TAB_MAIN_INDEX = 0;
    public static final int TAB_TOOL_INDEX = 1;
    public static final int TAB_PERSONAL_INDEX = 2;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TAB_MAIN_INDEX:
                return HomeFragment.newInstance();
            case TAB_TOOL_INDEX:
                return ToolFragment.newInstance();
            case TAB_PERSONAL_INDEX:
                return PersonalFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

}

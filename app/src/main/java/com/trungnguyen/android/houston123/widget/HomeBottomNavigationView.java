package com.trungnguyen.android.houston123.widget;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.ui.main.MainPagerAdapter;
import com.trungnguyen.android.houston123.util.AndroidUtils;

/**
 * Created by trungnd4 on 18/08/2018.
 */

public class HomeBottomNavigationView extends BottomNavigationView implements BottomNavigationView.OnNavigationItemSelectedListener {
    public HomeBottomNavigationView(Context context) {
        this(context, null);
    }

    public HomeBottomNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ViewPager mPager;

    private void init() {
        inflateMenu(R.menu.bottom_navigation_items);
        changeBottomNavigationLayout();
        initTabIconFont();
        addLineSeparate();
        setOnNavigationItemSelectedListener(this);
    }

    private void changeBottomNavigationLayout() {
        int paddingBottom = (int) getResources().getDimension(R.dimen.spacing_tiny_s);
        View tabHome = findViewById(R.id.menu_home);
        tabHome.findViewById(android.support.design.R.id.icon).setPadding(0, 0, 0, paddingBottom);
        View tabNearby = findViewById(R.id.menu_transaction);
        tabNearby.findViewById(android.support.design.R.id.icon).setPadding(0, 0, 0, paddingBottom);
        View tabProfile = findViewById(R.id.menu_profile);
        tabProfile.findViewById(android.support.design.R.id.icon).setPadding(0, 0, 0, paddingBottom);
    }

    private void addLineSeparate() {
        View line = new View(getContext());
        line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blockchain_dark_blue));
        addView(line, new LayoutParams(LayoutParams.MATCH_PARENT, AndroidUtils.dp(0.5F)));
    }

//    private StateListDrawable createStateListDrawable(@StringRes int iconNameActive,
//                                                      @ColorRes int iconColorActive,
//                                                      @StringRes int iconNameNormal,
//                                                      @ColorRes int iconColorNormal) {
//        StateListDrawable stateListDrawable = new StateListDrawable();
//        stateListDrawable.addState(new int[]{android.R.attr.state_checked},
//                Drawable.createFromXml(res, ));
//        stateListDrawable.addState(new int[]{},
//                new BottomNavigationDrawable(getContext(), iconNameNormal, iconColorNormal));
//        return stateListDrawable;
//    }

    public void initTabIconFont() {
        Menu menu = getMenu();
//        menu.getItem(MainPagerAdapter.TAB_MAIN_INDEX).setIcon(createStateListDrawable());
//        menu.getItem(MainPagerAdapter.TAB_TOOL_INDEX).setIcon(createStateListDrawable());
//        menu.getItem(MainPagerAdapter.TAB_PERSONAL_INDEX).setIcon(createStateListDrawable());
    }

    public void setSelected(int position) {
        setSelectedInternal(position);
        if (position == MainPagerAdapter.TAB_MAIN_INDEX) {
            setSelectedItemId(R.id.menu_home);
        } else if (position == MainPagerAdapter.TAB_TOOL_INDEX) {
            setSelectedItemId(R.id.menu_transaction);
        } else if (position == MainPagerAdapter.TAB_PERSONAL_INDEX) {
            setSelectedItemId(R.id.menu_profile);
        }
    }

    private void setSelectedInternal(int position) {
        if (mPager == null) {
            throw new RuntimeException("Viewpager not initialized");
        }
        mPager.setCurrentItem(position, false);
    }

    public void setViewPager(@NonNull ViewPager pager) {
        mPager = pager;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (mPager == null) {
            return false;
        }

        switch (item.getItemId()) {
            case R.id.menu_home:
                setSelectedInternal(MainPagerAdapter.TAB_MAIN_INDEX);
                return true;
            case R.id.menu_transaction:
                setSelectedInternal(MainPagerAdapter.TAB_TOOL_INDEX);
                return true;
            case R.id.menu_profile:
                setSelectedInternal(MainPagerAdapter.TAB_PERSONAL_INDEX);
                return true;
        }

        return false;
    }
}

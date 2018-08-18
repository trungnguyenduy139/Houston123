package com.trungnguyen.android.houston123.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by trungnd4 on 18/08/2018.
 */

public abstract class AbsFragmentPagerAdapter extends FragmentPagerAdapter {

    private final SparseArrayCompat<WeakReference<Fragment>> holder;

    public AbsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.holder = new SparseArrayCompat<>(getCount());
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object item = super.instantiateItem(container, position);
        if (item instanceof Fragment) {
            holder.put(position, new WeakReference<>((Fragment) item));
        }
        return item;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        holder.remove(position);
        super.destroyItem(container, position, object);
    }

    @Nullable
    public Fragment getPage(int position) {
        final WeakReference<Fragment> weakRefItem = holder.get(position);
        return (weakRefItem != null) ? weakRefItem.get() : null;
    }

}

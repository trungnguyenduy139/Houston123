package com.trungnguyen.android.houston123.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    @NonNull
    private Runnable mLoadMoreRunnable = this::onLoadMore;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (!recyclerView.canScrollVertically(1)) {
            recyclerView.post(mLoadMoreRunnable);
        }
    }

    protected abstract void onLoadMore();
}

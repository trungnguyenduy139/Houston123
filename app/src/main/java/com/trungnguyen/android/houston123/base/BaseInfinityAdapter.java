package com.trungnguyen.android.houston123.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.InfinityAdapterType;

public abstract class BaseInfinityAdapter<L extends IAdapterListener> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context context;
    @Nullable
    protected L mListener;

    protected boolean mShowLoader = true;

    @Override
    public int getItemViewType(int position) {

        // loader can't be at position 0
        // loader can only be at the last position

        if (position != 0 && position == getItemCount() - 1 && enableInfinity() && mShowLoader) {
            return InfinityAdapterType.TYPE_LOADING;
        }

        return InfinityAdapterType.TYPE_NORMAL;
    }

    public void setLoaderState(boolean state) {
        this.mShowLoader = state;
    }

    @NonNull
    protected BaseViewHolder getLoadingViewHolder(@NonNull ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.loading_more, parent, false);
        return new LoaderViewHolder(rootView);
    }

    public void setListener(@Nullable L listener) {
        if (listener == null) {
            return;
        }
        mListener = listener;
    }

    public void releaseListener() {
        mListener = null;
    }

    @Override
    public int getItemCount() {
        // If no items are present, there's no need for loader
        if (getDataSize() == 0) {
            return 0;
        }
        int dataSize = getDataSize();
        // +1 for loader
        return enableInfinity() && mShowLoader ? dataSize + 1 : dataSize;
    }

    public abstract boolean enableInfinity();

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.onBind(position);
    }

    public class LoaderViewHolder extends BaseViewHolder {

        ProgressBar progressBar;

        LoaderViewHolder(@NonNull View root) {
            super(root);
            progressBar = root.findViewById(R.id.loading_more_progress_bar);
        }

        @Override
        public void onBind(int position) {
            // Empty onBind method
        }

        public void setVisible(int status) {
            progressBar.setVisibility(status);
        }
    }


    public abstract int getLayoutResource();

    public abstract int getDataSize();

}

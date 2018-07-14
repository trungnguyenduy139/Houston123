package com.trungnguyen.android.houston123.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.InfinityAdapterType;

public abstract class BaseInfinityAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context context;


    @Override
    public int getItemViewType(int position) {

        // loader can't be at position 0
        // loader can only be at the last position

        if (position != 0 && position == getItemCount() - 1) {
            return InfinityAdapterType.TYPE_LOADING;
        }

        return InfinityAdapterType.TYPE_NORMAL;
    }

    protected BaseViewHolder getLoadingViewHolder(@NonNull ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.loading_more, parent, false);
        return new LoaderViewHolder(rootView);
    }

    @Override
    public int getItemCount() {
        // If no items are present, there's no need for loader
        if (getDataSize() == 0) {
            return 0;
        }

        // +1 for loader
        return getDataSize() + 1;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.onBind(position);
    }

    public class LoaderViewHolder extends BaseViewHolder {

        ProgressBar progressBar;

        public LoaderViewHolder(View root) {
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

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

import java.util.List;

public abstract class BaseInfinityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;


    @Override
    public int getItemViewType(int position) {

        // loader can't be at position 0
        // loader can only be at the last position

        if (position != 0 && position == getItemCount() - 1) {
            return InfinityAdapterType.TYPE_LOADING;
        }

        return InfinityAdapterType.TYPE_NORMAL;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView;
        switch (viewType) {
            case InfinityAdapterType.TYPE_NORMAL:
                rootView = LayoutInflater.from(context).inflate(getLayoutResource(), parent, false);
                break;
            case InfinityAdapterType.TYPE_LOADING:
                rootView = LayoutInflater.from(context).inflate(R.layout.loading_more, parent, false);
                return new LoaderViewHolder(rootView);
            default:
                rootView = LayoutInflater.from(context).inflate(getLayoutResource(), parent, false);
                break;
        }
        return getViewHolder(rootView);
    }

    @Override
    public int getItemCount() {
        // If no items are present, there's no need for loader
        if (getData() == null || getData().size() == 0) {
            return 0;
        }

        // +1 for loader
        return getData().size() + 1;
    }

    public class LoaderViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoaderViewHolder(View root) {
            super(root);
            progressBar = root.findViewById(R.id.loading_more_progress_bar);
        }

        public void setVisible(int status) {
            progressBar.setVisibility(status);
        }
    }


    public abstract int getLayoutResource();

    public abstract int getDataSize();

    public abstract List<Object> getData();

    public abstract RecyclerView.ViewHolder getViewHolder(View root);

}

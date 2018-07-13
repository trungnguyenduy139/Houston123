package com.trungnguyen.android.houston123.ui.listuser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseInfinityAdapter;
import com.trungnguyen.android.houston123.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 13/07/2018.
 */
public class UserListAdapter extends BaseInfinityAdapter {

    private List<UserModel> mListUser;

    public UserListAdapter(List<UserModel> listUser) {
        mListUser = listUser;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.user_list_item;
    }

    @Override
    public int getDataSize() {
        return mListUser.size();
    }

    @Override
    public List<Object> getData() {
        return new ArrayList<>(mListUser);
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View root) {
        return new UserListViewHolder(root);
    }

    public class UserListViewHolder extends BaseViewHolder {

        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(int position) {

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }
}

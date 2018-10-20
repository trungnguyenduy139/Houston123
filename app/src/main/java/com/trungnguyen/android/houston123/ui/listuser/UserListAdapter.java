package com.trungnguyen.android.houston123.ui.listuser;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.InfinityAdapterType;
import com.trungnguyen.android.houston123.base.BaseInfinityAdapter;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.base.BaseViewHolder;
import com.trungnguyen.android.houston123.databinding.UserListItemBinding;
import com.trungnguyen.android.houston123.util.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import io.reactivex.Observable;

/**
 * Created by trungnd4 on 13/07/2018.
 */

public class UserListAdapter<U extends BaseUserModel> extends BaseInfinityAdapter<UserListListener> {

    private List<U> mListUser;

    private List<U> mFilterList = new ArrayList<>();

    UserListAdapter(List<U> listUser) {
        mListUser = listUser;
        mFilterList.addAll(listUser);
    }

    @Override
    public boolean enableInfinity() {
        return true;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.user_list_item;
    }

    @Override
    public int getDataSize() {
        if (Lists.isEmptyOrNull(mListUser)) {
            return 0;
        }
        return mListUser.size();
    }

    public void addItems(@Nullable Collection<? extends U> lecturerModels) {
        if (Lists.isEmptyOrNull(lecturerModels)) {
            return;
        }
        mListUser.addAll(lecturerModels);
        mFilterList.addAll(lecturerModels);
        notifyDataSetChanged();
    }

    public void removeUser(int position) {
        if (Lists.isEmptyOrNull(mListUser)) {
            return;
        }
        mListUser.remove(position);
        mFilterList.remove(position);
        notifyDataSetChanged();
    }

    Observable<String> searchAction(String searchSequence) {

        final String textSearchSequence = searchSequence.toLowerCase(Locale.getDefault());

        return Observable.just(textSearchSequence)
                .map(string -> {
                    mListUser.clear();
                    return string;
                })
                .filter(string -> !Lists.isEmptyOrNull(mFilterList))
                .flatMap(string -> {
                    if (TextUtils.isEmpty(string)) {
                        mListUser.addAll(mFilterList);
                    } else {
                        return Observable.just(mFilterList)
                                .flatMapIterable(list -> list)
                                .flatMap(item -> {
                                    if (item.getName().toLowerCase(Locale.getDefault()).contains(textSearchSequence)) {
                                        mListUser.add(item);
                                    }
                                    return Observable.just(string);
                                });
                    }
                    return Observable.just(string);
                });

    }

    public class UserListViewHolder extends BaseViewHolder implements UserItemViewModel.OnUserClickListener {

        private UserItemViewModel userItemViewModel;

        private UserListItemBinding mBinding;

        UserListViewHolder(@NonNull UserListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final BaseUserModel user = mListUser.get(position);

            userItemViewModel = new UserItemViewModel(user, this);

            mBinding.setViewModel(userItemViewModel);

            mBinding.executePendingBindings();

        }

        @Override
        public void onItemClick() {
            if (mListener == null) {
                return;
            }
            int position = getAdapterPosition();
            mListener.onItemClick(mListUser.get(position), position);
        }

        @Override
        public boolean onUserLongClick() {
            if (mListener == null) {
                return false;
            }
            int position = getAdapterPosition();
            mListener.onItemLongClick(position);
            return true;
        }
    }

    @UiThread
    public void clearItems() {
        if (mListUser == null) {
            return;
        }
        mListUser.clear();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView;
        switch (viewType) {
            case InfinityAdapterType.TYPE_NORMAL:
                rootView = LayoutInflater.from(context).inflate(getLayoutResource(), parent, false);
                break;
            case InfinityAdapterType.TYPE_LOADING:
                return getLoadingViewHolder(parent);
            default:
                rootView = LayoutInflater.from(context).inflate(getLayoutResource(), parent, false);
                break;
        }
        return new UserListViewHolder(Objects.requireNonNull(DataBindingUtil.bind(rootView)));
    }
}

package com.trungnguyen.android.houston123.ui.userdetail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.AbsCommonAdapter;
import com.trungnguyen.android.houston123.base.BaseViewHolder;
import com.trungnguyen.android.houston123.databinding.UserDetailItemBinding;

import java.util.List;
import java.util.Objects;

/**
 * Created by trungnd4 on 14/08/2018.
 */
public class UserDetailAdapter extends AbsCommonAdapter<UserDetailListener, ItemDetailModel> {


    public UserDetailAdapter(List<ItemDetailModel> dataList) {
        super(dataList);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View rootView = LayoutInflater.from(context).inflate(getLayoutResource(), viewGroup, false);
        return new UserDetailAdapter.UserDetailViewHolder(Objects.requireNonNull(DataBindingUtil.bind(rootView)));
    }

    @Override
    public boolean enableInfinity() {
        return false;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.user_detail_item;
    }

    public class UserDetailViewHolder extends BaseViewHolder {

        private UserDetailItemViewModel userDetailItemViewModel;

        private UserDetailItemBinding mBinding;

        UserDetailViewHolder(@NonNull UserDetailItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ItemDetailModel itemDetailModel = mDataList.get(position);

            userDetailItemViewModel = new UserDetailItemViewModel(itemDetailModel);

            mBinding.setViewModel(userDetailItemViewModel);

            mBinding.executePendingBindings();

        }
    }
}

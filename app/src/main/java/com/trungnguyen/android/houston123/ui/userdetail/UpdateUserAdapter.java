package com.trungnguyen.android.houston123.ui.userdetail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseViewHolder;
import com.trungnguyen.android.houston123.databinding.UpdateUserDetailItemBinding;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ManagerModel;

import java.util.List;
import java.util.Objects;

/**
 * Created by trungnd4 on 26/08/2018.
 */
public class UpdateUserAdapter extends UserDetailAdapter {

    public UpdateUserAdapter(@NonNull List<ItemDetailModel> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.update_user_detail_item;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View rootView = LayoutInflater.from(context).inflate(getLayoutResource(), viewGroup, false);
        return new UpdateUserAdapter.UpdateUserItemViewHolder(Objects.requireNonNull(DataBindingUtil.bind(rootView)));
    }

//    public BaseModel getModelData(int userCode) {
//        for (int index = 0; index < mDataList.size(); index++) {
//
//        }
//    }

    public class UpdateUserItemViewHolder extends BaseViewHolder {

        private UpdateUserItemViewModel userDetailItemViewModel;

        private UpdateUserDetailItemBinding mBinding;

        UpdateUserItemViewHolder(@NonNull UpdateUserDetailItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ItemDetailModel itemDetailModel = mDataList.get(position);

            userDetailItemViewModel = new UpdateUserItemViewModel(itemDetailModel);

            mBinding.setViewModel(userDetailItemViewModel);

            mBinding.executePendingBindings();

        }
    }
}

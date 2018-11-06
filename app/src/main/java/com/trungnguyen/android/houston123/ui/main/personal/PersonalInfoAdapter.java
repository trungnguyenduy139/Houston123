package com.trungnguyen.android.houston123.ui.main.personal;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.AbsCommonAdapter;
import com.trungnguyen.android.houston123.base.BaseViewHolder;
import com.trungnguyen.android.houston123.base.IAdapterListener;
import com.trungnguyen.android.houston123.databinding.UserDetailItemBinding;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.ui.userdetail.UserDetailItemViewModel;

import java.util.List;
import java.util.Objects;

/**
 * Created by trungnd4 on 06/11/2018.
 */
public class PersonalInfoAdapter extends AbsCommonAdapter<IAdapterListener, ItemDetailModel> {

    public PersonalInfoAdapter(@NonNull List<ItemDetailModel> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.user_detail_item;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View rootView = LayoutInflater.from(context).inflate(getLayoutResource(), viewGroup, false);
        return new PersonalInfoAdapter.PersonalInfoViewHolder(Objects.requireNonNull(DataBindingUtil.bind(rootView)));
    }

    public class PersonalInfoViewHolder extends BaseViewHolder {

        private UserDetailItemViewModel userDetailItemViewModel;

        private UserDetailItemBinding mBinding;

        PersonalInfoViewHolder(@NonNull UserDetailItemBinding binding) {
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

package com.trungnguyen.android.houston123.ui.userdetail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseViewHolder;
import com.trungnguyen.android.houston123.databinding.UpdateUserDetailItemBinding;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by trungnd4 on 26/08/2018.
 */
public class UpdateUserAdapter extends UserDetailAdapter {

    private UpdateUserDetailItemBinding mBinding;

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

    public class UpdateUserItemViewHolder extends BaseViewHolder {

        private UpdateUserItemViewModel userDetailItemViewModel;


        UpdateUserItemViewHolder(@NonNull UpdateUserDetailItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
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

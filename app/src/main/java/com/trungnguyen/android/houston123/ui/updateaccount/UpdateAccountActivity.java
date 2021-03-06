package com.trungnguyen.android.houston123.ui.updateaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.data.LoginInfoModel;
import com.trungnguyen.android.houston123.databinding.ActivityUpdateAccountBinding;
import com.trungnguyen.android.houston123.injection.Injector;
import com.trungnguyen.android.houston123.injection.UserComponent;
import com.trungnguyen.android.houston123.ui.userdetail.ItemDetailModel;
import com.trungnguyen.android.houston123.util.Lists;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 20/12/2018.
 */
public class UpdateAccountActivity extends BaseToolbarActivity<ActivityUpdateAccountBinding, UpdateAccountViewModel> implements IUpdateAccountView {


    private LoginInfoModel mLoginModel;
    private List<AppCompatEditText> mListOfValueEditText = new ArrayList<>();
    protected LinearLayout mDetailContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetailContainer = findViewById(R.id.detail_container_update_account);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_update_account;
    }

    @Override
    public void initData() {
        super.initData();
        UserComponent userComponent = Injector.getInstance().getUserComponent();

        if (userComponent != null) {
            mLoginModel = userComponent.getLoginModel();
        }

        if (mLoginModel != null) {
            viewModel.loadUserInfoResource(mLoginModel);
        }
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }

    @Override
    public void onLoadResourceCompleted(List<ItemDetailModel> list) {
        if (Lists.isEmptyOrNull(list)) {
            return;
        }
        ViewGroup view;
        for (ItemDetailModel item : list) {
            view = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.update_user_detail_item, null);
            LinearLayout container = (LinearLayout) view.getChildAt(0);// as LinearLayout
            TextView tvKey = (AppCompatTextView) container.getChildAt(0);// as AppCompatTextView
            AppCompatEditText tvVal = (AppCompatEditText) container.getChildAt(1);//as AppCompatEditText
            mListOfValueEditText.add(tvVal);
            tvKey.setText(item.getKey());
            tvVal.setText(item.getValue());
            mDetailContainer.addView(view);
            mDetailContainer.invalidate();
        }
    }

    @Override
    public void updateAccountLogin() {
        if (mLoginModel == null) {
            return;
        }
        mLoginModel = mLoginModel.updateModelLoginInfo(getListOfValue());
        viewModel.startUpdateAccountFlow(mLoginModel);
    }

    private List<String> getListOfValue() {
        List<String> listVal = new ArrayList<>();
        for (AppCompatEditText editText : mListOfValueEditText) {
            Editable editable = editText.getText();
            if (editable == null) {
                continue;
            }
            listVal.add(editable.toString());
        }
        return listVal;
    }

    @Override
    public void updateSuccess(String message) {
        mDetailContainer.removeAllViews();
        if (mLoginModel != null) {
            viewModel.loadUserInfoResource(mLoginModel);
        }
        Intent intent = new Intent();
        intent.putExtra("RESULT_INFO", mLoginModel);
        setResult(666, intent);
        Injector.getInstance().createUserScope(mLoginModel);
        new SweetAlertDialog(this).setContentText(getString(R.string.update_user_success)).show();
    }
}

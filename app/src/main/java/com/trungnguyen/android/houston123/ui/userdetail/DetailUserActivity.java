package com.trungnguyen.android.houston123.ui.userdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.trungnguyen.android.houston123.BR;
import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.DetailServiceType;
import com.trungnguyen.android.houston123.anotation.UserType;
import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseToolbarActivity;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.bus.DeletedUserEvent;
import com.trungnguyen.android.houston123.data.ClassResponse;
import com.trungnguyen.android.houston123.databinding.ActivityDetailUserBinding;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel;
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel;
import com.trungnguyen.android.houston123.util.BundleConstants;
import com.trungnguyen.android.houston123.util.Constants;
import com.trungnguyen.android.houston123.util.Lists;
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DetailUserActivity extends BaseToolbarActivity<ActivityDetailUserBinding, DetailUserViewModel>
        implements IDetailUserView {

    @NonNull
    private List<ItemDetailModel> mItemDetailList = new ArrayList<>();
    private UserDetailAdapter mUserDetailAdapter;
    private BaseModel mUserModel;
    private int mCode;
    private int mPosition;

    @Inject
    EventBus mEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        BaseModel baseUserModel = null;
        if (bundle != null) {
            mPosition = bundle.getInt(BundleConstants.KEY_POSITION_USER);
            baseUserModel = (BaseModel) bundle.getSerializable(BundleConstants.KEY_USER_DETAIL);
            mCode = bundle.getInt(BundleConstants.KEY_CODE_DETAIL, Constants.DEFAULT_CODE_VALUE);
        }
        if (baseUserModel != null) {
            viewModel.setLecturerModel(baseUserModel);
            viewModel.initDetailList(baseUserModel);
            mUserModel = baseUserModel;
        }

        mUserDetailAdapter = new UserDetailAdapter(mItemDetailList);

        viewModel.attachAdapter(mUserDetailAdapter);

        binding.detailUserRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.detailUserRecycler.setAdapter(mUserDetailAdapter);

        setTitle(getResources().getString(R.string.user_detail));

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_detail_user;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public BaseUserModel getUserModel() {
        return null;
    }

    @Override
    public void updateResourceList(List<ItemDetailModel> list) {
        if (Lists.isEmptyOrNull(list) || mUserDetailAdapter == null) {
            return;
        }
        mUserDetailAdapter.addItems(list);
    }

    @Override
    public void deleteUserSuccess() {
        mEventBus.post(new DeletedUserEvent(mPosition));
        finish();
    }

    @Override
    public void deleteUserFailed() {
        new SweetAlertDialog(this)
                .setContentText(getString(R.string.delete_user_failed)).
                setConfirmText(getString(R.string.close_dialog)).
                setConfirmClickListener(sweetAlertDialog1 -> {
                    if (sweetAlertDialog1 != null) {
                        sweetAlertDialog1.dismissWithAnimation();
                    }
                }).show();
    }

    @Override
    public void loadClassOfLecturerSuccess(List<ClassResponse> dataList) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        int menuId;
        switch (mCode) {
            case UserType.LECTURER:
                menuId = R.menu.teacher_menu;
                break;
            case UserType.CLAZZ:
                menuId = R.menu.student_class_menu;
                break;
            default:
                menuId = R.menu.detail_user_menu;
                break;
        }
        inflater.inflate(menuId, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.detail_update:
                viewModel.onUpdateClick(DetailServiceType.START_UPDATE, null);
                break;
            case R.id.detail_delete:
                String modelId = mUserModel.getModelId();
                viewModel.deleteCurrentUser(mCode, modelId);
                break;
            case R.id.detail_class_teacher:
                if (mUserModel instanceof LecturerModel) {
                    viewModel.classOfLecturer(mUserModel.getModelId());
                }
                break;

            case R.id.student_in_class:
                if (mUserModel instanceof ClassModel) {
                    viewModel.studentInClass(mUserModel.getModelId());
                }
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void initParam() {
        getDataManagerComponent().inject(this);
    }
}

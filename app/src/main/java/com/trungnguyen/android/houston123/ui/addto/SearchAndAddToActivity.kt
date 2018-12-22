package com.trungnguyen.android.houston123.ui.addto

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.trungnguyen.android.houston123.BR
import com.trungnguyen.android.houston123.R
import com.trungnguyen.android.houston123.base.BaseModel
import com.trungnguyen.android.houston123.base.BaseToolbarActivity
import com.trungnguyen.android.houston123.databinding.ActivitySearchAndAddBinding
import com.trungnguyen.android.houston123.ui.listuser.UserListAdapter
import com.trungnguyen.android.houston123.ui.listuser.UserListListener
import com.trungnguyen.android.houston123.widget.sweetalert.SweetAlertDialog
import java.util.*

/**
 * Created by trungnd4 on 23/12/2018.
 */
class SearchAndAddToActivity : BaseToolbarActivity<ActivitySearchAndAddBinding, SearchAndAddToViewModel>(), ISearchAndAddToView, UserListListener {

    private var mDataAdapter: UserListAdapter<BaseModel>? = null

    override fun onItemLongClick(position: Int) {
        // Do nothing for now
    }

    override fun onItemClick(baseUserModel: BaseModel?, position: Int) {
        SweetAlertDialog(this)
                .setTitleText(getString(R.string.add_to_title))
                .setContentText(getString(R.string.add_to_message))
                .setConfirmText(getString(R.string.dialog_ok))
                .setCancelText(getString(R.string.close_dialog))
                .setCancelClickListener { it.dismissWithAnimation() }
                .setConfirmClickListener { viewModel.onAddUserAction() }
                .show()
    }

    override fun initContentView(savedInstanceState: Bundle): Int {
        return R.layout.activity_search_and_add
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initParam() {
        dataManagerComponent.inject(this)
    }

    override fun initData() {
        super.initData()
        binding.searchListRecycler.layoutManager = LinearLayoutManager(this)
        mDataAdapter = UserListAdapter(Collections.emptyList())
        mDataAdapter?.setListener(this)
    }

    override fun onSearchCompleted(userModels: Collection<BaseModel>) {
        if (binding.searchListRecycler.visibility == View.INVISIBLE) {
            binding.searchListRecycler.visibility = View.VISIBLE
            binding.tvEmptyData.visibility = View.INVISIBLE
        }
        mDataAdapter?.addItems(userModels)
    }
}

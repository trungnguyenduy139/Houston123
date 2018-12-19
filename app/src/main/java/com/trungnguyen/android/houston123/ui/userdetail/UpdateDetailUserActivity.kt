package com.trungnguyen.android.houston123.ui.userdetail

import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import com.trungnguyen.android.houston123.R
import com.trungnguyen.android.houston123.anotation.DetailServiceType
import com.trungnguyen.android.houston123.base.BaseModel
import com.trungnguyen.android.houston123.util.BundleConstants
import com.trungnguyen.android.houston123.util.Constants
import com.trungnguyen.android.houston123.util.Lists
import com.trungnguyen.android.houston123.util.ModelResourceLoader
import java.util.*

/**
 * Created by trungnd4 on 26/08/2018.
 */
class UpdateDetailUserActivity : DetailUserActivity() {

    private var mIsAddNew = false
    private var mUserCode = Constants.DEFAULT_CODE_VALUE
    private val mListOfValueEditText = ArrayList<AppCompatEditText>()

    private val listOfVal: List<String> = mListOfValueEditText.map { it.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        var baseUserModel: BaseModel? = null
        if (bundle != null) {
            mIsAddNew = bundle.containsKey(BundleConstants.ADD_NEW_USER_BUNDLE)
            if (mIsAddNew) {
                mUserCode = bundle.getInt(BundleConstants.ADD_NEW_USER_BUNDLE)
            } else
                baseUserModel = bundle.getSerializable(BundleConstants.KEY_UPDATE_USER_DETAIL) as BaseModel
        }
        if (baseUserModel != null) {
            viewModel.setLecturerModel(baseUserModel)
            viewModel.initDetailList(baseUserModel)
        } else {
            updateResourceList(ModelResourceLoader.loadEmptyResources(mUserCode))
        }

        viewModel.setApply(if (baseUserModel == null && mIsAddNew) DetailServiceType.START_NEW else DetailServiceType.DO_UPDATE)

        title = resources.getString(R.string.update_user_detail)
    }

    override fun updateResourceList(list: List<ItemDetailModel>) {
        if (Lists.isEmptyOrNull(list)) {
            return
        }
        var view: ViewGroup
        for (item in list) {
            view = LayoutInflater.from(this).inflate(R.layout.update_user_detail_item, null) as ViewGroup
            val container = view.getChildAt(0) as LinearLayout
            val tvKey = container.getChildAt(0) as AppCompatTextView
            val tvVal = container.getChildAt(1) as AppCompatEditText
            mListOfValueEditText.add(tvVal)
            tvKey.text = item.key
            tvVal.setText(item.value)

            mDetailContainer.addView(view)

            mDetailContainer.invalidate()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuId = if (mIsAddNew) R.menu.detail_add_new_menu else R.menu.detail_update_menu
        val inflater = menuInflater
        inflater.inflate(menuId, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.add_new_action -> {
                val model = ModelResourceLoader.convertModel(mUserCode, listOfVal)
                viewModel.onAddNewClicked(mUserCode, model)
            }
            R.id.update_action -> {
                val model = ModelResourceLoader.convertModel(mUserCode, listOfVal)
                viewModel.onUpdateClick(DetailServiceType.DO_UPDATE, model)
            }
            else -> {
            }
        }
        return false
    }
}
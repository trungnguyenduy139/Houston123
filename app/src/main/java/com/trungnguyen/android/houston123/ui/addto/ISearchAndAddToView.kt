package com.trungnguyen.android.houston123.ui.addto

import com.trungnguyen.android.houston123.base.BaseModel
import com.trungnguyen.android.houston123.base.IBaseView

/**
 * Created by trungnd4 on 23/12/2018.
 */
interface ISearchAndAddToView : IBaseView {
    fun onSearchCompleted(userModels: Collection<BaseModel>)
}

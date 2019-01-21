package com.trungnguyen.android.houston123.util

import com.trungnguyen.android.houston123.anotation.UserType
import com.trungnguyen.android.houston123.base.BaseModel
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.ClassModel
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.DetailClassModel
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.LecturerModel
import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel

/**
 * Created by trungnd4 on 23/12/2018.
 */
object AddUserUtils {
    fun transformClassModel(model: BaseModel, modelId: String): ClassModel {
        val classModelSource = model as ClassModel
        return ClassModel(classModelSource.mainContent, classModelSource.subCotent, classModelSource.modelId, modelId, classModelSource.departmen, classModelSource.startDate, classModelSource.endDate)
    }


    fun tranformDetailClsasModel(model: BaseModel, modelId: String): DetailClassModel {
        val classModel = model as ClassModel
        return DetailClassModel(classModel.modelId, modelId, "", "", "", "")
    }

    fun getCodeFromModel(model: BaseModel): Int {
        return when (model) {
            is LecturerModel -> UserType.LECTURER
            is StudentModel -> UserType.STUDENT
            is ClassModel -> UserType.CLAZZ
            else -> Constants.DEFAULT_CODE_VALUE
        }

    }
}

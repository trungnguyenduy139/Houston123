package com.trungnguyen.android.houston123.util

object Constants {
    const val APP_NAME = "Houston Android"
    // App helper API const
    const val CONNECTION_POOL_DOWNLOAD_COUNT = 1
    const val CONNECTION_KEEP_ALIVE_DOWNLOAD_DURATION = 5
    const val CONNECTION_POOL_COUNT = 5
    const val CONNECTION_KEEP_ALIVE_DURATION: Long = 5
    const val EMPTY = ""
    const val DEFAULT_CODE_VALUE = -1
    const val TOKEN_PREFIX = "Bearer"
    const val SPACE = " "
    const val FIRST_PAGE_REQUEST = 1
    const val LOADING_MORE_ERROR = -5

    // API url const
    object LoginApi {
        const val LOGIN = "login"
        const val LOGOUT = "logout"
        const val ACCOUNT_INFO = "account"
        const val CHANGE_PASSWORD = "change_pass"
    }

    object Api {
        const val LECTURER = "giaovien"
        const val STUDENT = "hocvien"
        const val MANAGER = "quanly"
        const val CLAZZ = "lophoc"
        const val SUBJECT = "monhoc"
        const val DETAIL_CLASS = "chitietlop"
    }

    object Marketing {
        const val PREDICT = "predict"

    }

    object ServerCode {
        const val SUCCESS = 200
        const val FAILED = -200
    }


    object UpdateFlowAction {
        const val STUDENT_IN_CLAZZ = 0
        const val CLAZZ_IS_LEARNING_SUBJECT = 1
        const val CLAZZ_OF_LECTURER = 2
    }
}

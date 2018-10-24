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

    // API url const
    object LoginApi {
        const val LOGIN = "login"
        const val LOGOUT = "logout"
    }

    object Api {
        const val LECTURER = "giaovien"
        const val STUDENT = "hocvien"
        const val MANAGER = "quanly"
        const val CLAZZ = "lophoc"
    }

    object ServerCode {
        const val DELETE_SUCCESS_MESSAGE = "Account deleted successfully"
        const val SUCCESS = 200
        const val FAILED = 0
    }
}

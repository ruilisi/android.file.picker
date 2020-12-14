package com.rls.pickfile.android.utils

import java.io.File

/**
 * Created by Ryan on 2020/12/14.
 */
object FileUtils {
    fun getParentOrNull(file: File): File? {
        return if (file.parent == null) {
            null
        } else file.parentFile
    }

}
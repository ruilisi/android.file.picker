package com.rls.pickfile.android.helper

import java.io.File
import java.io.Serializable

/**
 * Created by Ryan on 2020/12/15.
 */
interface FileFilter : Serializable {
    fun accept(pathname: File?): Boolean
}
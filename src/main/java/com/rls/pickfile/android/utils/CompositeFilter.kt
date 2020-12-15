package com.rls.pickfile.android.utils

import com.rls.pickfile.android.helper.FileFilter
import java.io.File

/**
 * Created by Ryan on 2020/12/15.
 */
class CompositeFilter(private val mFilters: List<FileFilter>?) : FileFilter {

    override fun accept(pathname: File?): Boolean {
        for (filter in mFilters!!) {
            if (!filter.accept(pathname)) {
                return false
            }
        }
        return true
    }
}
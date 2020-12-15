package com.rls.pickfile.android.utils

import com.rls.pickfile.android.helper.FileFilter
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Ryan on 2020/12/14.
 */
object FileUtils {
    fun getParentOrNull(file: File): File? {
        return if (file.parent == null) {
            null
        } else file.parentFile
    }

    fun getFileList(directory: File?, filter: FileFilter?): List<File> {
        if (filter == null) {
            return directory?.listFiles()?.toList() ?: listOf()
        } else {
            return directory?.listFiles(filter::accept)?.toList() ?: listOf()
        }
    }

    fun sortedFileList(list: List<File>, comparator: FileComparator): List<File> {
        return list.sortedWith(comparator)
    }

}
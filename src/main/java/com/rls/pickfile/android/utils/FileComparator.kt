package com.rls.pickfile.android.utils

import java.io.File
import java.util.*

/**
 * Created by Ryan on 2020/12/14.
 */
class FileComparator : Comparator<File> {
    override fun compare(f1: File, f2: File): Int {
        if (f1 === f2) {
            return 0
        }
        if (f1.isDirectory && f2.isFile) {
            // Show directories above files
            return -1
        }
        return if (f1.isFile && f2.isDirectory) {
            // Show files below directories
            1
        } else f1.name.compareTo(f2.name, ignoreCase = true)
        // Sort the directories alphabetically
    }
}
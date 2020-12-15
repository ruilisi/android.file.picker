package com.rls.pickfile.android.viewmodel

import android.app.Activity
import android.os.Environment
import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.rls.pickfile.android.helper.AndroidPermissionsHelper
import com.rls.pickfile.android.helper.FileFilter
import com.rls.pickfile.android.utils.CompositeFilter
import com.rls.pickfile.android.utils.HiddenFilter
import java.io.File
import java.util.ArrayList

/**
 * Created by Ryan on 2020/12/12.
 */
class FilePickerViewModel : ViewModel() {

    var title: ObservableField<String> = ObservableField("File Picker")

    val mStart = Environment.getExternalStorageDirectory()
    var mCurrent = mStart
    val pathMap: MutableMap<String, MutableList<File>> = HashMap()

    companion object {
        const val RESULT_FILE_PATH = "result_file_path"
    }

    var mFileFilter: CompositeFilter

    init {
        mFileFilter = getFilter()
    }

    private fun getFilter(): CompositeFilter {
        val filters: MutableList<FileFilter> = ArrayList<FileFilter>()
        filters.add(HiddenFilter())
        return CompositeFilter(filters)
    }
}
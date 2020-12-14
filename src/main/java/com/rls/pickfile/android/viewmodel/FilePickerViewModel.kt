package com.rls.pickfile.android.viewmodel

import android.app.Activity
import android.os.Environment
import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.rls.pickfile.android.helper.AndroidPermissionsHelper

/**
 * Created by Ryan on 2020/12/12.
 */
class FilePickerViewModel : ViewModel() {

    var title: String? = "File Picker"

    val mStart = Environment.getExternalStorageDirectory()
    val mCurrent = mStart

    companion object {
        const val RESULT_FILE_PATH = "result_file_path"
    }
}
package com.rls.pickfile.android.viewmodel

import android.app.Activity
import android.os.Environment
import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.rls.pickfile.android.helper.AndroidPermissionsHelper
import java.io.File

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
}
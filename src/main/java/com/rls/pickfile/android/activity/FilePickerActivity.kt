package com.rls.pickfile.android.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rls.pickfile.android.R
import com.rls.pickfile.android.databinding.ActivityFilePickerBinding
import com.rls.pickfile.android.fragment.DirectoryFragment
import com.rls.pickfile.android.helper.AndroidPermissionsHelper
import com.rls.pickfile.android.utils.FileUtils
import com.rls.pickfile.android.viewmodel.FilePickerViewModel
import java.io.File
import java.util.*

/**
 * Created by Ryan on 2020/12/12.
 */
class FilePickerActivity : AppCompatActivity() {

    private var mBinding: ActivityFilePickerBinding? = null
    private var mViewModel: FilePickerViewModel? = null
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_file_picker)
        mViewModel = ViewModelProvider(this).get(FilePickerViewModel::class.java)
        mBinding?.viewModel = mViewModel
        mBinding?.lifecycleOwner = this
        this.savedInstanceState = savedInstanceState
        checkStorePremission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == AndroidPermissionsHelper.READ_EXTERNAL_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                initFilePicker()
            } else {
                showError()
            }
        }
    }

    private fun checkStorePremission() {
        if (AndroidPermissionsHelper.hasReadExternalStoragePermission(this)) {
            initFilePicker()
        } else {
            AndroidPermissionsHelper.getReadExternalStoragePermission(this)
        }
    }

    private fun initFilePicker() {
        updateTitle()
        if (savedInstanceState == null) {
            initBackStackState()
        }
    }

    private fun initBackStackState() {
        val path: MutableList<File> = ArrayList()
        var current: File? = mViewModel?.mCurrent

        while (current != null) {
            path.add(current)
            if (current == mViewModel?.mStart) {
                break
            }
            current = FileUtils.getParentOrNull(current)
        }
        path.reverse()

        for (file in path) {
            addFragmentToBackStack(file)
        }
    }

    private fun addFragmentToBackStack(file: File) {
        supportFragmentManager
                .beginTransaction()
                .replace(
                        R.id.container,
                        DirectoryFragment.getInstance(file)
                )
                .addToBackStack(null)
                .commit()
    }


    private fun updateTitle() {
        mViewModel?.title = mViewModel?.mCurrent?.absolutePath ?: mViewModel?.title
    }


    private fun showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}
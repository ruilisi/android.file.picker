package com.rls.pickfile.android.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rls.pickfile.android.R
import com.rls.pickfile.android.databinding.ActivityFilePickerBinding
import com.rls.pickfile.android.helper.AndroidPermissionsHelper
import com.rls.pickfile.android.viewmodel.FilePickerViewModel

/**
 * Created by Ryan on 2020/12/12.
 */
class FilePickerActivity : AppCompatActivity() {

    private var mBinding: ActivityFilePickerBinding? = null
    private var mViewModel: FilePickerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_file_picker)
        mViewModel = ViewModelProvider(this).get(FilePickerViewModel::class.java)
        mBinding?.viewModel = mViewModel
        mBinding?.lifecycleOwner = this

        checkStorePremission()
    }

    private fun checkStorePremission() {
        if (AndroidPermissionsHelper.hasReadExternalStoragePermission(this)) {
            openFilePicker()
        } else {
            AndroidPermissionsHelper.getReadExternalStoragePermission(this)
        }
    }

    private fun openFilePicker() {
        Toast.makeText(this, "do next", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == AndroidPermissionsHelper.READ_EXTERNAL_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                openFilePicker()
            } else {
                showError()
            }
        }
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
package com.rls.pickfile.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rls.pickfile.android.R
import com.rls.pickfile.android.databinding.ActivityFilePickerBinding
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
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}
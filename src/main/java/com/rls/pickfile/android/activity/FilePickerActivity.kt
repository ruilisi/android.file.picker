package com.rls.pickfile.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rls.pickfile.android.R

/**
 * Created by Ryan on 2020/12/12.
 */
class FilePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_picker)
    }
}
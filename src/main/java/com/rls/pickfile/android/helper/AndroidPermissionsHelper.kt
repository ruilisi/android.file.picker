package com.rls.pickfile.android.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.ContextThemeWrapper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object AndroidPermissionsHelper {

    const val WRITE_EXTERNAL_STORAGE_CODE_IMAGE = 1
    const val WRITE_EXTERNAL_STORAGE_CODE_PHOTO = 2
    const val CAMERA_CODE = 2
    const val READ_EXTERNAL_CODE = 3

    private fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
                context,
                permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(context: Activity, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(context, arrayOf(permission), requestCode)
    }

    fun hasCameraPermission(context: Context): Boolean {
        return checkPermission(context, Manifest.permission.CAMERA)
    }

    fun getCameraPermission(activity: Activity) {
        requestPermission(activity, Manifest.permission.CAMERA, CAMERA_CODE)
    }

    fun hasWriteExternalStoragePermission(context: Context): Boolean {
        return checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun hasReadExternalStoragePermission(context: Context): Boolean {
        return checkPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    fun getReadExternalStoragePermission(activity: Activity) {
        requestPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE, READ_EXTERNAL_CODE)
    }
}
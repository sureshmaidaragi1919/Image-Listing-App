package com.maida.imagelistapp.utils

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


const val REQUEST_CODE_IMAGE = 100
const val REQUEST_CODE_PERMISSIONS = 101

const val KEY_PERMISSIONS_REQUEST_COUNT = "KEY_PERMISSIONS_REQUEST_COUNT"
const val MAX_NUMBER_REQUEST_PERMISSIONS = 2

@JvmField
val permissions = listOf(
    permission.READ_EXTERNAL_STORAGE,
    permission.WRITE_EXTERNAL_STORAGE
)

@JvmField
var permissionRequestCount: Int = 0

fun isPermissionGranted(context: Context, permission: String) =
    ContextCompat.checkSelfPermission(context, permission) ==
            PackageManager.PERMISSION_GRANTED
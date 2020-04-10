package com.mfsoftware.home.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Device (val id: String, val localIp: String, val type: Int, val room: String?): Parcelable
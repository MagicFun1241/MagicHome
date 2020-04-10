package com.mfsoftware.home.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Home (val id: String, val address: String, val localIp: String): Parcelable
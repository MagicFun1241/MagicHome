package com.mfsoftware.home.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Room (val id: String, val name: String): Parcelable
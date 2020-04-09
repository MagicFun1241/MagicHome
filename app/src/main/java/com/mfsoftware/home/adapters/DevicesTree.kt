package com.mfsoftware.home.adapters

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DevicesTree(val rooms: ArrayList<Room>?, val devices: ArrayList<Device>?) : Parcelable {
    @Parcelize
    data class Device(val localIp: String): Parcelable

    @Parcelize
    data class Room(val devices: ArrayList<Device>): Parcelable

    companion object {
        @JvmStatic
        fun parse(devices: List<com.mfsoftware.home.models.Device>, rooms: List<com.mfsoftware.home.models.Room>): DevicesTree {
            val obj = DevicesTree(null, null)
            return obj
        }
    }
}
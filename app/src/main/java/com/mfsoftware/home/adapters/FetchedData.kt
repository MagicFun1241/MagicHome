package com.mfsoftware.home.adapters

import android.os.Parcelable

import com.mfsoftware.home.data.Device
import com.mfsoftware.home.data.Home
import com.mfsoftware.home.data.Room
import com.mfsoftware.home.data.Tree

import kotlinx.android.parcel.Parcelize

@Parcelize
data class FetchedData(val devices: List<Device>, val rooms: List<Room>, val homes: List<Home>) : Parcelable {
    fun createTree(): Tree {
        return Tree()
    }

    companion object {
        @JvmStatic
        fun parse(devices: List<Device>, rooms: List<Room>, homes: List<Home>): FetchedData {
            return FetchedData(devices, rooms, homes)
        }

        @JvmStatic
        fun parseRealm(devices: List<com.mfsoftware.home.models.Device>, rooms: List<com.mfsoftware.home.models.Room>, homes: List<com.mfsoftware.home.models.Home>): FetchedData {
            val devicesList = ArrayList<Device>()
            devices.forEach { devicesList.add(Device(it.id, it.localIp, it.type, it.room)) }

            val roomsList = ArrayList<Room>()
            rooms.forEach { roomsList.add(Room(it.id, it.name)) }

            val homesList = ArrayList<Home>()
            homes.forEach { homesList.add(Home(it.id, it.address, it.localIp)) }

            return FetchedData(devicesList, roomsList, homesList)
        }
    }
}
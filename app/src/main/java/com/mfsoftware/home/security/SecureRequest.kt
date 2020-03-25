package com.mfsoftware.home.security

import com.mfsoftware.home.api.Api
import java.util.*

open class SecureRequest(var time: Int = Calendar.getInstance().timeInMillis.toInt(), var fingerprint: String = Api.getFingerPrint())
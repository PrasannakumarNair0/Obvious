package com.prasannakumar.obvioustest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NasaPicDetails(
    var copyright: String? = null,
    var date: String? = null,
    var explanation: String? = null,
    var hdurl: String? = null,
    var mediaType: String? = null,
    var serviceVersion: String? = null,
    var title: String? = null,
    var url: String? = null
) : Parcelable

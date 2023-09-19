package com.example.subject1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyItem(
    val aIcon: Int, // 그림
    val productName: String,
    val productDescription: String,
    val seller: String,
    val price: Int,
    val address: String,
    val likes: Int,
    val chatting: Int
) : Parcelable {}
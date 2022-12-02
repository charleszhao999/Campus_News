package cn.jan29th.campusnews

import java.util.*

class News(
    val id: Long,
    val imageId: Int,
    val title: String,
    val desc: String,
    val nPA: String,
    val publishTime: Date
) {
    var publishAccount = nPA
}
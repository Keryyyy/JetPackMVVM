package com.kxxg.mvvmcha.data.model.bean


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class UserInfo(
    val accid: String = "",
    val account: String = "",
    val address: String = "",
    val avatar: String = "",
    val birth: Int = 0,
    val block_end_time: Int = 0,
    val block_state: Int = 0,
    val channel_name: String = "",
    val city: String = "",
    val city_code: String = "",
    val coin_num: Int = 0,
    val create_time: String = "",
    val current_os: Int = 0,
    val current_version: String = "",
    val emotion_state: Int = 0,
    val gender: Int = 0,
    val hb_lat: String = "",
    val hb_lng: String = "",
    val hb_time: Long = 0,
    val id: Int = 0,
    val mac: String = "",
    val machine: String = "",
    val meid: String = "",
    val nickname: String = "",
    val province: String = "",
    val puppet: Int = 0,
    var qq: String = "",
    val register_ip: String = "",
    val register_os: String = "",
    val register_process_ok: Boolean = false,
    val register_version: String = "",
    val sign: String = "",
    val token: String = "",
    val token_expire: String = "",
    val vip_expire: Long = 0,
    val third_open_id: String = "",
    val source: String = "",
    val third_type: String = "",
    val have_passwd: Boolean = false,
    val followed_member_nums: Int = 0,
    val be_followed_member_nums: Int = 0,
    val pointed_content_nums: Int = 0,
    val be_pointed_content_nums: Int = 0,
    val published_content_nums: Int = 0,
    var member_followed: Boolean = false,
    val im: Im? = null
) : Parcelable {
    @Parcelize
    @Keep
    data class Im(val username: String = "", val password: String = "") : Parcelable
}

package com.kxxg.mvvmcha.base.utils

import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import android.view.View
import androidx.core.content.FileProvider
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.TimeUtils
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2020/11/23
 *     desc  :
 *
 * </pre>
 */


// 获取渠道工具函数
fun getChannelName(ctx: Context): String {
    var channelName: String? = null
    try {
        val packageManager = ctx.packageManager
        if (packageManager != null) {
            //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是activity标签中，所以用ApplicationInfo
            val applicationInfo = packageManager.getApplicationInfo(
                ctx.packageName,
                PackageManager.GET_META_DATA
            )
            if (applicationInfo.metaData != null) {
                channelName = applicationInfo.metaData["UMENG_CHANNEL"].toString()
            }
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    if (TextUtils.isEmpty(channelName)) {
        channelName = "Unknown"
    }
    return channelName!!
}

/**
 * 修复ViewPager2 Item高度不会自适应的问题
 */
fun updatePagerHeightForChild(view: View, pager: ViewPager2) {

}

/**
 * 读取本地Json文件
 */
fun getJsonFromAssets(context: Context, fileName:String): String {
    val stringBuilder = StringBuilder()
    try {
        val assetManager = context.assets
        val bf = BufferedReader(
            InputStreamReader(
                assetManager.open(fileName)
            )
        )
        var line: String?
        while (bf.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return stringBuilder.toString()
}




/**
 * @body 需要请求的body参数
 * @timeMills 时间戳
 * @return MD5加密后的值
 */

fun getSignature(jsonObject: JSONObject?, timeMills: String): String {
    val sb = StringBuilder()
    var str2 = ""
    val constStr = "LVCHA-APP${AppUtils.getAppVersionName()}"
    val map = TreeMap<String,String>()
    sb.append("_timestamp=${timeMills}")
    if (jsonObject == null) {
        str2 = "$constStr${sb}"
    } else {
        val iterator = jsonObject.keys()
        while (iterator.hasNext()) {
            val key = iterator.next()
            val value = jsonObject.getString(key)
            map[key] = value
        }
        map.forEach { (t, u) ->
            sb.append("&${t}=${u}")
        }
        str2 = "$constStr${sb}"
    }
    return EncryptUtils.encryptMD5ToString(str2).toLowerCase(Locale.CHINA)
}

fun JSONObject.getSign(){
    val timeStamp = (TimeUtils.getNowMills() / 1000).toString()
    val signature = getSignature(this, timeStamp)
    this.put("_timestamp",timeStamp)
    this.put("_signature",signature)
}

//fun JSONObject.getSignAnd2RequestBody():RequestBody{
//    val timeStamp = (TimeUtils.getNowMills() / 1000).toString()
//    val signature = getSignature(this, timeStamp)
//    this.put("_timestamp",timeStamp)
//    this.put("_signature",signature)
//    return this.toString().toRequestBody("application/json".toMediaTypeOrNull())
//}

//fun JSONObject.getAllParams():RequestBody{
//    this.put("accid", ChaApp.getUser()?.accid)
//    this.put("token", ChaApp.getUser()?.token)
//    val timeStamp = (TimeUtils.getNowMills() / 1000).toString()
//    val signature = getSignature(this, timeStamp)
//    this.put("_timestamp",timeStamp)
//    this.put("_signature",signature)
//    return this.toString().toRequestBody("application/json".toMediaTypeOrNull())
//}

//fun getDefaultNetParams():RequestBody{
//    val jsonObject = JSONObject()
//        .apply {
//            this.put("accid", ChaApp.getUser()?.accid)
//            this.put("token", ChaApp.getUser()?.token)
//            val timeStamp = (TimeUtils.getNowMills() / 1000).toString()
//            val signature = getSignature(this, timeStamp)
//            this.put("_timestamp",timeStamp)
//            this.put("_signature",signature)
//        }
//    return jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
//}

//Bugly更新需使用
class BuglyFileProvider : FileProvider()





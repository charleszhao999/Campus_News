package cn.jan29th.campusnews

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    private val createNews =
        "create table News (id integer primary key autoincrement,imageId integer, title string,detail string,publishAccount string,year integer, month integer,date interger,hour integer,minute integer, second integer)"
    private val createUser =
        "create table User (id integer primary key autoincrement,account string,password string,icon integer,nickName string,signature string,gender string)"

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(createNews)
            db.execSQL(createUser)
            val news1 = ContentValues().apply {
                put("imageId", 1)
                put("title", "防控疫情，戴好口罩")
                put("detail", "近日发现有些同学没带口罩直接进入食堂，疫情尚未结束，请大家进入食堂前自觉戴好口罩")
                put("publishAccount", "admin")
                put("year", 121)
                put("month", 5)
                put("date", 24)
                put("hour", 12)
                put("minute", 35)
                put("second", 20)
            }
            val news2 = ContentValues().apply {
                put("imageId", 2)
                put("title", "食堂门口漏油！")
                put("detail", "紫荆食堂门口的地面上都是油，好几个同学骑电动车经过滑倒，大家小心！注意绕行！")
                put("publishAccount", "admin")
                put("year", 121)
                put("month", 5)
                put("date", 24)
                put("hour", 12)
                put("minute", 39)
                put("second", 9)
            }
            val news3 = ContentValues().apply {
                put("imageId", 3)
                put("title", "爱护草坪，带走垃圾")
                put("detail", "昨天，有同学在阳光园的草地上野餐完没有及时清理垃圾，请同学们自觉带走垃圾，共同维护文明的校园")
                put("publishAccount", "admin")
                put("year", 121)
                put("month", 5)
                put("date", 24)
                put("hour", 12)
                put("minute", 48)
                put("second", 52)
            }
            val news4 = ContentValues().apply {
                put("imageId", 4)
                put("title", "防控疫情，井盖碎裂")
                put("detail", "生活三区到教学区的红绿灯路口处井盖碎裂，目前已在维修，大家小心,注意绕行")
                put("publishAccount", "admin")
                put("year", 121)
                put("month", 5)
                put("date", 24)
                put("hour", 18)
                put("minute", 59)
                put("second", 4)
            }
            db.insert("News", null, news1)
            db.insert("News", null, news2)
            db.insert("News", null, news3)
            db.insert("News", null, news4)

            val user = ContentValues().apply {
                put("account", "charleszhao999")
                put("password", "password")
                put("icon", 3)
                put("nickName ", "Jan29th")
                put("signature ", "能隔着玻璃看着我的，只有想和我一起这么溺死的人。")
                put("gender ", "男")
            }
            db.insert("User", null, user)
        }
        Toast.makeText(context, "Create succeed", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
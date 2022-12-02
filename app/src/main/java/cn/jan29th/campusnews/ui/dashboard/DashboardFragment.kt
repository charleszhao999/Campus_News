package cn.jan29th.campusnews.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.jan29th.campusnews.MyDatabaseHelper
import cn.jan29th.campusnews.News
import cn.jan29th.campusnews.R
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val icon: ImageView = root.findViewById(R.id.userIcon)
        val userName: TextView = root.findViewById(R.id.userName)
        val nickName: TextView = root.findViewById(R.id.userNickName)
        val gender: TextView = root.findViewById(R.id.userGender)
        val signature: TextView = root.findViewById(R.id.userSignature)

        val dbHelper = context?.let { MyDatabaseHelper(it, "News.db", 2) }
        val db = dbHelper?.writableDatabase
        val cursor = db?.query("User", null, null, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()

            val _account = cursor.getString(cursor.getColumnIndex("account"))
            var _icon = 0;
            when (cursor.getInt(cursor.getColumnIndex("icon"))) {
                4 -> {
                    _icon = R.drawable.head4
                }
                3 -> {
                    _icon = R.drawable.head3
                }
                2 -> {
                    _icon = R.drawable.head2
                }
                1 -> {
                    _icon = R.drawable.head1
                }
            }
            val _nickName = cursor.getString(cursor.getColumnIndex("nickName"))
            val _signature = cursor.getString(cursor.getColumnIndex("signature"))
            val _gender = cursor.getString(cursor.getColumnIndex("gender"))

            icon.setImageResource(_icon)
            userName.text = _account
            nickName.text = _nickName
            gender.text = _gender
            signature.text = _signature


            cursor.close()
        }
        return root
    }
}
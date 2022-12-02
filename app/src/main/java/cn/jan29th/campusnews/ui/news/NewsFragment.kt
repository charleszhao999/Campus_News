package cn.jan29th.campusnews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.jan29th.campusnews.MyDatabaseHelper
import cn.jan29th.campusnews.News
import cn.jan29th.campusnews.R
import java.util.*
import kotlin.collections.ArrayList

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private val newsList = ArrayList<News>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)

        initNews()
        val recycleview: RecyclerView = root.findViewById(R.id.recycleview)
        val layoutManager = LinearLayoutManager(activity)
        recycleview.layoutManager = layoutManager
        val adapter = NewsAdapter(newsList)
        recycleview.adapter = adapter
        return root
    }

    private fun initNews() {
        val dbHelper = context?.let { MyDatabaseHelper(it, "News.db", 2) }
        val db = dbHelper?.writableDatabase
        val cursor = db?.query("News", null, null, null, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    var imageId = 0;
                    when (cursor.getInt(cursor.getColumnIndex("imageId"))) {
                        4 -> {
                            imageId = R.drawable.head4
                        }
                        3 -> {
                            imageId = R.drawable.head3
                        }
                        2 -> {
                            imageId = R.drawable.head2
                        }
                        1 -> {
                            imageId = R.drawable.head1
                        }
                    }
                    val title = cursor.getString(cursor.getColumnIndex("title"))
                    val desc = cursor.getString(cursor.getColumnIndex("detail"))
                    val nPA = cursor.getString(cursor.getColumnIndex("publishAccount"))
                    val year = cursor.getInt(cursor.getColumnIndex("year"))
                    val month = cursor.getInt(cursor.getColumnIndex("month"))
                    val date1 = cursor.getInt(cursor.getColumnIndex("date"))
                    val hour = cursor.getInt(cursor.getColumnIndex("hour"))
                    val minute = cursor.getInt(cursor.getColumnIndex("minute"))
                    val second = cursor.getInt(cursor.getColumnIndex("second"))
                    val date: Date = Date(year, month, date1, hour, minute, second)
                    newsList.add(News(id.toLong(), imageId, title, desc, nPA, date))
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        /*newsList.add(
            News(
                1, R.drawable.head1,
                "防控疫情，戴好口罩", "近日发现有些同学没带口罩直接进入食堂，疫情尚未结束，请大家进入食堂前自觉戴好口罩",
                "admin", Date(121, 5, 24, 12, 35, 20)
            )
        )*/
    }
}
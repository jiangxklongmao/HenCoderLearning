package com.jiangxk.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jiangxk.base.BaseView
import com.jiangxk.lesson.entity.Lesson

/**
 * @description com.jiangxk.lesson
 * @author jiangxk
 * @time 2020-05-13  22:23
 */
class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>,
    Toolbar.OnMenuItemClickListener {

    override val presenter by lazy {
        LessonPresenter(this)
    }


    private val lessonAdapter = LessonAdapter()

    private lateinit var refreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        findViewById<RecyclerView>(R.id.list)
            .apply {
                layoutManager = LinearLayoutManager(this@LessonActivity)
                adapter = lessonAdapter
                addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
            }


        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            setOnRefreshListener { presenter.fetchData() }
            isRefreshing = true
        }


        presenter.fetchData()
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        presenter.showPlayback()
        return false
    }

    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

}
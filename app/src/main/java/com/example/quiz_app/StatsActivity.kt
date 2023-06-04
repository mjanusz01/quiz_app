package com.example.quiz_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

var stats = ArrayList<Stats>()
lateinit var adapter: StatisticAdapter

class StatsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val db = MyDatabase.getInstance(applicationContext)
        val stats = db.statsDao().getAllData()
        Log.v("SIZE", stats.size.toString())

        adapter = StatisticAdapter(stats as ArrayList<Stats>)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
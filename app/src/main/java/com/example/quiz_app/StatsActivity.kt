package com.example.quiz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

var stats = ArrayList<Stats>()
lateinit var adapter: StatisticAdapter
lateinit var login: String

class StatsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        login = intent.getStringExtra("login").toString()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val db = MyDatabase.getInstance(applicationContext)
        val stats = db.statsDao().getAllData()
        Log.v("SIZE", stats.size.toString())

        adapter = StatisticAdapter(stats as ArrayList<Stats>)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun stats_back_button(view: View) {
        val intent = Intent(this,MainMenuActivity::class.java)
        intent.putExtra("login",login)
        startActivity(intent)
    }
}
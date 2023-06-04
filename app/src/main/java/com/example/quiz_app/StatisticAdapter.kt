package com.example.quiz_app

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class StatisticAdapter(private val stats: ArrayList<Stats>): RecyclerView.Adapter<StatisticAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gametype: TextView
        val points: TextView
        val answers: TextView
        val time: TextView
        val layout: ConstraintLayout

        init {
            gametype = itemView.findViewById(R.id.stats_gametype)
            points = itemView.findViewById(R.id.stats_points)
            answers = itemView.findViewById(R.id.stats_answers)
            time = itemView.findViewById(R.id.stats_time)
            layout = itemView.findViewById(R.id.layoutId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stats_row, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position%2==0){
            holder.layout.setBackgroundColor(Color.parseColor("#FF382876"))
        }
        else{
            holder.layout.setBackgroundColor(Color.parseColor("#FF6f50d4"))
        }
        if(stats[position].gametype == "normal_game"){
            holder.gametype.text = "normalna"
        }
        else if(stats[position].gametype == "time_game"){
            holder.gametype.text = "czasowa"
        }
        else{
            holder.gametype.text = "undefined"
        }
        holder.answers.text = stats[position].goodAnswerNum.toString() + "/" + (stats[position].badAnswerNum+stats[position].goodAnswerNum).toString()
        holder.time.text = stats[position].time.toString() + "s"
    }

    override fun getItemCount() = (stats.size)


}

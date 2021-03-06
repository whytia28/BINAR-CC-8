package com.example.binarchapter8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.binarchapter8.R
import com.example.binarchapter8.pojo.GetBattleResponse
import kotlinx.android.synthetic.main.history_item.view.*

class AdapterHistory(
    private val historyBattle: List<GetBattleResponse.Data>
) : RecyclerView.Adapter<AdapterHistory.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = historyBattle.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyBattle[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(battle: GetBattleResponse.Data) {
            with(itemView) {
                tv_date.text = battle.createdAt
                tv_mode.text = battle.mode
                tv_history.text = battle.message
            }
        }
    }
}
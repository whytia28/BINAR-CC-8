package com.example.binarchapter7.main.ui.history


import android.content.Context
import com.example.binarchapter7.database.Battle
import com.example.binarchapter7.database.BattleDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HistoryPresenter(context: Context, private val listener: Listener) {

    private var battleDb = BattleDatabase.getInstance(context)

    fun showAllHistory() {
        GlobalScope.launch {
            val listHistory = battleDb?.battleDao()?.getAllBattle()
            listHistory?.let {
                listener.showAllHistory(it)
            }

        }
    }

    fun deleteHistory(battle: Battle) {
        GlobalScope.launch {
            val result = battleDb?.battleDao()?.deleteHistory(battle)
            if (result != 0) {
                listener.showSuccessDelete()
            } else {
                listener.showFailedDelete()
            }
        }
    }

    fun setupUi(listHistory: List<Battle>) {
        listener.setupUi(listHistory)
    }


    interface Listener {
        fun showAllHistory(listHistory: List<Battle>)
        fun showSuccessDelete()
        fun showFailedDelete()
        fun setupUi(listHistory: List<Battle>)
    }
}

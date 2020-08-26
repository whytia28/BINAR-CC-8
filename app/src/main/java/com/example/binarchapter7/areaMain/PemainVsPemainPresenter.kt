package com.example.binarchapter7.areaMain


import com.example.binarchapter7.database.Battle
import java.text.SimpleDateFormat
import java.util.*

class PemainVsPemainPresenter() {

    var listener: Listener? = null

//    private var battleDb = BattleDatabase.getInstance(context)

    fun showResult() {
        listener?.showResult()
    }

    fun startNew() {
        listener?.startNew()
    }

    fun setOverlay() {
        listener?.setOverlay()
    }

    fun saveHistory(battle: Battle) {
//        GlobalScope.launch {
//            val result = battleDb?.battleDao()?.insert(battle)
//            if (result != 0.toLong()) {
//                listener?.showSuccessSave()
//            } else {
//                listener?.showFailedSave()
//            }
//        }
    }

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }


    interface Listener {
        fun startNew()
        fun showResult()
        fun setOverlay()
        fun showSuccessSave()
        fun showFailedSave()
        fun showSuccessDelete()
        fun showFailedDelete()
    }
}
package com.example.binarchapter8.main.ui.battle


class BattlePresenter {

    var listener: Listener? = null

    fun goToPemainVsPemain(username: String) {
        listener?.goToPemainVsPemain(username)
    }

    fun goToPemainVsCpu(username: String) {
        listener?.goToPemainVsCpu(username)
    }

    interface Listener {
        fun goToPemainVsPemain(username: String)
        fun goToPemainVsCpu(username: String)
    }
}
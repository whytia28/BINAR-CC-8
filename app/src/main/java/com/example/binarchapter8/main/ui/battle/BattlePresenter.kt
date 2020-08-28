package com.example.binarchapter8.main.ui.battle

import com.example.binarchapter8.pojo.LoginResponse

class BattlePresenter {

    var listener: Listener? = null

    fun goToPemainVsPemain(data: LoginResponse.Data) {
        listener?.goToPemainVsPemain(data)
    }

    fun goToPemainVsCpu(data: LoginResponse.Data) {
        listener?.goToPemainVsCpu(data)
    }

    interface Listener {
        fun goToPemainVsPemain(data: LoginResponse.Data)
        fun goToPemainVsCpu(data: LoginResponse.Data)
    }
}
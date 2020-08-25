package com.example.binarchapter7.main.ui.battle

import com.example.binarchapter7.pojo.LoginResponse

class BattlePresenter(val listener: Listener) {

    fun goToPemainVsPemain(data: LoginResponse.Data) {
        listener.goToPemainVsPemain(data)
    }

    fun goToPemainVsCpu(data: LoginResponse.Data) {
        listener.goToPemainVsCpu(data)
    }

    interface Listener {
        fun goToPemainVsPemain(data: LoginResponse.Data)
        fun goToPemainVsCpu(data: LoginResponse.Data)
    }
}
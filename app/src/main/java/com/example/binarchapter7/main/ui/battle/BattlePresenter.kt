package com.example.binarchapter7.main.ui.battle

import com.example.binarchapter7.pojo.PostLoginResponse

class BattlePresenter(val listener: Listener) {

    fun goToPemainVsPemain(data: PostLoginResponse.Data) {
        listener.goToPemainVsPemain(data)
    }

    fun goToPemainVsCpu(data: PostLoginResponse.Data) {
        listener.goToPemainVsCpu(data)
    }

    interface Listener {
        fun goToPemainVsPemain(data: PostLoginResponse.Data)
        fun goToPemainVsCpu(data: PostLoginResponse.Data)
    }
}
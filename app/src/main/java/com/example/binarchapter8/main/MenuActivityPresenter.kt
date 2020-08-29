package com.example.binarchapter8.main

class MenuActivityPresenter {

    var listener : Listener? = null

    fun onLogoutSuccess() {
        listener?.onLogoutSuccess()
    }

    interface Listener {
        fun onLogoutSuccess()
    }
}
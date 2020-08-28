package com.example.binarchapter8.areaMain


import com.example.binarchapter8.network.ApiService
import com.example.binarchapter8.pojo.PostBattleBody
import com.example.binarchapter8.pojo.PostBattleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PemainVsPemainPresenter(private val apiService: ApiService) {

    var listener: Listener? = null

    fun showResult() {
        listener?.showResult()
    }

    fun startNew() {
        listener?.startNew()
    }

    fun setOverlay() {
        listener?.setOverlay()
    }

    fun saveHistory(token: String, body: PostBattleBody) {
        apiService.saveHistoryBattle(token, body).enqueue(object : Callback<PostBattleResponse> {
            override fun onResponse(
                call: Call<PostBattleResponse>,
                response: Response<PostBattleResponse>
            ) {
                listener?.showSuccessSave()
            }

            override fun onFailure(call: Call<PostBattleResponse>, t: Throwable) {
                t.message?.let {
                    listener?.showFailedSave(it)
                }
            }

        })
    }

    interface Listener {
        fun startNew()
        fun showResult()
        fun setOverlay()
        fun showSuccessSave()
        fun showFailedSave(errorMessage: String)
    }
}
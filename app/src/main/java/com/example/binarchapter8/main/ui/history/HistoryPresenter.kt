package com.example.binarchapter8.main.ui.history


import com.example.binarchapter8.network.ApiService
import com.example.binarchapter8.pojo.GetBattleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HistoryPresenter(private val apiService: ApiService) {

    var listener: Listener? = null

    fun showAllHistory(token: String) {
        listener?.showProgressBar()
        apiService.getHistoryBattle(token).enqueue(object : Callback<GetBattleResponse> {
            override fun onResponse(
                call: Call<GetBattleResponse>,
                response: Response<GetBattleResponse>
            ) {
                response.body()?.data.let {
                    if (it != null) {
                        listener?.getHistoryListSuccess(it)
                    }
                }
                listener?.hiddenProgressBar()
            }

            override fun onFailure(call: Call<GetBattleResponse>, t: Throwable) {
                t.message?.let {
                    listener?.getHistoryListFailed(it)
                }
            }

        })
    }

    fun setupUi(listHistory: List<GetBattleResponse.Data>) {
        listener?.setupUi(listHistory)
    }


    interface Listener {
        fun setupUi(listHistory: List<GetBattleResponse.Data>)
        fun getHistoryListSuccess(historyList: List<GetBattleResponse.Data>)
        fun getHistoryListFailed(errorMessage: String)
        fun showProgressBar()
        fun hiddenProgressBar()
    }
}

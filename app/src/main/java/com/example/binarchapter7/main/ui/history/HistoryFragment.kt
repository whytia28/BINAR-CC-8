package com.example.binarchapter7.main.ui.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.binarchapter7.R
import com.example.binarchapter7.adapter.AdapterHistory
import com.example.binarchapter7.main.MenuActivity
import com.example.binarchapter7.pojo.GetBattleResponse
import com.example.binarchapter7.pojo.LoginResponse
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject


class HistoryFragment : Fragment(), HistoryPresenter.Listener {

    private lateinit var historyViewModel: HistoryViewModel

    @Inject
    lateinit var presenter: HistoryPresenter

    companion object {
        var result: LoginResponse.Data? = null
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = view.context as MenuActivity

        context.supportActionBar?.title = getString(R.string.history)
        context.intent.getParcelableExtra<LoginResponse.Data>("data").let {
            result = it
        }
        presenter.listener = this
        presenter.showAllHistory(result)
    }

    override fun onResume() {
        super.onResume()
        presenter.showAllHistory(result)
    }


    private fun setRecyclerView(listHistory: List<GetBattleResponse.Data>) {
        rv_history_battle.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_history_battle.setHasFixedSize(true)
        val adapter = AdapterHistory(listHistory)
        rv_history_battle.adapter = adapter
        presenter.setupUi(listHistory)
    }

    override fun setupUi(listHistory: List<GetBattleResponse.Data>) {
        if (listHistory.isNotEmpty()) {
            tv_history_empty.visibility = View.GONE
        }
    }


    override fun getHistoryListSuccess(historyList: List<GetBattleResponse.Data>) {
        setRecyclerView(historyList)
    }

    override fun getHistoryListFailed(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

}
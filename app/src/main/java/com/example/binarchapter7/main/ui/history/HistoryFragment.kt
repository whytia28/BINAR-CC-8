package com.example.binarchapter7.main.ui.history

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
import com.example.binarchapter7.database.Battle
import com.example.binarchapter7.main.MenuActivity
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment(), HistoryPresenter.Listener {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var presenter: HistoryPresenter

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
        presenter = HistoryPresenter(context, this)

        rv_history_battle.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_history_battle.setHasFixedSize(true)
        presenter.showAllHistory()
    }

    override fun onResume() {
        super.onResume()
        presenter.showAllHistory()
    }


    override fun showAllHistory(listHistory: List<Battle>) {
        activity?.runOnUiThread {
            val adapter = AdapterHistory(listHistory, presenter)
            rv_history_battle.adapter = adapter
            presenter.setupUi(listHistory)
        }
    }

    override fun showSuccessDelete() {
        activity?.runOnUiThread {
            Toast.makeText(
                context,
                getString(R.string.delete_success),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun showFailedDelete() {
        activity?.runOnUiThread {
            Toast.makeText(
                context,
                getString(R.string.delete_failed),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun setupUi(listHistory: List<Battle>) {
        if (listHistory.isNotEmpty()) {
            tv_history_empty.visibility = View.GONE
        }
    }

}
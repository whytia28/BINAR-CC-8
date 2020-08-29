package com.example.binarchapter8.main.ui.battle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.binarchapter8.R
import com.example.binarchapter8.areaMain.PemainVsCpu
import com.example.binarchapter8.areaMain.PemainVsPemain
import com.example.binarchapter8.main.MenuActivity
import com.example.binarchapter8.pojo.AuthResponse
import com.example.binarchapter8.pojo.LoginResponse
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_battle.*
import javax.inject.Inject


class BattleFragment : Fragment(), View.OnClickListener, BattlePresenter.Listener {
    private lateinit var battleViewModel: BattleViewModel
    private lateinit var username: String

    @Inject
    lateinit var presenter: BattlePresenter


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        battleViewModel =
            ViewModelProvider(this).get(BattleViewModel::class.java)
        return inflater.inflate(R.layout.fragment_battle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = view.context as MenuActivity
        context.supportActionBar?.title = getString(R.string.battle)

        if (context.intent.hasExtra("data")) {
            context.intent.getParcelableExtra<LoginResponse.Data>("data")?.let {
                username = it.username
            }
        }
        if (context.intent.hasExtra("dataFromPrepare")) {
            context.intent.getParcelableExtra<AuthResponse.Data>("dataFromPrepare")?.let {
                username = it.username
            }
        }


        tv_pemain.text = getString(R.string.vs_pemain, username)
        tv_cpu.text = getString(R.string.vs_cpu, username)

        presenter.listener = this

        pemainvspemain.setOnClickListener(this)
        pemainvscpu.setOnClickListener(this)
        btn_exit.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.pemainvspemain -> {
                presenter.goToPemainVsPemain(username)
            }
            R.id.pemainvscpu -> {
                presenter.goToPemainVsCpu(username)
            }
            R.id.btn_exit -> {
                activity?.finish()
            }
        }
    }

    override fun goToPemainVsPemain(username: String) {
        val moveIntent = Intent(context, PemainVsPemain::class.java)
        moveIntent.putExtra("username", username)
        startActivity(moveIntent)
    }

    override fun goToPemainVsCpu(username: String) {
        val moveIntent = Intent(context, PemainVsCpu::class.java)
        moveIntent.putExtra("username", username)
        startActivity(moveIntent)
    }

}
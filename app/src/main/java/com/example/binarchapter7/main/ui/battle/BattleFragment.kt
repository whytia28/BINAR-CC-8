package com.example.binarchapter7.main.ui.battle

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.binarchapter7.R
import com.example.binarchapter7.areaMain.PemainVsCpu
import com.example.binarchapter7.areaMain.PemainVsPemain
import com.example.binarchapter7.main.MenuActivity
import com.example.binarchapter7.pojo.PostLoginResponse
import kotlinx.android.synthetic.main.fragment_battle.*


class BattleFragment : Fragment(), View.OnClickListener, BattlePresenter.Listener {
    private lateinit var battleViewModel: BattleViewModel
    private lateinit var result: PostLoginResponse.Data
    private lateinit var presenter: BattlePresenter


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

        context.intent.getParcelableExtra<PostLoginResponse.Data>("data")?.let {
            result = it
        }

        tv_pemain.text = getString(R.string.vs_pemain, result.username)
        tv_cpu.text = getString(R.string.vs_cpu, result.username)

        presenter = BattlePresenter(this)

        pemainvspemain.setOnClickListener(this)
        pemainvscpu.setOnClickListener(this)
        btn_exit.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.pemainvspemain -> {
                presenter.goToPemainVsPemain(result)
            }
            R.id.pemainvscpu -> {
                presenter.goToPemainVsCpu(result)
            }
            R.id.btn_exit -> {
                activity?.finish()
            }
        }
    }

    override fun goToPemainVsPemain(data: PostLoginResponse.Data) {
        val moveIntent = Intent(context, PemainVsPemain::class.java)
        moveIntent.putExtra("data", data)
        startActivity(moveIntent)
    }

    override fun goToPemainVsCpu(data: PostLoginResponse.Data) {
        val moveIntent = Intent(context, PemainVsCpu::class.java)
        moveIntent.putExtra("data", data)
        startActivity(moveIntent)
    }

}
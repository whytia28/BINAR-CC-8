package com.example.binarchapter8.areaMain

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.binarchapter8.R
import com.example.binarchapter8.logic.Controller
import com.example.binarchapter8.pojo.LoginResponse
import com.example.binarchapter8.pojo.PostBattleBody
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_pemain_vs_cpu.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import javax.inject.Inject


class PemainVsCpu : AppCompatActivity(), PemainVsCpuPresenter.Listener {

    private var pilihanSatu: String = ""
    private var pemenang: String = ""
    private lateinit var result: LoginResponse.Data

    @Inject
    lateinit var presenter: PemainVsCpuPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemain_vs_cpu)


        intent.getParcelableExtra<LoginResponse.Data>("data")?.let {
            result = it
        }
        presenter.listener = this
        pemain1.text = result.username

        batu1.setOnClickListener {
            pilihanSatu = Controller.pilihanGame[0]
            presenter.setOverlay()
            presenter.showResult()

        }

        kertas1.setOnClickListener {
            pilihanSatu = Controller.pilihanGame[1]
            presenter.setOverlay()
            presenter.showResult()
        }

        gunting1.setOnClickListener {
            pilihanSatu = Controller.pilihanGame[2]
            presenter.setOverlay()
            presenter.showResult()
        }

        iv_restart.setOnClickListener {
            presenter.startNew()
        }

        iv_save.setOnClickListener {
            val token = "Bearer ${result.token}"
            val mode = "Singleplayer"
            val body = PostBattleBody(mode, pemenang)
            presenter.saveHistory(token, body)
        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun showResult() {
        if (pilihanSatu != "") {
            val control = Controller()
            val hasilMain = control.caraMainCpu(pilihanSatu)
            pemenang = when (hasilMain) {
                "Player Win" -> {
                    "Player Win"
                }
                "CPU 2 menang" -> {
                    "Opponent Win"
                }
                else -> {
                    getString(R.string.hasil_draw)
                }
            }
            presenter.setCpuOverlay()

            val builder = AlertDialog.Builder(this)
            val dialog = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
            builder.setView(dialog)
            builder.setCustomTitle(hasil)
            dialog.selamat.text = pemenang
            val dialogMessage = builder.create()
            val handler = Handler()
            handler.postDelayed({
                kotlin.run {
                    dialogMessage.show()
                }
            }, 1000)
            dialog.btn_exit.setOnClickListener {
                dialogMessage.dismiss()
            }
        }
    }

    override fun setCpuOverlay() {
        when (Controller.pilihanCpu) {
            Controller.pilihanGame[0] -> {
                batu2.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }

            Controller.pilihanGame[1] -> {
                kertas2.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }

            Controller.pilihanGame[2] -> {
                gunting2.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
        }
    }

    override fun showSuccessSave() {
        Toast.makeText(
            this@PemainVsCpu,
            getString(R.string.add_history_success),
            Toast.LENGTH_SHORT
        ).show()
        iv_save.setImageResource(R.drawable.ic_save_active)
    }

    override fun showFailedSave(errorMessage: String) {
        Toast.makeText(
            this@PemainVsCpu,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun startNew() {
        pilihanSatu = ""
        iv_save.setImageResource(R.drawable.ic_save)
        batu1.foreground = null
        batu2.foreground = null
        kertas1.foreground = null
        kertas2.foreground = null
        gunting1.foreground = null
        gunting2.foreground = null
    }

    override fun setOverlay() {
        when (pilihanSatu) {
            Controller.pilihanGame[0] -> {
                batu1.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }

            Controller.pilihanGame[1] -> {
                kertas1.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }

            Controller.pilihanGame[2] -> {
                gunting1.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
        }
    }

}

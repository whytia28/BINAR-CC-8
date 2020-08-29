package com.example.binarchapter8.areaMain


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.binarchapter8.R
import com.example.binarchapter8.logic.Controller
import com.example.binarchapter8.pojo.PostBattleBody
import com.example.binarchapter8.sharedpref.MySharedPreferences
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_pemain_vs_pemain.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import javax.inject.Inject

class PemainVsPemain : AppCompatActivity(), PemainVsPemainPresenter.Listener {

    private var pilihanSatu: String = ""
    private var pilihanDua: String = ""
    private var pemenang: String = ""
    private var username: String? = ""

    @Inject
    lateinit var presenter: PemainVsPemainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemain_vs_pemain)



        username = intent.getStringExtra("username")
        pemain1.text = username
        presenter.listener = this

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
        batu2.setOnClickListener {
            pilihanDua = Controller.pilihanGame[0]
            presenter.showResult()
            presenter.setOverlay()
        }
        kertas2.setOnClickListener {
            pilihanDua = Controller.pilihanGame[1]
            presenter.showResult()
            presenter.setOverlay()
        }
        gunting2.setOnClickListener {
            pilihanDua = Controller.pilihanGame[2]
            presenter.showResult()
            presenter.setOverlay()
        }
        iv_restart.setOnClickListener {
            presenter.startNew()
        }
        iv_save.setOnClickListener {
            val token = MySharedPreferences(applicationContext).getData("token").toString()
            val mode = "Multiplayer"
            val body = PostBattleBody(mode, pemenang)
            presenter.saveHistory(token, body)
        }
        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun showResult() {
        if (pilihanSatu != "" && pilihanDua != "") {
            val control = Controller()
            val hasilMain = control.caraMain(pilihanSatu, pilihanDua)
            pemenang = when (hasilMain) {
                "pemain 1 menang" -> {
                    "Player Win"
                }
                "pemain 2 menang" -> {
                    "Opponent Win"
                }
                else -> {
                    getString(R.string.hasil_draw)
                }
            }
            val builder = AlertDialog.Builder(this)
            val dialog = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
            builder.setView(dialog)
            builder.setCustomTitle(hasil)
            dialog.selamat.text = pemenang
            val dialogMessage = builder.create()
            dialogMessage.show()
            dialog.btn_exit.setOnClickListener {
                dialogMessage.dismiss()
            }
        }

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
        when (pilihanDua) {
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
        runOnUiThread {
            Toast.makeText(
                this@PemainVsPemain,
                getString(R.string.add_history_success),
                Toast.LENGTH_SHORT
            ).show()
            iv_save.setImageResource(R.drawable.ic_save_active)
        }
    }

    override fun showFailedSave(errorMessage: String) {
        runOnUiThread {
            Toast.makeText(
                this@PemainVsPemain,
                errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun startNew() {
        pilihanSatu = ""
        pilihanDua = ""
        iv_save.setImageResource(R.drawable.ic_save)
        batu1.foreground = null
        batu2.foreground = null
        kertas1.foreground = null
        kertas2.foreground = null
        gunting1.foreground = null
        gunting2.foreground = null
    }

}

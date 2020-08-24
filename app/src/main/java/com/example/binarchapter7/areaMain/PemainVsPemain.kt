package com.example.binarchapter7.areaMain


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.binarchapter7.R
import com.example.binarchapter7.database.Battle
import com.example.binarchapter7.logic.Controler
import com.example.binarchapter7.pojo.PostLoginResponse
import kotlinx.android.synthetic.main.activity_pemain_vs_pemain.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*

class PemainVsPemain : AppCompatActivity(), PemainVsPemainPresenter.Listener {

    private var pilihanSatu: String = ""
    private var pilihanDua: String = ""
    private var pemenang: String = ""
    private lateinit var result: PostLoginResponse.Data
    private lateinit var presenter: PemainVsPemainPresenter
    private lateinit var date: String
    private lateinit var objBattle: Battle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemain_vs_pemain)

        intent.getParcelableExtra<PostLoginResponse.Data>("data")?.let {
            result = it
        }
        pemain1.text = result.username
        presenter = PemainVsPemainPresenter(this, this)
        date = presenter.getCurrentDate()

        batu1.setOnClickListener {
            pilihanSatu = Controler.pilihanGame[0]
            presenter.setOverlay()
            presenter.showResult()
        }
        kertas1.setOnClickListener {
            pilihanSatu = Controler.pilihanGame[1]
            presenter.setOverlay()
            presenter.showResult()
        }
        gunting1.setOnClickListener {
            pilihanSatu = Controler.pilihanGame[2]
            presenter.setOverlay()
            presenter.showResult()
        }
        batu2.setOnClickListener {
            pilihanDua = Controler.pilihanGame[0]
            presenter.showResult()
            presenter.setOverlay()
        }
        kertas2.setOnClickListener {
            pilihanDua = Controler.pilihanGame[1]
            presenter.showResult()
            presenter.setOverlay()
        }
        gunting2.setOnClickListener {
            pilihanDua = Controler.pilihanGame[2]
            presenter.showResult()
            presenter.setOverlay()
        }
        iv_restart.setOnClickListener {
            presenter.startNew()
        }
        iv_save.setOnClickListener {
            objBattle = Battle(null, pemenang, date)
            presenter.saveHistory(objBattle)
        }
    }

    override fun showResult() {
        if (pilihanSatu != "" && pilihanDua != "") {
            val control = Controler()
            val hasilMain = control.caraMain(pilihanSatu, pilihanDua)
            pemenang = when (hasilMain) {
                "pemain 1 menang" -> {
                    getString(R.string.selamat_kamu_menang, result.username)
                }
                "pemain 2 menang" -> {
                    getString(R.string.selamat_pemain_2_menang)
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
            Controler.pilihanGame[0] -> {
                batu1.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
            Controler.pilihanGame[1] -> {
                kertas1.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
            Controler.pilihanGame[2] -> {
                gunting1.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
        }
        when (pilihanDua) {
            Controler.pilihanGame[0] -> {
                batu2.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
            Controler.pilihanGame[1] -> {
                kertas2.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
            Controler.pilihanGame[2] -> {
                gunting2.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
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

    override fun showFailedSave() {
        runOnUiThread {
            Toast.makeText(
                this@PemainVsPemain,
                getString(R.string.add_history_failed),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun showSuccessDelete() {
        runOnUiThread {
            Toast.makeText(
                this@PemainVsPemain,
                getString(R.string.delete_success),
                Toast.LENGTH_SHORT
            ).show()
            iv_save.setImageResource(R.drawable.ic_save)
        }
    }

    override fun showFailedDelete() {
        runOnUiThread {
            Toast.makeText(
                this@PemainVsPemain,
                getString(R.string.delete_failed),
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

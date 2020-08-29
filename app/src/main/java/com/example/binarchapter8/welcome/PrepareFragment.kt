package com.example.binarchapter8.welcome


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.binarchapter8.login.LoginActivity
import com.example.binarchapter8.R
import com.example.binarchapter8.main.MenuActivity
import com.example.binarchapter8.pojo.AuthResponse
import com.example.binarchapter8.sharedpref.MySharedPreferences
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_prepare.*
import javax.inject.Inject


class PrepareFragment : Fragment(), PreparePresenter.Listener {

    private lateinit var sharedPref: SharedPreferences

    @Inject
    lateinit var preparePresenter: PreparePresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prepare, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedPref = view.context.getSharedPreferences("userData", Context.MODE_PRIVATE)

        next.setOnClickListener {
            if (sharedPref.contains("token")) {
                val token = MySharedPreferences(view.context).getData("token").toString()
                preparePresenter.autoLogin(token)
            } else {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    override fun onLoginSuccess() {
        Toast.makeText(activity, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
    }

    override fun onLoginFailed(errorMessage: String) {
        Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun goToMenuActivity(data: AuthResponse.Data) {
        val intent = Intent(activity, MenuActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
        activity?.finish()
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        progress_bar.visibility = View.GONE
    }
}
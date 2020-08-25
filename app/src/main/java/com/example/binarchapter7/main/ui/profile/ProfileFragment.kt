package com.example.binarchapter7.main.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.binarchapter7.R
import com.example.binarchapter7.main.MenuActivity
import com.example.binarchapter7.pojo.LoginResponse
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_profil.*
import javax.inject.Inject


class ProfileFragment : Fragment(), ProfilePresenter.Listener {
    private lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var presenter: ProfilePresenter

    companion object {
        lateinit var result: LoginResponse.Data
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidInjection.inject(activity)

        val context = view.context as MenuActivity
        context.supportActionBar?.title = getString(R.string.profile)
        context.intent.getParcelableExtra<LoginResponse.Data>("data")?.let {
            result = it
        }

        presenter.listener = this
        presenter.showProfile()

        cv_profile.setOnClickListener {
            val dialog = UpdateUserFragment.newInstance(result.email, result.username)
            dialog.show(context.supportFragmentManager, "Dialog Update Fragment")
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.showProfile()
    }

    override fun onUpdateSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateFailed(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showProfile() {
        et_email.setText(result.email)
        et_username.setText(result.username)
    }

}
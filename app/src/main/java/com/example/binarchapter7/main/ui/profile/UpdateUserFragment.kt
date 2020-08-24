package com.example.binarchapter7.main.ui.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.binarchapter7.R
import kotlinx.android.synthetic.main.fragment_update_user.*

private const val USERNAME = "username"
private const val EMAIL = "email"

class UpdateUserFragment : DialogFragment(), View.OnClickListener, ProfilePresenter.Listener {

    private lateinit var presenter: ProfilePresenter
    private var username: String? = null
    private var email: String? = null
    private var result = ProfileFragment.result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            username = it.getString(USERNAME, "username")
            email = it.getString(EMAIL, "email")
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_user, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(email: String, username: String)  =
            UpdateUserFragment().apply {
                arguments = Bundle().apply {
                    putString(EMAIL, email)
                    putString(USERNAME, username)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_update_user.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)

        presenter = ProfilePresenter(this)

        et_email.setText(result.email)
        et_username.setText(result.username)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_update_user -> {
                result.apply {
                    email = et_email.text.toString()
                    username = et_username.text.toString()
                }
                dialog?.dismiss()
                presenter.updateUser(result)
                presenter.showProfile()
            }
            R.id.btn_cancel -> {
                dialog?.dismiss()
            }
        }
    }

    override fun onUpdateSuccess(message: String) {

    }

    override fun onUpdateFailed(errorMessage: String) {

    }

    override fun showProfile() {

    }
}
package com.example.binarchapter8.main.ui.profile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.binarchapter8.sharedpref.MySharedPreferences
import com.example.binarchapter8.R
import com.example.binarchapter8.main.MenuActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profil.*


import javax.inject.Inject


class ProfileFragment : Fragment(), ProfilePresenter.Listener {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var bitmapResult: Bitmap
    private lateinit var token: String

    @Inject
    lateinit var presenter: ProfilePresenter

    companion object {
        const val REQUEST_CODE = 201
        const val CAMERA_REQUEST = 1001
        const val GALLERY_REQUEST = 1002
        val arrayListPermission = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
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
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val context = view.context as MenuActivity
        context.supportActionBar?.title = getString(R.string.profile)

        presenter.listener = this
        token = MySharedPreferences(context).getData("token").toString()


        tv_edit.setOnClickListener {
            presenter.editProfile()
        }

        iv_set_profile.setOnClickListener {
            if (checkPermission()) {
                val pictureDialog = AlertDialog.Builder(context)
                pictureDialog.setTitle(R.string.choose_from)
                val option =
                    arrayOf(getString(R.string.from_gallery), getString(R.string.take_picture))
                pictureDialog.setItems(option) { _, which ->
                    when (which) {
                        0 -> {
                            choosePhotoFromGallery()
                        }
                        1 -> {
                            takePhotoFromCamera()
                        }
                    }
                }
                pictureDialog.show()
            } else {
                requestPermissions()
            }
        }

        btn_save.setOnClickListener {
            presenter.updateUser(
                token,
                bitmapResult,
                et_username.text.toString(),
                et_email.text.toString()
            )

        }

        btn_back.setOnClickListener {
            presenter.showSetupUi()
        }

        presenter.getProfileUser(token)
    }

    private fun checkPermission(): Boolean {
        return (
                (context?.let {
                    checkSelfPermission(
                        it,
                        Manifest.permission.CAMERA
                    )
                } == PackageManager.PERMISSION_GRANTED) &&
                        (context?.let {
                            checkSelfPermission(
                                it,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        } == PackageManager.PERMISSION_GRANTED) &&
                        (context?.let {
                            checkSelfPermission(
                                it,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                        } == PackageManager.PERMISSION_GRANTED)

                )
    }

    private fun requestPermissions() {
        requestPermissions(arrayListPermission, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE -> {
                for (i in permissions.indices) {
                    if ((permissions[i] == arrayListPermission[i]) && (grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(
                            context,
                            "Permission ${permissions[1]} granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Permission ${permissions[1]} not granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST) {
            if (data != null) {
                val contentUri = data.data
                val bitmap =
                    MediaStore.Images.Media.getBitmap(activity?.contentResolver, contentUri)
                context?.let { Glide.with(it).load(bitmap).circleCrop().into(iv_profile) }

                bitmapResult = bitmap
            }
        } else if (requestCode == CAMERA_REQUEST) {
            val thumbnail = data?.extras?.get("data") as Bitmap
            context?.let { Glide.with(it).load(thumbnail).circleCrop().into(iv_profile) }

            bitmapResult = thumbnail
        }
    }

    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST)
    }

    private fun takePhotoFromCamera() {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera, CAMERA_REQUEST)
    }

    override fun onUpdateSuccess() {
        Toast.makeText(context, getString(R.string.update), Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateFailed(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showProfile(username: String, email: String, photo: String) {
        et_email.setText(email)
        et_username.setText(username)
        activity?.let {
            Glide.with(it).load(photo).circleCrop().into(iv_profile)
        }
    }

    override fun showProfileFailed(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showEditUi() {
        iv_set_profile.visibility = View.VISIBLE
        btn_save.visibility = View.VISIBLE
        et_username.isEnabled = true
        et_email.isEnabled = true
        btn_back.visibility = View.VISIBLE
    }

    override fun showSetupUi() {
        btn_save.visibility = View.GONE
        iv_set_profile.visibility = View.GONE
        et_username.isEnabled = false
        et_email.isEnabled = false
        btn_back.visibility = View.GONE
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        progress_bar.visibility = View.GONE
    }

}
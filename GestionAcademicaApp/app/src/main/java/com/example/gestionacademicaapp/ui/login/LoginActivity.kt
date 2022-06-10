package com.example.gestionacademicaapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.ActivityLoginBinding
import com.example.gestionacademicaapp.ui.career.CareerActivity
import com.example.gestionacademicaapp.utils.LoggedUser
import kotlinx.coroutines.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginVM
    private val repository = LoginRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this)[LoginVM::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        repository.viewModel = viewModel
    }


    fun login(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiResult = repository.login()
            runOnUiThread {
                if (apiResult.Code!! > 0) {
                    if (apiResult.Content != null) {

                        LoggedUser.user = viewModel.getUser().value
                        Log.d("LOGIN", "${LoggedUser.user?.UserID} logged to the app")

                        val intent = Intent(baseContext, CareerActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Log.d("LOGIN", "Wrong username or password")
                        Toast.makeText(view.context, "Username or password incorrect", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Log.d("LOGIN", "Unexpected error")
                    Toast.makeText(view.context, "An unexpected error occurred. Try again.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
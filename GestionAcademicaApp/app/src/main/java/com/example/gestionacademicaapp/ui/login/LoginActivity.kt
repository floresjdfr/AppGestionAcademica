package com.example.gestionacademicaapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginRepository by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    fun login(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val loggedResult = viewModel.login()
            withContext(Dispatchers.Main) {
                if (loggedResult) {
                    Toast.makeText(
                        view.context,
                        "Username: ${viewModel.getUser().value?.UserID} has being logged",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(view.context, "Username or password incorrect", Toast.LENGTH_LONG).show()
                }
                println("Changing view finished")
            }
            println("Login request finished")
        }
        println("Login finished")
    }
}
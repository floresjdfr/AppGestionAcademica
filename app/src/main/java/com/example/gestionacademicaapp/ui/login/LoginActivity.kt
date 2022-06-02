package com.example.gestionacademicaapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginRepository by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    fun login(view: View){
            viewModel.login {
                if(viewModel.isLogged){
                    val username = viewModel.getUser().value?.UserID
                    Toast.makeText(this, "Logged username: $username", Toast.LENGTH_LONG).show()
                }

                else
                    Toast.makeText(this, "Username or Password incorrect", Toast.LENGTH_LONG).show()
            }



    }
}
package com.example.gestionacademicaapp.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gestionacademicaapp.data.model.UserModel
import com.example.gestionacademicaapp.data.model.UserType
import com.example.gestionacademicaapp.databinding.ActivityLoginBinding
import com.example.gestionacademicaapp.ui.view.career.CareerActivity
import com.example.gestionacademicaapp.ui.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.isLogged.observe(this){
            if(it){
                Toast.makeText(this, "Logged", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, CareerActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun login(view: View) {
        val username = binding.usernameTI.editText?.text.toString()
        val password = binding.passwordTI.editText?.text.toString()

        val user = UserModel(0, username, password, UserType(""))

        loginViewModel.login(user)
    }
}
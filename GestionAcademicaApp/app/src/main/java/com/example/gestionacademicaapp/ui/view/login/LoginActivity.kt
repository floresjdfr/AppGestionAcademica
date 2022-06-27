package com.example.gestionacademicaapp.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gestionacademicaapp.data.model.user.UserModel
import com.example.gestionacademicaapp.data.model.user.UserType
import com.example.gestionacademicaapp.databinding.ActivityLoginBinding
import com.example.gestionacademicaapp.ui.view.MainActivity
import com.example.gestionacademicaapp.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

                val intent = Intent(this, MainActivity::class.java)
                val bundle = Bundle()

                bundle.putSerializable("loggedUser", loginViewModel.loggedUser.value)
                intent.putExtras(bundle)

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

        val user = UserModel(0, username, password, UserType(0,""))

        CoroutineScope(Dispatchers.Main).launch {
            loginViewModel.login(user)
        }
    }
}
package com.example.gestionacademicaapp.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
        println(viewModel.getUser().value?.UserID)
        GlobalScope.launch(Dispatchers.IO) {
            val apiResult = repository.login()
            withContext(Dispatchers.Main) {
                if (apiResult.Code!! > 0) {
                    if (apiResult.Content != null) {
                        Toast.makeText(
                            view.context,
                            "Username: ${viewModel.getUser().value?.UserID} has being logged",
                            Toast.LENGTH_LONG
                        ).show()
                    } else
                        Toast.makeText(view.context, "Username or password incorrect", Toast.LENGTH_LONG).show()
                } else
                    Toast.makeText(view.context, "An unexpected error occurred. Try again.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
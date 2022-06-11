package com.example.gestionacademicaapp.ui.view.career

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.databinding.FragmentCreateCareerBinding
import com.example.gestionacademicaapp.ui.viewmodel.CareerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CreateCareerFragment : Fragment() {

    private lateinit var binding: FragmentCreateCareerBinding
    private val viewModel: CareerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCreateCareerBinding.inflate(inflater, container, false)

        initObservers()
        initListeners()

        return binding.root
    }
    private fun initObservers(){
        viewModel.isLoading.observe(this){
            binding.progress.isVisible = it
        }
    }

    private fun initListeners(){
        binding.createButton.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                createCareer()
                Toast.makeText(context, "Career added!", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction().replace(R.id.fragment_container, CareersFragment()).commit()
            }
        }
    }

    suspend fun createCareer(){
        val careerCode = binding.careerCode.editText?.text.toString()
        val careerName = binding.careerName.editText?.text.toString()
        val careerTitle = binding.careerTitle.editText?.text.toString()

        val career = CareerModel(0, careerCode, careerName, careerTitle)

        viewModel.createCareer(career)
    }
}
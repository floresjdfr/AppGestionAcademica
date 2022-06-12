package com.example.gestionacademicaapp.ui.view.career

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.FragmentCareerInfoBinding


class CareerInfoFragment : Fragment() {
    private lateinit var binding: FragmentCareerInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCareerInfoBinding.inflate(inflater, container, false)


        return binding.root
    }
}
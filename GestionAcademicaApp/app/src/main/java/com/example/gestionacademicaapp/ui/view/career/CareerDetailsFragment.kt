package com.example.gestionacademicaapp.ui.view.career

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.FragmentCareerDetailsBinding
import com.example.gestionacademicaapp.ui.view.course.CoursesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class CareerDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCareerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCareerDetailsBinding.inflate(inflater, container, false)
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.careerBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.career_info -> {
                    parentFragmentManager.beginTransaction().replace(R.id.career_details_container, CareerInfoFragment()).commit()
                    true
                }
                R.id.career_courses -> {
                    parentFragmentManager.beginTransaction().replace(R.id.career_details_container, CoursesFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}
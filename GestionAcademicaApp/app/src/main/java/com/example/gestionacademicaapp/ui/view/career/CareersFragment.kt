package com.example.gestionacademicaapp.ui.view.career

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.ActivityCareerBinding
import com.example.gestionacademicaapp.databinding.FragmentCareersBinding
import com.example.gestionacademicaapp.ui.viewmodel.CareerViewModel

class CareersFragment : Fragment() {

    private lateinit var recyclerViewElement: RecyclerView
    private lateinit var adapter: CareerAdapterRecyclerView

    private val viewModel: CareerViewModel by viewModels()

    private lateinit var binding: FragmentCareersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_careers, container, false)

        binding = FragmentCareersBinding.inflate(inflater, container, false)

        recyclerViewElement = binding.careerRecyclerview
        recyclerViewElement.layoutManager = LinearLayoutManager(recyclerViewElement.context)
        recyclerViewElement.setHasFixedSize(true)

        initAdapter()
        initObservers()

        return binding.root
    }

    private fun initObservers() {
        viewModel.careers.observe(this) {
            adapter.itemsList = it
            adapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
    }

    private fun initAdapter() {
        viewModel.getCareers()
        var nCareerList = viewModel.careers.value!!
        adapter = CareerAdapterRecyclerView(nCareerList)
        recyclerViewElement.adapter = adapter
    }

    fun addNewCareer(view: View) {
        Toast.makeText(context, "Not implemented yet!", Toast.LENGTH_SHORT).show()
    }
}
package com.example.gestionacademicaapp.ui.view.career

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.FragmentCareersBinding
import com.example.gestionacademicaapp.ui.viewmodel.CareerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CareersFragment : Fragment() {

    private lateinit var recyclerViewElement: RecyclerView
    private lateinit var adapter: CareerAdapterRecyclerView

    private val viewModel: CareerViewModel by viewModels()

    private lateinit var binding: FragmentCareersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCareersBinding.inflate(inflater, container, false)

        recyclerViewElement = binding.careerRecyclerview
        recyclerViewElement.layoutManager = LinearLayoutManager(recyclerViewElement.context)
        recyclerViewElement.setHasFixedSize(true)

        initAdapter()
        initListeners()
        initObservers()

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerViewElement)

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
    private fun initListeners() {
        binding.addCareer.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container, CreateCareerFragment()).commit()
        }
    }

    private val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            if (direction == ItemTouchHelper.LEFT) { //Delete
                deleteItem(position)
            } else { //Edit
                Toast.makeText(context, "Position: $position", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteItem(position: Int) {
        AlertDialog.Builder(context)
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Delete") { _: DialogInterface, _: Int ->
                CoroutineScope(Dispatchers.Main).launch {
                    var itemToDelete = adapter.getAtPosition(position)?.ID!!
                    val response = viewModel.deleteCareer(itemToDelete)
                    if(response){
                        adapter.deleteAtPosition(position)
                        adapter.notifyItemRemoved(position)
                    }
                    else{
                        adapter.notifyItemChanged(position)
                    }
                }
            }
            .setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
                adapter.notifyItemChanged(position)
            }
            .create()
            .show()
    }
}
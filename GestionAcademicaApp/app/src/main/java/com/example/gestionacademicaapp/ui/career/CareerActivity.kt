package com.example.gestionacademicaapp.ui.career

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.ActivityCareerBinding
import com.example.gestionacademicaapp.models.Career
import kotlinx.android.synthetic.main.activity_career.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CareerActivity : AppCompatActivity() {
    private lateinit var recyclerViewElement: RecyclerView
    private lateinit var adapter: CareerAdapterRV
    private lateinit var dataBinding: ActivityCareerBinding
    private var careerRepository = CareerRepository()
    lateinit var viewModel: CareerVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_career)
        viewModel = ViewModelProvider(this)[CareerVM::class.java]
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
        careerRepository.viewModel = viewModel

        recyclerViewElement = career_recyclerview
        recyclerViewElement.layoutManager = LinearLayoutManager(recyclerViewElement.context)
        recyclerViewElement.setHasFixedSize(true)

        initAdapter()

        addCareer.setOnClickListener {
            Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapter() {
        val nCareerList = ArrayList<Career>()
        GlobalScope.launch {
            getCareerItems()

            withContext(Dispatchers.Main){
                for (p in viewModel.getCareerList().value!!) {
                    nCareerList.add(p)
                }
                adapter = CareerAdapterRV(nCareerList)
                recyclerViewElement.adapter = adapter
            }
        }
    }

    private suspend fun getCareerItems() {
        var response = careerRepository.fetchCareers()

        withContext(Dispatchers.Main) {
            if (response.Code!! > 0) {
                val careerList = response.Content as ArrayList<Career>
                viewModel.setCareerList(careerList)
                Toast.makeText(baseContext, "Careers fetched", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(baseContext, "Error fetching careers", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getIndex(index: Int): Int {
        var index = index
        val adapterItems = adapter.itemsList
        val careerGeneralList = viewModel.getCareerList().value

        val career = adapterItems?.get(index)!!

        index = careerGeneralList!!.indexOfFirst {
            it.ID == career.ID
        }
        return index
    }
}
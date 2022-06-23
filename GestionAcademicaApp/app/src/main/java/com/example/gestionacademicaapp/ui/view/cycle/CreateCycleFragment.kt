package com.example.gestionacademicaapp.ui.view.cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.data.model.CycleModel
import com.example.gestionacademicaapp.data.model.CycleStateModel
import com.example.gestionacademicaapp.databinding.FragmentCreateCycleBinding
import com.example.gestionacademicaapp.ui.viewmodel.CycleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class CreateCycleFragment : Fragment() {

    private lateinit var binding: FragmentCreateCycleBinding
    private val viewModel: CycleViewModel by viewModels()
    private var editCycle: CycleModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateCycleBinding.inflate(inflater, container, false)

        val cycleArg = arguments?.getSerializable("cycle")
        if (cycleArg != null)
            editCycle = cycleArg as CycleModel

        initObservers()

        if (editCycle != null) { //Edit
            initEditListeners()
            initEditFields()
        } else { //Create
            initCreateListeners()
        }

        return binding.root
    }

    private fun initObservers() {
        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
    }

    private fun initCreateListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = createCycle()
                if (response) {
                    Toast.makeText(context, "Course added!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, CyclesFragment()).commit()
                } else
                    Toast.makeText(context, "There was an error adding the course", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun initEditListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                var response = editCourse()
                if (response) {
                    Toast.makeText(context, "Course edited!", Toast.LENGTH_SHORT).show()
//                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, CoursesFragment())
//                        .commit()
                } else {
                    Toast.makeText(context, "An error occurred while editing!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEditFields() {
//        binding.courseCode.editText?.setText(editCourse?.Code)
//        binding.courseName.editText?.setText(editCourse?.CourseName)
//        binding.courseTitle.editText?.setText(editCourse?.DegreeName)
    }

    private suspend fun createCycle(): Boolean {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val cycleNumber = binding.cycleNumber.editText?.text.toString().toInt()
        val cycleYear = binding.cycleYear.editText?.text.toString().toInt()
        val cycleStartDate = simpleDateFormat.parse(binding.cycleStartDate.editText?.text.toString())
        val cycleEndDate = simpleDateFormat.parse(binding.cycleEndDate.editText?.text.toString())
        val cycleStatus = 2

        val cycleState = CycleStateModel(cycleStatus, "")
        val cycle = CycleModel(0, cycleYear, cycleNumber, cycleStartDate, cycleEndDate, cycleState)
        return viewModel.createCycle(cycle)
    }

    private suspend fun editCourse(): Boolean {
//        val courseCode = binding.courseCode.editText?.text.toString()
//        val courseName = binding.courseName.editText?.text.toString()
//        val courseTitle = binding.courseTitle.editText?.text.toString()

//        editCourse?.Code = courseCode
//        editCourse?.CourseName = courseName
//        editCourse?.DegreeName = courseTitle

        return viewModel.updateCycle(editCycle!!)
    }

}
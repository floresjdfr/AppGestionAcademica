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
import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.databinding.FragmentCreateCourseBinding
import com.example.gestionacademicaapp.ui.view.career.CareerDetailsFragment
import com.example.gestionacademicaapp.ui.viewmodel.CourseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CreateCourseFragment : Fragment() {

    private lateinit var binding: FragmentCreateCourseBinding
    private val viewModel: CourseViewModel by viewModels()
    private lateinit var career: CareerModel
    private var editCareerCourse: CareerCourseModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateCourseBinding.inflate(inflater, container, false)

        val courseArg = arguments?.getSerializable("course")
        if (courseArg != null)
            editCareerCourse = courseArg as CareerCourseModel

        career = arguments?.getSerializable("career") as CareerModel


        initObservers()

        if (editCareerCourse != null) { //Edit
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
                createCourse()
                Toast.makeText(context, "Course added!", Toast.LENGTH_SHORT).show()

                val bundle = Bundle()
                val careerDetailsFragment = CareerDetailsFragment()

                bundle.putSerializable("career", career)
                bundle.putSerializable("fragment", 2)
                careerDetailsFragment.arguments = bundle

                parentFragmentManager.beginTransaction().replace(R.id.fragment_container, careerDetailsFragment).commit()
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

    private fun initEditFields(){
//        binding.courseCode.editText?.setText(editCourse?.Code)
//        binding.courseName.editText?.setText(editCourse?.CourseName)
//        binding.courseTitle.editText?.setText(editCourse?.DegreeName)
    }

    private suspend fun createCourse() {
        val courseCode = binding.courseCode.editText?.text.toString()
        val courseName = binding.courseName.editText?.text.toString()
        val courseCredits = binding.courseCredits.editText?.text.toString().toInt()
        val courseHours = binding.courseHours.editText?.text.toString().toInt()
        val courseCycleYear = binding.courseCycleYear.editText?.text.toString().toInt()
        val courseCycleNumber = binding.courseCycleNumber.editText?.text.toString().toInt()


        val course = CourseModel(0, courseCode, courseName, courseCredits, courseHours)
        val careerCourse = CareerCourseModel(0, courseCycleYear, courseCycleNumber,course, career)
        viewModel.createCareerCourse(careerCourse)
    }

    private suspend fun editCourse(): Boolean {
//        val courseCode = binding.courseCode.editText?.text.toString()
//        val courseName = binding.courseName.editText?.text.toString()
//        val courseTitle = binding.courseTitle.editText?.text.toString()

//        editCourse?.Code = courseCode
//        editCourse?.CourseName = courseName
//        editCourse?.DegreeName = courseTitle

        return viewModel.updateCareerCourse(editCareerCourse!!)
    }

}
package com.example.gestionacademicaapp.ui.view.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.databinding.FragmentCourseInfoBinding


class CourseInfoFragment : Fragment() {
    private lateinit var binding: FragmentCourseInfoBinding
    private lateinit var careerCourse: CareerCourseModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCourseInfoBinding.inflate(inflater, container, false)

        val courseArg = arguments?.getSerializable("course")
        careerCourse = courseArg as CareerCourseModel

        initData()
        return binding.root
    }

    private fun initData() {
        binding.courseCode.editText?.setText(careerCourse.Course.Code)
        binding.courseName.editText?.setText(careerCourse.Course.Name)
        binding.courseCredits.editText?.setText(careerCourse.Course.Credits.toString())
        binding.courseHours.editText?.setText(careerCourse.Course.WeeklyHours.toString())
        binding.courseCycleNumber.editText?.setText(careerCourse.Cycle.toString())
        binding.courseCycleYear.editText?.setText(careerCourse.Year.toString())
    }
}
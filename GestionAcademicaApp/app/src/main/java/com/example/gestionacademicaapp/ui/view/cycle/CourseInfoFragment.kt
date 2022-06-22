package com.example.gestionacademicaapp.ui.view.cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.databinding.FragmentCourseInfoBinding


class CourseInfoFragment : Fragment() {
    private lateinit var binding: FragmentCourseInfoBinding
    private lateinit var course: CourseModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCourseInfoBinding.inflate(inflater, container, false)

        val courseArg = arguments?.getSerializable("course")
        course = courseArg as CourseModel

        initData()
        return binding.root
    }

    private fun initData() {
        binding.courseCode.editText?.setText(course.Code)
        binding.courseName.editText?.setText(course.Name)
        binding.courseCredits.editText?.setText(course.Credits)
        binding.courseHours.editText?.setText(course.WeeklyHours)
    }
}
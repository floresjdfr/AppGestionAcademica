package com.example.gestionacademicaapp.ui.view.cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.databinding.FragmentCourseDetailsBinding
import kotlinx.android.synthetic.main.nav_fragment_container.*


class CourseDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCourseDetailsBinding
    private lateinit var course: CareerModel
    private var preferredShowedFragment: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCourseDetailsBinding.inflate(inflater, container, false)

        val courseArg = arguments?.getSerializable("course")
        course = courseArg as CareerModel

        val preferredFragmentArg = arguments?.getSerializable("fragment")
        if(preferredFragmentArg != null ) preferredShowedFragment = preferredFragmentArg as Int

        initListeners()
        initFragment()
        return binding.root
    }

    private fun initListeners() {
        binding.courseBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.course_info -> {
                    (activity as AppCompatActivity).toolbar.title = "Course Information"
                    initInfoFragment()
                    true
                }
                R.id.course_groups -> {
                    (activity as AppCompatActivity).toolbar.title = "Groups"
                    initCoursesFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun initFragment(){
        if(preferredShowedFragment != null){
            when(preferredShowedFragment){
                1 -> {
                    (activity as AppCompatActivity).toolbar.title = "Career Information"
                    initInfoFragment()
                }
                2 -> {
                   (activity as AppCompatActivity).toolbar.title = "Courses"
                    initCoursesFragment()
                }
            }
        }
        else{
            (activity as AppCompatActivity).toolbar.title = "Career Information"
            initInfoFragment()
        }
    }

    private fun initInfoFragment(){
        val bundle = Bundle()
        val fragment = CourseInfoFragment()

        bundle.putSerializable("course", course)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().replace(R.id.career_details_container, fragment).commit()
    }

    private fun initCoursesFragment(){
        val bundle = Bundle()
        val fragment = CoursesFragment()

        bundle.putSerializable("course", course)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().replace(R.id.career_details_container, fragment).commit()
    }

}
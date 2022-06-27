package com.example.gestionacademicaapp.ui.view.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.databinding.FragmentCourseDetailsBinding
import com.example.gestionacademicaapp.ui.view.group.GroupsFragment
import kotlinx.android.synthetic.main.nav_fragment_container.*


class CourseDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCourseDetailsBinding
    private lateinit var course: CareerCourseModel
    private var preferredShowedFragment: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCourseDetailsBinding.inflate(inflater, container, false)

        course = arguments?.getSerializable("course") as CareerCourseModel

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
                R.id.course_groups-> {
                    (activity as AppCompatActivity).toolbar.title = "Groups"
                    initGroupsFragment()
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
                    (activity as AppCompatActivity).toolbar.title = "Course Information"
                    initInfoFragment()
                }
                2 -> {
                   (activity as AppCompatActivity).toolbar.title = "Courses"
                    initGroupsFragment()
                }
            }
        }
        else{
            (activity as AppCompatActivity).toolbar.title = "Course Information"
            initInfoFragment()
        }

    }

    private fun initInfoFragment(){
        val bundle = Bundle()
        val fragment = CourseInfoFragment()

        bundle.putSerializable("course", course)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().replace(R.id.course_details_container, fragment).commit()
    }

    private fun initGroupsFragment(){
        val bundle = Bundle()
        val fragment = GroupsFragment()

        bundle.putSerializable("course", course?.Course)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().replace(R.id.course_details_container, fragment).commit()
    }

}
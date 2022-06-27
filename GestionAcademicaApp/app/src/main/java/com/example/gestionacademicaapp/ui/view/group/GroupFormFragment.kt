package com.example.gestionacademicaapp.ui.view.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.core.utils.enums.ViewMode
import com.example.gestionacademicaapp.data.model.CourseGroupModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.model.GroupModel
import com.example.gestionacademicaapp.data.model.TeacherModel
import com.example.gestionacademicaapp.databinding.FragmentGroupFormBinding
import com.example.gestionacademicaapp.ui.viewmodel.GroupViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupFormFragment : Fragment() {

    private lateinit var binding: FragmentGroupFormBinding
    private lateinit var viewMode: ViewMode
    private val viewModel: GroupViewModel by viewModels()
    private var group: GroupModel? = null
    private var course: CourseModel? = null
    private var teacherSelected: TeacherModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentGroupFormBinding.inflate(inflater, container, false)
        course = arguments?.getSerializable("course") as CourseModel

        //Get group to edit (Might be null)
        val groupArg = arguments?.getSerializable("group")
        if (groupArg != null)
            group = groupArg as GroupModel

        initObservers()
        mainInitializer()

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getTeachers()
        }

        return binding.root
    }

    private fun mainInitializer() {
        val mode = arguments?.getSerializable("viewMode")
        viewMode = if (mode == null) ViewMode.CREATE else mode as ViewMode

        when (viewMode) {
            ViewMode.EDIT -> {
                initEditListeners()
                initEditFields()
            }
            ViewMode.VIEW -> {
                initViewDetails()
            }
            else -> { //ViewMode.Create
                initCreateListeners()
            }
        }

        binding.groupTeacherAutoComplete.onItemClickListener = dropdownOnClickListener
    }

    private val dropdownOnClickListener =
        AdapterView.OnItemClickListener { _, _, position, _ ->
            teacherSelected = viewModel.teachers.value?.get(position)!!
        }

    private fun initObservers() {
        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
        viewModel.teachers.observe(this) {
            setTeachersToDropDown(it)
        }
    }
    private fun setTeachersToDropDown(teachers: ArrayList<TeacherModel>) {
        val items = arrayListOf<String>()
        teachers.forEach { items.add(it.Name) }

        val arrayAdapter = ArrayAdapter(context!!, R.layout.template_dropdown_item, items)
        binding.groupTeacherAutoComplete.setAdapter(arrayAdapter)
    }
    private fun getGroupFromView(): GroupModel {
        val groupNumber = binding.groupNumber.editText?.text.toString()
        val groupSchedule = binding.groupSchedule.editText?.text.toString()

        return GroupModel(0, groupNumber, groupSchedule, teacherSelected, null, course!!)
    }

    private fun setGroupFormFields() {
        binding.groupNumber.editText?.setText(group?.Number)
        binding.groupSchedule.editText?.setText(group?.Schedule)
        binding.groupTeacherAutoComplete.setText(group?.Teacher?.Name, false)
    }

    //<editor-fold desc="Create Group">
    private fun initCreateListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = createGroup()
                if (response) {

                    val bundle = Bundle()
                    val fragment = GroupsFragment()

                    bundle.putSerializable("course", course)
                    fragment.arguments = bundle

                    Toast.makeText(context, "Group added!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit()
                } else
                    Toast.makeText(context, "There was an error adding the group", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun createGroup(): Boolean {
        val group = getGroupFromView()
        val courseGroup = CourseGroupModel(0, course!!, group)
        return viewModel.createCourseGroup(courseGroup)
    }
    //</editor-fold>

    //<editor-fold desc="Edit Group">
    private fun initEditListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = editGroup()
                if (response) {
                    val bundle = Bundle()
                    val fragment = GroupsFragment()

                    bundle.putSerializable("course", course)
                    fragment.arguments = bundle

                    Toast.makeText(context, "Group edited!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                } else {
                    Toast.makeText(context, "An error occurred while editing!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEditFields() {
        setGroupFormFields()
    }

    private suspend fun editGroup(): Boolean {
        val group = getGroupFromView()

        this.group?.Number = group.Number
        this.group?.Schedule = group.Schedule
        this.group?.Teacher = if (teacherSelected != null) teacherSelected!! else this.group?.Teacher!!
        this.group?.Course = course!!

        return viewModel.updateGroup(this.group!!)
    }
    //</editor-fold>

    //<editor-fold desc="Group Details">
    private fun initViewDetails() {
        binding.createButton.visibility = View.INVISIBLE
        binding.groupNumber.editText?.focusable = View.NOT_FOCUSABLE
        binding.groupSchedule.editText?.focusable = View.NOT_FOCUSABLE

        binding.groupTeacherAutoComplete.isEnabled = false
        binding.groupTeacher.isEnabled = false

        setGroupFormFields()
    }
    //</editor-fold>

}
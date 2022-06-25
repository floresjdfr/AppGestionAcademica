package com.example.gestionacademicaapp.ui.view.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.core.utils.enums.ViewMode
import com.example.gestionacademicaapp.data.model.TeacherModel
import com.example.gestionacademicaapp.data.model.user.EnumUserType
import com.example.gestionacademicaapp.data.model.user.UserModel
import com.example.gestionacademicaapp.data.model.user.UserType
import com.example.gestionacademicaapp.databinding.FragmentTeacherFormBinding
import com.example.gestionacademicaapp.ui.viewmodel.TeacherViewModel
import kotlinx.android.synthetic.main.fragment_teacher_form.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TeacherFormFragment : Fragment() {

    private lateinit var binding: FragmentTeacherFormBinding
    private val viewModel: TeacherViewModel by viewModels()
    private var teacher: TeacherModel? = null
    private lateinit var viewMode: ViewMode
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTeacherFormBinding.inflate(inflater, container, false)

        //Get teacher to edit (Might be null)
        val teacherArg = arguments?.getSerializable("teacher")
        if (teacherArg != null)
            teacher = teacherArg as TeacherModel

        initObservers()
        mainInitializer()

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
    }

    private fun initObservers() {
        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
    }

    private fun getTeacherFromView(): TeacherModel {
        val teacherId = binding.teacherID.editText?.text.toString()
        val teacherName = binding.teacherName.editText?.text.toString()
        val teacherPhone = binding.teacherPhone.editText?.text.toString()
        val teacherEmail = binding.teacherEmail.editText?.text.toString()
        val password = binding.teacherPassword.editText?.text.toString()

        val user = UserModel(0, teacherId, password, UserType(EnumUserType.Profesor.id,""))

        return TeacherModel(0, teacherId, teacherName, teacherPhone, teacherEmail, user)
    }

    private fun setTeacherFormFields() {
        binding.teacherID.editText?.setText(teacher?.IdIdentidad)
        binding.teacherName.editText?.setText(teacher?.Name) 
        binding.teacherPhone.editText?.setText(teacher?.PhoneNumber)
        binding.teacherEmail.editText?.setText(teacher?.Email)
    }

    //<editor-fold desc="Create Teacher">
    private fun initCreateListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = createTeacher()
                if (response) {
                    Toast.makeText(context, "Teacher added!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, TeachersFragment())
                        .commit()
                } else
                    Toast.makeText(context, "There was an error adding the teacher", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private suspend fun createTeacher(): Boolean {
        val teacher = getTeacherFromView()
        return viewModel.createTeacher(teacher)
    }
    //</editor-fold>

    //<editor-fold desc="Edit Teacher">
    private fun initEditListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = editTeacher()
                if (response) {
                    Toast.makeText(context, "Teacher edited!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, TeachersFragment()).commit()
                } else {
                    Toast.makeText(context, "An error occurred while editing!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEditFields() {
        binding.teacherID.editText?.focusable = View.NOT_FOCUSABLE

        setTeacherFormFields()
    }

    private suspend fun editTeacher(): Boolean {
        val teacher = getTeacherFromView()

        this.teacher?.IdIdentidad = teacher.IdIdentidad
        this.teacher?.Name = teacher.Name
        this.teacher?.PhoneNumber = teacher.PhoneNumber
        this.teacher?.Email = teacher.Email
        this.teacher?.User = teacher.User

        return viewModel.updateTeacher(this.teacher!!)
    }
    //</editor-fold>

    //<editor-fold desc="Teacher Details">
    private fun initViewDetails() {
        binding.createButton.visibility = View.INVISIBLE
        binding.teacherID.editText?.focusable = View.NOT_FOCUSABLE
        binding.teacherName.editText?.focusable = View.NOT_FOCUSABLE
        binding.teacherPhone.editText?.focusable = View.NOT_FOCUSABLE
        binding.teacherEmail.editText?.focusable = View.NOT_FOCUSABLE
        binding.teacherPassword.editText?.focusable = View.NOT_FOCUSABLE

        setTeacherFormFields()
    }
    //</editor-fold>
}
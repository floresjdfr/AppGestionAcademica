package com.example.gestionacademicaapp.ui.view.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.core.utils.enums.ViewMode
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.StudentModel
import com.example.gestionacademicaapp.data.model.user.UserModel
import com.example.gestionacademicaapp.data.model.user.UserType
import com.example.gestionacademicaapp.databinding.FragmentStudentFormBinding
import com.example.gestionacademicaapp.ui.viewmodel.StudentViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class StudentFormFragment : Fragment() {

    private lateinit var binding: FragmentStudentFormBinding
    private val viewModel: StudentViewModel by viewModels()
    private var student: StudentModel? = null
    private lateinit var career: CareerModel
    private lateinit var viewMode: ViewMode
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    private lateinit var datePicker: MaterialDatePicker<Long>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentStudentFormBinding.inflate(inflater, container, false)

        //Get student to edit (Might be null)
        val studentArg = arguments?.getSerializable("student")
        if (studentArg != null)
            student = studentArg as StudentModel

        initObservers()
        mainInitializer()

        return binding.root
    }

    private fun mainInitializer() {
        val mode = arguments?.getSerializable("viewMode")

        viewMode = if (mode == null) ViewMode.CREATE else mode as ViewMode

        when (viewMode) {
            ViewMode.EDIT -> {
                getCareers()
                initDatePickers(student)
                initEditListeners()
                initEditFields()
            }
            ViewMode.VIEW -> {
                initViewDetails()
            }
            else -> { //ViewMode.Create
                getCareers()
                initDatePickers()
                initCreateListeners()
            }
        }
    }

    private fun initDatePickers(student: StudentModel? = null) {
        val startDate = student?.DateOfBirth?.time ?: MaterialDatePicker.todayInUtcMilliseconds()

        val calendarStart = Calendar.getInstance()
        val calendarEnd = Calendar.getInstance()
        val currentYear = calendarStart.get(Calendar.YEAR)

        calendarStart.set(currentYear - 60, 1, 1)

        val startFrom = calendarStart.timeInMillis
        val upTo = calendarEnd.timeInMillis
        val constraints = CalendarConstraints.Builder().setStart(startFrom).setEnd(upTo).build()

        //Date pickers
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setCalendarConstraints(constraints)
            .setSelection(startDate)
            .build()
    }

    private fun initDatePickersListeners() {
        binding.studentDateOfBirth.editText?.setOnClickListener {
            datePicker.show(parentFragmentManager, "DATEPICKER")
        }

        datePicker.addOnPositiveButtonClickListener {

            val dateSelected = datePicker.selection
            val date = Date(dateSelected!!)


            binding.studentDateOfBirth.editText?.setText(dateFormat.format(date))
        }
    }

    private fun initObservers() {
        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
        viewModel.careers.observe(this){
            setCareersToDropDown(it)
        }
    }

    private fun setCareersToDropDown(careers: ArrayList<CareerModel>){
        val items = arrayListOf<String>()
        careers.forEach { items.add(it.CareerName) }

        val arrayAdapter = ArrayAdapter(context!!, R.layout.template_dropdown_item, items)
        binding.autoComplete.setAdapter(arrayAdapter)
    }

    private val dropdownOnClickListener =
        OnItemClickListener { _, _, position, _ ->
            career = viewModel.careers.value?.get(position)!!
            println("Career selected: ${career.CareerName}")
        }

    private fun getCareers(){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getCareers()
        }
    }

    private fun getStudentFromView(): StudentModel {
        val studentId = binding.studentID.editText?.text.toString()
        val studentName = binding.studentName.editText?.text.toString()
        val studentPhone = binding.studentPhone.editText?.text.toString()
        val studentEmail = binding.studentEmail.editText?.text.toString()
        val password = binding.studentPassword.editText?.text.toString()
        val dateOfBirth = dateFormat.parse(binding.studentDateOfBirth.editText?.text.toString())

        val user = UserModel(0, studentId, password, UserType(""))

        return StudentModel(0, studentId, studentName, studentPhone, studentEmail, dateOfBirth!!, user, null)
    }

    private fun setStudentFormFields() {
        binding.studentID.editText?.setText(student?.IdStudent)
        binding.studentName.editText?.setText(student?.Name)
        binding.studentPhone.editText?.setText(student?.PhoneNumber)
        binding.studentEmail.editText?.setText(student?.Email)
    }

    //<editor-fold desc="Create Student">
    private fun initCreateListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = createStudent()
                if (response) {
                    Toast.makeText(context, "Student added!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, StudentsFragment())
                        .commit()
                } else
                    Toast.makeText(context, "There was an error adding the student", Toast.LENGTH_SHORT).show()

            }
        }
        binding.autoComplete.onItemClickListener = dropdownOnClickListener

        initDatePickersListeners()
    }

    private suspend fun createStudent(): Boolean {
        val student = getStudentFromView()
        student.Career = career
        return viewModel.createStudent(student)
    }
    //</editor-fold>

    //<editor-fold desc="Edit Student">
    private fun initEditListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = editStudent()
                if (response) {
                    Toast.makeText(context, "Student edited!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, StudentsFragment())
                        .commit()
                } else {
                    Toast.makeText(context, "An error occurred while editing!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        initDatePickersListeners()
    }

    private fun initEditFields() {
        binding.studentID.editText?.focusable = View.NOT_FOCUSABLE

        setStudentFormFields()
    }

    private suspend fun editStudent(): Boolean {
        val student = getStudentFromView()

        this.student?.IdStudent = student.IdStudent
        this.student?.Name = student.Name
        this.student?.PhoneNumber = student.PhoneNumber
        this.student?.Email = student.Email
        this.student?.User = student.User

        return viewModel.updateStudent(this.student!!)
    }
    //</editor-fold>

    //<editor-fold desc="Student Details">
    private fun initViewDetails() {
        binding.createButton.visibility = View.INVISIBLE
        binding.studentID.editText?.focusable = View.NOT_FOCUSABLE
        binding.studentName.editText?.focusable = View.NOT_FOCUSABLE
        binding.studentPhone.editText?.focusable = View.NOT_FOCUSABLE
        binding.studentEmail.editText?.focusable = View.NOT_FOCUSABLE
        binding.studentPassword.editText?.focusable = View.NOT_FOCUSABLE

        setStudentFormFields()
    }
    //</editor-fold>
}
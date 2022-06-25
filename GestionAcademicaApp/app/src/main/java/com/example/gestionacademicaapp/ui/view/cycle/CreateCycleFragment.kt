package com.example.gestionacademicaapp.ui.view.cycle

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.core.utils.enums.ViewMode
import com.example.gestionacademicaapp.data.model.cycle.CycleModel
import com.example.gestionacademicaapp.data.model.cycle.CycleStateModel
import com.example.gestionacademicaapp.data.model.cycle.CycleStates
import com.example.gestionacademicaapp.databinding.FragmentCreateCycleBinding
import com.example.gestionacademicaapp.ui.viewmodel.CycleViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateCycleFragment : Fragment() {

    private lateinit var binding: FragmentCreateCycleBinding
    private val viewModel: CycleViewModel by viewModels()
    private var cycle: CycleModel? = null
    private val toStringDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
    private lateinit var startDatePicker: MaterialDatePicker<Long>
    private lateinit var endDatePicker: MaterialDatePicker<Long>
    private lateinit var viewMode: ViewMode

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateCycleBinding.inflate(inflater, container, false)

        //Get cycle to edit (Might be null)
        val cycleArg = arguments?.getSerializable("cycle")
        if (cycleArg != null)
            cycle = cycleArg as CycleModel

        initObservers()
        initCycleStatesDropdown()

        mainInitializer()

        initDatePickersListeners()

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
                initCreateFields()
            }
        }
    }

    private fun initObservers() {
        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
    }

    private fun datepickerListeners() {
        binding.cycleStartDate.editText?.setOnClickListener {
            startDatePicker.show(parentFragmentManager, "tag")
            hideKeyboard()
        }

        binding.cycleEndDate.editText?.setOnClickListener {
            endDatePicker.show(parentFragmentManager, "tag")
            hideKeyboard()
        }
    }

    private fun initDatePickers(cycle: CycleModel? = null) {
        val startDate = cycle?.StartDate?.time ?: MaterialDatePicker.todayInUtcMilliseconds()
        val endDate = cycle?.EndDate?.time ?: MaterialDatePicker.todayInUtcMilliseconds()

        val calendarStart = Calendar.getInstance()
        val calendarEnd = Calendar.getInstance()
        val currentYear = calendarStart.get(Calendar.YEAR)

        calendarEnd.set(currentYear+5, 12, 31)

        val startFrom = calendarStart.timeInMillis
        val upTo = calendarEnd.timeInMillis
        val constraints = CalendarConstraints.Builder().setStart(startFrom).setEnd(upTo).build()


        //Date pickers
        startDatePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setCalendarConstraints(constraints)
            .setSelection(startDate)
            .build()

        endDatePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setCalendarConstraints(constraints)
            .setSelection(endDate)
            .build()

        //Removing focusable to avoid keyboard
        binding.cycleStartDate.editText?.focusable = View.NOT_FOCUSABLE
        binding.cycleEndDate.editText?.focusable = View.NOT_FOCUSABLE
    }

    private fun initDatePickersListeners() {
        startDatePicker.addOnPositiveButtonClickListener {
            val dateSelected = startDatePicker.headerText
            binding.cycleStartDate.editText?.setText(dateSelected)
        }
        endDatePicker.addOnPositiveButtonClickListener {
            val dateSelected = endDatePicker.headerText
            binding.cycleEndDate.editText?.setText(dateSelected)
        }
    }

    private fun initCycleStatesDropdown() {
        val options = arrayListOf<String>()
        CycleStates.values().forEach {
            options.add(it.description)
        }
        val arrayAdapter = ArrayAdapter(context!!, R.layout.template_dropdown_item, options)
        binding.autoComplete.setAdapter(arrayAdapter)
    }

    private fun getCycleFromView(): CycleModel {
        val cycleNumber = binding.cycleNumber.editText?.text.toString().toInt()
        val cycleYear = binding.cycleYear.editText?.text.toString().toInt()
        val cycleStartDate = toStringDateFormat.parse(binding.cycleStartDate.editText?.text.toString())
        val cycleEndDate = toStringDateFormat.parse(binding.cycleEndDate.editText?.text.toString())
        val cycleStatusOption = binding.autoComplete.text.toString()
        val cycleStatusInt =
            if (CycleStates.ACTIVE.description == cycleStatusOption) CycleStates.ACTIVE.id else CycleStates.INACTIVE.id

        val cycleState = CycleStateModel(cycleStatusInt, "")
        return CycleModel(0, cycleYear, cycleNumber, cycleStartDate!!, cycleEndDate!!, cycleState)
    }

    private fun setCycleFormFields() {
        val stateString =
            if (cycle?.CycleState?.ID == CycleStates.ACTIVE.id) CycleStates.ACTIVE.description else CycleStates.INACTIVE.description

        binding.cycleNumber.editText?.setText(cycle?.Number.toString())
        binding.cycleYear.editText?.setText(cycle?.Year.toString())
        binding.cycleStartDate.editText?.setText(toStringDateFormat.format(cycle?.StartDate!!))
        binding.cycleEndDate.editText?.setText(toStringDateFormat.format(cycle?.EndDate!!))
        binding.autoComplete.setText(stateString, false)
    }
    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //<editor-fold desc="Create Cycle">
    private fun initCreateListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = createCycle()
                if (response) {
                    Toast.makeText(context, "Cycle added!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, CyclesFragment()).commit()
                } else
                    Toast.makeText(context, "There was an error adding the cycle", Toast.LENGTH_SHORT).show()

            }
        }

        binding.autoComplete.setOnClickListener {
            hideKeyboard()
        }

        datepickerListeners()
    }

    private fun initCreateFields() {
        initDatePickers()
    }

    private suspend fun createCycle(): Boolean {
        val cycle = getCycleFromView()
        return viewModel.createCycle(cycle)
    }
    //</editor-fold>

    //<editor-fold desc="Edit Cycle">
    private fun initEditListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = editCycle()
                if (response) {
                    Toast.makeText(context, "Cycle edited!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, CyclesFragment()).commit()
                } else {
                    Toast.makeText(context, "An error occurred while editing!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        datepickerListeners()
    }

    private fun initEditFields() {
        setCycleFormFields()
        initDatePickers(cycle)
    }

    private suspend fun editCycle(): Boolean {
        val cycle = getCycleFromView()

        this.cycle?.Number = cycle.Number
        this.cycle?.Year = cycle.Year
        this.cycle?.StartDate = cycle.StartDate
        this.cycle?.EndDate = cycle.EndDate
        this.cycle?.CycleState = cycle.CycleState

        return viewModel.updateCycle(this.cycle!!)
    }
    //</editor-fold>

    //<editor-fold desc="Cycle Details">
    private fun initViewDetails() {
        binding.createButton.visibility = View.INVISIBLE
        binding.cycleYear.editText?.focusable = View.NOT_FOCUSABLE
        binding.cycleNumber.editText?.focusable = View.NOT_FOCUSABLE
        binding.cycleStartDate.editText?.isClickable = false
        binding.cycleEndDate.editText?.isClickable = false
        binding.autoComplete.focusable = View.NOT_FOCUSABLE

        binding.cycleState.endIconMode = TextInputLayout.END_ICON_NONE
        binding.cycleState.isEnabled = false




        setCycleFormFields()
        initDatePickers(cycle)
    }
    //</editor-fold>


}
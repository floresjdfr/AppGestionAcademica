package com.example.gestionacademicaapp.ui.view.user

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
import com.example.gestionacademicaapp.data.model.user.UserModel
import com.example.gestionacademicaapp.data.model.user.UserType
import com.example.gestionacademicaapp.databinding.FragmentUserFormBinding
import com.example.gestionacademicaapp.ui.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class UserFormFragment : Fragment() {

    private lateinit var binding: FragmentUserFormBinding
    private val viewModel: UserViewModel by viewModels()
    private var user: UserModel? = null
    private var userType: UserType? = null
    private lateinit var viewMode: ViewMode
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUserFormBinding.inflate(inflater, container, false)

        //Get user to edit (Might be null)
        val userArg = arguments?.getSerializable("user")
        if (userArg != null){
            user = userArg as UserModel
            userType = user?.UserType
        }


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
                viewModel.getUserTypes()
            }
            ViewMode.VIEW -> {
                initViewDetails()
            }
            else -> { //ViewMode.Create
                initCreateListeners()
                viewModel.getUserTypes()
            }
        }

        binding.autoComplete.onItemClickListener = dropdownOnClickListener
    }

    private val dropdownOnClickListener =
        AdapterView.OnItemClickListener { _, _, position, _ ->
            userType = viewModel.userTypes.value?.get(position)!!
        }

    private fun initObservers() {
        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
        viewModel.userTypes.observe(this) {
            setUserTypesToDropDown(it)
        }
    }

    private fun setUserTypesToDropDown(userTypes: ArrayList<UserType>) {
        val items = arrayListOf<String>()
        userTypes.forEach { items.add(it.TypeDescription) }

        val arrayAdapter = ArrayAdapter(context!!, R.layout.template_dropdown_item, items)
        binding.autoComplete.setAdapter(arrayAdapter)
    }

    private fun getUserFromView(): UserModel {
        val userId = binding.userID.editText?.text.toString()
        val userPassword = binding.userPassword.editText?.text.toString()

        return UserModel(0, userId, userPassword, userType!!)
    }
    private fun setUserFormFields() {
        binding.userID.editText?.setText(user?.UserID)
        binding.userPassword.editText?.setText(user?.Password)
        binding.autoComplete.setText(user?.UserType?.TypeDescription, false)
    }

    //<editor-fold desc="Create User">
    private fun initCreateListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = createUser()
                if (response) {
                    Toast.makeText(context, "User added!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, UsersFragment())
                        .commit()
                } else
                    Toast.makeText(context, "There was an error adding the user", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private suspend fun createUser(): Boolean {
        val user = getUserFromView()
        return viewModel.createUser(user)
    }
    //</editor-fold>

    //<editor-fold desc="Edit User">
    private fun initEditListeners() {
        binding.createButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = editUser()
                if (response) {
                    Toast.makeText(context, "User edited!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.fragment_container, UsersFragment()).commit()
                } else {
                    Toast.makeText(context, "An error occurred while editing!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEditFields() {
        binding.userID.editText?.focusable = View.NOT_FOCUSABLE

        setUserFormFields()
    }

    private suspend fun editUser(): Boolean {
        val user = getUserFromView()

        this.user?.UserID = user.UserID
        this.user?.Password = user.Password
        this.user?.UserType = userType!!

        return viewModel.updateUser(this.user!!)
    }
    //</editor-fold>

    //<editor-fold desc="User Details">
    private fun initViewDetails() {
        binding.createButton.visibility = View.INVISIBLE
        binding.userID.editText?.focusable = View.NOT_FOCUSABLE
        binding.userPassword.editText?.focusable = View.NOT_FOCUSABLE
        binding.autoComplete.focusable = View.NOT_FOCUSABLE

        setUserFormFields()
    }
    //</editor-fold>
}
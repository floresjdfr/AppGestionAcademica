package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.data.model.CourseGroupModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.repository.CourseGroupRepository
import com.example.gestionacademicaapp.data.repository.GroupRepository

class GroupViewModel: ViewModel() {

    val courseGroups =  MutableLiveData<ArrayList<CourseGroupModel>>(ArrayList())
    val isLoading = MutableLiveData<Boolean>()
    fun getCourseGroups(course: CourseModel) {
        isLoading.postValue(true)

        val result = CourseGroupRepository.getCourseGroups(course)
        courseGroups.postValue(result)

        isLoading.postValue(false)
    }

    fun deleteGroup(itemToDelete: CourseGroupModel): Boolean {
        isLoading.postValue(true)

        var result = GroupRepository.deleteGroup(itemToDelete.Group.ID)//Deletes group
        if(result)
            result = CourseGroupRepository.deleteCourseGroup(itemToDelete.ID)//Deletes relationship with course

        isLoading.postValue(false)
        return result
    }
}
package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.data.model.CourseGroupModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.model.GroupModel
import com.example.gestionacademicaapp.data.model.TeacherModel
import com.example.gestionacademicaapp.data.repository.CourseGroupRepository
import com.example.gestionacademicaapp.data.repository.GroupRepository
import com.example.gestionacademicaapp.data.repository.TeacherRepository

class GroupViewModel: ViewModel() {

    val teachers = MutableLiveData<ArrayList<TeacherModel>>(ArrayList())
    val courseGroups =  MutableLiveData<ArrayList<CourseGroupModel>>(ArrayList())
    val isLoading = MutableLiveData<Boolean>()
    suspend fun getCourseGroups(course: CourseModel) {
        isLoading.postValue(true)

        val result = CourseGroupRepository.getCourseGroups(course.ID)
        courseGroups.postValue(result)

        isLoading.postValue(false)
    }

    suspend fun getTeachers() {
        isLoading.postValue(true)

        val result = TeacherRepository.getTeachers()
        teachers.postValue(result)

        isLoading.postValue(false)
    }

    suspend fun createCourseGroup(courseGroup: CourseGroupModel): Boolean {
        isLoading.postValue(true)
        val result = CourseGroupRepository.createCourseGroup(courseGroup)
        isLoading.postValue(false)
        return result
    }
    suspend fun deleteGroup(itemToDelete: CourseGroupModel): Boolean {
        isLoading.postValue(true)

        var result = CourseGroupRepository.deleteCourseGroup(itemToDelete.ID)

        isLoading.postValue(false)
        return result
    }

    suspend fun updateGroup(group: GroupModel): Boolean {
        isLoading.postValue(true)
        val result = GroupRepository.updateGroup(group.ID, group)
        isLoading.postValue(false)
        return result
    }
}
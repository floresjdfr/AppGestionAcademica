package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.CourseGroupModel
import com.example.gestionacademicaapp.data.model.cycle.CycleStates
import com.example.gestionacademicaapp.data.network.courseGroup.CourseGroupService

class CourseGroupRepository {
    companion object {
        private val api = CourseGroupService()
        suspend fun getCourseGroups(id: Int): ArrayList<CourseGroupModel> {
            val groups = api.getCourseGroups(id)
            val activeGroups: ArrayList<CourseGroupModel> = ArrayList()
            groups.forEach {
                if(it.Group.Cycle?.CycleState?.ID == CycleStates.ACTIVE.id)
                    activeGroups.add(it)
            }
            return activeGroups
        }
        suspend fun deleteCourseGroup(id: Int): Boolean {
            return api.deleteCourseGroup(id)
        }
        suspend fun getCourseGroup(): ArrayList<CourseGroupModel> {
            TODO("Not yet implemented")
        }

        suspend fun createCourseGroup(courseGroup: CourseGroupModel): Boolean {
            return api.createCourseGroup(courseGroup)
        }

        suspend fun updateCourseGroup(id: Int, courseGroup: CourseGroupModel): Boolean {
            TODO("Not yet implemented")
        }
    }
}
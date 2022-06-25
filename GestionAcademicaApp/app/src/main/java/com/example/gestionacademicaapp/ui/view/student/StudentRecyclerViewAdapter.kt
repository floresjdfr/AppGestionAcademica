package com.example.gestionacademicaapp.ui.view.student

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.data.model.StudentModel
import kotlinx.android.synthetic.main.template_student.view.*

class StudentRecyclerViewAdapter(items: List<StudentModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var itemsList: List<StudentModel>? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var mContext: Context
    private lateinit var mListener: OnItemClickListener

    class CourseHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    init {
        this.itemsList = items
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val courseListView = LayoutInflater.from(parent.context).inflate(R.layout.template_student, parent, false)
        val sch = CourseHolder(courseListView, mListener)
        mContext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.rv_studentID.text = item?.IdStudent
        holder.itemView.rv_studentName.text = item?.Name
    }

    fun getAtPosition(position: Int): StudentModel? {
        return itemsList?.get(position)
    }

    fun deleteAtPosition(position: Int) {
        val auxList = ArrayList<StudentModel>()
        val elementToDelete = itemsList?.get(position)
        itemsList?.forEach {
            if (it.ID != elementToDelete?.ID)
                auxList.add((it))
        }
        itemsList = auxList
    }
}
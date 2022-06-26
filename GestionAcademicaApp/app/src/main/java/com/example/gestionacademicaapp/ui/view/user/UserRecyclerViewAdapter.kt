package com.example.gestionacademicaapp.ui.view.user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.data.model.user.UserModel
import kotlinx.android.synthetic.main.template_user.view.*

class UserRecyclerViewAdapter(items: List<UserModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemsList: List<UserModel>? = null

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
        val courseListView = LayoutInflater.from(parent.context).inflate(R.layout.template_user, parent, false)
        val sch = CourseHolder(courseListView, mListener)
        mContext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.rv_userID.text = item?.UserID
        holder.itemView.rv_userType.text = item?.UserType?.TypeDescription
    }

    fun getAtPosition(position: Int): UserModel? {
        return itemsList?.get(position)
    }

    fun deleteAtPosition(position: Int) {
        val auxList = ArrayList<UserModel>()
        val elementToDelete = itemsList?.get(position)
        itemsList?.forEach {
            if (it.ID != elementToDelete?.ID)
                auxList.add((it))
        }
        itemsList = auxList
    }
}
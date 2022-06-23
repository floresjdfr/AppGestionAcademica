package com.example.gestionacademicaapp.ui.view.cycle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.data.model.CycleModel
import kotlinx.android.synthetic.main.template_cycle.view.*

class CycleAdapterRecyclerView(items: List<CycleModel>) : RecyclerView.Adapter<ViewHolder>() {

    var itemsList: List<CycleModel>? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var mContext: Context
    private lateinit var mListener: OnItemClickListener

    class CourseHolder(itemView: View, listener: OnItemClickListener) : ViewHolder(itemView) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val courseListView = LayoutInflater.from(parent.context).inflate(R.layout.template_cycle, parent, false)
        val sch = CourseHolder(courseListView, mListener)
        mContext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.rv_cycleYear.text = item?.Year.toString()
        holder.itemView.rv_cycleNumber.text = item?.Number.toString()

        holder.itemView.rv_cycle_isActive.visibility = if (item?.CycleState?.ID == 1) View.VISIBLE else View.INVISIBLE
    }

    fun getAtPosition(position: Int): CycleModel? {
        return itemsList?.get(position)
    }

    fun deleteAtPosition(position: Int) {
        val auxList = ArrayList<CycleModel>()
        val elementToDelete = itemsList?.get(position)
        itemsList?.forEach {
            if (it.ID != elementToDelete?.ID)
                auxList.add((it))
        }
        itemsList = auxList
    }
}
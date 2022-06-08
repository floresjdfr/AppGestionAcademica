package com.example.gestionacademicaapp.ui.career

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.models.Career
import kotlinx.android.synthetic.main.template_career.view.*

class CareerRecyclerViewAdapter(items: ArrayList<Career>) : RecyclerView.Adapter<ViewHolder>() {

    var itemsList: ArrayList<Career>? = null

    lateinit var mcontext: Context

    class CareerHolder(itemView: View) : ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val careerListView = LayoutInflater.from(parent.context).inflate(R.layout.template_career, parent, false)
        val sch = CareerHolder(careerListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.recyclerview_career_id.text = item?.Code
        holder.itemView.recyclerview_career_name.text = item?.CareerName
    }
}
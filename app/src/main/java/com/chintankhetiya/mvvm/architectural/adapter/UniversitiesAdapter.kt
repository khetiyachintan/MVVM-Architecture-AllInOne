package com.chintankhetiya.mvvm.architectural.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chintankhetiya.mvvm.architectural.R
import com.chintankhetiya.mvvm.architectural.model.UniversitiesEntity
import com.chintankhetiya.mvvm.architectural.databinding.RowUniversityItemBinding

class UniversitiesAdapter(private val arrUniversitiesList: ArrayList<UniversitiesEntity>) :
    RecyclerView.Adapter<UniversitiesAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: RowUniversityItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_university_item,
            parent,
            false
        )

    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.universitiesList = arrUniversitiesList[position]
    }

    override fun getItemCount(): Int = arrUniversitiesList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun clear() {
        arrUniversitiesList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: ArrayList<UniversitiesEntity>) {
        arrUniversitiesList.addAll(list)
        notifyDataSetChanged()
    }
}
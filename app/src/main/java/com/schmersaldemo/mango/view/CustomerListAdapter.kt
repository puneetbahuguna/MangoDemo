package com.schmersaldemo.mango.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.schmersaldemo.mango.data.entity.Customer

/***
Author: Puneet Bahuguna
 ***/
class CustomerListAdapter (private val list: List<Customer>): RecyclerView.Adapter<CustomerListviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerListviewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return CustomerListviewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: CustomerListviewHolder, position: Int) {
    val customer:Customer=list[position]
        holder.bind(customer)
    }
    override fun getItemCount(): Int=list.size

}
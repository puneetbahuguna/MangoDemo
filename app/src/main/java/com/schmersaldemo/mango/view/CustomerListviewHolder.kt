package com.schmersaldemo.mango.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.data.entity.Customer

/***
Author: Puneet Bahuguna
 ***/

class CustomerListviewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

    private var customername:TextView?=null
    private var address:TextView?=null
    private var country:TextView?=null

    init {
        customername = itemView.findViewById(R.id.customername)
        address = itemView.findViewById(R.id.address)
        country = itemView.findViewById(R.id.country)
    }
    fun bind(customer:Customer) {
        customername?.text = customer.name
        address?.text=customer.address
        country?.text=customer.country
    }
    }
package com.example.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.room.R
import com.example.room.models.Contact

class ContactAdapter(val contacts:List<Contact>, val itemClickListener: OnItemClickListener): Adapter<ContactPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactPrototype {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_contact, parent, false)

        return ContactPrototype(view)
    }

    override fun onBindViewHolder(holder: ContactPrototype, position: Int) {
       holder.bind(contacts.get(position), itemClickListener)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}

class ContactPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvTelephone = itemView.findViewById<TextView>(R.id.tvTelephone)
    val cvContact = itemView.findViewById<CardView>(R.id.cvContact)

    fun bind(contact: Contact, itemClickListener: OnItemClickListener){
        tvName.text = contact.name
        tvTelephone.text = contact.telephone

        cvContact.setOnClickListener {
            itemClickListener.OnItemClicked(contact)
        }
    }
}

interface OnItemClickListener{
    fun OnItemClicked(contact: Contact)
}

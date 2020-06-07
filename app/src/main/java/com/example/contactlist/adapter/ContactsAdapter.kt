package com.example.contactlist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactlist.R
import com.example.contactlist.model.Contact


class ContactsAdapter(var contactList: MutableList<Contact>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {


    private var onContactItemClickedListener: OnContactItemClickedListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onContactItemClickedListener!!.onContactItemClicked(contactList!!.get(position))
            }


        val contact = contactList[position]
        holder.bindItems(contact)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(contact: Contact) {

            val txt = itemView.findViewById<TextView>(R.id.name)

            val photo = itemView.findViewById<ImageView>(R.id.photo)

            val surname = itemView.findViewById<TextView>(R.id.surname)

            val gmail = itemView.findViewById<TextView>(R.id.email)

            txt.text = contact.name

            surname.text = contact.surname

            gmail.text = contact.email

            Glide.with(itemView)
                .load(contact.image)
                .placeholder(R.drawable.noimage).error(R.drawable.noimage)
                .fallback(R.drawable.noimage)
                .into(photo);


        }
    }


    fun setContactItemClickedListener(onContactItemClickedListener: OnContactItemClickedListener) {
        this.onContactItemClickedListener = onContactItemClickedListener

    }

    interface OnContactItemClickedListener {
        fun onContactItemClicked(contact: Contact)


    }




}
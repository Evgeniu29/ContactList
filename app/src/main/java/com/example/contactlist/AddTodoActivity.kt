package com.example.contactlist
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.bumptech.glide.Glide
import com.example.contactlist.model.Contact
import com.example.contactlist.model.ContactsListDatabase

import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTodoActivity:   MainActivity(){

    private var contactListDatabase: ContactsListDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        contactListDatabase = ContactsListDatabase.getInstance(this)

        val edit_name = findViewById<EditText>(R.id.name)

        val edit_surname = findViewById<EditText>(R.id.surname)

        val edit_pandemicname = findViewById<EditText>(R.id.pandemicname)


        val edit_email = findViewById<EditText>(R.id.email)

        val edit_year = findViewById<EditText>(R.id.year_of_birth)

        val add_contact = findViewById<Button>(R.id.add_contact)

        val reverse = findViewById<Button>(R.id.reverse)

        val image = intent.getStringExtra("image")

        Glide.with(photo)
            .load(image)
            .placeholder(R.drawable.noimage).error(R.drawable.noimage)
            .fallback(R.drawable.noimage)
            .into(photo);

        val id = intent.getIntExtra("id", 0)

        var name = intent.getStringExtra("name")

        var surname = intent.getStringExtra("surname")

        var pandemicname = intent.getStringExtra("pandemicname")

        var  email = intent.getStringExtra("email")

        var year_of_birth = intent.getStringExtra("year_of_birth")


        edit_name.setText(name)

        edit_surname.setText(surname)

        edit_email.setText(email)

        edit_pandemicname.setText(pandemicname)

        edit_year.setText(year_of_birth)

        add_contact.setOnClickListener() {


            var contact = Contact(
                id,
                image!!,
                edit_name.text.toString(),
                edit_surname.text.toString(),
                edit_pandemicname.text.toString(),
                edit_email.text.toString(),
                edit_year.text.toString()

            )

            GlobalScope.launch  {

                contactListDatabase!!.getContactsDao().updateContact(contact)

            }

            finish()

        }

        reverse.setOnClickListener(){

            edit_name.setText(name.toString())

            edit_surname.setText(surname.toString())

            edit_pandemicname.setText(pandemicname.toString())

            edit_email.setText(email.toString())

            edit_year.setText(year_of_birth.toString())

        }

    }


}





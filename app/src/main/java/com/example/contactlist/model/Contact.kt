package com.example.contactlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contactlist.model.Contact.Companion.CONTACTS_TABLE_NAME

@Entity(tableName = CONTACTS_TABLE_NAME)
data class Contact (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var image:String= "",
    var name: String = "",
    var surname: String = "",
    var pandemicname: String = "",
    var email: String = "",
    var year_of_birth:String =""


){
    companion object{
        const val CONTACTS_TABLE_NAME="contacts"
    }
}

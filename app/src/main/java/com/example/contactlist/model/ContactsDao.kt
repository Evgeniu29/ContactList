package com.example.contactlist.model

import androidx.room.*

@Dao
interface ContactsDao{

    /**
     * SELECT -> This retrieve rows from a table in a database
     * FROM -> You specify the table to retrieve the rows from
     * ORDER BY -> This is just a sort algorithm
     * ASC -> Ascending order
     * WHERE -> This is a condition used to query data
     * */
    @Query("SELECT*FROM contacts ORDER BY id ASC")
    fun getContactList(): List<Contact>


    @Query("SELECT*FROM contacts WHERE id=:id")
    fun getContactItem(id: Int): Contact
    /**
     * @param todo is what we want to save in our database
     * so many conflict can occur when a data is to be saved, the strategy is used to handle such conflicts
     * Abort -> this aborts the transaction
     * Ignore -> this ignores and continues the transaction
     * Replace -> this replace the data
     * others includes fail, and roolback
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun removeContact(contact: Contact)

    @Query("DELETE FROM contacts")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(contactList: List<Contact>)
}
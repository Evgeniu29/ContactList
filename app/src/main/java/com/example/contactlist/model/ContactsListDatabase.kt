package com.example.contactlist.model
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [Contact::class], version = 2, exportSchema = false)
abstract class ContactsListDatabase: RoomDatabase(){

    /**
     * This is an abstract method that returns a dao for the Db
     * */
    abstract fun getContactsDao(): ContactsDao

    /**
     * A singleton design pattern is used to ensure that the database instance created is one
     * */

    companion object {
        val databaseName = "tododatabase"
        var contactListDatabase: ContactsListDatabase? = null

        fun getInstance(context: Context): ContactsListDatabase?{
            if (contactListDatabase == null){
                contactListDatabase = Room.databaseBuilder(context,
                    ContactsListDatabase::class.java,
                    ContactsListDatabase.databaseName).allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build()
            }
            return contactListDatabase
        }
    }
}

package com.example.contactlist

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlist.adapter.ContactsAdapter
import com.example.contactlist.model.Contact
import com.example.contactlist.model.ContactsListDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class MainActivity : AppCompatActivity(), ContactsAdapter.OnContactItemClickedListener{
    var contactList = mutableListOf<Contact>()

    protected lateinit var adapter: ContactsAdapter

    private var contactListDatabase: ContactsListDatabase? = null

    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contactListDatabase = ContactsListDatabase.getInstance(this)

        GlobalScope.launch {


            if (contactListDatabase?.getContactsDao()?.getContactList().isNullOrEmpty()) {
                contactList = getContacts()

            } else {
                contactList =
                    contactListDatabase?.getContactsDao()?.getContactList() as MutableList<Contact>
            }

        }

        adapter =  ContactsAdapter(contactList);

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.hasFixedSize()
        adapter?.setContactItemClickedListener(this)

        updateList.setOnClickListener(){
            GlobalScope.launch  {
                contactListDatabase!!.getContactsDao().deleteAll()
                contactList.clear();
                contactListDatabase?.getContactsDao()?.insertAll(getContacts())
                contactList = contactListDatabase?.getContactsDao()?.getContactList() as MutableList<Contact>

            }

            adapter.contactList = contactList

            adapter.notifyDataSetChanged()

        }

    }



        fun getContacts(): MutableList<Contact> {

            contactList.add(Contact(1, "", "Евгений", "Петров", "андреевич","evgeniupetrove@gmail.com","1988"))
            contactList.add(
                Contact(2,
                    "https://sociumin.com/img.php?i=https://sun9-22.userapi.com/c851428/v851428128/da66d/MWAeU3NitJ0.jpg?ava=1",
                    "Елена",
                    "Шокодько",
                    "Викторовна",
                    "semenuk@gmail.com",
                    "1988"
                )
            )

            contactList.add(
                Contact(3,
                    "https://i.work.ua/sent_photo/1/9/6/196f6d35d630e130e329de6fbef3678b.jpg",
                    "Наталья",
                    "Поддубняк",
                    "Анатольевна",
                    "mogirtm@gmail.com",
                    "1986"
                )
            )

            contactList.add(
                Contact(4,
                    "https://i.work.ua/sent_photo/c/0/8/c08916da4938198868177448bd028e76.jpg",
                    "Мирошниченко",
                    "Карина",
                    "Владимировна",
                    "pushenko.dr@gmail.com",
                    "1990"

                )
            )

            return contactList
        }

        private fun showToast(msg: String) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        override fun onContactItemClicked(contact: Contact) {

            val alertDialog = AlertDialog.Builder(this)
                .setItems(R.array.dialog_list, DialogInterface.OnClickListener { dialog, which ->
                    if (which==0){
                        val intent = Intent(this@MainActivity, AddTodoActivity::class.java)

                        intent.putExtra("id", contact.id)
                        intent.putExtra("image", contact.image)
                        intent.putExtra("name", contact.name)
                        intent.putExtra("surname", contact.surname)
                        intent.putExtra("pandemicname", contact.pandemicname)
                        intent.putExtra("email", contact.email)
                        intent.putExtra("year_of_birth", contact.year_of_birth)

                        startActivity(intent)

                    }else{
                        contactListDatabase?.getContactsDao()?.removeContact(contact)
                        onResume()
                    }
                    dialog.dismiss()
                })
                .create()
            alertDialog.show()
        }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch  {
            adapter?.contactList =
                contactListDatabase?.getContactsDao()?.getContactList() as MutableList<Contact>

        }

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.hasFixedSize()

    }

}








package com.example.room.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.room.R
import com.example.room.database.AppDataBase
import com.example.room.models.Contact
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class ContactActivity : AppCompatActivity() {

    lateinit var contact:Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val toolbar = findViewById<Toolbar>(R.id.tbContact)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadContact()
    }

    private fun loadContact() {
        // recivo la info de l cv

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etTelephone = findViewById<TextInputEditText>(R.id.etTelephone)

        val gson = Gson()
        val stringObj = intent.getStringExtra("contact")

        contact = gson.fromJson(stringObj, Contact::class.java)?: Contact(null, "","")

        if(contact.id != null){
            etName.setText(contact.name)
            etTelephone.setText(contact.telephone)
        }
    }

    fun saveContact(){
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etTelephone = findViewById<TextInputEditText>(R.id.etTelephone)

        //val name = etName.text.toString()
        //val telephone = etTelephone.text.toString()

        contact.name = etName.text.toString()
        contact.telephone = etTelephone.text.toString()


        if(contact.id == null){
            AppDataBase.getInstance(this).getDao().insertContact(contact)
        }else {
            AppDataBase.getInstance(this).getDao().updateContact(contact)
        }
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.contact_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.itemSave->{
                saveContact()
                true
            }
            R.id.itemDelete->{
                deleteContact()
                true
            }
            else ->    super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteContact() {
        AppDataBase.getInstance(this).getDao().deleteContact(contact)
        finish()
    }



}
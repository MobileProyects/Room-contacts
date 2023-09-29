package com.example.room.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.adapter.ContactAdapter
import com.example.room.adapter.OnItemClickListener
import com.example.room.database.AppDataBase
import com.example.room.models.Contact
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), OnItemClickListener {

    override fun OnItemClicked(contact: Contact) {
        val intent = Intent(this, ContactActivity::class.java)
        val gson = Gson()
        intent.putExtra("contact", gson.toJson(contact))
        startActivity(intent)
    }

    lateinit var contacts: List<Contact>
    lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.tbMain)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onResume() {
        super.onResume()
        loadContacts()

        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        contactAdapter = ContactAdapter(contacts, this)
        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    private fun loadContacts() {
        contacts = AppDataBase.getInstance(this).getDao().getAllContacts()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.itemAdd->{
                val intent = Intent (this, ContactActivity::class.java)
                startActivity(intent)
                true
            }
            else ->    super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}
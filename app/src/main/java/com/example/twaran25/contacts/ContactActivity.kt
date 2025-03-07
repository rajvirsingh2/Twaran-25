package com.example.twaran25.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twaran25.R
import com.example.twaran25.databinding.ActivityContactBinding
class ContactActivity : AppCompatActivity() {
    private lateinit var contactBinding: ActivityContactBinding // Declare lateinit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        contactBinding = ActivityContactBinding.inflate(layoutInflater) // Inflate here
        setContentView(contactBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        contactBinding.sportsSelectot.setEndIconOnClickListener { view ->
            val popupMenu = PopupMenu(this, view).apply {
                menuInflater.inflate(R.menu.sports_menu, menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_cricket -> {
                            contactBinding.sportsSelectot.editText?.setText(item.title)
                            true
                        }

                        R.id.menu_football -> {
                            contactBinding.sportsSelectot.editText?.setText(item.title)
                            true
                        }

                        R.id.menu_basketball -> {
                            contactBinding.sportsSelectot.editText?.setText(item.title)
                            true
                        }

                        R.id.menu_tennis -> {
                            contactBinding.sportsSelectot.editText?.setText(item.title)
                            true
                        }

                        else -> false
                    }
                }
            }
            popupMenu.show()
        }
        contactBinding.callIcon1.setOnClickListener {
           dialNumber (contactBinding.contactNumber1.text.toString())
        }
        contactBinding.callIcon2.setOnClickListener {
            dialNumber(contactBinding.contactNumber2.text.toString())
        }
        contactBinding.callIcon3.setOnClickListener {
            dialNumber(contactBinding.contactNumber3.text.toString())
        }
        contactBinding.callIcon4.setOnClickListener {
            dialNumber(contactBinding.contactNumber4.text.toString())
        }
        contactBinding.btnSubmit.setOnClickListener {
            val name=contactBinding.studentName.text.toString()
            val email=contactBinding.emailId.text.toString()
            val sportsName=contactBinding.sportsName.text.toString()
            Log.d("student detail","$name $email $sportsName")
            Toast.makeText(this,"Submitted",Toast.LENGTH_SHORT).show()
        }

    }
    private fun dialNumber(number: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$number")
        }
        startActivity(intent)
    }
}
